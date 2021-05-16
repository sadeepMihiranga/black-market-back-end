package lk.sadeep.balckmarket.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity(name = "Product")
@Table(
        name = "product",
        uniqueConstraints = {
                @UniqueConstraint(name = "product_product_name_unique", columnNames = "product_name") // customize the unique constraint
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "product_name",
            nullable = false
    )
    private String productName;

    @Column(
            name = "carton_size",
            nullable = false
    )
    private Integer cartonSize;

    @Column(
            name = "carton_price",
            nullable = false
    )
    private Double cartonPrice;

    @Column(
            name = "image_url",
            nullable = false
    )
    private String imageUrl;
}
