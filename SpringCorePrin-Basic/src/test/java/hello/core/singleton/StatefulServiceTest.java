package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA
//        statefulService1.order("userA", 10000);

        // ThreadB
//        statefulService1.order("userB", 20000);

        // ThreadA : userA 주문 금액 조회
//        int price = statefulService1.getPrice();
        // ThreadA와 ThreadB가 다른 주문을 해도 statefulService 자체가 같은 주소기 때문에 20000이 나온다.
//        System.out.println("price = " + price);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        // 따라서 public void가 아닌 public int로 바꿔주고 아래와 같이 하면 된다.
        int priceA = statefulService1.order("userA", 10000);
        int priceB = statefulService2.order("userB", 20000);

        Assertions.assertThat(priceA).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}