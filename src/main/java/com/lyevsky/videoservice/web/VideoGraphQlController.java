package com.lyevsky.videoservice.web;

import com.lyevsky.videoservice.dtos.CreatorRequest;
import com.lyevsky.videoservice.dtos.VideoRequest;
import com.lyevsky.videoservice.entities.Creator;
import com.lyevsky.videoservice.entities.Video;
import com.lyevsky.videoservice.services.CreatorManager;
import com.lyevsky.videoservice.services.VideoManager;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
public class VideoGraphQlController {

    private final VideoManager videoManager;
    private final CreatorManager creatorManager;

    public VideoGraphQlController(VideoManager videoManager, CreatorManager creatorManager) {
        this.videoManager = videoManager;
        this.creatorManager = creatorManager;
    }


    @QueryMapping
    public List<Video> videoList() {
        return videoManager.findAll();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorManager.findById(id);
    }

    @QueryMapping
    public List<Creator> creatorList() {
        return creatorManager.findAll();
    }

    @QueryMapping
    public Video videoById(@Argument Long id) {
        return videoManager.findById(id);
    }

    @QueryMapping
    public List<Video> videosByCreator(@Argument Long creatorId) {
        return videoManager.findByCreatorId(creatorId);
    }

    // --------------------------------------------------------------- Mutations

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest creator) {
        return creatorManager.saveCreator(creator);
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoRequest video) {
        return videoManager.saveVideo(video);
    }


    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(Stream.generate(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            CreatorRequest creatorRequest = CreatorRequest.builder()
                    .name("x" + new Random().nextInt())
                    .email("x@gmail.com")
                    .build();
            Creator creator = creatorManager.saveCreator(creatorRequest);
            Video video = videoManager.findById(1L);
            video.setCreator(creator);
            videoManager.updateVideo(video);
            return video;
        }));
    }
}
