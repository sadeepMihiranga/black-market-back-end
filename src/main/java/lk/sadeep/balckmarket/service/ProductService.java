package lk.sadeep.balckmarket.service;

import lk.sadeep.balckmarket.dto.*;

import java.util.List;

public interface ProductService
{
    List<ProductDto> getAllProducts();

    ProductDto getAProduct(Integer productId);

    PriceTableResponse getProductPricesWithUnits(PriceTableRequest priceTableRequest);

    SingleProductPriceCalculateResponse calculatePriceForSingleProduct(SingleProductPriceCalculateRequest productPriceCalculateRequest);
}
