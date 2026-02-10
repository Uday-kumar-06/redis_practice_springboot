package com.practice.urlshortner.controller;


import com.practice.urlshortner.dto.UrlMappingRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlController {

    @GetMapping("/urlShroten")
    public ResponseEntity<?> urlShorten(@Valid  @RequestBody UrlMappingRequest urlMappingRequest){
        return ResponseEntity.status(HttpStatus.OK).body();
    }

}
