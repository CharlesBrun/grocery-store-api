package interview_mock_api.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import interview_mock_api.model.Item;

import java.util.Arrays;
import java.util.List;

@Repository
public class ItemRepository {

    private final RestTemplate restTemplate = new RestTemplate();

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