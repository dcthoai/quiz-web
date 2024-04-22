package vn.ptit.controller.user;

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

import vn.ptit.model.Exam;
import vn.ptit.model.ExamAnswersRequest;
import vn.ptit.model.Question;
import vn.ptit.model.ResponseJSON;
import vn.ptit.model.Result;
import vn.ptit.model.ResultAnswer;
import vn.ptit.model.UserCustom;
import vn.ptit.service.impl.ExamService;
import vn.ptit.service.impl.ResultService;
import vn.ptit.service.impl.UserService;

@Controller
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/exam")
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
	
	@PostMapping(value = "/exam/submit")
	public ResponseEntity<?> submitAnswer(HttpServletRequest request, @RequestBody String jsonString){
		Gson gson = new Gson();
		ExamAnswersRequest requestAnswers = gson.fromJson(jsonString, ExamAnswersRequest.class);

        int examId = requestAnswers.getExamId();
        List<ResultAnswer> listAnswers = requestAnswers.getListAnswers();
        
        for (ResultAnswer resultAnswer : listAnswers) {
			System.out.println(resultAnswer.getUserChoice());
		}
        
        Result result = resultService.markExam(examId, listAnswers);
        
        HttpSession session = request.getSession(false);
        
        if (session != null) {
        	try {
        		String username = (String) session.getAttribute("username");
                
        		if (username != null) {
                	UserCustom user = userService.getUserByUsername(username);
                	
                	if (user != null) {
                		result.setUserId(user.getUserId());

                		int resultId = resultService.insertResult(result);
                		
                		for (ResultAnswer resultAnswer : listAnswers) {
        					resultAnswer.setResultId(resultId);
        					
        					resultService.insertResultAnswer(resultAnswer);
        				}
                		
                		return new ResponseJSON(true, "Your answers is submitted.").ok();
                	}
                }
			} catch (Exception e) {
				e.printStackTrace();
				
				return new ResponseJSON(false, "Server error").serverError();
			}
        }
  
        return new ResponseJSON(false, "Please login to submit your answer").badRequest();
	}
}
