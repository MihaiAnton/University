package AppSim;

import Domain.IdEntity;

public class Student implements IdEntity<String> {

    private String id, name, mail, teacher;
    private int group;

    public Student(String id, String name, String mail, String teacher, int group) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.teacher = teacher;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
