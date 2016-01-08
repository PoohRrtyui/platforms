package com.xunyun.infanteduplatform.service;

import com.xunyun.infanteduplatform.domain.AccountBinding;
import com.xunyun.infanteduplatform.persistent.OAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * oAuth Service
 * Created by Pooh on 2016/1/5.
 */
@Service("OAuthService")
public class OAuthService {

    private static Cache cache;
    @Autowired static OAuthMapper oauthMapper;

    @Autowired
    public OAuthService(CacheManager cacheManager){
        this.cache = cacheManager.getCache("code-cache");
    }
    /**
     * 检查客户端Id是否存在
     * @param clientId 客户端Id
     * @return
     */
    public  boolean checkClientId(String clientId) {
        List<AccountBinding> list= oauthMapper.checkClientId(clientId);
        return list.size() > 0;
    }

    /**
     * 添加授权代码
     * @param authCode 授权代码
     * @param username 用户名
     */
    public  void addAuthCode(String authCode, String username){
        cache.put(authCode, username);
    }

    /**
     * 验证授权代码是否有效
     * @param authCode 授权代码
     * @return
     */
    public boolean checkAuthCode(String authCode) {
        return cache.get(authCode) != null;
    }

    /**
     * 添加访问令牌
     * @param accessToken 访问令牌
     * @param username 用户名
     */
    public void addAccessToken(String accessToken, String username) {
        cache.put(accessToken, username);
    }

    /**
     * 根据authCode获取用户名
     * @param authCode
     * @return
     */
    public String getUsernameByAuthCode(String authCode) {
        return (String)cache.get(authCode).get();
    }

    /**
     * 获取授权代码/令牌过期时间
     * @return
     */
    public long getExpireIn() {
        return 3600L;
    }

    /**
     * 验证token
     * @param accessToken
     * @return
     */
    public boolean checkAccessToken(String accessToken) {
        return cache.get(accessToken) != null;
    }

    /**
     * 根据token获取用户名
     * @param accessToken
     * @return
     */
    public String getUsernameByAccessToken(String accessToken) {
        return (String)cache.get(accessToken).get();
    }
}
