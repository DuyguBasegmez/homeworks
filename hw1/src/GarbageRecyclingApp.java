import java.io.IOException;

public class GarbageRecyclingApp {
    public static void main(String[] args) throws IOException {

        FileIO file = new FileIO("garbage.txt"); // Create an instance of FileIO
        TrashCan trashCan = (TrashCan) file.readTrashCan(); // Read the "garbage.txt"

        String emoji = "🗑️"; // Emoji for output design

        System.out.println("The Trash Can\nSize: " + trashCan.getItemCount() + "\n" +
                "Contents:");
        trashCan.displayItems();
        System.out.println(emoji.repeat(20) + "\n");

        // Separate operations from trashCan to bins
        int i = 0;
        while (i < trashCan.getItemCount()) {
            if (trashCan.separate(trashCan.trashList[i]) && trashCan.getIndexOf(trashCan.trashList[i]) != -1){
                i = trashCan.getIndexOf(trashCan.trashList[i]);
            }
            else {
                i++;
            }
        }
        // Displays contents of bins
        System.out.println("Plastic Recycle Bin\nSize: " + trashCan.plasticRecycleBin.getItemCount() + "\n" +
                "Contents:");
        trashCan.plasticRecycleBin.displayItems();
        System.out.println(emoji.repeat(20) + "\n");

        System.out.println("Paper Recycle Bin\nSize: " + trashCan.paperRecycleBin.getItemCount() + "\n" +
                "Contents:");
        trashCan.paperRecycleBin.displayItems();
        System.out.println(emoji.repeat(20) + "\n");

        System.out.println("Glass Recycle Bin\nSize: " + trashCan.glassRecycleBin.getItemCount() + "\n" +
                "Contents:");
        trashCan.glassRecycleBin.displayItems();
        System.out.println(emoji.repeat(20) + "\n");

        System.out.println("Fabric Recycle Bin\nSize: " + trashCan.fabricRecycleBin.getItemCount() + "\n" +
                "Contents:");
        trashCan.fabricRecycleBin.displayItems();
        System.out.println(emoji.repeat(20) + "\n");

        System.out.println("Metal Recycle Bin\nSize: " + trashCan.metalRecycleBin.getItemCount() + "\n" +
                "Contents:");
        trashCan.metalRecycleBin.displayItems();
        System.out.println(emoji.repeat(20) + "\n");

        System.out.println("Organic Recycle Bin\nSize: " + trashCan.organicRecycleBin.getItemCount() + "\n" +
                "Contents:");
        trashCan.organicRecycleBin.displayItems();
        System.out.println(emoji.repeat(20) + "\n");
        
        System.out.println("The Updated Trash Can\nSize: " + trashCan.getItemCount() + "\n" +
                "Contents:");
        file.updateTrashCan(trashCan);
        trashCan.displayItems();
      
    }
}