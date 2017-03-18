package pgs.attandance.common.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pgs.attandance.common.DTO.ActivityDTO;
import pgs.attandance.common.api.ActivityApi.ActivityCreateApi;
import pgs.attandance.common.api.ActivityApi.ActivityUpdateApi;
import pgs.attandance.common.core.ActivitiesToUser;
import pgs.attandance.common.core.Activity;
import pgs.attandance.common.core.User;
import pgs.attandance.common.repository.ActivitiesToUserRepository;
import pgs.attandance.common.repository.ActivityRepository;
import pgs.attandance.common.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivitiesToUserRepository activitiesToUserRepository;


    public ActivityDTO convertToDTO(Activity activity) {
        return new ActivityDTO(
                activity.getId(),
                activity.getName(),
                activity.getActivityPlace(),
                activity.getActivityDate(),
                activity.getActivityDescription()
        );

    }


    public ActivityDTO create(ActivityCreateApi activityCreateApi) {
        Activity activity = new Activity();

        activity.setName(activityCreateApi.getActivityName());
        activity.setActivityPlace(activityCreateApi.getActivityPlace());
        activity.setActivityDate(activityCreateApi.getActivityDate());
        activity.setActivityDescription(Optional.ofNullable(activityCreateApi.getActivityDescription()).orElse(""));

        activityRepository.save(activity);
        List<User> users = userRepository.findAllWithQueryDescription();
        users.forEach(user -> {
            ActivitiesToUser activitiesToUser = new ActivitiesToUser();
            activitiesToUser.setUser(user);
            activitiesToUser.setActivity(activity);

            activitiesToUserRepository.save(activitiesToUser);
        });


        return convertToDTO(activity);
    }


    public ActivityDTO update(Activity activity, ActivityUpdateApi activityUpdateApi) {

        activity.setName(activityUpdateApi.getActivityName());
        activity.setActivityPlace(activityUpdateApi.getActivityPlace());
        activity.setActivityDate(activityUpdateApi.getActivityDate());
        activity.setActivityDescription(activityUpdateApi.getActivityDescription());

        activityRepository.save(activity);

        return convertToDTO(activity);
    }
}