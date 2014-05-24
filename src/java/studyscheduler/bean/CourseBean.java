package studyscheduler.bean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import studyscheduler.ejb.CourseFacade;
import studyscheduler.ejb.UserFacade;
import studyscheduler.entity.Course;
import studyscheduler.entity.Event;

@ManagedBean(name = "courseBean")
@SessionScoped
public class CourseBean implements Serializable {

    @EJB
    private UserFacade user;
    @EJB
    private CourseFacade course;

    public CourseBean() {
        this.addedCourse = new Course();
        this.addedCourseEvent = new Event();
        this.selectedCourse = new Course();

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
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.print("!!!!!!!!!!!" + selectedCourse);
        if (selectedCourse != null) {
            user.deleteCourse(selectedCourse);
            context.addMessage(null, new FacesMessage("Deleted Course"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Deleting Course", "Database Error"));

        }
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

    public void editCourse(ActionEvent e) {

    }

    public Course addedCourse;
    public Event addedCourseEvent;

    public Course selectedCourse;
    public Event selectedCourseEvent;
}
