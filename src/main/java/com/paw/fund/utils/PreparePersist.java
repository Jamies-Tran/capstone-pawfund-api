//package com.paw.fund.utils;
//
//import core.service.paw.fund.common.account.CurrentAccountLogin;
//import core.service.paw.fund.common.audit.AuditableEntity;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//public class PreparePersist<T extends AuditableEntity> {
//    public static <T extends AuditableEntity> void prepareSave(T data, CurrentAccountLogin currentAccountLogin) {
//        Optional.ofNullable(currentAccountLogin).ifPresentOrElse(
//                x -> {
//                    data.setCreatedAt(LocalDateTime.now());
//                    data.setUpdatedAt(LocalDateTime.now());
//                    data.setCreatedById(x.accountId());
//                    data.setUpdatedById(x.accountId());
//                    data.setCreatedByName(x.fullName());
//                    data.setUpdatedByName(x.fullName());
//                },
//                () -> {
//                    data.setCreatedAt(LocalDateTime.now());
//                    data.setUpdatedAt(LocalDateTime.now());
//                    data.setCreatedById(-1L);
//                    data.setUpdatedById(-1L);
//                    data.setCreatedByName("-");
//                    data.setUpdatedByName("-");
//                }
//        );
//    }
//
//    public static <T extends AuditableEntity> void prepareUpdate(T data, CurrentAccountLogin currentAccountLogin) {
//        Optional.ofNullable(currentAccountLogin).ifPresentOrElse(
//                x -> {
//                    data.setUpdatedAt(LocalDateTime.now());
//                    data.setUpdatedById(x.accountId());
//                    data.setUpdatedByName(x.fullName());
//                },
//                () -> {
//                    data.setUpdatedAt(LocalDateTime.now());
//                    data.setUpdatedById(-1L);
//                    data.setUpdatedByName("-");
//                }
//        );
//    }
//}
