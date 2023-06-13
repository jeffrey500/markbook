import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClientNew {
    ArrayList<ClassList> listOfCLasses = new ArrayList<ClassList>();
    UIInterface ui;

    public ClientNew(UIInterface ui) {
        this.ui = ui;
    }

    public static void main(String[] args) {
        UIInterface swingUI = new JOptionPaneUI();
        ClientNew clientNew = new ClientNew(swingUI);

        while (true) {
            clientNew.InputLoop();
        }
    }

    public ArrayList<ClassList> getListOfCLasses() {
        return listOfCLasses;
    }

    public void InputLoop() {

        String currClassCode = newClass();

        int iChoice = selectNewStudents();
        while (iChoice != 2) {
            switch (iChoice) {
                case 0:
                    addStudentInRow(currClassCode);
                    break;
                case 1:
                    addStudentNewRow(currClassCode);
                    break;
            }

            iChoice = selectNewStudents();
        }

        iChoice = selectNextSteps();
        while (iChoice != 0) {
            String inputClassCode = findExitingClass();

            if (inputClassCode != null) {
                int iSelect = selectExitingStudents();
                switch (iSelect) {
                    case 0:
                        StudentModifyOrAccess(inputClassCode);
                        break;
                    case 1:
                        StudentStatistics(inputClassCode);
                        break;
                    case 2:
                        StudentAddOrModify(inputClassCode);
                        break;
                }
            }

            iChoice = selectNextSteps();
        }
    }

    private void StudentModifyOrAccess(String inputClassCode) {
        String[] modifyOrAccessGrades = {"access a student statistic", "Modify a grade from a student"};
        var modifyOrAccessGradesMenu = ui.showOptionDialog(null, "What would you like to do?",
                "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modifyOrAccessGrades, modifyOrAccessGrades[0]);

        if (modifyOrAccessGradesMenu == 0) {
            doAccessStudentStatistics(inputClassCode);

        } else {
            doModifyStudentGrade(inputClassCode);
        }
    }

    private void StudentStatistics(String inputClassCode) {
        String[] classStats = {"display class average", "display class median"};
        var classStatsMenu = ui.showOptionDialog(null, "What would you like to do?",
                "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, classStats, classStats[0]);

        if (classStatsMenu == 0) {
            uiShowMessage("The class average mark is " + findClassList(inputClassCode).getAverageMark());

        } else {
            uiShowMessage("The class Median mark is " + findClassList(inputClassCode).getMedianMark());
        }
    }

    private void StudentAddOrModify(String inputClassCode) {
        String[] addAndRemove = {"Add a student", "remove a student"};
        var addAndRemoveMenu = ui.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, addAndRemove, addAndRemove[0]);

        if (addAndRemoveMenu == 0) {
            String addStudent = ui.showInputDialog(null,
                    "Where would you like to add the student type row then column\nMake sure that the row exists!\n" + findClassList(inputClassCode).printClassroom(), "Markbock", 3);
            try {
                int space = addStudent.indexOf(" ");
                int row = Integer.valueOf(addStudent.substring(0, space));
                int column = Integer.valueOf(addStudent.substring(space + 1));
                String studName = ui.showInputDialog(null,
                        "What is the first name of your first student in the row?", "Markbock", 3);
                String studMarks = ui.showInputDialog(null,
                        "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100", "Markbock", 3);
                findClassList(inputClassCode).addStudent(row, column, new Student(studName, idk(studMarks)));
            } catch (Exception e) {
                uiShowMessage("You did not type a valid input");
            }

        } else {
            String removeStudent = ui.showInputDialog(null,
                    "Which student would you like to remove?\nType row followed by a space and then the column\n" + findClassList(inputClassCode).printClassroom(), "Markbock", 3);
            try {
                int space = removeStudent.indexOf(" ");
                int row = Integer.valueOf(removeStudent.substring(0, space));
                int column = Integer.valueOf(removeStudent.substring(space + 1));
                findClassList(inputClassCode).removeStudent(row, column);
            } catch (Exception e) {
                uiShowMessage("You did not type a valid input");
            }
        }
    }

    private int selectExitingStudents() {
        String[] classMenuButtons = {"Modify/Access student grades", "class statistics", "Add/remove students"};
        var classMenu = ui.showOptionDialog(null, "What would you like to do?",
                "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, classMenuButtons, classMenuButtons[0]);

        return classMenu;
    }

    private String findExitingClass() {
        String mainMenuClasses = "Type the class code of the class you want to access\nAvailable class codes:";
        for (int i = 0; i < listOfCLasses.size(); i++) {
            mainMenuClasses += "\n";
            mainMenuClasses += listOfCLasses.get(i).getClassCode();
        }

        String inputClassCode = ui.showInputDialog(null, mainMenuClasses, "Markbock", 3);

        ClassList classList = findClassList(inputClassCode);
        if (classList == null) {
            uiShowMessage("That class code does not exist!");
            return null;
        }

        return classList.getClassCode();
    }

    private void uiShowMessage(String text) {
        ui.showMessageDialog(null, text, "Markbock", 3);
    }

    private int selectNextSteps() {
        String[] menuButtons = {"Create new classList", "Access existing ClassList"};
        var yesOrNo = ui.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menuButtons, menuButtons[0]);

        return yesOrNo;
    }

    private void doAccessStudentStatistics(String inputClassCode) {
        String[] studentStatistic = {"get student average", "get student median", "show all grades"};
        var studentStatisticMenu = ui.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, studentStatistic, studentStatistic[0]);


        if (studentStatisticMenu == 0) {
            String inputStudent = ui.showInputDialog(null, "Type the name of the student", "Markbock", 3);


            uiShowMessage(inputStudent + " has an average of " + findStudent(inputStudent, findClassList(inputClassCode)).getAverageMark() + "%");


        } else if (studentStatisticMenu == 1) {
            String inputStudent = ui.showInputDialog(null, "Type the name of the student", "Markbock", 3);
            uiShowMessage(inputStudent + " has a median " + findStudent(inputStudent, findClassList(inputClassCode)).getMedian());


        } else {
            String inputStudent = ui.showInputDialog(null, "Type the name of the student", "Markbock", 3);
            String output = inputStudent + "'s marks:";
            ArrayList<Double> temp = new ArrayList<Double>();
            temp = findClassList(inputClassCode).getStudent(inputStudent).getMarks();
            for (int i = 0; i < findStudent(inputStudent, findClassList(inputClassCode)).getMarks().size(); i++) {
                output += "\n";
                output += temp.get(i);
                output += "%";
            }
            uiShowMessage(output);
        }
    }

    private void doModifyStudentGrade(String inputClassCode) {
        String[] modifyGrades = {"add a grade", "remove a grade"};
        var modifyGradesMenu = ui.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modifyGrades, modifyGrades[0]);

        if (modifyGradesMenu == 0) {
            String inputStudent = ui.showInputDialog(null, "Type the name of the student", "Markbock", 3);
            String gradeInput = ui.showInputDialog(null,
                    "What percentage grade would you like to add\ndeciamls allowed only numbers!", "Markbock", 3);

            try {
                findStudent(inputStudent, findClassList(inputClassCode)).addMark(Double.valueOf(gradeInput));
                uiShowMessage("Grade has been added!");
            } catch (Exception e) {
                uiShowMessage("You did not type a valid input");
            }

        } else {
            String inputStudent = ui.showInputDialog(null, "Type the name of the student", "Markbock", 3);
            String output = inputStudent + "'s marks:";
            ArrayList<Double> temp = new ArrayList<Double>();
            temp = findClassList(inputClassCode).getStudent(inputStudent).getMarks();

            for (int i = 0; i < findStudent(inputStudent, findClassList(inputClassCode)).getMarks().size(); i++) {
                output += "\n";
                output += temp.get(i);
                output += "%";
            }

            output += "\nWhich mark would you like to remove (type the index number starting from 1)";
            String removeMark = ui.showInputDialog(null, output, "Markbock", 3);
            try {
                findClassList(inputClassCode).getStudent(inputStudent).removeMark(Integer.valueOf(removeMark));
                uiShowMessage("That mark has been removed!");
            } catch (Exception e) {
                uiShowMessage("You did not type a valid input");
            }
        }
    }

    private Student findStudent(String studentName, ClassList c) {
        if (c.getStudent(studentName) != null) {
            return c.getStudent(studentName);
        }
        return null;
    }

    private void addStudentNewRow(String classCode) {
        String studName = ui.showInputDialog(null, "What is the first name of the student", "Markbock", 3);
        String studMarks = ui.showInputDialog(null,
                "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100", "Markbock", 3);
        findClassList(classCode).addStudetNewRow(new Student(studName, idk(studMarks)));
    }

    private void addStudentInRow(String classCode) {
        String studName = ui.showInputDialog(null, "What is the first name of your first student in the row?", "Markbock", 3);
        String studMarks = ui.showInputDialog(null,
                "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100", "Markbock", 3);
        findClassList(classCode).addStudentInRow(new Student(studName, idk(studMarks)));
    }

    private int selectNewStudents() {
        String[] buttons = {"Add student in row", "Move to next row", "finish class"};
        var yesOrNo = ui.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);
        return yesOrNo;
    }

    private ClassList findClassList(String classCode) {
        for (int i = 0; i < listOfCLasses.size(); i++) {
            if (listOfCLasses.get(i).getClassCode().equals(classCode)) {
                return listOfCLasses.get(i);
            }
        }
        return null;
    }

    private String newClass() {

        String classCode = ui.showInputDialog(null,
                "Let's create your first classroom!\nWhat is the class code?", "Markbock", 3);
        String firstStudent = ui.showInputDialog(null,
                "Let's move on to adding students!\nWhat is the first name of your first student in you first row?", "Markbock", 3);
        String firstStudentMarksSTR = ui.showInputDialog(null,
                "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100", "Markbock", 3);
        double[] firstStudentsMarks = idk(firstStudentMarksSTR);

        listOfCLasses.add(makeClassList(classCode, firstStudent, firstStudentsMarks));
        return classCode;
    }


    private double[] idk(String m) {
        String marks = m;
        String[] str = marks.split(" ", m.length());
        double[] arrMarks = new double[str.length];
        for (int i = 0; i < str.length; i++) {
            arrMarks[i] = Double.valueOf(str[i]);
        }//end of for loop transferring marks to double array
        return arrMarks;
    }

    private ClassList makeClassList(String classCode, String firstStudent, double[] firstStudentsMarks) {
        ArrayList<ArrayList<Student>> tempStudents = new ArrayList<ArrayList<Student>>();
        tempStudents.add(new ArrayList<Student>());
        tempStudents.get(0).add(new Student(firstStudent, firstStudentsMarks));
        ClassList classList = new ClassList(classCode, tempStudents);
        return classList;
    }
}

class JOptionPaneUI implements UIInterface {

    @Override
    public String showInputDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException {
        return JOptionPane.showInputDialog(parentComponent, message, title, messageType);
    }

    @Override
    public int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) throws HeadlessException {
        return JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
    }

    @Override
    public void showMessageDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException {
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
    }
}