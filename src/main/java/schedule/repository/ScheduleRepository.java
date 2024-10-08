package schedule.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.ScheduleDTO;
import schedule.entity.ScheduleEntity;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Slf4j
@Transactional
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키 객체

    public ScheduleDTO save(ScheduleEntity scheduleEntity) throws SQLException {
        String sql = "INSERT INTO schedules(director_id, director_name, work, pw, schedule_time, registered_date, modified_date) values(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, scheduleEntity.getDirectorId());
            preparedStatement.setString(2, scheduleEntity.getDirectorName());
            preparedStatement.setString(3, scheduleEntity.getWork());
            preparedStatement.setString(4, scheduleEntity.getPassword());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(scheduleEntity.getScheduleTime()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(scheduleEntity.getRegisteredDate()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(scheduleEntity.getRegisteredDate()));
            return preparedStatement;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue(); // DB Insert 후 받아온 기본키 확인

        ScheduleDTO scheduleDTO = ScheduleDTO.toDTO(scheduleEntity);
        scheduleDTO.setModifiedDate(scheduleEntity.getRegisteredDate());
        scheduleDTO.setScheduleId(id);
        return scheduleDTO;

    }

    public ScheduleDTO findById(Long id) throws SQLException {

        String sql = "SELECT * FROM schedules WHERE schedule_id =?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                ScheduleDTO scheduleDTO = new ScheduleDTO();
                scheduleDTO.setScheduleId(id);
                scheduleDTO.setDirectorId(resultSet.getLong("director_id"));
                scheduleDTO.setDirectorName(resultSet.getString("director_name"));
                scheduleDTO.setWork(resultSet.getString("work"));
                scheduleDTO.setPassword(resultSet.getString("pw"));
                scheduleDTO.setScheduleTime(resultSet.getTimestamp("schedule_time").toLocalDateTime());
                scheduleDTO.setRegisteredDate(resultSet.getTimestamp("registered_date").toLocalDateTime());
                scheduleDTO.setModifiedDate(resultSet.getTimestamp("modified_date").toLocalDateTime());
                return scheduleDTO;

            } else {
                throw new NoSuchElementException("ID가" + id + "인 스케줄은 존재하지 않습니다.");
            }

        }, id);

    }

    public List<ScheduleDTO> findAll(LocalDateTime modifiedDate, Long directorId) throws SQLException {

        String sql = "SELECT * FROM schedules WHERE DATE(modified_date) = ? OR director_id = ? ORDER BY modified_date DESC ";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleDTO>() {
            @Override
            public ScheduleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ScheduleDTO scheduleDTO = new ScheduleDTO();
                scheduleDTO.setDirectorId(rs.getLong("director_id"));
                scheduleDTO.setScheduleId(rs.getLong("schedule_id"));
                scheduleDTO.setDirectorName(rs.getString("director_name"));
                scheduleDTO.setWork(rs.getString("work"));
                scheduleDTO.setPassword(rs.getString("pw"));
                scheduleDTO.setScheduleTime(rs.getTimestamp("schedule_time").toLocalDateTime());
                scheduleDTO.setRegisteredDate(rs.getTimestamp("registered_date").toLocalDateTime());
                scheduleDTO.setModifiedDate(rs.getTimestamp("modified_date").toLocalDateTime());
                return scheduleDTO;

            }
        }, modifiedDate, directorId);


    }

    public ScheduleDTO update(Long id, ScheduleEntity scheduleEntity) throws SQLException {
        ScheduleDTO schedule = findById(id);

        if (schedule != null) {
            String sql = "UPDATE schedules SET work =?, director_name = ?, modified_date = ? WHERE schedule_id =?";
            jdbcTemplate.update(sql, scheduleEntity.getWork(), scheduleEntity.getDirectorName(), LocalDateTime.now(), schedule.getScheduleId());

            scheduleEntity.setScheduleId(id);
            scheduleEntity.setModifiedDate(LocalDateTime.now());
            schedule.setScheduleId(id);
            return ScheduleDTO.toDTO(scheduleEntity);
        } else {
            throw new NoSuchElementException("선택한 일정이 존재하지 않습니다.");
        }

    }

    public Long deleteById(Long id) throws SQLException {
        ScheduleDTO schedule = findById(id);


        String sql = "DELETE FROM schedules WHERE schedule_id = ?;";
        jdbcTemplate.update(sql, id);

        return id;


    }
}

