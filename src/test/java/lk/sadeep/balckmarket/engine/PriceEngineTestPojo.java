package lk.sadeep.balckmarket.engine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceEngineTestPojo
{
    private Double expectedResult;
    private Double cartonPrice;
    private Integer cartonSize;
    private Integer noOfSingleUnits;
    private Integer noOfCartons;
    private Double priceAddedPercentage;
    private Double priceDiscountPercentage;
    private Integer cartonDiscountStartingQuantity;
}

