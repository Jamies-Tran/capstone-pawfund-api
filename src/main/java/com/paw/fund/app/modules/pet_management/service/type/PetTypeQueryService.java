package com.paw.fund.app.modules.pet_management.service.type;

import com.paw.fund.app.modules.pet_management.domain.type.IPetTypeMapper;
import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeSearchCriteria;
import com.paw.fund.app.modules.pet_management.repository.database.type.IPetTypeRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetTypeQueryService {
    @NonNull
    IPetTypeRepository repository;

    @NonNull
    IPetTypeMapper mapper;

    public PetType findById(Long petTypeId) {
        return repository.findByStatusCodeNotDeletedAndPetTypeId(petTypeId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Page<PetType> findAll(PetTypeSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }
}
