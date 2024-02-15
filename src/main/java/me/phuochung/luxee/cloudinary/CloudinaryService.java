package me.phuochung.luxee.cloudinary;

import com.cloudinary.Cloudinary;
import me.phuochung.luxee.media.MediaService;
import me.phuochung.luxee.media.Signature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;

@Service
public class CloudinaryService implements MediaService {
    private final Cloudinary cloudinary = new Cloudinary();

    public Signature generateUploadSignature() {
        long timestamp = System.currentTimeMillis() / 1000;
        HashMap<String, Object> params = new HashMap<>();
        params.put("timestamp", timestamp);
        String signature = cloudinary.apiSignRequest(params,
                                                     cloudinary.config.apiSecret);
        return new Signature(timestamp, signature);
    }

    @Override
    public void deleteAsset(String publicId) throws ResponseStatusException {
        HashMap<String, String> options = new HashMap<>();
        options.put("invalidate", "true");
        try {
            cloudinary.uploader().destroy(publicId, options);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to delete asset");
        }
    }
}
