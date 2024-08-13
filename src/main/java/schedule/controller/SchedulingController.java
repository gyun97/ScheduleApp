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

//    @GetMapping("/{id}")
//    public ScheduleDTO getSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
//        return schedulingService.findSchedule(scheduleDTO);
//    }

    @PostMapping
    public ScheduleDTO saveSchedule(@RequestBody ScheduleDTO scheduleDTO) throws SQLException {
        return schedulingService.saveSchedule(scheduleDTO);

    }
}
