package lv.startup.BalticWaterTemp.core.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final String API_BASE_URL = "https://data.gov.lv/dati/api/3/action/datastore_search_sql";
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> fetchDataFromApi(String fromDate, String toDate, String station) {
        String sqlQuery = "SELECT * FROM \"a90de53b-e8b6-4cda-97c4-ecb86fbafc2d\" " +
                "WHERE \"a90de53b-e8b6-4cda-97c4-ecb86fbafc2d\".\"ABBREVIATION\" = 'WTEMD' " +
                "AND \"a90de53b-e8b6-4cda-97c4-ecb86fbafc2d\".\"STATION_ID\" = '"+ station +"' " +
                "AND \"a90de53b-e8b6-4cda-97c4-ecb86fbafc2d\".\"DATETIME\" BETWEEN '" + fromDate + "' AND '" + toDate + "'";

        String url = API_BASE_URL + "?sql=" + sqlQuery;
        return restTemplate.getForEntity(url, String.class);
    }
}
