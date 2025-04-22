package com.paw.fund.app.modules.shelter_management.service.usecase;

import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterActive;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterId;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationCreate;

public interface IShelterUseCase {
    Shelter registerShelter(ShelterRegistrationCreate shelterRegistrationCreate);

    Shelter activeShelter(ShelterActive shelterActive);
}
