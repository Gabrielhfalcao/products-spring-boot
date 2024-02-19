package br.com.api.products.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.products.service.ProductService;

@WebMvcTest
@AutoConfigureMockMvc
public class ProductResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ProductResource productResource;

    @MockBean
    private ProductService productService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void contextLoads() throws Exception {
        assertThat(productResource).isNotNull();
    }

    @SuppressWarnings("null")
    @Test
    void shouldReturnEmptyList() throws Exception {

        when(productService.findAll()).thenReturn(Collections.emptyList());

        String expectedJson = objectMapper.writeValueAsString(Collections.emptyList());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }

    @SuppressWarnings("null")
    @Test
    void shouldReturnOneProduct() throws Exception {
        final long id = 1_000L;
        final String name = "Product Name";
        final String brand = "Product Brand";

        List<ProductResponse> products = Collections.singletonList(new ProductResponse(id, name, brand));

        when(productService.findAll()).thenReturn(products);

        String expectedJson = objectMapper.writeValueAsString(products);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
        ;
    }

    @SuppressWarnings("null")
    @Test
    void whenCreatingProduct_shouldReturnHttpCode201() throws Exception {

        final long id = 1_000L;
        final String name = "Product Name";
        final String brand = "Product Brand";

        ProductRequest productRequest = new ProductRequest(name, brand);
        ProductResponse productResponse = new ProductResponse(id, name, brand);

        when(productService.insert(productRequest)).thenReturn(productResponse);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(productRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @SuppressWarnings("null")
    // @Test
    void whenCreatingProduct_shouldReturnProductDetail() throws Exception {

        final long id = 1_000L;
        final String name = "Product Name";
        final String brand = "Product Brand";

        ProductRequest productRequest = new ProductRequest(name, brand);
        ProductResponse productResponse = new ProductResponse(id, name, brand);

        Mockito.when(productService.insert(Mockito.eq(productRequest))).thenReturn(productResponse);

        System.out.println(objectMapper.writeValueAsString(productRequest));

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("""
                    {
                    "id": 4,
                    "name": "Product Name",
                    "brand": "Product Brand"
                }
                """
                ))
                ;
    }

}
