package app.manager.client.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order")
public class Order{
    @Id
    private String id;

    private Customer customer;
    private double totalPrice;
    private double subTotal;
    private double disCount;
    private Date orderDate;
    private List<Product> productList;
}
