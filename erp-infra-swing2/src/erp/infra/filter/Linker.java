package erp.infra.filter;

/**
 *
 * @author leonardo
 */
public class Linker {
    
    private String name;
    private LogicOperation operation;
    
    private Container leftContainer;
    private Container rightContainer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(LogicOperation operation) {
        this.operation = operation;
    }

    public Container getLeftContainer() {
        return leftContainer;
    }

    public void setLeftContainer(Container leftContainer) {
        this.leftContainer = leftContainer;
        if (leftContainer.getRightLinker() != this) {
            leftContainer.setRightLinker(this);
        }
    }

    public Container getRightContainer() {
        return rightContainer;
    }

    public void setRightContainer(Container rightContainer) {
        this.rightContainer = rightContainer;
        if (rightContainer.getLeftLinker() != this) {
            rightContainer.setLeftLinker(this);
        }
    }
    
}
