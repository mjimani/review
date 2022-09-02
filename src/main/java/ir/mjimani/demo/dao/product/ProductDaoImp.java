package ir.mjimani.demo.dao.product;

import ir.mjimani.demo.domain.BaseDomain;
import ir.mjimani.demo.domain.Product;
import ir.mjimani.demo.model.ResCountDto;
import ir.mjimani.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import ir.mjimani.demo.controller.product.dto.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDaoImp implements ProductDao {

    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<ResReviewPageDto> getPage(PageRequest pageRequest) {
        long skip = (long) pageRequest.getPageNumber() * pageRequest.getPageSize();
        if (skip < 0) {
            skip = 0L;
        }
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where(Product.FN.isPublish.name()).is(true)),
                Aggregation.sort(Sort.Direction.DESC, BaseDomain.BFN.creationDate.name()),
                Aggregation.skip(skip),
                Aggregation.limit(pageRequest.getPageSize())
        );

        List<ResReviewPageDto> productList = mongoTemplate.aggregate(aggregation, Product.class, ResReviewPageDto.class).getMappedResults();
        ResCountDto resCountDto = new ResCountDto();
        if (!productList.isEmpty()) {
            Aggregation countAggregation = Aggregation.newAggregation(
                    Aggregation.match(Criteria.where(Product.FN.isPublish.name()).is(true)),
                    Aggregation.count().as(ResCountDto.FN.count.name())
            );
            resCountDto = mongoTemplate.aggregate(countAggregation, Product.class, ResCountDto.class).getUniqueMappedResult();
        }
        return new PageImpl<>(productList, pageRequest, resCountDto.getCount());
    }

    @Override
    public Product findOneByProductIdAndIsPublished(String productId) {
        Query query = new Query()
                .addCriteria(Criteria.where(BaseDomain.BFN.id.name()).is(productId)
                .and(Product.FN.isPublish.name()).is(true));

        return mongoTemplate.findOne(query, Product.class);
    }

}
