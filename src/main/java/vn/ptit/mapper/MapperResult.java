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
        result.setExamId(rs.getInt("exam_id"));
        result.setUserId(rs.getInt("user_id"));
        result.setScore(rs.getFloat("score"));
        result.setTotalCorrectAns(rs.getInt("totalCorrectAns"));
        return result;
	}
	
}
