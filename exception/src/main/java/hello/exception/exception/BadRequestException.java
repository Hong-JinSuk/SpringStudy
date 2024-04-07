package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// ExceptionHandler 구현을 대신해준다.
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청 오류") // 오류 종류, 오류 원인
public class BadRequestException extends RuntimeException{
}
