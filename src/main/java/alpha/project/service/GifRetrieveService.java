package alpha.project.service;

import alpha.project.feign.FeignGiphyGifClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GifRetrieveService {

    @Value("${giphy.rich}")
    private String richTag;
    @Value("${giphy.broke}")
    private String brokeTag;
    @Value("${giphy.apikey}")
    private String apikey;

    private final String urlWildcard = "<iframe src=\"https://giphy.com/embed/%s\" width=\"480\" height=\"270\" frameBorder=\"0\" class=\"giphy-embed\" allowFullScreen>";

    private final FeignGiphyGifClient feignClient;

    public String retrieveGifUrl(boolean isRich) {
        String uuid = feignClient.getRandomGif(apikey, isRich ? richTag : brokeTag)
                .getData()
                .getUuidGifCode();
        return String.format(urlWildcard, uuid);
    }
}
