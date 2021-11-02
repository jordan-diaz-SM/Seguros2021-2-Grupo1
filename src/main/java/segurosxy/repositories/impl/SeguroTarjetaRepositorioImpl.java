package segurosxy.repositories.impl;

import segurosxy.modelos.SeguroTarjeta;
import segurosxy.repositories.SeguroTarjetaRepositorio;

import java.util.List;
import java.util.LinkedList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

public class SeguroTarjetaRepositorioImpl implements SeguroTarjetaRepositorio {

    private static final String COLLECTION_NAME = "segurosTarjetas";
    private static final FindOneAndReplaceOptions UPDATE_OPTIONS = new FindOneAndReplaceOptions()
            .returnDocument(ReturnDocument.AFTER);

    private final MongoCollection<SeguroTarjeta> segurosTarjetas;
    
    public SeguroTarjetaRepositorioImpl(MongoDatabase database) {
        this.segurosTarjetas = database.getCollection(COLLECTION_NAME, SeguroTarjeta.class);
    }

    @Override
    public void create(SeguroTarjeta seguroTarjeta) {       
        segurosTarjetas.insertOne(seguroTarjeta);
    }

    @Override
    public void delete(Integer numero) {
        segurosTarjetas.deleteOne(new Document("numero", numero));
    }

    @Override
    public SeguroTarjeta find(Integer numero) {
        //System.out.println("_id: " + id);
        return segurosTarjetas.find(eq("numero", numero)).first();
    }

    @Override
    public List<SeguroTarjeta> findAll() {
        List<SeguroTarjeta> result = new LinkedList<>();

        for (SeguroTarjeta seguroTarjeta : segurosTarjetas.find()) {
            //System.out.println("customer: " + customer);
            result.add(seguroTarjeta);
        }

        return result;
    }

    @Override
    public SeguroTarjeta update(SeguroTarjeta post, Integer numero) {
        return segurosTarjetas.findOneAndReplace(new Document("numero", numero), post, UPDATE_OPTIONS);
    }
}
