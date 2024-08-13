package schedule.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import schedule.dto.ScheduleDTO;
import schedule.service.SchedulingService;

import java.sql.SQLException;

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
    public ScheduleDTO findById(@PathVariable Long id) throws SQLException{
        log.info("조회할 일정 ID: " + id);
        return schedulingService.findById(id);
    }



}
