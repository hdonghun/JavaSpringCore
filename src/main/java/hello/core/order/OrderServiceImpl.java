package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    // final 을 사용하면 좋은점 , 초기화 단계에 값이(생성자값) 들어와야 되는데, 값을 입력을 안해주면 컴파일 오류가 발생, 그래서 알기 쉽다.
    // final 키워드 :  생성자 주입을 사용하면 필드에 'final' 키워드를 사용할 수 있다. 그래서 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.
    private final MemberRepository memberRepository; // 회원의 등급을 확인하기 위해 //final로 되어있으면, 생성자든 = 값 이든 할당되어야 한다.
    private final DiscountPolicy discountPolicy; // 어떠한 할인 정책에 따른 //인터페이스에만 의존하도록 설계를 변경

    // RequiredArgsConstructor이 아래와 같은 생성자 주입에 필요한 것들을 자동으로 만들어준다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return  memberRepository;
    }

}
