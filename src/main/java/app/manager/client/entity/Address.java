package app.manager.client.entity;

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
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)", nullable = false)
    private String id;

    @Column(name = "street", columnDefinition = "VARCHAR(100)", nullable = false)
    private String street;

    @Column(name = "city", columnDefinition = "VARCHAR(100)", nullable = false)
    private String city;

    @Column(name = "country", columnDefinition = "VARCHAR(100)", nullable = false)
    private String country;

    @Column(name = "postal_code", columnDefinition = "VARCHAR(50)", nullable = false)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
