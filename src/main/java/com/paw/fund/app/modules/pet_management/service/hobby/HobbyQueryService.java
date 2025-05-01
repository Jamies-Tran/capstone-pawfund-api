package com.paw.fund.app.modules.pet_management.service.hobby;

import com.paw.fund.app.modules.pet_management.domain.hobby.Hobby;
import com.paw.fund.app.modules.pet_management.domain.hobby.IHobbyMapper;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbySearchCriteria;
import com.paw.fund.app.modules.pet_management.repository.database.hobby.IHobbyRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HobbyQueryService {
    @NonNull
    IHobbyRepository repository;

    @NonNull
    IHobbyMapper mapper;

    public Hobby findById(Long hobbyId) {
        return repository.findByStatusCodeNotDeletedAndHobbyId(hobbyId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Hobby> findAll(HobbySearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }

    public List<Hobby> findAllByHobbyIdIn(List<Long> hobbyIds) {
        return repository.findAllByHobbyIdIn(hobbyIds)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
