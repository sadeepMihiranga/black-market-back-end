package lk.sadeep.balckmarket.engine;

import lk.sadeep.balckmarket.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.ArrayList;

class PriceEngineTest
{
    private final PriceEngine priceEngine = new PriceEngine();

    @Test
    public void shouldCalculateOptimizedPriceForSingleUnits()
    {
        // given
        List<PriceEngineTestPojo> priceEngineTestPojoList = new ArrayList<>();

        ProductDto productDto = new ProductDto();

        // Penguin-ears
        priceEngineTestPojoList.add(new PriceEngineTestPojo(91.0, 175.0, 20, 8, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(147.875, 175.0, 20, 13, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(231.875, 175.0, 20, 25, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(472.5, 175.0, 20, 60, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(529.375, 175.0, 20, 65, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(609.0, 175.0, 20, 72, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(787.5, 175.0, 20, 100, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(1633.625, 175.0, 20, 199, 0, 0.3, 0.1, 3));

        // Horseshoe
        priceEngineTestPojoList.add(new PriceEngineTestPojo(429.0, 825.0, 5, 2, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(825.0, 825.0, 5, 5, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(1039.5, 825.0, 5, 6, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(1468.5, 825.0, 5, 8, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(2656.5, 825.0, 5, 17, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(3085.5, 825.0, 5, 19, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(4356.0, 825.0, 5, 28, 0, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(5098.5, 825.0, 5, 33, 0, 0.3, 0.1, 3));

        for(int i = 0; i < priceEngineTestPojoList.size(); i++)
        {
            PriceEngineTestPojo pojo = priceEngineTestPojoList.get(i);

            productDto.setCartonPrice(pojo.getCartonPrice());
            productDto.setCartonSize(pojo.getCartonSize());
            productDto.setCartonDiscountStartingQuantity(pojo.getCartonDiscountStartingQuantity());
            productDto.setPriceAddedPercentage(pojo.getPriceAddedPercentage());
            productDto.setPriceDiscountPercentage(pojo.getPriceDiscountPercentage());

            // when
            double result = priceEngine.calculateThePricing(productDto, pojo.getNoOfSingleUnits(), pojo.getNoOfCartons());

            // then
            Assertions.assertEquals(pojo.getExpectedResult(), result);
        }
    }

    @Test
    public void shouldCalculateOptimizedPriceForCartons()
    {
        // given
        List<PriceEngineTestPojo> priceEngineTestPojoList = new ArrayList<>();

        ProductDto productDto = new ProductDto();

        // Penguin-ears
        priceEngineTestPojoList.add(new PriceEngineTestPojo(175.0, 175.0, 20, 0, 1, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(350.0, 175.0, 20, 0, 2, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(472.5, 175.0, 20, 0, 3, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(630.0, 175.0, 20, 0, 4, 0.3, 0.1, 3));

        // Horseshoe
        priceEngineTestPojoList.add(new PriceEngineTestPojo(825.0, 825.0, 5, 0, 1, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(1650.0, 825.0, 5, 0, 2, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(2227.5, 825.0, 5, 0, 3, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(2970.0, 825.0, 5, 0, 4, 0.3, 0.1, 3));

        for(int i = 0; i < priceEngineTestPojoList.size(); i++)
        {
            PriceEngineTestPojo pojo = priceEngineTestPojoList.get(i);

            productDto.setCartonPrice(pojo.getCartonPrice());
            productDto.setCartonSize(pojo.getCartonSize());
            productDto.setCartonDiscountStartingQuantity(pojo.getCartonDiscountStartingQuantity());
            productDto.setPriceAddedPercentage(pojo.getPriceAddedPercentage());
            productDto.setPriceDiscountPercentage(pojo.getPriceDiscountPercentage());

            // when
            double result = priceEngine.calculateThePricing(productDto, pojo.getNoOfSingleUnits(), pojo.getNoOfCartons());

            // then
            Assertions.assertEquals(pojo.getExpectedResult(), result);
        }
    }

    @Test
    public void shouldCalculateOptimizedPriceWithCartonsAndSingleUnits()
    {
        // given
        List<PriceEngineTestPojo> priceEngineTestPojoList = new ArrayList<>();

        ProductDto productDto = new ProductDto();

        // Penguin-ears
        priceEngineTestPojoList.add(new PriceEngineTestPojo(186.375, 175.0, 20, 1, 1, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(350.0, 175.0, 20, 20, 1, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(506.625, 175.0, 20, 23, 2, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(924.0, 175.0, 20, 12, 5, 0.3, 0.1, 3));

        // Horseshoe
        priceEngineTestPojoList.add(new PriceEngineTestPojo(1039.5, 825.0, 5, 1, 1, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(1650.0, 825.0, 5, 5, 1, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(2871.0, 825.0, 5, 3, 3, 0.3, 0.1, 3));
        priceEngineTestPojoList.add(new PriceEngineTestPojo(5313.0, 825.0, 5, 9, 5, 0.3, 0.1, 3));

        for(int i = 0; i < priceEngineTestPojoList.size(); i++)
        {
            PriceEngineTestPojo pojo = priceEngineTestPojoList.get(i);

            productDto.setCartonPrice(pojo.getCartonPrice());
            productDto.setCartonSize(pojo.getCartonSize());
            productDto.setCartonDiscountStartingQuantity(pojo.getCartonDiscountStartingQuantity());
            productDto.setPriceAddedPercentage(pojo.getPriceAddedPercentage());
            productDto.setPriceDiscountPercentage(pojo.getPriceDiscountPercentage());

            // when
            double result = priceEngine.calculateThePricing(productDto, pojo.getNoOfSingleUnits(), pojo.getNoOfCartons());

            // then
            Assertions.assertEquals(pojo.getExpectedResult(), result);
        }
    }
}