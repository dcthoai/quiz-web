package vn.ptit.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vn.ptit.model.ResponseJSON;
import vn.ptit.model.UserCustom;
import vn.ptit.security.UserAuthenticationRequest;
import vn.ptit.service.impl.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    
	@Autowired
    private AuthenticationManager authenticationManager;
	
	public void createSession(String username, HttpServletRequest request) {
        // Return the current session or create a new one if it doesn't exist
        HttpSession session = request.getSession(true);
        session.setAttribute("username", username);
        session.setAttribute("isUserAuthenticated", true);
	}
	
	@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody String jsonString) {
    	JSONObject userData = new JSONObject(jsonString);
    	
    	// Check data format from client
        if (!userData.has("username") || !userData.has("email") || !userData.has("password")) {
            return new ResponseJSON(false, "Missing required data attributes from client!").badRequest();
        }
	        
        try {
        	String username = userData.getString("username");
        	String email = userData.getString("email");
        	String password = userData.getString("password");
        	String encodedPassword = passwordEncoder.encode(password);
        	
			boolean isExists = userService.isExistUser(username);
			
			if(isExists) {
				return new ResponseJSON(false, "User has already exist!").badRequest();
			} else {
				List<GrantedAuthority> authorities = new ArrayList<>();
				
				UserCustom user = new UserCustom(username, encodedPassword, authorities, true, true, true, true);
				user.setEmail(email);
				user.setRole("ROLE_USER");
				
				int userId = userService.insertUserCustom(user);
				
				if(userId > 0) {
					return new ResponseJSON(true, "Register successful!").ok();
				}
					
				return new ResponseJSON(false, "Register failure! Can not save user to database.").badRequest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseJSON(false, "Exception when checking data user!").badRequest();
		}
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthenticationRequest user, HttpServletRequest request) {
    	try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication authenticated = authenticationManager.authenticate(authentication);
            
            // Get user information after successful authentication
            UserCustom userAuthenticated = (UserCustom) authenticated.getPrincipal();
            String username = userAuthenticated.getUsername();

            // Save authentication state in current session
            createSession(username, request);
            
            return new ResponseJSON(true, "Login successful!").ok();
        } catch (BadCredentialsException e) {
        	return new ResponseJSON(false, "Invalid username or password!").unAuthorized();
        } catch (LockedException e) {
        	return new ResponseJSON(false, "Account has been locked!").accessDenied();
        } catch (AuthenticationException e) {
        	return new ResponseJSON(false, "Internal server error!").serverError();
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        
    	HttpSession session = request.getSession(false);
        	
        if (session != null) {
        	if (session.getAttribute("username") != null && (Boolean) session.getAttribute("isUserAuthenticated")) {
        		session.invalidate();
        		return new ResponseJSON(true, "Logout successful!").ok();        		
        	} else {
        		return new ResponseJSON(false, "User was not login!").badRequest();
        	}
        } else {
        	return new ResponseJSON(false, "Can not logged out your account!").badRequest();
        }
    }
}
