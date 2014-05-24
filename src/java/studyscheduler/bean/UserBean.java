package studyscheduler.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import studyscheduler.ejb.UserFacade;
import studyscheduler.entity.Course;
import studyscheduler.entity.Event;
import studyscheduler.entity.User;

@ManagedBean
@RequestScoped
public class UserBean implements Serializable {

    @EJB
    private UserFacade user;

    public UserBean() {
    }

    public User getUser() {
        return user.getUser();
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
    
    public void addCourse() {
        
    }
    
    public Course addedCourse;
    public Event  addedCourseEvent;
}
