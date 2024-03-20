package kz.insoft.newsapi.service.impl;

import kz.insoft.newsapi.entity.News;
import kz.insoft.newsapi.repository.NewsRepository;
import kz.insoft.newsapi.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> getRecentNews() {
        return newsRepository.getRecentViews();
    }

    @Override
    public News getById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public void create(News news) {
        newsRepository.save(news);
    }
}
