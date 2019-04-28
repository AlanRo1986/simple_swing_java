package system.model;

/**
 * Created by alan on 2019/4/24.
 */
public class TreeNodeFileModel extends TreeNodeModel {

    private String filepath;
    private boolean isDirectory;

    public TreeNodeFileModel() {
    }

    public TreeNodeFileModel(String name,String path,boolean isDirectory) {
        this(name);
        this.filepath = path;
        this.isDirectory = isDirectory;
    }

    public TreeNodeFileModel(Object userObject) {
        super(userObject);
    }

    public TreeNodeFileModel(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    public String getFilepath() {
        return filepath;
    }

    public boolean isDirectory() {
        return isDirectory;
    }
}
