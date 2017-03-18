package pgs.attandance.common.DTO;


import java.time.LocalDateTime;

public class ActivityDTO {
    private Long id;
    private String activityName;
    private String activityPlace;
    private LocalDateTime activityDate;
    private String activityDescription;

    public ActivityDTO() {
    }

    public ActivityDTO(Long id, String activityName, String activityPlace, LocalDateTime activityDate, String activityDescription) {
        this.id = id;
        this.activityName = activityName;
        this.activityPlace = activityPlace;
        this.activityDate = activityDate;
        this.activityDescription = activityDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace;
    }

    public LocalDateTime getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDateTime activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }
}
