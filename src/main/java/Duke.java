import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static Task[] task = new Task[100];
    private static Integer taskNo = 0;
    private static ArrayList<Task> ArrayTask = new ArrayList<Task>();
    private static Integer listNo = 0;
    private Ui ui;

    public Duke(String filename){
        ui = new Ui();
/*        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } */
    }

    public static void addTask(Task t) {
        //task[taskNo] = t;
        //taskNo++;
        ArrayTask.add(t);
        //listNo++;
    }

    public void run(){
        ui.welcomeMessage();
    }

    public static void main(String[] args) {

        new Duke("D:\\git\\output.txt").run();

        Message.welcomeMessage(); // Duke welcome message

        String bye_word = "bye";
        String user_input = "";
        int index = 0;

        String command = ""; // initialise command to loop while() until "bye"

        Storage.LoadFile(ArrayTask);

        while (!command.equals(bye_word)) {
            Scanner in = new Scanner(System.in);
            user_input = in.nextLine();
            command = user_input.split(" ")[0].toLowerCase();
            switch (command) {
                case "list":
                    Message.listMessage(ArrayTask, ArrayTask.size());
                    break;

                case "todo":
                    if (ErrType.TaskCheck(user_input)) {
                        addTask(new Todo(user_input.replace("todo", "").trim()));
                        Message.acknowledgeMessage(ArrayTask, ArrayTask.size());
                    }
                    break;

                case "deadline":
                    if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                        addTask(new Deadline(user_input.split("/")[0].replace("deadline ", ""),
                                user_input.split("/")[1].replace("by", "")));
                        Message.acknowledgeMessage(ArrayTask, ArrayTask.size());
                    }
                    break;

                case "event":
                    if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                        addTask(new Event(user_input.split("/")[0].replace("event ", ""),
                                user_input.split("/")[1].replace("at", "")));
                        Message.acknowledgeMessage(ArrayTask, ArrayTask.size());
                    }
                    break;

                case "done":
                    index = ErrType.toInteger(user_input.split(" ")[1], ArrayTask.size()); // with Exceptions handling
                    if (index == -1) {
                        System.out.println("\tPlease key a valid task number.");
                        break;
                    }
                    Message.doneMessage(ArrayTask, index);
                    break;

                case "delete":
                    index = ErrType.toInteger(user_input.split(" ")[1], ArrayTask.size()); // with Exceptions handling
                    if (index == -1) {
                        System.out.println("\tPlease key a valid task number.");
                        break;
                    }
                    Message.deleteMessage(ArrayTask, index);
                    break;

                case "bye": // "bye" command will end loop after looping back to while()
                    break;

                default:   // any other command will be considered as error
                    System.out.println("\tOops!! You have key an invalid command.");
                    break;
            }
            Storage.SaveFile(ArrayTask);
        }
        Message.byeMessage();  // */
    }
}