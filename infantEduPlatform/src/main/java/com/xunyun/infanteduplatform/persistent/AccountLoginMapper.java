package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.AccountBinding;
import org.springframework.stereotype.Repository;

/**
 * 第三方登录Mapper
 * Created by Pooh on 2015/12/30.
 */
@Repository
public interface AccountLoginMapper {
    AccountBinding queryAccount(AccountBinding accountBinding);
    void saveAccount(AccountBinding accountBinding);
}
