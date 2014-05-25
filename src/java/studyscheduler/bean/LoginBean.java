package studyscheduler.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import studyscheduler.ejb.UserFacade;
import studyscheduler.entity.User;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private UserFacade user;

    public LoginBean() {
    }

    @PostConstruct
    public void initialize() {
        this.registerUser = new User();
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

    public User getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(User registerUser) {
        this.registerUser = registerUser;
    }

    public String getRegisterConfirmPassword() {
        return registerConfirmPassword;
    }

    public void setRegisterConfirmPassword(String registerConfirmPassword) {
        this.registerConfirmPassword = registerConfirmPassword;
    }

    public void register() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        try {
            if (!registerUser.getPassword().equals(this.registerConfirmPassword)) {
                fcontext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Passwords Don't Match", ""));
            } else {
                user.create(registerUser);
                fcontext.getApplication()
                        .getNavigationHandler()
                        .handleNavigation(fcontext, null, "index");
            }
        } catch (Exception e) {
            fcontext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Registration Error", e.getMessage()));
        }
    }

    private String username;
    private String password;

    private User registerUser;
    private String registerConfirmPassword;
}
