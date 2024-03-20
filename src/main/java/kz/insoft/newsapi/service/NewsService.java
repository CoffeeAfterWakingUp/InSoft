package kz.insoft.newsapi.service;

import kz.insoft.newsapi.entity.News;

import java.util.List;

public interface NewsService {

    List<News> getRecentNews();

    News getById(Long id);

    void create(News news);
}
