package com.smartICT.PTMS.Controller;

import com.smartICT.PTMS.Entities.Route;
import com.smartICT.PTMS.Services.RouteService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteController.class)
@OverrideAutoConfiguration(enabled = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RouteControllerTest {

    @Mock
    private MockMvc mockMvc;

    private RouteController routeController;

    @MockBean
    private RouteService routeService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.routeController = new RouteController(this.routeService);
        mockMvc = MockMvcBuilders.standaloneSetup(this.routeController).build();
    }

    @Test
    void getRoute() throws Exception{
        Route route = new Route("06KYL20",6L);

        when(routeController.getRoute(anyString())).thenReturn(route);
        mockMvc.perform(get("/api/routes/{plate}","06KYL20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plate").value("06KYL20"))
                .andExpect(jsonPath("$.sid").value(6L));
    }

    @Test
    void getAllRoutes() throws Exception{
        Route route1 = new Route("06KYL20",6L);
        Route route2 = new Route("06EBS45",7L);

        List<Route> routes = Arrays.asList(route1,route2);
        when(routeController.getAllRoutes()).thenReturn(routes);
        mockMvc.perform(get("/api/routes/{plate}","06KYL20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plate").value("06KYL20"))
                .andExpect(jsonPath("$.sid").value(6L));
    }

    @Test
    void creteRoute() {
        Route route = new Route("06KYL20",6L);
        String url = "http://localhost:8080/api/routes";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate("omer","123");
        HttpEntity<Route> entity = new HttpEntity<>(route,headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void updateRoute() {
        Route updatedRoute = new Route("updated",6L);
        String url = "http://localhost:8080/api/routes/{plate}";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate("omer","123");
        HttpEntity<Route> entity = new HttpEntity<>(updatedRoute,headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT,entity,String.class);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void deleteRoute() throws Exception {
        mockMvc.perform(delete("/api/routes/{plate}",6L))
                .andExpect(status().isOk());
        verify(routeService,times(1)).deleteRoute(eq("06KYL20"));
    }
}