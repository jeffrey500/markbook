import javax.swing.*;
import java.awt.*;

public interface UIInterface {

    String showInputDialog(Object message) throws HeadlessException;

    int showOptionDialog(Object message, Object[] options, Object initialValue) throws HeadlessException;

    void showMessageDialog(Object message) throws HeadlessException;
}
