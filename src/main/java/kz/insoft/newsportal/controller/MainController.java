package kz.insoft.newsportal.controller;

import kz.insoft.newsportal.client.NewsApi;
import kz.insoft.newsportal.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/news")
public class MainController {

    private final NewsApi newsApi;

    @Autowired
    public MainController(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    @GetMapping
    public ModelAndView getView() {
        ModelAndView modelAndView = new ModelAndView("main");
        List<NewsDTO> recentNews = newsApi.getRecentNews();
        modelAndView.addObject("news", recentNews);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getNewsView(@PathVariable(value = "id") Long newsId) {
        ModelAndView modelAndView = new ModelAndView("news_page");
        NewsDTO newsDTO = newsApi.getById(newsId);
        if (newsDTO != null) {
            modelAndView.addObject("news", newsDTO);
            return modelAndView;
        } else {
            modelAndView = new ModelAndView("error/not_found_error_page");
            return modelAndView;
        }
    }
}
