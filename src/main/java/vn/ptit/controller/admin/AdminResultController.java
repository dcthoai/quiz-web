package vn.ptit.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import vn.ptit.model.ResponseJSON;
import vn.ptit.model.Result;
import vn.ptit.model.ResultAnswer;
import vn.ptit.model.UserCustom;
import vn.ptit.service.impl.ResultService;
import vn.ptit.service.impl.UserService;

@Controller
public class AdminResultController {
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/admin/result")
	public ResponseEntity<?> getResult(@RequestParam("id") int id, HttpServletRequest request){
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
		
		Result result = resultService.getResultById(id);
		
		if (result != null) {
			List<ResultAnswer> listAnswers = resultService.getResultAnswers(id);
			UserCustom student = userService.getUserById(result.getUserId());
			
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("student", student.getUsername());
			response.put("result", result);
			response.put("listAnswers", listAnswers);
			
			Gson gson = new Gson();
			String json = gson.toJson(response);
			
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
		}
		
		return new ResponseJSON(false, "Not found result!").badRequest();
	}
	
	@GetMapping(value = "/admin/results")
	public ResponseEntity<?> getResults(@RequestParam(value = "student", required = false) Integer studentId,
										@RequestParam(value = "exam", required = false) Integer examId,
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
		
		List<Result> results = new ArrayList<Result>();
		
		if (studentId != null && studentId > 0) {
			results = resultService.getResultsByUserId(studentId);
		}
		
		if (examId != null && examId > 0) {
			results = resultService.getResultsByExamId(examId);
		}
		
		if (studentId != null && examId != null && studentId > 0 && examId > 0) {
			results = resultService.getResultsByUserId(studentId);
			int resultsSize = results.size();
			
			for (int i = resultsSize - 1; i >= 0; i--) {
			    if (results.get(i).getExamId() != examId) {
			        results.remove(i);
			    }
			}
		}
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("listResults", results);
		
		Gson gson = new Gson();
		String json = gson.toJson(response);
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
}
