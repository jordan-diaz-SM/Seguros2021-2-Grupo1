package segurosxy.modelos.jerarquiaSeguroVehicular;

import segurosxy.modelos.interfacesSeguroVehicular.ISVRobo;
import segurosxy.modelos.SeguroVehicular;

public class SVRobo extends SeguroVehicular implements ISVRobo{

    public SVRobo(String marca, String modelo)    {

        super(marca, modelo);
     
    }

    @Override
    public void cacularRiesgo()   {

        if (this.marca.equals("Toyota") && this.modelo.equals("HYLUX")) {
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
}
