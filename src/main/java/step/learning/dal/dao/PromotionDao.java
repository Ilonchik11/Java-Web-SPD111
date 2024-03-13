package step.learning.dal.dao;

import step.learning.dal.dto.PromotionItem;

import java.util.UUID;

public class PromotionDao {
    public PromotionItem[] getPromotion() {
        return new PromotionItem[] {
                new PromotionItem(UUID.randomUUID(), UUID.randomUUID(), 1),
                new PromotionItem(UUID.randomUUID(), UUID.randomUUID(), 2),
                new PromotionItem(UUID.randomUUID(), UUID.randomUUID(), 3),
        };
    }
}
