package sample;

public interface Drawing {
    public boolean isPosInDrawing(double x, double y);
    public boolean isDrawingInRectangle(double x, double y, double width, double height);
    public Drawing translate(double x, double y);
}
