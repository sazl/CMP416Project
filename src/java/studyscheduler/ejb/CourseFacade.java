package studyscheduler.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import studyscheduler.entity.Course;
import studyscheduler.entity.Event;

@Stateless
public class CourseFacade extends AbstractFacade<Course> {
    
    @PersistenceContext(unitName = "StudySchedulerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CourseFacade() {
        super(Course.class);
    }
    
    public List<Course> getCourses() {
        Query q = em.createNamedQuery("Course.findAll");
        List<Course> res = q.getResultList();
        return res;
    }
    
    public void setCourses(List<Course> courses) {
    }
    
    public void editCourse(Course course, Event event) {
        Course c = em.merge(course);
        System.out.println(c.getPriority());
        em.refresh(c);
    }
}
