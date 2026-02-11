package com.practice.urlshortner.repository;

import com.practice.urlshortner.model.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity,Long> {

    boolean existsByOriginalUrl(String originalUrl);



    Optional<UrlEntity> findByShortCode(String shortCode);
}
