/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpiraRun;

import com.inflectra.spiratest.addons.junitextension.SpiraTestListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.junit.runner.JUnitCore;

/**
 *
 * @author TestingIT
 */
public class FireFoxThread extends Thread {
    
    @Override
    public void run(){
        Properties Config =null;
        try{
            Config = new generic.genericGrid().getPropetiesFile("configuracion\\configuracion.properties");
        }catch(Exception e){
            System.out.println("Mensaje: "+e);
        }
        JUnitCore core = new JUnitCore();
        core.addListener(new SpiraTestListener());
        Config.setProperty("NavegadorPaq","firefox");
        try{
            Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a firefox");
        }catch(Exception e){
            System.out.println("Mensaje: "+e);
        }
        
        core.run (TestCases.Test_Paqueteria_Buscar_Guia.class);
        Config.setProperty("NavegadorFac","firefox");
        try{
            Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a firefox");
        }catch(Exception e){
            System.out.println("Mensaje: "+e);
        }
        core.run (TestCases.Test_Facturas_Facturacion.class);
        Config.setProperty("NavegadorBol","firefox");
        try{
            Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a firefox");
        }catch(Exception e){
            System.out.println("Mensaje: "+e);
        }
        core.run (TestCases.Test_Boletos_Reservar.class);
    }
    
}
