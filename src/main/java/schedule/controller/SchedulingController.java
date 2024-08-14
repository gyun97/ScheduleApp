package schedule.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import schedule.dto.ScheduleDTO;
import schedule.service.SchedulingService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@Slf4j
@RequiredArgsConstructor
public class SchedulingController {

    private final SchedulingService schedulingService;


    // 일정 저장
    @PostMapping
    public ScheduleDTO save(@RequestBody ScheduleDTO scheduleDTO) throws SQLException {
        log.info("일정 추가 로직 실행");
        return schedulingService.save(scheduleDTO);

    }

    // 일정 단건 조회
    @GetMapping("/{id}")
    public ScheduleDTO findById(@PathVariable Long id) throws SQLException {
        log.info("조회할 일정 ID: " + id);
        return schedulingService.findById(id);
    }

    @GetMapping
    public List<ScheduleDTO> findAll(@RequestBody ScheduleDTO scheduleDTO) throws SQLException {
        log.info("일정 조건 조회");
        return schedulingService.findAll(scheduleDTO);
    }

    @PutMapping("/{id}")
    public ScheduleDTO update(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) throws SQLException {
        log.info("수정 로직 실행");
        return schedulingService.update(id, scheduleDTO);
    }

    @DeleteMapping("{id}")
    public Long deleteById(@PathVariable Long id) throws SQLException {
        log.info("일정 삭제");
        return schedulingService.deleteById(id);
    }
}
