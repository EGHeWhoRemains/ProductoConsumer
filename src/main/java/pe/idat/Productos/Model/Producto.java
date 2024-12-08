package pe.idat.Productos.Model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "descripcion", length = 255)
    private String descripcion;
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;
    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", foreignKey = @ForeignKey(name = "fk_producto_categoria"))
    private Categoria categoria;
}
