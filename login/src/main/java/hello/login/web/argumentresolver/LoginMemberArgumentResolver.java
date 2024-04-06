package hello.login.web.argumentresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");

        // 로그인에 annotation 이 붙어 있는가??
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        // 붙어 있는 것이 Member Type 이니??
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

        // @Login {} 에서 {} 이 있고 그게 Member 이면 true 를 리턴해서 아래 resolveArgument 실행
        return hasMemberType && hasLoginAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        log.info("resolveArgument 실행");

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        // 세션이 있으면, 세션 상수에서 세션을 받아온다. 이 반환된 멤버가 호출된 곳으로 간다.
        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
