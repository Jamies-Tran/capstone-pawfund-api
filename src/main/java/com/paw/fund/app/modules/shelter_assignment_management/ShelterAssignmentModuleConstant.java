package com.paw.fund.app.modules.shelter_assignment_management;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAssignmentModuleConstant {
    static String MODULE_NAME = "SHELTER_ASSIGNMENT";


    static String APP_VERSION;

    @PostConstruct
    public void postConstruct() {
        log.info("MODULE: [{} {}]", MODULE_NAME);
    }
}
