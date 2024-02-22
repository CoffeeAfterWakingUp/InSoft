package kz.insoft.usercrudapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

    private HttpStatus httpStatus;
    private T body;
    private int status;
    private LocalDateTime timestamp;

    public ResponseDTO(HttpStatus httpStatus, T body, int status) {
        this.httpStatus = httpStatus;
        this.body = body;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
