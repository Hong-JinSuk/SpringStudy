package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

// requestParamV3 이 가독성, 실용성 둘다 챙긴느낌

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username = {}, age = {}", memberName, memberAge);
        return "param-v2";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3( // 이 정도가 제일 적당한 것 같음.
                                  @RequestParam String username,
                                  @RequestParam int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "param-v3";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "param-v4";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            // required = false 라고 하면, 이 데이터가 없어도 null 처리하고 넘길 수 있다.
            // 주소값에 /request-param-required?username 와 같이 들어오면 username="" 처리해준다.
            @RequestParam(required = false) String username, // 없으면 null 처리
            @RequestParam(required = false) Integer age // int 에는 null 이 안된다. 이렇게 하고싶으면 Integer로 해야한다.
    ) {
        log.info("username = {}, age = {}", username, age);
        return "param-required";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            // default 값이 들어가면, required 가 의미없다. 어차피 defval이 들어가기 때문이다.
            // 빈문자열도 defaultValue로 처리해준다.
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "param-required";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            // 받아올 인자가 없으면 null 처리 해버린다.
            @RequestParam Map<String, Object> paramMap
    ) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "param-map";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(
//            @RequestParam String username,
//            @RequestParam int age
            // param을 username으로 받아오면 알아서 setUsername()이라는 Setter에 넣어준다.
            @ModelAttribute HelloData helloData
    ) {
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "model-attribute-v1";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    // 스프링은 String, int, Integer 와 같은 단순한 타입이 아니면 ModelAttribute 로 자동 지정해준다.
    public String modelAttributeV2(HelloData helloData) {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "model-attribute-v2";
    }
}
