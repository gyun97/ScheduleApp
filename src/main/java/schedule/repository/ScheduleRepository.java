package schedule.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedule.dto.ScheduleDTO;
import schedule.entity.ScheduleEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

@Repository
@Slf4j
@Transactional
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키 객체

    public ScheduleDTO saveSchedule(ScheduleEntity scheduleEntity) throws SQLException {
        String sql = "INSERT INTO schedules(director_name, work, pw, schedule_time, registered_date, modified_date) values(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

//            preparedStatement.setLong(1, scheduleEntity.getDirectorId());
            preparedStatement.setString(1, scheduleEntity.getDirectorName());
            preparedStatement.setString(2, scheduleEntity.getWork());
            preparedStatement.setString(3, scheduleEntity.getPassword());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(scheduleEntity.getScheduleTime()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(scheduleEntity.getRegisteredDate()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(scheduleEntity.getRegisteredDate()));
            return preparedStatement;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue(); // DB Insert 후 받아온 기본키 확인
        scheduleEntity.setScheduleId(id);

        ScheduleDTO scheduleDTO = ScheduleDTO.toDTO(scheduleEntity);
        return scheduleDTO;

    }
}




//@RestController
//@RequestMapping("/api")
//public class MemoController {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public MemoController(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @PostMapping("/memos")
//    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
//        // RequestDto -> Entity
//        Memo memo = new Memo(requestDto);
//
//        // DB 저장
//        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
//
//        String sql = "INSERT INTO memo (username, contents) VALUES (?, ?)";
//        jdbcTemplate.update( con -> {
//                    PreparedStatement preparedStatement = con.prepareStatement(sql,
//                            Statement.RETURN_GENERATED_KEYS);
//
//                    preparedStatement.setString(1, memo.getUsername());
//                    preparedStatement.setString(2, memo.getContents());
//                    return preparedStatement;
//                },
//                keyHolder);
//
//        // DB Insert 후 받아온 기본키 확인
//        Long id = keyHolder.getKey().longValue();
//        memo.setId(id);
//
//        // Entity -> ResponseDto
//        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
//
//        return memoResponseDto;
//    }
//
//    @GetMapping("/memos")
//    public List<MemoResponseDto> getMemos() {
//        // DB 조회
//        String sql = "SELECT * FROM memo";
//
//        return jdbcTemplate.query(sql, new RowMapper<MemoResponseDto>() {
//            @Override
//            public MemoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
//                Long id = rs.getLong("id");
//                String username = rs.getString("username");
//                String contents = rs.getString("contents");
//                return new MemoResponseDto(id, username, contents);
//            }
//        });
//    }
//
//    @PutMapping("/memos/{id}")
//    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
//        // 해당 메모가 DB에 존재하는지 확인
//        Memo memo = findById(id);
//        if(memo != null) {
//            // memo 내용 수정
//            String sql = "UPDATE memo SET username = ?, contents = ? WHERE id = ?";
//            jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getContents(), id);
//
//            return id;
//        } else {
//            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
//        }
//    }
//
//    @DeleteMapping("/memos/{id}")
//    public Long deleteMemo(@PathVariable Long id) {
//        // 해당 메모가 DB에 존재하는지 확인
//        Memo memo = findById(id);
//        if(memo != null) {
//            // memo 삭제
//            String sql = "DELETE FROM memo WHERE id = ?";
//            jdbcTemplate.update(sql, id);
//
//            return id;
//        } else {
//            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
//        }
//    }
//
//    private Memo findById(Long id) {
//        // DB 조회
//        String sql = "SELECT * FROM memo WHERE id = ?";
//
//        return jdbcTemplate.query(sql, resultSet -> {
//            if(resultSet.next()) {
//                Memo memo = new Memo();
//                memo.setUsername(resultSet.getString("username"));
//                memo.setContents(resultSet.getString("contents"));
//                return memo;
//            } else {
//                return null;
//            }
//        }, id);
//    }
//}