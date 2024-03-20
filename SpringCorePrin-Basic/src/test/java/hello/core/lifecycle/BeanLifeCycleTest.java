package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetWorkClient client = ac.getBean(NetWorkClient.class);
        ac.close();
    }

    // 여기서 NetWorkClient를 생성할 때, 주소도 넣으면 안되냐? 하면 되는데, 이건 단일책임 원칙에 위배되고,
    // 생성할 때, 초기화 하는 것은 내부에서 필요한 데이터, 같은 것들만 해야한다. 외부와 연결된 것들은 모두 다른 메소드로 해야한다.
    @Configuration
    static class LifeCycleConfig {

        // 외부 라이브러리에 적용해야 된다면 아래와 같이 @Bean에 넣어주면 되고, 내부 라이브러리에서 해결할 수 있다면,
        // 코드에 있는 @PostConstruct, @PreDestroy 를 쓰자
        //        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetWorkClient netWorkClient() {
            NetWorkClient netWorkClient = new NetWorkClient();
            netWorkClient.setUrl("http://hello-spring.io");
            return netWorkClient;
        }

    }
}
