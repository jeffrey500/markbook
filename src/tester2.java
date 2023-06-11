import javax.swing.*;
        import java.util.ArrayList;

class Tester2 {
    public static void main(String[] args) {

//        double[] marks = {99.0,100.0,80.0};
//        ArrayList<ArrayList<Student>> list = new ArrayList<ArrayList<Student>>();
//        list.add(new ArrayList<Student>());
//        list.add(new ArrayList<Student>());
//        list.get(0).add(new Student("Bob", marks));
//        list.get(1).add(new Student("Joe", marks));
//        ClassList classList = new ClassList("123", list);
//
//        System.out.println(classList.getStudentMark("Bob"));
//        System.out.println(classList.getStudentMark("Joe", 1));
        String str = "12 800 2343 23";
        double[] test;
        test = idk(str);
        for (int i = 0; i < test.length; i++){
            System.out.println(test[i]);
        }


    }
    public static double[] idk(String m) {
        String marks = m;
        String[] str = marks.split(" ", m.length());
        double[] arrMarks = new double[str.length];
        for(int i = 0; i<str.length; i++) {

            arrMarks[i] = Double.valueOf(str[i]);

        }//end of for loop transferring marks to double array
        return arrMarks;
    }
}

