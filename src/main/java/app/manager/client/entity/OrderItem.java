package app.manager.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Product product;

    @Column(name = "quantity", columnDefinition = "DOUBLE", nullable = false)
    private Double quantity;

    @Column(name = "price",  columnDefinition = "DOUBLE", nullable = false)
    private Double price;
}
