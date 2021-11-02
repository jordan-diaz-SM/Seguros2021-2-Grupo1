package segurosxy.controllers.impl;

import segurosxy.config.Paths;
import segurosxy.controllers.SeguroVehicularController;
import segurosxy.repositories.SeguroVehicularRepositorio;
import segurosxy.modelos.SeguroVehicular;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.BadRequestResponse;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;

public class SeguroVehicularControllerImpl implements SeguroVehicularController {
    private static final String NUMERO = "numero" ;

    private SeguroVehicularRepositorio seguroVehicularRepositorio;

    public SeguroVehicularControllerImpl(SeguroVehicularRepositorio segRepository) {
        this.seguroVehicularRepositorio = segRepository;
    }

    @Override
    public void create(Context context) {

        SeguroVehicular seguroVehicular = context.bodyAsClass(SeguroVehicular.class);
        System.out.println("Seguro: " + seguroVehicular);

        seguroVehicularRepositorio.create(seguroVehicular);
        context.status(HttpStatus.CREATED_201)
                .header(HttpHeader.LOCATION.name(), Paths.formatPostLocation(seguroVehicular.getNumero().toString()));

    }

    @Override
    public void delete(Context context) {
        seguroVehicularRepositorio.delete( Integer.parseInt(context.pathParam(NUMERO)));

    }

    @Override
    public void find(Context context) {
        Integer numero = Integer.parseInt(context.pathParam(NUMERO));
        SeguroVehicular seguroVehicular = seguroVehicularRepositorio.find(numero);

        if (seguroVehicular == null) {
            throw new NotFoundResponse(String.format("A customer with id '%s' is not found", numero));
        }

        context.json(seguroVehicular);
    }

    @Override
    public void findAll(Context context) {
        context.json(seguroVehicularRepositorio.findAll());
    }

    @Override
    public void update(Context context) {
        SeguroVehicular seguroVehicular = context.bodyAsClass(SeguroVehicular.class);
        Integer numero = Integer.parseInt(context.pathParam(NUMERO));

        if (seguroVehicular.getNumero() != null && !seguroVehicular.getNumero().toString().equals(numero)) {
            throw new BadRequestResponse("Id update is not allowed");
        }

        seguroVehicularRepositorio.update(seguroVehicular, numero);

    }
}
