package studyscheduler.ejb;

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
        return (User) em.createNamedQuery("User.findById")
                .setParameter("id", 3L)
                .getSingleResult();
    }
    
    public void addCourse(Course course) {
        User u = getUser();
        u.getCoursesList().add(course);
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
    }
    
    public void deleteCourse(Course course) {
        em.getTransaction().begin();
        em.remove(course);
        em.getTransaction().commit();
    }
}
