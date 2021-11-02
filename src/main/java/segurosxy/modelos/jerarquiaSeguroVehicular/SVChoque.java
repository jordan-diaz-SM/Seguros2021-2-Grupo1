package segurosxy.modelos.jerarquiaSeguroVehicular;

import segurosxy.modelos.SeguroVehicular;
import segurosxy.modelos.interfacesSeguroVehicular.ISVChoque;

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

        return "Seg. Vehicular tipo Choque Numero: " + this.numero + " con riesgo: " + this.nivelRiesgo;
    }

    public String metodoSVChoque()    {

        return "Seg. Vehicular tipo Choque";
    }
}
