package me.phuochung.luxee.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    public Collection createCollection(Collection collection) {
        return collectionRepository.save(collection);
    }

    public Collection getCollection(Long id) throws ResponseStatusException {
        return collectionRepository.findById(id)
                                   .orElseThrow(
                                           () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND,
                                                   "Collection not found"
                                           ));
    }

    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }
}
