package lk.sadeep.balckmarket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto
{
    private Integer id;
    private String productName;
    private Integer cartonSize;
    private Double cartonPrice;
    private String imageUrl;
}
