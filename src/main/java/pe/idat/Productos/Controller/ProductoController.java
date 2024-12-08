package pe.idat.Productos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.Productos.Model.Producto;
import pe.idat.Productos.Service.ProductoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @GetMapping("/")
    public ResponseEntity<List<Producto>> listarTodos() {
        List<Producto> productos = productoService.listarTodos();
        return ResponseEntity.ok(productos);
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@PathVariable Integer idCategoria) {
        List<Producto> productos = productoService.buscarPorCategoria(idCategoria);
        return ResponseEntity.ok(productos);
    }
    @GetMapping("/disponibles")
    public ResponseEntity<List<Producto>> buscarDisponible() {
        List<Producto> productos = productoService.buscarDisponibles();
        return ResponseEntity.ok(productos);
    }
    @GetMapping("/{idProducto}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer idProducto) {
        Producto producto = productoService.obtenerPorId(idProducto);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }
    @PostMapping("/")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }
    @PutMapping("/{idProducto}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer idProducto, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizar(idProducto, producto);
        if (productoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoActualizado);
    }
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer idProducto){
        boolean eliminado = productoService.eliminar(idProducto);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
