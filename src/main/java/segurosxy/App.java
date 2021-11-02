package segurosxy;

import segurosxy.modelos.Cliente;
import segurosxy.modelos.SeguroTarjeta;
import segurosxy.modelos.SeguroVehicular;
import segurosxy.modelos.jerarquiaSeguroVehicular.SVChoque;

public class App {

    public static void main( String[] args )
    {

        Cliente cliente = new Cliente("Juan Perez");

        SeguroVehicular seguro = new SeguroVehicular("Toyota","Yaris");
        seguro.cacularRiesgo();
        cliente.setCompraSeguro(seguro);

        SeguroTarjeta seguro2 = new SeguroTarjeta("BCP");
        seguro2.cacularRiesgo();
        cliente.setCompraSeguro(seguro2);

        SVChoque seguro3 = new SVChoque("KIA", "Rio");
        seguro3.cacularRiesgo();
       // System.out.println(seguro3.metodoSVChoque());
        cliente.setCompraSeguro(seguro3);
        
        cliente.getListaSeguroCliente();
        //Changes
        
    }

}
