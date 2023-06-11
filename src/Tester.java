import java.util.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.net.*;
import java.awt.*;

public class Tester {

    public static void main(String[] args) throws Exception {

        String[] buttons = { "Yes", "No", "EXIT"};
        boolean inUse = true;

        final ImageIcon icon = new ImageIcon("C:/Users/349611912/Downloads/l.png");

        JOptionPane.showMessageDialog(null,"Hello, Welcome to Markbock.", "Markbock", JOptionPane.INFORMATION_MESSAGE, icon);

        UIManager.put("OptionPane.minimumSize",new Dimension(250,250));

        JLabel label = new JLabel("Are you a returning user?");
        label.setFont(new Font("Arial", Font.BOLD, 80));

        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,60)));

        //while loop so user can keep adding new students or do something else
        while(inUse) {

            String tempClassCode;
            String tempStudentName;
            String tempInput;
            double[] tempDArray;

            ArrayList<ArrayList<Student>> tempStudents = new ArrayList<ArrayList<Student>>();


            var yesOrNo = JOptionPane.showOptionDialog(null, label, "Select an Option",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

            switch (yesOrNo) {
                case 0:
                    JOptionPane.showMessageDialog(null, "You chose yes!");
                    break;
                case 1:
                    tempClassCode = JOptionPane.showInputDialog(null, "Enter your class code here:");
                    tempStudentName = JOptionPane.showInputDialog(null, "Input the name of your first student (first letter uppercase)");
                    tempInput = JOptionPane.showInputDialog(null, "Input a list of the students marks seperated by a space");
                    //tempDArray = idk(tempInput);
                    tempStudents.add(new ArrayList<Student>());
                    //tempStudents.get(0).add(new Student(tempStudentName,tempDArray));
                    ClassList c = new ClassList(tempClassCode, tempStudents);
                    System.out.println(tempInput);
                    break;
                case 2:
                    JLabel cancel = new JLabel("You chose to exit."+"\n Thank you for using Markbock!");
                    cancel.setFont(new Font("Arial", Font.BOLD, 60));
                    JOptionPane.showMessageDialog(null, cancel);
                    inUse = false;
                    break;
            }

        }//end of while loop in use
    }//end of main
}//end of class