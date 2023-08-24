package lv.startup.BalticWaterTemp.web_ui.controllers;

import lv.startup.BalticWaterTemp.core.entity.FavoriteLocation;
import lv.startup.BalticWaterTemp.core.entity.Location;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.services.FavoriteLocationService;
import lv.startup.BalticWaterTemp.core.services.LocationService;
import lv.startup.BalticWaterTemp.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteLocationService favoriteLocationService;
    @Autowired
    private LocationService locationService;

    @GetMapping(value = "/")
    public String index(Model model, Principal principal) {
        if (principal != null) {
            String loggedInUserEmail = principal.getName();
            User loggedInUser = userService.findByEmail(loggedInUserEmail);
            List<FavoriteLocation> favoriteLocations = favoriteLocationService.findByUserEmail(loggedInUserEmail);
            List<Location> locations = favoriteLocations.stream()
                    .map(location -> locationService.findLocationById(location.getLocationId()))
                    .collect(Collectors.toList());
            model.addAttribute("loggedInUser", loggedInUser.getUsername());
            model.addAttribute("favoriteLocations", locations);
        }
        return "index";
    }

}
