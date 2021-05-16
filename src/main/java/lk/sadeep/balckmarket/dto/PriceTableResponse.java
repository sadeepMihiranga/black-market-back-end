package lk.sadeep.balckmarket.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceTableResponse
{
    private ProductDto product;
    private List<PriceTableData> prices;
}
