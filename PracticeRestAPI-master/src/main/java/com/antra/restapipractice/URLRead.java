package com.antra.restapipractice;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class URLRead {

    public String urlRead(String url) throws IOException {

        InputStream is = new URL(url).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));


        StringBuilder sb = new StringBuilder();
        int input;
        while ((input = rd.read()) != -1) {
            sb.append((char) input);
        }

        return sb.toString();
    }
}
