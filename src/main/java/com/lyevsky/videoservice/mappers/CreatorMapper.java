package com.lyevsky.videoservice.mappers;

import com.lyevsky.videoservice.dtos.CreatorRequest;
import com.lyevsky.videoservice.entities.Creator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreatorMapper {

    private final ModelMapper modelMapper;

    public CreatorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.typeMap(CreatorRequest.class, Creator.class).addMappings(mapping -> {
            mapping.skip(Creator::setId);
            mapping.skip(Creator::setVideos);
        });
    }

    public Creator fromCreatorRequest(CreatorRequest creatorRequest) {
        return modelMapper.map(creatorRequest, Creator.class);
    }
}
