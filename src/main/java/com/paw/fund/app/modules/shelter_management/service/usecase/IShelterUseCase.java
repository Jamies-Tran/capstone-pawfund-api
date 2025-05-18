package com.paw.fund.app.modules.shelter_management.service.usecase;

import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterActive;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterId;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationCreate;
import org.springframework.data.domain.Page;

public interface IShelterUseCase {
    Shelter registerShelter(ShelterRegistrationCreate shelterRegistrationCreate);

    Shelter activeShelter(ShelterActive shelterActive);

    Page<Shelter> getShelterList(ShelterFilter shelterFilter);

    Shelter getShelterDetail(ShelterId shelterId);

    Page<Shelter> getShelterDistanceList(ShelterFilter shelterFilter);
}
