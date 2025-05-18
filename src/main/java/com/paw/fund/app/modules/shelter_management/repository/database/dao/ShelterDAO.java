package com.paw.fund.app.modules.shelter_management.repository.database.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ShelterDAO {
    Long getShelterId();

    BigDecimal getDistance();
}
