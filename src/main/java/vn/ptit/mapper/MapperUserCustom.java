package vn.ptit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;

import vn.ptit.model.UserCustom;

public class MapperUserCustom implements RowMapper<UserCustom> {

    @Override
    public UserCustom mapRow(ResultSet rs, int rowNum) throws SQLException {
    	List<GrantedAuthority> authorities = new ArrayList<>();
        
        boolean enabled = (rs.getInt("enabled") == 1);
        boolean accountNonExpired = (rs.getInt("accountNonExpired") == 1);
        boolean credentialsNonExpired = (rs.getInt("credentialsNonExpired") == 1);
        boolean accountNonLocked = (rs.getInt("accountNonLocked") == 1);
        
        UserCustom user = new UserCustom(rs.getString("username"),
        								 rs.getString("password"),
        								 authorities, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked);
        
        user.setUserId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        
        return user;
    }
}