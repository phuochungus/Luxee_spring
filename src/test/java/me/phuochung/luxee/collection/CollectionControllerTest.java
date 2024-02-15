package me.phuochung.luxee.collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.phuochung.luxee.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CollectionControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ShouldCreateCollection() throws Exception {
        Product testProduct = new Product();
        testProduct.setTitle("ShouldCreateCollection");
        testProduct.setPrice(1000.0);

        Product testProduct2 = new Product();

        Collection testCollection = new Collection();
        testCollection.setName("ShouldCreateCollection");
        testCollection.setDescription("This is a test collection");
        testCollection.setProducts(List.of(testProduct2));

        mockMvc.perform(
                       post("/products")
                               .contentType(MediaType.APPLICATION_JSON)
                               .content(objectMapper.writeValueAsString(testProduct))
               )
               .andExpect(status().isOk())
               .andDo(result -> testProduct2.setId(Long.parseLong(
                       result.getResponse().getContentAsString())));

        mockMvc.perform(
                       post("/collections")
                               .contentType(MediaType.APPLICATION_JSON)
                               .content(
                                       objectMapper.writeValueAsString(testCollection))
               )
               .andExpect(status().isOk())
               .andDo(result -> testCollection.setId(Long.parseLong(
                       result.getResponse().getContentAsString())));

        mockMvc.perform(
                get("/collections/" + testCollection.getId().toString())
        ).andExpectAll(
                status().isOk(),
                jsonPath("$.id").value(testCollection.getId().intValue()),
                jsonPath("$.name").value(testCollection.getName()),
                jsonPath("$.products[0].id").value(
                        testProduct2.getId().intValue())
        );
    }

    @Test
    void ShouldNotCreateCollectionWithoutName() throws Exception {
        Collection testCollection = new Collection();
        testCollection.setDescription("This is a test collection");

        mockMvc.perform(
                post("/collections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(testCollection))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void ShouldGetAllCollections() throws Exception {
        mockMvc.perform(get("/collections"))
               .andExpectAll(
                       status().isOk(),
                       jsonPath("$").isArray()
               );
    }
}