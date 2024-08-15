package schedule.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dto.DirectorDTO;
import schedule.entity.DirectorEntity;
import schedule.repository.DirectorRepository;

import java.sql.SQLException;

@Service
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Transactional
public class DirectorService {

    private final DirectorRepository directorRepository;


    public DirectorDTO save(DirectorDTO directorDTO) throws SQLException {
        DirectorEntity directorEntity = DirectorEntity.toEntity(directorDTO);
        return directorRepository.save(directorEntity);
    }


    public DirectorDTO findById(Long id) {
        return directorRepository.findById(id);
    }
}
