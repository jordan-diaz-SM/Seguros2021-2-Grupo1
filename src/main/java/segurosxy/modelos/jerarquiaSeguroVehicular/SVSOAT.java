package segurosxy.modelos.jerarquiaSeguroVehicular;

import segurosxy.modelos.SeguroVehicular;
import segurosxy.modelos.interfacesSeguroVehicular.ISVSOAT;

public class SVSOAT extends SeguroVehicular implements ISVSOAT{
    
    public SVSOAT(String marca, String modelo)    {

        super(marca, modelo);
     
    }

    @Override
    public void cacularRiesgo()   {

        if (this.marca.equals("MODASA") && this.modelo.equals("Custer")) {
            this.nivelRiesgo = "ALTO";
        }
        else {
            this.nivelRiesgo = "BAJO";
        }
    }

    @Override
    public String getDetalleSeguro()    {

        return "Seg. Vehicular Numero: " + this.numero + " con riesgo: " + this.nivelRiesgo;
    }

    public String metodoSVSOAT()    {

        return "Seg. Vehicular tipo SOAT";
    }
}
