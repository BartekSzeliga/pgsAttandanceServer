package pgs.attandance.common.api.ActivityApi;


import pgs.attandance.common.DTO.ActivityDTO;

import java.util.List;


public class ActivityResponse {
    private List<ActivityDTO> activityDTOS;

    public ActivityResponse() {
    }

    public ActivityResponse(List<ActivityDTO> activityDTOS) {
        this.activityDTOS = activityDTOS;
    }

    public List<ActivityDTO> getActivityDTOS() {
        return activityDTOS;
    }

    public void setActivityDTOS(List<ActivityDTO> activityDTOS) {
        this.activityDTOS = activityDTOS;
    }
}

