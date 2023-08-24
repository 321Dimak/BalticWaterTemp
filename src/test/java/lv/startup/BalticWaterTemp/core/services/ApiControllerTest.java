package lv.startup.BalticWaterTemp.web_ui.controllers;

import lv.startup.BalticWaterTemp.core.services.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ApiControllerTest {

    @InjectMocks
    ApiController apiController;

    @Mock
    ApiService apiService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchApiData() {
        String fromDate = "2023-08-01";
        String toDate = "2023-08-24";
        String station = "station1";
        ResponseEntity<String> mockResponse = new ResponseEntity<>("Sample Response", HttpStatus.OK);
        when(apiService.fetchDataFromApi(fromDate, toDate, station)).thenReturn(mockResponse);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<String> result = apiController.fetchApiData(fromDate, toDate, station);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Sample Response", result.getBody());
    }
}
