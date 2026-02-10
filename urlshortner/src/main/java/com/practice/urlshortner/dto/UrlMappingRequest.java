package com.practice.urlshortner.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public record UrlMappingRequest(
        @NotBlank
        @URL
        String originalUrl) {
}
