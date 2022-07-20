package net.javaguides.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_name")
   private String projectName;
    @Column(name ="quantity" )
    private int quantity;
    @Column(name = "javateam")
    private String javateam;
    @Column(name = "state")
    private String state;

    public Project() {
    }


    public long getid() {
        return id;
    }

    public void setid(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getJavateam() {
        return javateam;
    }

    public void setJavateam(String javateam) {
        this.javateam = javateam;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
