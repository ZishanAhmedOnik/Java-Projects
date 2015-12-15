package mediaPlayer.pac;

import javafx.scene.layout.BorderPane;

public class LayoutManager extends BorderPane{
	public Controls controls;
	public FileControls fileControls;
	public MediaList mediaList;
	
	public LayoutManager() {
		mediaList = new MediaList();
		this.setLeft(mediaList);
		
		fileControls = new FileControls();
		this.setTop(fileControls);
		
		controls = new Controls();
		this.setBottom(controls);
	}
}
