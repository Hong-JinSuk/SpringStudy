package hello.springmvc.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("uesrname = {}, age = {}", helloData.getUsername(), helloData.getAge());
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("uesrname = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "responseBody-V2";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    // @RequestBody 를 생략하면, @ModelAttribute 가 자동으로 들어오는 것이므로 요청 파라미터를 처리하게 된다.
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {

        log.info("uesrname = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "responseBody-V3";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    // @RequestBody 를 생략하면, @ModelAttribute 가 자동으로 들어오는 것이므로 요청 파라미터를 처리하게 된다.
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData helloData = httpEntity.getBody();
        log.info("uesrname = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "responseBody-V4";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    // @RequestBody 를 생략하면, @ModelAttribute 가 자동으로 들어오는 것이므로 요청 파라미터를 처리하게 된다.
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        log.info("uesrname = {}, age = {}", data.getUsername(), data.getAge());
        return data;
    }

}