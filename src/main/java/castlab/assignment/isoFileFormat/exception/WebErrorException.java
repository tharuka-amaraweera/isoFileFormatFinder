package castlab.assignment.isoFileFormat.exception;

import castlab.assignment.isoFileFormat.dto.WebErrorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebErrorException extends RuntimeException{
    private final WebErrorDTO webErrorDTO;

    public WebErrorException(WebErrorDTO webErrorDTO) {
        this.webErrorDTO = webErrorDTO;
    }
}
