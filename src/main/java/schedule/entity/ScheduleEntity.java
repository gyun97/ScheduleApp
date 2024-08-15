package schedule.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import schedule.dto.ScheduleDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntity {

    private Long scheduleId;
    private Long directorId;
    private String directorName;
    private String password;
    private String work;
    private LocalDateTime registeredDate;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime modifiedDate;
    private LocalDateTime scheduleTime;

    public static ScheduleEntity toEntity(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();

        scheduleEntity.setScheduleId(scheduleDTO.getScheduleId());
        scheduleEntity.setDirectorId(scheduleDTO.getDirectorId());
        scheduleEntity.setDirectorName(scheduleDTO.getDirectorName());
        scheduleEntity.setPassword(scheduleDTO.getPassword());
        scheduleEntity.setWork(scheduleDTO.getWork());
        scheduleEntity.setRegisteredDate(scheduleDTO.getRegisteredDate());
        scheduleEntity.setModifiedDate(scheduleDTO.getModifiedDate());
        scheduleEntity.setScheduleTime(scheduleDTO.getScheduleTime());

        return scheduleEntity;
    }
}
