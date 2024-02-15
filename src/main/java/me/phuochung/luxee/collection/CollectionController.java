package me.phuochung.luxee.collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @PostMapping
    public Long createCollection(@RequestBody Collection collection) {
        return collectionService.createCollection(collection).getId();
    }

    @GetMapping("/{id}")
    public Collection getCollection(@PathVariable Long id) {
        return collectionService.getCollection(id);
    }

    @GetMapping
    public List<Collection> getAllCollections() {
        return collectionService.getAllCollections();
    }
}
