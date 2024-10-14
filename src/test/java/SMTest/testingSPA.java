package SMTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testingSPA extends SmokeTesting {

    WebElement el;
    WebElement cont;

    @Test
    public void testHomePage(){

        el = dr.findElement(By.linkText("Home"));
        el.click();
        cont = dr.findElement(By.id("Content"));
        String expectedTxt = "Welcome to RTX company. This is our homepage";
        Assert.assertTrue(cont.getText().contains(expectedTxt));

    }

    @Test
    public void testAboutUs(){
        el = dr.findElement(By.linkText("About Us"));
        el.click();
        cont = dr.findElement(By.id("Content"));
        String expectedTxt = "Briefly explained about our company RTX";
        Assert.assertTrue(cont.getText().contains(expectedTxt), "About Page content mismatch");
    }

    @Test
    public void testServices(){
        el = dr.findElement(By.linkText("Services"));
        el.click();
        cont = dr.findElement(By.id("Content"));
        String expectedTxt = "The services we provide is mainly in gaming area";
        Assert.assertTrue(cont.getText().contains(expectedTxt), "Service Page content mismatch");
    }

    @Test
    public void testContact(){
        el = dr.findElement(By.linkText("Contact"));
        el.click();
        cont = dr.findElement(By.id("Content"));
        String expectedTxt = "You can contact us here";
        Assert.assertTrue(cont.getText().contains(expectedTxt), "Contact page content mismatch");
    }




}
