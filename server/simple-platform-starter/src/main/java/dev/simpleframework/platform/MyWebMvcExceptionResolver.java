package dev.simpleframework.platform;

import dev.simpleframework.core.CommonResponse;
import dev.simpleframework.token.exception.SimpleTokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * spring 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class MyWebMvcExceptionResolver {

    @ExceptionHandler(Throwable.class)
    public CommonResponse<String> handler(HttpServletRequest request, HttpServletResponse response,
                                          Throwable exception) {
        log.error(this.errorMsg(request, exception), exception);
        return CommonResponse.failure("500", "Unknown");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<String> handler(HttpServletRequest request, HttpServletResponse response,
                                          MethodArgumentNotValidException exception) {
        log.warn(this.errorMsg(request, exception), exception);
        String msg = exception.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return CommonResponse.failure("400", msg);
    }

    @ExceptionHandler(SimpleTokenException.class)
    public CommonResponse<String> handler(HttpServletRequest request, HttpServletResponse response,
                                          SimpleTokenException exception) {
        log.error(this.errorMsg(request, exception), exception);
        return CommonResponse.failure("500", exception.getMessage());
    }

    private String errorMsg(HttpServletRequest request, Throwable exception) {
        String uri = (String) request.getAttribute("jakarta.servlet.error.request_uri");
        if (uri == null) {
            uri = request.getRequestURI();
        }
        if (uri == null) {
            uri = " ";
        }
        return "[" + uri + "] " + exception.getMessage();
    }

}
