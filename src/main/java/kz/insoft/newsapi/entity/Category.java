package kz.insoft.newsapi.entity;

import jakarta.persistence.*;
import kz.insoft.newsapi.enums.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@EqualsAndHashCode(exclude = {"news"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(value = EnumType.STRING)
    private Categories category;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<News> news = new HashSet<>();

    public Category(Categories category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category=" + category +
                '}';
    }
}
