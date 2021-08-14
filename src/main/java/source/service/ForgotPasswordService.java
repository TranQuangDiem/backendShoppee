package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.ForgotPassword;
import source.model.SendMail;
import source.repository.ForgotPasswordRepository;

import java.sql.Timestamp;

@Service
public class ForgotPasswordService {
    @Autowired
    ForgotPasswordRepository forgotPasswordRepository;
    @Autowired
    SendMail sendMail;
    public void save(String email) throws Exception {
        ForgotPassword forgotPassword = new ForgotPassword();
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        Timestamp datetime = new Timestamp(date.getTime());
        int code = (int) Math.floor(((Math.random() * 899999) + 10000000));
        forgotPassword.setDate(datetime);
        forgotPassword.setOtp(code);
        forgotPassword.setEmail(email);
        sendMail.sendEmail(forgotPassword.getEmail(),"Bạn muốn thay đổi mật khẩu","Mã xác thực của bạn là: "+code + " mã có thời hạn 5 phút");
        forgotPasswordRepository.save(forgotPassword);
    }
    public ForgotPassword findByEmail(String email){
        return forgotPasswordRepository.findByEmail(email);
    }
    public ForgotPassword checkOtp(String email,int otp){
        return forgotPasswordRepository.checkOtp(email,otp);
    }
    public void delete(ForgotPassword forgotPassword){
        forgotPasswordRepository.delete(forgotPassword);
    }
}
