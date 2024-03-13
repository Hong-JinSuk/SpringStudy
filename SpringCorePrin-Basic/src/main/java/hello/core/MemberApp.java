package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // Spring은 모두 ApplicationContext로 시작하여 모든 객체들을 관리한다. (@Bean들)
        // Annotation(@) 기반으로 만든 메소드들이므로 new An... text 로 생성해줘야 한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // Appconfig에서 memberService라는 메서드를 가져오고, 그 클래스를 MemberService.class 로 알려준다.
        // 여기서는 memberService 가 memberService = new MemberServiceImpl(memberRepository()); 가 되는 것이다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}