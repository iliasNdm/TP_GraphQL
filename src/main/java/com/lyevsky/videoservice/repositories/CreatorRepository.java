package com.lyevsky.videoservice.repositories;

import com.lyevsky.videoservice.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreatorRepository extends JpaRepository<Creator, Long> {
}
