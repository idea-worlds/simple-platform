package dev.simpleframework.platform.integration.spring;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * spring 全局异常处理
 */
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
@RequiredArgsConstructor
public class SpringErrorController implements ErrorController {
    private final ErrorAttributes errorAttributes;

    @RequestMapping
    public void error(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        WebRequest webRequest = new ServletWebRequest(request, response);
        Throwable error = this.errorAttributes.getError(webRequest);
        if (error != null) {
            throw error;
        }
    }

}
