package com.paw.fund.app.modules.pet_management.domain.pet.hobby;

import com.paw.fund.app.modules.pet_management.domain.hobby.Hobby;
import lombok.Builder;
import lombok.With;

@Builder
public record PetHobby(
        Long petHobbyId,
        @With Long petId,
        Long hobbyId,
        @With Hobby hobby
) {
}
