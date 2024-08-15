package schedule.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private Long directorId;
    private String directorName;

    @NotNull
    private String password;

    @NotNull
    @Size(max = 200)
    private String work;

    private LocalDateTime registeredDate = LocalDateTime.now();

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedDate;
    private LocalDateTime scheduleTime;


    public static ScheduleDTO toDTO(ScheduleEntity scheduleEntity) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setScheduleId(scheduleEntity.getScheduleId());
        scheduleDTO.setDirectorId(scheduleEntity.getDirectorId());
        scheduleDTO.setDirectorName(scheduleEntity.getDirectorName());
        scheduleDTO.setPassword(scheduleEntity.getPassword());
        scheduleDTO.setWork(scheduleEntity.getWork());
        scheduleDTO.setRegisteredDate(scheduleEntity.getRegisteredDate());
        scheduleDTO.setModifiedDate(scheduleEntity.getModifiedDate());
        scheduleDTO.setScheduleTime(scheduleEntity.getScheduleTime());

        return scheduleDTO;
    }


}
