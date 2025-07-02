package com.paw.fund.app.modules.adopt_registration_management;

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
public class AdoptModuleConstant {
    static String MODULE_NAME = "ADOPT";

    @NonFinal
    @Value("${app.version}")
    String APP_VERSION;

    static String API_VERSION;

    @Value("${api.version}")
    public static void setApiVersion(String apiVersion) {
        AdoptModuleConstant.API_VERSION = apiVersion;
    }

    public static String getApiVersion() {
        return AdoptModuleConstant.API_VERSION;
    }

    @PostConstruct
    public void postConstruct() {
        log.info("MODULE: [{} {}]", MODULE_NAME);
    }
}
