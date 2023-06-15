//Jeffrey Zhu and Andrew Lu
//June 12th 2023
//Client, ClassList, Student
//A program that can be used by teachers to keep track of a classroom, the students inside it, and the grades of the students

//import libraries

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Client class
public class Client {

    ArrayList<ClassList> listOfCLasses = new ArrayList<ClassList>();
    UIInterfaceFull ui;

    public Client(UIInterfaceFull ui) {
        this.ui = ui;
    }

    //Create main method
    public static void main(String[] args) {

        JOptionPaneFullUI swingUI = new JOptionPaneFullUI();
        Client client = new Client(swingUI);

        client.InputLoop();
    }

    public ArrayList<ClassList> getListOfCLasses() {
        return listOfCLasses;
    }

    public void InputLoop() {
        //declare variables and constants
        String[] buttons = {"Add student in row", "Move to next row", "finish class"};

        //Some welcome messages
        uiShowMessage("Welcome to Markbock!" + "\nEmpowering teachers since 2023!");

        //tells the user the purpose of the program and some instructions on what to do
        uiShowMessage("This program is intended to help teachers keep track of the marks of students\n" +
                "This program uses a 2d matrix to simulate the seating plan of a classroom\n" +
                "You will interact with this program through text boxes that ask for input and buttons that you have to click\n" +
                "NEVER PRESS CANCEL OR LEAVE AN INPUT BLANK");

        //first classroom arraylist
        String classCode = removeSpaceEnd(ui.showInputDialog(null,
                "Let's create your first classroom!\nWhat is the class code?", "Markbock", 3));

        //popups and textboxes for user to enter vital information to make a classroom
        String firstStudent = removeSpaceEnd(ui.showInputDialog(null,
                "Let's move on to adding students!\nWhat is the first name of your first student in you first row?", "Markbock", 3));


        String firstStudentMarksSTR = removeSpaceEnd(ui.showInputDialog(null,
                "Give us a list of marks of that first student! (decimals allowed)" +
                        "\nRemember to add a space between marks to represent separate marks!" +
                        "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

        //create array of marks for first student
        double[] firstStudentsMarks = stringToDoubleArray(firstStudentMarksSTR);

        while (true) {
            if (firstStudentsMarks != null) {
                break;
            }
            //invalid message
            uiShowMessage("Your list of marks contains a non number! It's invalid!");

            firstStudentMarksSTR = removeSpaceEnd(ui.showInputDialog(null,
                    "Give us a list of marks of that first student! (decimals allowed)\nNote that non-numbers will be rejected, <0 = 0, >100 = 100"
            ,"Markbock", 3));

            //create array of marks for first student
            firstStudentsMarks = stringToDoubleArray(firstStudentMarksSTR);
        }

        listOfCLasses.add(makeClassList(classCode, firstStudent, firstStudentsMarks));

        //create options for user to choose
        var yesOrNo = ui.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

        //0 corresponds to the first option, 1 corresponds to the second option and so forth
        if (yesOrNo == 0) {
            String studName = removeSpaceEnd(ui.showInputDialog(null, "What is the first name of your first student in the row?", "Markbock", 3));
            if (findClassList(classCode).getStudent(studName) == null) {

                //showing a popup that the user has to enter marks into
                String studMarks = removeSpaceEnd(ui.showInputDialog(null,
                        "Give us a list of marks of that first student! (decimals allowed)" +
                                "\nRemember to add a space between marks to represent separate marks!" +
                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                //while loop to make sure user dows not enter invalid input
                while (true) {
                    if (stringToDoubleArray(studMarks) != null) {
                        break;
                    }
                    //invalid output message
                    uiShowMessage("Your list of marks contains a non number! It's invalid!");

                    //A string of the user marks to be converted into double and sent into the program
                    studMarks = removeSpaceEnd(ui.showInputDialog(null,
                            "Give us a list of marks of that first student! (decimals allowed)" +
                                    "\nRemember to add a space between marks to represent separate marks!" +
                                    "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));
                }

                listOfCLasses.get(0).addStudentInRow(new Student(studName, stringToDoubleArray(studMarks)));
            } else {
                //in case they enter the same name twice
                uiShowMessage("That student name has been taken already!");
            }
            //repeats the program starting from the menu again, only for adding students
            menuRepeat(classCode);
            //if they wish to add students it comes into this if statement
        } else if (yesOrNo == 1) {
            //second option
            String studName = removeSpaceEnd(ui.showInputDialog(null, "What is the first name of the student", "Markbock", 3));
            if (findClassList(classCode).getStudent(studName) == null) {
                String studMarks = removeSpaceEnd(ui.showInputDialog(null,
                        "Give us a list of marks of that first student! (decimals allowed)" +
                                "\nRemember to add a space between marks to represent separate marks!" +
                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                while (true) {
                    if (stringToDoubleArray(studMarks) != null) {
                        break;
                    }
                    //invalid output message
                    uiShowMessage("Your list of marks contains a non number! It's invalid!");

                    //A string of the user marks to be converted into double and sent into the program

                    studMarks = removeSpaceEnd(ui.showInputDialog(null,
                            "Give us a list of marks of that first student! (decimals allowed)" +
                                    "\nRemember to add a space between marks to represent separate marks!" +
                                    "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));
                }

                listOfCLasses.get(0).addStudentNewRow(new Student(studName, stringToDoubleArray(studMarks)));
            } else {
                uiShowMessage("That student name has been taken already!");
            }
            //only for creating classes
            menuRepeat(classCode);
        }
        //start from the beginning again
        normalOperation();
    }


    //create a method that makes it less repetitive to output a GUI
    private void uiShowMessage(String text) {
        ui.showMessageDialog(null, text, "Markbock", 3);
    }


    //create a method that repeats the menu function
    private void menuRepeat(String classCode) {
        //if the user wants to keep using the program
        while (true) {

            //options for user to choose from
            String[] repeat1 = {"Add student in row", "Move to next row", "finish class"};
            //takes in the number that corresponds with the users choice
            var repeatMenu = ui.showOptionDialog(null,
                    "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, repeat1, repeat1[0]);
            //switch to check which value the user chose
            switch (repeatMenu) {
                case 0:
                    String studName1 = removeSpaceEnd(ui.showInputDialog(null,
                            "What is the first name of your first student in the row?", "Markbock", 3));

                    if (findClassList(classCode).getStudent(studName1) == null) {
                        String studMarks1 = removeSpaceEnd(ui.showInputDialog(null,
                                "Give us a list of marks of that first student! (decimals allowed)" +
                                        "\nRemember to add a space between marks to represent separate marks!" +
                                        "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                        while (true) {
                            if (stringToDoubleArray(studMarks1) != null) {
                                break;
                            }
                            uiShowMessage("Your list of marks contains a non number! It's invalid!");

                            //A string of the user marks to be converted into double and sent into the program
                            studMarks1 = removeSpaceEnd(ui.showInputDialog(null,
                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                            "\nRemember to add a space between marks to represent separate marks!" +
                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));
                        }
                        findClassList(classCode).addStudentInRow(new Student(studName1, stringToDoubleArray(studMarks1)));
                    } else {
                        //if student name has already been entered
                        uiShowMessage("That student name has been taken already!");
                    }
                    break;

                case 1:
                    //if user wants to move to the next row
                    studName1 = removeSpaceEnd(ui.showInputDialog(null,
                            "What is the first name of the student", "Markbock", 3));

                    //again checking if the name isn't taken and taking in the marks
                    if (findClassList(classCode).getStudent(studName1) == null) {

                        String studMarks1 = removeSpaceEnd(ui.showInputDialog(null,
                                "Give us a list of marks of that first student! (decimals allowed)" +
                                        "\nRemember to add a space between marks to represent separate marks!" +
                                        "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                        while (true) {
                            if (stringToDoubleArray(studMarks1) != null) {
                                break;
                            }
                            //invalid message again
                            uiShowMessage("Your list of marks contains a non number! It's invalid!");

                            //A string of the user marks to be converted into double and sent into the program

                            studMarks1 = removeSpaceEnd(ui.showInputDialog(null,
                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                            "\nRemember to add a space between marks to represent separate marks!" +
                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));
                        }

                        findClassList(classCode).addStudentNewRow(new Student(studName1, stringToDoubleArray(studMarks1)));
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
    private ClassList makeClassList(String classCode, String firstStudent, double[] firstStudentsMarks) {
        ArrayList<ArrayList<Student>> tempStudents = new ArrayList<ArrayList<Student>>();
        tempStudents.add(new ArrayList<Student>());
        tempStudents.get(0).add(new Student(firstStudent, firstStudentsMarks));
        ClassList classList = new ClassList(classCode, tempStudents);
        return classList;
    }


    //create a method that allows for continuous operation
    private void normalOperation() {
        while (true) {
            String[] menuButtons = {"Create new classList", "Access existing ClassList", "Stop program"};
            //returns value corresponding with the option the user chooses
            var yesOrNo = ui.showOptionDialog(null,
                    "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menuButtons, menuButtons[0]);

            //first option
            if (yesOrNo == 0) {
                String newClassCode = removeSpaceEnd(ui.showInputDialog(null, "What is the name of your new classlist?", "Markbock", 3));
                if (findClassList(newClassCode) == null) {
                    String newStudent = removeSpaceEnd(ui.showInputDialog(null, "What is the first student in your classlist?", "Markbock", 3));
                    String newStudentMarks = removeSpaceEnd(ui.showInputDialog(null,
                            "Give us a list of marks of that first student! (decimals allowed)" +
                                    "\nRemember to add a space between marks to represent separate marks!" +
                                    "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                    //new double array converting the string marks the user entered into double type
                    double[] newStudentMarksArray = stringToDoubleArray(newStudentMarks);

                    while (true) {
                        if (newStudentMarksArray != null) {
                            break;
                        }//invalid message
                        uiShowMessage("Your list of marks contains a non number! It's invalid!");
                        newStudentMarks = removeSpaceEnd(ui.showInputDialog(null,
                                "Give us a list of marks of that first student! (decimals allowed)" +
                                        "\nRemember to add a space between marks to represent separate marks!" +
                                        "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                        //create array of marks for first student
                        newStudentMarksArray = stringToDoubleArray(newStudentMarks);
                    }

                    //button options for the user to press
                    String[] newButtons = {"Add student in row", "Move to next row", "finish class"};
                    var newYesOrNo = ui.showOptionDialog(null,
                            "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, newButtons, newButtons[0]);
                    while (true) {
                        if (newStudentMarksArray != null) {
                            break;
                        }
                        //invalid message
                        uiShowMessage("Your list of marks contains a non number! It's invalid!");

                        //A string of the user marks to be converted into double and sent into the program
                        newStudentMarks = removeSpaceEnd(ui.showInputDialog(null,
                                "Give us a list of marks of that first student! (decimals allowed)" +
                                        "\nRemember to add a space between marks to represent separate marks!" +
                                        "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                        //create array of marks for first student
                        newStudentMarksArray = stringToDoubleArray(newStudentMarks);
                    }

                    //use the makeClasslist method to create a new class if the user chooses to
                    listOfCLasses.add(makeClassList(newClassCode, newStudent, newStudentMarksArray));

                    if (newYesOrNo == 0) {
                        String studName = removeSpaceEnd(ui.showInputDialog(null,
                                "What is the first name of your first student in the row?", "Markbock", 3));

                        //test if student name has been used already
                        if (findClassList(newClassCode).getStudent(studName) == null) {
                            String studMarks = removeSpaceEnd(ui.showInputDialog(null,
                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                            "\nRemember to add a space between marks to represent separate marks!" +
                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                            while (true) {
                                if (stringToDoubleArray(studMarks) != null) {
                                    break;
                                }
                                //invalid message
                                uiShowMessage("Your list of marks contains a non number! It's invalid!");

                                //A string of the user marks to be converted into double and sent into the program
                                studMarks = removeSpaceEnd(ui.showInputDialog(null,
                                        "Give us a list of marks of that first student! (decimals allowed)" +
                                                "\nRemember to add a space between marks to represent separate marks!" +
                                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));
                            }
                            listOfCLasses.get(listOfCLasses.size() - 1).addStudentInRow(new Student(studName, stringToDoubleArray(studMarks)));

                            menuRepeat(newClassCode);
                        } else {
                            //if user enters same name twice in the same class
                            uiShowMessage("That student name has been taken already!");
                        }

                    } else if (newYesOrNo == 1) {
                        String studName = removeSpaceEnd(ui.showInputDialog(null, "What is the first name of the student", "Markbock", 3));

                        //test if student name has been used already
                        if (findClassList(newClassCode).getStudent(studName) == null) {

                            String studMarks = removeSpaceEnd(ui.showInputDialog(null,
                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                            "\nRemember to add a space between marks to represent separate marks!" +
                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                            while (true) {
                                if (stringToDoubleArray(studMarks) != null) {
                                    break;
                                }
                                //invalid messages
                                uiShowMessage("Your list of marks contains a non number! It's invalid!");

                                //A string of the user marks to be converted into double and sent into the program
                                studMarks = removeSpaceEnd(ui.showInputDialog(null,
                                        "Give us a list of marks of that first student! (decimals allowed)" +
                                                "\nRemember to add a space between marks to represent separate marks!" +
                                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));
                            }

                            listOfCLasses.get(listOfCLasses.size() - 1).addStudentNewRow(new Student(studName, stringToDoubleArray(studMarks)));

                            menuRepeat(newClassCode);
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
                for (int i = 0; i < listOfCLasses.size(); i++) {
                    mainMenuClasses += "\n";
                    mainMenuClasses += listOfCLasses.get(i).getClassCode();
                }

                String inputClassCode = removeSpaceEnd(ui.showInputDialog(null, mainMenuClasses, "Markbock", 3));

                //if the classcode does not match any code they have previously entered
                if (findClassList(inputClassCode) == null) {
                    uiShowMessage("That class code does not exist!");
                } else {

                    //new array of options for the user to press
                    String[] classMenuButtons = {"Modify/Access student grades", "class statistics", "Add/remove students", "display classlist"};
                    var classMenu = ui.showOptionDialog(null, "What would you like to do?",
                            "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, classMenuButtons, classMenuButtons[0]);

                    //switch for the classMenu of student statistics or modifying grades
                    switch (classMenu) {
                        case 0:
                            //array of options that will be used in a popup for the user to press and interact with
                            String[] modifyOrAccessGrades = {"access a student statistic", "Modify a grade from a student"};
                            var modifyOrAccessGradesMenu = ui.showOptionDialog(null, "What would you like to do?",
                                    "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modifyOrAccessGrades, modifyOrAccessGrades[0]);

                            //first choice in the array
                            if (modifyOrAccessGradesMenu == 0) {
                                doAccessStudentStatistics(inputClassCode);

                            } else {
                                doModifyStudentGrade(inputClassCode);
                            }
                            break;

                        case 1:
                            //what happens if user wants class statistics
                            String[] classStats = {"display class average", "display class median"};
                            var classStatsMenu = ui.showOptionDialog(null, "What would you like to do?",
                                    "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, classStats, classStats[0]);

                            //first option the user presses
                            if (classStatsMenu == 0) {
                                uiShowMessage("The class average mark is " + findClassList(inputClassCode).getClassAverageMark() + "%");

                            } else {
                                uiShowMessage("The class Median mark is " + findClassList(inputClassCode).getMedianClassMark() + "%");
                            }
                            break;

                        case 2:
                            //if user wants to modify students
                            String[] addAndRemove = {"Add a student", "remove a student"};
                            var addAndRemoveMenu = ui.showOptionDialog(null,
                                    "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                    null, addAndRemove, addAndRemove[0]);

                            //if user wants to add a student anywhere in the 2D arraylist
                            if (addAndRemoveMenu == 0) {
                                String addStudent = removeSpaceEnd(ui.showInputDialog(null,
                                        "Where would you like to add the student type row then column\nnote that row and colum both start at index one\nYou can go one over the current row or colum\n\n"
                                                + findClassList(inputClassCode).printClassroom(), "Markbock", 3));

                                //catch invalid input
                                try {
                                    //instantiate variables in try catch
                                    int space = addStudent.indexOf(" ");
                                    int row = Integer.valueOf(addStudent.substring(0, space));
                                    int column = Integer.valueOf(addStudent.substring(space + 1));

                                    if (row == 0 || column == 0) {
                                        uiShowMessage("You cannot have a row or colum equal to 0");

                                    } else if (row <= findClassList(inputClassCode).getStudentList().size() && column <= findClassList(inputClassCode).getStudentList().get(row - 1).size()) {
                                        String studName = removeSpaceEnd(ui.showInputDialog(null,
                                                "What is the first name of your student?", "Markbock", 3));

                                        if (findClassList(inputClassCode).getStudent(studName) == null) {
                                            String studMarks = removeSpaceEnd(ui.showInputDialog(null,
                                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                                            "\nRemember to add a space between marks to represent separate marks!" +
                                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                                            while (true) {
                                                if (stringToDoubleArray(studMarks) != null) {
                                                    break;
                                                }
                                                //invalid message
                                                uiShowMessage("Your list of marks contains a non number! It's invalid!");

                                                //A string of the user marks to be converted into double and sent into the program
                                                studMarks = removeSpaceEnd(ui.showInputDialog(null,
                                                        "Give us a list of marks of that first student! (decimals allowed)" +
                                                                "\nRemember to add a space between marks to represent separate marks!" +
                                                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));
                                            }
                                            findClassList(inputClassCode).addStudent(row - 1, column - 1, new Student(studName, stringToDoubleArray(studMarks)));
                                        } else {
                                            //if user enters the same name more than once, there will be a popup that informs the user of their mistake
                                            uiShowMessage("That student name has been taken already!");
                                        }

                                    } else if (row == findClassList(inputClassCode).getStudentList().size() + 1 && column == 1) {
                                        String studName = removeSpaceEnd(ui.showInputDialog(null,
                                                "What is the first name of your student?", "Markbock", 3));
                                        if (findClassList(inputClassCode).getStudent(studName) == null) {
                                            String studMarks = removeSpaceEnd(ui.showInputDialog(null,
                                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                                            "\nRemember to add a space between marks to represent separate marks!" +
                                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                                            while (true) {
                                                if (stringToDoubleArray(studMarks) != null) {
                                                    break;
                                                }
                                                uiShowMessage("Your list of marks contains a non number! It's invalid!");
                                                studMarks = removeSpaceEnd(ui.showInputDialog(null,
                                                        "Give us a list of marks of that first student! (decimals allowed)" +
                                                                "\nRemember to add a space between marks to represent separate marks!" +
                                                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));
                                            }
                                            findClassList(inputClassCode).addStudentNewRow(new Student(studName, stringToDoubleArray(studMarks)));
                                        } else {
                                            uiShowMessage("That student name has been taken already!");
                                        }
                                    } else if (row <= findClassList(inputClassCode).getStudentList().size() && column == findClassList(inputClassCode).getStudentList().get(row - 1).size() + 1) {
                                        String studName = removeSpaceEnd(ui.showInputDialog(null,
                                                "What is the first name of your student?", "Markbock", 3));
                                        if (findClassList(inputClassCode).getStudent(studName) == null) {
                                            String studMarks = removeSpaceEnd(ui.showInputDialog(null,
                                                    "Give us a list of marks of that first student! (decimals allowed)" +
                                                            "\nRemember to add a space between marks to represent separate marks!" +
                                                            "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));

                                            while (true) {
                                                if (stringToDoubleArray(studMarks) != null) {
                                                    break;
                                                }
                                                uiShowMessage("Your list of marks contains a non number! It's invalid!");

                                                //A string of the user marks to be converted into double and sent into the program
                                                studMarks = removeSpaceEnd(ui.showInputDialog(null,
                                                        "Give us a list of marks of that first student! (decimals allowed)" +
                                                                "\nRemember to add a space between marks to represent separate marks!" +
                                                                "\nNote that non-numbers will be rejected, <0 = 0, >100 = 100","Markbock", 3));
                                            }
                                            findClassList(inputClassCode).getStudentList().get(row - 1).add(column - 1, new Student(studName, stringToDoubleArray(studMarks)));
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
                                String removeStudent = removeSpaceEnd(removeSpaceEnd(ui.showInputDialog(null,
                                        "Which student would you like to remove?\nType row followed by a space and then the column\n" + findClassList(inputClassCode).printClassroom(), "Markbock", 3)));
                                //need to check for invalid inputs again
                                try {
                                    int space = removeStudent.indexOf(" ");
                                    int row = Integer.valueOf(removeStudent.substring(0, space));
                                    int column = Integer.valueOf(removeStudent.substring(space + 1));
                                    findClassList(inputClassCode).removeStudent(row - 1, column - 1);
                                } catch (Exception e) {
                                    uiShowMessage("You did not type a valid input or you went out of bounds");
                                }
                            }
                            break;
                        default:
                            //if the user does everything correctly the first time they will enter their classroom from the class code
                            uiShowMessage("This is the classroom:\n" + findClassList(inputClassCode).printClassroom());
                    }
                }
            } else {
                uiShowMessage("Thank you for using Markbock");
                return;
            }
        }
    }


    //create a method that makes it less repetitive to modify a Student's grade
    private void doModifyStudentGrade(String inputClassCode) {
        String[] modifyGrades = {"add a grade", "remove a grade"};
        var modifyGradesMenu = ui.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modifyGrades, modifyGrades[0]);

        //if user wants to add a grade
        if (modifyGradesMenu == 0) {
            String inputStudent = removeSpaceEnd(ui.showInputDialog(null, "Type the name of the student" + "\nList of Students\n" + findClassList(inputClassCode).printClassroom(), "Markbock", 3));
            String gradeInput = removeSpaceEnd(ui.showInputDialog(null,
                    "What percentage grade would you like to add\ndeciamls allowed only numbers!", "Markbock", 3));

            try {
                findStudent(inputStudent, findClassList(inputClassCode)).addMark(Double.valueOf(gradeInput));
                uiShowMessage("Grade has been added!");
            } catch (Exception e) {
                uiShowMessage("You did not type a valid input");
            }

            //if user wants to remove a grade
        } else {
            String inputStudent = removeSpaceEnd(ui.showInputDialog(null, "Type the name of the student" + "\nList of Students\n" + findClassList(inputClassCode).printClassroom(), "Markbock", 3));
            String output = inputStudent + "'s marks:";
            ArrayList<Double> temp = new ArrayList<Double>();
            temp = findClassList(inputClassCode).getStudent(inputStudent).getMarks();

            //loop to find the student from the list of classes
            for (int i = 0; i < findStudent(inputStudent, findClassList(inputClassCode)).getMarks().size(); i++) {
                output += "\n";
                output += temp.get(i);
                output += "%";
            }//end of for loop

            output += "\nWhich mark would you like to remove (type the index number starting from 1)";
            String removeMark = removeSpaceEnd(ui.showInputDialog(null, output, "Markbock", 3));
            try {
                findClassList(inputClassCode).getStudent(inputStudent).removeMark(Integer.valueOf(removeMark));
                uiShowMessage("That mark has been removed!");
            } catch (Exception e) {
                uiShowMessage("You did not type a valid input");
            }
        }
    }//end of modifying student grade method


    //create a method that makes it less repetitive to access a Student's statistics
    private void doAccessStudentStatistics(String inputClassCode) {
        String[] studentStatistic = {"get student average", "get student median", "show all grades"};
        var studentStatisticMenu = ui.showOptionDialog(null,
                "What would you like to do?", "Markbock", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, studentStatistic, studentStatistic[0]);


        //if user wants to get student average
        if (studentStatisticMenu == 0) {
            String inputStudent = removeSpaceEnd(ui.showInputDialog(null, "Type the name of the student" + "\nList of Students\n"
                    + findClassList(inputClassCode).printClassroom(), "Markbock", 3));


            uiShowMessage(inputStudent + " has an average of " + findStudent(inputStudent, findClassList(inputClassCode)).getAverageMarkM() + "%");

            //if user wants to get student medians
        } else if (studentStatisticMenu == 1) {
            String inputStudent = removeSpaceEnd(ui.showInputDialog(null, "Type the name of the student" + "\nList of Students\n"
                    + findClassList(inputClassCode).printClassroom(), "Markbock", 3));
            uiShowMessage(inputStudent + " has a median " + findStudent(inputStudent, findClassList(inputClassCode)).getMedian());

            //if user wants to get all student grades
        } else {
            String inputStudent = removeSpaceEnd(ui.showInputDialog(null, "Type the name of the student" + "\nList of Students\n"
                    + findClassList(inputClassCode).printClassroom(), "Markbock", 3));
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
    }//end of accessing student statistics method


    //create a method that searches for and returns a ClassList object given an arraylist of ClassList objects
    private ClassList findClassList(String classCode) {
        for (int i = 0; i < listOfCLasses.size(); i++) {
            if (listOfCLasses.get(i).getClassCode().equals(classCode)) {
                return listOfCLasses.get(i);
            }
        }
        return null;
    }//end of find classlist method


    //create a method that locates a Student object given a student's name
    private Student findStudent(String studentName, ClassList c) {
        if (c.getStudent(studentName) != null) {
            return c.getStudent(studentName);
        }
        return null;
    }


    //create a method that turns a String in an array of doubles
    private double[] stringToDoubleArray(String m) {
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
    private String removeSpaceEnd(String a) {
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


class JOptionPaneFullUI implements UIInterfaceFull {

    @Override
    public String showInputDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException {
        return JOptionPane.showInputDialog(null, message, "Markbock", 3);
    }

    @Override
    public int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) throws HeadlessException {
        return JOptionPane.showOptionDialog(null, message, "Markbock",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, initialValue);
    }

    @Override
    public void showMessageDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException {
        JOptionPane.showMessageDialog(null, message, "Markbock", 3);
    }
}

//TODO: list of things that don't work
// remove