package interview_mock_api.model;

public class Promotions {
    private String id;
    private String type;
    private int price;
    private int amount;
    private int required_qty;
    private int free_qty;

    public Promotions() {}
    public Promotions(String id, String type, int price, int amount, int required_qty, int free_qty) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

}