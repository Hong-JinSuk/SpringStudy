package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.model.IAttribute;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() { // Test 가 끝날 때 마다 레포는 정리해줘야지 오류를 확인할 수 있다.
        itemRepository.clearStore();
    }

    @Test
    void Save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item saveItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem); // 저장된 값과 조회된 값이 같은지 확인
    }

    @Test
    void findAll() {
        // given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);
        Item itemC = new Item("itemC", 30000, 30);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).contains(itemA, itemB, itemC);
    }

    @Test
    void updateItem() {
        // given
        Item itemA = new Item("itemA", 10000, 10);

        Item saveItem = itemRepository.save(itemA);
        Long itemId = saveItem.getId();

        // when
        Item updateParam = new Item("itemB", 20000, 20);
//        System.out.println(itemRepository.findById(itemId));
        itemRepository.update(itemId, updateParam); // itemId 가 itemA -> itemB 출력문으로 확인 가능
//        System.out.println(itemRepository.findById(itemId));

        // then

        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
    }
}
