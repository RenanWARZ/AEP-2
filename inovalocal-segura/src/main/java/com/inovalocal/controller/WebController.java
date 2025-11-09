package com.inovalocal.controller;


import com.inovalocal.model.Occurrence;
import com.inovalocal.model.User;
import com.inovalocal.service.OccurrenceService;
import com.inovalocal.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    private final UserService userService;
    private final OccurrenceService occurrenceService;

    public WebController(UserService userService, OccurrenceService occurrenceService) {
        this.userService = userService;
        this.occurrenceService = occurrenceService;
    }

    @GetMapping({"/index"})
    public String index(Model model) {
        try {
            List<User> users = userService.findAll();
            List<Occurrence> occurrences = occurrenceService.findAll();
            model.addAttribute("users", users);
            model.addAttribute("occurrences", occurrences);
        } catch (Exception e) {
            e.printStackTrace(); // Exibe o erro no console
            model.addAttribute("errorMessage", "Erro ao carregar dados!");
        }
        return "index";
    }
}
