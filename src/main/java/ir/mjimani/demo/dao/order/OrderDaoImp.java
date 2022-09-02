package ir.mjimani.demo.dao.order;

import ir.mjimani.demo.domain.BaseDomain;
import ir.mjimani.demo.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderDaoImp implements OrderDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public Order create(Order order) {
        return mongoTemplate.save(order);
    }

    @Override
    public Boolean checkBuyerByProductIdUserId(String productId, String userId) {
        Query query = new Query()
                .addCriteria(
                        Criteria.where(Order.FN.productIds.name()).is(productId)
                                .and(BaseDomain.BFN.creatorId.name()).is(userId)
                );
        return mongoTemplate.exists(query, Order.class);
    }
}
