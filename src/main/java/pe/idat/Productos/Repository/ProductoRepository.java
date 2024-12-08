package pe.idat.Productos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.idat.Productos.Model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    //Método para buscar producto por nombre
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    //Método para buscar productos por categoria
    List<Producto> findByCategoriaIdCategoria(Integer idCategoria);
    //Método para buscar productos disponibles (stock mayor a 0)
    List<Producto> findByStockGreaterThan(Integer stockMinimo);
}
