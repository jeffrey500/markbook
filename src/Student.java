import java.util.ArrayList;


//Create a Student class
public class Student {


    //Private instance variables
    private String studentName;
    private ArrayList<Double> marks = new ArrayList<Double>();


    //Student constructor that assigns instance variable studentName to the input and assigns all elements in the passed
    //Double array to the private instance array list
    public Student(String studentName, double[] marks) {
        this.studentName = studentName.toLowerCase();

        for (int i = 0; i < marks.length; i++) {
            this.marks.add(makeValid(marks[i]));
        }
    }


    //Method to return student name
    public String getName() {
        return studentName;
    }


    //Method to return the students marks
    public ArrayList<Double> getMarks() {
        return marks;
    }


    //Add mark to the marks array list
    public void addMark(double mark) {
        marks.add(makeValid(mark));
    }

    //method to add a list of double values to the private array list
    public void addMarks(double[] marks) {
        for (int i = 0; i < marks.length; i++) {
            this.marks.add(makeValid(marks[i]));
        }
    }


    public void removeMark(int index){
        marks.remove(index-1);
    }


    //method to get the average mark of the student
    public double getAverageMarkM() {
        double total = 0;
        for (int i = 0; i < marks.size(); i++) {
            total += marks.get(i);
        }
        return ((int) (total/marks.size() * 10))/10.0;
    }


    //Method to turn negative marks to zero and marks over 100 to 100
    private double makeValid(double check){
        if (check<0){
            return 0;
        } else if (check>100) {
            return 100;
        }else {
            return check;
        }
    }


    //Method to get the class median and return a double
    public double getMedian() {
        if (marks.size() == 0){
            return -1.0;
        } else if (marks.size() == 1) {
            return marks.get(0);
        } else if(marks.size()%2==1){
            return marks.get(marks.size()/2);
        }else {
            return ((int) ((marks.get(marks.size()/2))+(marks.get(marks.size()/2-1)))/2.0 * 10)/10.0;
        }
    }
}//end of Student Class