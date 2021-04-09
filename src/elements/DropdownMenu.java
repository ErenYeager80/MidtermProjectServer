package elements;

import javafx.scene.control.*;
import javafx.stage.StageStyle;
import pages.PrepareBoard;

import java.util.Optional;


public class DropdownMenu extends ContextMenu {
    public DropdownMenu(boolean removeAble, int x, int y) {

        Menu AddItem = new Menu(PrepareBoard.board[x][y].hasElement() ? "Replace" : "Add");
        MenuItem wall = new MenuItem("Wall");
        MenuItem slow = new MenuItem("Slow");
        MenuItem star = new MenuItem("Star");

        AddItem.getItems().addAll(wall, slow, star);


        MenuItem removeItem = new MenuItem("Remove");
        removeItem.setDisable(!removeAble);

        wall.setOnAction(e -> {
            if (PrepareBoard.board[x][y].hasElement()) {
                PrepareBoard.board[x][y].getElement().setVisible(false);
            }
            PrepareBoard.walls[x][y].setVisible(true);
            PrepareBoard.board[x][y].setElement(PrepareBoard.walls[x][y]);
        });

        slow.setOnAction(e -> {

            TextInputDialog dialog = new TextInputDialog();

            dialog.setTitle("Set value");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.setContentText("Pleas enter slow value:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(s ->{
                if (PrepareBoard.board[x][y].hasElement()) {
                    PrepareBoard.board[x][y].getElement().setVisible(false);
                }
                PrepareBoard.slows[x][y].setVisible(true);
                PrepareBoard.slows[x][y].setValue(Integer.parseInt(s));
                PrepareBoard.board[x][y].setElement(PrepareBoard.slows[x][y]);
            } );

        });

        star.setOnAction(e -> {
            if (PrepareBoard.board[x][y].hasElement()) {
                PrepareBoard.board[x][y].getElement().setVisible(false);
            }
            PrepareBoard.stars[x][y].setVisible(true);
            PrepareBoard.board[x][y].setElement(PrepareBoard.stars[x][y]);
        });

        removeItem.setOnAction(e -> {
            PrepareBoard.board[x][y].getElement().setVisible(false);
            PrepareBoard.board[x][y].setElement(null);
        });
        // Add MenuItem to ContextMenu
        getItems().addAll(AddItem, removeItem);
    }

}