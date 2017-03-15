package pgs.attandance.common.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pgs.attandance.common.core.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
