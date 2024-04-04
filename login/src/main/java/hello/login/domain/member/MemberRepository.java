package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // static 사용
    private static long sequence = 0L; // static 사용

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save : member = {}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    // Optional 은 빈 통 같은 것이다. null 대신 Optional 로 확인할 수 있게 해주는 것이다.
    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> all = findAll();
//        for (Member member : all) {
//            if(member.getLoginId().equals(loginId)){
//                return Optional.of(member);
//            }
//        }
//        return Optional.empty();

        // findAll 로 들어오는 것들 중에서 filter 에서 매칭 되는 것들 중 첫 번째 놈들 반환.
        return findAll().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store 의 Member 들이 list 로 변환이 된다.
    }

    public void clearStore() {
        store.clear();
    }
}
