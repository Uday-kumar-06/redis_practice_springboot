package com.practice.urlshortner.service;
import com.practice.urlshortner.dto.UrlMappingRequest;
import com.practice.urlshortner.dto.UrlMappingResponse;
import com.practice.urlshortner.model.UrlEntity;
import com.practice.urlshortner.repository.UrlRepository;
import com.practice.urlshortner.util.Base62Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private static final String CACHE_PREFIX = "short-url:";

    private final StringRedisTemplate stringRedisTemplate;
    public UrlMappingResponse urlShorten(UrlMappingRequest urlMappingRequest){

        if(urlRepository.existsByOriginalUrl(urlMappingRequest.originalUrl())){
            throw new RuntimeException("Url Already Exist");
        }


        UrlEntity originalUrl = UrlEntity.builder()
                .originalUrl(urlMappingRequest.originalUrl())
                .build();

        urlRepository.save(originalUrl);

        String encoded = Base62Encoder.encode(originalUrl.getId());
        originalUrl.setShortCode(encoded);
        urlRepository.save(originalUrl);


        stringRedisTemplate.opsForValue().set( CACHE_PREFIX+originalUrl.getShortCode(),originalUrl.getOriginalUrl(), Duration.ofMinutes(20));

        return new UrlMappingResponse(
                 buildShortUrl(encoded)
        );
    }

    public String getOriginalUrl(String code){

        String key = CACHE_PREFIX + code;

        String cachedUrl = stringRedisTemplate.opsForValue().get(key);
        if(cachedUrl != null){
            return cachedUrl;
        }

        UrlEntity entity = urlRepository.findByShortCode(code)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));


        stringRedisTemplate.opsForValue()
                .set(key, entity.getOriginalUrl(), Duration.ofMinutes(20));

        return entity.getOriginalUrl();
    }

    private String buildShortUrl(String code){
        return "http://localhost:8080/api"+code;
    }
}
