package segurosxy.repositories;

import segurosxy.modelos.SeguroVehicular;
import java.util.List;

public interface SeguroVehicularRepositorio {
    void create(SeguroVehicular seguroVehicular);

    SeguroVehicular find(Integer numero);

    List<SeguroVehicular> findAll();

    SeguroVehicular update(SeguroVehicular post, Integer numero);

    void delete(Integer numero);
}
