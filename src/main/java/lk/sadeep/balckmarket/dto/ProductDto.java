package lk.sadeep.balckmarket.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto
{
    private Integer id;
    private String productName;
    private Integer cartonSize;
    private Double cartonPrice;
    private String imageUrl;
    private Double priceAddedPercentage;
    private Double priceDiscountPercentage;
    private Integer cartonDiscountStartingQuantity;
}
