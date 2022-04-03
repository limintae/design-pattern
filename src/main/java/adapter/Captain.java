package adapter;

public class Captain {

    private RowingBoat rowingBoat;

    protected Captain() {};

    void row() {
        rowingBoat.row();
    }

    public void setRowingBoat(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

}
