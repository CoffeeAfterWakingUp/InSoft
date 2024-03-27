package kz.insoft.newsportal.client;

import kz.insoft.newsportal.dto.NewsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NewsApi {

    private final RestTemplate restTemplate;

    @Value("${newsApi.recentNews}")
    private String recentNewsUrl;
    @Value("${newsApi.getById}")
    private String getByIdUrl;


    @Autowired
    public NewsApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<NewsDTO> getRecentNews() {
        try {
            ResponseEntity<NewsDTO[]> responseEntity = restTemplate.exchange(recentNewsUrl, HttpMethod.GET, null, NewsDTO[].class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                if (responseEntity.getBody() != null) {
                    return Arrays.stream(responseEntity.getBody()).collect(Collectors.toList());
                }
            }
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public NewsDTO getById(Long newsId) {
        try {
            ResponseEntity<NewsDTO> responseEntity = restTemplate.exchange(getByIdUrl, HttpMethod.GET, null, NewsDTO.class, newsId);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
