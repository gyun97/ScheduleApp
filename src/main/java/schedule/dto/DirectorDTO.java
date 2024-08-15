package schedule.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import schedule.entity.DirectorEntity;

import java.time.LocalDateTime;

@Data
public class DirectorDTO {

    private Long directorId;
    private String directorName;

    @Email
    private String email;
    private LocalDateTime registeredDate;
    private LocalDateTime modifiedDate;

    public static DirectorDTO toDTO(DirectorEntity directorEntity) {
        DirectorDTO directorDTO = new DirectorDTO();

        directorDTO.setDirectorName(directorEntity.getDirectorName());
        directorDTO.setEmail(directorEntity.getEmail());
        directorDTO.setRegisteredDate(directorEntity.getRegisteredDate());
        directorDTO.setModifiedDate(directorEntity.getModifiedDate());
        return directorDTO;
    }


}
