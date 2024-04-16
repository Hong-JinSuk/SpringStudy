package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Call;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class InternalCallV1Test {

    @Autowired
    CallService callService;

    @Test
    void printProxy() {
        log.info("callService class = {}", callService.getClass());
    }

    @Test
    void internalCall() {
        callService.internal();
    }

    /**
     * external -> internal 로 들어갈 경우 internal 에 있는 @Transactional 이 작동하지 않음을 알 수 있다.
     * 트랜잭션이 작동하지 않아 큰 문제가 발생할 수 있는 문제가 있다.
     * 이는 proxy 에서 호출하는 것이 아닌 external 이라는 박스에서 internal 을 호출해서 발생하는 문제다. - 섹션9-AOP 주의사항 14분경...
     * 이를 해결하는 방법으로 코드를 트랜잭션 메서드로 감싸는 방법이 있다. <- 매우 어렵쓰...
     * 가장 간단한 방법은 internal() 메소드를 여벌로 분리하는 것이다.
     */
    @Test
    void externalCall() {
        callService.external();
    }

    @TestConfiguration
    static class InternalCallV1TestConfig {
        @Bean
        CallService callService() {
            return new CallService();
        }
    }

    static class CallService {
        public void external() {
            log.info("call external");
            printTxInfo();
            log.info("external ->");
            internal();
        }

        @Transactional
        public void internal() {
            log.info("call internal");
            printTxInfo();
        }

        private void printTxInfo() {
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active = {}", txActive);
            boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            log.info("tx readOnly = {}", readOnly);
        }
    }


}
