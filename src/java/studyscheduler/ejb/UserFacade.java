package studyscheduler.ejb;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import studyscheduler.entity.User;

@Startup
@Singleton
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "StudySchedulerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public boolean authenticate(String username, String password) {
        User user = (User) em.createNamedQuery("User.findByUsername")
                .setParameter("username", username)
                .getSingleResult();
        if (user != null && user.getPassword().equals(password)) {
            this.user = user;
            this.isAuthenticated = true;
            return true;
        }

        this.user = null;
        this.isAuthenticated = false;
        return false;
    }

    public boolean logout() {
        if (this.isAuthenticated) {
            this.user = null;
            this.username = null;
            this.isAuthenticated = false;
            return true;
        }
        return false;
    }

    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    public User getUser() {
        return this.user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private boolean isAuthenticated;
    private String username;
    private User user;
}
