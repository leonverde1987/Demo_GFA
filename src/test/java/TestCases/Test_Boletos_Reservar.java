package TestCases;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import com.inflectra.spiratest.addons.junitextension.*;
import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;
import steps.steps_Boletos_Reservar;

@SpiraTestConfiguration(
	    url="https://testing-it.spiraservice.net",
	    login="automationQA",
	    password="test1234", 
	    projectId=67,
	    testSetId=73
	)

public class Test_Boletos_Reservar extends steps_Boletos_Reservar{
    public Properties Config = null;
    public Properties Datos = null;
    public Properties Elementos = null;
    private RemoteWebDriver driver = null;
    public List<String> Pasos = new ArrayList<String>();
    public int contador = 0;
    public String Resultado = "";
    public String Escenario = "";
    public String RutaEvidencia = "";
    public String Navegador="";
    
    
    @Before
    public void PrepararEjecucion() throws FileNotFoundException, MalformedURLException, InterruptedException{
        Config = this.getPropetiesFile("configuracion\\configuracion.properties");
        Datos = this.getPropetiesFile("configuracion\\datosBoletosReservar.properties");
        Elementos = this.getPropetiesFile("configuracion\\pageBoletosReservar.properties");
        contador = 1;
        RutaEvidencia = Config.getProperty("rutaEvidencia");
        Resultado = "Fallido";
        Navegador = Config.getProperty("Navegador");
        driver = this.openGridBrowser(Navegador, Config);
        
    }
    
    @Test
    @SpiraTestCase(testCaseId=7681)
    public void Test_Ver_Horarios_Boletos() throws InterruptedException, DocumentException, BadElementException, IOException, Exception {
        try{
            Escenario = "BTO_Reservar_Buscar sin Origen y Destino.";
            Navegador = this.navegador(driver.toString());
            
            //Paso 1
            Pasos.add(contador+".- Abrir navegador en la URL: "+Config.getProperty("urlAppBoletos"));
            this.ingresar_A_URL(driver, contador, Config, Escenario, Navegador);
            
            //Paso 2
            contador++;
            Pasos.add(contador+".- Presionar el Botón: Buscar.");
            this.presionar_Buscar(driver, contador, Config, Elementos, Escenario, Navegador);
            
            //paso 3
            contador++;
            Pasos.add(contador+".- Validar que existe el mensaje "+Datos.getProperty("mensajeAssert")+" y no permita avanzar si no tiene Origen y Destino");
            Resultado = this.validar_Mensaje(driver, Datos, Config, Elementos, contador, Escenario, Navegador);
            
            
        }catch(NoSuchElementException s){
            Resultado = "Ejecución Fallida, No se encontró elemento: "+s;
            this.capturarEvidencia(driver, Config, contador, Escenario, Navegador);
            System.out.println(Resultado);
        }catch(InterruptedException e){
            Resultado = "Ejecución Fallida: "+e;
            this.capturarEvidencia(driver, Config, contador, Escenario, Navegador);
            System.out.println(Resultado);
        }finally{
            this.finalizarTestCase(driver, Escenario, Resultado, contador, Pasos, RutaEvidencia, Config.getProperty("Modulo"), Config.getProperty("Version"), Navegador);
        }
    }
    
    
    @After
    public void cerrarTest(){
        this.cerrar_Navegador(driver);
    }

    

    
}
