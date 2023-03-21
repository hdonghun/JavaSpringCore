package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeTest.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        // 서로 다른 참조값을 가진 인스턴스를 생성한다! 서로 다르다!
        // 스프링 컨테이너에 요청할 떄 마다 새로 생성된다., 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다.
        // 그래서 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 괸리해야 한다. 종료 메서드에 대한 호출도 클라이언트가 직접 해야한다.
        // prototypeBean1.close() 이렇게
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2); // 다르면 초록불!
        ac.close(); // 알아서 쓰고 버린다. close 관계없이;
        //싱글톤 빈은 스프링 컨테이너가 관리하기 떄문에 스프링 컨테이너가 종료될 떄 빈의 종료 메서드가 실행되지만,
        // 프로토타입 빈은 컨테이너가 종료될 떄 , @PreDestory 같은 종료 메서드가 전혀 실행되지 않는다.

    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
