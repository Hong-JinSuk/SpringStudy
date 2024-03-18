package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 선언, 내부의 스태틱으로 존재. instance가 하나만 존재하게 해준다.
    private static final SingletonService instance = new SingletonService();

    // 새로운 인스턴스를 호출하지 못하고, 항상 하나의 instance만 조회할 수 있게 해준다.
    public static SingletonService getInstance() {
        return instance;
    }

    // private로 선언해주면, 다른 곳에서 new SingletonService로 새로운 객체를 생성하지 못함
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
