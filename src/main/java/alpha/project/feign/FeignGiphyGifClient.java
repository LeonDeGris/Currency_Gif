package alpha.project.feign;

import alpha.project.model.GiphyResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Клиент для обращения к сервисам Api Giphy
 */
@FeignClient(name = "giphyClient", url = "${giphy.url}")
public interface FeignGiphyGifClient {

    /**
     * Получить ответ - случайную гифку от сервиса
     * @param apiKey api-ключ клиента (из properties) для вс
     * @param tag тэг для
     * @return ответ от api c url-ом на гифку
     */
    @GetMapping("/random")
    GiphyResponseEntity getRandomGif(@RequestParam("api_key") String apiKey,
                                     @RequestParam("tag") String tag);

}