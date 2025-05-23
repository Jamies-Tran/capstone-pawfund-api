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

    static String SHELTER_ASSIGNMENTS_DESTINATION;

    @Value("${app.version}")
    public static void setAppVersion(String appVersion) {
        ShelterAssignmentModuleConstant.APP_VERSION = appVersion;
    }

    public static String getAppVersion() {
        return ShelterAssignmentModuleConstant.APP_VERSION;
    }

    @Value("${app.websocket.shelter-assignment.topic}")
    public static void setShelterAssignmentsDestination(String shelterAssignmentsDestination) {
        ShelterAssignmentModuleConstant.SHELTER_ASSIGNMENTS_DESTINATION = shelterAssignmentsDestination;
    }

    public static String getShelterAssignmentsDestination() {
        return ShelterAssignmentModuleConstant.SHELTER_ASSIGNMENTS_DESTINATION;
    }

    @PostConstruct
    public void postConstruct() {
        log.info("MODULE: [{} {}]", MODULE_NAME, APP_VERSION);
    }
}
