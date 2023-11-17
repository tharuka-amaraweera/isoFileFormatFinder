package castlab.assignment.isoFileFormat.controller;
import castlab.assignment.isoFileFormat.dto.WebErrorDTO;
import castlab.assignment.isoFileFormat.exception.WebErrorException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Component
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WebErrorException.class)
    public ResponseEntity<WebErrorDTO> handleException(final WebErrorException exception, final HttpServletRequest request){
        WebErrorDTO responseBody = exception.getWebErrorDTO();
        HttpStatus httpStatus = HttpStatus.valueOf(responseBody.getStatus());
        return new ResponseEntity<>(responseBody,httpStatus);
    }
}
