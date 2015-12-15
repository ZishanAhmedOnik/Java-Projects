package mediaPlayer.pac;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class FileControls extends HBox {
	public Button openButton;
	public Button addToListButton;
	public MenuBar menuBar;
	public Menu fileMenu;
	public MenuItem open;
	public MenuItem addToList;
	
	public FileControls() {
		
		openButton = new Button("Open");
		addToListButton = new Button("Add To List");
		
		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		
		open = new MenuItem("Open");
		addToList = new MenuItem("Add to list");
		
		menuBar.getMenus().addAll(fileMenu);
		fileMenu.getItems().addAll(open, addToList);
		
		this.getChildren().addAll(menuBar);
	}
}
