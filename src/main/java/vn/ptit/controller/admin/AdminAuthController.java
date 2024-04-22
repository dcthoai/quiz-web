package vn.ptit.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vn.ptit.model.ResponseJSON;
import vn.ptit.model.UserCustom;
import vn.ptit.security.UserAuthenticationRequest;
import vn.ptit.service.impl.UserService;

@Controller
public class AdminAuthController {
	@Autowired
	private UserService userService;
    
	@Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/admin/login")
    public ResponseEntity<?> login(@RequestBody UserAuthenticationRequest user, HttpServletRequest request) {
    	try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication authenticated = authenticationManager.authenticate(authentication);
            
            // Get user information after successful authentication
            UserCustom userAuthenticated = (UserCustom) authenticated.getPrincipal();
            String username = userAuthenticated.getUsername();
            if (userService.isAdmin(username)) {
            	// Save authentication state in current session
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("isAdmin", true);
                session.setAttribute("isUserAuthenticated", true);
                
                return new ResponseJSON(true, "Login admin successful!").ok();
            }

            return new ResponseJSON(false, "Your account does not have enough permissions to access this page.").badRequest();
        } catch (BadCredentialsException e) {
        	return new ResponseJSON(false, "Invalid username or password!").unAuthorized();
        } catch (LockedException e) {
        	return new ResponseJSON(false, "Account has been locked!").accessDenied();
        } catch (AuthenticationException e) {
        	return new ResponseJSON(false, "Internal server error!").serverError();
        }
    }
    
    @PostMapping("/admin/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        
    	HttpSession session = request.getSession(false);
        	
        if (session != null) {
        	if (session.getAttribute("username") != null && (Boolean) session.getAttribute("isUserAuthenticated")) {
        		session.invalidate();
        		return new ResponseJSON(true, "Logout admin successful!").ok();        		
        	} else {
        		return new ResponseJSON(false, "Admin was not login!").badRequest();
        	}
        } else {
        	return new ResponseJSON(false, "Can not logged out your account!").badRequest();
        }
    }
}
