package vn.ptit.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractDAO<T>{
	@Autowired 
	public JdbcTemplate _jdbcTemplate;
	
	public List<T> executeQuery(String sql, RowMapper<T> rowMapper, Object... parameters){
		try {
			System.out.println(sql);
			
			List<T> listItems = _jdbcTemplate.query(sql, parameters, rowMapper);
			
			return listItems;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList(); // Return empty list if has exception
		}
	}

	public int executeInsert(String sql, Object... parameters) {
		try {
			System.out.println(sql);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();

		    // Execute INSERT and pass KeyHolder into for save generated key
		    int affectedRows = _jdbcTemplate.update(con -> {
			    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			            
			    	for (int i = 0; i < parameters.length; i++) {
			            ps.setObject(i + 1, parameters[i]);
			    	}
			            
			        return ps;
			    }, keyHolder);
		    
		    if (affectedRows > 0) {
		    	int newRecordId = keyHolder.getKey().intValue();
		    	System.out.println("Id bản ghi mới là: " + newRecordId);
		    	
		    	return newRecordId;
		    }

		    return 0;
        } catch (DataAccessException e) {
        	e.printStackTrace();
            return 0;
        }
	}
	
	public int executeUpdate(String sql, Object... parameters) {
		try {
			System.out.println(sql);
			
			int affectedRows = _jdbcTemplate.update(sql, parameters);
			
            return affectedRows;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
	}
}