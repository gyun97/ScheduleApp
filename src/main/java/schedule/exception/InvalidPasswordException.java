package schedule.exception;


public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super("비밀번호가 일치하지 않습니다.");
    }

}

