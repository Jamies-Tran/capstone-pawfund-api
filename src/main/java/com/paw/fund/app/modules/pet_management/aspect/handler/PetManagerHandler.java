package com.paw.fund.app.modules.pet_management.aspect.handler;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.service.account.AccountQueryService;
import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaCommandService;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaQueryService;
import com.paw.fund.app.modules.log_management.annotation.CreatePetActivityLogHelper;
import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import com.paw.fund.app.modules.log_management.domain.pet.PetActivityLog;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.domain.pet.hobby.PetHobby;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetUpdate;
import com.paw.fund.app.modules.log_management.service.pet.PetActivityLogCommandService;
import com.paw.fund.app.modules.pet_management.service.pet.PetQueryService;
import com.paw.fund.app.modules.pet_management.service.pet.hobby.PetHobbyCommandService;
import com.paw.fund.app.modules.pet_management.service.pet.hobby.PetHobbyQueryService;
import com.paw.fund.configuration.handler.exceptions.ServiceException;
import com.paw.fund.enums.EHealthStatus;
import com.paw.fund.enums.EPetAction;
import com.paw.fund.enums.EPetStatus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetManagerHandler {
    @NonNull
    PetQueryService petQueryService;

    @NonNull
    PetHobbyCommandService petHobbyCommandService;

    @NonNull
    PetHobbyQueryService petHobbyQueryService;

    @NonNull
    CommonMediaCommandService mediaCommandService;

    @NonNull
    CommonMediaQueryService mediaQueryService;

    @NonNull
    AccountQueryService accountQueryService;

    @Around("@annotation(com.paw.fund.app.modules.pet_management.aspect.CreatePetHelper)")
    public Object createPetHelper(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            Object arg = joinPoint.getArgs()[0];
            if(result instanceof Pet savedPet && arg instanceof Pet requestPet) {
                List<PetHobby> petHobbies = petHobbyCommandService
                        .saveAllWithPetId(savedPet.petId(), requestPet.hobbies());
                List<CommonMedia> medias = mediaCommandService
                        .saveAllWithPetId(savedPet.petId(), requestPet.medias());

                return savedPet
                        .withHobbies(petHobbies)
                        .withMedias(medias);
            }

            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }

    @Around("@annotation(com.paw.fund.app.modules.pet_management.aspect.GetPetDetailHelper)")
    public Object getPetDetailHelper(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            Object result = joinPoint.proceed();
            if(result instanceof Pet pet) {
                List<CommonMedia> medias = mediaQueryService.findAllByPetId(pet.petId());
                List<PetHobby> petHobbies = petHobbyQueryService.findAllByPetId(pet.petId());

                return pet
                        .withMedias(medias)
                        .withHobbies(petHobbies);
            }

            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }

    @Around("@annotation(com.paw.fund.app.modules.pet_management.aspect.UpdatePetHelper)")
    public Object updatePetHelper(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            Object arg = joinPoint.getArgs()[0];
            if(result instanceof Pet updatedPet && arg instanceof PetUpdate petUpdate) {
                Pet newPet = petUpdate.pet();
                List<CommonMedia> medias = mediaCommandService
                        .updateAllByPetId(updatedPet.petId(), newPet.medias());
                List<PetHobby> petHobbies = petHobbyCommandService
                        .updateAllByPetId(updatedPet.petId(), newPet.hobbies());

                return updatedPet
                        .withMedias(medias)
                        .withHobbies(petHobbies);
            }

            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }

    @Around("@annotation(com.paw.fund.app.modules.pet_management.aspect.GetPetHealthRecordDetailHelper)")
    public Object getPetHealthRecordDetailHelper(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            Object result = joinPoint.proceed();
            if(result instanceof PetHealthRecord petHealthRecord) {
                Pet pet = petQueryService.findById(petHealthRecord.petId());
                Account staff = accountQueryService.findById(petHealthRecord.createdById());

                return petHealthRecord.withPet(pet).withStaffLoggedRecord(staff);
            }

            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }
}
