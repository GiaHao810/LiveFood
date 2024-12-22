package app.manager.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "shopping_cart", referencedColumnName = "id", nullable = false)
    private ShoppingCart shoppingCart;

    @Column(name = "quantity", columnDefinition = "DOUBLE", nullable = false)
    private Double quantity;

    @ManyToMany
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private List<Product> product;
}
