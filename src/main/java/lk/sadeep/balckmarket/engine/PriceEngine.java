package lk.sadeep.balckmarket.engine;

import org.springframework.stereotype.Component;

import static lk.sadeep.balckmarket.util.AppConstants.DECIMAL_ZERO;

@Component
public class PriceEngine
{
    /**
     * @Method            :   calculateThePricing
     * @Parameters        :   double cartonPrice, int cartonSize, int noOfSingleUnits, int noOfCarton
     * @Description       :   calculate and return optimized price of a product
     * */
    public double calculateThePricing(double cartonPrice, int cartonSize, int noOfSingleUnits, int noOfCarton)
    {
        int calculatedSingleUnits;
        int calculatedCartons = 0;

        if(noOfSingleUnits >= cartonSize)
        {
            calculatedSingleUnits = noOfSingleUnits % cartonSize;
            calculatedCartons = noOfSingleUnits / cartonSize;
        }
        else
            calculatedSingleUnits = noOfSingleUnits;

        if(noOfCarton >= 1)
            calculatedCartons += noOfCarton;

        return calculateOrderAmount(calculatedSingleUnits, calculatedCartons, cartonPrice, cartonSize);
    }

    /**
     * @Method            :   calculateOrderAmount
     * @Parameters        :   int noOfSingleUnits, int noOfCarton, double cartonPrice, int cartonSize
     * @Description       :   calculated and return price of a product as per carton size and no of units
     * */
    private static double calculateOrderAmount(int noOfSingleUnits, int noOfCarton, double cartonPrice, int cartonSize)
    {
        double orderPrice = DECIMAL_ZERO;

        if(noOfSingleUnits < cartonSize && noOfSingleUnits != 0)
            orderPrice += singleUnitAmount(noOfSingleUnits, cartonPrice, cartonSize);

        if(noOfCarton >= 1)
            orderPrice += cartonAmount(noOfCarton, cartonPrice);

        return orderPrice;
    }

    /**
     * @Method            :   singleUnitAmount
     * @Parameters        :   int noOfSingleUnits, double cartonPrice, int cartonSize
     * @Description       :   calculated and return price of a product as single units
     * */
    private static double singleUnitAmount(int noOfSingleUnits, double cartonPrice, int cartonSize)
    {
        return noOfSingleUnits * ((cartonPrice + (cartonPrice * 30/100)) / cartonSize);
    }

    /**
     * @Method            :   cartonAmount
     * @Parameters        :   int noOfCarton, double cartonPrice
     * @Description       :   calculated and return price of a product as cartons
     * */
    private static double cartonAmount(int noOfCarton, double cartonPrice)
    {
        double priceForCartons;

        if(noOfCarton < 3)
            priceForCartons = noOfCarton * cartonPrice;
        else
            priceForCartons = noOfCarton * (cartonPrice - (cartonPrice * 10/100));

        return priceForCartons;
    }
}
