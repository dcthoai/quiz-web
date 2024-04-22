package vn.ptit.controller.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vn.ptit.model.Exam;
import vn.ptit.model.ExamRequest;
import vn.ptit.model.Question;
import vn.ptit.model.ResponseJSON;
import vn.ptit.model.TimestampDeserializer;
import vn.ptit.service.impl.ExamService;

@Controller
public class AdminExamController {

	@Autowired
	private ExamService examService;
	
	@GetMapping(value = "/admin/home")
	public ResponseEntity<?> homepage(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			try {
				boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
				
				if (!isAdmin)
					return new ResponseJSON(false, "Admin was not login.").badRequest();
			} catch (Exception e) {
				return new ResponseJSON(false, "Admin was not login.").badRequest();
			}
		} else {
			return new ResponseJSON(false, "Not found session. Admin was not login.").badRequest();
		}
		
		List<Exam> listExams = examService.getAllExams();
		Map<String, Object> examResult = new LinkedHashMap<String, Object>();
		examResult.put("listExams", listExams);

        Gson gson = new Gson();
        String json = gson.toJson(examResult);
        
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
	
	@GetMapping(value = "/admin/exam/search")
	public ResponseEntity<?> searchExam(@RequestParam("name") String name, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			try {
				boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
				
				if (!isAdmin)
					return new ResponseJSON(false, "Admin was not login.").badRequest();
			} catch (Exception e) {
				return new ResponseJSON(false, "Admin was not login.").badRequest();
			}
		} else {
			return new ResponseJSON(false, "Not found session. Admin was not login.").badRequest();
		}
		
		String s = "%" + name.trim() + "%";
		List<Exam> listExams = examService.getExamByName(s);
		Map<String, Object> examResult = new LinkedHashMap<String, Object>();
		examResult.put("listExams", listExams);
		
        Gson gson = new Gson();
        String json = gson.toJson(examResult);
        
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
	
	@GetMapping(value = "/admin/exam/filter")
	public  ResponseEntity<?> filter(@RequestParam(value = "enabled", required = false) String isEnabled,
            						 @RequestParam(value = "free", required = false) String isFree,
            						 HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			try {
				boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
				
				if (!isAdmin)
					return new ResponseJSON(false, "Admin was not login.").badRequest();
			} catch (Exception e) {
				return new ResponseJSON(false, "Admin was not login.").badRequest();
			}
		} else {
			return new ResponseJSON(false, "Not found session. Admin was not login.").badRequest();
		}
		
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
	
	@PostMapping(value = "/admin/add-exam")
	public ResponseEntity<?> addExam(@RequestBody String examJson, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			try {
				boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
				
				if (!isAdmin)
					return new ResponseJSON(false, "Admin was not login.").badRequest();
			} catch (Exception e) {
				return new ResponseJSON(false, "Admin was not login.").badRequest();
			}
		} else {
			return new ResponseJSON(false, "Not found session. Admin was not login.").badRequest();
		}
		
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(Timestamp.class, new TimestampDeserializer())
		        .create();
		ExamRequest examRequest = gson.fromJson(examJson, ExamRequest.class);
		
		if (examRequest != null) {
			Exam exam = new Exam();
			
			exam.setExamName(examRequest.getExamName());
			exam.setDescription(examRequest.getDescription());
			exam.setStatus(examRequest.getStatus());
			exam.setDeadline(examRequest.getDeadline());
			exam.setTimeStart(examRequest.getTimeStart());
			exam.setTime(examRequest.getTime());
			
			List<Question> questions = examRequest.getListQuestions();
			
			int examId = examService.insertExam(exam);
			
			if (examId > 0) {
				for (Question question : questions) {
					question.setExam_id(examId);
					
					examService.insertQuestion(question);
				}
			}
			
			return new ResponseJSON(true, "Insert exam successful!").ok();
		}
		return new ResponseJSON(false, "Insert exam failure! Can not fommat data from client").badRequest();
	}
	
	@GetMapping(value = "admin/exam")
	public ResponseEntity<?> getExamById(@RequestParam("id") int id){
		Exam exam = examService.getExamById(id);
		List<Question> listQuestions = examService.getQuestionsByExamId(id);
		
		Map<String, Object> examResult = new LinkedHashMap<String, Object>();
		examResult.put("exam", exam);
		examResult.put("listQuestions", listQuestions);
		
        Gson gson = new Gson();
        String json = gson.toJson(examResult);
        
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
	
	@PostMapping(value = "/admin/update-exam")
	public ResponseEntity<?> updateExam(@RequestBody String examJson, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			try {
				boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
				
				if (!isAdmin)
					return new ResponseJSON(false, "Admin was not login.").badRequest();
			} catch (Exception e) {
				return new ResponseJSON(false, "Admin was not login.").badRequest();
			}
		} else {
			return new ResponseJSON(false, "Not found session. Admin was not login.").badRequest();
		}
		
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(Timestamp.class, new TimestampDeserializer())
		        .create();
		ExamRequest examRequest = gson.fromJson(examJson, ExamRequest.class);
		Exam exam = new Exam();
		
		exam.setId(examRequest.getId());
		exam.setExamName(examRequest.getExamName());
		exam.setDescription(examRequest.getDescription());
		exam.setDeadline(examRequest.getDeadline());
		exam.setStatus(examRequest.getStatus());
		exam.setTimeStart(examRequest.getTimeStart());
		exam.setTime(examRequest.getTime());
		
		boolean isUpdate = examService.updateExam(exam);
		
		if (isUpdate)
			return new ResponseJSON(true, "Exam has been updated.").ok();
		return new ResponseJSON(false, "Update failure! Server error.").serverError();
	}
	
	@PostMapping(value = "/admin/update-exam-questions")
	public ResponseEntity<?> updateExamQuestions(@RequestBody String examJson, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			try {
				boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
				
				if (!isAdmin)
					return new ResponseJSON(false, "Admin was not login.").badRequest();
			} catch (Exception e) {
				return new ResponseJSON(false, "Admin was not login.").badRequest();
			}
		} else {
			return new ResponseJSON(false, "Not found session. Admin was not login.").badRequest();
		}
		
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(Timestamp.class, new TimestampDeserializer())
		        .create();
		ExamRequest examRequest = gson.fromJson(examJson, ExamRequest.class);
		List<Question> questions = examRequest.getListQuestions();
		boolean isUpdate = true;
		int examId = examRequest.getId();
		
		for (Question question : questions) {
			question.setExam_id(examId);
			
			if (!examService.updateQuestion(question))
				isUpdate = false;
		}
		
		if (isUpdate)
			return new ResponseJSON(true, "Exam has been updated.").ok();
		return new ResponseJSON(false, "Update failure! Server error.").serverError();
	}
	
	@PostMapping(value = "/admin/exam/delete")
	public ResponseEntity<?> delete(@RequestParam("id") int examId, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			try {
				boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
				
				if (!isAdmin)
					return new ResponseJSON(false, "Admin was not login.").badRequest();
			} catch (Exception e) {
				return new ResponseJSON(false, "Admin was not login.").badRequest();
			}
		} else {
			return new ResponseJSON(false, "Not found session. Admin was not login.").badRequest();
		}
		
		boolean isDelete = examService.deleteExam(examId);
		
		if (isDelete)
			return new ResponseJSON(true, "Delete exam successful!").ok();
		
		return new ResponseJSON(false, "Delete exam failure! Server error").badRequest();
	}
}
