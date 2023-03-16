package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService(); //static 클래스 레벨에 올라가기 때문에 하나만 존재하게 됨.

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }


}
