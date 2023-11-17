package castlab.assignment.isoFileFormat.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Mp 4 box.
 */
public class Mp4Box {
    private long size;
    private String type;
    private List<Mp4Box> subBoxes;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Mp4Box> getSubBoxes() {
        return subBoxes;
    }

    public void setSubBoxes(List<Mp4Box> subBoxes) {
        this.subBoxes = subBoxes;
    }

    /**
     * Instantiates a new Mp 4 box.
     *
     * @param size the size
     * @param type the type
     */
    public Mp4Box(long size, String type) {
        this.size = size;
        this.type = type;
        this.subBoxes = new ArrayList<>();
    }

    public void addSubBox(Mp4Box subBox) {
        subBoxes.add(subBox);
    }
}
