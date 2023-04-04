package com.alura.controller;

import com.alura.model.TopLanguages;
import com.alura.service.TopLanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/Languages")
public class TopLanguagesController {

    @Autowired
    TopLanguagesService topLanguagesService;

    @PostMapping
    public TopLanguages CreateTopLanguages(@RequestBody TopLanguages topLanguages){
        return topLanguagesService.saveTopLanguages(topLanguages);
    }

    @GetMapping
    public List<TopLanguages> GetTopLanguages(){
        return topLanguagesService.findAll();
    }
    @GetMapping("/{idTopLanguages}")
    public ResponseEntity<TopLanguages> getTopLanguagesById(@PathVariable("idTopLanguages") Long idTopLanguages) throws Exception{
        return ResponseEntity.ok(topLanguagesService.getById(idTopLanguages).orElseThrow(() ->
                new NoSuchElementException("TopLanguage not found")));
    }
    @PutMapping
    public TopLanguages updateTopLanguages(@RequestBody TopLanguages topLanguages){
        return topLanguagesService.updateTopLanguages(topLanguages);
    }
    @DeleteMapping("/{idTopLanguages}")
    public ResponseEntity<TopLanguages> deleteTopLanguagesById(@PathVariable("idTopLanguages") Long idTopLanguages) throws Exception{
        topLanguagesService.deleteTopLanguagesById(idTopLanguages);
        return (ResponseEntity<TopLanguages>) ResponseEntity.ok();
    }

}
