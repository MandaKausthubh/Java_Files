import java.util.*;

// Student Class
class Student {
    private int studentID;
    private static int currentStudentID = 1;
    private String studentName;
    private int age;
    private int grade;

    public int getStudentID() {
        return studentID;
    }

    public int getStudentAge() {
        return age;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getGrade() {
        return grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setStudentName(String name) {
        this.studentName = name;
    }

    public String displayStudentDetails() {
        return studentID + " " + studentName + " " + age + " " + grade + "\n";
    }

    public Student(String name, int age, int grade, int studID) {
        this.studentID = studID;
        this.studentName = name;
        this.age = age;
        this.grade = grade;
    }
}

class ClassRoom {
    private int classID;
    private static int currentClassID = 1;
    private Map<Integer, Student> listOfStudents;

    public int getClassID() {
        return classID;
    }

    public ClassRoom() {
        this.classID = currentClassID++;
        this.listOfStudents = new HashMap<>();
    }

    public void addStudent(String name, int age, int grade, int ID) {
        Student newStudent = new Student(name, age, grade, ID);
        listOfStudents.put(newStudent.getStudentID(), newStudent);
    }

    public void removeStudent(int studentID) {
        listOfStudents.remove(studentID);
    }

    public String displayClassDetails() {
        StringBuilder ans = new StringBuilder();
        ans.append("Classroom ").append(classID).append(" ").append(listOfStudents.size()).append("\n");
        for (Student student : listOfStudents.values()) {
            ans.append(student.displayStudentDetails());
        }
        return ans.toString();
    }
}

public class SchoolPortal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String Input1 = scanner.nextLine();
        int C = Integer.parseInt(Input1.split(" ", 2)[0]);
        int S = Integer.parseInt(Input1.split(" ", 2)[1]);
        Map<Integer, ClassRoom> listOfClassRoom = new HashMap<>();

        for (int i = 0; i < C; i++) {
            ClassRoom newClassRoom = new ClassRoom();
            listOfClassRoom.put(newClassRoom.getClassID(), newClassRoom);
        }

        for (int i = 0; i < S; i++) {
            String input = scanner.nextLine();
            int classroomNumber = Integer.parseInt(input.split(" ",5)[0]);
            int studentID =  Integer.parseInt(input.split(" ",5)[1]);
            String name = input.split(" ",5)[2];
            int age = Integer.parseInt(input.split(" ",5)[3]);
            int grade =  Integer.parseInt(input.split(" ",5)[4]);
            listOfClassRoom.get(classroomNumber).addStudent(name, age, grade, studentID);
        }

        StringBuilder ans = new StringBuilder();
        for (ClassRoom room : listOfClassRoom.values()) {
            ans.append(room.displayClassDetails());
        }
        System.out.println(ans);
    }
}
