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
        final long timestamp = System.currentTimeMillis();
        HashMap<String, Object> params = new HashMap<>();
        params.put("timestamp", timestamp);
        String signature = cloudinary.apiSignRequest(params, cloudinary.config.apiKey);
        System.out.println("signature = " + signature);
        return new Signature(timestamp, signature);
    }
}