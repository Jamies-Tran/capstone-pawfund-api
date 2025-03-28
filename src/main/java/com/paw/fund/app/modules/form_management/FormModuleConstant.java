package com.paw.fund.app.modules.form_management;

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
public class FormModuleConstant {
    static String MODULE_NAME = "FORM";

    @NonFinal
    @Value("${app.version}")
    String APP_VERSION;

    @PostConstruct
    public void postConstruct() {
        log.info("MODULE: [{} {}]", MODULE_NAME, APP_VERSION);
    }
}
