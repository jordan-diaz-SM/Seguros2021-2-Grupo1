package segurosxy.controllers.impl;

import segurosxy.config.Paths;
import segurosxy.controllers.ClienteController;
import segurosxy.repositories.ClienteRepositorio;
import segurosxy.modelos.Cliente;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.BadRequestResponse;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;

public class ClienteControllerImpl implements ClienteController{
    private static final String NOMBRE = "nombre" ;

    private ClienteRepositorio clienteRepositorio;

    public ClienteControllerImpl(ClienteRepositorio cliRepository) {
        this.clienteRepositorio = cliRepository;
    }

    @Override
    public void create(Context context) {

        Cliente cliente = context.bodyAsClass(Cliente.class);
        System.out.println("Seguro: " + cliente);

        clienteRepositorio.create(cliente);
        context.status(HttpStatus.CREATED_201)
                .header(HttpHeader.LOCATION.name(), Paths.formatPostLocation(cliente.getNombre().toString()));

    }

    @Override
    public void delete(Context context) {
        clienteRepositorio.delete((context.pathParam(NOMBRE)).toString());

    }

    @Override
    public void find(Context context) {
        String nombre = (context.pathParam(NOMBRE)).toString();
        Cliente cliente = clienteRepositorio.find(nombre);

        if (cliente == null) {
            throw new NotFoundResponse(String.format("A customer with id '%s' is not found", nombre));
        }

        context.json(cliente);
    }

    @Override
    public void findAll(Context context) {
        context.json(clienteRepositorio.findAll());
    }

    @Override
    public void update(Context context) {
        Cliente cliente = context.bodyAsClass(Cliente.class);
        String nombre = (context.pathParam(NOMBRE)).toString();

        if (cliente.getNombre() != null && !cliente.getNombre().toString().equals(nombre)) {
            throw new BadRequestResponse("Id update is not allowed");
        }

        clienteRepositorio.update(cliente, nombre);

    }
}
