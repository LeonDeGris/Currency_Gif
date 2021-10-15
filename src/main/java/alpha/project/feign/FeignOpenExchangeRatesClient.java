package alpha.project.feign;

import alpha.project.model.ExchangeRatesEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OERClient", url = "${exchanges.url}")
public interface FeignOpenExchangeRatesClient {

    @GetMapping("/latest.json")
    ExchangeRatesEntity getLatestRates(@RequestParam("app_id") String appId);

    @GetMapping("/historical/{date}.json")
    ExchangeRatesEntity getHistoricalRates(@PathVariable String date, @RequestParam("app_id") String appId);
}
