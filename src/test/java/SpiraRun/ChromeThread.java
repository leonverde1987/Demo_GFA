/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpiraRun;

import com.inflectra.spiratest.addons.junitextension.SpiraTestListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author TestingIT
 */
public class ChromeThread extends Thread {
    
    @Override
    public void run(){
        Properties Config=null;
        try{
            Config = new generic.genericGrid().getPropetiesFile("configuracion\\configuracion.properties");
        }catch(Exception e){
            System.out.println("Mensaje: "+e);
        }
        JUnitCore core = new JUnitCore();
        core.addListener(new SpiraTestListener());
        //new generic.genericGrid().leventarNodosGrid();
        Config.setProperty("NavegadorBol","chrome");
        try{
        Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a Chrome");
        }catch(Exception e){
            System.out.println("Mensaje: "+e);
        }
        core.run (TestCases.Test_Boletos_Reservar.class);
        Config.setProperty("NavegadorFac","chrome");
        try{
            Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a Chrome");
        }catch(Exception e){
            System.out.println("Mensaje: "+e);
        }
        core.run (TestCases.Test_Facturas_Facturacion.class);
        Config.setProperty("NavegadorPaq","chrome");
        try{
            Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a Chrome");
        }catch(Exception e){
            System.out.println("Mensaje: "+e);
        }
        core.run (TestCases.Test_Paqueteria_Buscar_Guia.class);
    }
    
}
