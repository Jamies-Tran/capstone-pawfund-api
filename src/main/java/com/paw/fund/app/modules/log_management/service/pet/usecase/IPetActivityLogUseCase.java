package com.paw.fund.app.modules.log_management.service.pet.usecase;

import com.paw.fund.app.modules.log_management.domain.pet.PetActivityLog;
import com.paw.fund.app.modules.log_management.domain.pet.usecase.PetActivityLogFilter;
import org.springframework.data.domain.Page;

public interface IPetActivityLogUseCase {
    Page<PetActivityLog> getPetActivityLogList(PetActivityLogFilter filter);
}
