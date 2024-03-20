package kz.insoft.newsapi.controller;

import kz.insoft.newsapi.controller.adapter.NewsAdapter;
import kz.insoft.newsapi.dto.NewsDTO;
import kz.insoft.newsapi.entity.News;
import kz.insoft.newsapi.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

    private final NewsAdapter newsAdapter;

    @Autowired
    public NewsController(NewsAdapter newsAdapter) {
        this.newsAdapter = newsAdapter;
    }

    @GetMapping("/recent")
    public ResponseEntity<List<NewsDTO>> getRecentViews() {
        List<NewsDTO> recentViews = newsAdapter.getRecentViews();
        return ResponseEntity.ok(recentViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable(value = "id") Long id) {
        NewsDTO newsDto = newsAdapter.getById(id);
        return ResponseEntity.ok(newsDto);
    }

    @PostMapping
    public void createNews(@RequestBody NewsDTO newsDTO) {
        newsAdapter.create(newsDTO);
    }




}
