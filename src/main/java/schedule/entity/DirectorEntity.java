package schedule.entity;

import lombok.Data;
import schedule.dto.DirectorDTO;

import java.time.LocalDateTime;

@Data
public class DirectorEntity {

    private Long directorId;
    private String directorName;
    private String email;
    private LocalDateTime registeredDate;
    private LocalDateTime modifiedDate;

    public static DirectorEntity toEntity(DirectorDTO directorDTO) {
        DirectorEntity directorEntity = new DirectorEntity();

        directorEntity.setDirectorName(directorDTO.getDirectorName());
        directorEntity.setEmail(directorDTO.getEmail());
        directorEntity.setRegisteredDate(directorDTO.getRegisteredDate());
        directorEntity.setModifiedDate(directorDTO.getModifiedDate());
        return directorEntity;
    }



}
