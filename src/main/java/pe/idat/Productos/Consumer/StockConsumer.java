package pe.idat.Productos.Consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.idat.Productos.Model.ProductoStockRequest;
import pe.idat.Productos.Service.ProductoService;

import java.util.List;

@Component
public class StockConsumer {
    private final ProductoService productoService;

    public StockConsumer(ProductoService productoService) {
        this.productoService = productoService;
    }
    @KafkaListener(topics = "verificar-stock", groupId = "productos-group")
    public void consume(String message) {
        System.out.println("Mensaje recibido de kafka: "+ message);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductoStockRequest> productos = objectMapper.readValue(
                    message,
                    new TypeReference<List<ProductoStockRequest>>() {}
            );
            for (ProductoStockRequest request : productos) {
                boolean isAvailable = productoService.verificarDisponibilidad(request.getIdProducto(), request.getCantidad());
                System.out.println("Producto ID: " + request.getIdProducto() +
                        " | Cantidad Solicitada: " + request.getCantidad() +
                        " | Disponibilidad: " + isAvailable);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar mensaje de Kafka: " + e.getMessage());
        }
    }
}
