import java.io.*;
import java.util.Scanner;

public class FileIO {
	TrashCan trashCan = new TrashCan();
    File garbageFile;
    
    public FileIO(String pathName) {
        garbageFile = new File(pathName);
    }
    
    public IBag<Garbage> readTrashCan() {
        try {
            Scanner garbage = new Scanner(garbageFile);
            while (garbage.hasNextLine()) {
                String line = garbage.nextLine();
                String[] splitLine = line.split(",");

                for (int i = 0; i < Integer.parseInt(splitLine[2].split(" ")[0]); i++) {
                    Garbage newGarbage = new Garbage(splitLine[0], splitLine[1]);
                    if (!trashCan.isFull()) {
                        trashCan.add(newGarbage);
                    }
                }
            }
            garbage.close();

        }catch (FileNotFoundException e) {
           throw new RuntimeException(e);
        }

        return trashCan;}
    

    public boolean updateTrashCan(TrashCan updatedTrashCan) throws IOException {
        String strings = "";
        TrashCan oldTrash = new TrashCan();
        @SuppressWarnings("unused")
		int count = 1;
        Garbage[] trashCan = updatedTrashCan.trashList;
        for (int i = 1; i <= updatedTrashCan.size; i++) {
            Garbage current = trashCan[i - 1];
            if (current == null) {
                continue;
            }
            boolean isNew = !oldTrash.contains(current);
            if (isNew && i > 1) {
                strings += trashCan[i - 2] + "," + current.type +  "\n";
                count = 1;
            } 
            else if (isNew) {
                count = 1;
            } 
            else {
                count++;
            }
            if (i == updatedTrashCan.size) {
                strings += trashCan[i - 1] + "," + current.type + "\n";
            }
            oldTrash.add(current);
        }
        try (FileOutputStream out = new FileOutputStream("updated-garbage.txt")) {
			byte[] theAllStrings = strings.getBytes();
			out.write(theAllStrings);}
        
		catch (FileNotFoundException e) {
	        throw new RuntimeException(e);
		}
        return false;
    }

}
    

        