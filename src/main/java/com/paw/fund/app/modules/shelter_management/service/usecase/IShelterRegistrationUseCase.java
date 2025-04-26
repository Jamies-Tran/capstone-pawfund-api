package com.paw.fund.app.modules.shelter_management.service.usecase;

import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationEmail;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationId;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationNotification;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationReject;
import org.springframework.data.domain.Page;

public interface IShelterRegistrationUseCase {
    ShelterRegistrationNotification getRegistrationNotification(ShelterRegistrationFilter filter);

    ShelterRegistration getShelterRegistrationDetail(ShelterRegistrationEmail email);

    ShelterRegistration receiveShelterRegistration(ShelterRegistrationId shelterRegistrationId);

    ShelterRegistration approveShelterRegistration(ShelterRegistrationId shelterRegistrationId);

    ShelterRegistration rejectedShelterRegistration(ShelterRegistrationReject shelterRegistrationReject);

    Page<ShelterRegistration> getShelterRegistrationListProcessingByAccount(ShelterRegistrationFilter filter);

    ShelterRegistration getShelterRegistrationDetail(ShelterRegistrationId shelterRegistrationId);
}
