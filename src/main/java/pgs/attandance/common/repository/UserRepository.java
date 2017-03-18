package pgs.attandance.common.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pgs.attandance.common.core.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findByEmail(String email);
     User findById(Long id);

     @Query(nativeQuery = true, value = "select * from users \n" +
             "join roles on users.id = roles.user_id\n" +
             "where roles.rolename = 'Student'")
     List<User> findAllWithRoleNameStudent();


}
