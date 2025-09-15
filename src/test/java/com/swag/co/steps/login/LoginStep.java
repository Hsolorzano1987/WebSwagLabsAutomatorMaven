package com.swag.co.steps.login;

import com.swag.co.pages.login.LoginPage;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginStep extends LoginPage {

    @Step("Ingresar Usuario")
    public void typeUsername(String username){
        txt_username.sendKeys(username);
    }

    @Step("Ingresar Contraseña ")
    public void typePassword(String password){
        txt_password.sendKeys(password);
    }

    @Step("Click en el boton login")
    public void clickLogin(){
        btn_login.click();

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // Espera a que la alerta esté presente para evitar fallos
            wait.until(ExpectedConditions.alertIsPresent());

            // Cambia el foco a la alerta y la acepta (hace clic en "OK" o "Aceptar")
            driver.switchTo().alert().accept();
            System.out.println("Alerta de cambio de contraseña manejada correctamente.");

        } catch (org.openqa.selenium.NoAlertPresentException | org.openqa.selenium.TimeoutException e) {
            // El test no fallará si la alerta no aparece.
            System.out.println("No se detectó ninguna alerta.");
        }
    }
}
