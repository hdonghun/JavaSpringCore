package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자가 10000원 주문
        // 지역변수를 사용하자
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB : B사용자가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);
        // ThreadA : 사용자A가 주문 금액 조회
//        int price1 = statefulService1.getPrice();
        System.out.println("price1 = " + userAPrice); // 20000원이 출력된다.

//        int price2 = statefulService2.getPrice();
        System.out.println("price2 = " + userBPrice); // 20000원이 출력된다.

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}
//    StatefulService 의 price 필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경한다.
//        사용자A의 주문금액은 10000원이 되어야 하는데, 20000원이라는 결과가 나왔다.
//        실무에서 이런 경우를 종종 보는데, 이로인해 정말 해결하기 어려운 큰 문제들이 터진다.(몇년에 한번씩 꼭
//        만난다.)
//        진짜 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자.