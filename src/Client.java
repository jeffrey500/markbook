// TODO: the date and purpose

//import libraries

import javax.swing.*;
import java.util.*;

//Client class
public class Client {

    //Create main method
    public static void main(String[] args) {

        String[] buttons = {"Add student in row", "Move to next row", "finish class"};
        ArrayList<ClassList> listOfCLasses = new ArrayList<ClassList>();

        uiShowMessage("Welcome to markbock!" + "\nEmpowering teachers since 2023!");

        String classCode = JOptionPane.showInputDialog(null,
                "Let's create your first classroom!\nWhat is the class code?", "Markbock", 3);
        String firstStudent = JOptionPane.showInputDialog(null,
                "Let's move on to adding students!\nWhat is the first name of your first student in you first row?", "Markbock", 3);
        String firstStudentMarksSTR = JOptionPane.showInputDialog(null,
                "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100");
        double[] firstStudentsMarks = idk(firstStudentMarksSTR);

        var yesOrNo = JOptionPane.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

        listOfCLasses.add(makeClassList(classCode, firstStudent, firstStudentsMarks));
        if (yesOrNo == 0) {
            String studName = JOptionPane.showInputDialog(null, "What is the first name of your first student in the row?", "Markbock", 3);
            String studMarks = JOptionPane.showInputDialog(null,
                    "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100");
            listOfCLasses.get(0).addStudentInRow(new Student(studName, idk(studMarks)));

            menuRepeat(listOfCLasses, classCode);


        } else if (yesOrNo == 1) {
            String studName = JOptionPane.showInputDialog(null, "What is the first name of the student", "Markbock", 3);
            String studMarks = JOptionPane.showInputDialog(null,
                    "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100");
            listOfCLasses.get(0).addStudetNewRow(new Student(studName, idk(studMarks)));

            menuRepeat(listOfCLasses, classCode);
        }

        normalOperation(listOfCLasses);
    }

    private static void uiShowMessage(String text) {
        JOptionPane.showMessageDialog(null, text, "Markbock", 3);
    }


    private static void menuRepeat(ArrayList<ClassList> listOfCLasses, String classCode) {
        while (true) {
            String[] repeat1 = {"Add student in row", "Move to next row", "finish class"};
            var repeatmenu = JOptionPane.showOptionDialog(null,
                    "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, repeat1, repeat1[0]);

            switch (repeatmenu) {
                case 0:
                    String studName1 = JOptionPane.showInputDialog(null,
                            "What is the first name of your first student in the row?", "Markbock", 3);
                    String studMarks1 = JOptionPane.showInputDialog(null,
                            "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100");
                    findClassList(listOfCLasses, classCode).addStudentInRow(new Student(studName1, idk(studMarks1)));
                    break;

                case 1:
                    studName1 = JOptionPane.showInputDialog(null,
                            "What is the first name of the student", "Markbock", 3);
                    studMarks1 = JOptionPane.showInputDialog(null,
                            "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100");
                    findClassList(listOfCLasses, classCode).addStudetNewRow(new Student(studName1, idk(studMarks1)));
                    break;

                default:
                    System.out.println(listOfCLasses.get(0).printClassroom());
                    System.out.println("here");
                    return;
            }
        }
    }


    private static ClassList makeClassList(String classCode, String firstStudent, double[] firstStudentsMarks) {
        ArrayList<ArrayList<Student>> tempStudents = new ArrayList<ArrayList<Student>>();
        tempStudents.add(new ArrayList<Student>());
        tempStudents.get(0).add(new Student(firstStudent, firstStudentsMarks));
        ClassList classList = new ClassList(classCode, tempStudents);
        return classList;
    }


    public static void normalOperation(ArrayList<ClassList> listOfClasses) {
        // TODO: add an option to exit the program
        //go to menu
        while (true) {
            String[] menuButtons = {"Create new classList", "Access existing ClassList"};
            var yesOrNo = JOptionPane.showOptionDialog(null,
                    "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menuButtons, menuButtons[0]);

            if (yesOrNo == 0) {

                String newClassCode = JOptionPane.showInputDialog(null, "What is the name of your new classlist?", "Markbock", 3);
                String newStudent = JOptionPane.showInputDialog(null, "What is the first student in your classlist?", "Markbock", 3);
                String newStudentMarks = JOptionPane.showInputDialog(null,
                        "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100");
                double[] newStudentMarksArray = idk(newStudentMarks);

                String[] newButtons = {"Add student in row", "Move to next row", "finish class"};
                var newYesOrNo = JOptionPane.showOptionDialog(null,
                        "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, newButtons, newButtons[0]);

                listOfClasses.add(makeClassList(newClassCode, newStudent, newStudentMarksArray));
                if (newYesOrNo == 0) {
                    String studName = JOptionPane.showInputDialog(null, "What is the first name of your first student in the row?", "Markbock", 3);
                    String studMarks = JOptionPane.showInputDialog(null,
                            "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100");
                    listOfClasses.get(listOfClasses.size()-1).addStudetNewRow(new Student(studName, idk(studMarks)));

                    menuRepeat(listOfClasses,newClassCode);

                } else if (newYesOrNo == 1) {
                    String studName = JOptionPane.showInputDialog(null, "What is the first name of the student", "Markbock", 3);
                    String studMarks = JOptionPane.showInputDialog(null,
                            "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100");
                    listOfClasses.get(listOfClasses.size()-1).addStudetNewRow(new Student(studName, idk(studMarks)));

                    menuRepeat(listOfClasses,newClassCode);
                }

            } else {
                String mainMenuClasses = "Type the class code of the class you want to access\nAvailable class codes:";
                for (int i = 0; i < listOfClasses.size(); i++) {
                    mainMenuClasses += "\n";
                    mainMenuClasses += listOfClasses.get(i).getClassCode();
                }

                String inputClassCode = JOptionPane.showInputDialog(null, mainMenuClasses, "Markbock", 3);
                System.out.println(findClassList(listOfClasses, inputClassCode));

                if (findClassList(listOfClasses, inputClassCode) == null) {
                    uiShowMessage("That class code does not exist!");
                    break;
                }

                String[] classMenuButtons = {"Modify/Access student grades", "class statistics", "Add/remove students"};
                var classMenu = JOptionPane.showOptionDialog(null, "What would you like to do?",
                        "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, classMenuButtons, classMenuButtons[0]);

                switch (classMenu) {
                    case 0:
                        String[] modifyOrAccessGrades = {"access a student statistic", "Modify a grade from a student"};
                        var modifyOrAccessGradesMenu = JOptionPane.showOptionDialog(null, "What would you like to do?",
                                "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modifyOrAccessGrades, modifyOrAccessGrades[0]);

                        if (modifyOrAccessGradesMenu == 0) {
                            doAccessStudentStatistics(listOfClasses, inputClassCode);

                        } else {
                            doModifyStudentGrade(listOfClasses, inputClassCode);
                        }
                        break;

                    case 1:
                        String[] classStats = {"display class average", "display class median"};
                        var classStatsMenu = JOptionPane.showOptionDialog(null, "What would you like to do?",
                                "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, classStats, classStats[0]);

                        if (classStatsMenu == 0) {
                            uiShowMessage("The class average mark is " + findClassList(listOfClasses, inputClassCode).getAverageMark());

                        } else {
                            uiShowMessage("The class Median mark is " + findClassList(listOfClasses, inputClassCode).getMedianMark());
                        }
                        break;

                    default:
                        String[] addAndRemove = {"Add a student", "remove a student"};
                        var addAndRemoveMenu = JOptionPane.showOptionDialog(null,
                                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                null, addAndRemove, addAndRemove[0]);

                        if (addAndRemoveMenu == 0) {
                            String addStudent = JOptionPane.showInputDialog(null,
                                    "Where would you like to add the student type row then column\nMake sure that the row exists!\n" + findClassList(listOfClasses,
                                            inputClassCode).printClassroom(), "Markbock", 3);
                            try {
                                int space = addStudent.indexOf(" ");
                                int row = Integer.valueOf(addStudent.substring(0, space));
                                int column = Integer.valueOf(addStudent.substring(space + 1));
                                String studName = JOptionPane.showInputDialog(null,
                                        "What is the first name of your first student in the row?", "Markbock", 3);
                                String studMarks = JOptionPane.showInputDialog(null,
                                        "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100");
                                findClassList(listOfClasses, inputClassCode).addStudent(row, column, new Student(studName, idk(studMarks)));
                            } catch (Exception e) {
                                uiShowMessage("You did not type a valid input");
                            }

                        } else {
                            String removeStudent = JOptionPane.showInputDialog(null,
                                    "Which student would you like to remove?\nType row followed by a space and then the column\n" + findClassList(listOfClasses,
                                            inputClassCode).printClassroom(), "Markbock", 3);
                            try {
                                int space = removeStudent.indexOf(" ");
                                int row = Integer.valueOf(removeStudent.substring(0, space));
                                int column = Integer.valueOf(removeStudent.substring(space + 1));
                                findClassList(listOfClasses, inputClassCode).removeStudent(row, column);
                            } catch (Exception e) {
                                uiShowMessage("You did not type a valid input");
                            }
                        }
                }
            }
        }
    }


    private static void doModifyStudentGrade(ArrayList<ClassList> listOfClasses, String inputClassCode) {
        String[] modifyGrades = {"add a grade", "remove a grade"};
        var modifyGradesMenu = JOptionPane.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modifyGrades, modifyGrades[0]);

        if (modifyGradesMenu == 0) {
            String inputStudent = JOptionPane.showInputDialog(null, "Type the name of the student", "Markbock", 3);
            String gradeInput = JOptionPane.showInputDialog(null,
                    "What percentage grade would you like to add\ndeciamls allowed only numbers!", "Markbock", 3);

            try {
                findStudent(inputStudent, findClassList(listOfClasses, inputClassCode)).addMark(Double.valueOf(gradeInput));
                uiShowMessage("Grade has been added!");
            } catch (Exception e) {
                uiShowMessage("You did not type a valid input");
            }

        } else {
            String inputStudent = JOptionPane.showInputDialog(null, "Type the name of the student", "Markbock", 3);
            String output = inputStudent + "'s marks:";
            ArrayList<Double> temp = new ArrayList<Double>();
            temp = findClassList(listOfClasses, inputClassCode).getStudent(inputStudent).getMarks();

            for (int i = 0; i < findStudent(inputStudent, findClassList(listOfClasses, inputClassCode)).getMarks().size(); i++) {
                output += "\n";
                output += temp.get(i);
                output += "%";
            }

            output += "\nWhich mark would you like to remove (type the index number starting from 1)";
            String removeMark = JOptionPane.showInputDialog(null, output, "Markbock", 3);
            try {
                findClassList(listOfClasses, inputClassCode).getStudent(inputStudent).removeMark(Integer.valueOf(removeMark));
                uiShowMessage("That mark has been removed!");
            } catch (Exception e) {
                uiShowMessage("You did not type a valid input");
            }
        }
    }


    private static void doAccessStudentStatistics(ArrayList<ClassList> listOfClasses, String inputClassCode) {
        String[] studentStatistic = {"get student average", "get student median", "show all grades"};
        var studentStatisticMenu = JOptionPane.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, studentStatistic, studentStatistic[0]);


        if (studentStatisticMenu == 0) {
            String inputStudent = JOptionPane.showInputDialog(null, "Type the name of the student", "Markbock", 3);


            uiShowMessage(inputStudent + " has an average of " + findStudent(inputStudent, findClassList(listOfClasses, inputClassCode)).getAverageMark() + "%");


        } else if (studentStatisticMenu == 1) {
            String inputStudent = JOptionPane.showInputDialog(null, "Type the name of the student", "Markbock", 3);
            uiShowMessage(inputStudent + " has a median " + findStudent(inputStudent, findClassList(listOfClasses, inputClassCode)).getMedian());


        } else {
            String inputStudent = JOptionPane.showInputDialog(null, "Type the name of the student", "Markbock", 3);
            String output = inputStudent + "'s marks:";
            ArrayList<Double> temp = new ArrayList<Double>();
            temp = findClassList(listOfClasses, inputClassCode).getStudent(inputStudent).getMarks();
            for (int i = 0; i < findStudent(inputStudent, findClassList(listOfClasses, inputClassCode)).getMarks().size(); i++) {
                output += "\n";
                output += temp.get(i);
                output += "%";
            }
            uiShowMessage(output);
        }
    }


    public static ClassList findClassList(ArrayList<ClassList> a, String classCode) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getClassCode().equals(classCode)) {
                return a.get(i);
            }
        }
        return null;
    }


    public static Student findStudent(String studentName, ClassList c) {
        if (c.getStudent(studentName) != null) {
            return c.getStudent(studentName);
        }
        return null;
    }


    public static ClassList selectClassList(String className, ArrayList<ClassList> c) {
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getClassCode().equals(className)) {
                return c.get(i);
            }
        }
        return null;
    }


    public static double[] idk(String m) {
        String marks = m;
        String[] str = marks.split(" ", m.length());
        double[] arrMarks = new double[str.length];
        for (int i = 0; i < str.length; i++) {
            arrMarks[i] = Double.valueOf(str[i]);
        }//end of for loop transferring marks to double array
        return arrMarks;
    }
} //End of Client Class