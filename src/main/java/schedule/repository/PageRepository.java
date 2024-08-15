package schedule.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.ScheduleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PageRepository {

    private final JdbcTemplate jdbcTemplate;


    public List<ScheduleDTO> findAll(int page, int size) throws SQLException {
        int offset = (page - 1) * size;
        String sql = "SELECT * FROM schedules ORDER BY registered_date DESC LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, new Object[]{size, offset}, new RowMapper<ScheduleDTO>() {
            @Override
            public ScheduleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ScheduleDTO scheduleDTO = new ScheduleDTO();
                scheduleDTO.setScheduleId(rs.getLong("schedule_id"));
                scheduleDTO.setDirectorId(rs.getLong("director_id"));
                scheduleDTO.setDirectorName(rs.getString("director_name"));
                scheduleDTO.setWork(rs.getString("work"));
                scheduleDTO.setPassword(rs.getString("pw"));
                scheduleDTO.setScheduleTime(rs.getTimestamp("schedule_time").toLocalDateTime());
                scheduleDTO.setRegisteredDate(rs.getTimestamp("registered_date").toLocalDateTime());
                scheduleDTO.setModifiedDate(rs.getTimestamp("modified_date") != null
                        ? rs.getTimestamp("modified_date").toLocalDateTime() : null);
                return scheduleDTO;
            }
        });
    }

}
