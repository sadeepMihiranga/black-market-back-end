package lk.sadeep.balckmarket.service.impl;

import lk.sadeep.balckmarket.dto.PriceTableRequest;
import lk.sadeep.balckmarket.dto.ProductDto;
import lk.sadeep.balckmarket.dto.SingleProductPriceCalculateRequest;
import lk.sadeep.balckmarket.exception.BadApiRequestException;
import lk.sadeep.balckmarket.exception.DataNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceImplementationTest
{
    @Autowired
    private ProductServiceImplementation productServiceImplementation;

    @Test
    @DisplayName("Returned product object should not be null")
    public void shouldReturnProduct()
    {
        // given
        Integer productId = 1;

        // when
        ProductDto product = productServiceImplementation.getAProduct(productId);

        // then
        Assertions.assertTrue(product != null);
    }

    @Test
    @DisplayName("Product list size should be larger than 0")
    public void shouldReturnProducts()
    {
        // then
        Assertions.assertTrue(productServiceImplementation.getAllProducts().size() > 0);
    }

    @Test
    @DisplayName("Should throw a DataNotFoundException custom exception if requested product is not found")
    public void shouldThrowDataNotFoundExceptionWhenProductIsNotFound()
    {
        // given
        Integer productId = 3;

        // then
        Assertions.assertThrows(DataNotFoundException.class, () -> {
            productServiceImplementation.getAProduct(productId);
        });
    }

    @Test
    @DisplayName("Should throw a BadApiRequestException custom exception if requested product PriceTableRequest is not found")
    public void shouldThrowBadApiRequestExceptionWhenRequestedObjectIsNull()
    {
        // given
        PriceTableRequest priceTableRequest = null;

        // then
        Assertions.assertThrows(BadApiRequestException.class, () -> {
            productServiceImplementation.getProductPricesWithUnits(priceTableRequest);
        });
    }

    @Test
    @DisplayName("Should throw a BadApiRequestException custom exception if no of units is less than 1")
    public void shouldThrowBadApiRequestExceptionWhenNoOfUnitsLessThanExpected()
    {
        // given
        PriceTableRequest priceTableRequest = new PriceTableRequest(1, -1);

        // then
        Assertions.assertThrows(BadApiRequestException.class, () -> {
            productServiceImplementation.getProductPricesWithUnits(priceTableRequest);
        });
    }

    @Test
    @DisplayName("Should throw a BadApiRequestException custom exception if productId is less than 0")
    public void shouldThrowBadApiRequestExceptionWhenProductIdLessThanExpected()
    {
        // given
        PriceTableRequest priceTableRequest = new PriceTableRequest(0, 20);

        // then
        Assertions.assertThrows(BadApiRequestException.class, () -> {
            productServiceImplementation.getProductPricesWithUnits(priceTableRequest);
        });
    }

    @Test
    @DisplayName("Should throw a BadApiRequestException custom exception if requested SingleProductPriceCalculateRequest object is null")
    public void shouldThrowBadApiRequestExceptionWhenRequestedObjectIsNullTwo()
    {
        // given
        SingleProductPriceCalculateRequest request = null;

        // then
        Assertions.assertThrows(BadApiRequestException.class, () -> {
            productServiceImplementation.calculatePriceForSingleProduct(request);
        });
    }

    @Test
    @DisplayName("Should throw a BadApiRequestException custom exception if both single units or cartons should be less than 1")
    public void shouldThrowBadApiRequestExceptionWhenBothCartonsAndSingleUnitsNotAsExpected()
    {
        // given
        SingleProductPriceCalculateRequest request = new SingleProductPriceCalculateRequest(1, 0 ,0);

        // then
        Assertions.assertThrows(BadApiRequestException.class, () -> {
            productServiceImplementation.calculatePriceForSingleProduct(request);
        });
    }
}