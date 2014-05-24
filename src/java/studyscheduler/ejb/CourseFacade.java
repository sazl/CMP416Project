package studyscheduler.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import studyscheduler.entity.Course;

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
        Query q = em.createNamedQuery("Course.All");
        List<Course> res = q.getResultList();
        return res;
    }
}
