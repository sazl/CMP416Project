package studyscheduler.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import studyscheduler.ejb.CourseFacade;
import studyscheduler.ejb.UserFacade;
import studyscheduler.entity.Course;
import studyscheduler.entity.Event;

@ViewScoped
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
        this.addedCourse = new Course();
        this.addedCourse.setGpa(new Float(0.0));
        this.addedCourse.setName("");
        this.addedCourse.setPriority(new Float(0.0));
        this.addedCourseEvent = new Event();
        this.addedCourseEvent.setId(27);
        this.addedCourseEvent.setStartdate(new Date());
        this.addedCourseEvent.setEnddate(new Date(2015, 10, 10));

    }

    public List<Course> getCourses() {
        return user.getUser().getCoursesList();
    }

    public void setCourses(List<Course> courses) {
        user.getUser().setCoursesList(courses);
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

    public void addCourse(ActionEvent ev) {
        addedCourseEvent.setDescription(addedCourse.getName());
        addedCourseEvent.setName(addedCourse.getName());
        user.addCourse(addedCourse, addedCourseEvent);
        RequestContext.getCurrentInstance().execute("PF('courseAddDialog').hide();");
    }

    public void deleteSelectedCourse(ActionEvent e) {
        FacesContext context = FacesContext.getCurrentInstance();
        Course selectedCourse = getSelectedCourse();
        if (selectedCourse != null) {
            user.deleteCourse(getSelectedCourse());
            context.addMessage(null, new FacesMessage("Deleted Course"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Deleting Course", "Database Error"));
        }
    }

    public Course getSelectedCourse() {
        return (Course) courseDataTable.getSelection();
    }

    public Event getSelectedCourseEvent() {
        Course selectedCourse = getSelectedCourse();
        if (selectedCourse == null) {
            return null;
        }
        if (selectedCourse.getEventsList().size() > 0) {
            return selectedCourse.getEventsList().get(0);
        }
        return null;
    }

    public Course getEditedCourse() {
        return editedCourse;
    }

    public void setEditedCourse(Course editedCourse) {
        this.editedCourse = editedCourse;
    }

    public Event getEditedCourseEvent() {
        return editedCourseEvent;
    }

    public void setEditedCourseEvent(Event editedCourseEvent) {
        this.editedCourseEvent = editedCourseEvent;
    }

    public void prepareEdit(ActionEvent e) {
        FacesContext context = FacesContext.getCurrentInstance();
        Course selectedCourse = getSelectedCourse();
        if (selectedCourse != null) {
            setEditedCourse(selectedCourse);
            setEditedCourseEvent(getSelectedCourseEvent());
            RequestContext.getCurrentInstance().execute("PF('courseEditDialog').show();");
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Editing Course", "No course selected"));
        }
    }

    public void editCourse(ActionEvent e) {
        course.editCourse(editedCourse, editedCourseEvent);
        System.out.println(editedCourse + " " + editedCourseEvent);
        RequestContext.getCurrentInstance().execute("PF('courseEditDialog').hide();");
    }

    public DataTable getCourseDataTable() {
        return courseDataTable;
    }

    public void setCourseDataTable(DataTable courseDataTable) {
        this.courseDataTable = courseDataTable;
    }

    public HtmlPanelGrid getAddDialog() {
        return addDialog;
    }

    public void setAddDialog(HtmlPanelGrid addDialog) {
        this.addDialog = addDialog;
    }


    private Course editedCourse;
    private Event editedCourseEvent;

    private Course addedCourse;
    private Event addedCourseEvent;

    private HtmlPanelGrid addDialog;
    private DataTable courseDataTable;
}
