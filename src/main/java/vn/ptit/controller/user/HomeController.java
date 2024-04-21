package vn.ptit.controller.user;

import java.util.ArrayList;
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

import vn.ptit.model.Exam;
import vn.ptit.service.impl.ExamService;

@Controller("userHomeController")
public class HomeController {
	
	@Autowired
	private ExamService examService;
	
	@GetMapping(value = "/")
	public ResponseEntity<?> homepage() {
		List<Exam> listExams = examService.getAllExams();
		Map<String, Object> examResult = new LinkedHashMap<String, Object>();
		examResult.put("listExams", listExams);

        Gson gson = new Gson();
        String json = gson.toJson(examResult);
        
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<?> searchExam(@RequestParam("name") String name){
		String s = "%" + name.trim() + "%";
		List<Exam> listExams = examService.getExamByName(s);
		Map<String, Object> examResult = new LinkedHashMap<String, Object>();
		examResult.put("listExams", listExams);
		
        Gson gson = new Gson();
        String json = gson.toJson(examResult);
        
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
	
	@GetMapping(value = "/filter")
	public  ResponseEntity<?> filter(@RequestParam(value = "enabled", required = false) String isEnabled,
            						 @RequestParam(value = "free", required = false) String isFree){
		
		List<Exam> listExams = new ArrayList<Exam>();

		if (isEnabled != null && isEnabled.equals("true")) {
		    listExams = examService.getExamEnabled();
		}

		if (isFree != null && isFree.equals("true")) {
		    listExams = examService.getExamFree();
		}
		
		Map<String, Object> examResult = new LinkedHashMap<String, Object>();
		examResult.put("listExams", listExams);
		
        Gson gson = new Gson();
        String json = gson.toJson(examResult);
        
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
}