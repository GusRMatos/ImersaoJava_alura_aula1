package com.alura.service;

import com.alura.model.TopLanguages;
import com.alura.repository.TopLanguagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TopLanguagesService {

    @Autowired
    TopLanguagesRepository topLanguagesRepository;

    public TopLanguagesService(TopLanguagesRepository topLanguagesRepository){
        this.topLanguagesRepository = topLanguagesRepository;
    }

    public TopLanguages saveTopLanguages(TopLanguages topLanguages){
        return topLanguagesRepository.save(topLanguages);
    }

    public List<TopLanguages> findAll() {
        return topLanguagesRepository.findAll();
    }
    public Optional<TopLanguages> getById(Long idTopLanguages) {
        return topLanguagesRepository.findById(idTopLanguages);
    }
    public TopLanguages updateTopLanguages(TopLanguages topLanguages){
        return topLanguagesRepository.save(topLanguages);
    }
    public void deleteTopLanguagesById(Long idTopLanguages) {
       topLanguagesRepository.deleteById(idTopLanguages);
    }

}
