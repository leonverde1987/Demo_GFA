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
import java.util.Properties;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 *
 * @author TestingIT
 */
public class RunGrid {
    FireFoxThread firefox = new FireFoxThread();
    ChromeThread chrome = new ChromeThread();
    
    @Test
    public void TestBuscarGoogleGrid() throws InterruptedException, FileNotFoundException, IOException {
        Properties Config = new generic.genericGrid().getPropetiesFile("configuracion\\configuracion.properties");
        JUnitCore core = new JUnitCore();
        core.addListener(new SpiraTestListener());
        //new generic.genericGrid().leventarNodosGrid();
        Config.setProperty("Navegador","chrome");
        Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a Chrome");
        core.run (TestCases.Test_Boletos_Reservar.class);
        core.run (TestCases.Test_Facturas_Facturacion.class);
        core.run (TestCases.Test_Paqueteria_Buscar_Guia.class);
        Config.setProperty("Navegador","firefox");
        Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a firefox");
        core.run (TestCases.Test_Boletos_Reservar.class);
        core.run (TestCases.Test_Facturas_Facturacion.class);
        core.run (TestCases.Test_Paqueteria_Buscar_Guia.class);
        //new generic.genericGrid().cierraNodosGrid();

        
//        chrome.start();
//        firefox.start();
    }
}