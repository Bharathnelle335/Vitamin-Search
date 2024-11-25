package com.Vitamins.Vitamins;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VitaminController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("welcomeMessage", "Welcome to Vitamin Search");
        return "vitamins"; // This should map to vitamins.html in templates folder
    }

    @GetMapping("/vitamins")
    public String getVegetables(@RequestParam(required = false) String vitamin, Model model) {
        if (vitamin == null || vitamin.isEmpty()) {
            model.addAttribute("result", "Welcome to Vitamin Search");
            return "vitamins";
        }

        String[] vegetables = {
                "Vitamin_A_Carrots_Sweet_Potatoes_Pumpkin_Spinach_Kale",
                "Vitamin_B6_Potatoes_Chickpeas_Bananas_Spinach_Bell_Peppers",
                "Vitamin_C_Bell_Peppers_Broccoli_Brussels_Sprouts_Strawberries",
                "Vitamin_K_Kale_Spinach_Broccoli_Brussels_Sprouts_Green_Beans",
                "Vitamin_E_Asparagus_Swiss_Chard_Spinach_Broccoli_Turnip_Greens_Folate",
                "Vitamin_B9_Spinach_Asparagus_Brussels_Sprouts_Broccoli_Lentils"
        };

        StringBuilder result = new StringBuilder();
        boolean found = false;

        for (String veggeis : vegetables) {
            String[] words = veggeis.split("_");
            if (words.length > 1 && words[0].equalsIgnoreCase("Vitamin") && words[1].startsWith(vitamin.toUpperCase())) {
                result.append(veggeis).append("<br>");
                found = true;
            }
        }

        if (found) {
            model.addAttribute("result", "Vitamin foods found for this vitamin: <br>" + result.toString());
        } else {
            model.addAttribute("result", "No vitamin foods found for the asked vitamin: " + vitamin);
        }

        return "vitamins";
    }
}
