package com.art.movieland.service;

import com.art.movieland.dao.UserDao;
import com.art.movieland.entity.User;
import com.art.movieland.entity.UserCredentials;
import com.art.movieland.entity.UserToken;
import com.art.movieland.service.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DefaultSecurityService implements SecurityService {
    private int expirationTimeInHours;
    private UserDao userDao;

    private ConcurrentHashMap<String, LocalDateTime> cacheUuid = new ConcurrentHashMap<>();

    @Autowired
    public DefaultSecurityService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserToken login(UserCredentials userCredentials) {
        User user = userDao.getByEmail(userCredentials.getEmail());
        if (user == null) {
            throw new AuthenticationException("Invalid user");
        }
        if (user.getPassword() != userCredentials.getPassword()) {
            throw new AuthenticationException("Invalid password");
        }

        String uuid = UUID.randomUUID().toString();
        UserToken userToken = new UserToken(uuid, user.getName());
        cacheUuid.put(uuid, LocalDateTime.now());
        return userToken;
    }

    @Override
    public String logout(String uuid) {
        cacheUuid.remove(uuid);
        return uuid;
    }

    @Scheduled(fixedRateString = "#{${scheduled.fixedRate.cacheUuid.inMinutes}*60*1000}")
    public void removeExpiredUuids() {
        cacheUuid.forEach((k, v) -> {
                    if (v.plusHours(expirationTimeInHours).isAfter(LocalDateTime.now())) {
                        cacheUuid.remove(k);
                    }
                }
        );
    }

    @Value("${authorization.expirationTime.inHours}")
    public void setExpirationTimeInHours(int expirationTimeInHours) {
        this.expirationTimeInHours = expirationTimeInHours;
    }
}
