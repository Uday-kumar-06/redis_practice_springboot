package com.practice.urlshortner.controller;


import com.practice.urlshortner.dto.UrlMappingRequest;
import com.practice.urlshortner.dto.UrlMappingResponse;
import com.practice.urlshortner.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;
    @PostMapping("/urlShroten")
    public ResponseEntity<?> urlShorten(@Valid  @RequestBody UrlMappingRequest urlMappingRequest){
        UrlMappingResponse response = urlService.urlShorten(urlMappingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




}
