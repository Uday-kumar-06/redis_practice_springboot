package com.practice.urlshortner.repository;

import com.practice.urlshortner.model.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity,Long> {

    boolean existsOriginalUrl(String originalUrl);
}
