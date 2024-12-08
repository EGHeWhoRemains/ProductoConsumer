package pe.idat.Productos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.idat.Productos.Model.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    //Método para buscar categorias por nombre(busqueda exacta en este caso)
    Categoria findByNombre(String nombre);
}
