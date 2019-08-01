package com.forgegrid.authentication;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachedRememberMeServices extends PersistentTokenBasedRememberMeServices {

    private static final int TOKEN_CACHE_MAX_SIZE = 100;
    private final PersistentTokenRepository tokenRepository;
    private final UserDetailsService userDetailsService;
    private final Map<String, CachedTokenInfo> tokenCache = new ConcurrentHashMap<>();

    public CachedRememberMeServices(String key, UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {
        if (cookieTokens.length != 2) {
            throw new InvalidCookieException(
                    "Cookie token did not contain 2 tokens, but contained '" + Arrays.asList(cookieTokens) + "'"
            );
        }

        final String presentedSeries = cookieTokens[0];
        final String presentedToken = cookieTokens[1];

        PersistentRememberMeToken token = tokenRepository.getTokenForSeries(presentedSeries);

        if (token == null) {
            // No series match, so we can't authenticate using this cookie
            throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
        }

        UserDetails details;

        if (isTokenCached(presentedSeries, presentedToken)) {
            tokenCache.remove(presentedSeries);
            details = userDetailsService.loadUserByUsername(token.getUsername());
            rewriteCookie(token, request, response);
        } else {
            /* IMPORTANT: We should store token in cache before calling <code>loginWithSpringSecurity</code> method.
               Because execution of this method can take a long time.
             */
            cacheToken(token);
            try {
                details = super.processAutoLoginCookie(cookieTokens, request, response);
                //We should remove token from cache if cookie really was stolen or other authentication error occurred
            } catch (RememberMeAuthenticationException authException) {
                tokenCache.remove(token.getSeries());
                throw authException;
            }
        }
        validateTokenCache();

        return details;
    }

    private void rewriteCookie(PersistentRememberMeToken token, HttpServletRequest request, HttpServletResponse response) {
        setCookie(new String[]{token.getSeries(), token.getTokenValue()}, getTokenValiditySeconds(), request, response);
    }

    private void cacheToken(PersistentRememberMeToken token) {
        if (tokenCache.size() >= TOKEN_CACHE_MAX_SIZE) {
            validateTokenCache();
        }
        CachedTokenInfo tokenInfo = new CachedTokenInfo(token.getTokenValue(), System.currentTimeMillis());
        tokenCache.put(token.getSeries(), tokenInfo);
    }

    private void validateTokenCache() {
        tokenCache.values().removeIf(tokenInfo -> !isTokenInfoValid(tokenInfo));
    }

    private boolean isTokenInfoValid(CachedTokenInfo tokenInfo) {
        int cachedTokenValidityTime = 20 * 1000;
        return (System.currentTimeMillis() - tokenInfo.getCachingTime()) < cachedTokenValidityTime;
    }

    private boolean isTokenCached(String series, String value) {
        return tokenCache.containsKey(series)
                && isTokenInfoValid(tokenCache.get(series))
                && value.equals(tokenCache.get(series).getTokenValue());
    }

    @Data
    @RequiredArgsConstructor
    private static final class CachedTokenInfo {
        private final String tokenValue;
        private final long cachingTime;
    }
}
