// Lombok은 생성자들을 자동으로 만들어준다!! 오오... 좋다!!
package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("Lombok!!");

        String name = helloLombok.getName();
        System.out.println("helloLombok : " + helloLombok);
    }


}
