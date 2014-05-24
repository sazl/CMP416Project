package studyscheduler.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EXAMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findByCourseid", query = "SELECT e FROM Exam e WHERE e.examsPK.courseid = :courseid"),
    @NamedQuery(name = "Exam.findByEventid", query = "SELECT e FROM Exam e WHERE e.examsPK.eventid = :eventid"),
    @NamedQuery(name = "Exam.findByWeight", query = "SELECT e FROM Exam e WHERE e.weight = :weight"),
    @NamedQuery(name = "Exam.findByGrade", query = "SELECT e FROM Exam e WHERE e.grade = :grade")})
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExamPK examsPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WEIGHT")
    private Float weight;
    @Column(name = "GRADE")
    private Float grade;
    @JoinColumn(name = "EVENTID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Event events;
    @JoinColumn(name = "COURSEID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course courses;

    public Exam() {
    }

    public Exam(ExamPK examsPK) {
        this.examsPK = examsPK;
    }

    public Exam(int courseid, int eventid) {
        this.examsPK = new ExamPK(courseid, eventid);
    }

    public ExamPK getExamsPK() {
        return examsPK;
    }

    public void setExamsPK(ExamPK examsPK) {
        this.examsPK = examsPK;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Event getEvents() {
        return events;
    }

    public void setEvents(Event events) {
        this.events = events;
    }

    public Course getCourses() {
        return courses;
    }
    
    public void setCourses(Course courses) {
        this.courses = courses;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examsPK != null ? examsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.examsPK == null && other.examsPK != null) || (this.examsPK != null && !this.examsPK.equals(other.examsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Exams[ examsPK=" + examsPK + " ]";
    }
    
}
