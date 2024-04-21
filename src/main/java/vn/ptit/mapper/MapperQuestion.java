package vn.ptit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import vn.ptit.model.Question;

public class MapperQuestion implements RowMapper<Question>{

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		Question question = new Question();
		
        question.setId(rs.getInt("id"));
        question.setExam_id(rs.getInt("exam_id"));
        question.setQuestionName(rs.getString("questionName"));
        question.setAnswerA(rs.getString("answerA"));
        question.setAnswerB(rs.getString("answerB"));
        question.setAnswerC(rs.getString("answerC"));
        question.setAnswerD(rs.getString("answerD"));
        question.setCorrectAns(rs.getString("correctAns"));
        
        return question;
	}

}
