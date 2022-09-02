package ir.mjimani.demo.service.product;

import ir.mjimani.demo.controller.product.dto.ReqCreateProductDto;
import ir.mjimani.demo.controller.product.dto.ResReviewPageDto;
import ir.mjimani.demo.dao.comment.CommentDao;
import ir.mjimani.demo.dao.comment.dto.ResCommentsDto;
import ir.mjimani.demo.dao.product.ProductDao;
import ir.mjimani.demo.dao.vote.VoteDao;
import ir.mjimani.demo.dao.vote.dto.ResVoteDto;
import ir.mjimani.demo.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductDao productDao;
    private final CommentDao commentDao;
    private final VoteDao voteDao;

    @Override
    public Product create(ReqCreateProductDto reqCreateProductDto) {
        reqCreateProductDto.validation();
        Product product = reqCreateProductDto.toProduct();
        product = productDao.create(product);
        return new Product(product.getId());
    }

    @Override
    public Page<ResReviewPageDto> getReviewPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ResReviewPageDto> productsPage = productDao.getPage(pageRequest);
        if (!productsPage.isEmpty()) {
            List<ResReviewPageDto> productList = productsPage.getContent();
            List<String> productIds = productList.stream().map(ResReviewPageDto::getId).collect(Collectors.toList());

            List<ResCommentsDto> comments = commentDao.getCommentsByProductIds(productIds);
            List<ResVoteDto> votes = voteDao.getAvgVoteByProductIds(productIds);

            productList.forEach(product -> {
                if (comments != null && !comments.isEmpty()) {
                    Optional<ResCommentsDto> resCommentsDtoOptional = comments.stream().filter(i -> i.getProductId().equals(product.getId())).findFirst();
                    if (resCommentsDtoOptional.isPresent()) {
                        ResCommentsDto resCommentsDto = resCommentsDtoOptional.get();
                        product.setTotalComments(resCommentsDto.getCount());
                        product.setLastComments(resCommentsDto.getLastComments());
                    }
                }

                if (votes != null && !votes.isEmpty()) {
                    Optional<ResVoteDto> resVoteDtoOptional = votes.stream().filter(i -> i.getProductId().equals(product.getId())).findFirst();
                    if (resVoteDtoOptional.isPresent()) {
                        ResVoteDto resVoteDto = resVoteDtoOptional.get();
                        product.setAvgVote(resVoteDto.getAvgVote());
                    }
                }
            });

            return new PageImpl<>(productList, pageRequest, productsPage.getTotalElements());
        }
        return productsPage;
    }

    @Override
    public Product getDetail(String productId) {
        return productDao.findOneByProductIdAndIsPublished(productId);
    }
}
