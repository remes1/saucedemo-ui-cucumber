package com.saucedemo.hooks;

import com.saucedemo.context.PageObjectContext;
import com.saucedo.driver.DriverManager;
import com.saucedo.driver.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private final PageObjectContext context;

    public Hooks(PageObjectContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        WebDriver driver = WebDriverFactory.createDriver(
                WebDriverFactory.EnvType.valueOf(
                        System.getProperty("driver.env", "LOCAL").toUpperCase()
                )
        );

        DriverManager.setDriver(driver);

        context.init(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }

        DriverManager.quitDriver();
    }
}
