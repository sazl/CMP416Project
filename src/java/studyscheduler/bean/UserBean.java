package studyscheduler.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import studyscheduler.ejb.UserFacade;

@Named
@RequestScoped
public class UserBean implements Serializable {

    @EJB
    private UserFacade user;
    
    public UserBean() {
    }
    
    public String getUsername() {
        return user.getUser().getUsername();
    }
}
