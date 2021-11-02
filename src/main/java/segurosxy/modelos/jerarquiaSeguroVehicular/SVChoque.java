package segurosxy.modelos.jerarquiaSeguroVehicular;

import segurosxy.modelos.interfacesSeguroVehicular.ISVChoque;
import segurosxy.modelos.SeguroVehicular;

public class SVChoque extends SeguroVehicular implements ISVChoque{

    public SVChoque(String marca, String modelo)    {

        super(marca, modelo);
      
    }

    @Override
    public void cacularRiesgo()   {

        if (this.marca.equals("KIA") && this.modelo.equals("Rio")) {
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
