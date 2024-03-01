package kz.insoft.usercrudapp.client;

import kz.insoft.usercrudapp.dto.ResponseDTO;
import kz.insoft.usercrudapp.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserApiService {

    private RestTemplate restTemplate;

    @Autowired
    public UserApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getAllUsers() {
        UserDTO[] users = restTemplate.getForObject("http://localhost:8080/api/v1/users", UserDTO[].class);
        Arrays.stream(users).forEach(u -> log.info("User: {}", u));
    }

    public void getById(Long id) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", id);
       // ResponseDTO responseDTO = restTemplate.getForObject("http://localhost:8080/api/v1/users/{id}", ResponseDTO.class, params);
        ResponseEntity<ResponseDTO<UserDTO>> responseEntity = restTemplate.exchange("http://localhost:8080/api/v1/users/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseDTO<UserDTO>>() {},
                id);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ResponseDTO<UserDTO> responseDTO = responseEntity.getBody();
            UserDTO userDto = responseDTO.getBody();
            log.info("By id {}: {}", id, userDto);
        }

    }

    public void getByEmail(String email) {
     //   UserDTO userDTO = restTemplate.getForObject("http://localhost:8080/api/v1/users/email/{email}", UserDTO.class, email);
        try {
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.put("MY-API-TOKEN", Arrays.asList("MySecret123"));
            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<UserDTO> responseEntity = restTemplate.exchange("http://localhost:8080/api/v1/users/email/{email}",
                    HttpMethod.GET, httpEntity, UserDTO.class, email);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                if (responseEntity.getBody() != null) {
                    log.info("By email {}: {}", email, responseEntity.getBody());
                }
            } else {
                log.error("Error: {}", responseEntity.getStatusCodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById() {
        //restTemplate.delete("http://localhost:8080/api/v1/users/{id}", 23);

        ResponseEntity<Void> exchange = restTemplate.exchange("http://localhost:8080/api/v1/users/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                24L);
        log.info("User is deleted");
    }


}
