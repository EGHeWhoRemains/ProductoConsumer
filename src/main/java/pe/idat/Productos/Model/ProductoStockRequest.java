package pe.idat.Productos.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoStockRequest {
    private Integer idProducto;
    private Integer cantidad;
    @Override
    public String toString() {
        return "ProductoStockRequest{" +
                "idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                '}';
    }
}
