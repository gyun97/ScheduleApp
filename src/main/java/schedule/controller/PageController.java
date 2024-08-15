package schedule.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import schedule.dto.ScheduleDTO;
import schedule.service.PageService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/pages")
@RequiredArgsConstructor
@Slf4j
public class PageController {

    private final PageService pageService;

    @GetMapping
    public List<ScheduleDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws SQLException {
        log.info("일정 목록 조회 - 페이지 번호: {}, 페이지 크기: {}", page, size);
        return pageService.findAll(page, size);
    }
}
