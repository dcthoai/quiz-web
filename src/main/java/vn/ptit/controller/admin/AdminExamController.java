package vn.ptit.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vn.ptit.model.Exam;

@Controller
public class AdminExamController {
	
	@PostMapping(value = "/add-exam")
	public ResponseEntity<?> addExam(@RequestBody Exam exam){
		
		return null;
	}
}
