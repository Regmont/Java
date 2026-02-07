package com.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fractions")
public class FractionController {

    @GetMapping("/is-proper")
    public String checkFraction(
            @RequestParam int numerator,
            @RequestParam int denominator) {

        if (denominator == 0) {
            return "ERROR: Знаменатель не может быть нулём";
        }

        boolean isProper = Math.abs(numerator) < Math.abs(denominator);
        String fraction = numerator + "/" + denominator;

        if (isProper) {
            return "Дробь " + fraction + " является ПРАВИЛЬНОЙ";
        } else {
            return "Дробь " + fraction + " является НЕПРАВИЛЬНОЙ";
        }
    }
}
