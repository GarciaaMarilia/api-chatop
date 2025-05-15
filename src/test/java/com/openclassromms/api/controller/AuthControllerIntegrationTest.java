package com.openclassromms.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassromms.api.model.LoginRequest;
import com.openclassromms.api.model.RegisterRequest;
import com.openclassromms.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldRegisterUserSuccessfully() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("teste@exemplo.com");
        request.setPassword("senha123");
        request.setName("Usuário Teste");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        boolean userExists = userRepository.existsByEmail("teste@exemplo.com");
        assertTrue(userExists);
    }

    //@Test
    //public void shouldLoginUserSuccessfully() throws Exception {
    //    LoginRequest request = new LoginRequest();
    //    request.setLogin("teste@exemplo.com");
    //    request.setPassword("senha123");
    //}
}
