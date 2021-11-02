package segurosxy;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;

import segurosxy.config.DBConnectionManager;
import segurosxy.modelos.Cliente;
import segurosxy.modelos.SeguroTarjeta;
import segurosxy.modelos.SeguroVehicular;
import segurosxy.modelos.jerarquiaSeguroVehicular.SVChoque;

import segurosxy.repositories.impl.SeguroTarjetaRepositorioImpl;
import segurosxy.controllers.impl.SeguroTarjetaControllerImpl;
import segurosxy.repositories.impl.SeguroVehicularRepositorioImpl;
import segurosxy.controllers.impl.SeguroVehicularControllerImpl;
import segurosxy.repositories.impl.ClienteRepositorioImpl;
import segurosxy.controllers.impl.ClienteControllerImpl;

public class App {
    private final DBConnectionManager manager;
    private final SeguroTarjetaControllerImpl seguroTarjetaController;
    private final SeguroVehicularControllerImpl seguroVehicularController;
    private final ClienteControllerImpl clienteController;

    public App() {
        this.manager = new DBConnectionManager();

        SeguroTarjetaRepositorioImpl seguroTarjetaRepositorioImpl = new SeguroTarjetaRepositorioImpl(this.manager.getDatabase());
        this.seguroTarjetaController = new SeguroTarjetaControllerImpl(seguroTarjetaRepositorioImpl);

        SeguroVehicularRepositorioImpl seguroVehicularRepositorioImpl = new SeguroVehicularRepositorioImpl(this.manager.getDatabase());
        this.seguroVehicularController = new SeguroVehicularControllerImpl(seguroVehicularRepositorioImpl);

        ClienteRepositorioImpl clienteRepositorioImpl = new ClienteRepositorioImpl(this.manager.getDatabase());
        this.clienteController = new ClienteControllerImpl(clienteRepositorioImpl);
    }

    public void startup() {
        Info applicationInfo = new Info()
                .version("1.0")
                .description("Demo API");

        OpenApiOptions openApi = new OpenApiOptions(applicationInfo)
                .path("/api")
                .swagger(new SwaggerOptions("/api-ui")); // endpoint for swagger-ui
        
        Javalin server = Javalin.create(
                    config -> {
                        config.registerPlugin(new OpenApiPlugin(openApi));
                    }
            ).start(7000);

        System.out.println(server);             
        server.get("api/seguroTarjeta/:numero", this.seguroTarjetaController::find);
        server.delete("api/seguroTarjeta/:id", this.seguroTarjetaController::delete);
        server.get("api/segurosTarjetas", this.seguroTarjetaController::findAll);
        server.post("api/seguroTarjeta", this.seguroTarjetaController::create);

        server.get("api/seguroVehicular/:numero", this.seguroVehicularController::find);
        server.delete("api/seguroVehicular/:id", this.seguroVehicularController::delete);
        server.get("api/segurosVehiculares", this.seguroVehicularController::findAll);
        server.post("api/seguroVehicular", this.seguroVehicularController::create);

        server.get("api/cliente/:nombre", this.clienteController::find);
        server.delete("api/cliente/:id", this.clienteController::delete);
        server.get("api/clientes", this.clienteController::findAll);
        server.post("api/cliente", this.clienteController::create);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            this.manager.closeDatabase();
            server.stop();
        }));
    }
    public static void main( String[] args )
    {
        new App().startup();
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
