package lk.sadeep.balckmarket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto
{
    private int statusCode;
    private String message;
    private Object response;
}
