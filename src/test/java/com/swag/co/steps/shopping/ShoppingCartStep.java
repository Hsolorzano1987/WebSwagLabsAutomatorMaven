package com.swag.co.steps.shopping;

import com.swag.co.pages.shopping.ShoppingCartPage;
import net.thucydides.core.annotations.Step;

public class ShoppingCartStep extends ShoppingCartPage {

    @Step("Click en checkout")
    public void clickCheckoutButton(){
        checkoutButton.click();
    }

    @Step("Ingresar nombre")
    public void typeFirstName(String firstname){
        txt_firstname.sendKeys(firstname);

    }

    @Step("Ingresar apellido")
    public void typeLastName(String lastname){
        txt_lastname.sendKeys(lastname);
    }

    @Step("Ingresar codigo postal")
    public void typePostal(String postal){
        txt_postal.sendKeys(postal);
    }

    @Step("Click en continuar")
    public void clickContinue(){
        continueButton.click();
    }

    @Step("Click en finalizar")
    public void clickFinish(){
        finishButton.click();
    }
}
