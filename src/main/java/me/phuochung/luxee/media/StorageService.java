package me.phuochung.luxee.media;

import org.springframework.web.server.ResponseStatusException;

public interface StorageService {
    Signature generateUploadSignature();

    void deleteAsset(String publicId) throws ResponseStatusException;
}
