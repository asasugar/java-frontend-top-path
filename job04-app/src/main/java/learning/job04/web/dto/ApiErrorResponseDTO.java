package learning.job04.web.dto;

import java.util.List;

public record ApiErrorResponseDTO(
        String code,
        String message,
        List<ApiFieldErrorDTO> errors
) {
}
