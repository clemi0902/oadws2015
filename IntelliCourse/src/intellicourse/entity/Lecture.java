package intellicourse.entity;
// Generated 08.12.2015 10:38:01 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Lecture generated by hbm2java
 */
@Entity
@Table(name="lecture"
    ,catalog="oad_db"
)
public class Lecture  implements java.io.Serializable {


     private Integer lid;
     private Room room;
     private Staff staff;
     private String name;
     private String beschreibung;
     private Integer preference;
     private Integer priority;
     private Event event;
     private Course course;
     private Set students = new HashSet(0);
     private Set curriculums = new HashSet(0);

    public Lecture() {
    }

    public Lecture(Integer lid, String name, String beschreibung) {
        this.lid = lid;
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public Lecture(Integer lid, String name, String beschreibung,Integer rid, Integer uid, Integer preference, Integer priority) {
        this.lid = lid;
        this.room = new Room(rid);
        this.staff = new Staff(uid);
        this.name = name;
        this.beschreibung = beschreibung;
        this.preference = preference;
        this.priority = priority;
    }

    
    
    
	
    public Lecture(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }
    public Lecture(Room room, Staff staff, String name, String beschreibung, Integer preference, Integer priority, Event event, Course course, Set students, Set curriculums) {
       this.room = room;
       this.staff = staff;
       this.name = name;
       this.beschreibung = beschreibung;
       this.preference = preference;
       this.priority = priority;
       this.event = event;
       this.course = course;
       this.students = students;
       this.curriculums = curriculums;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="lid", unique=true, nullable=false)
    public Integer getLid() {
        return this.lid;
    }
    
    public void setLid(Integer lid) {
        this.lid = lid;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rid")
    public Room getRoom() {
        return this.room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="uid")
    public Staff getStaff() {
        return this.staff;
    }
    
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    
    @Column(name="name", nullable=false, length=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="beschreibung", nullable=false, length=65535)
    public String getBeschreibung() {
        return this.beschreibung;
    }
    
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    
    @Column(name="preference")
    public Integer getPreference() {
        return this.preference;
    }
    
    public void setPreference(Integer preference) {
        this.preference = preference;
    }

    
    @Column(name="priority")
    public Integer getPriority() {
        return this.priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="lecture")
    public Event getEvent() {
        return this.event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="lecture")
    public Course getCourse() {
        return this.course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="student_lecture", catalog="oad_db", joinColumns = { 
        @JoinColumn(name="lid", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="uid", nullable=false, updatable=false) })
    public Set getStudents() {
        return this.students;
    }
    
    public void setStudents(Set students) {
        this.students = students;
    }

@ManyToMany(fetch=FetchType.LAZY, mappedBy="lectures")
    public Set getCurriculums() {
        return this.curriculums;
    }
    
    public void setCurriculums(Set curriculums) {
        this.curriculums = curriculums;
    }




}


