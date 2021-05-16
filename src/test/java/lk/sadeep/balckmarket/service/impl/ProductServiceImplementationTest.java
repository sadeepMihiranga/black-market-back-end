package lk.sadeep.balckmarket.service.impl;

import lk.sadeep.balckmarket.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceImplementationTest
{
    @Autowired
    private ProductServiceImplementation productServiceImplementation;

    @Test
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
    public void shouldReturnProducts()
    {
        // then
        Assertions.assertTrue(productServiceImplementation.getAllProducts().size() > 0);
    }
}