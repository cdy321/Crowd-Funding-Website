package cn.cdy.crowd.config;

import cn.cdy.crowd.entity.TAdmin;
import cn.cdy.crowd.entity.TRole;
import cn.cdy.crowd.service.TAdminService;
import cn.cdy.crowd.service.TAuthService;
import cn.cdy.crowd.service.TRoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class CrowdUserDetailsService implements UserDetailsService {

    @Resource
    private TAdminService tAdminService;
    @Resource
    private TRoleService tRoleService;
    @Resource
    private TAuthService tAuthService;
    @Override
    public UserDetails loadUserByUsername(String loginAcct) throws UsernameNotFoundException {
        TAdmin admin = tAdminService.getAdminByLoginAcct(loginAcct);
        Integer adminId = admin.getId();
        List<TRole> roleList = tRoleService.getAssignedRole(adminId);
        List<String> authList = tAuthService.getAssignedAuthNameByAdminId(adminId);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (TRole role : roleList) {
            String roleName = "ROLE_"+role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }
        for (String authName : authList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
            authorities.add(simpleGrantedAuthority);
        }
        SecurityAdmin securityAdmin = new SecurityAdmin(admin,authorities);
        return securityAdmin;
    }
}
