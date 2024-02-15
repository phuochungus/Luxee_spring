package me.phuochung.luxee.media;

import org.springframework.web.server.ResponseStatusException;

public interface MediaService {
    Signature generateUploadSignature();

    void deleteAsset(String publicId) throws ResponseStatusException;
}
