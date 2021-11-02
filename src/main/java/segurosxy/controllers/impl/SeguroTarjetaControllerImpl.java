package segurosxy.controllers.impl;

import segurosxy.config.Paths;
import segurosxy.controllers.SeguroTarjetaController;
import segurosxy.repositories.SeguroTarjetaRepositorio;
import segurosxy.modelos.SeguroTarjeta;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.BadRequestResponse;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;

public class SeguroTarjetaControllerImpl implements SeguroTarjetaController {
    private static final String NUMERO = "numero" ;

    private SeguroTarjetaRepositorio seguroTarjetaRepositorio;

    public SeguroTarjetaControllerImpl(SeguroTarjetaRepositorio segRepository) {
        this.seguroTarjetaRepositorio = segRepository;
    }

    @Override
    public void create(Context context) {

        SeguroTarjeta seguroTarjeta = context.bodyAsClass(SeguroTarjeta.class);
        System.out.println("Seguro: " + seguroTarjeta);

        seguroTarjetaRepositorio.create(seguroTarjeta);
        context.status(HttpStatus.CREATED_201)
                .header(HttpHeader.LOCATION.name(), Paths.formatPostLocation(seguroTarjeta.getNumero().toString()));

    }

    @Override
    public void delete(Context context) {
        seguroTarjetaRepositorio.delete( Integer.parseInt(context.pathParam(NUMERO)));

    }

    @Override
    public void find(Context context) {
        Integer numero = Integer.parseInt(context.pathParam(NUMERO));
        SeguroTarjeta seguroTarjeta = seguroTarjetaRepositorio.find(numero);

        if (seguroTarjeta == null) {
            throw new NotFoundResponse(String.format("A customer with id '%s' is not found", numero));
        }

        context.json(seguroTarjeta);
    }

    @Override
    public void findAll(Context context) {
        context.json(seguroTarjetaRepositorio.findAll());
    }

    @Override
    public void update(Context context) {
        SeguroTarjeta seguroTarjeta = context.bodyAsClass(SeguroTarjeta.class);
        Integer numero = Integer.parseInt(context.pathParam(NUMERO));

        if (seguroTarjeta.getNumero() != null && !seguroTarjeta.getNumero().toString().equals(numero)) {
            throw new BadRequestResponse("Id update is not allowed");
        }

        seguroTarjetaRepositorio.update(seguroTarjeta, numero);

    }

}
