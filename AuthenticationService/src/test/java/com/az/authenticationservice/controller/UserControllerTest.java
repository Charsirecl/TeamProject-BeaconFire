package com.az.authenticationservice.controller;

import com.az.authenticationservice.domain.User;
import com.az.authenticationservice.security.JwtFilter;
import com.az.authenticationservice.service.UserRoleService;
import com.az.authenticationservice.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @MockBean
    private UserService userService;

    @MockBean
    private UserRoleService userRoleService;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @WithMockUser(username = "admin",roles={"HR"})
    void testGetUser() throws Exception {
        User mockUser = new User(
                23L,
                "kayk",
                "kay@example.com",
                "$10$KrDXHVhZUmooKqzaiQO6xOYphO1lUZqylm82s7tLyc6yM1jZNG9Vq",
                LocalDateTime.now(),
                true,
                new HashSet<>()

        );
        Mockito.when(userService.getUserById(23)).thenReturn(mockUser);

        //JWT token
        String mockJwtToken = "${security.jwt.mockJwtToken}";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/user?userId=23")
                        //.header("Authorization",mockJwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Gson gson = new Gson();
        User actualUser = gson.fromJson(mvcResult.getResponse().getContentAsString(), User.class);
        assertEquals(mockUser.toString(), actualUser.toString());
    }
}
