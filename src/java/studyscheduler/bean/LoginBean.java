package studyscheduler.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;
import studyscheduler.ejb.UserFacade;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private UserFacade user;

    public LoginBean() {
    }

    public void login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext fcontext = FacesContext.getCurrentInstance();

        if (username != null && password != null && user.authenticate(username, password)) {
            fcontext.getApplication()
                    .getNavigationHandler()
                    .handleNavigation(fcontext, null, "home");
            fcontext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username));
        } else {
            fcontext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials"));
        }
    }

    public void logout() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        if (user.logout()) {
            fcontext.getApplication()
                    .getNavigationHandler()
                    .handleNavigation(fcontext, null, "index");
            fcontext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bye ", username));
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return user.isAuthenticated();
    }

    private String username;
    private String password;
}
