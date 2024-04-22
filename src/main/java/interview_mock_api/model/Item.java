package interview_mock_api.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about an item")
public class Item {

    @ApiModelProperty(hidden = true)
    private String id;
    private String name;
    private int price;
    private List<Promotions> promotions;

    public Item() {}
    public Item(String id, String name, int price, List<Promotions> promotions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.promotions = promotions;
    }

    public Item(String name, int price, List<Promotions> promotions) {
        this.name = name;
        this.price = price;
        this.promotions = promotions;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", promotions=" + promotions +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public List<Promotions> getPromotions() {
        return promotions;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPromotions(List<Promotions> promotions) {
        this.promotions = promotions;
    }

}
