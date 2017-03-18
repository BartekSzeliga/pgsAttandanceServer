package pgs.attandance.common.resources;


import com.google.common.base.Preconditions;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pgs.attandance.common.DTO.ActivityDTO;
import pgs.attandance.common.api.ActivityApi.ActivityCreateApi;
import pgs.attandance.common.api.ActivityApi.ActivityResponse;
import pgs.attandance.common.api.ActivityApi.ActivityUpdateApi;
import pgs.attandance.common.core.ActivitiesToUser;
import pgs.attandance.common.core.Activity;
import pgs.attandance.common.repository.ActivitiesToUserRepository;
import pgs.attandance.common.repository.ActivityRepository;
import pgs.attandance.common.service.ActivityService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/activity/")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivitiesToUserRepository activitiesToUserRepository;


    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "api/getAll", method = RequestMethod.GET)
    public ActivityResponse getAll() {
        List<Activity> activitys = activityRepository.findAll();
        return new ActivityResponse(activitys.stream()
                .map(obj -> activityService.convertToDTO(obj))
                .collect(Collectors.toList()));
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "api/create", method = RequestMethod.POST)
    public ActivityDTO create(@Valid @RequestBody ActivityCreateApi activityCreateApi) {
        return activityService.create(activityCreateApi);
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "api/update{id}", method = RequestMethod.PUT)
    public ActivityDTO update(@PathVariable Long id ,
                          @Valid @RequestBody ActivityUpdateApi activityUpdateApi) {
        Activity activity = activityRepository.findOne(id);
        Preconditions.checkNotNull(activity, "Podaj poprawne id użytkownika");
        return activityService.update(activity, activityUpdateApi);
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ActivityDTO get(@PathVariable Long id) {
        Activity activity = activityRepository.findOne(id);
        Preconditions.checkNotNull(activity,"Podaj poprawne id");
        ActivityDTO activityDTO = activityService.convertToDTO(activity);
        return activityDTO;
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        List<ActivitiesToUser> activitiesToUsers = activitiesToUserRepository.findAllByActivityId(id);

        activitiesToUsers.forEach(activitiesToUser -> activitiesToUserRepository.delete(activitiesToUser));
        activityRepository.delete(id);
        return "Activity deleted" ;
    }


}