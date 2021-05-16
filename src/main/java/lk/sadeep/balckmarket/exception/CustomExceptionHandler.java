package lk.sadeep.balckmarket.exception;

import lk.sadeep.balckmarket.util.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler
{
    /**
     * @Method            :   handleDataNotFoundException
     * @Parameters        :   DataNotFoundException object
     * @Description       :   handle DataNotFoundException custom exception
     * */
    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<ApiResponse> handleDataNotFoundException(DataNotFoundException exception)
    {
        return createExceptionPayloadBy(exception.getMessage(), NOT_FOUND);
    }

    /**
     * @Method            :   handleBadApiRequestException
     * @Parameters        :   BadApiRequestException object
     * @Description       :   handle BadApiRequestException custom exception
     * */
    @ExceptionHandler(value = {BadApiRequestException.class})
    public ResponseEntity<ApiResponse> handleBadApiRequestException(BadApiRequestException exception)
    {
        return createExceptionPayloadBy(exception.getMessage(), BAD_REQUEST);
    }

    /**
     * @Method            :   createExceptionPayloadBy
     * @Parameters        :   String message, HttpStatus httpStatus
     * @Description       :   create and return exception response using ApiResponseBuilder
     * */
    private static ResponseEntity<ApiResponse> createExceptionPayloadBy(String message, HttpStatus httpStatus)
    {
        return new ApiResponse.ApiResponseBuilder<>()
                .withData(null)
                .withHttpStatus(httpStatus)
                .withMessage(message)
                .build();
    }
}
