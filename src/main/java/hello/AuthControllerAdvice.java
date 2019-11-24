package hello;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.ControllerAdvice;

//additional import 
import hello.repositories.UserRepository;
import hello.entities.AppUser;
import java.util.List;

@ControllerAdvice
public class AuthControllerAdvice {

    //what is missing 
    @Autowired
    private UserRepository userRepository;

    @Autowired   
    private MembershipService membershipService;

    @ModelAttribute("isLoggedIn")
    public boolean getIsLoggedIn(OAuth2AuthenticationToken token){
        return token != null;
    }

    @ModelAttribute("id")

    //this is the new version
    public String getUid(OAuth2AuthenticationToken token){
        if (token == null) return "";

        String uid = token.getPrincipal().getAttributes().get("id").toString();

        List<AppUser> users = userRepository.findByUid(uid);

        if (users.size()==0) {
            AppUser u = new AppUser();
            u.setUid(uid);
            u.setLogin(token2login(token));
            userRepository.save(u);
        }

        return uid;
    }

    /*
    public String getUid(OAuth2AuthenticationToken token){
        if (token == null) return "";
        return token.getPrincipal().getAttributes().get("id").toString();
    }
    */

    @ModelAttribute("login")
    public String getLogin(OAuth2AuthenticationToken token){
        if (token == null) return "";
        
        //to DRY the code 
        
        //return token.getPrincipal().getAttributes().get("login").toString();
        return token2login(token); 
    }

    @ModelAttribute("isMember")
    public boolean getIsMember(OAuth2AuthenticationToken token){
        return membershipService.isMember(token);
    }
    @ModelAttribute("isAdmin")
    public boolean getIsAdmin(OAuth2AuthenticationToken token){
        return membershipService.isAdmin(token);
    }

    @ModelAttribute("role")
    public String getRole(OAuth2AuthenticationToken token){
        return membershipService.role(token);
    }

    //new editing for add users to table
    private String token2login(OAuth2AuthenticationToken token) {
        return token.getPrincipal().getAttributes().get("login").toString();
    }



}