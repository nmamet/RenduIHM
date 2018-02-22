package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Controller {
    @FXML
    VBox vBoxLeft;

    @FXML
    RadioButton radioSelectMove;

    @FXML
    RadioButton radioEllipse;

    @FXML
    RadioButton radioRectangle;

    @FXML
    RadioButton radioLine;

    @FXML
    ColorPicker colorPicker;

    @FXML
    VBox vBoxButtons;

    @FXML
    Button deleteButton;

    @FXML
    Button cloneButton;

    @FXML
    Canvas canvas;

    ToggleGroup radioButtonGroup;

    Drawer d;

    public void disableButtons(boolean b){
        deleteButton.setDisable(b);
        cloneButton.setDisable(b);
    }

    @FXML
    public void clickOnCanvas(MouseEvent e){
        if(radioButtonGroup.getSelectedToggle() == radioSelectMove){
            d.selectOne(e.getX(), e.getY());
        } else {
            d.draw(e.getX(), e.getY());
        }
    }

    public void draggedReleasedOnCanvas(MouseEvent e){
        if(radioButtonGroup.getSelectedToggle() == radioSelectMove){
            d.move(e.getX(), e.getY());
            System.out.println("drag released");
        }
    }

    public void initialize(){

        d = new Drawer(canvas.getGraphicsContext2D(), colorPicker.getValue());

        colorPicker.valueProperty().addListener(new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                d.setColor(newValue);
            }
        });

        radioButtonGroup = new ToggleGroup();
        radioEllipse.setToggleGroup(radioButtonGroup);
        //radioEllipse.setUserData(Drawer.Drawings.CIRCLE);
        radioLine.setToggleGroup(radioButtonGroup);
        //radioLine.setUserData(Drawer.Drawings.LINE);
        radioRectangle.setToggleGroup(radioButtonGroup);
        //radioRectangle.setUserData(Drawer.Drawings.RECTANGLE);
        radioSelectMove.setToggleGroup(radioButtonGroup);

        radioButtonGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(newValue == radioSelectMove){
                    disableButtons(false);
                    d.setMode(Drawer.Drawings.NONE);
                } else {
                    if(oldValue == radioSelectMove) {
                        disableButtons(true);
                    }
                    if(newValue == radioEllipse){
                        d.setMode(Drawer.Drawings.CIRCLE);
                    } else if(newValue == radioLine){
                        d.setMode(Drawer.Drawings.LINE);
                    } else if(newValue == radioRectangle){
                        d.setMode(Drawer.Drawings.RECTANGLE);
                    }
                }
            }
        });

        disableButtons(true);
        d.setColor(colorPicker.getValue());
    }
}
