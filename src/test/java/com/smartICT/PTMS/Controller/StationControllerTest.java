package com.smartICT.PTMS.Controller;

import com.smartICT.PTMS.Entities.Station;
import com.smartICT.PTMS.Services.StationService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StationController.class)
@OverrideAutoConfiguration(enabled = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StationControllerTest {

    @Mock
    private MockMvc mockMvc;

    private StationController stationController;

    @MockBean
    private StationService stationService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.stationController = new StationController(this.stationService);
        mockMvc = MockMvcBuilders.standaloneSetup(this.stationController).build();
    }

    @Test
    void getStation() throws Exception{
        Station station = new Station(6L,"mecidiye","kizlarpinari");

        when(stationController.getStation(anyLong())).thenReturn(station);

        mockMvc.perform(get("/api/stations/{sid}", 6L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sid").value(6L))
                .andExpect(jsonPath("$.sname").value("mecidiye"))
                .andExpect(jsonPath("$.street").value("kizlarpinari"));
    }

    @Test
    void getAllStations() throws Exception {
        Station station1 = new Station(6L,"mecidiye","kizlarpinari");
        Station station2 = new Station(7L,"Yigit","Elmas street");

        List<Station> stations = Arrays.asList(station1,station2);

        when(stationController.getAllStations()).thenReturn(stations);

        mockMvc.perform(get("/api/stations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sid").value(6L))
                .andExpect(jsonPath("$[0].sname").value("mecidiye"))
                .andExpect(jsonPath("$[0].street").value("kizlarpinari"));
    }

    @Test
    void creteStation() {
        Station station = new Station(6L,"mecidiye","kizlarpinari");
        String url = "http://localhost:8080/api/stations";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate("omer","123");
        HttpEntity<Station> entity = new HttpEntity<>(station,headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void updateStation() {
        Station updatedStation = new Station(7L,"update_test","kizlarpinari");
        String url = "http://localhost:8080/api/drivers/{sid}";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate("omer","123");
        HttpEntity<Station> requestEntity = new HttpEntity<>(updatedStation,headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                String.class,
                6L // Güncellenecek sürücünün id'sini burada belirtin
        );
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void deleteStation() throws Exception{
        mockMvc.perform(delete("/api/stations/{sid}",6L))
                .andExpect(status().isOk());
        verify(stationService,times(1)).deleteStation(eq(6L));
    }
}