package com.paw.fund.app.modules.account_management.domain;

import com.paw.fund.app.modules.account_management.repository.database.AccountEntity;
import com.paw.fund.utils.password.encoder.PawFundPasswordEncoder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IAccountMapper {
    Account toDto(AccountEntity entity);

    @Mapping(target = "password", qualifiedByName = "encodeByBCryptPasswordEncoder")
    AccountEntity toEntity(Account dto);

    void update(@MappingTarget AccountEntity foundEntity, Account dto);


    @Named("encodeByBCryptPasswordEncoder")
    default String encodePassword(String password) {
        PawFundPasswordEncoder passwordEncoder = new PawFundPasswordEncoder();
        return passwordEncoder.bCryptpasswordEncoder().encode(password);
    }
}
