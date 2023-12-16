package app.manager.client.service;

import app.manager.client.dto.ResponseObject;
import app.manager.client.model.User;
import app.manager.client.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ResponseObject> login(User user){
        Optional<User> iUser = userRepository.findByEmail(user.getEmail());

        if(iUser.isPresent()){
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<ResponseObject> response = restTemplate.postForEntity(
                    "http://localhost:8080/api/authenticate"
                    , user
                    , ResponseObject.class
            );

            return response;
        } else {
            return ResponseEntity.ok(
                    new ResponseObject("Bad Credentials", null)
            );
        }
    }
}
