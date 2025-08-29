package garden_lamsay.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getOrCreateUser(String deviceId, String name) {
        return userRepository.findByDeviceId(deviceId)
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .deviceId(deviceId)
                                .name(name != null ? name : "익명 사용자")
                                .build()
                ));
    }
}
