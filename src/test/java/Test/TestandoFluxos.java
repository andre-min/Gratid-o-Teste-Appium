package Test;

import Telas.Login;
import org.junit.Test;

public class TestandoFluxos {
    @Test
    public void testandoLogin(){
        Login login = new Login();
        login.deveFazerLogin();
    }
}
