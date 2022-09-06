package web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.globaltest.configuration.controller.ConfigController;
import org.globaltest.configuration.entity.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConfigController.class)
class ConfigControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    void saveConfig() throws Exception {
        Config config = Config.builder()
                .serialNumber("sdkjfj23874")
                .ip("192.168.1.101")
                .netmask("255.255.255.0")
                .build();
        String configJson = mapper.writeValueAsString(config);
        mockMvc.perform(post("api/v1/configurations/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(configJson))
                .andExpect(status().isOk());
    }

    @Test
    void getConfigBySerialNumber() throws Exception{
        mockMvc.perform(get("api/v1/configurations/sdkjfj23874"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllConfigurations() throws Exception{
        mockMvc.perform(get("api/v1/configurations/all"))
                .andExpect(status().isOk());
    }
}