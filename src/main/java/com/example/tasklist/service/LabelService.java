package com.example.tasklist.service;

import com.example.tasklist.dto.LabelDto;
import com.example.tasklist.entity.Label;
import com.example.tasklist.mapper.LabelMapper;
import com.example.tasklist.repository.LabelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    private final static Logger logger= LoggerFactory.getLogger(LabelService.class);

    LabelMapper labelMapper;
    private final LabelRepository labelRepository;



    public LabelService(LabelMapper labelMapper, LabelRepository labelRepository) {
        this.labelMapper = labelMapper;
        this.labelRepository = labelRepository;
    }

    public LabelDto getLabelDto(Long id) {
        Label label1=labelRepository.findById(id).orElse(null);
        return labelMapper.toDto(label1);
    }

    public LabelDto addLabel(LabelDto labelDto){

        Label label1=labelMapper.toEntity(labelDto);
        labelRepository.save(label1);
        logger.info("Label saved");
        return labelMapper.toDto(label1);
    }
    public LabelDto updateLabel(LabelDto labelDto, Long id){
        Label label1=labelRepository.findById(id).orElse(null);
        assert label1 != null;
        label1.setTitle(labelDto.getTitle());
        logger.info("Label edited");
        return labelMapper.toDto(label1);
    }

    public void deleteLabel(Long id){
        labelRepository.deleteById(id);
        logger.info("Label deleted");
    }
}
