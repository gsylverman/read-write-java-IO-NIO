import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class SOmeClass {
    private static Map<Integer, Location> list = new HashMap<>();

    public static void main(String[] args) {
        list.put(1, new Location(1, "In the road"));
        list.put(2, new Location(2, "Lost in the forest"));
        list.put(3, new Location(3, "Swimming in the river"));
        list.put(4, new Location(4, "Climbing the mountain"));

        Path filePath = FileSystems.getDefault().getPath("someFile.txt"); // Relative Path
        filePath = Paths.get("C:\\Users\\Gavril\\Desktop\\date.txt");     // Absolute Path

        //Get absolute path of working directory
        filePath = Paths.get(".");
        Path currentPath = filePath.toAbsolutePath();
        System.out.println(currentPath);

        filePath = Paths.get(".","someDir","date.txt"); // compose Path
        readTextFileWithNIO(filePath);
    }

    private static void readTextFileWithNIO(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                System.out.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeTxtWithNIO(Path path) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (Location location : list.values()) {
                bufferedWriter.write(location.getDescription() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readObjectsWithNIO() {
        Path filePath = FileSystems.getDefault().getPath("someFile.dat");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(filePath)))) {
            boolean endOfFile = false;
            while (!endOfFile) {
                try {
                    Location location = (Location) objectInputStream.readObject();
                    System.out.println(location.getDescription());
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeObjectsWithNIO() {
        Path filePath = FileSystems.getDefault().getPath("someFile.dat");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(filePath)))) {
            for (Location location : list.values()) {
                objectOutputStream.writeObject(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readObjects() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data.dat")))) {
            boolean endOfFile = false;
            while (!endOfFile) {
                try {
                    Location location = (Location) objectInputStream.readObject();
                    System.out.println(location.getDescription());
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeObjects() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data.dat")))) {
            for (Location location : list.values()) {
                objectOutputStream.writeObject(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readTxt() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Gavril\\Desktop\\date.txt"))) {
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String[] inputArray = input.split(",");
                int id = Integer.parseInt(inputArray[0]);
                String description = inputArray[1];
                System.out.println(id + ", " + description);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeTxt() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Gavril\\Desktop\\date.txt"))) {
            for (Integer key : list.keySet()) {
                bufferedWriter.write(key + "," + list.get(key).getDescription() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
