package lk.sadeep.balckmarket.engine;

import org.springframework.stereotype.Component;

import static lk.sadeep.balckmarket.util.AppConstants.DECIMAL_ZERO;

@Component
public class PriceEngine
{
    private static final Double PRICE_ADDED_PERCENTAGE = 0.3;
    private static final Double PRICE_DISCOUNT_PERCENTAGE = 0.1;
    private static final Integer CARTON_DISCOUNT_STARTING_RANGE = 3;

    /**
     * @Method            :   calculateThePricing
     * @Parameters        :   double cartonPrice, int cartonSize, int noOfSingleUnits, int noOfCarton
     * @Description       :   calculate and return optimized price of a product
     * */
    public double calculateThePricing(double cartonPrice, int cartonSize, int noOfSingleUnits, int noOfCarton)
    {
        int calculatedSingleUnits;
        int calculatedCartons = 0;

        // if no of single units are greater that carton size then do the optimizing calculations
        if(noOfSingleUnits >= cartonSize)
        {
            calculatedSingleUnits = noOfSingleUnits % cartonSize;
            calculatedCartons = noOfSingleUnits / cartonSize;
        }
        else
            calculatedSingleUnits = noOfSingleUnits;

        // if requested no of cartons are also greater than 1 then add optimized no of carton with requested cartons
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

        // if optimized no of units less than carton size and not equal to the zero then call single unit's price calculation method
        if(noOfSingleUnits < cartonSize && noOfSingleUnits != 0)
            orderPrice += singleUnitAmount(noOfSingleUnits, cartonPrice, cartonSize);

        // if there are more than one carton then call carton price calculation method
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
        return noOfSingleUnits * ((cartonPrice + (cartonPrice * PRICE_ADDED_PERCENTAGE)) / cartonSize);
    }

    /**
     * @Method            :   cartonAmount
     * @Parameters        :   int noOfCarton, double cartonPrice
     * @Description       :   calculated and return price of a product as cartons
     * */
    private static double cartonAmount(int noOfCarton, double cartonPrice)
    {
        double priceForCartons;

        if(noOfCarton < CARTON_DISCOUNT_STARTING_RANGE)
            priceForCartons = noOfCarton * cartonPrice;
        else
            priceForCartons = noOfCarton * (cartonPrice - (cartonPrice * PRICE_DISCOUNT_PERCENTAGE));

        return priceForCartons;
    }
}
