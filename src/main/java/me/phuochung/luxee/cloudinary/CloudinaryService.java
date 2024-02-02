package me.phuochung.luxee.cloudinary;

import com.cloudinary.Cloudinary;
import me.phuochung.luxee.media.MediaService;
import me.phuochung.luxee.media.Signature;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CloudinaryService implements MediaService {
    private final Cloudinary cloudinary = new Cloudinary();

    public Signature generateUploadSignature() {
        long timestamp = System.currentTimeMillis() / 1000;
        HashMap<String, Object> params = new HashMap<>();
        params.put("timestamp", timestamp);
        String signature = cloudinary.apiSignRequest(params, cloudinary.config.apiSecret);
        return new Signature(timestamp, signature);
    }
}
