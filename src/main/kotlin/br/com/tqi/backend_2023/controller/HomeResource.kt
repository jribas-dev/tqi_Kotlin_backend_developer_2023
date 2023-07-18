package br.com.tqi.backend_2023.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HomeResource {
    @GetMapping
    fun homeContent(): ResponseEntity<String> {
        val html =  "<!DOCTYPE html>" +
                "<html>"+
                "<head>"+
                "<meta charset=\"UTF-8\">"+
                "<title>API | Kotlin | Spring</title>"+
                "<style>"+
                "body {"+
                "font-family: 'Roboto', sans-serif;"+
                "text-align: center;"+
                "text-color: #0066ff;"+
                "}"+
                "a {text-decoration: none; color: #336600; font-weight: bold; font-size: 14px;}"+
                "</style>"+
                "<link href=\"https://fonts.googleapis.com/css?family=Roboto&display=swap\" rel=\"stylesheet\">"+
                "</head>"+
                "<body>"+
                "<div>"+
                "<p style=\"margin-top:40px;\"><h2>TQI - DESAFIO (Kotlin Backend)</h2></p>" +
                "</div>"+
                "<hr>"+
                "<p><a href=\"/sandbox\">API Sandbox</a> ▶</p>" +
                "</body>"+
                "</html>"
        return ResponseEntity.status(HttpStatus.OK).body(html)
    }
}