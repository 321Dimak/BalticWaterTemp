package lv.startup.BalticWaterTemp.web_ui.controllers;

// lv.startup.BalticWaterTemp.core.services.ApiService;

import lv.startup.BalticWaterTemp.core.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/fetch-api-data")
    public ResponseEntity<String> fetchApiData(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("station") String station) {
        // Pass fromDate and toDate to the ApiService method
        ResponseEntity<String> apiResponse = apiService.fetchDataFromApi(fromDate, toDate, station);
        return apiResponse;
    }
}