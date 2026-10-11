package com.lyevsky.videoservice.services;

import com.lyevsky.videoservice.dtos.CreatorRequest;
import com.lyevsky.videoservice.entities.Creator;
import com.lyevsky.videoservice.mappers.CreatorMapper;
import com.lyevsky.videoservice.repositories.CreatorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CreatorManagerImpl implements CreatorManager {

    private final CreatorRepository creatorRepository;
    private final CreatorMapper creatorMapper;

    public CreatorManagerImpl(CreatorRepository creatorRepository, CreatorMapper creatorMapper) {
        this.creatorRepository = creatorRepository;
        this.creatorMapper = creatorMapper;
    }

    @Override
    public Creator saveCreator(CreatorRequest creatorRequest) {
        return creatorRepository.save(creatorMapper.fromCreatorRequest(creatorRequest));
    }

    @Override
    @Transactional(readOnly = true)
    public Creator findById(Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Creator> findAll() {
        return creatorRepository.findAll();
    }
}
