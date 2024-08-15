package schedule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.ScheduleDTO;
import schedule.entity.ScheduleEntity;
import schedule.exception.InvalidInformationException;
import schedule.exception.InvalidPasswordException;
import schedule.repository.ScheduleRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SchedulingService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleDTO save(ScheduleDTO scheduleDTO) throws SQLException {
        ScheduleEntity scheduleEntity = ScheduleEntity.toEntity(scheduleDTO);
        return scheduleRepository.save(scheduleEntity);
    }

    public ScheduleDTO findById(Long id) throws SQLException {
        return scheduleRepository.findById(id);
    }

    public List<ScheduleDTO> findAll(ScheduleDTO scheduleDTO) throws SQLException {
        ScheduleEntity scheduleEntity = ScheduleEntity.toEntity(scheduleDTO);
        LocalDateTime modifiedDate = scheduleEntity.getModifiedDate();
        Long directorId = scheduleEntity.getDirectorId();
        return scheduleRepository.findAll(modifiedDate, directorId);
    }

    public ScheduleDTO update(Long id, ScheduleDTO scheduleDTO) throws SQLException {
        ScheduleEntity scheduleEntity = ScheduleEntity.toEntity(scheduleDTO);
        if (!scheduleEntity.getPassword().equals(scheduleRepository.findById(id).getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        } else if (scheduleRepository.findById(id) == null) {
            throw new InvalidInformationException("해당 일정이 존재하지 않습니다.");
        } else {
            return scheduleRepository.update(id, scheduleEntity);
        }
    }

    public Long deleteById(Long id, String password) throws SQLException {
        if (!password.equals(scheduleRepository.findById(id).getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        } else if (scheduleRepository.findById(id) == null) {
            throw new InvalidInformationException("해당 일정이 존재하지 않습니다.");
        } else {
            return scheduleRepository.deleteById(id);
        }
    }
}
