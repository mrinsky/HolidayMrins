package modules.functional;

import main.Resources;
import model.User;

import java.math.BigInteger;

/**
 * Created by root on 05.04.15.
 */
public class Registration {
    /*************************
     * Constructors
     *************************/
    private Registration() {}
    /*************************
     * Methods
     *************************/
    public static void readLogin(String login) {
        if (checkLogin(login)) {
            throw new IllegalArgumentException(Resources.language.getLOGIN_EXCEPTION());
        }
    }
    public static void registration(String login, String pass1, String pass2) {
        readLogin(login);
        if (pass1.equals(pass2)) {

            BigInteger pass = new BigInteger(pass1.getBytes());
            User user = new User(login, pass, UserData.rsa.getPublicKey(),
                    UserData.rsa.getModulus());
            UserData.users.add(user);
            UserData.loadData(login, pass1);
        } else {
            throw new IllegalArgumentException(Resources.language.getPASS_EXCEPTION());
        }
    }

    private static boolean checkLogin(String login) {
        boolean result = false;
        for (int i = 0; i < UserData.users.size(); i++) {
            if (login.equals(UserData.users.get(i).getLogin())) {
                result = true;
            }
        }
        return result;
    }
}
