package alpha.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Модель для работы с курсами валют от openexchangerates.org
 * Пример json`а:
 * https://openexchangerates.org/api/historical/2020-12-21.json?app_id=3b8c120bb3624e3eb59a8002fdc0c03e
 */
@Data
@NoArgsConstructor
public class ExchangeRatesEntity {

    @JsonProperty(value = "timestamp", required = true)
    private Long timestamp;

    @JsonProperty(value = "base", required = true)
    private String baseCurrency;

    @JsonProperty(value = "rates", required = true)
    private Map<String, Double> rates;

}
