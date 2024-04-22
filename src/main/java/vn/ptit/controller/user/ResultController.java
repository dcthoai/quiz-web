package vn.ptit.controller.user;

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
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/result")
	public ResponseEntity<?> getResult(@RequestParam("id") int id){
		Result result = resultService.getResultById(id);
		List<ResultAnswer> listAnswers = resultService.getResultAnswers(id);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", result);
		response.put("listAnswers", listAnswers);
		
		Gson gson = new Gson();
		String json = gson.toJson(response);
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
	
	@GetMapping(value = "/result/all")
	public ResponseEntity<?> getAllResults(HttpServletRequest request){
		HttpSession session = request.getSession(false);
        
        if (session != null) {
        	try {
        		String username = (String) session.getAttribute("username");
                
        		if (username != null) {
        			UserCustom user = userService.getUserByUsername(username);
                	
                	if (user != null) {
                		List<Result> results = resultService.getResultsByUserId(user.getUserId());
                		
                		Map<String, Object> response = new HashMap<String, Object>();
                		response.put("results", results);
                		
                		Gson gson = new Gson();
                		String json = gson.toJson(response);
                		
                		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
                	}
        		
        		}
        	} catch (Exception e) {
				e.printStackTrace();
				return new ResponseJSON(false, "Please login to view your results.").badRequest();
			}
        }
		
        return new ResponseJSON(false, "Please login to view your results.").badRequest();
	}
}
