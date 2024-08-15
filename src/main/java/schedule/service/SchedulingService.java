package schedule.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.ScheduleDTO;
import schedule.entity.ScheduleEntity;
import schedule.exception.InvalidPasswordException;
import schedule.repository.ScheduleRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Transactional
public class SchedulingService {

    private final ScheduleRepository scheduleRepository;


    // 일정 저장
    public ScheduleDTO save(ScheduleDTO scheduleDTO) throws SQLException {
        ScheduleEntity scheduleEntity = ScheduleEntity.toEntity(scheduleDTO);
        return scheduleRepository.save(scheduleEntity);

    }

    // 일정 단건 조회
    public ScheduleDTO findById(Long id) throws SQLException {
        return scheduleRepository.findById(id);

    }

    public List<ScheduleDTO> findAll(ScheduleDTO scheduleDTO) throws SQLException {
        ScheduleEntity scheduleEntity = ScheduleEntity.toEntity(scheduleDTO);
        LocalDateTime modifiedDate = scheduleEntity.getModifiedDate();
//        String directorName = scheduleEntity.getDirectorName();
        Long directorId = scheduleEntity.getDirectorId();
        return scheduleRepository.findAll(modifiedDate, directorId);
    }



    public ScheduleDTO update(Long id, ScheduleDTO scheduleDTO) throws SQLException{
        ScheduleEntity scheduleEntity = ScheduleEntity.toEntity(scheduleDTO);
        if (scheduleEntity.getPassword().equals(scheduleRepository.findById(id).getPassword())) {
            return scheduleRepository.update(id, scheduleEntity);
        } else {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
//        return scheduleRepository.update(id, scheduleEntity);

    }

    public Long deleteById(Long id) throws SQLException {
        return scheduleRepository.deleteById(id);
    }
}
