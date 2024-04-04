package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Locale;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String loginId; // 사용자가 쓰는 로그인 ID
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
}
