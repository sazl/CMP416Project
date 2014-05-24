package studyscheduler.bean;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import studyscheduler.ejb.CourseFacade;
import studyscheduler.entity.Course;

@ManagedBean(name = "courseBean")
@RequestScoped
public class CourseBean {

    @EJB
    private CourseFacade course;

    public CourseBean() {
    }

    public List<Course> getCourses() {
        return course.getCourses();
    }

    public void setCourses(List<Course> courses) {

    }

    public void deleteSelectedCourse() {
        
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
    
    public Course selectedCourse;
}
