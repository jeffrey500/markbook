import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientNewTest {

    ClientNew client;

    @Test
    void testAddStudentInrowFirst() {
        String[] inputList = new String[]{
                "firstClassRoom",   // newClass - first classroom
                "dan",              // newClass - first student name
                "90 65 87 56",      // newClass - marks of first student
                "jeff",             // addStudentInRow - student name
                "100 79",           // addStudentInRow - marks of the student
                "sam",              // addStudentNewRow - student name
                "67 78",            // addStudentNewRow - marks of the student
                "firstClassRoom",   // findExitingClass - find class code
                "sam",              // doAccessStudentStatistics - name of student
        };
        int[] optionList = new int[]{
                0,   // selectNewStudents - 0: Add student in row, 1: Move to next row, 2: finish class
                1,   // selectNewStudents - 0: Add student in row, 1: Move to next row, 2: finish class
                2,   // selectNewStudents - 0: Add student in row, 1: Move to next row, 2: finish class
                1,   // selectNextSteps - 0: Create new classList, 1: Access existing ClassList
                0,   // selectExitingStudents - 0: Modify/Access student grades, 1: class statistics, 2: Add/remove students
                0,   // StudentModifyOrAccess - 0: access a student statistic, 1: Modify a grade from a student
                0,   // doAccessStudentStatistics - 0: get student average, 1: get student median, 2: show all grades
                0,   // selectNextSteps - 0: Create new classList, 1: Access existing ClassList
        };

        TextUI ui = new TextUI(inputList, optionList);
        client = new ClientNew(ui);
        client.InputLoop();
        ArrayList<ClassList> listOfCLasses = client.getListOfCLasses();

        assertEquals(1, listOfCLasses.size());
        assertEquals(2, listOfCLasses.get(0).getStudentList().size());
        assertEquals(2, listOfCLasses.get(0).getStudentList().get(0).size());
        assertEquals(1, listOfCLasses.get(0).getStudentList().get(1).size());

        assertEquals(1, ui.getOutputList().size());
        assertEquals("sam has an average of 72.5%", ui.getOutputList().get(0));
    }

    @Test
    void testAddStudentNewrowFirst() {
        String[] inputList = new String[]{
                "firstClassRoom",   // newClass - first classroom
                "dan",              // newClass - first student name
                "90 65 87 56",      // newClass - marks of first student
                "jeff",             // addStudentInRow - student name
                "100 79",           // addStudentInRow - marks of the student
                "sam",              // addStudentNewRow - student name
                "67 78",            // addStudentNewRow - marks of the student
                "firstClassRoom",   // findExitingClass - find class code
                "sam",              // doAccessStudentStatistics - name of student
        };
        int[] optionList = new int[]{
                1,   // selectNewStudents - 0: Add student in row, 1: Move to next row, 2: finish class
                0,   // selectNewStudents - 0: Add student in row, 1: Move to next row, 2: finish class
                2,   // selectNewStudents - 0: Add student in row, 1: Move to next row, 2: finish class
                1,   // selectNextSteps - 0: Create new classList, 1: Access existing ClassList
                0,   // selectExitingStudents - 0: Modify/Access student grades, 1: class statistics, 2: Add/remove students
                0,   // StudentModifyOrAccess - 0: access a student statistic, 1: Modify a grade from a student
                0,   // doAccessStudentStatistics - 0: get student average, 1: get student median, 2: show all grades
                0,   // selectNextSteps - 0: Create new classList, 1: Access existing ClassList
        };

        TextUI ui = new TextUI(inputList, optionList);
        client = new ClientNew(ui);
        client.InputLoop();
        ArrayList<ClassList> listOfCLasses = client.getListOfCLasses();

        assertEquals(1, listOfCLasses.size());
        assertEquals(2, listOfCLasses.get(0).getStudentList().size());
        assertEquals(1, listOfCLasses.get(0).getStudentList().get(0).size());
        assertEquals(2, listOfCLasses.get(0).getStudentList().get(1).size());

        assertEquals(1, ui.getOutputList().size());
        assertEquals("sam has an average of 72.5%", ui.getOutputList().get(0));
    }

}

class TextUI implements UIInterface {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    String[] inputList;
    int[] optionList;

    int posInput = 0;
    int posOption = 0;

    ArrayList<String> outputList = new ArrayList<>();

    public TextUI(String[] inputList, int[] optionList) {
        this.inputList = inputList;
        this.optionList = optionList;
    }

    @Override
    public String showInputDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException {
        System.out.printf("Input: %s, return %s\n", message, ANSI_RED + inputList[posInput] + ANSI_RESET);
        return inputList[posInput++];
    }

    @Override
    public int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) throws HeadlessException {
        System.out.printf("Option: %s %s, return " + ANSI_GREEN + "%d" + ANSI_RESET + "\n",
                message,
                ANSI_GREEN_BACKGROUND+Arrays.toString(options)+ANSI_RESET,
                optionList[posOption]);
        return optionList[posOption++];
    }

    @Override
    public void showMessageDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException {
        outputList.add((String) message);
        System.out.printf("Dialog: %s\n", ANSI_PURPLE_BACKGROUND + message + ANSI_RESET);
    }

    public List<String> getOutputList() {
        return outputList;
    }
}