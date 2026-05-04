package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Schema(description = "Elasticsearch Search Request Payload Structure")
public class EsSearchRequest {
    @Schema(description = "The number of hits to return", example = "10", defaultValue = "10")
    private Integer size = 10;

    @Schema(description = "The offset from the first result you want to fetch", example = "0",
            defaultValue = "0")
    private Integer from = 0;

    @Schema(description = "Specifies which fields in the _source should be returned",
            example = "[\"@timestamp\", \"message\"]")
    private List<String> includes = new ArrayList<>();

    @Schema(description = "The query element in the search request body, using Elasticsearch Query DSL",
            example = "{\"match\": {\"message\": \"error\"}}", defaultValue = "{\"match_all\": {}}")
    private Map<String, Object> query = Map.of("match_all", Map.of());

    @Schema(description = "Sorting criteria, allowing multiple fields and orders",
            example = "[{\"@timestamp\": {\"order\": \"desc\"}}]")
    private List<Map<String, Object>> sort = new ArrayList<>();

    @Schema(description = "Aggregation definitions to group and analyze data",
            example = "{\"status_counts\": {\"terms\": {\"field\": \"status_code\"}}}")
    private Map<String, Object> aggs = new HashMap<>();
}
