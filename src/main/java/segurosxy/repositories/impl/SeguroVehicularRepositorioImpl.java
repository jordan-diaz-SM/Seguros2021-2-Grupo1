package segurosxy.repositories.impl;

import segurosxy.modelos.SeguroVehicular;
import segurosxy.repositories.SeguroVehicularRepositorio;

import java.util.List;
import java.util.LinkedList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

public class SeguroVehicularRepositorioImpl implements SeguroVehicularRepositorio {
    private static final String COLLECTION_NAME = "segurosVehiculares";
    private static final FindOneAndReplaceOptions UPDATE_OPTIONS = new FindOneAndReplaceOptions()
            .returnDocument(ReturnDocument.AFTER);

    private final MongoCollection<SeguroVehicular> segurosVehiculares;
    
    public SeguroVehicularRepositorioImpl(MongoDatabase database) {
        this.segurosVehiculares = database.getCollection(COLLECTION_NAME, SeguroVehicular.class);
    }

    @Override
    public void create(SeguroVehicular seguroVehicular) {       
        segurosVehiculares.insertOne(seguroVehicular);
    }

    @Override
    public void delete(Integer numero) {
        segurosVehiculares.deleteOne(new Document("numero", numero));
    }

    @Override
    public SeguroVehicular find(Integer numero) {
        //System.out.println("_id: " + id);
        return segurosVehiculares.find(eq("numero", numero)).first();
    }

    @Override
    public List<SeguroVehicular> findAll() {
        List<SeguroVehicular> result = new LinkedList<>();

        for (SeguroVehicular seguroVehicular : segurosVehiculares.find()) {
            //System.out.println("customer: " + customer);
            result.add(seguroVehicular);
        }

        return result;
    }

    @Override
    public SeguroVehicular update(SeguroVehicular post, Integer numero) {
        return segurosVehiculares.findOneAndReplace(new Document("numero", numero), post, UPDATE_OPTIONS);
    }
}
