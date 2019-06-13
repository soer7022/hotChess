package hotchess.framework;

public class Position {

    protected int row;
    protected int column;

    /**
     * Create a new position
     * @param row the row
     * @param column the column
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get the row of the position
     * @return the row of the position
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column of the position
     * @return the column of the position
     */
    public int getColumn() {
        return column;
    }

    /**
     * Check if position is equal
     * @param o object to check if is equal
     * @return boolean that presents whether it is equal or not
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != Position.class) return false;
        Position other = (Position) o;
        return getRow() == other.getRow() && getColumn() == other.getColumn();
    }

    @Override
    public String toString() {
        return "[" + getRow() + "," + getColumn() + "]";
    }

    @Override
    public int hashCode() {
        return 64*getRow()+getColumn();
    }
}
