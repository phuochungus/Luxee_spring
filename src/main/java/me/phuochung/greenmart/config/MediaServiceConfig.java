package me.phuochung.greenmart.config;

import me.phuochung.greenmart.cloudinary.CloudinaryService;
import me.phuochung.greenmart.media.MediaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediaServiceConfig {

    @Bean
    public MediaService mediaService() {
        return new CloudinaryService();
    }
}
