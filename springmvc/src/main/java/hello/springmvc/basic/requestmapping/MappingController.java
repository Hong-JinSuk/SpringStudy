package hello.springmvc.basic.requestmapping;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    // spring 3.0 이상부터는 /이 있나 없나의 차이가 있다... 그래서 둘다 되게 해줬다!
    @RequestMapping(value = {"/hello-basic", "/hello-basic/"}, method = RequestMethod.GET)
    public String helloBasic() {
        log.info("basic");
        return "hello-basic!!";
    }


    @RequestMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "mappingGetV2!!";
    }

    /**
     * @param userId
     * @param orderId 위 두개의 파라미터를 @PathVariable로 받아와서 변수에 매핑시켜준다.
     * @return
     */
    @RequestMapping("/mapping/users/{userId}/orders/{orderId}")
    // PathVariable("userId") String data 에서 아래와 같이 변수명을 맞추면 중간과정 생략가능
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId = {}, orderId = {}", userId, orderId);
        return "mappingPath";
    }

    // URL 경로 뿐만 아니라 파라미터 조건까지 줘야함. mapping-param?mode=debug 으로 해줘야만 호출이 된다.
    @RequestMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "Debug Mode";
    }

    // postman 에서 Header 에서 mode, debug 를 추가해줘야 한다.
    @RequestMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "mappingHeader!!";
    }

    // json 타입만 받아온다.
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "mapping-consumes";
    }

    // Accept */* 과 같이 json 과 관련된 거여야 가능하다.
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "mapping-produce!!";
    }
}
