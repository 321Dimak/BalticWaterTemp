package lv.startup.BalticWaterTemp.core.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final String API_BASE_URL = "https://data.gov.lv/dati/lv/api/3/action";

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> fetchDataFromApi() {
        String resource_id = "a90de53b-e8b6-4cda-97c4-ecb86fbafc2d";
        int limit = 5;

        String url = API_BASE_URL + "/datastore_search?resource_id=" + resource_id + "&limit=" + limit;
        return restTemplate.getForEntity(url, String.class);
    }
}
