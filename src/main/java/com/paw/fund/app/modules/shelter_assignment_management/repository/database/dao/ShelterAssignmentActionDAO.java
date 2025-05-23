package com.paw.fund.app.modules.shelter_assignment_management.repository.database.dao;

public interface ShelterAssignmentActionDAO {
    Long getShelterAssignmentId();
    Boolean getAllowUpdate();
    Boolean getAllowReceive();
    Boolean getAllowCancel();
    Boolean getAllowComplete();
}
