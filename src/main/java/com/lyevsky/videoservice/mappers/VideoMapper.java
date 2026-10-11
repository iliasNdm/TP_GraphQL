package com.lyevsky.videoservice.mappers;

import com.lyevsky.videoservice.dtos.VideoRequest;
import com.lyevsky.videoservice.entities.Video;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class VideoMapper {

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    private final ModelMapper modelMapper;

    public VideoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.typeMap(VideoRequest.class, Video.class).addMappings(mapping -> {
            mapping.skip(Video::setId);
            mapping.skip(Video::setDatePublication);
            mapping.skip(Video::setCreator);
        });
    }

    public Video fromVideoRequest(VideoRequest videoRequest) {
        Video video = modelMapper.map(videoRequest, Video.class);
        video.setDatePublication(parseDate(videoRequest.getDatePublication()));
        return video;
    }

    private Date parseDate(String date) {
        if (date == null || date.isBlank()) {
            return null;
        }
        try {
            return new SimpleDateFormat(DATE_PATTERN).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(String.format("Invalid date %s, expected format %s", date, DATE_PATTERN));
        }
    }
}
