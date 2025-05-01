package com.paw.fund.app.modules.pet_management.service.hobby.usecase;

import com.paw.fund.app.modules.pet_management.domain.hobby.Hobby;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbyFilter;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbyId;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbyList;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IHobbyUseCase {
    Hobby createHobby(Hobby hobby);

    List<Hobby> createHobbyList(HobbyList hobbyList);

    Hobby getHobbyDetail(HobbyId hobbyId);

    Page<Hobby> getHobbyList(HobbyFilter filter);

    Hobby activeHobby(HobbyId hobbyId);

    Hobby blockHobby(HobbyId hobbyId);

    void deleteHobby(HobbyId hobbyId);
}
