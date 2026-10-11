package com.lyevsky.videoservice;

import com.lyevsky.videoservice.entities.Creator;
import com.lyevsky.videoservice.entities.Video;
import com.lyevsky.videoservice.repositories.CreatorRepository;
import com.lyevsky.videoservice.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootApplication
public class VideoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
        return args -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Creator ilias = creatorRepository.save(Creator.builder()
                    .name("ilias nadmi")
                    .email("ilias@gmail.com")
                    .build());
            Creator badr = creatorRepository.save(Creator.builder()
                    .name("Badr")
                    .email("badr@gmail.com")
                    .build());
            Creator alae = creatorRepository.save(Creator.builder()
                    .name("alae")
                    .email("alae@gmail.com")
                    .build());

            videoRepository.saveAll(List.of(
                    Video.builder()
                            .name("Introduction a GraphQL")
                            .url("https://videos.xproce.ma/graphql-intro")
                            .description("Concepts de base de GraphQL : schema, queries, mutations")
                            .datePublication(sdf.parse("12/01/2023"))
                            .creator(ilias)
                            .build(),
                    Video.builder()
                            .name("Spring for GraphQL")
                            .url("https://videos.xproce.ma/spring-graphql")
                            .description("Mise en place d'un serveur GraphQL avec Spring Boot")
                            .datePublication(sdf.parse("03/02/2023"))
                            .creator(ilias)
                            .build(),
                    Video.builder()
                            .name("Spring Data JPA")
                            .url("https://videos.xproce.ma/spring-data-jpa")
                            .description("Repositories, requetes derivees et JPQL")
                            .datePublication(sdf.parse("18/03/2023"))
                            .creator(badr)
                            .build(),
                    Video.builder()
                            .name("Subscriptions GraphQL")
                            .url("https://videos.xproce.ma/graphql-subscriptions")
                            .description("Flux reactifs et WebSocket avec Spring for GraphQL")
                            .datePublication(sdf.parse("07/06/2023"))
                            .creator(alae)
                            .build(),
                    Video.builder()
                            .name("Angular et Apollo Client")
                            .url("https://videos.xproce.ma/angular-apollo")
                            .description("Consommer une API GraphQL depuis une application Angular")
                            .datePublication(sdf.parse("28/11/2023"))
                            .creator(alae)
                            .build()
            ));

            System.out.println(">>> Q8 : " + creatorRepository.count() + " creators et "
                    + videoRepository.count() + " videos inseres.");
            videoRepository.findAll()
                    .forEach(v -> System.out.println("    - " + v.getName() + " / " + v.getCreator().getName()));
        };
    }
}
