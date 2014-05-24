package studyscheduler.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import studyscheduler.entity.Course;
import studyscheduler.entity.User;

@Stateless
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
    
    public User getUser() {
        return em.find(User.class, 3L);
    }
    
    public List<Course> getCourses() {
        return em.createNamedQuery("User.findAll")
                .getResultList();
    }
}
