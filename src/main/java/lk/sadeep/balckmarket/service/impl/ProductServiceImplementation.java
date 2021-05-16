package lk.sadeep.balckmarket.service.impl;

import lk.sadeep.balckmarket.dto.*;
import lk.sadeep.balckmarket.entity.Product;
import lk.sadeep.balckmarket.engine.PriceEngine;
import lk.sadeep.balckmarket.service.ProductService;
import lk.sadeep.balckmarket.repository.ProductRepository;
import lk.sadeep.balckmarket.exception.DataNotFoundException;
import lk.sadeep.balckmarket.exception.BadApiRequestException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

@Service
public class ProductServiceImplementation implements ProductService
{
    private final ProductRepository productRepository;
    private final PriceEngine priceEngine;

    @Autowired
    public ProductServiceImplementation(ProductRepository productRepository, PriceEngine priceEngine)
    {
        this.productRepository = productRepository;
        this.priceEngine = priceEngine;
    }

    /**
     * @Method            :   getAllProducts
     * @Parameters        :   no parameter
     * @Description       :   returns list of all products by calling a query method
     * */
    @Override
    public List<ProductDto> getAllProducts()
    {
        List<ProductDto> productDtoList = new ArrayList<>();

        productRepository.findAll().forEach(product -> productDtoList.add(getProductDTOByProduct(product)));

        return productDtoList;
    }

    /**
     * @Method            :   getAllProducts
     * @Parameters        :   Integer productId
     * @Description       :   returns a product by calling a query method if not found throw a DataNotFoundException
     * */
    @Override
    public ProductDto getAProduct(Integer productId)
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> {
            throw new DataNotFoundException("Product not found");
        });

        return getProductDTOByProduct(product);
    }

    /**
     * @Method            :   getProductPricesWithUnits
     * @Parameters        :   PriceTableRequest object
     * @Description       :   returns list of prices for the given product under given range
     *                        validates inputs if not passed throw exceptions
     * */
    @Override
    public PriceTableResponse getProductPricesWithUnits(PriceTableRequest priceTableRequest)
    {
        int noOfUnitsToCalculate;
        int productId;

        // if requested object is null throw an exception
        if(priceTableRequest == null)
            throw new BadApiRequestException("Requested object cannot be null");

        productId = priceTableRequest.getProductId();
        noOfUnitsToCalculate = priceTableRequest.getNoOfUnits();

        // if requested parameters no as expected throw an exception
        if(noOfUnitsToCalculate < 1)
            throw new BadApiRequestException("No of units should be larger than 0");

        // if requested parameters no as expected throw an exception
        if(productId <= 0)
            throw new BadApiRequestException("Product id should be larger than 0");

        var priceTableResponse = new PriceTableResponse();
        List<PriceTableData> prices = new ArrayList<>();

        try
        {
            // fetching product details from DB using declared method
            ProductDto productDto = getAProduct(productId);

            priceTableResponse.setProduct(productDto);

            // calculate and create list of optimized prices for each no of units by calling the price engine
            for(int i = 1; i <= noOfUnitsToCalculate; i++)
            {
                prices.add(new PriceTableData(i, priceEngine.calculateThePricing(productDto, i, 0)));
            }

            priceTableResponse.setPrices(prices);
        }
        catch(DataNotFoundException e)
        {
            throw new DataNotFoundException("Product not found");
        }
        finally
        {
            priceTableRequest = null;
            prices = null;
        }

        return priceTableResponse;
    }

    /**
     * @Method            :   calculatePriceForSingleProduct
     * @Parameters        :   SingleProductPriceCalculateRequest object
     * @Description       :   returns calculated price for a product product details
     *                        validates inputs if not passed throw exceptions
     * */
    @Override
    public SingleProductPriceCalculateResponse calculatePriceForSingleProduct(SingleProductPriceCalculateRequest productPriceCalculateRequest)
    {
        // if requested object is null throw an exception
        if(productPriceCalculateRequest == null)
            throw new BadApiRequestException("Requested object cannot be null");

        // extract values to local variables from the request
        int noOfSingleUnits = productPriceCalculateRequest.getNoOfSingleUnits();
        int noOfCartons = productPriceCalculateRequest.getNoOfCartons();

        // if requested parameters no as expected throw an exception
        if(noOfSingleUnits < 1 && noOfCartons < 1)
            throw new BadApiRequestException("Either single units or cartons should be larger than 0");

        var priceCalculateResponse = new SingleProductPriceCalculateResponse();

        try
        {
            // fetching product details from DB using declared method
            ProductDto productDto = getAProduct(productPriceCalculateRequest.getProductId());

            // creating response by merging product and order price
            priceCalculateResponse.setProduct(productDto);
            priceCalculateResponse.setPrice(priceEngine.calculateThePricing(productDto, noOfSingleUnits, noOfCartons));
        }
        catch(DataNotFoundException e)
        {
            throw new DataNotFoundException("Product not found");
        }
        finally
        {
            productPriceCalculateRequest = null;
        }

        return priceCalculateResponse;
    }

    /**
     * @Method            :   getProductDTOByProduct
     * @Parameters        :   Product object
     * @Description       :   create and returns ProductDto object using Product object
     * */
    private ProductDto getProductDTOByProduct(Product product)
    {
        return new ProductDto(
                product.getId(),
                product.getProductName(),
                product.getCartonSize(),
                product.getCartonPrice(),
                product.getImageUrl(),
                product.getPriceAddedPercentage(),
                product.getPriceDiscountPercentage(),
                product.getCartonDiscountStartingQuantity()
        );
    }
}
