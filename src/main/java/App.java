import javax.swing.UIManager;
import controller.MainController;

public class App {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check if the required number of arguments are passed
        if (args.length < 3) {
            System.out.println("Usage: java App <db_url> <db_user> <db_password>");
            return;
        }

        // Use command line arguments
        String dbUrl = args[0];
        String dbUser = args[1];
        String dbPassword = args[2];

        MainController.getInstance(dbUrl, dbUser, dbPassword);
    }
}
