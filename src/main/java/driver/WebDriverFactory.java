package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    public enum EnvType {LOCAL, DOCKER, DOCKER_REMOTE}

    public static WebDriver createDriver(EnvType envType) {
        return switch (envType) {
            case LOCAL -> createLocal();
            case DOCKER -> createDocker("http://localhost:4444/wd/hub");
            case DOCKER_REMOTE -> createDocker(System.getProperty("remote.hub.url"));
        };
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

    private static WebDriver createDocker(String hubUrl) {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            URI uri = URI.create(hubUrl);
            return new RemoteWebDriver(uri.toURL(), options);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Docker WebDriver", e);
        }
    }
}