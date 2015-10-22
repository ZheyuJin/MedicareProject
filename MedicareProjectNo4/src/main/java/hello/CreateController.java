package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.UsersDao;
import model.User;

@Controller
public class CreateController {

    @RequestMapping("/create")
   public String greeting(@RequestParam(value="lastname", required=false, defaultValue="World") String lastname, 
		   @RequestParam(value="age", required=true) String age,
		   @RequestParam(value="city", required=true) String city,
		   @RequestParam(value="email", required=true) String email,
		   @RequestParam(value="firstname", required=true) String firstname,
		   Model model) {
    	
    	User user = new User(lastname, Integer.parseInt(age), city, email, firstname);
		String sucessfail = "error";
		if (user == null) {
			sucessfail = "failed to create user";
		} else {
			sucessfail = "succesfully created user";
		}
		UsersDao ud = new UsersDao();
		ud.create(user);
    	
        model.addAttribute("message", sucessfail);
        return "create";
    }

}