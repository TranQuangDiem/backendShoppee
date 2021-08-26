package source.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import source.entity.User;
import source.model.CustomUserDetails;
import source.payload.UserEditDTO;
import source.payload.UserReponse;
import source.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void save(User user){

        userRepository.save(user);
    }
    public void edit(UserEditDTO userEditDTO, User user){
        user.setEmail(userEditDTO.getEmail());
        user.setFullname(userEditDTO.getFullname());
        user.setPhone(userEditDTO.getPhone());
        userRepository.save(user);
    }
    public User findById(long id){
        return userRepository.findById(id);
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(user);
    }


}