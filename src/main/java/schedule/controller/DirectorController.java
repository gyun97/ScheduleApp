package schedule.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import schedule.dto.DirectorDTO;
import schedule.service.DirectorService;

import java.sql.SQLException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;


    // 담당자 추가
    @PostMapping
    public DirectorDTO save(@RequestBody DirectorDTO directorDTO) throws SQLException {
        log.info("담당자 추가 로직 실행");
        return directorService.save(directorDTO);
    }

    // 담당자 조회
    @GetMapping("/{id}")
    public DirectorDTO findById(@PathVariable Long id) throws SQLException {
        log.info("조회할 담당자 ID: " + id);
        return directorService.findById(id);
    }



}
