package studyscheduler.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import studyscheduler.ejb.UserFacade;
import studyscheduler.entity.User;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private UserFacade user;

    public UserBean() {
    }

    public User getUser() {
        return user.getUser();
    }

    public void editProfile(ActionEvent e) {
        RequestContext.getCurrentInstance().execute("PF('profileEditDialog').hide();");
    }
}
