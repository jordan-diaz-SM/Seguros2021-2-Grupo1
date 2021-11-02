package segurosxy.repositories;

import segurosxy.modelos.Cliente;
import java.util.List;

public interface ClienteRepositorio {
    void create(Cliente cliente);

    Cliente find(String nombre);

    List<Cliente> findAll();

    Cliente update(Cliente post, String nombre);

    void delete(String  nombre);
}
