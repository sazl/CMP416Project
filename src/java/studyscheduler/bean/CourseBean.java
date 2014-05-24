package studyscheduler.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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

@SessionScoped
@ManagedBean(name = "courseBean")
public class CourseBean implements Serializable {

    @EJB
    private UserFacade user;
    @EJB
    private CourseFacade course;

    public CourseBean() {
    }
    
    @PostConstruct
    public void initialize() {
        this.cachedCourses = course.getCourses();
    }

    public List<Course> getCourses() {
        return cachedCourses;
    }

    public void setCourses(List<Course> courses) {
        this.cachedCourses = courses;
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
    
    private List<Course> cachedCourses;
    
    private Course addedCourse;
    private Event addedCourseEvent;

    private Course selectedCourse;
    private Event selectedCourseEvent;
}
