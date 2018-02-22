package sample;

import javafx.scene.canvas.GraphicsContext;

public class Circle implements Drawing{

    private double x;
    private double y;
    private double r;

    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public boolean isPosInDrawing(double x, double y) {
        double xt = x-this.x;
        double yt = y-this.y;
        return (xt*xt+yt*yt<=r*r);
    }

    @Override
    public boolean isDrawingInRectangle(double x, double y, double width, double height){
        return false;
    }

    @Override
    public Drawing translate(double x, double y) {
        return new Circle(this.x+x, this.y+y, r);
    }
}
