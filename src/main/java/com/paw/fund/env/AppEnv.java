package com.paw.fund.env;

import org.springframework.beans.factory.annotation.Value;

public class AppEnv {
    public static String API_VERSION;

    @Value("${app.version}")
    public void setApiVersion(String apiVersion) {
        AppEnv.API_VERSION = apiVersion;
    }
}
