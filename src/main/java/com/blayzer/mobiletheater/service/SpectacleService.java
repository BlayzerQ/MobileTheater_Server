package com.blayzer.mobiletheater.service;

import com.blayzer.mobiletheater.model.entity.EntitySpectacle;
import com.blayzer.mobiletheater.model.repository.SpectacleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpectacleService {

    private final SpectacleRepository spectacleRepository;

    public Optional<EntitySpectacle> getByID(Integer id) {
        return spectacleRepository.findById(id);
    }

    public List<EntitySpectacle> getAll() {
        return spectacleRepository.findAll();
    }

}
