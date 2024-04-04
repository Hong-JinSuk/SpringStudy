package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")
public class Item {

    //    @NotNull(groups = UpdateCheck.class) // 이 부분은 우리가 처음 입력할 때, ID를 넣는게 아니고, 등록할 때, ID가 들어오는 것이기 때문에 NotNull 을 쓰면 안된다.
    private Long id;

    //    @NotBlank(groups = {UpdateCheck.class, SaveCheck.class}, message = "공백 That's nono")
    private String itemName;

    //    @NotNull(groups = {UpdateCheck.class, SaveCheck.class})
//    @Range(min = 1000, max = 1000000)
    private Integer price;

    //    @NotNull(groups = {UpdateCheck.class, SaveCheck.class})
//    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
