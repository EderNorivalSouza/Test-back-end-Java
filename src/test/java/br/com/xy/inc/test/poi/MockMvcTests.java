package br.com.xy.inc.test.poi;

import br.com.xy.inc.poi.PoiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = PoiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class MockMvcTests {

    @Autowired
    MockMvc mockMvc;
    @Test
    void listAllPoisMokcTests() throws Exception {
        mockMvc
                .perform(get("http://localhost:8080/pois"))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$[*].id",containsInRelativeOrder(1,2,3,4,5,6,7)))
                .andExpect(jsonPath("$[*].name",containsInRelativeOrder("Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7")))
                .andExpect(jsonPath("$[*].coordX",containsInRelativeOrder(10,20,30,40,50,60,70)))
                .andExpect(jsonPath("$[*].coordY",containsInRelativeOrder(10,20,30,40,50,60,70)));
    }
    @Test
    void listPoiWithIdMockTest() throws Exception {
        Long id = 1L;
        mockMvc
                .perform(get("http://localhost:8080/pois/id?id="+ id))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath(".id", contains(1)))
                .andExpect(jsonPath(".name", contains("Test 1")))
                .andExpect(jsonPath(".coordX", contains(10)))
                .andExpect(jsonPath(".coordY", contains(10)));
    }
    @Test
    void filteredPoiMockTest() throws Exception{
        mockMvc
                .perform(post("http://localhost:8080/filter").content("dmax:20,coordX:20,coordY:20").contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id",containsInRelativeOrder(1,2,3)))
                .andExpect(jsonPath("$.name",containsInRelativeOrder("Test 1", "Test 2", "Test 3")))
                .andExpect(jsonPath("$.coordX",containsInRelativeOrder(10,20,30)))
                .andExpect(jsonPath("$.coordY",containsInRelativeOrder(10,20,30)));
    }


}
