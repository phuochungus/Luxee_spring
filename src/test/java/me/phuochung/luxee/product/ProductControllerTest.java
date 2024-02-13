package me.phuochung.luxee.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import me.phuochung.luxee.media.Media;
import me.phuochung.luxee.option.Option;
import me.phuochung.luxee.option.value.Value;
import me.phuochung.luxee.variant.Variant;
import me.phuochung.luxee.variantoptionvalue.VariantOptionValue;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.hamcrest.Matchers.*;
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

    @Test()
    void ShouldCreateVariants() throws Exception {
        Product testProduct = new Product();
        testProduct.setTitle("ShouldCreateVariants");
        testProduct.setOptions(testOptions);

        Product finalTestProduct = testProduct;
        mockMvc.perform(
                       post("/products").contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(
                                                testProduct)))
               .andExpect(status().isOk())
               .andExpect(content().string(matchesPattern("\\d+")))
               .andDo(result -> finalTestProduct.setId(
                       Long.parseLong(
                               result.getResponse().getContentAsString()))
               );

        MvcResult result = mockMvc.perform(
                                          get("/products/" + testProduct.getId()))
                                  .andExpect(status().isOk())
                                  .andReturn();

        testProduct = objectMapper.readValue(
                result.getResponse().getContentAsString(), Product.class);

        List<Set<VariantOptionValue>> listList = new ArrayList<>();


        testProduct.getOptions().forEach(option -> {
            Set<VariantOptionValue> variantOptionValues = new HashSet<>();
            option.getValues().forEach(value -> {
                variantOptionValues.add(
                        new VariantOptionValue(value.getId(), option.getId()));
            });
            listList.add(variantOptionValues);
        });

        var cartesianProduct = Sets.cartesianProduct(listList);

        List<Variant> variants = new ArrayList<>();

        for (List<VariantOptionValue> variantOptionValues : cartesianProduct) {
            variants.add(
                    new Variant(variantOptionValues, 100.0));
        }

        mockMvc.perform(
                       put("/products/" + testProduct.getId() + "/variants")
                               .contentType(MediaType.APPLICATION_JSON)
                               .content(objectMapper.writeValueAsString(variants)))
               .andExpect(status().isOk());

        mockMvc.perform(get("/products/" + testProduct.getId()))
               .andExpectAll(
                       jsonPath("$.variants[*].id", hasSize(4))
               );
    }

}