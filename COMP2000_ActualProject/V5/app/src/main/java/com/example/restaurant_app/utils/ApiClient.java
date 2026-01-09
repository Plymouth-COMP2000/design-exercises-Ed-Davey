package com.example.restaurant_app.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    private static final String BASE_URL = "http://10.240.72.69/comp2000/coursework/";

    public static JSONObject readUser(String studentId, String username) throws Exception {
        URL url = new URL(BASE_URL + "read_user/" + studentId + "/" + username);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);

        int code = conn.getResponseCode();

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream()
        ));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);

        br.close();
        conn.disconnect();

        if (code != 200) {
            throw new Exception("API error " + code + ": " + sb);
        }

        return new JSONObject(sb.toString());
    }
}