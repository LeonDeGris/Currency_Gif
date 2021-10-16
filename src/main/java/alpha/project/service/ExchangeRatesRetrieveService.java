package alpha.project.service;

import alpha.project.feign.FeignOpenExchangeRatesClient;
import alpha.project.model.ExchangeRatesEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class ExchangeRatesRetrieveService {

    private final FeignOpenExchangeRatesClient openExchangeRatesClient;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");;

    @Value("${exchanges.apikey}")
    private String appId;

    @Value("${exchanges.currency}")
    private String base;

    public ExchangeRatesRetrieveService(FeignOpenExchangeRatesClient openExchangeRatesClient) {
        this.openExchangeRatesClient = openExchangeRatesClient;
    }


    public boolean retrieveRatesAndCompare() {
        String lastDay = formatter.format(LocalDate.now().minus(1, ChronoUnit.DAYS));
        Double currentRates = openExchangeRatesClient.getLatestRates(this.appId)
                .getRates().get(base);
        ExchangeRatesEntity exchangeRatesEntity = openExchangeRatesClient.getHistoricalRates(lastDay, appId);
        Double previousRates = exchangeRatesEntity.getRates().get(base);
        log.info("RetrieveRatesAndCompare {} {}", currentRates, previousRates);
        return Double.compare(currentRates, previousRates) == 1;
    }

}
