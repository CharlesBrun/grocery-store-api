package interview_mock_api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import interview_mock_api.enums.PromotionType;
import interview_mock_api.model.Item;
import interview_mock_api.model.Promotions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ItemService {

    private final RestTemplate restTemplate = new RestTemplate();
    
    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public Item addItem(Item item) {
        String id = generateRandomString(10);
        Item newItem = new Item(id, item.getName(), item.getPrice(), item.getPromotions());
        return newItem;
    }

    public List<Item> getItems() {
        String url = "http://localhost:8081/products";
        ResponseEntity<Item[]> responseEntity = restTemplate.getForEntity(url, Item[].class);
        Item[] itemsArray = responseEntity.getBody();
        return Arrays.asList(itemsArray);
    }

    public Item getItem(String id) {
        String url = "http://localhost:8081/products/" + id;
        return restTemplate.getForObject(url, Item.class);
    }

    public Map<String, String> calculateSavings(List<Item> items) {
        Double totalDiscountedPrice = 0.0;
        Double totalPrice = 0.0;
        List<Item> ItensJson = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("#.##");
        
        Map<String, Integer> promotionTypeQtyMap = new HashMap<>();
        Map<String, Integer> buyXGetYFreePromotionQtyMap = new HashMap<>();
        Map<String, Integer> flatPercentMap = new HashMap<>();
        
        for (Item item : items) {
            String itemId = item.getId();
            Item itemJson = getItem(itemId);
            ItensJson.add(itemJson);
            totalPrice += item.getPrice();

            List<Promotions> promotions = itemJson.getPromotions();
            if (promotions != null && !promotions.isEmpty()) {
    
                for (Promotions promotion : itemJson.getPromotions()) {
                    if (promotion.getType() == PromotionType.QTY_BASED_PRICE_OVERRIDE) {
                        promotionTypeQtyMap.put(itemId, promotionTypeQtyMap.getOrDefault(itemId, 0) + 1);
                    }
                    else if (promotion.getType() == PromotionType.BUY_X_GET_Y_FREE) {
                        buyXGetYFreePromotionQtyMap.put(itemId, buyXGetYFreePromotionQtyMap.getOrDefault(itemId, 0) + 1);
                    }
                    else if (promotion.getType() == PromotionType.FLAT_PERCENT) {
                        flatPercentMap.put(itemId, flatPercentMap.getOrDefault(itemId, 0) + 1);
                    }
                }
            }
        }

        for (Item item : ItensJson) {
            String itemId = item.getId();

            int promotionTypeQty = promotionTypeQtyMap.getOrDefault(itemId, 0);
            int buyXGetYFreePromotionQty = buyXGetYFreePromotionQtyMap.getOrDefault(itemId, 0);
            int flatPercentQty = flatPercentMap.getOrDefault(itemId, 0);

            List<Promotions> promotions = item.getPromotions();
            if (promotionTypeQty >= 2) {
                if (promotions != null && !promotions.isEmpty()) {
                    for (Promotions promotion : item.getPromotions()) {
                        if (promotion.getType() == PromotionType.QTY_BASED_PRICE_OVERRIDE) {
                            item.setPrice(promotion.getPrice() / 2);
                        }
                    }
                }
            }
            if (buyXGetYFreePromotionQty >= 2) {
                if (promotions != null && !promotions.isEmpty()) {
                    for (Promotions promotion : item.getPromotions()) {
                        if (promotion.getType() == PromotionType.BUY_X_GET_Y_FREE && buyXGetYFreePromotionQty >= 2) {
                            item.setPrice(0.0);
                            buyXGetYFreePromotionQtyMap.put(itemId, buyXGetYFreePromotionQtyMap.getOrDefault(itemId, 0) - 2);
                        }
                    }
                }
            }
            if (flatPercentQty >= 1) {
                if (promotions != null && !promotions.isEmpty()) {
                    for (Promotions promotion : item.getPromotions()) {
                        if (promotion.getType() == PromotionType.FLAT_PERCENT) {
                            double price = item.getPrice();
                            double discountedPrice = price - (price * (promotion.getAmount() / 100));
                            item.setPrice(discountedPrice);
                        }
                    }
                }
            }

            totalDiscountedPrice += item.getPrice();
        }

        Double savedPrice = totalPrice - totalDiscountedPrice;

        Map<String, String> savingsMap = new HashMap<>();
        savingsMap.put("totalPrice", df.format(totalPrice));
        savingsMap.put("totalDiscountedPrice", df.format(totalDiscountedPrice));
        savingsMap.put("savedPrice", df.format(savedPrice));

        return savingsMap;
    }

}
