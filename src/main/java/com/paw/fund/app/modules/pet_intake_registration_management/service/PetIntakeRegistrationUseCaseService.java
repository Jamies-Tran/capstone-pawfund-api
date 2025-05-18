package com.paw.fund.app.modules.pet_intake_registration_management.service;

import com.paw.fund.app.modules.pet_intake_registration_management.PetIntakeRegistrationModuleConstant;
import com.paw.fund.app.modules.pet_intake_registration_management.aspect.CreatePetIntakeRegistrationParamHelper;
import com.paw.fund.app.modules.pet_intake_registration_management.aspect.CreatePetIntakeRegistrationHelper;
import com.paw.fund.app.modules.pet_intake_registration_management.aspect.GetPetIntakeRegistrationHelper;
import com.paw.fund.app.modules.pet_intake_registration_management.aspect.NotifyHelper;
import com.paw.fund.app.modules.pet_intake_registration_management.aspect.UpdatePetIntakeRegistrationHelper;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationCancel;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationDelete;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationFilter;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationId;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationInformerPhone;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationUpdate;
import com.paw.fund.app.modules.pet_intake_registration_management.service.usecase.IPetIntakeRegistrationUseCase;
import com.paw.fund.enums.EPetIntakeRegistrationStatus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetIntakeRegistrationUseCaseService implements IPetIntakeRegistrationUseCase {
    @NonNull
    PetIntakeRegistrationCommandService commandService;

    @NonNull
    PetIntakeRegistrationQueryService queryService;

    @Override
    @Transactional
    @CreatePetIntakeRegistrationParamHelper
    @CreatePetIntakeRegistrationHelper
    @NotifyHelper(appDestination = "/topic/pet-intake-registration/new")
    public PetIntakeRegistration createPetIntakeRegistration(PetIntakeRegistration petIntakeRegistration) {
        return commandService.save(petIntakeRegistration);
    }

    @Override
    @GetPetIntakeRegistrationHelper
    public PetIntakeRegistration getPetIntakeRegistrationDetail(PetIntakeRegistrationId petIntakeRegistrationId) {
        return queryService.findByPetIntakeRegistrationId(petIntakeRegistrationId.value());
    }

    @Override
    public List<PetIntakeRegistration> getPetIntakeRegistrationListByInformerPhone(PetIntakeRegistrationInformerPhone petIntakeRegistrationInformerPhone) {
        return queryService.findByPetIntakeRegistrationInformerPhone(petIntakeRegistrationInformerPhone.value());
    }

    @Override
    public Page<PetIntakeRegistration> getPetIntakeRegistrationList(PetIntakeRegistrationFilter filter) {
        return queryService.findAll(filter.searchCriteria(), filter.pageRequestCustom());
    }

    @Override
    @Transactional
    @UpdatePetIntakeRegistrationHelper
    @NotifyHelper(appDestination = "/topic/pet-intake-registration/new")
    public PetIntakeRegistration updatePetIntakeRegistration(PetIntakeRegistrationUpdate petIntakeRegistrationUpdate) {
        return commandService.update(
                petIntakeRegistrationUpdate.petIntakeRegistrationId(),
                petIntakeRegistrationUpdate.petIntakeRegistration());
    }

    @Override
    @Transactional
    @NotifyHelper(appDestination = "/topic/pet-intake-registration/new")
    public void delete(PetIntakeRegistrationDelete petIntakeRegistrationDelete) {
        commandService.deleteWithVerificationByPhone(
                petIntakeRegistrationDelete.petIntakeRegistrationId(),
                petIntakeRegistrationDelete.phone());
    }

    @Override
    @Transactional
    @NotifyHelper(appDestination = "/topic/pet-intake-registration/new", variableDestination = "/topic/pet-intake-registration")
    public PetIntakeRegistration processPetIntakeRegistration(PetIntakeRegistrationId petIntakeRegistrationId) {
        return commandService.updateStatus(petIntakeRegistrationId.value(), EPetIntakeRegistrationStatus.PROCESSING, null);
    }

    @Override
    @Transactional
    @NotifyHelper(appDestination = "/topic/pet-intake-registration/new", variableDestination = "/topic/pet-intake-registration")
    public PetIntakeRegistration cancelPetIntakeRegistration(PetIntakeRegistrationCancel petIntakeRegistrationCancel) {
        return commandService.updateStatus(
                petIntakeRegistrationCancel.petIntakeRegistrationId(),
                EPetIntakeRegistrationStatus.CANCEL,
                petIntakeRegistrationCancel.canceledReason());
    }

    @Override
    @Transactional
    @NotifyHelper(appDestination = "/topic/pet-intake-registration/new", variableDestination = "/topic/pet-intake-registration")
    public PetIntakeRegistration finishPetIntakeRegistration(PetIntakeRegistrationId petIntakeRegistrationId) {
        return commandService.updateStatus(petIntakeRegistrationId.value(), EPetIntakeRegistrationStatus.FINISHED, null);
    }
}
