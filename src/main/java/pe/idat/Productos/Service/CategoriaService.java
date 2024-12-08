package pe.idat.Productos.Service;

import java.util.List;

import pe.idat.Productos.Model.Categoria;

public interface CategoriaService {
    List<Categoria> listarTodas();
    Categoria buscarPorNombre(String nombre);
    Categoria obtenerPorId(Integer idCategoria);
}
