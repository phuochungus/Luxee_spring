package me.phuochung.luxee.config;

import me.phuochung.luxee.cloudinary.CloudinaryService;
import me.phuochung.luxee.media.MediaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediaServiceConfig {

    @Bean
    public MediaService mediaService() {
        return new CloudinaryService();
    }
}
