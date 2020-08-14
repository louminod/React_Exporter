package app;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Launcher {
	public static void main(String[] args) {
		File wpFolder = new File("REPERTOIRE REACT"); // TODO Changer le répertoire
		File wpSubFolders[] = wpFolder.listFiles();


		String destPath = "DESTINATION"; // TODO Changer la destination
		File destFolder = new File(destPath);
		if (!destFolder.exists())
			destFolder.mkdir();

		for (int i = 0; i < wpSubFolders.length; i++) {
			File newSubFolder = new File(destPath + "/" + wpSubFolders[i].getName());
			if (!newSubFolder.exists())
				newSubFolder.mkdir();

			File wpSubSubFolders[] = wpSubFolders[i].listFiles();

			for (int j = 0; j < wpSubSubFolders.length; j++) {
				if (!wpSubSubFolders[j].getName().contains("module")) {	
					try {
						if (wpSubSubFolders[j].isDirectory()) {
							File newSubSubFolder = new File(newSubFolder.getPath() + "/" + wpSubSubFolders[j].getName());
							FileUtils.copyDirectory(wpSubSubFolders[j], newSubSubFolder);
						}
						if (wpSubSubFolders[j].isFile()) {
							FileUtils.copyFile(wpSubSubFolders[j], new File(newSubFolder.getPath() + "/" + wpSubSubFolders[j].getName()));
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
