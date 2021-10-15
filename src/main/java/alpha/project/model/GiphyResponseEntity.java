package alpha.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GiphyResponseEntity {

    private GiphyData data;

    private GiphyMeta meta;

    @Data
    @NoArgsConstructor
    public static class GiphyData {

        @JsonProperty("id")
        private String uuidGifCode;
    }

    @Data
    @NoArgsConstructor
    public static class GiphyMeta {

        @JsonProperty("status")
        private Integer statusCode;

        @JsonProperty("msg")
        private String message;
    }

}
