public class LoginOccupiedException extends RuntimeException {

    public LoginOccupiedException() {
        super("Podany login już istnieje. Proszę wybrać inny.");
    }
}
