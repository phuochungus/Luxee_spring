package me.phuochung.luxee.media;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Media {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "\"url\" is required")
    private String url;

    private String publicId;
    private MediaType mediaType = MediaType.IMAGE;

    public enum MediaType {
        IMAGE, VIDEO
    }
}
