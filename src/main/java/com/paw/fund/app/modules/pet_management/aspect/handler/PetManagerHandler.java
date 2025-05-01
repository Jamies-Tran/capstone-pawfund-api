package com.paw.fund.app.modules.pet_management.aspect.handler;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaCommandService;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaQueryService;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.domain.pet.hobby.PetHobby;
import com.paw.fund.app.modules.pet_management.service.pet.hobby.PetHobbyCommandService;
import com.paw.fund.app.modules.pet_management.service.pet.hobby.PetHobbyQueryService;
import com.paw.fund.configuration.handler.exceptions.ServiceException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetManagerHandler {
    @NonNull
    PetHobbyCommandService petHobbyCommandService;

    @NonNull
    CommonMediaCommandService mediaCommandService;

    @NonNull
    CommonMediaQueryService mediaQueryService;

    @NonNull
    PetHobbyQueryService petHobbyQueryService;

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
}
