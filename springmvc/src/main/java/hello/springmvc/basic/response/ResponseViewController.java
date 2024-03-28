package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
//                .addObject("data2", "oh!");

        return mav;
    }

    // @ResponseBody 를 실행시키 template 의 response/hello 경로의 data 에 hello~! 를 넣어주는 것이 아닌
    // view 를 찾지 않고 그냥 HTML 의 body 에 "response/hello" 를 넣어버린다.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello~!");
        return "response/hello";
    }

    // Spring 에서 Controller 의 이름으로 반환을 해주긴 하는데,,, 이렇게 하면 안좋겠지..?
    // 아마 같은 주소에 다른 방식의 반응이 필요할 수 있는데, 그 때, 컨트롤러의 이름이 같으면,, ㅎㅎ 오류날듯
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello~!");
    }
}
