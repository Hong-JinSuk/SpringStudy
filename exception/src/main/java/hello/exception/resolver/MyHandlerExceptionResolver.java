package hello.exception.resolver;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

// WebConfig 에서 extendHandlerExceptionResolvers 를 이용해서 등록한다.
@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400"); // 500 - > 400 으로 바꿀 것임.
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage()); // 500을 가로채서 400으로 변환
                return new ModelAndView(); // 빈 ModelAndView 를 반환하므로 오류 페이지를 뒤지게 되는 것이다.
            }
        } catch (IOException ioe){
            log.info("resolver ex ", ioe);
        }
        return null;
    }
}
