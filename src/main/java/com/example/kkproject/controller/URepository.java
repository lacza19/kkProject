package com.example.kkproject.controller;

import com.example.kkproject.model.UDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

@org.springframework.stereotype.Repository
public interface URepository extends JpaRepositoryImplementation<UDomain, Integer> {

    @Query("select a.originalUrl from UDomain a where a.shortedUrl = :shortedUrl")
    String getOriginalLink(
            @Param("shortedUrl") String shortedUrl
    );
}
