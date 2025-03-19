package com.paw.fund.app.modules.auditable_management.service.usecase;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;

public interface IAuditableUseCase {
    AuditableEntity createAuditableForNew();

    AuditableEntity createAuditableForUpdate();
}
