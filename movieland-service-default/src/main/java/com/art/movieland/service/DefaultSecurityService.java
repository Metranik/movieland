package com.art.movieland.service;

import com.art.movieland.dao.UserDao;
import com.art.movieland.entity.Session;
import com.art.movieland.entity.User;
import com.art.movieland.entity.UserCredentials;
import com.art.movieland.entity.UserToken;
import com.art.movieland.service.exception.AuthenticationException;
import com.lambdaworks.crypto.SCryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DefaultSecurityService implements SecurityService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private int expirationTimeInHours;
    private UserDao userService;

    private ConcurrentHashMap<String, Session> cacheSession = new ConcurrentHashMap<>();

    @Autowired
    public DefaultSecurityService(UserDao userDao) {
        this.userService = userDao;
    }

    @Override
    public UserToken login(UserCredentials userCredentials) {
        String email = userCredentials.getEmail();
        Optional<User> userOptional = userService.getByEmail(email);
        User user = userOptional.orElseThrow(() -> new AuthenticationException("Invalid user"));

        if (!SCryptUtil.check(userCredentials.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }

        String uuid = UUID.randomUUID().toString();
        UserToken userToken = new UserToken(uuid, user.getName());
        cacheSession.put(uuid, new Session(userToken, LocalDateTime.now().plusHours(expirationTimeInHours)));
        logger.debug("Created Token: ", userToken);

        return userToken;
    }

    @Override
    public UserToken logout(String uuid) {
        Session session = cacheSession.get(uuid);
        cacheSession.remove(uuid);
        if (session != null) {
            return session.getUserToken();
        } else {
            return null;
        }
    }

    @Override
    public String getNameByUuid(String uuid) {
        if (uuid==null){
            return "";
        }
        Session session = cacheSession.get(uuid);
        return (session == null) ? "" : session.getUserToken().getName();
    }

    @Scheduled(fixedRateString = "#{${scheduled.fixedRate.cacheSession.inMinutes}*60*1000}")
    public void removeExpiredSessions() {
        cacheSession.forEach((k, v) -> {
                    if (v.getExpirationTime().isAfter(LocalDateTime.now())) {
                        cacheSession.remove(k);
                    }
                }
        );
    }

    @Value("${authorization.expirationTime.inHours}")
    public void setExpirationTimeInHours(int expirationTimeInHours) {
        this.expirationTimeInHours = expirationTimeInHours;
    }
}
