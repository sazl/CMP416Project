package studyscheduler.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import studyscheduler.ejb.UserFacade;
import studyscheduler.entity.User;

@ManagedBean
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
