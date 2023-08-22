package lv.startup.BalticWaterTemp.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComingSoonController {

    @GetMapping(value = "/favorites")
    public String showComingSoonPage() { return "favorites"; }
}

