package com.pale.clientrestproject.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllClients() throws Exception {
        RequestBuilder requestBuilder = get("/client/api/v1/clients");
        MvcResult results = mockMvc.perform(requestBuilder)
                .andReturn();

        int responseCode = results.getResponse().getStatus();
        assertEquals(200, responseCode);
    }

    @Test
    void addClient() throws Exception {
        RequestBuilder requestBuilder = post("/client/api/v1/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{    \"firstName\": \"Paul\",\n" +
                        "    \"lastName\": \"Thelele\",\n" +
                        "    \"mobileNumber\": \"0846521345\",\n" +
                        "    \"idNumber\": \"Please Enter Valid SA ID\",\n" + // ID validated to SA ID only
                        "    \"physicalAddress\": \"285 West Ave, Centurio Die Hoewes\"}");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andReturn();
        int responseCode = result.getResponse().getStatus();
        System.out.println(responseCode);
        assertEquals(201, responseCode);
    }

    @Test
    void updateClient() throws Exception {
        RequestBuilder requestBuilder = post("/client/api/v1/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{    \"firstName\": \"Paul\",\n" +
                        "    \"lastName\": \"Thelele\",\n" +
                        "    \"mobileNumber\": \"0846521345\",\n" +
                        "    \"idNumber\": \"Please Enter Valid SA ID\",\n" + // ID validated to SA ID only
                        "    \"physicalAddress\": \"285 West Ave, Centurio Die Hoewes\"}");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andReturn();
        int responseCode = result.getResponse().getStatus();
        System.out.println(responseCode);
        assertEquals(201, responseCode);
    }

    @Test
    void getByFirstName() throws Exception {
        RequestBuilder requestBuilder = get("/client/api/v1/idnumber")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Wilbur\"}");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andReturn();
        int resultResponseCode = result.getResponse().getStatus();
        assertEquals(200, resultResponseCode);

    }

    @Test
    void getByLastName() throws Exception {
        RequestBuilder requestBuilder = get("/client/api/v1/idnumber")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"lastName\": \"Thelele\"}");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andReturn();
        int resultResponseCode = result.getResponse().getStatus();
        assertEquals(200, resultResponseCode);
    }

    @Test
    void getByIdNumber() throws Exception {
        RequestBuilder requestBuilder = get("/client/api/v1/idnumber")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idNumber\": \"8506257777089\"}");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andReturn();
        int resultResponseCode = result.getResponse().getStatus();
        assertEquals(200, resultResponseCode);
    }

    @Test
    void getByMobileNumber() throws Exception {
        RequestBuilder requestBuilder = get("/client/api/v1/idnumber")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"mobileNumber\": \"0826542132\"}");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andReturn();
        int resultResponseCode = result.getResponse().getStatus();
        assertEquals(200, resultResponseCode);
    }
}