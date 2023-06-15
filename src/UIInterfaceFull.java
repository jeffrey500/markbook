import javax.swing.*;
import java.awt.*;

public interface UIInterfaceFull {
    String showInputDialog(Component parentComponent,
                           Object message, String title, int messageType)
            throws HeadlessException;

    int showOptionDialog(Component parentComponent,
                         Object message, String title, int optionType, int messageType,
                         Icon icon, Object[] options, Object initialValue)
            throws HeadlessException;

    void showMessageDialog(Component parentComponent,
                           Object message, String title, int messageType)
            throws HeadlessException;
}
