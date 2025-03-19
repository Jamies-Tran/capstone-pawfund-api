package com.paw.fund.app.modules.account_role_management.repository.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountRoleId;

    @Column
    Long accountId;

    @Column
    Long roleId;
}
