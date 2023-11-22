package TP.Devops.SpringMongoApp.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import TP.Devops.SpringMongoApp.model.Employee;
import TP.Devops.SpringMongoApp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() throws Exception {
        // Mock data
        List<Employee> employees = Arrays.asList(
                new Employee(1L,"test","test","test","test","test","test1"),
                new Employee(2L,"test","test","test","test","test","test2")
        );

        // Mock the service method to return the above data
        Mockito.when(employeeService.findAllEmployees()).thenReturn(employees);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[0].email").value("lalal"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("test"))
                .andExpect(jsonPath("$[1].email").value("test"));
    }

    // Add other test methods
}
