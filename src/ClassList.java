import java.util.ArrayList;


public class ClassList {
    private ArrayList<ArrayList<Student>> studentList;
    private String classCode;


    public ClassList(String classCode, ArrayList<ArrayList<Student>> studentList) {
        this.classCode = classCode;
        this.studentList = studentList;
    }


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


    public ArrayList<Double> getStudentMark(String name){
        if (getStudent(name).equals(null)){
            return new ArrayList<Double>(-1);
        }
        else return getStudent(name).getMarks();
    }


    public double getStudentMark(String name, int markEntry){
        if (getStudent(name).equals(null)){
            return -1;
        }
        else return getStudent(name).getMarks(markEntry);
    }


    public void addMarkToStudent(String name, double mark) {
        if (getStudent(name).equals(null)) {
        } else getStudent(name).addMark(mark);
    }


    public void addMarkToStudent(String name, double[] marks){
        if (getStudent(name).equals(null)) {
        } else getStudent(name).addMarks(marks);
    }


    public void removeStudent(int row, int column){
        studentList.get(row).remove(column);
    }

    public void addStudent(int row, int column, Student s){
        try {
            studentList.get(row).add(column, s);
        } catch (Exception e){
        }
    }

    public void addStudentInRow(Student s){
        studentList.get(studentList.size()-1).add(s);
    }

    public void addStudetNewRow(Student s){
        studentList.add(new ArrayList<Student>());
        studentList.get(studentList.size()-1).add(s);
    }

    public double getAverageMark(){
        double average = 0;
        int total = 0;
        for (int i = 0; i<studentList.size(); i++){
            for (int j = 0; j<studentList.get(i).size(); i++){
                average += studentList.get(i).get(j).getAverageMark();
                total++;
            }
        }
        return average/total;
    }


    public double getMedianMark(){
        int count = 0;
        int counter = 0;
        for (int i = 0; i<studentList.size(); i++){
            for (int j = 0; j<studentList.get(i).size(); i++){
                count++;
            }
        }
        double total[] = new double[count];
        for (int i = 0; i<studentList.size(); i++){
            for (int j = 0; j<studentList.get(i).size(); i++){
                total[counter] = studentList.get(i).get(j).getAverageMark();
            }
        }
        if (total.length == 0){
            return -1.0;
        } else if (total.length == 1) {
            return total[0];
        } else if(total.length%2==1){
            return total[total.length/2];
        }else {
            return (total[total.length/2]+(total[(total.length/2-1)])/2.0);
        }
    }
    public Student getStudent(String studentName){
        for (int a = 0; a < studentList.size(); a++){
            for (int b = 0; b < studentList.get(a).size(); b++){
                if (studentList.get(a).get(b).getName().equals(studentName)){
                    return studentList.get(a).get(b);
                }
            }
        }
        return null;
    }


    public String getClassCode(){
        return classCode;
    }
}
