package vn.ptit.model;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseJSON {
	private JSONObject response = new JSONObject();
	
	public ResponseJSON(boolean status, String message) {
		response.put("success", status);
		response.put("message", message);
	}
	
	public ResponseEntity<?> ok(){
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
	}
	
	public ResponseEntity<?> badRequest(){ 
		return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(response.toString());
	}
	
	public ResponseEntity<?> notFound(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(response.toString());
	}
	
	public ResponseEntity<?> serverError(){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response.toString());
	}
	
	public ResponseEntity<?> unAuthorized() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(response.toString());
	}
	
	public ResponseEntity<?> accessDenied(){
		return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).body(response.toString());
	}
}
