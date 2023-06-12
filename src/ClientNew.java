//import javax.swing.*;
//
//public class ClientNew {
//    public static void main(String[] args) {
//        NewClassResult result = newClass();
//
//    }
//
//    private static NewClassResult newClass() {
//
//        String classCode = JOptionPane.showInputDialog(null,
//                "Let's create your first classroom!\nWhat is the class code?", "Markbock", 3);
//        String firstStudent = JOptionPane.showInputDialog(null,
//                "Let's move on to adding students!\nWhat is the first name of your first student in you first row?", "Markbock", 3);
//        String firstStudentMarksSTR = JOptionPane.showInputDialog(null,
//                "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100");
//        double[] firstStudentsMarks = idk(firstStudentMarksSTR);
//
//        return new NewClassResult(classCode, firstStudent, firstStudentsMarks);
//    }
//
//
//    public static double[] idk(String m) {
//        String marks = m;
//        String[] str = marks.split(" ", m.length());
//        double[] arrMarks = new double[str.length];
//        for (int i = 0; i < str.length; i++) {
//            arrMarks[i] = Double.valueOf(str[i]);
//        }//end of for loop transferring marks to double array
//        return arrMarks;
//    }
//
//    class NewClassResult {
//        String classCode;
//        String firstStudent;
//        String firstStudentMarksSTR;
//    }
//
//}
