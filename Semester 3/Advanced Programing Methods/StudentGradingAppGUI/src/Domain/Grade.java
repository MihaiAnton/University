package Domain;

public class Grade implements IdEntity<Integer>{

    private String studentId;
    private int homeworkId;
    private int id;
    private double grade;

    private int studGroup;
    private String studTeacher;
    private String studName;


    public Grade(String studentId, int homeworkId, int id, double grade){
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.id = id;
        this.grade = grade;
    }

    public String getStudId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public void setId(Integer integer) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }


    public int getStudGroup() {
        return studGroup;
    }

    public void setStudGroup(int studGroup) {
        this.studGroup = studGroup;
    }

    public String getStudTeacher() {
        return studTeacher;
    }

    public void setStudTeacher(String studTeacher) {
        this.studTeacher = studTeacher;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }


}
