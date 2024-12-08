package pe.idat.Productos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.idat.Productos.Model.Producto;
import pe.idat.Productos.Repository.CategoriaRepository;
import pe.idat.Productos.Repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }
    @Override
    public List<Producto> buscarPorNombre(String nombre){
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    @Override
    public List<Producto> buscarPorCategoria(Integer idCategoria) {
        return productoRepository.findByCategoriaIdCategoria(idCategoria);
    }
    @Override
    public List<Producto> buscarDisponibles() {
        return productoRepository.findByStockGreaterThan(0);
    }

    @Override
    public boolean verificarDisponibilidad(Integer idProducto, Integer cantidad) {
        return productoRepository.findById(idProducto).map(producto ->
                producto.getStock() >= cantidad).orElse(false);
    }

    @Override
    public Producto obtenerPorId(Integer idProducto){
        return productoRepository.findById(idProducto).orElse(null); //Maneja casos donde no se encuentre el producto
    }
    @Override
    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }
    @Override
    public Producto actualizar(Integer idProducto,Producto producto){
        Producto productoUpdate = productoRepository.findById(idProducto).orElse(null);
        if (productoUpdate == null) {
            return null;
        }
        validarCategoria(producto.getCategoria().getIdCategoria());
        productoUpdate.setNombre(producto.getNombre());
        productoUpdate.setDescripcion(producto.getDescripcion());
        productoUpdate.setPrecio(producto.getPrecio());
        productoUpdate.setStock(producto.getStock());
        productoUpdate.setCategoria(producto.getCategoria());
        return productoRepository.save(productoUpdate);
    }
    @Override
    public boolean eliminar(Integer idProducto){
        if (productoRepository.existsById(idProducto)) {
            productoRepository.deleteById(idProducto);
            return true;
        }
        return false;
    }
    private void validarCategoria(Integer idCategoria){
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new IllegalArgumentException("La categoria con ID "+idCategoria+"no existe.");
        }
    }
}
