package com.antra.restapipractice;

import com.antra.restapipractice.pojo.CatFact;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CatController {

    String url = "https://catfact.ninja/fact";

    @GetMapping("/catFact")
    public ResponseEntity<?> getRandomFact() throws IOException {

        CatFact ret = new CatFact();
        String str = new URLRead().urlRead(url);

        JSONObject obj = new JSONObject(str);
        ret.setCatFact(obj.getString("fact"));

        return ResponseEntity.ok(ret);
    }
}
