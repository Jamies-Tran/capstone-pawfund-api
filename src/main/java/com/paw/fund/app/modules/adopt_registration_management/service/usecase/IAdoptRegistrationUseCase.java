package com.paw.fund.app.modules.adopt_registration_management.service.usecase;

import com.paw.fund.app.modules.adopt_registration_management.domain.AdoptRegistration;

public interface IAdoptRegistrationUseCase {
    AdoptRegistration createAdoptRegistration(AdoptRegistration adoptRegistration);
}
