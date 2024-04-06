package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    // 로그인 정보가 없어도 허용해야되는 것들
    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    // init 과 destroy 는 default 로 되어있기 때문에 굳이 구현할 필요 없다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);

            if (isLoginCheckPath((requestURI))) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    // 로그인 페이지로 redirect
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return; // 미인증 사용자면 더이상 진행X
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /**
     * 화이트리스트의 경우 인증 체크X
     */
    private boolean isLoginCheckPath(String requestURI) {
        // 단순히 패턴이 매칭되는지 확인, 화이트리스트에 없는 것은 false 가 되면서 접근이 안되게 해준다.
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }

}
