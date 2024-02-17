package me.phuochung.luxee.config;

import me.phuochung.luxee.cloudinary.CloudinaryService;
import me.phuochung.luxee.media.StorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediaServiceConfig {

    @Bean
    public StorageService storageService() {
        return new CloudinaryService();
    }
}
