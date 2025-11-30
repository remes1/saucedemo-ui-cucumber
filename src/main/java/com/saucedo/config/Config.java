package com.saucedo.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private final Dotenv dotenv;

    public Config(String envName) {
        dotenv = Dotenv.configure()
                .filename(envName + ".env")
                .load();
    }

    public String getBaseUrl() {
        return dotenv.get("BASE_URL");
    }

    public String getUsername() {
        return dotenv.get("USERNAME");
    }

    public String getBlockedUsername() {
        return dotenv.get("BLOCKED_USERNAME");
    }

    public String getPassword() {
        return dotenv.get("PASSWORD");
    }
}
