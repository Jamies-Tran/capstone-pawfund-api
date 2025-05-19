package com.paw.fund.app.modules.pet_management.repository.database.pet.dao;

public interface PetSummarizeInfoDAO {
    Long getShelterId();

    Integer getTotal();

    String getPetTypeCode();

    String getPetTypeName();
}
