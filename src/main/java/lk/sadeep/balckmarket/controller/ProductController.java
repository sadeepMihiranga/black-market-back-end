package lk.sadeep.balckmarket.controller;

import lk.sadeep.balckmarket.dto.*;
import lk.sadeep.balckmarket.util.ApiResponse;
import lk.sadeep.balckmarket.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController
{
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    /**
     * @Method            :   getAllProducts
     * @Parameters        :   no parameter
     * @Description       :   return a response contains all products by calling the service
     * */
    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts()
    {
        return new ApiResponse.ApiResponseBuilder<>()
                .withData(productService.getAllProducts())
                .withHttpStatus(OK)
                .withMessage("Products fetched")
                .build();
    }

    /**
     * @Method            :   getProductPricesWithUnits
     * @Parameters        :   PriceTableRequest object
     * @Description       :   return a response contains products and prices for given range by calling the service
     * */
    @PostMapping("/calculate/prices")
    public ResponseEntity<ApiResponse> getProductPricesWithUnits(@RequestBody PriceTableRequest priceTableRequest)
    {
        return new ApiResponse.ApiResponseBuilder<>()
                .withData(productService.getProductPricesWithUnits(priceTableRequest))
                .withHttpStatus(OK)
                .withMessage("Prices calculated")
                .build();
    }

    /**
     * @Method            :   getProductPricesWithUnits
     * @Parameters        :   SingleProductPriceCalculateRequest object
     * @Description       :   return a response contains product price by calling the service
     * */
    @PostMapping("/calculate/price")
    public ResponseEntity<ApiResponse> getProductPricesWithUnits(@RequestBody SingleProductPriceCalculateRequest productPriceCalculateRequest)
    {
        return new ApiResponse.ApiResponseBuilder<>()
                .withData(productService.calculatePriceForSingleProduct(productPriceCalculateRequest))
                .withHttpStatus(OK)
                .withMessage("Price calculated")
                .build();
    }
}
