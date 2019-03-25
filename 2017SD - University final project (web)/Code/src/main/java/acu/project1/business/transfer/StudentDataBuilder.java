package acu.project1.business.transfer;

public class StudentDataBuilder {

    private StudentData student;

    public StudentDataBuilder() {
        student = new StudentData();
    }

    public StudentDataBuilder setId(Long id) {
        student.setId(id);
        return this;
    }

    public StudentDataBuilder setName(String name) {
        student.setName(name);
        return this;
    }

    public StudentDataBuilder setUsername(String username) {
        student.setUsername(username);
        return this;
    }

    public StudentData build() {
        return student;
    }
}
