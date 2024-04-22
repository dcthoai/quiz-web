package vn.ptit.controller.admin;

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

import com.google.gson.Gson;

import vn.ptit.model.ResponseJSON;
import vn.ptit.model.UserCustom;
import vn.ptit.service.impl.UserService;

@Controller
public class AdminAccountController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/admin/account")
	public ResponseEntity<?> accounts(HttpServletRequest request){
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
		
		Gson gson = new Gson();
		List<UserCustom> students = userService.getAllStudents();
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("students", students);
		String json = gson.toJson(response);
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
}
