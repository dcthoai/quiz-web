package vn.ptit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import vn.ptit.dao.IUserCustomDAO;
import vn.ptit.mapper.MapperUserCustom;
import vn.ptit.model.UserCustom;

@Repository
public class UserCustomDAO extends AbstractDAO<UserCustom> implements IUserCustomDAO{

	@Override
	public int insert(UserCustom user) {
		String sql = "INSERT INTO `exam`.`user` (`username`, "
											   + "`password`, "
											   + "`email`, "
											   + "`role`, "
											   + "`enabled`, "
											   + "`accountNonExpired`, "
											   + "`credentialsNonExpired`, "
											   + "`accountNonLocked`) "
											   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		int isEnabled = user.isEnabled() ? 1 : 0;
		int isAccountNonExpired = user.isAccountNonExpired() ? 1 : 0;
		int isCredentialsNonExpired = user.isCredentialsNonExpired() ? 1 : 0;
		int isAccountNonLocked = user.isAccountNonLocked() ? 1 : 0;

		int userId = executeInsert(sql, user.getUsername(),
										user.getPassword(),
										user.getEmail(),
										user.getRole(),
										isEnabled,
										isAccountNonExpired,
										isCredentialsNonExpired,
										isAccountNonLocked);
		return userId;
	}

	@Override
	public int update(UserCustom user) {
		String sql = "UPDATE `exam`.`user` SET "
					+ "`username` = ?, "
					+ "`password` = ?, "
					+ "`email` = ?, "
					+ "`role` = ?, "
					+ "`enabled` = ?, "
					+ "`accountNonExpired` = ?, "
					+ "`credentialsNonExpired` = ?, "
					+ "`accountNonLocked` = ? WHERE (`id` = ?)";
		
		int isEnabled = user.isEnabled() ? 1 : 0;
		int isAccountNonExpired = user.isAccountNonExpired() ? 1 : 0;
		int isCredentialsNonExpired = user.isCredentialsNonExpired() ? 1 : 0;
		int isAccountNonLocked = user.isAccountNonLocked() ? 1 : 0;
		int userId = user.getUserId();
		
		int affectedRows = executeUpdate(sql, user.getUsername(),
											  user.getPassword(),
											  user.getEmail(),
											  user.getRole(),
											  isEnabled,
											  isAccountNonExpired,
											  isCredentialsNonExpired,
											  isAccountNonLocked,
											  userId);

		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `exam`.`user` WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		
		return affectedRows;
	}

	@Override
	public UserCustom getById(int id) {
		String sql = "SELECT * FROM `exam`.`user` WHERE (`id` = ?)";
		
		List<UserCustom> listUsers = executeQuery(sql, new MapperUserCustom(), id);
		
		return listUsers.isEmpty() ? null : listUsers.get(0);
	}

	@Override
	public UserCustom getUserByUsername(String username) {
		String sql = "SELECT * FROM `exam`.`user` WHERE (`username` = ?)";
		
		List<UserCustom> listUsers = executeQuery(sql, new MapperUserCustom(), username);
		
		return listUsers.isEmpty() ? null : listUsers.get(0);
	}
}
