package interview_mock_api.model;

import interview_mock_api.enums.PromotionType;

public class Promotions {
    private String id;
    private PromotionType type;
    private Double price;
    private Double amount;
    private int required_qty;
    private int free_qty;

    public Promotions() {}
    public Promotions(String id, PromotionType type, Double price, Double amount, int required_qty, int free_qty) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.required_qty = required_qty;
        this.free_qty = free_qty;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", required_qty=" + required_qty +
                ", free_qty=" + free_qty +
                '}';
    }

    public int getFree_qty() {
        return free_qty;
    }

    public void setFree_qty(int free_qty) {
        this.free_qty = free_qty;
    }

    public int getRequired_qty() {
        return required_qty;
    }

    public void setRequired_qty(int required_qty) {
        this.required_qty = required_qty;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public PromotionType getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(PromotionType type) {
        this.type = type;
    }

}