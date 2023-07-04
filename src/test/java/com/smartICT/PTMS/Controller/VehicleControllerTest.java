package com.smartICT.PTMS.Controller;

import com.smartICT.PTMS.Entities.Vehicle;
import com.smartICT.PTMS.Services.VehicleService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
@OverrideAutoConfiguration(enabled = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VehicleControllerTest {

    @Mock
    private MockMvc mockMvc;

    private VehicleController vehicleController;

    @MockBean
    private VehicleService vehicleService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.vehicleController = new VehicleController(this.vehicleService);
        mockMvc = MockMvcBuilders.standaloneSetup(this.vehicleController). build();
    }

    @Test
    void getVehicle() throws Exception{
        Vehicle vehicle = new Vehicle("06AA06","MAN",2020);

        when(vehicleController.getVehicle(anyString())).thenReturn(vehicle);

        mockMvc.perform(get("/api/stations/{sid}", 6L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plate").value("06AA06"))
                .andExpect(jsonPath("$.brand").value("MAN"))
                .andExpect(jsonPath("$.model").value(2020));
    }

    @Test
    void getAllVehicles() throws Exception{
        Vehicle vehicle1 = new Vehicle("06AA06","MAN",2020);
        Vehicle vehicle2 = new Vehicle("06Ab06","MAN",2020);

        List<Vehicle> vehicles = Arrays.asList(vehicle1,vehicle2);
        when(vehicleController.getAllVehicles()).thenReturn(vehicles);

        mockMvc.perform(get("/api/vehicles"))
                .andExpect(status().isOk());
    }

    @Test
    void creteVehicle() {
        Vehicle vehicle = new Vehicle("06AA06","MAN",2020);

        String url = "http://localhost:8080/api/vehicles";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate("omer","123");
        HttpEntity<Vehicle> entity = new HttpEntity<>(vehicle,headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void updateVehicle() {
        Vehicle vehicle = new Vehicle("06AA06","MAN",2020);

        String url = "http://localhost:8080/api/vehicles/{plate}";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate("omer","123");
        HttpEntity<Vehicle> entity = new HttpEntity<>(vehicle,headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT,entity,String.class);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void deleteVehicle() throws Exception{
        mockMvc.perform(delete("/api/vehicles/{plate}","06AA06"))
                .andExpect(status().isOk());
        verify(vehicleService,times(1)).deleteVehicle(eq("06AA06"));
    }
}