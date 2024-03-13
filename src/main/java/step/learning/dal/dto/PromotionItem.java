package step.learning.dal.dto;

import java.util.UUID;

public class PromotionItem {
    private UUID id;
    private UUID productId;
    private int count;

    public PromotionItem() {
    }

    public PromotionItem(UUID id, UUID productId, int count) {
        this.id = id;
        this.productId = productId;
        this.count = count;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
