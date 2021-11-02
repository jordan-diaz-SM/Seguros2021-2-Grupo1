package segurosxy.repositories;

import segurosxy.modelos.SeguroTarjeta;
import java.util.List;

public interface SeguroTarjetaRepositorio {
    void create(SeguroTarjeta seguroTarjeta);

    SeguroTarjeta find(Integer numero);

    List<SeguroTarjeta> findAll();

    SeguroTarjeta update(SeguroTarjeta post, Integer numero);

    void delete(Integer numero);
}
