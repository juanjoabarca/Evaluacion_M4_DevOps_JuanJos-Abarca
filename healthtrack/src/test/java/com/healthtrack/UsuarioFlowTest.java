package com.healthtrack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assumptions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UsuarioFlowTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8080/";

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions opts = new ChromeOptions()
            .addArguments("--headless", "--disable-gpu", "--window-size=1280,800");
        driver = new ChromeDriver(opts);
    }

    @AfterEach
    void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    void testActualizarPesoEnUI() {
        // Intentamos conectar; si falla, saltamos el test en lugar de fallar
        try {
            driver.get(BASE_URL);
        } catch (WebDriverException e) {
            Assumptions.assumeTrue(false,
                "No se puede conectar a " + BASE_URL + " — salto el test funcional");
        }

        // sigue el flujo normal...
        driver.findElement(By.name("nombre")).sendKeys("TestUser");
        driver.findElement(By.name("peso")).sendKeys("75");
        driver.findElement(By.cssSelector("button[type=submit]")).click();

        driver.findElement(By.name("peso")).clear();
        driver.findElement(By.name("peso")).sendKeys("80");
        driver.findElement(By.cssSelector("button[type=submit]")).click();

        String texto = driver.findElement(By.id("peso-actual")).getText();
        assertTrue(texto.contains("80.0"),
                   "El peso mostrado debe ser 80.0 kg, se obtuvo: " + texto);
    }
}