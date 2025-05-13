package com.paw.fund.app.modules.pet_intake_registration_management.service.usecase;


import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationDelete;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationFilter;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationId;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationInformerPhone;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationUpdate;
import org.springframework.data.domain.Page;

public interface IPetIntakeRegistrationUseCase {
    PetIntakeRegistration createPetIntakeRegistration(PetIntakeRegistration petIntakeRegistration);

    PetIntakeRegistration getPetIntakeRegistrationDetail(PetIntakeRegistrationId petIntakeRegistrationId);

    PetIntakeRegistration getPetIntakeRegistrationDetail(PetIntakeRegistrationInformerPhone petIntakeRegistrationInformerPhone);

    Page<PetIntakeRegistration> getPetIntakeRegistrationList(PetIntakeRegistrationFilter filter);

    PetIntakeRegistration updatePetIntakeRegistration(PetIntakeRegistrationUpdate petIntakeRegistrationUpdate);

    void delete(PetIntakeRegistrationDelete petIntakeRegistrationDelete);
}
