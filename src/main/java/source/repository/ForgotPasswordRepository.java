package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import source.entity.ForgotPassword;
@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword,String> {
    ForgotPassword findByEmail(String email);
    @Query(value = "select * from Forgot_password q where q.email=:email and q.otp=:otp and LOCALTIMESTAMP-q.date<600",nativeQuery = true)
    ForgotPassword checkOtp(@Param("email") String email, @Param("otp") int otp);
}
