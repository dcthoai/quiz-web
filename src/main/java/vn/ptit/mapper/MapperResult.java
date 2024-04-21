package vn.ptit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import vn.ptit.model.Result;

public class MapperResult implements RowMapper<Result>{

	@Override
	public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
		Result result = new Result();
        result.setId(rs.getInt("id"));
        result.setExam_id(rs.getInt("exam_id"));
        result.setUser_id(rs.getInt("user_id"));
        result.setScore(rs.getInt("score"));
        result.setTotalCorrectAns(rs.getInt("totalCorrectAns"));
        return result;
	}
	
}
