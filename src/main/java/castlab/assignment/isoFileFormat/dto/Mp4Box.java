package castlab.assignment.isoFileFormat.dto;

import java.util.ArrayList;
import java.util.List;

public class Mp4Box {
    private int size;
    private String type;
    private List<Mp4Box> subBoxes;

    public Mp4Box(int size, String type) {
        this.size = size;
        this.type = type;
        this.subBoxes = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public List<Mp4Box> getSubBoxes() {
        return subBoxes;
    }

    public void addSubBox(Mp4Box subBox) {
        subBoxes.add(subBox);
    }
}
