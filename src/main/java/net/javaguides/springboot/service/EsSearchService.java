package net.javaguides.springboot.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.controller.EsSearchController;
import net.javaguides.springboot.dto.EsSearchRequest;
import net.javaguides.springboot.dto.EsSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EsSearchService {

    private final RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(EsSearchService.class);


    public EsSearchResponse executeSearch(String index, EsSearchRequest request) {
        Map<String, Object> payload = getElasticSearchPayload(request);
        logger.info("Executing ES Search on index: {}, payload: {}", index, payload);
        return restClient.post().uri("/{index}/_search", index).body(payload).retrieve()
                .body(EsSearchResponse.class);
    }


    public Map<String, Object> getElasticSearchPayload(EsSearchRequest request) {
        Map<String, Object> payload = new HashMap<>();

        payload.put("size", request.getSize());
        payload.put("from", request.getFrom());
        payload.put("query", request.getQuery());

        if (!request.getSort().isEmpty())
            payload.put("sort", request.getSort());
        if (!request.getIncludes().isEmpty())
            payload.put("_source", request.getIncludes());
        if (!request.getAggs().isEmpty())
            payload.put("aggs", request.getAggs());

        return payload;
    }
}
