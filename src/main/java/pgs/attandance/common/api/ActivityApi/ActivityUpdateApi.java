package pgs.attandance.common.api.ActivityApi;


import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class ActivityUpdateApi {

    @ApiModelProperty(value = "Name", required = true)
    private String activityName;

    @ApiModelProperty(value = "Place")
    private String activityPlace;

    @ApiModelProperty(value = "Start Date")
    private LocalDateTime activityDate;

    @ApiModelProperty(value = "Description")
    private String activityDescription;


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

