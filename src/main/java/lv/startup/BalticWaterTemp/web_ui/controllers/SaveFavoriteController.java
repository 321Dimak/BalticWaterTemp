package lv.startup.BalticWaterTemp.web_ui.controllers;

import lv.startup.BalticWaterTemp.core.dto.FavoriteLocationDTO;
import lv.startup.BalticWaterTemp.core.services.FavoriteLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class SaveFavoriteController {

    @Autowired
    FavoriteLocationService favoriteLocationService;

    @GetMapping(value = "/saveFavorite")
    public String showSaveFavoritePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FavoriteLocationDTO());
        return "saveFavorite";
    }

    @PostMapping("/saveFavorite")
    public String saveFavorite(Model model, Principal principal,
                                    @ModelAttribute(value = "request") FavoriteLocationDTO request,
                                    ModelMap modelMap) {
        String loggedInUserEmail = principal.getName();
        model.addAttribute("userEmail", loggedInUserEmail);
        request.setUserEmail(loggedInUserEmail);
        favoriteLocationService.saveFavoriteLocation(request);
        modelMap.addAttribute("message", "Success");

        return "saveFavorite";
    }
}
