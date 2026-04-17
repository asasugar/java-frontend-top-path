package learning.job04.web;

import learning.job04.common.exception.BizException;
import learning.job04.web.dto.ApiErrorResponseDTO;
import learning.job04.web.dto.ApiFieldErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    private static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        List<ApiFieldErrorDTO> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toApiFieldErrorDTO)
                .toList();

        ApiErrorResponseDTO response = new ApiErrorResponseDTO(
                VALIDATION_ERROR,
                "请求参数校验失败",
                errors
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleBizException(BizException exception) {
        ApiErrorResponseDTO response = new ApiErrorResponseDTO(
                exception.getCode(),
                exception.getMessage(),
                List.of()
        );
        return ResponseEntity.status(exception.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDTO> handleException(Exception exception) {
        ApiErrorResponseDTO response = new ApiErrorResponseDTO(
                INTERNAL_SERVER_ERROR,
                "服务器开小差了",
                List.of()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private ApiFieldErrorDTO toApiFieldErrorDTO(FieldError fieldError) {
        return new ApiFieldErrorDTO(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
