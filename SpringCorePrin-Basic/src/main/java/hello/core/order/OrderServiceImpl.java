package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final이 붙은 변수의 생성자를 자동으로 만들어준다. 아래에 OrderServiceImpl를 만들어주지 않아도 된다.
public class OrderServiceImpl implements OrderService {

    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    @Autowired
    private DiscountPolicy rateDiscountPolicy;

    // 생성자를 통해서만 의존관계가 주입이 된다. 이는 외부에서 수정할 수 없는 "불변"의 값이다. 이는 코딩 과정 에서 상당히 중요하다.
    // setDiscountPolicy 같이 임의로 수정하는 메서드를 만들면 안된다. (실수를 방지하기 위해)
    // 이렇게 생성자가 1개인 경우는 @Autowired가 생랴되어도 된다. (그런데, 그냥 해놓자...)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }
    // DiscountPolicy가 2개 이상의 Bean을 받아올 때, @Qualifier 로 매칭시키거나 우선순위의 메소드에 @Primary 를 써준다.

//    @Autowired
//    public void init(MemoryMemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
