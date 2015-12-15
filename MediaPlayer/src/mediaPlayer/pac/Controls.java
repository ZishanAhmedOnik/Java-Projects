package mediaPlayer.pac;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Controls extends HBox {
	Button playButton;
	Button pauseButton;
	Button stopButton;
	Slider volumeControl;
	Slider durationSlider;
	
	public Controls() {
		super(10);
		
		this.setPadding(new Insets(5));
		
		playButton = new Button("Play");
		pauseButton = new Button("Pause");
		stopButton = new Button("Stop");
		
		volumeControl = new Slider();
		volumeControl.setMin(0);
		volumeControl.setMax(100);
		volumeControl.setValue(50);
		
		durationSlider = new Slider();
		HBox.setHgrow(durationSlider, Priority.ALWAYS);
		//durationSlider.setMinWidth(50);
		//durationSlider.setMaxWidth(Double.MAX_VALUE);
		
		this.getChildren().addAll(playButton, pauseButton, stopButton, volumeControl, durationSlider);
		this.setAlignment(Pos.BASELINE_CENTER);
	}
}
