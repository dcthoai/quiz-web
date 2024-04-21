package vn.ptit.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import vn.ptit.model.Result;
import vn.ptit.model.ResultAnswer;
import vn.ptit.service.impl.ResultService;

@Controller
public class ResultController {

	@Autowired
	private ResultService resultService;
	
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
}
