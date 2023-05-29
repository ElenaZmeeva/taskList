package com.example.tasklist.controller;

import com.example.tasklist.dto.LabelDto;
import com.example.tasklist.service.LabelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/label")
public class LabelController {

    private final LabelService labelService;


    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @PostMapping
    public ResponseEntity<LabelDto> addLabel(@RequestBody LabelDto labelDto){
        LabelDto labelDto1= labelService.addLabel(labelDto);
        return ResponseEntity.ok(labelDto1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabelDto> getLabel(@PathVariable("id") Long id){
        LabelDto labelDto=labelService.getLabelDto(id);
        if (labelDto==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(labelDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LabelDto> updateLabel(@RequestBody LabelDto label, @PathVariable("id") Long id){
        LabelDto labelDto=labelService.updateLabel(label, id);
        if (labelDto==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(labelDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabel(@PathVariable("id") Long id){
        labelService.deleteLabel(id);
        return ResponseEntity.noContent().build();
    }
}
