package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Map;

@Repository // ComponentScan 의 대상이 된다.
public class ItemRepository {

    // ⚠️ 실제로는 HashMap 을 쓰면 안된다. Why? 동시에 여러 Thread 가 접근할 수 있기 때문이다.
    // HashMap 을 쓰고 싶으면 ConcurrentHashMap 을 써야한다.
    private static final Map<Long, Item> store = new HashMap<>(); // static
    private static long sequence = 0L; // static, 여기도 long 말고 다르게 써야한다. 이유는 위와 같다.

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item); // key = Id, value = item
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values()); // 감싸서 반환해야 ArrayList 에 값이 변경되도 store 에 영향 X
    }

    public void update(Long itemId, Item updateParam) { // id는 그대로이고 정보만 바뀜
        Item findItem = findById(itemId);
        // 이런 경우에는 ItemDTO 같은 클래스를 만들어서 Id를 제외한 나머지 데이터들로만 세팅되게 해줘야 한다.
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
