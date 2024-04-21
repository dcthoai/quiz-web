package vn.ptit.controller.user;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import vn.ptit.dao.impl.ExamDAO;
import vn.ptit.dao.impl.QuestionDAO;
import vn.ptit.model.Exam;
import vn.ptit.model.Question;

@Controller
public class ExamController {
	
	@Autowired
	private ExamDAO examDAO;
	
	@Autowired
	private QuestionDAO questionDAO;
	
	@GetMapping(value = "/exam")
	public ResponseEntity<?> getExamById(@RequestParam("id") int id){
		Exam exam = examDAO.getById(id);
		List<Question> listQuestions = questionDAO.getQuestionsByExamId(id);
		
		Map<String, Object> examResult = new LinkedHashMap<String, Object>();
		examResult.put("exam", exam);
		examResult.put("listQuestions", listQuestions);
		
        Gson gson = new Gson();
        String json = gson.toJson(examResult);
        
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
}
