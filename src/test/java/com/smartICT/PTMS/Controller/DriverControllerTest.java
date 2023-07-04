package com.smartICT.PTMS.Controller;

import com.smartICT.PTMS.Entities.Driver;
import com.smartICT.PTMS.Services.DriverService;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DriverController.class)
@OverrideAutoConfiguration(enabled = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DriverControllerTest {

    @Mock
    private MockMvc mockMvc;

    private DriverController driverController;
    @MockBean
    private DriverService driverService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.driverController = new DriverController(this.driverService);
        mockMvc = MockMvcBuilders.standaloneSetup(this.driverController).build();
    }

    @Test
    public void testGetDriver() throws Exception {
        Driver driver = new Driver();
        driver.setSsn(6L);
        driver.setFirstname("Omer");
        driver.setLastname("Saglam");
        driver.setDob(LocalDate.of(2001,3,7));
        driver.setEmail("omersaglam@gmail.com");
        driver.setPhone(05425105551L);

        when(driverController.getDriver(anyLong())).thenReturn(driver);

        mockMvc.perform(get("/api/drivers/{ssn}", 6L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ssn").value(6L))
                .andExpect(jsonPath("$.firstname").value("Omer"))
                .andExpect(jsonPath("$.lastname").value("Saglam"))
               // .andExpect(jsonPath("$.dob").value(LocalDate.of(2023,3,7)))
                .andExpect(jsonPath("$.email").value("omersaglam@gmail.com"))
                .andExpect(jsonPath("$.phone").value(05425105551L));
    }

    @Test
    public void testGetAllDrivers() throws Exception {
        Driver driver1 = new Driver();
        driver1.setSsn(7L);
        driver1.setFirstname("ali");
        driver1.setLastname("Saglam");
        driver1.setDob(LocalDate.of(2001,03,07));
        driver1.setEmail("omersaglam@gmail.com");
        driver1.setPhone(05425105551L);

        Driver driver2 = new Driver();
        driver2.setSsn(6L);
        driver2.setFirstname("Omer");
        driver2.setLastname("Saglam");
        driver2.setDob(LocalDate.of(2001,03,07));
        driver2.setEmail("omersaglam@gmail.com");
        driver2.setPhone(05425105551L);

        List<Driver> drivers = Arrays.asList(driver1, driver2);

        when(driverController.getAllDrivers()).thenReturn(drivers);

        mockMvc.perform(get("/api/drivers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].ssn").value(6L))
                .andExpect(jsonPath("$[1].firstname").value("Omer"))
                .andExpect(jsonPath("$[1].lastname").value("Saglam"))
                //.andExpect(jsonPath("$[1].dob").value(LocalDate.of(2023,3,7)))
                .andExpect(jsonPath("$[1].email").value("omersaglam@gmail.com"))
                .andExpect(jsonPath("$[1].phone").value(05425105551L));
    }
        @Test
        public void testCreateDriver() throws Exception {

            Driver driver1 = new Driver();
            driver1.setSsn(6L);
            driver1.setFirstname("Omer");
            driver1.setLastname("Saglam");
            driver1.setDob(LocalDate.of(2001,03,07));
            System.out.println(driver1.getDob());
            driver1.setEmail("omersaglam@gmail.com");
            driver1.setPhone(05425105551L);

            HttpHeaders headers = new HttpHeaders();
            TestRestTemplate restTemplate = new TestRestTemplate("omer","123");
            HttpEntity<Driver> entity = new HttpEntity<>(driver1,headers);

            ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/api/drivers", HttpMethod.POST,entity,String.class);
            Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

     /*
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/drivers"))
                    .andExpect(status().isOk()).andExpect(content().json((new Driver (6L,"omer","saglam", LocalDate.of(2001,3,7),"omersad",523525L))
                            .contentType(APPLICATION_JSON))
                    .andExpect((ResultMatcher) jsonPath(String.valueOf(driver1)));

            JSONObject driver = new JSONObject();
                    driver.put( "ssn", 8L);
                    driver.put("firstname", "post");
                    driver.put( "lastname", "saglam");
                    driver.put("dob","2001-07-03");
                    driver.put("email", "test@post.com");
                    driver.put("phone", "0555555");

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/drivers")
                            .content(driver)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED).accept(APPLICATION_JSON)
                            .andExpect(status().isOk())
            );
  */
            /*
            // I/O error on POST request for "http://localhost:8080/api/drivers": Connection refused
            HttpHeaders headers = new HttpHeaders();
            TestRestTemplate restTemplate = new TestRestTemplate();
            HttpEntity<Driver> entity = new HttpEntity<>(driver1,headers);

            ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/drivers"), HttpMethod.POST,entity,String.class);
            */
        }

    @Test
    public void testUpdateDriver() throws Exception {
        Driver updatedDriver = new Driver();
        updatedDriver.setSsn(6L);
        updatedDriver.setFirstname("Updated");
        updatedDriver.setLastname("up");
        updatedDriver.setDob(LocalDate.of(2001,03,07));
        updatedDriver.setEmail("omersaglam@gmail.com");
        updatedDriver.setPhone(05425105551L);

        String url = "http://localhost:8080/api/drivers/{ssn}";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("omer", "123");

        // TestRestTemplate'i kullanarak PUT isteği gönderin
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpEntity<Driver> requestEntity = new HttpEntity<>(updatedDriver, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                String.class,
                6L
        );
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteDriver() throws Exception {
        mockMvc.perform(delete("/api/drivers/{ssn}", 6L))
                .andExpect(status().isOk());

        verify(driverService, times(1)).deleteDriver(eq(6L));
    }
}