import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UserManager {

    Map<String, User> userMap = new HashMap<>();
    private Application application = new Application();

    public UserManager() throws IOException {

        CSVReader csvReader = new CSVReader(new FileReader("D:\\Java\\projekty\\menadzerZadan\\src\\main\\resources\\users.csv"), ';');

        for (String[] row : csvReader.readAll()) {
            userMap.put(row[0], new User(row[0], row[1], row[2]));
        }
    }

    public void register(String login, String password, String role) throws LoginOccupiedException, IOException {
        try {
            if (!userMap.containsKey(login)) {
                password = BCrypt.hashpw(password, BCrypt.gensalt());
                userMap.put(login, new User(login, password, role));
                updateFile();
                System.out.println("Użytkownik pomyślnie zarejestrowany");
                application.printMenu();
            } else throw new LoginOccupiedException();

        } catch (LoginOccupiedException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }

    boolean matches(String login, String password) {
        if (BCrypt.checkpw(password, userMap.get(login).getPassword())) {
            System.out.println("Logowanie przebiegło pomyślnie");
            return true;
        }
        return false;
    }

    public void updateFile() throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter("D:\\Java\\projekty\\menadzerZadan\\src\\main\\resources\\users.csv"), ';');
        List<String[]> userList = new ArrayList<>();

        for (User user : userMap.values()) {
            userList.add(new String[]{user.getLogin(), user.getPassword(), user.getRole().name()});
        }
        csvWriter.writeAll(userList);
        csvWriter.close();
    }
}

