import java.util.UUID;

public class Task {

    private String uuid = UUID.randomUUID().toString();
    private String login;
    private String value;
    private boolean isAccomplished;

    public Task(String login, String value, boolean isAccomplished) {
        this.login = login;
        this.value = value;
        this.isAccomplished = isAccomplished;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isAccomplished() {
        return isAccomplished;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid='" + uuid + '\'' +
                ", login='" + login + '\'' +
                ", value='" + value + '\'' +
                ", isAccomplished=" + isAccomplished +
                '}';
    }
}
