package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// 롬북 라이브러리가 제공하는 //@RequiredArgsConstructor 기능을 사용하면 final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.(보이지 않지만 실제 호출이 가능하다)
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("롬북을 사용한 setName");

        System.out.println("helloLombok = " + helloLombok);
    }
}
