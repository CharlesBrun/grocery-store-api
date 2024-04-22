package interview_mock_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interview_mock_api.model.Item;
import interview_mock_api.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
    
    @Autowired
    private ItemService service;

    @GetMapping()
    public List<Item> getItems(){
        return service.getItems();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable String id){
        return service.getItem(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        return ResponseEntity.ok(service.addItem(item));
    }
}
