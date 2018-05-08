import java.io.IOException;
import java.util.Scanner;

public class Application {

    private State state = State.NOONE_LOGGED_IN;
    private Scanner wejscie = new Scanner(System.in);
    private int menu;
    private String password;
    private String role;
    private String login;

    public void printMenu() throws IOException {

        UserManager userManager = new UserManager();
        User loggedUser = new User();

        while (state == State.NOONE_LOGGED_IN) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Proszę wybrać menu:");
            menu = wejscie.nextInt();

            switch (menu) {
                case 1:
                    System.out.println("REJESTRACJA");
                    System.out.println("Proszę podać login:");
                    login = wejscie.next();
                    System.out.println("Proszę podać hasło");
                    password = wejscie.next();
                    System.out.println("Jaki jest Twój status (Manager czy Pracownik)? M/P:");
                    role = wejscie.next().toUpperCase();
                    if (role == "M") {
                        role = "ROLE_MANAGER";
                    } else role = "ROLE_EMPLOYEE";
                    userManager.register(login, password, role);
                case 2:
                    System.out.println("LOGOWANIE");
                    System.out.println("Proszę podać login:");
                    login = wejscie.next();
                    System.out.println("Proszę podać hasło");
                    password = wejscie.next();
                    if (userManager.matches(login, password)) {
                        state = State.LOGGED_IN;
                        loggedUser = new User(login, password, userManager.userMap.get(login).getRole().name());
                        printMenu();
                    }
                case 3:
                    System.out.println("Wyłączenie aplikacji");
                    System.exit(0);
                default:
                    System.out.println("Wybrano błędne menu, proszę spróbować poniwnie:");
                    printMenu();
            }
        }
        while (state == State.LOGGED_IN) {
            System.out.println("1. Przeglądaj zadania");
            System.out.println("2. Zaznacz zadanie jako wykonane");
            System.out.println("3. Wyloguj");
            if (loggedUser.getRole() == Role.ROLE_MANAGER) {
                System.out.println("4. Dodaj zadanie");
            }
            System.out.println("Proszę wybrać menu:");
            menu = wejscie.nextInt();
        }
    }
}
