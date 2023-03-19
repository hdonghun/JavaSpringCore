package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import javax.swing.text.html.Option;
import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        //Member은 스프링 빈이 아닌 상태.
        @Autowired(required = false) // 의존 관계가 없으면 이 메서드 자체가 호출이 안된다(기본값은 true). // 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { // Nullable로 넣어주면 호출은 된다.
            System.out.println("noBean2 = " + noBean2);
        }
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // 스프링이 빈에 등록이 안되어 있으면, .empty로 호출된다. 값이 있으면 Option.깂으로 호출
            System.out.println("noBean3 = " + noBean3);
        }


    }

}
