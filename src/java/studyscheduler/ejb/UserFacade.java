package studyscheduler.ejb;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import studyscheduler.entity.Course;
import studyscheduler.entity.Event;
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
    
    public void addCourse(Course course, Event event) {

        /*
        course.setEventsList(new ArrayList<Event>());
        em.persist(event);
        em.flush();
        course.getEventsList().add(event);
        u.getCoursesList().add(course);
        */
        em.persist(course);
        course.setUserid(getUser());
        em.flush();
    }
    
    public void deleteCourse(Course course) {
        Course c = em.merge(course);
        em.remove(c);
    }
}
