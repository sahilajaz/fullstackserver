package com.fullstack.backend_application.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.backend_application.Model.User;
import com.fullstack.backend_application.Repository.UserRepository;
import com.fullstack.backend_application.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("john", "John Doe", "john.doe@example.com");
        testUser.setId(1L);
    }

    @Test
    public void createUserTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/create/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isCreated());
    }

    @Test
    public void fetchUsers() throws Exception {
        List<User> users = Arrays.asList(testUser);

        given(userService.getUsers()).willReturn(users);

        mvc.perform(MockMvcRequestBuilders.get("/get/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("john"))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));
    }

    @Test
    public void testGetUserById() throws Exception {

        given(userService.findUserThroughId(anyLong())).willReturn(testUser);

        mvc.perform(MockMvcRequestBuilders.get("/user/1")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        }

        @Test
        public  void deleteUserTest() throws Exception{

            mvc.perform(MockMvcRequestBuilders.delete("/user/1"))
                    .andExpect(status().isOk());
        }
    }
