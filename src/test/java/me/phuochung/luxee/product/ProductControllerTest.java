package me.phuochung.luxee.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.option.Option;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    private final List<Option> testOptions = List.of(
            new Option(
                    null,
                    null,
                    null,
                    List.of("Red", "Blue"),
                    "Color"
            ),
            new Option(
                    null,
                    null,
                    null,
                    List.of("S", "M"),
                    "Size"
            ));
    private final List<Media> testMedia = List.of(
            new Media(null, null, null, "test_url1", "test_public_id1", Media.MediaType.IMAGE),
            new Media(null, null, null, "test_url2", "test_public_id2", Media.MediaType.IMAGE)
    );

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void shouldCreateProductTest() throws Exception {
        Product testProduct = new Product();
        testProduct.setTitle("shouldCreateProductTest");
        testProduct.setOptions(testOptions);

        mockMvc
                .perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testProduct)))
                .andExpect(status().isOk())
                .andExpect(content().string(matchesPattern("\\d+")))
                .andDo(result -> {
                    testProduct.setId(Long.parseLong(result.getResponse().getContentAsString()));
                });

        mockMvc
                .perform(get("/products/" + testProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title").value(testProduct.getTitle()))
                .andExpect(jsonPath("$..options[0].name").value(testOptions.get(0).getName()))
                .andExpect(jsonPath("$..options[1].name").value(testOptions.get(1).getName()))
                .andExpect(jsonPath("$..options[0].values[0]").value(testOptions.get(0).getValues().get(0)))
                .andExpect(jsonPath("$..options[0].values[1]").value(testOptions.get(0).getValues().get(1)))
                .andExpect(jsonPath("$..options[1].values[0]").value(testOptions.get(1).getValues().get(0)))
                .andExpect(jsonPath("$..options[1].values[1]").value(testOptions.get(1).getValues().get(1)))
                .andExpect(jsonPath("$.id").value(testProduct.getId().intValue()));


    }

    @Test
    @Order(2)
    void ShouldNotCreateProductTest() throws Exception {
        Product testProduct = new Product();
        testProduct.setTitle("ShouldNotCreateProductTest");
        testProduct.setPrice(100.0);
        testProduct.setOptions(testOptions);

        mockMvc
                .perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Product())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(2)
    void shouldGetAllProductTest() throws Exception {
        mockMvc
                .perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(3)
    void shouldAddMediaTest() throws Exception {
        Product testProduct = new Product();
        testProduct.setTitle("shouldAddMediaTest");
        testProduct.setPrice(100.0);
        testProduct.setMedia(testMedia);

        mockMvc
                .perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testProduct)))
                .andExpect(status().isOk())
                .andExpect(content().string(matchesPattern("\\d+")))
                .andDo(result -> {
                    testProduct.setId(Long.parseLong(result.getResponse().getContentAsString()));
                });

        mockMvc
                .perform(put("/products/" + testProduct.getId() + "/media")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testMedia)))
                .andExpect(status().isOk());

        mockMvc
                .perform(get("/products/" + testProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..media[0].url").value(testMedia.get(0).getUrl()))
                .andExpect(jsonPath("$..media[1].url").value(testMedia.get(1).getUrl()));

    }

    @Test
    @Order(4)
    void addVariants() {
    }
}