package schedule.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.PageDTO;
import schedule.dto.ScheduleDTO;
import schedule.repository.PageRepository;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PageService {

    private final PageRepository pageRepository;


    public List<ScheduleDTO> findAll(int page, int size) throws SQLException {
        return pageRepository.findAll(page, size);
    }
}
