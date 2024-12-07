package app.manager.client.entity;

import app.manager.client.entity.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)", nullable = false)
    private String id;

    @Column(name = "code", columnDefinition = "VARCHAR(50)", nullable = false)
    private String code;

    @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Column(name="price", columnDefinition = "DOUBLE", nullable = false)
    private Double price;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToOne(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    private ProductDetail productDetails;


    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    private CartItem cartItem;
}
