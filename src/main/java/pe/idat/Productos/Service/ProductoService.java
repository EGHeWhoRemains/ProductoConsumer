package pe.idat.Productos.Service;

import java.util.List;

import pe.idat.Productos.Model.Producto;

public interface ProductoService {
    List<Producto> listarTodos();
    List<Producto> buscarPorNombre(String nombre);
    List<Producto> buscarPorCategoria(Integer idCategoria);
    List<Producto> buscarDisponibles();
    boolean verificarDisponibilidad(Integer idProducto, Integer cantidad);
    Producto obtenerPorId(Integer idProducto);
    Producto guardar(Producto producto);
    Producto actualizar(Integer idProducto, Producto producto);
    boolean eliminar(Integer idProducto);
}
