package vn.ptit.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import vn.ptit.dao.impl.ExamDAO;
import vn.ptit.model.Exam;

@Controller("userHomeController")
public class HomeController {
	@Autowired
	private ExamDAO examDAO;
	
	@GetMapping(value = "/")
	public ResponseEntity<?> homepage() {
		Exam exam = examDAO.getById(1);

        Gson gson = new Gson();
        String json = gson.toJson(exam);
        
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
	
	@GetMapping(value = "/exam")
	public ResponseEntity<?> getExamById(@RequestParam("id") int id){
		Exam exam = examDAO.getById(id);
        Gson gson = new Gson();
        String json = gson.toJson(exam);
        
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<?> searchExam(@RequestParam("name") String name){
		String s = "%" + name.trim() + "%";
		List<Exam> listExams = examDAO.getExamByName(s);
		
        Gson gson = new Gson();
        String json = gson.toJson(listExams);
        
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
}