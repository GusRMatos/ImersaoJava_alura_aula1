package com.alura.repository;

import com.alura.model.TopLanguages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopLanguagesRepository extends JpaRepository<TopLanguages, Long> {
    List<TopLanguages> findAll();
}
