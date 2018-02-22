package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Drawer {

    public enum Drawings{
        CIRCLE, RECTANGLE, LINE, NONE
    }

    private GraphicsContext gc;
    private LinkedList<Drawing> drawings;
    private Drawings mode;
    private Color c;
    private ArrayList<Drawing> selected;

    public Drawer(GraphicsContext gc, Color c){
        this.gc = gc;
        drawings = new LinkedList<Drawing>();
        mode = Drawings.NONE;
        this.c = c;
        selected = new ArrayList<Drawing>();
    }

    public void setMode(Drawings mode){
        this.mode = mode;
    }

    public void draw(double x, double y){
        switch (mode) {
            case CIRCLE:
            {
                gc.fillOval(x,y,20.,20.);
                drawings.add(new Circle(x,y,20));
            } break;
            case RECTANGLE: {
                gc.fillRect(x,y,20,20);
                drawings.add(new Rectangle(x,y,20,20));
            } break;
            case LINE:
            {
                gc.strokeLine(x,y,x+20, y);
                drawings.add(new Line(x,y,20));
            } break;
            default:
                break;
        }
    }

    public void setColor(Color c){
        this.c = c;
        gc.setFill(c);
        gc.setStroke(c);
    }

    public void selectOne(double x, double y){
        selected.clear();
        boolean found = false;
        Iterator<Drawing> it = drawings.iterator();
        while(!found && it.hasNext()){
            Drawing d = it.next();
            if(d.isPosInDrawing(x,y)){
                found = true;
                selected.add(d);
            }
        }
    }

    public void selectMany(double x, double y, double width, double height){
        selected.clear();
        for (Drawing d : drawings) {
            if(d.isDrawingInRectangle(x,y,width,height)){
                selected.add(d);
            }
        }
    }

    public void deleteSelected(){
        int j=0;
        int i = 0;
        for (Iterator<Drawing> it = drawings.iterator(); it.hasNext();){
            if(it.next() == selected.get(j)) {
                j++;
                it.remove();
            }
        }
    }

    public void move(double x, double y){
        for(Drawing d : selected){
            drawings.add(d.translate(x,y));
        }
        deleteSelected();
    }

    public void clone2(){
        for(Drawing d : selected){
            drawings.add(d.translate(2.,2.));
        }
    }
}
