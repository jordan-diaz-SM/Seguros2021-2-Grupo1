package segurosxy.modelos.jerarquiaSeguroVehicular;

import segurosxy.modelos.SeguroVehicular;
import segurosxy.modelos.interfacesSeguroVehicular.ISVTodoRiesgo;

public class SVTodoRiesgo extends SeguroVehicular implements ISVTodoRiesgo{
    
    public SVTodoRiesgo(String marca, String modelo)    {

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

    public String metodoSVTodoRiesgo()    {

        return "Seg. Vehicular tipo TodoRiesgo";
    }
}
