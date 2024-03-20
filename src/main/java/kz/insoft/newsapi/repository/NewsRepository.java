package kz.insoft.newsapi.repository;

import kz.insoft.newsapi.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select n from News n order by n.createdTime DESC")
    List<News> getRecentViews();
}
