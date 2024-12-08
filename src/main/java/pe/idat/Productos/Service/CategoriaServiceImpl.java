package pe.idat.Productos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.idat.Productos.Model.Categoria;
import pe.idat.Productos.Repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public List<Categoria> listarTodas(){
        return categoriaRepository.findAll();
    }
    @Override
    public Categoria buscarPorNombre(String nombre){
        return categoriaRepository.findByNombre(nombre);
    }
    @Override
    public Categoria obtenerPorId(Integer idCategoria){
        return categoriaRepository.findById(idCategoria).orElse(null);//En caso no encuentre la categoria
    }
}
