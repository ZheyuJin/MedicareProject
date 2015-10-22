package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ProvidersDao;
import dao.UsersDao;
import model.User;

@Controller
public class MostExpensiveController {

    @RequestMapping("/mostexpensive")
   public String greeting(@RequestParam(value="pcode", required=true) String pcode, 
		   @RequestParam(value="state", required=true) String state,
		   Model model) {
    	
    	ProvidersDao pd = new ProvidersDao();
    	String result = pd.mostExpensive(pcode, state);
    	
    	if (result == null) {
    		result = "an error occured with your search";
    	} else if (result == "") {
    		result = "no results found";
    	}
    	model.addAttribute("message", result);
        return "mostexpensive";
    }
    //could hard code 10 results into this, not sure how to pass unlimited number

}