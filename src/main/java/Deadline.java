public class Deadline extends Task{
    private static String description;
    private static String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
