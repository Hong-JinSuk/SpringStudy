package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

// @Qualifier 와 동일한 어노테이션을 받아옴. -> @Qualifier("mainDiscountPolicy")를 쓰다보면 문자열이라 틀릴 수 있음 이를 그냥 함수로 만든느낌
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("rateDiscountPolicy")
public @interface MainDiscountPolicy {

}
