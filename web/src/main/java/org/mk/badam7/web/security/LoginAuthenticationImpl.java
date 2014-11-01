package org.mk.badam7.web.security;

import java.util.ArrayList;
import java.util.List;

import org.mk.badam7.database.dao.PlayerDAO;
import org.mk.badam7.database.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * This service is used for authenticating users using Spring-security. It verifies whether a user is registered in the database and assigns appropriate authority to the user i.e either ADMIN OR USER.
 *
 */
public class LoginAuthenticationImpl implements UserDetailsService
{
    @Autowired
    private PlayerDAO playerDAO;

    private static final Integer ROLE_USER = 2;

    /**
     * Checks whether specified username/password is present in the database. If yes, allows access access to the user.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        Player player = playerDAO.findByUsernameAndVerified(username, true);

        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(player.getUsername(), player.getPassword(), true, true, true, true,
                getAuthorities(ROLE_USER));

        return userDetail;
    }

    private List<GrantedAuthority> getAuthorities(Integer role)
    {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if (role.intValue() == 1)
        {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else
        {
            if (role.intValue() == 2)
            {
                authList.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }
        return authList;
    }

}
