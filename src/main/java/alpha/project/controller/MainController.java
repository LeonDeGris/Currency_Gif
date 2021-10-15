package alpha.project.controller;

import alpha.project.service.ExchangeRatesRetrieveService;
import alpha.project.service.GifRetrieveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
//@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final ExchangeRatesRetrieveService exchangeRatesService;
    private final GifRetrieveService gifService;

    @GetMapping("/gif")
    public ResponseEntity<String> getGif() {
        boolean isRich = exchangeRatesService.retrieveRatesAndCompare();
        log.info("Is Rich {}", isRich);
        return ResponseEntity.ok(gifService.retrieveGifUrl(isRich));
    }

}