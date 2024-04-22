package interview_mock_api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import interview_mock_api.model.Item;

import java.util.Arrays;
import java.util.List;
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

}
