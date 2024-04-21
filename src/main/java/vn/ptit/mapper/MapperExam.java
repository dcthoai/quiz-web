package vn.ptit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import vn.ptit.model.Exam;

public class MapperExam implements RowMapper<Exam>{
	
	@Override
	public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
		Exam exam = new Exam();
        exam.setId(rs.getInt("id"));
        exam.setExamName(rs.getString("examName"));
        exam.setDescription(rs.getString("description"));
        exam.setStatus(rs.getString("status"));
        exam.setTime(rs.getInt("time"));
        exam.setDeadline(rs.getTimestamp("deadline"));
        exam.setTimeStart(rs.getTimestamp("timeStart"));
        
        return exam;
	}
}
