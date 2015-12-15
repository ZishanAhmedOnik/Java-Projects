package mediaPlayer.pac;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class MediaList extends ListView<String>{
	ArrayList<String> fileNames;
	Map< String, File > fileAddress;
	ObservableList<String> obl;
	
	public MediaList() {
		fileNames = new ArrayList<String>();
		fileAddress = new TreeMap<String, File>();
	}
	
	public void addToList(File fileToAdd) {
		String fName = fileToAdd.getName();
		fileNames.add(fName);
		fileAddress.put(fName, fileToAdd);
		
		obl = FXCollections.observableArrayList(fileNames);
		this.setItems(obl);
	}
	
	int getCurrentIndex(String current) {
		for(int i = 0; i < fileNames.size(); i++) {
			if(current.equals(fileNames.get(i))) return i;
		}
		
		return -1;
	}
}
