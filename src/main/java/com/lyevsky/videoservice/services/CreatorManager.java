package com.lyevsky.videoservice.services;

import com.lyevsky.videoservice.dtos.CreatorRequest;
import com.lyevsky.videoservice.entities.Creator;

import java.util.List;

public interface CreatorManager {

    Creator saveCreator(CreatorRequest creatorRequest);

    Creator findById(Long id);

    List<Creator> findAll();
}
