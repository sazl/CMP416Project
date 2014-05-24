package studyscheduler.bean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import studyscheduler.ejb.CourseFacade;
import studyscheduler.ejb.UserFacade;
import studyscheduler.entity.Course;
import studyscheduler.entity.Event;

@ManagedBean(name = "courseBean")
@RequestScoped
public class CourseBean {

    @EJB
    private UserFacade user;
    @EJB
    private CourseFacade course;

    public CourseBean() {
        this.addedCourse = new Course();
        this.addedCourseEvent = new Event();
        this.selectedCourse = new Course();
        this.selectedCourseEvent = new Event();
    }

    public List<Course> getCourses() {
        return course.getCourses();
    }

    public void setCourses(List<Course> courses) {

    }
    
    public Course getAddedCourse() {
        return addedCourse;
    }

    public void setAddedCourse(Course addedCourse) {
        this.addedCourse = addedCourse;
    }

    public Event getAddedCourseEvent() {
        return addedCourseEvent;
    }

    public void setAddedCourseEvent(Event addedCourseEvent) {
        this.addedCourseEvent = addedCourseEvent;
    }
    
    public void addCourse(ActionEvent e) {
        addedCourse.getEventsList().add(addedCourseEvent);
        user.addCourse(addedCourse);
    }
    
    public void deleteSelectedCourse(ActionEvent e) {
        System.out.println("!!!!!!!!!!!!!!! " + selectedCourse.getName());
        user.deleteCourse(selectedCourse);
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public Event getSelectedCourseEvent() {
        return selectedCourseEvent;
    }

    public void setSelectedCourseEvent(Event selectedCourseEvent) {
        this.selectedCourseEvent = selectedCourseEvent;
    }
    
    public void editCourse() {
      
    }
    
    public Course addedCourse;
    public Event  addedCourseEvent;
    
    public Course selectedCourse;
    public Event  selectedCourseEvent;
}
