package learning.job04.web;

import jakarta.validation.Valid;
import learning.job04.common.exception.BizException;
import learning.job04.web.dto.PingRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PingController {

    private static final String BIZ_TRIGGER = "biz";
    private static final String CRASH_TRIGGER = "crash";
    private static final String BUSINESS_ERROR = "BUSINESS_ERROR";

    @GetMapping("/api/ping")
    public Map<String, Boolean> ping() {
        return Map.of("ok", true);
    }

    @PostMapping("/api/ping")
    public Map<String, Object> ping(@Valid @RequestBody PingRequestDTO requestDTO) {
        if (BIZ_TRIGGER.equals(requestDTO.name())) {
            throw new BizException(BUSINESS_ERROR, "触发了业务异常", HttpStatus.BAD_REQUEST);
        }
        if (CRASH_TRIGGER.equals(requestDTO.name())) {
            throw new IllegalStateException("触发了未捕获异常");
        }
        return Map.of(
                "ok", true,
                "name", requestDTO.name(),
                "message", requestDTO.message()
        );
    }
}
