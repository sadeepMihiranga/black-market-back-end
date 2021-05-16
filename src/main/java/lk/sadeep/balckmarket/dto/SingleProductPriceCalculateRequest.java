package lk.sadeep.balckmarket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SingleProductPriceCalculateRequest
{
    private Integer productId;
    private Integer noOfSingleUnits;
    private Integer noOfCartons;
}
