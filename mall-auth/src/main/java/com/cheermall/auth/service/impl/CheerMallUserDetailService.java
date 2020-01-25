package com.cheermall.auth.service.impl;

import com.cheermall.common.entity.CheerMallAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: LuoHaiYang
 */
@Service
public class CheerMallUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //HttpServletRequest httpServletRequest = HttpContextUtil.getHttpServletRequest();
        String permissions = "user:view";
        CheerMallAuthUser authUser = new CheerMallAuthUser("Bruis", "$2a$10$Ar9DE/CF.XKlwT/SCyYfdOEhtsuzoQJ1DojbZEtNB4BEH4SYTjeB.",
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        return authUser;
    }
}
