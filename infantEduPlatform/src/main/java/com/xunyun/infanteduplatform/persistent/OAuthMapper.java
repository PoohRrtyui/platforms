package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.AccountBinding;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * oAuth Mapper
 * Created by Pooh on 2016/1/5.
 */
@Repository
public interface OAuthMapper {
    List<AccountBinding> checkClientId(String clientId);
}
