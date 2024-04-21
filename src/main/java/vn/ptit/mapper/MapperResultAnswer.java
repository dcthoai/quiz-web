package vn.ptit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import vn.ptit.model.ResultAnswer;

public class MapperResultAnswer implements RowMapper<ResultAnswer>{

	@Override
	public ResultAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultAnswer resultAnswer = new ResultAnswer();
        resultAnswer.setId(rs.getInt("id"));
        resultAnswer.setQuestionid(rs.getInt("questionid"));
        resultAnswer.setResultid(rs.getInt("resultid"));
        resultAnswer.setUserChoice(rs.getString("userChoice"));
        return resultAnswer;
	}

}
