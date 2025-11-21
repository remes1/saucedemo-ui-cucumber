package com.saucedo.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    public enum EnvType {LOCAL, DOCKER}

    public static WebDriver createDriver(EnvType envType) {
        return envType == EnvType.LOCAL ? createLocal() : createDocker();
    }

    private static WebDriver createLocal() {
        WebDriverManager.chromedriver().setup();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false); // Disable compromised password alerts

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }

    private static WebDriver createDocker() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            URI remoteUri = URI.create("http://localhost:4444/wd/hub");
            return new RemoteWebDriver(remoteUri.toURL(), options);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Docker WebDriver", e);
        }
    }
}