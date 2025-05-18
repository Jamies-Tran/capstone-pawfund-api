package com.paw.fund.app.modules.pet_intake_registration_management.repository.database.dao;

public interface PetIntakeRegistrationActionDAO {
    Long petIntakeRegistrationId();

    Boolean getAllowUpdate();

    Boolean getAllowDelete();

    Boolean getAllowProcess();

    Boolean getAllowCancel();

    Boolean getAllowFinished();
}
