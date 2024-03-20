package kz.insoft.newsapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
@EqualsAndHashCode(exclude = {"categories", "comments"})
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "snippet", nullable = false)
    private String snippet;

    @Column(name = "full_text", nullable = false, columnDefinition = "TEXT")
    private String fullText;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "news_category",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public News(String title, String snippet, String fullText, String author) {
        this.title = title;
        this.snippet = snippet;
        this.fullText = fullText;
        this.author = author;
        this.createdTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", snippet='" + snippet + '\'' +
                ", fullText='" + fullText + '\'' +
                ", author='" + author + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
