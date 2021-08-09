package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.ForgotPassword;
@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword,String> {

}
