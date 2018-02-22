package sample;

public class Rectangle implements Drawing {
    private double x,y,width, height;

    public Rectangle(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean isPosInDrawing(double x, double y){
        return x>=this.x && y>= this.y && x<=this.x+width && y<= this.y+height;
    }

    @Override
    public boolean isDrawingInRectangle(double x, double y, double width, double height){
        return false;
    }

    @Override
    public Drawing translate(double x, double y) {
        return new Rectangle(this.x+x, this.y+y, width, height);
    }
}
