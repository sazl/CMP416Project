package studyscheduler.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ExamPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSEID")
    private int courseid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EVENTID")
    private int eventid;

    public ExamPK() {
    }

    public ExamPK(int courseid, int eventid) {
        this.courseid = courseid;
        this.eventid = eventid;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) courseid;
        hash += (int) eventid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamPK)) {
            return false;
        }
        ExamPK other = (ExamPK) object;
        if (this.courseid != other.courseid) {
            return false;
        }
        if (this.eventid != other.eventid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ExamsPK[ courseid=" + courseid + ", eventid=" + eventid + " ]";
    }
    
}
