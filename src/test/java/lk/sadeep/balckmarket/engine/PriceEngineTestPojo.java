package lk.sadeep.balckmarket.engine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceEngineTestPojo
{
    Double expectedResult;
    Double cartonPrice;
    Integer cartonSize;
    Integer noOfSingleUnits;
    Integer noOfCartons;
}

