package garden_lamsay.config;

import garden_lamsay.user.User;
import garden_lamsay.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class DeviceAuthInterceptor implements HandlerInterceptor {

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String deviceId = request.getHeader("X-Device-Id");
        if (deviceId == null || deviceId.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 임시로 사용자 이름은 "익명" (프론트에서 제공해줄 수도 있음)
        User user = userService.getOrCreateUser(deviceId, "익명");
        request.setAttribute("user", user);
        return true;
    }
}
