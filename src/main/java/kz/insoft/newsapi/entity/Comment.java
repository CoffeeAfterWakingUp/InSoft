package kz.insoft.newsapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@EqualsAndHashCode(exclude = {"news"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private News news;

    public Comment(String text, String author) {
        this.text = text;
        this.author = author;
        this.createdTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", createdTime=" + createdTime +
                ", news=" + news +
                '}';
    }
}
