package me.phuochung.luxee.media;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Media {
    @Id
    @GeneratedValue
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "\"url\" is required")
    @URL
    private String url;

    private String publicId;

    @Column(nullable = false)
    @NotNull
    private MediaType mediaType = MediaType.IMAGE;

    public Media(String url, String publicId, MediaType mediaType) {
        this.url = url;
        this.publicId = publicId;
        this.mediaType = mediaType;
    }

    public enum MediaType {
        IMAGE, VIDEO
    }
}
