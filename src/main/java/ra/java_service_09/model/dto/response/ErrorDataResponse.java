package ra.java_service_09.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDataResponse <T>{
    private String message;
    private T errors;
    private HttpStatus status;
}