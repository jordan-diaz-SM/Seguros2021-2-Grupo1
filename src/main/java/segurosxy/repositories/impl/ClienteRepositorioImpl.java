package segurosxy.repositories.impl;

import segurosxy.repositories.ClienteRepositorio;
import segurosxy.modelos.Cliente;

import java.util.List;
import java.util.LinkedList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

public class ClienteRepositorioImpl implements ClienteRepositorio{
    private static final String COLLECTION_NAME = "clientes";
    private static final FindOneAndReplaceOptions UPDATE_OPTIONS = new FindOneAndReplaceOptions()
            .returnDocument(ReturnDocument.AFTER);

    private final MongoCollection<Cliente> clientes;
    
    public ClienteRepositorioImpl(MongoDatabase database) {
        this.clientes = database.getCollection(COLLECTION_NAME, Cliente.class);
    }

    @Override
    public void create(Cliente cliente) {       
        clientes.insertOne(cliente);
    }

    @Override
    public void delete(String nombre) {
        clientes.deleteOne(new Document("nombre", nombre));
    }

    @Override
    public Cliente find(String nombre) {
        //System.out.println("_id: " + id);
        return clientes.find(eq("nombre", nombre)).first();
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> result = new LinkedList<>();

        for (Cliente cliente : clientes.find()) {
            //System.out.println("customer: " + customer);
            result.add(cliente);
        }

        return result;
    }

    @Override
    public Cliente update(Cliente post, String nombre) {
        return clientes.findOneAndReplace(new Document("nombre", nombre), post, UPDATE_OPTIONS);
    }
}
