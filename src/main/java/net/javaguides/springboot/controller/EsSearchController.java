package net.javaguides.springboot.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.service.EsSearchService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/query")
@RequiredArgsConstructor
public class EsSearchController {

    private final EsSearchService searchService;

    @PostMapping("/{index}")
    public Object search(@PathVariable String index, @RequestBody Map<String, Object> payload) {
        return searchService.executeSearch(index, payload);
    }
}
