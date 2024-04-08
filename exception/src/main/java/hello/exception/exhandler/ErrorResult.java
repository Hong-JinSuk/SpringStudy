package hello.exception.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.SimpleTimeZone;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
}
