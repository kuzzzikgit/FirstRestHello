package hello.web.controllers;

import hello.core.model.Item;
import hello.core.repository.ItemsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class ItemsController {

    private ItemsRepository itemsRepository;

    public ItemsController(ItemsRepository itemsRepository) {

        this.itemsRepository = itemsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Item> list() {

        return itemsRepository.getAllItems();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Item> create(@RequestBody Item newItem) {
        Item createdItem = itemsRepository.create(newItem);
        URI location = UriComponentsBuilder.fromPath("/hello/")
                .path(String.valueOf(createdItem.getId()))
                .build().toUri();
        return ResponseEntity.created(location).body(createdItem);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Item> update(
            @PathVariable long id,
            @RequestBody Item newItem) {
        Item createdItem = itemsRepository.edit(id, newItem);
        if (createdItem == null) {
            return ResponseEntity.notFound().build();
        }
        URI location = UriComponentsBuilder.fromPath("/hello/")
                .path(String.valueOf(createdItem.getId())).build().toUri();
        return ResponseEntity.created(location).body(createdItem);
    }
}
