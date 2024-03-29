package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Bean을 전부 받아오기 위한 Annotation, @component가 붙은 클래스를 모두 찾아 자동으로 Spring Bean으로 등록해준다.
@ComponentScan( // 권장 : 패지지 위치를 지정하지 않고, 설정 정보를 클래스의 위치를 프로젝트 최상단에 두는 것.
        // 이 조건은 빼줌. 여기서는 AppConfig가 등록되면 비교할 수 없으니 Configuration이 붙은 AppConfig는 제외해준다.
//        basePackages = "hello.core.member", // 지정한 곳부터 할 수 있음
//        basePackageClasses = AutoAppConfig.class, // 현재 내 패키지에서 부터 찾음
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

public class AutoAppConfig {

    // 수동 vs 자동 -> 수동이 오버라이딩 해버림 => 하지만, 이런 오류를 막기 위해 SpringBoot 실행시 수동과 자동이 있으면 오류를 발생시킴
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
