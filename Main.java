
package app;

import javax.swing.SwingUtilities;
import vista.LoginForm;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
