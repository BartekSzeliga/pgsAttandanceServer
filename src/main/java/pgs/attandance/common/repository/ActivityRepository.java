package pgs.attandance.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pgs.attandance.common.core.Activity;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {


}
