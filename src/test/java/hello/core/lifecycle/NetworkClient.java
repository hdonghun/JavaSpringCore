package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient  {

    private String url;
    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작시 호춫
    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call: "+url+" message = "+message);
    }

    //서비스 종류시 호출
    public void disconnect(){
        System.out.println("close "+url);
    }

    @Override // 의존 관계 주입이 끝나면, 호출해 주겠다.
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
}
