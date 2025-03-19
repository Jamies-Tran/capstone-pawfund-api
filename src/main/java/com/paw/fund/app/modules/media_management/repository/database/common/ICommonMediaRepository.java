package com.paw.fund.app.modules.media_management.repository.database.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommonMediaRepository extends JpaRepository<CommonMediaEntity, Long> {
}
