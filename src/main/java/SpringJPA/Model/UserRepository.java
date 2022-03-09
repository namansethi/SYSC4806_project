package SpringJPA.Model;

import SpringJPA.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserId(@Param("id") Long id);

    List<User> findByUsername(@Param("username") String username);
}