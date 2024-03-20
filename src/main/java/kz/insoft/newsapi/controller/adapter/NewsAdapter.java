package kz.insoft.newsapi.controller.adapter;

import kz.insoft.newsapi.dto.NewsDTO;
import kz.insoft.newsapi.entity.News;
import kz.insoft.newsapi.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewsAdapter {

    private final NewsService newsService;

    @Autowired
    public NewsAdapter(NewsService newsService) {
        this.newsService = newsService;
    }

    public List<NewsDTO> getRecentViews() {
        List<News> recentNews = newsService.getRecentNews();
        return recentNews.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public NewsDTO getById(Long id) {
        News news = newsService.getById(id);
        return toDto(news);
    }

    public void create(NewsDTO newsDTO) {
        newsService.create(toEntity(newsDTO));
    }

    private NewsDTO toDto(News news) {
        return NewsDTO.builder()
                .title(news.getTitle())
                .author(news.getAuthor())
                .fullText(news.getFullText())
                .snippet(news.getSnippet())
                .createdTime(news.getCreatedTime())
                .updatedTime(news.getUpdatedTime())
                .build();
    }

    private News toEntity(NewsDTO dto) {
        return News.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .snippet(dto.getSnippet())
                .fullText(dto.getFullText())
                .createdTime(dto.getCreatedTime())
                .build();
    }

}
