package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UrlChechkController {

    private static final String SITE_IS_UP = "Site is up!";
    private static final String SITE_IS_DOWN = "Site is down!";
    private static final String SITE_IS_UNREACHABLE = "Site is unreachable!";

    @GetMapping("/check")
    public String getUrlStatus(@RequestParam String url) {
        String returnMessage = "";
        URL urlObject;
        try {
            urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == 200) {
                returnMessage = SITE_IS_UP;
            } else {
                returnMessage = SITE_IS_DOWN;
            }
        } catch (MalformedURLException e1) {
            returnMessage = SITE_IS_UNREACHABLE;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }
        return returnMessage;
    }
}
