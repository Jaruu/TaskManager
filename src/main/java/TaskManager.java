import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    Map<String, Task> userTask = new HashMap<>();

    public TaskManager() throws IOException {

        CSVReader csvReader = new CSVReader(new FileReader("D:\\Java\\projekty\\menadzerZadan\\src\\main\\resources\\task.csv"), ';');

        for (String[] row : csvReader.readAll()) {
            userTask.put(row[0], new Task(row[1], row[2], (row[3].equals("1"))));
        }
        for (Map.Entry<String, Task> entry : userTask.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", value: " + entry.getValue());
        }
        System.out.println();
    }

    public void addTask(String login, String value) {
        Task task = new Task(login, value, false);
        userTask.put(task.getUuid(), task);
        for (Map.Entry<String, Task> entry : userTask.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }

    public void accomplish(String taskUuid) {
        userTask.replace(taskUuid, new Task(userTask.get(taskUuid).getLogin(), userTask.get(taskUuid).getValue(), true));
    }

    public void allUserTasks(String login) {
        List<Task> getAllUserTasks = new ArrayList<>();
        for (Map.Entry<String, Task> entry : userTask.entrySet()) {
            if (entry.getValue().getLogin().equals(login))
                getAllUserTasks.add(new Task(entry.getValue().getLogin(), entry.getValue().getValue(), entry.getValue().isAccomplished()));
        }
        for (Task getAllUserTask : getAllUserTasks) {
            System.out.println("Użytkownik: " + getAllUserTask.getLogin() + ", zadanie: " + getAllUserTask.getValue() +
                    ", czy wykonane: " + getAllUserTask.isAccomplished());
        }
    }

    public void updateFile() throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter("D:\\Java\\projekty\\menadzerZadan\\src\\main\\resources\\task.csv"), ';');
        List<String[]> userTaskToCSV = new ArrayList<>();

        for (Task task : userTask.values()) {
            String temp;
            if (task.isAccomplished()) {
                temp = "1";
            } else temp = "0";
            userTaskToCSV.add(new String[]{task.getUuid(), task.getLogin(), task.getValue(), temp});
        }
        csvWriter.writeAll(userTaskToCSV);
        csvWriter.close();
    }
}
