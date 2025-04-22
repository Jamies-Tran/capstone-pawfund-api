package com.paw.fund.app.modules.shelter_management.service.usecase;

import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationId;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationNotification;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationReject;

public interface IShelterRegistrationUseCase {
    ShelterRegistrationNotification getRegistrationNotification(ShelterRegistrationFilter filter);

    ShelterRegistration receiveShelterRegistration(ShelterRegistrationId shelterRegistrationId);

    ShelterRegistration approveShelterRegistration(ShelterRegistrationId shelterRegistrationId);

    ShelterRegistration rejectedShelterRegistration(ShelterRegistrationReject shelterRegistrationReject);
}
