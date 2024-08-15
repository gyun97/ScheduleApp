package schedule.exception;

public class InvalidInformationException extends RuntimeException {

    public InvalidInformationException() {
        super("잘못된 정보이거나 이미 삭제된 정보여서 선택한 일정 정보를 조회할 수 없습니다.");
    }

}

