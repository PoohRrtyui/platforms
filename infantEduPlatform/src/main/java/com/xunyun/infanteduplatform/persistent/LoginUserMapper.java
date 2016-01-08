package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.LoginUserInfo;

public interface LoginUserMapper {

    public LoginUserInfo findLoginUserById(LoginUserInfo loginuser);
    
}
