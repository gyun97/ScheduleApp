package schedule.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.DirectorDTO;
import schedule.dto.ScheduleDTO;
import schedule.entity.DirectorEntity;

import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DirectorRepository {

    private final JdbcTemplate jdbcTemplate;


    KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키 객체

    public DirectorDTO save(DirectorEntity directorEntity) throws SQLException {

        String sql = "INSERT INTO director(email, registered_date, modified_date, director_name) values(?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, directorEntity.getEmail());
            preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(directorEntity.getRegisteredDate()));
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(directorEntity.getRegisteredDate()));
            preparedStatement.setString(4, directorEntity.getDirectorName());
            return preparedStatement;

        }, keyHolder);

        Long id = keyHolder.getKey().longValue(); // DB Insert 후 받아온 기본키 확인
        directorEntity.setDirectorId(id);

        DirectorDTO directorDTO = DirectorDTO.toDTO(directorEntity);
        directorDTO.setDirectorId(id);
        directorDTO.setModifiedDate(directorEntity.getModifiedDate());
        return directorDTO;

    }

    public DirectorDTO findById(Long id) {
        String sql = "SELECT * FROM director WHERE director_id =?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            DirectorDTO directorDTO = new DirectorDTO();
            directorDTO.setDirectorId(rs.getLong("director_id"));
            directorDTO.setEmail(rs.getString("email"));
            directorDTO.setDirectorName(rs.getString("director_name"));
            directorDTO.setRegisteredDate(rs.getTimestamp("registered_date").toLocalDateTime());
            directorDTO.setModifiedDate(rs.getTimestamp("modified_date").toLocalDateTime());
            return directorDTO;
        });
    }
}
