package com.paw.fund.app.modules.shelter_management.service;

import com.paw.fund.app.modules.shelter_management.domain.IShelterMapper;
import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.repository.database.IShelterRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterQueryService {
    @NonNull
    IShelterRepository repository;

    @NonNull
    IShelterMapper mapper;

    public Shelter findById(Long shelterId) {
        return repository.findById(shelterId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Optional<Shelter> findByAccountIdNullable(Long accountId) {
        return repository.findByAccountId(accountId)
                .map(mapper::toDto);
    }
}
