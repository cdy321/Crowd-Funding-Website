package cn.cdy.crowd.config;

import cn.cdy.crowd.entity.TAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityAdmin extends User {

    private TAdmin originalAdmin;
    public SecurityAdmin(TAdmin originalAdmin, List<GrantedAuthority> authorities) {
        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);
        this.originalAdmin = originalAdmin;
        this.originalAdmin.setUserPswd(null);
    }
    public TAdmin getOriginalAdmin(){
        return originalAdmin;
    }
}
