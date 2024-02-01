package me.phuochung.greenmart.media;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/media")
public class MediaController {
    private final MediaService mediaService;

    @GetMapping("/signature")
    public Signature getSignature() {
        return mediaService.generateUploadSignature();
    }
}
