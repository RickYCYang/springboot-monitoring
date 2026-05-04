package net.javaguides.springboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@Schema(description = "Elasticsearch 搜尋結果回傳結構")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EsSearchResponse {

    private int took;

    @JsonProperty("timed_out")
    private boolean timedOut;

    // @JsonProperty("_shards")
    // private Shards shards;

    private HitsMetadata hits;

    // @Data
    // public static class Shards {
    // private int total;
    // private int successful;
    // private int skipped;
    // private int failed;
    // }

    @Data
    public static class HitsMetadata {
        private Total total;

        // @JsonProperty("max_score")
        // private Double maxScore;

        private List<Hit> hits;
    }

    @Data
    public static class Total {
        private long value;
        private String relation;
    }

    @Data
    // @JsonIgnoreProperties(ignoreUnknown = true) // 非常重要！忽略你不關心的欄位
    public static class Hit {
        // @JsonProperty("_index")
        // private String index;

        // @JsonProperty("_id")
        // private String id;

        // @JsonProperty("_score")
        // private Double score;

        @JsonProperty("_source")
        @Schema(description = "實際的日誌內容或文件資料")
        private LogEntry source;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true) // 非常重要！忽略你不關心的欄位
    public static class LogEntry {
        @JsonProperty("@timestamp")
        private String timestamp;
        private String message;
    }
}
