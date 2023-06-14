import java.util.ArrayList;


public class ClassList {

    //declare instance variables
    private ArrayList<ArrayList<Student>> studentList;
    private String classCode;


    //create constructor to be able to create a ClassList object
    public ClassList(String classCode, ArrayList<ArrayList<Student>> studentList) {
        this.classCode = classCode;
        this.studentList = studentList;
    }

    public ArrayList<ArrayList<Student>> getStudentList() {
        return studentList;
    }

    //create a method that returns the entire classroom to be presented in the GUI
    public String printClassroom() {
        String stuff = "";
        for (int a = 0; a < studentList.size(); a++){
            for (int b = 0; b < studentList.get(a).size(); b++){
                stuff += studentList.get(a).get(b).getName() + " ";
                System.out.print(studentList.get(a).get(b).getName() + " ");
            }
            stuff += "\n";
            System.out.println("");
        }
        return stuff;
    }


    //create a method that removes a Student given a row and column from the private 2d arraylist of Students
    public void removeStudent(int row, int column){
        studentList.get(row).remove(column);
    }


    //adds a student into the 2d arraylist of Students given the row and column
    public void addStudent(int row, int column, Student s){
            studentList.get(row).add(column, s);
    }


    //create a method that adds a Student into the last row of the 2d arraylist of Students
    public void addStudentInRow(Student s){
        studentList.get(studentList.size()-1).add(s);
    }


    //create a method that adds a Student to the first spot in a new row int the 2d arraylist of Students
    public void addStudentNewRow(Student s){
        studentList.add(new ArrayList<Student>());
        studentList.get(studentList.size()-1).add(s);
    }


    //create a method that returns the class average
    public double getClassAverageMark(){
        double average = 0;
        int totalStudents = 0;
        for (int i = 0; i<studentList.size(); i++){
            for (int j = 0; j<studentList.get(i).size(); j++){
                average += studentList.get(i).get(j).getAverageMarkM();
                totalStudents++;
            }
        }
        return average/totalStudents;
    }


    //create a method that returns the class median mark
    public double getMedianClassMark(){
        int count = 0;
        int counter = 0;
        for (int i = 0; i<studentList.size(); i++){
            for (int j = 0; j<studentList.get(i).size(); j++){
                count++;
            }
        }
        double total[] = new double[count];
        for (int i = 0; i<studentList.size(); i++){
            for (int j = 0; j<studentList.get(i).size(); j++){
                total[counter] = studentList.get(i).get(j).getAverageMarkM();
                counter++;
            }
        }
        if (total.length == 0){
            return -1.0;
        } else if (total.length == 1) {
            return total[0];
        } else if(total.length%2==1){
            return total[total.length/2];
        }else {
            return (total[total.length/2]+(total[(total.length/2-1)]))/2.0;
        }
    }


    //create a method that returns a Student object based on the student's name
    public Student getStudent(String studentName){
        for (int a = 0; a < studentList.size(); a++){
            for (int b = 0; b < studentList.get(a).size(); b++){
                if (studentList.get(a).get(b).getName().equals(studentName.toLowerCase())){
                    return studentList.get(a).get(b);
                }
            }
        }
        return null;
    }


    //create a method that returns the class code of the CLassList
    public String getClassCode(){
        return classCode;
    }

} //end of ClassList class