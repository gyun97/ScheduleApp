package schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import schedule.entity.ScheduleEntity;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    private Long scheduleId;
//    private Long directorId;
    private String directorName;
    private String password;
    private String work;
    private LocalDateTime registeredDate;
    private LocalDateTime modifiedDate;
    private LocalDateTime scheduleTime;


    public static ScheduleDTO toDTO(ScheduleEntity scheduleEntity) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
//        scheduleDTO.setDirectorId(scheduleEntity.getDirectorId());
        scheduleDTO.setDirectorName(scheduleEntity.getDirectorName());
        scheduleDTO.setPassword(scheduleEntity.getPassword());
        scheduleDTO.setWork(scheduleEntity.getWork());
        scheduleDTO.setRegisteredDate(scheduleEntity.getRegisteredDate());
        scheduleDTO.setModifiedDate(scheduleEntity.getModifiedDate());
        scheduleDTO.setScheduleTime(scheduleEntity.getScheduleTime());

        return scheduleDTO;
    }


}
