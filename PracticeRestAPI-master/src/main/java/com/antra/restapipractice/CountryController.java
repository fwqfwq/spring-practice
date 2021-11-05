package com.antra.restapipractice;

import com.antra.restapipractice.pojo.CountryPredict;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;


@RestController
@RequestMapping("/api/country")
public class CountryController {

    String url = "https://api.nationalize.io?name=";

    @GetMapping
    public ResponseEntity<CountryPredict> getCountryByName(@RequestParam String name) throws IOException {

        CountryPredict cp = new CountryPredict();
        String str = new URLRead().urlRead(url + name);

        JSONObject obj = new JSONObject(str);
        JSONArray jArray = obj.getJSONArray("country");

        cp.setName(name);
        cp.setCountry(jArray.getJSONObject(0).getString("country_id"));

        return ResponseEntity.ok(cp);
    }
}
