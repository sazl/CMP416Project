package studyscheduler.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
        return (User) em.createNamedQuery("User.findById")
                .setParameter("id", 3L)
                .getSingleResult();
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void addCourse(Course course) {
        User u = getUser();
        u.getCoursesList().add(course);
        em.flush();
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void deleteCourse(Course course) {
        em.remove(course);
        em.flush();
    }
}
