package kz.insoft.newsportal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "snippet")
    private String snippet;
    @JsonProperty(value = "full_text")
    private String fullText;
    @JsonProperty(value = "author")
    private String author;

    @JsonProperty(value = "created_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @JsonProperty(value = "updated_time")
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime updatedTime;
}
