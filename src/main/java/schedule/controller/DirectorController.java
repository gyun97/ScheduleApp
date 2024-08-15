package schedule.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedule.dto.DirectorDTO;
import schedule.service.DirectorService;

import java.sql.SQLException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;


    @PostMapping
    public DirectorDTO save(@RequestBody DirectorDTO directorDTO) throws SQLException {
        log.info("담당자 추가 로직 실행");
        return directorService.save(directorDTO);
    }



}
