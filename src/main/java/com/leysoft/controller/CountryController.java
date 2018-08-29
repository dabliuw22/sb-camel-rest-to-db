
package com.leysoft.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = {
            "/country"
        })
public class CountryController {

    @PostMapping(
            value = {
                ""
            })
    public String get(@RequestBody String body) {
        return body;
    }
}
