package sample;

public class Line implements Drawing{
    double x,y,length;

    public Line(double x, double y, double length){
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public boolean isPosInDrawing(double x, double y){
        return (y == this.y) && (x>= this.x) && (x <= this.x+length);
    }

    @Override
    public boolean isDrawingInRectangle(double x, double y, double width, double height) {
        return (this.y >= y && this.y<=y+height) && ((this.x+length>=x) && (this.x <= x+width));
    }

    @Override
    public Drawing translate(double x, double y) {
        return new Line(this.x+x, this.y+y, length);
    }
}
