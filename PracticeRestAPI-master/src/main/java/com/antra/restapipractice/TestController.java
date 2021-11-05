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
@RequestMapping("/api/test")
public class TestController {
    @GetMapping
    public ResponseEntity<String> test() throws IOException {

        String str = new URLRead().urlRead("https://api.nationalize.io?name=nathaniel");

        JSONObject obj = new JSONObject(str);
        JSONArray jArray = obj.getJSONArray("country");

        return ResponseEntity.ok(jArray.getJSONObject(0).getString("country_id"));
    }
}
