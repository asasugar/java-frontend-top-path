package learning.job04.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PingRequestDTO(
        @NotBlank(message = "name 不能为空")
        @Size(max = 20, message = "name 长度不能超过 20")
        String name,
        @NotBlank(message = "message 不能为空")
        @Size(max = 100, message = "message 长度不能超过 100")
        String message
) {
}
