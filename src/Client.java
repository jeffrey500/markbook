//Jeffrey Zhu and Andrew Lu
//June 12th 2023
//Client, ClassList, Student
//A program that can be used by teachers to keep track of a classroom, the students inside it, and the grades of the students

//import libraries

import java.io.*;
import javax.swing.*;
import java.util.*;

//Client class
public class Client {
    //Create main method
    public static void main(String[] args) {

        //declare variables and constants

        String[] buttons = {"Add student in row", "Move to next row", "finish class"};
        ArrayList<ClassList> listOfCLasses = new ArrayList<ClassList>();

        //Some welcome messages
        uiShowMessage("Welcome to Markbock!" + "\nEmpowering teachers since 2023!");

        //tells the user the purpose of the program and some instructions on what to do
        uiShowMessage("This program is intended to help teachers keep track of the marks of students\n" +
                "This program uses a 2d matrix to simulate the seating plan of a classroom\n" +
                "You will interact with this program through text boxes that ask for input and buttons that you have to click\n" +
                "NEVER PRESS CANCEL OR LEAVE AN INPUT BLANK");

        //first classroom arraylist
        String classCode = removeSpaceEnd(JOptionPane.showInputDialog(null,
                "Let's create your first classroom!\nWhat is the class code?", "Markbock", 3));

        //popups and textboxes for user to enter vital information to make a classroom
        String firstStudent = removeSpaceEnd(JOptionPane.showInputDialog(null,
                "Let's move on to adding students!\nWhat is the first name of your first student in you first row?", "Markbock", 3));


        String firstStudentMarksSTR = removeSpaceEnd(JOptionPane.showInputDialog(null,
                "Give us a list of marks of that first student! (decimals allowed)" +
                        "\nRemember to add a space between marks to represent separate marks!" +
                        "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

        //create array of marks for first student
        double[] firstStudentsMarks = stringToDoubleArray(firstStudentMarksSTR);

        while (true) {
            if (firstStudentsMarks != null) {
                break;
            }
            //invalid message
            uiShowMessage("Your list of marks contains a non number! It's invalid!");

            firstStudentMarksSTR = removeSpaceEnd(JOptionPane.showInputDialog(null,
                    "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

            //create array of marks for first student
            firstStudentsMarks = stringToDoubleArray(firstStudentMarksSTR);
        }

        listOfCLasses.add(makeClassList(classCode, firstStudent, firstStudentsMarks));

        //create options for user to choose
        var yesOrNo = JOptionPane.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

        //0 corresponds to the first option, 1 corresponds to the second option and so forth
        if (yesOrNo == 0) {
            String studName = removeSpaceEnd(JOptionPane.showInputDialog(null, "What is the first name of your first student in the row?", "Markbock", 3));
            if (findClassList(listOfCLasses, classCode).getStudent(studName) == null) {

                //showing a popup that the user has to enter marks into
                String studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                        "Give us a list of marks of that first student! (decimals allowed)" +
                                "\nRemember to add a space between marks to represent separate marks!" +
                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                //while loop to make sure user dows not enter invalid input
                while (true) {
                    if (stringToDoubleArray(studMarks) != null) {
                        break;
                    }
                    //invalid output message
                    uiShowMessage("Your list of marks contains a non number! It's invalid!");

                    //A string of the user marks to be converted into double and sent into the program
                    studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                            "Give us a list of marks of that first student! (decimals allowed)" +
                                    "\nRemember to add a space between marks to represent separate marks!" +
                                    "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));
                }

                listOfCLasses.get(0).addStudentInRow(new Student(studName, stringToDoubleArray(studMarks)));
            } else {
                //in case they enter the same name twice
                uiShowMessage("That student name has been taken already!");
            }
            //repeats the program starting from the menu again, only for adding students
            menuRepeat(listOfCLasses, classCode);
            //if they wish to add students it comes into this if statement
        } else if (yesOrNo == 1) {
            //second option
            String studName = removeSpaceEnd(JOptionPane.showInputDialog(null, "What is the first name of the student", "Markbock", 3));
            if (findClassList(listOfCLasses, classCode).getStudent(studName) == null) {
                String studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                        "Give us a list of marks of that first student! (decimals allowed)" +
                                "\nRemember to add a space between marks to represent separate marks!" +
                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                while (true) {
                    if (stringToDoubleArray(studMarks) != null) {
                        break;
                    }
                    //invalid output message
                    uiShowMessage("Your list of marks contains a non number! It's invalid!");

                    //A string of the user marks to be converted into double and sent into the program

                    studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                            "Give us a list of marks of that first student! (decimals allowed)" +
                                    "\nRemember to add a space between marks to represent separate marks!" +
                                    "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));
                }

                listOfCLasses.get(0).addStudentNewRow(new Student(studName, stringToDoubleArray(studMarks)));
            } else {
                uiShowMessage("That student name has been taken already!");
            }
            //only for creating classes
            menuRepeat(listOfCLasses, classCode);
        }
        //start from the beginning again
        normalOperation(listOfCLasses);
    }


    //create a method that makes it less repetitive to output a GUI
    private static void uiShowMessage(String text) {
        JOptionPane.showMessageDialog(null, text, "Markbock", 3);
    }


    //create a method that repeats the menu function
    private static void menuRepeat(ArrayList<ClassList> listOfCLasses, String classCode) {
        //if the user wants to keep using the program
        while (true) {

            //options for user to choose from
            String[] repeat1 = {"Add student in row", "Move to next row", "finish class"};
            //takes in the number that corresponds with the users choice
            var repeatMenu = JOptionPane.showOptionDialog(null,
                    "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, repeat1, repeat1[0]);
            //switch to check which value the user chose
            switch (repeatMenu) {
                case 0:
                    String studName1 = removeSpaceEnd(JOptionPane.showInputDialog(null,
                            "What is the first name of your first student in the row?", "Markbock", 3));

                    if (findClassList(listOfCLasses, classCode).getStudent(studName1) == null) {
                        String studMarks1 = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                "Give us a list of marks of that first student! (decimals allowed)" +
                                        "\nRemember to add a space between marks to represent separate marks!" +
                                        "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                        while (true) {
                            if (stringToDoubleArray(studMarks1) != null) {
                                break;
                            }
                            uiShowMessage("Your list of marks contains a non number! It's invalid!");

                            //A string of the user marks to be converted into double and sent into the program
                            studMarks1 = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                            "\nRemember to add a space between marks to represent separate marks!" +
                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));
                        }
                        findClassList(listOfCLasses, classCode).addStudentInRow(new Student(studName1, stringToDoubleArray(studMarks1)));
                    } else {
                        //if student name has already been entered
                        uiShowMessage("That student name has been taken already!");
                    }
                    break;

                case 1:
                    //if user wants to move to the next row
                    studName1 = removeSpaceEnd(JOptionPane.showInputDialog(null,
                            "What is the first name of the student", "Markbock", 3));

                    //again checking if the name isn't taken and taking in the marks
                    if (findClassList(listOfCLasses, classCode).getStudent(studName1) == null) {

                        String studMarks1 = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                "Give us a list of marks of that first student! (decimals allowed)" +
                                        "\nRemember to add a space between marks to represent separate marks!" +
                                        "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                        while (true) {
                            if (stringToDoubleArray(studMarks1) != null) {
                                break;
                            }
                            //invalid message again
                            uiShowMessage("Your list of marks contains a non number! It's invalid!");

                            //A string of the user marks to be converted into double and sent into the program

                            studMarks1 = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                            "\nRemember to add a space between marks to represent separate marks!" +
                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));
                        }

                        findClassList(listOfCLasses, classCode).addStudentNewRow(new Student(studName1, stringToDoubleArray(studMarks1)));
                    } else {
                        //goes back if the user enters the same student name twice
                        uiShowMessage("That student name has been taken already!");
                    }
                    break;
                //default thing to go to while running this case
                default:
                    return;
            }
        }
    }


    //create a method that can create a new CLassList object
    private static ClassList makeClassList(String classCode, String firstStudent, double[] firstStudentsMarks) {
        ArrayList<ArrayList<Student>> tempStudents = new ArrayList<ArrayList<Student>>();
        tempStudents.add(new ArrayList<Student>());
        tempStudents.get(0).add(new Student(firstStudent, firstStudentsMarks));
        ClassList classList = new ClassList(classCode, tempStudents);
        return classList;
    }


    //create a method that allows for continuous operation
    public static void normalOperation(ArrayList<ClassList> listOfClasses) {
        while (true) {
            String[] menuButtons = {"Create new classList", "Access existing ClassList", "Stop program"};
            //returns value corresponding with the option the user chooses
            var yesOrNo = JOptionPane.showOptionDialog(null,
                    "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menuButtons, menuButtons[0]);

            //first option
            if (yesOrNo == 0) {
                String newClassCode = removeSpaceEnd(JOptionPane.showInputDialog(null, "What is the name of your new classlist?", "Markbock", 3));
                if (findClassList(listOfClasses, newClassCode) == null) {
                    String newStudent = removeSpaceEnd(JOptionPane.showInputDialog(null, "What is the first student in your classlist?", "Markbock", 3));
                    String newStudentMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                            "Give us a list of marks of that first student! (decimals allowed)" +
                                    "\nRemember to add a space between marks to represent separate marks!" +
                                    "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                    //new double array converting the string marks the user entered into double type
                    double[] newStudentMarksArray = stringToDoubleArray(newStudentMarks);

                    while (true) {
                        if (newStudentMarksArray != null) {
                            break;
                        }//invalid message
                        uiShowMessage("Your list of marks contains a non number! It's invalid!");
                        newStudentMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                "Give us a list of marks of that first student! (decimals allowed)" +
                                        "\nRemember to add a space between marks to represent separate marks!" +
                                        "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                        //create array of marks for first student
                        newStudentMarksArray = stringToDoubleArray(newStudentMarks);
                    }

                    //button options for the user to press
                    String[] newButtons = {"Add student in row", "Move to next row", "finish class"};
                    var newYesOrNo = JOptionPane.showOptionDialog(null,
                            "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, newButtons, newButtons[0]);
                    while (true) {
                        if (newStudentMarksArray != null) {
                            break;
                        }
                        //invalid message
                        uiShowMessage("Your list of marks contains a non number! It's invalid!");

                        //A string of the user marks to be converted into double and sent into the program
                        newStudentMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                "Give us a list of marks of that first student! (decimals allowed)" +
                                        "\nRemember to add a space between marks to represent separate marks!" +
                                        "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                        //create array of marks for first student
                        newStudentMarksArray = stringToDoubleArray(newStudentMarks);
                    }

                    //use the makeClasslist method to create a new class if the user chooses to
                    listOfClasses.add(makeClassList(newClassCode, newStudent, newStudentMarksArray));

                    if (newYesOrNo == 0) {
                        String studName = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                "What is the first name of your first student in the row?", "Markbock", 3));

                        //test if student name has been used already
                        if (findClassList(listOfClasses, newClassCode).getStudent(studName) == null) {
                            String studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                            "\nRemember to add a space between marks to represent separate marks!" +
                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                            while (true) {
                                if (stringToDoubleArray(studMarks) != null) {
                                    break;
                                }
                                //invalid message
                                uiShowMessage("Your list of marks contains a non number! It's invalid!");

                                //A string of the user marks to be converted into double and sent into the program
                                studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                        "Give us a list of marks of that first student! (decimals allowed)" +
                                                "\nRemember to add a space between marks to represent separate marks!" +
                                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));
                            }
                            listOfClasses.get(listOfClasses.size() - 1).addStudentInRow(new Student(studName, stringToDoubleArray(studMarks)));

                            menuRepeat(listOfClasses, newClassCode);
                        } else {
                            //if user enters same name twice in the same class
                            uiShowMessage("That student name has been taken already!");
                        }

                    } else if (newYesOrNo == 1) {
                        String studName = removeSpaceEnd(JOptionPane.showInputDialog(null, "What is the first name of the student", "Markbock", 3));

                        //test if student name has been used already
                        if (findClassList(listOfClasses, newClassCode).getStudent(studName) == null) {

                            String studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                            "\nRemember to add a space between marks to represent separate marks!" +
                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                            while (true) {
                                if (stringToDoubleArray(studMarks) != null) {
                                    break;
                                }
                                //invalid messages
                                uiShowMessage("Your list of marks contains a non number! It's invalid!");

                                //A string of the user marks to be converted into double and sent into the program
                                studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                        "Give us a list of marks of that first student! (decimals allowed)" +
                                                "\nRemember to add a space between marks to represent separate marks!" +
                                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));
                            }

                            listOfClasses.get(listOfClasses.size() - 1).addStudentNewRow(new Student(studName, stringToDoubleArray(studMarks)));

                            menuRepeat(listOfClasses, newClassCode);
                        } else {
                            uiShowMessage("That student name has been taken already!");
                        }
                    }
                } else {
                    //if user enters the same class name nore than once
                    uiShowMessage("That ClassList name has been taken already!");
                }

            } else if (yesOrNo == 1) {
                //user has to enter the exact same classcode they entered previously to access their class
                String mainMenuClasses = "Type the class code of the class you want to access\nAvailable class codes:";
                for (int i = 0; i < listOfClasses.size(); i++) {
                    mainMenuClasses += "\n";
                    mainMenuClasses += listOfClasses.get(i).getClassCode();
                }

                String inputClassCode = removeSpaceEnd(JOptionPane.showInputDialog(null, mainMenuClasses, "Markbock", 3));

                //if the classcode does not match any code they have previously entered
                if (findClassList(listOfClasses, inputClassCode) == null) {
                    uiShowMessage("That class code does not exist!");
                } else {

                    //new array of options for the user to press
                    String[] classMenuButtons = {"Modify/Access student grades", "class statistics", "Add/remove students", "display classlist"};
                    var classMenu = JOptionPane.showOptionDialog(null, "What would you like to do?",
                            "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, classMenuButtons, classMenuButtons[0]);

                    //switch for the classMenu of student statistics or modifying grades
                    switch (classMenu) {
                        case 0:
                            //array of options that will be used in a popup for the user to press and interact with
                            String[] modifyOrAccessGrades = {"access a student statistic", "Modify a grade from a student"};
                            var modifyOrAccessGradesMenu = JOptionPane.showOptionDialog(null, "What would you like to do?",
                                    "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modifyOrAccessGrades, modifyOrAccessGrades[0]);

                            //first choice in the array
                            if (modifyOrAccessGradesMenu == 0) {
                                doAccessStudentStatistics(listOfClasses, inputClassCode);

                            } else {
                                doModifyStudentGrade(listOfClasses, inputClassCode);
                            }
                            break;

                        case 1:
                            //what happens if user wants class statistics
                            String[] classStats = {"display class average", "display class median"};
                            var classStatsMenu = JOptionPane.showOptionDialog(null, "What would you like to do?",
                                    "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, classStats, classStats[0]);

                            //first option the user presses
                            if (classStatsMenu == 0) {
                                uiShowMessage("The class average mark is " + findClassList(listOfClasses, inputClassCode).getClassAverageMark() + "%");

                            } else {
                                uiShowMessage("The class Median mark is " + findClassList(listOfClasses, inputClassCode).getMedianClassMark() + "%");
                            }
                            break;

                        case 2:
                            //if user wants to modify students
                            String[] addAndRemove = {"Add a student", "remove a student"};
                            var addAndRemoveMenu = JOptionPane.showOptionDialog(null,
                                    "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                    null, addAndRemove, addAndRemove[0]);

                            //if user wants to add a student anywhere in the 2D arraylist
                            if (addAndRemoveMenu == 0) {
                                String addStudent = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                        "Where would you like to add the student type row then column\nnote that row and colum both start at index one\nYou can go one over the current row or colum\n\n" + findClassList(listOfClasses,
                                                inputClassCode).printClassroom(), "Markbock", 3));

                                //catch invalid input
                                try {
                                    //instantiate variables in try catch
                                    int space = addStudent.indexOf(" ");
                                    int row = Integer.valueOf(addStudent.substring(0, space));
                                    int column = Integer.valueOf(addStudent.substring(space + 1));

                                    if (row == 0 || column == 0) {
                                        uiShowMessage("You cannot have a row or colum equal to 0");

                                    } else if (row <= findClassList(listOfClasses, inputClassCode).getStudentList().size() && column <= findClassList(listOfClasses, inputClassCode).getStudentList().get(row-1).size()) {
                                        String studName = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                                "What is the first name of your student?", "Markbock", 3));

                                        if (findClassList(listOfClasses,inputClassCode).getStudent(studName) == null) {
                                            String studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                                            "\nRemember to add a space between marks to represent separate marks!" +
                                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                                            while (true) {
                                                if (stringToDoubleArray(studMarks) != null) {
                                                    break;
                                                }
                                                //invalid message
                                                uiShowMessage("Your list of marks contains a non number! It's invalid!");

                                                //A string of the user marks to be converted into double and sent into the program
                                                studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                                        "Give us a list of marks of that first student! (decimals allowed)" +
                                                                "\nRemember to add a space between marks to represent separate marks!" +
                                                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));
                                            }
                                            findClassList(listOfClasses, inputClassCode).addStudent(row-1, column-1, new Student(studName, stringToDoubleArray(studMarks)));
                                        } else {
                                            //if user enters the same name more than once, there will be a popup that informs the user of their mistake
                                            uiShowMessage("That student name has been taken already!");
                                        }

                                    } else if (row == findClassList(listOfClasses, inputClassCode).getStudentList().size() + 1  && column == 1) {
                                        String studName = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                                "What is the first name of your student?", "Markbock", 3));
                                        if (findClassList(listOfClasses,inputClassCode).getStudent(studName) == null) {
                                            String studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                                            "\nRemember to add a space between marks to represent separate marks!" +
                                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                                            while (true) {
                                                if (stringToDoubleArray(studMarks) != null) {
                                                    break;
                                                }
                                                uiShowMessage("Your list of marks contains a non number! It's invalid!");
                                                studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                                        "Give us a list of marks of that first student! (decimals allowed)" +
                                                                "\nRemember to add a space between marks to represent separate marks!" +
                                                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));
                                            }
                                            findClassList(listOfClasses, inputClassCode).addStudentNewRow(new Student(studName, stringToDoubleArray(studMarks)));
                                        } else {
                                            uiShowMessage("That student name has been taken already!");
                                        }
                                    } else if (row <= findClassList(listOfClasses, inputClassCode).getStudentList().size() && column == findClassList(listOfClasses, inputClassCode).getStudentList().get(row-1).size()+1) {
                                        String studName = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                                "What is the first name of your student?", "Markbock", 3));
                                        if (findClassList(listOfClasses,inputClassCode).getStudent(studName) == null) {
                                            String studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                                            "\nRemember to add a space between marks to represent separate marks!" +
                                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));

                                            while (true) {
                                                if (stringToDoubleArray(studMarks) != null) {
                                                    break;
                                                }
                                                uiShowMessage("Your list of marks contains a non number! It's invalid!");

                                                //A string of the user marks to be converted into double and sent into the program
                                                studMarks = removeSpaceEnd(JOptionPane.showInputDialog(null,
                                                        "Give us a list of marks of that first student! (decimals allowed)" +
                                                                "\nRemember to add a space between marks to represent separate marks!" +
                                                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"));
                                            }
                                            findClassList(listOfClasses, inputClassCode).getStudentList().get(row-1).add(column-1,new Student(studName, stringToDoubleArray(studMarks)));
                                        } else {
                                            uiShowMessage("That student name has been taken already!");
                                        }
                                    } else {
                                        //if user enters a number
                                        uiShowMessage("You inputted a number that is out of bounds");
                                    }
                                } catch (Exception e) {
                                    uiShowMessage("You inputted a non number value!");
                                }

                            } else {
                                //if the user wants to remove a student
                                String removeStudent = removeSpaceEnd(removeSpaceEnd(JOptionPane.showInputDialog(null,
                                        "Which student would you like to remove?\nType row followed by a space and then the column\n" + findClassList(listOfClasses,
                                                inputClassCode).printClassroom(), "Markbock", 3)));
                                //need to check for invalid inputs again
                                try {
                                    int space = removeStudent.indexOf(" ");
                                    int row = Integer.valueOf(removeStudent.substring(0, space));
                                    int column = Integer.valueOf(removeStudent.substring(space + 1));
                                    findClassList(listOfClasses, inputClassCode).removeStudent(row-1, column-1);
                                } catch (Exception e) {
                                    uiShowMessage("You did not type a valid input or you went out of bounds");
                                }
                            }
                            break;
                        default:
                            //if the user does everything correctly the first time they will enter their classroom from the class code
                            uiShowMessage("This is the classroom:\n" + findClassList(listOfClasses, inputClassCode).printClassroom());
                    }
                }
            } else {
                uiShowMessage("Thank you for using Markbock");
                System.exit(0);
            }
        }
    }


    //create a method that makes it less repetitive to modify a Student's grade
    private static void doModifyStudentGrade(ArrayList<ClassList> listOfClasses, String inputClassCode) {
        String[] modifyGrades = {"add a grade", "remove a grade"};
        var modifyGradesMenu = JOptionPane.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modifyGrades, modifyGrades[0]);

        //if user wants to add a grade
        if (modifyGradesMenu == 0) {
            String inputStudent = removeSpaceEnd(JOptionPane.showInputDialog(null, "Type the name of the student" + "\nList of Students\n" + findClassList(listOfClasses,
                    inputClassCode).printClassroom(), "Markbock", 3));
            String gradeInput = removeSpaceEnd(JOptionPane.showInputDialog(null,
                    "What percentage grade would you like to add\ndeciamls allowed only numbers!", "Markbock", 3));

            try {
                findStudent(inputStudent, findClassList(listOfClasses, inputClassCode)).addMark(Double.valueOf(gradeInput));
                uiShowMessage("Grade has been added!");
            } catch (Exception e) {
                uiShowMessage("You did not type a valid input");
            }

            //if user wants to remove a grade
        } else {
            String inputStudent = removeSpaceEnd(JOptionPane.showInputDialog(null, "Type the name of the student" + "\nList of Students\n" + findClassList(listOfClasses,
                    inputClassCode).printClassroom(), "Markbock", 3));
            String output = inputStudent + "'s marks:";
            ArrayList<Double> temp = new ArrayList<Double>();
            temp = findClassList(listOfClasses, inputClassCode).getStudent(inputStudent).getMarks();

            //loop to find the student from the list of classes
            for (int i = 0; i < findStudent(inputStudent, findClassList(listOfClasses, inputClassCode)).getMarks().size(); i++) {
                output += "\n";
                output += temp.get(i);
                output += "%";
            }//end of for loop

            output += "\nWhich mark would you like to remove (type the index number starting from 1)";
            String removeMark = removeSpaceEnd(JOptionPane.showInputDialog(null, output, "Markbock", 3));
            try {
                findClassList(listOfClasses, inputClassCode).getStudent(inputStudent).removeMark(Integer.valueOf(removeMark));
                uiShowMessage("That mark has been removed!");
            } catch (Exception e) {
                uiShowMessage("You did not type a valid input");
            }
        }
    }//end of modifying student grade method


    //create a method that makes it less repetitive to access a Student's statistics
    private static void doAccessStudentStatistics(ArrayList<ClassList> listOfClasses, String inputClassCode) {
        String[] studentStatistic = {"get student average", "get student median", "show all grades"};
        var studentStatisticMenu = JOptionPane.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, studentStatistic, studentStatistic[0]);


        //if user wants to get student average
        if (studentStatisticMenu == 0) {
            String inputStudent = removeSpaceEnd(JOptionPane.showInputDialog(null, "Type the name of the student" + "\nList of Students\n" + findClassList(listOfClasses,
                    inputClassCode).printClassroom(), "Markbock", 3));


            uiShowMessage(inputStudent + " has an average of " + findStudent(inputStudent, findClassList(listOfClasses, inputClassCode)).getAverageMarkM() + "%");

            //if user wants to get student medians
        } else if (studentStatisticMenu == 1) {
            String inputStudent = removeSpaceEnd(JOptionPane.showInputDialog(null, "Type the name of the student" + "\nList of Students\n" + findClassList(listOfClasses,
                    inputClassCode).printClassroom(), "Markbock", 3));
            uiShowMessage(inputStudent + " has a median " + findStudent(inputStudent, findClassList(listOfClasses, inputClassCode)).getMedian());

            //if user wants to get all student grades
        } else {
            String inputStudent = removeSpaceEnd(JOptionPane.showInputDialog(null, "Type the name of the student" + "\nList of Students\n" + findClassList(listOfClasses,
                    inputClassCode).printClassroom(), "Markbock", 3));
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
    }//end of accessing student statistics method


    //create a method that searches for and returns a ClassList object given an arraylist of ClassList objects
    public static ClassList findClassList(ArrayList<ClassList> a, String classCode) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getClassCode().equals(classCode)) {
                return a.get(i);
            }
        }
        return null;
    }//end of find classlist method


    //create a method that locates a Student object given a student's name
    public static Student findStudent(String studentName, ClassList c) {
        if (c.getStudent(studentName) != null) {
            return c.getStudent(studentName);
        }
        return null;
    }


    //create a method that turns a String in an array of doubles
    public static double[] stringToDoubleArray(String m) {
        try {
            String marks = m;
            String[] str = marks.split(" ", m.length());
            double[] arrMarks = new double[str.length];
            for (int i = 0; i < str.length; i++) {
                arrMarks[i] = Double.valueOf(str[i]);
            }//end of for loop transferring marks to double array
            return arrMarks;
        } catch (Exception e) {
            return null;
        }
    }


    //remove all blank spaces behind a string
    public static String removeSpaceEnd(String a) {
        boolean isTrue = true;
        while (isTrue == true) {
            if (a.substring(a.length() - 1).equals(" ")) {
                a = a.substring(0, a.length() - 1);
            } else {
                isTrue = false;
            }
        }
        return a;
    }
} //End of Client Class


//TODO: list of things that don't work
// remove