package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final 이 붙은 애들한테 생성자를 만들어준다.
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items"; // 이 위치에 view 를 넣는다.
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // 여기서 Http 의 method 가 get 이냐 post 냐에 따라서 다른 함수가 작동한다.
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //    @PostMapping("/add")
    public String addItemV1(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model
    ) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
        // ModelAttribute 가 요청 파라미너를 받아온다.
//        model.addAttribute("item", item);

        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model) {
        itemRepository.save(item);
        // ModelAttribute 가 받아오는게 Item 이므로 item을 받아오는 것이 된다. 이 때, 자동적으로 item 인 변수명이 있으니 매칭시켜준다.
        // 찬가지로 Hellodata -> hellodata 이런식으로 된다.
        return "basic/item";
    }

    // 위의 add 는 templates 의 view 를 해주지만 요놈은 그냥 URL 에서 redirect 를 한다.
    // 다시 Get 으로 받아오기 때문에 새로고침해도 id 가 증가하지 않는다.
//    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV5(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute(item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
