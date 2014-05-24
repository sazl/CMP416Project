package studyscheduler.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private boolean loggedIn;

    public LoginBean() {
    }

    public void login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;

        if (username != null && username.equals("admin") && password != null && password.equals("admin")) {
            loggedIn = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");
        }

        FacesContext fcontext = FacesContext.getCurrentInstance();
        fcontext.addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
    }
    
    public void logout() {
        System.out.println("!!!!!LOGOUT");
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
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
