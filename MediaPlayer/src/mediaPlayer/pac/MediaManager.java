package mediaPlayer.pac;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;

public class MediaManager extends MediaView {
	private Media media;
	public MediaPlayer mediaPlayer;
	boolean paused;
	
	public MediaManager() {
		paused = false;
	}
	
	public void add(File mediaFile) {
		media = new Media(mediaFile.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.25);
		
		this.setMediaPlayer(mediaPlayer);
		mediaPlayer.play();
	}
	
	public void play() {
		if(mediaPlayer != null) mediaPlayer.play();
	}
	
	public void pause() {
		if(mediaPlayer != null) {
			mediaPlayer.pause();
		}
	}
	
	public void stop() {
		if(mediaPlayer != null) mediaPlayer.stop();
	}
}
