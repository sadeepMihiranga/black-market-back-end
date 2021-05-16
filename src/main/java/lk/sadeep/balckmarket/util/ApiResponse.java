package lk.sadeep.balckmarket.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;
import java.util.Collections;

@JsonPropertyOrder({"statusCode","message","data","additionalParams"})
public class ApiResponse<T>
{
    private int statusCode;
    private String message;
    private T data;
    private Map<String, Object> additionalParams;

    @SuppressWarnings("unchecked")
    private ApiResponse(ApiResponseBuilder builder)
    {
        this.statusCode = builder.statusCode;
        this.message = builder.message;
        this.data = (T) builder.data;
        this.additionalParams = builder.additionalParams;
    }

    protected ApiResponse(){

    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Map<String, Object> getAdditionalParams() {
        return additionalParams;
    }

    public static class ApiResponseBuilder<T>
    {
        private int statusCode = HttpStatus.OK.value();
        private String message = "";
        private Map<String, Object> additionalParams = Collections.<String,Object>emptyMap();
        private T data;

        public ApiResponseBuilder<T> withData(T data)
        {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder<T> withMessage(String message)
        {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder<T> withHttpStatus(HttpStatus httpStatus)
        {
            this.message = httpStatus.getReasonPhrase();
            this.statusCode = httpStatus.value();
            return this;
        }

        public ApiResponseBuilder<T> withAdditionalParams(Map<String, Object> additionalParams)
        {
            this.additionalParams = additionalParams;
            return this;
        }

        public ResponseEntity<ApiResponse> build()
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(this));
        }
    }
}
