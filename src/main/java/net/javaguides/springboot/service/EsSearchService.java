package net.javaguides.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EsSearchService {

    private final RestClient restClient;

    public Object executeSearch(String index, Map<String, Object> payload) {
        return restClient.post().uri("/{index}/_search", index).body(payload).retrieve()
                .body(Object.class);
    }
}
