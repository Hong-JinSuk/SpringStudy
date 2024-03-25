package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private Map<Long, Member> store = new HashMap<>(); // Key, 정보
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    // Getter로만 조회 간으
    public static MemberRepository getInstance() {
        return instance;
    }

    // 아무나 생서하지 못하게 생성자로 막아둔다.
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 있는 value를 건들고 싶지 않아 new 로 만들어서 반환
    }

    public void clearStroe() {
        store.clear();
    }
}
