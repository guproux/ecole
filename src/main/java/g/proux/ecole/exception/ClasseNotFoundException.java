package g.proux.ecole.exception;

import lombok.Getter;

@Getter
public class ClasseNotFoundException extends Exception {

    private String code;

    public ClasseNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

}
