package Utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

public class DSL extends DriverFatory{
    public void escrever (By by, String texto) {

        getDriver().findElement(by).sendKeys(texto);

    }

    public String obterTexto(By by) {

        return getDriver().findElement(by).getText();

    }

    public void clicar (By by) {

        getDriver().findElement(by).click();

    }

    public void clicarPorTexto (String texto) {

        clicar(By.xpath("//*[@text='"+ texto.trim() +"']"));
    }

    public void aguardarPorTexto(String texto){
        WebDriverWait aguardando = new WebDriverWait(getDriver(),40);
        aguardando.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='"+ texto.trim() +"']")));
    }
    public void aguardandoBy(By by){
        WebDriverWait aguardando = new WebDriverWait(getDriver(),40);
        aguardando.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void aguardarPagina (String className){
        WebDriverWait aguardarElemento = new WebDriverWait(getDriver(),40);
        aguardarElemento.until(ExpectedConditions.presenceOfElementLocated(By.className(className.trim())));
    }

    public void tap(int x, int y) {
        TouchAction touch = new TouchAction(getDriver()).tap(PointOption.point(x, y)).perform(); }


    public void metadeScrollUp(){ scroll(0.7,0.6);}
    public void scrollUpTransferenciaContaInterna(){scroll(0.7, 0.5);}
    public void scrollDown() { scroll(0.3, 0.7); }
    public void scrollUp() { scroll(0.7, 0.3); }
    public void scrollUpPix (){scroll(0.7,0.4);}
    //public void scrollCartao(){scroll(0.9, 0.3);}

    public void swipeRigth() { swipe(0.9, 0.1); }
    public void swipeLeft() { swipe(0.1, 0.9); }

    public void scroll(double inicio, double fim) {
        Dimension size = getDriver().manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height*inicio);
        int end_y = (int) (size.height*fim);
        new TouchAction(getDriver())
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();

    }

    public void scrollAndClick(String visibleText) {
        getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                ".scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))").click();
    }
    public void scrollAndgetText(String visibleText){
        getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                ".scrollIntoView(new UiSelector().textConteins(\""+visibleText+"\").instance(0))").getText();
    }


    public void swipe(double inicio, double fim) {
        Dimension size = getDriver().manage().window().getSize();
        int y = size.height / 2;

        int start_x = (int) (size.width * inicio);
        int end_x = (int) (size.width * fim);

        new TouchAction(getDriver())
                .press(PointOption.point(start_x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(end_x, y))
                .release()
                .perform();

    }

    public void swipeFinalTela(double inicio, double fim) {
        Dimension size = getDriver().manage().window().getSize();
        int y = size.height;

        int start_x = (int) (size.width * inicio);
        int end_x = (int) (size.width * fim);

        new TouchAction(getDriver())
                .press(PointOption.point(start_x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(end_x, y))
                .release()
                .perform();

    }

    public void swipeDivisaoDeTres (double inicio, double fim){
        Dimension size = getDriver().manage().window().getSize();
        int y = size.height / 3;

        int start_x = (int) (size.width * inicio);
        int end_x = (int) (size.width * fim);

        new TouchAction(getDriver())
                .press(PointOption.point(start_x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(end_x, y))
                .release()
                .perform();



    }

    public void swipeDaEsquerdaParaDireitaTelaDivididoEmTres(){
        swipeDivisaoDeTres(0.9, 0.1);
    }



    public void swipeDaDireitaParaEsquerda(){
        swipe(0.8,0.1);
    }


    public void esperar(long tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void buscaDeElemento(By by){
        MobileElement element = getElement(by);
        while (element == null) {
            scroll(0.7, 0.5);
            //scrollUpTransferenciaContaInterna();
            element = getElement(by);

        }

    }
    private MobileElement getElement(By by) {
        try {
            return getDriver().findElement(by);
        } catch (Exception e) {
            return null;
        }

    }
    public void gerarEvidenciaDasTelas() {
        File imagem = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(imagem, new File("target/evidencia/" + UUID.randomUUID().toString() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
