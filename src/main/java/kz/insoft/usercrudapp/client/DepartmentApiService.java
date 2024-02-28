package kz.insoft.usercrudapp.client;

import kz.insoft.usercrudapp.dto.DepartmentDTO;
import kz.insoft.usercrudapp.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DepartmentApiService {

    private RestTemplate restTemplate;

    @Autowired
    public DepartmentApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String DEPARTMENTS_API_URL = "http://localhost:8080/api/v1/departments";

    public void createDepartment() {
        DepartmentDTO dto = DepartmentDTO.builder()
                .name("New Department")
                .build();
        HttpEntity<DepartmentDTO> httpEntity = new HttpEntity<>(dto);
        ResponseEntity<ResponseDTO<DepartmentDTO>> responseEntity = restTemplate.exchange(DEPARTMENTS_API_URL,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<ResponseDTO<DepartmentDTO>>() {
                });
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ResponseDTO<DepartmentDTO> responseDTO = responseEntity.getBody();
            log.info("Created Department: {}", responseDTO.getBody());
        }
    }

    public void updateDepartment() {
        Long id = 18L;
        DepartmentDTO dto = DepartmentDTO.builder()
                .id(id)
                .name("Updated Department")
                .build();
        String url = DEPARTMENTS_API_URL + "/" + id;

        HttpEntity<DepartmentDTO> httpEntity = new HttpEntity<>(dto);

        ResponseEntity<ResponseDTO<DepartmentDTO>> responseEntity = restTemplate.exchange(url,
                HttpMethod.PUT,
                httpEntity,
                new ParameterizedTypeReference<ResponseDTO<DepartmentDTO>>() {
                });
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ResponseDTO<DepartmentDTO> responseDTO = responseEntity.getBody();
            log.info("Updated Department: {}", responseDTO.getBody());
        }

    }



}
