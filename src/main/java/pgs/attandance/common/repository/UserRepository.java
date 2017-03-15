package pgs.attandance.common.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pgs.attandance.common.core.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findByUsername(String userName);
     User findById(Long id);


}
