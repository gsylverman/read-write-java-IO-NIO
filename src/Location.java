import java.io.Serializable;

public class Location implements Serializable {
    private final int id;
    private final String description;

    public Location(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
