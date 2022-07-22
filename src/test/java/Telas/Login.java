package Telas;

import Utils.DSL;
import static org.junit.Assert.*;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class Login extends DSL {
    public void deveFazerLogin(){

        aguardarPorTexto("Bem Vindo ao Diário de Gratidão");
        clicarPorTexto("Entrar");
        MobileElement campoUsuario = getDriver().findElement(By.id(Locator.locatorCampoUsuario));
        campoUsuario.sendKeys("andre@xds.com.br");
        MobileElement campoSenha = getDriver().findElement(By.id(Locator.locatorCampoSenha));
        campoSenha.sendKeys("Teste@2022@");
        getDriver().hideKeyboard();
        esperar(100);
        clicarPorTexto("Entrar");
        aguardarPorTexto("Hoje sou grato por ...");
        assertEquals("Toque no coração para compartilhar o seu primeiro momento Gratidão",
                obterTexto(By.id(Locator.locatorTextoHome)));
        MobileElement coracaoBotao = getDriver().findElement(By.id(Locator.locatorCoracaoBotao));
        coracaoBotao.click();
        aguardarPorTexto("Como você se sente hoje?");
        swipe(0.1, 10);
        clicarPorTexto("agradecer");


    }
}
