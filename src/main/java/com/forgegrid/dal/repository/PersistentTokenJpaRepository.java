package com.forgegrid.dal.repository;

import com.forgegrid.dal.entity.PersistentLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersistentTokenJpaRepository
        extends JpaRepository<PersistentLoginEntity, String>, PersistentTokenRepository {

    Optional<PersistentLoginEntity> findBySeries(String series);

    List<PersistentLoginEntity> findByUsername(String username);

    @Override
    default void createNewToken(PersistentRememberMeToken token) {
        PersistentLoginEntity loginEntity = new PersistentLoginEntity();
        loginEntity.setUsername(token.getUsername());
        loginEntity.setSeries(token.getSeries());
        loginEntity.setToken(token.getTokenValue());
        loginEntity.setLastUsed(token.getDate());
        saveAndFlush(loginEntity);
    }

    @Override
    default void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        findBySeries(seriesId).ifPresent(loginEntity -> {
            loginEntity.setToken(tokenValue);
            loginEntity.setLastUsed(lastUsed);
            saveAndFlush(loginEntity);
        });
    }

    @Override
    default PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return findBySeries(seriesId).map(loginEntity -> new PersistentRememberMeToken(
                loginEntity.getUsername(),
                loginEntity.getSeries(),
                loginEntity.getToken(),
                loginEntity.getLastUsed()
        )).orElse(null);
    }

    @Override
    default void removeUserTokens(String username) {
        deleteAll(findByUsername(username));
    }
}
