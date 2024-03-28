package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass()); // 내 클래스 지정

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace(" trace my log = Spring");
        log.trace("trace log = {}", name);

        log.debug("trace log = {}", name);
        log.info(" info log ={}", name);
        log.warn("trace log = {}", name);
        log.error("trace log = {}", name);

        return "ok"; // view 가 return 되는 것.
    }
}
