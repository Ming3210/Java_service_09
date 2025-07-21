package ra.java_service_09.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    @NotBlank (message = "Title is required")
    private String title;

    @NotBlank (message = "Description is required")
    private String description;

    @NotNull (message = "Release date is required")
    private String releaseDate;

    private String poster;

    private MultipartFile posterFile;
}
