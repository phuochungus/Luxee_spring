package me.phuochung.luxee.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.option.value.Value;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    private final List<Option> testOptions = List.of(
            new Option(List.of(new Value("Red"), new Value("Blue")), "Color"),
            new Option(List.of(new Value("S"), new Value("L")), "Size"));

    private final List<Media> testMedia = List.of(
            new Media("http://test_url1", "test_public_id1",
                      Media.MediaType.IMAGE),
            new Media("http://test_url2", "test_public_id2",
                      Media.MediaType.IMAGE));

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateProductTest() throws Exception {
        Product testProduct = new Product();
        testProduct.setTitle("shouldCreateProductTest");
        testProduct.setOptions(testOptions);
        testProduct.setMedia(testMedia);

        mockMvc.perform(
                       post("/products").contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(
                                                testProduct)))
               .andExpectAll(status().isOk(),
                             content().string(matchesPattern("\\d+")))
               .andDo(result -> testProduct.setId(Long.parseLong(
                       result.getResponse().getContentAsString())));

        mockMvc.perform(get("/products/" + testProduct.getId().toString()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$..title").value(testProduct.getTitle()))
               .andExpectAll(jsonPath("$.media.[*].url").value(contains(
                                     testMedia.stream().map(Media::getUrl).toArray())),
                             jsonPath("$.options[*].name").value(contains(
                                     testOptions.stream()
                                                .map(Option::getName)
                                                .toArray())),
                             jsonPath("$.options[*].values[*].value").value(
                                     contains(testOptions.stream()
                                                         .map(Option::getValues)
                                                         .flatMap(List::stream)
                                                         .map(Value::getValue)
                                                         .toArray())),
                             jsonPath("$.id").value(
                                     testProduct.getId().intValue()));

    }

    @Test
    void ShouldNotCreateProductTest() throws Exception {
        Product testProduct = new Product();
        testProduct.setTitle("ShouldNotCreateProductTest");
        testProduct.setPrice(100.0);
        testProduct.setOptions(testOptions);
        mockMvc.perform(
                       post("/products").contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(
                                                testProduct)))
               .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetAllProductTest() throws Exception {
        mockMvc.perform(get("/products"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReplaceMedia() throws Exception {
        Product testProduct = new Product();
        testProduct.setTitle("shouldCreateProductTest");
        double randomPrice = new Random().nextDouble() * 100;
        testProduct.setPrice(randomPrice);
        testProduct.setMedia(testMedia);

        mockMvc.perform(
                       post("/products").contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(
                                                testProduct)))
               .andExpect(status().isOk())
               .andExpect(content().string(matchesPattern("\\d+")))
               .andDo(result -> testProduct.setId(
                       Long.parseLong(
                               result.getResponse().getContentAsString()))
               );

        mockMvc.perform(get("/products/" + testProduct.getId()))
               .andExpectAll(status().isOk(),
                             jsonPath("$.price").value(randomPrice),
                             jsonPath("$.media[*].url").value(
                                     containsInAnyOrder(testMedia.stream()
                                                                 .map(Media::getUrl)
                                                                 .toArray())));

        List<Media> newMedia = List.of(
                new Media(null, "http://new_url1", "new_public_id1",
                          Media.MediaType.IMAGE),
                new Media(null, "http://new_url2", "new_public_id2",
                          Media.MediaType.IMAGE));

        mockMvc.perform(
                       put("/products/" + testProduct.getId() + "/media").contentType(
                                                                                 MediaType.APPLICATION_JSON)
                                                                         .content(
                                                                                 objectMapper.writeValueAsString(
                                                                                         newMedia)))
               .andExpect(status().isOk());

        mockMvc.perform(get("/products/" + testProduct.getId()))
               .andExpectAll(status().isOk(), jsonPath("$.media[*].url").value(
                       containsInAnyOrder(newMedia.stream()
                                                  .map(Media::getUrl)
                                                  .toArray())));
    }

    @Test
    void UpdateNotFoundProduct() throws Exception {
        mockMvc.perform(
                       put("/products/0/media").contentType(MediaType.APPLICATION_JSON)
                                               .content("[]"))
               .andExpect(status().isNotFound());
    }

    //    @Test()
    void ShouldCreateVariants() throws Exception {
        Product testProduct = new Product();
        testProduct.setTitle("ShouldCreateVariants");
        testProduct.setOptions(testOptions);

        mockMvc.perform(
                       post("/products").contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(
                                                testProduct)))
               .andExpect(status().isOk())
               .andExpect(content().string(matchesPattern("\\d+")))
               .andDo(result -> testProduct.setId(Long.parseLong(
                       result.getResponse().getContentAsString())));

//        List<Variant> testVariants = List.of(
//                new Variant(null, null, null, List.of(
//                        new VariantOptionValue(
//                                null, null,
//                                testOptions.get(0).getValues().get(0).getId(),
//
//                                )
//                ), 100.0, null, null,
//                            null, null, null, null, null, null)
//
//
//        );
    }
}