package hello.core.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
