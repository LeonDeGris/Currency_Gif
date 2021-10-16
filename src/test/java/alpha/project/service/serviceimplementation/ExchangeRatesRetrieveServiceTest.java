package alpha.project.service.serviceimplementation;

import alpha.project.feign.FeignOpenExchangeRatesClient;
import alpha.project.model.ExchangeRatesEntity;
import alpha.project.service.ExchangeRatesRetrieveService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest


public class ExchangeRatesRetrieveServiceTest {
    @Autowired
    private ExchangeRatesRetrieveService exchangeRatesRetrieveService;
    @MockBean
    private FeignOpenExchangeRatesClient feignOpenExchangeRatesClient;

    @Test
    public void ExchangeRates() throws Exception {
        //prepare
        ExchangeRatesEntity exchangeRatesEntity = new ExchangeRatesEntity();
        Map<String, Double> setRates = new HashMap<>();
        setRates.put("RUB", 1d);
        exchangeRatesEntity.setRates(setRates);
        Mockito.when(feignOpenExchangeRatesClient.getHistoricalRates(anyString(), anyString()))
                .thenReturn(exchangeRatesEntity);

        Mockito.when(feignOpenExchangeRatesClient.getLatestRates(anyString()))
                .thenReturn(exchangeRatesEntity);
        //when
        boolean result = exchangeRatesRetrieveService.retrieveRatesAndCompare();
        //then
        Assert.assertFalse(result);
    }


}


