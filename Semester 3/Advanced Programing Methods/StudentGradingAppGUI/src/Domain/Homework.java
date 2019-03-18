package Domain;



public class Homework implements IdEntity<Integer>{

    /**
     *  Homework class
     */

    private int id;
    private String description;
    private int targetWeek;
    private int deadlineWeek;
    private int assignmentWeek;
    private double grade;

    public Homework(int id, String description, int targetWeek, int deadlineWeek, int assignmentWeek){
        this.id = id;
        this.description = description;
        this.targetWeek = targetWeek;
        this.deadlineWeek = deadlineWeek;
        this.assignmentWeek = assignmentWeek;
        this.grade = 10;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTargetWeek() {
        return targetWeek;
    }

    public void setTargetWeek(int targetWeek) {
        this.targetWeek = targetWeek;
    }

    public int getDeadlineWeek() {
        return deadlineWeek;
    }

    public void setDeadlineWeek(int deadlineWeek) {
        this.deadlineWeek = deadlineWeek;
    }

    public int getAssignmentWeek() {
        return assignmentWeek;
    }

    public void setAssignmentWeek(int assignmentWeek) {
        this.assignmentWeek = assignmentWeek;
    }

    /**
     *  Computes the grade according to the rules
     */
    private double computeGrade(){
        double newGrade = this.grade;
        int delay = this.getAssignmentWeek() - this.getTargetWeek();
        if(delay >= 3)
            newGrade = 1;
        else {
            newGrade = this.grade - (2.5 * delay);
        }

        if(newGrade <= 1){
            newGrade = 1;
        }

        return newGrade;

    }

    /**
     *
     * @return grade
     */
    public double getGrade() {
        return this.computeGrade();
    }

    /**
     * Sets the grade
     * @param grade
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }


    /**
     *
     * @return the homework string
     */
    @Override
    public String toString(){
       return "Homework " + getId() + ": " + getDescription() + ", Target week: " + getTargetWeek() + ", Deadline week: " + getDeadlineWeek();
    }










}
