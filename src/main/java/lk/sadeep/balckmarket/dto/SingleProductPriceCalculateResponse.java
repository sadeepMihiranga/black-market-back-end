package lk.sadeep.balckmarket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingleProductPriceCalculateResponse
{
    private ProductDto product;
    private double price;
}
