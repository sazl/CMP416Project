package studyscheduler.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import studyscheduler.ejb.UserFacade;
import studyscheduler.entity.User;

@Named
@RequestScoped
public class UserBean implements Serializable {

    @EJB
    private UserFacade user;
    
    public UserBean() {
    }
    
    public User getUser() {
        return user.getUser();
    }
}
