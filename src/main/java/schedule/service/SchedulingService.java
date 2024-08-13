package schedule.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.ScheduleDTO;
import schedule.entity.ScheduleEntity;
import schedule.repository.ScheduleRepository;

import java.sql.SQLException;

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
    public ScheduleDTO findById(Long id) throws SQLException{
        return scheduleRepository.findById(id);

    }

//    public ScheduleDTO findSchedule(ScheduleDTO scheduleDTO) {
//
//    }
}
