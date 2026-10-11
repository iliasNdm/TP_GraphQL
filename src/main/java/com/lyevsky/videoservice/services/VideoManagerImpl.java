package com.lyevsky.videoservice.services;

import com.lyevsky.videoservice.dtos.VideoRequest;
import com.lyevsky.videoservice.entities.Creator;
import com.lyevsky.videoservice.entities.Video;
import com.lyevsky.videoservice.mappers.VideoMapper;
import com.lyevsky.videoservice.repositories.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VideoManagerImpl implements VideoManager {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final CreatorManager creatorManager;

    public VideoManagerImpl(VideoRepository videoRepository, VideoMapper videoMapper, CreatorManager creatorManager) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
        this.creatorManager = creatorManager;
    }

    /**
     * Q28 : le creator imbrique est sauvegarde en premier pour que la video
     * reference un Creator deja persistant (les deux dans la meme transaction).
     */
    @Override
    public Video saveVideo(VideoRequest videoRequest) {
        Video video = videoMapper.fromVideoRequest(videoRequest);
        if (videoRequest.getCreator() != null) {
            Creator creator = creatorManager.saveCreator(videoRequest.getCreator());
            video.setCreator(creator);
        }
        return videoRepository.save(video);
    }

    @Override
    @Transactional(readOnly = true)
    public Video findById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Video %s not found", id)));
    }

    @Override
    public Video updateVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Video> findByCreatorId(Long creatorId) {
        return videoRepository.findByCreatorId(creatorId);
    }
}
