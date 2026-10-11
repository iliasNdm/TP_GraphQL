package com.lyevsky.videoservice.services;

import com.lyevsky.videoservice.dtos.VideoRequest;
import com.lyevsky.videoservice.entities.Video;

import java.util.List;

public interface VideoManager {

    Video saveVideo(VideoRequest videoRequest);

    Video findById(Long id);

    Video updateVideo(Video video);

    List<Video> findAll();

    List<Video> findByCreatorId(Long creatorId);
}
