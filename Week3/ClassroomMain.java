class Student{
    int StudentID;
    String name;
    int age;
    String grade;

    static int index = 0;
    static Student ListOfStudents[] = new Student[100];

    //Getters:
    public int GetStudentID() {return StudentID;}
    public String GetName() {return name;}
    public int GetAge() {return age;}
    public String GetGrade() {return grade;}

    Student(String Name, String grade, int age){
        this.age = age;
        this.name = Name;
        this.grade = grade;
        this.StudentID = index + 1000;
        ListOfStudents[index++] = this;
    }

    public void PrintDetails() {
        System.out.println(name + "\t" + StudentID + "\t" + age + "\t" + grade);
    }
}

class ClassRoom{
    int ClassID;
    int ListOfStudentsinClass[] = new int[100];
    int CurrentIndex = 0;

    static int CurrentID = 1;

    ClassRoom(){
        this.ClassID = 1000 + CurrentID++;
    }

    public void AddStudent(String StudentName, String Grade, int age) {
        Student NewStudent = new Student(StudentName, Grade, age);
        if(CurrentIndex < 100){
            ListOfStudentsinClass[CurrentIndex++] = Student.index - 1;
            System.out.println("New Student Added to Class, " + ClassID + ":");
            NewStudent.PrintDetails();
            System.out.println();
        }
    }

    public void RemoveStudent(int ID){
        for(int i = 0; i < CurrentIndex; i++){
            if(Student.ListOfStudents[ListOfStudentsinClass[i]].StudentID == ID){
                System.out.println("Deleting Student: ");
                Student.ListOfStudents[ListOfStudentsinClass[i]].PrintDetails();
                ListOfStudentsinClass[i] = -1;
                System.out.println();
            }
        }
    }

    public void DisplayClassRoom(){
        for(int i = 0; i < CurrentIndex; i++){
            if(ListOfStudentsinClass[i] > -1) Student.ListOfStudents[ListOfStudentsinClass[i]].PrintDetails();
        }
        System.out.println();
    }
}


class ClassroomMain{
    public static void main(String args[]){
        ClassRoom ClassRoom1 = new ClassRoom();
        ClassRoom1.AddStudent("Jinesh Pagaria", "A", 19);
        ClassRoom1.AddStudent("Aayush Gupta", "A-", 19);
        ClassRoom1.AddStudent("Aaryan Mishra", "B", 18);

        ClassRoom1.DisplayClassRoom();
        ClassRoom1.RemoveStudent(1001);
        ClassRoom1.DisplayClassRoom();
    }
}



