package lv.startup.BalticWaterTemp.web_ui.controllers;

import lv.startup.BalticWaterTemp.core.dto.TempThresholdDTO;
import lv.startup.BalticWaterTemp.core.responses.SaveTempNotificationResponse;
import lv.startup.BalticWaterTemp.core.services.ThresholdSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ThresholdSetterController {

    @Autowired
    ThresholdSetService thresholdSetService;

    @GetMapping(value = "/thresholdSetter")
    public String showThresholdSetterPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new TempThresholdDTO());
        return "thresholdSetter";
    }

    @PostMapping("/thresholdSetter")
    public String saveTempThreshold(Model model, Principal principal,
            @ModelAttribute(value = "request") TempThresholdDTO request,
                                    ModelMap modelMap) {
        String loggedInUserEmail = principal.getName();
        model.addAttribute("userEmail", loggedInUserEmail);
        request.setUserEmail(loggedInUserEmail);
        SaveTempNotificationResponse response = thresholdSetService.setTempThreshold(request);
        modelMap.addAttribute("message", response.getMessage());

        return "thresholdSetter";
    }
}
