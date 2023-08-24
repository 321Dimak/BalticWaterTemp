package lv.startup.BalticWaterTemp.web_ui.controllers;

import lv.startup.BalticWaterTemp.core.dto.FavoriteLocationDTO;
import lv.startup.BalticWaterTemp.core.services.FavoriteLocationService;
import lv.startup.BalticWaterTemp.core.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private FavoriteLocationService favoriteLocationService;
    @Autowired
    UserAuthenticationService userAuthenticationService;

    @GetMapping(value = "/")
    public String index() { return "index"; }

    @PostMapping("/save-favorite-location")
    public ResponseEntity<String> saveFavoriteLocation(@RequestParam("locationId") String locationId) {
        String userEmail = userAuthenticationService.getLoggedInUserEmail();
        FavoriteLocationDTO dto = new FavoriteLocationDTO();
        dto.setUserEmail(userEmail);
        dto.setLocationId(locationId);

        favoriteLocationService.saveFavoriteLocation(dto);

        return ResponseEntity.ok("Favorite location saved.");
    }
}
