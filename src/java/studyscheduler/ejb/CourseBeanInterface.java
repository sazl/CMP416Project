package studyscheduler.ejb;

import java.util.List;
import javax.ejb.Local;
import studyscheduler.entity.Course;

@Local
public interface CourseBeanInterface {
    public List<Course> getCourses();
}
