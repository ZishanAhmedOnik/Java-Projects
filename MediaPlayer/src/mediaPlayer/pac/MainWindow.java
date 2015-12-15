package mediaPlayer.pac;

import java.io.File;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainWindow extends Application {
	LayoutManager layoutManager;
	MediaManager mediaManager;
	FileChooser fileChoser;
	Duration dur;
	Scene sc;
	
	String current;
	boolean durationSliderTimeSet;
	
	@Override
	public void start(Stage primaryStage) {
		mediaManager = new MediaManager();
		
		layoutManager = new LayoutManager();
		layoutManager.setCenter(mediaManager);
		
		initializeControls();
		
		sc = new Scene(layoutManager, 1000, 700);
		
		primaryStage.setScene(sc);
		primaryStage.show();
	}
	
	public void initializeControls() {
		Stage str = new Stage();
		fileChoser = new FileChooser();
		
		layoutManager.controls.playButton.setOnAction((ActionEvent evt) -> {
			mediaManager.play();
		});
		
		layoutManager.controls.pauseButton.setOnAction((ActionEvent evt) -> {
			mediaManager.pause();
		});
		
		layoutManager.controls.stopButton.setOnAction((ActionEvent evt) -> {
			mediaManager.stop();
		});
		
		layoutManager.mediaList.setOnMouseClicked((MouseEvent mevt) -> {
			String fileName = layoutManager.mediaList.getSelectionModel().getSelectedItem();
			current = fileName;
			
			if(mediaManager.mediaPlayer != null) {
				mediaManager.mediaPlayer.stop();
			}
			
			File fp = layoutManager.mediaList.fileAddress.get(fileName);
			mediaManager.add(fp);
			
			initSlider();
		});
		
		layoutManager.controls.volumeControl.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				mediaManager.mediaPlayer.setVolume(arg2.doubleValue() / 200);
				//System.out.println(arg2.doubleValue() / 200);
			}
			
		});
		
		layoutManager.fileControls.open.setOnAction((ActionEvent evt) -> {
			layoutManager.mediaList.fileAddress.clear();
			layoutManager.mediaList.fileNames.clear();
			
			File newFile = fileChoser.showOpenDialog(str);
			mediaManager.stop();
			layoutManager.mediaList.addToList(newFile);
			mediaManager.add(newFile);
			
			mediaManager.play();
			initSlider();
			
			current = newFile.getName();
		});
		
		layoutManager.fileControls.addToList.setOnAction((ActionEvent evt) -> {
			File newFile = fileChoser.showOpenDialog(str);
			
			layoutManager.mediaList.addToList(newFile);
		});
	}
	
	void initSlider() {
		layoutManager.controls.durationSlider.setMin(0.0);
		layoutManager.controls.durationSlider.setValue(0.0);
		durationSliderTimeSet = false;
		
		mediaManager.mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
			@Override
			public void changed(ObservableValue<? extends Duration> arg0,
					Duration arg1, Duration arg2) {
				layoutManager.controls.durationSlider.setValue(arg2.toSeconds());
				if(!mediaManager.mediaPlayer.getTotalDuration().toString().equals("UNKNOWN") && !durationSliderTimeSet) {
					layoutManager.controls.durationSlider.setMax(mediaManager.mediaPlayer.getTotalDuration().toSeconds());
					durationSliderTimeSet = true;
				}
				
				if(arg2.toSeconds() >= mediaManager.mediaPlayer.getTotalDuration().toSeconds()) {
					mediaManager.stop();
					playNext();
				}
			}
		});
		
		layoutManager.controls.durationSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				if(mediaManager.mediaPlayer != null) {
					if(layoutManager.controls.durationSlider.isValueChanging()) {
						mediaManager.mediaPlayer.seek(Duration.seconds(arg2.doubleValue()));
					}
				}
			}
		});
	}
	
	void playNext() {
		int index = layoutManager.mediaList.getCurrentIndex(current);
		if(index < (layoutManager.mediaList.fileNames.size() - 1)) {
			mediaManager.add(layoutManager.mediaList.fileAddress.get(layoutManager.mediaList.fileNames.get(index + 1)));
			mediaManager.play();
			
			initSlider();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
