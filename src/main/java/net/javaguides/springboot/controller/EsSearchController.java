package net.javaguides.springboot.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.dto.EsSearchRequest;
import net.javaguides.springboot.dto.EsSearchResponse;
import net.javaguides.springboot.service.EsSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Map;

@RestController
@RequestMapping("/api/query")
@RequiredArgsConstructor
public class EsSearchController {

    private final EsSearchService searchService;
    private static final Logger logger = LoggerFactory.getLogger(EsSearchController.class);

    @Operation(summary = "執行唯讀搜尋", description = "傳入索引名稱與 DSL 語法來獲取 Elasticsearch 資料")
    @PostMapping("/{index}")
    public EsSearchResponse search(@PathVariable String index,
            @RequestBody EsSearchRequest request) {
        return searchService.executeSearch(index, request);
    }


}
