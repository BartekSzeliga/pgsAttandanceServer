package pgs.attandance.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pgs.attandance.common.core.ActivitiesToUser;

import java.util.List;


@Repository
public interface ActivitiesToUserRepository extends JpaRepository<ActivitiesToUser, Long> {
    List<ActivitiesToUser> findAllByActivityId(Long id);

}
