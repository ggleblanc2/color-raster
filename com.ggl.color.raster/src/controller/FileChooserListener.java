package com.ggl.color.raster.controller;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ggl.color.raster.model.ColorRasterModel;
import com.ggl.color.raster.view.ColorRasterFrame;

public class FileChooserListener implements ActionListener {

	private final ColorRasterFrame view;

	private final ColorRasterModel model;

	public FileChooserListener(ColorRasterFrame view, ColorRasterModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		model.setImage(null);

		JFileChooser fc = new JFileChooser(model.getDirectory());
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = 
				new FileNameExtensionFilter("Images", "jpeg", "jpg", "png");
		fc.addChoosableFileFilter(filter);
		setFileChooserFont(fc.getComponents());
		int res = fc.showOpenDialog(view.getFrame());

		if (res == JFileChooser.APPROVE_OPTION) {
			model.setDirectory(fc.getSelectedFile());
			try {
				model.setImage(ImageIO.read(fc.getSelectedFile()));
				view.updateImagePanel();
				view.setProgressBarValue(0);
				view.resetRowCount();
			} catch (IOException e) {
				e.printStackTrace();
			}
			view.updateFileNameField();
		}
	}

	private void setFileChooserFont(Component[] comp) {
		for (int x = 0; x < comp.length; x++) {
			// System.out.println( comp[x].toString() ); 
			// Trying to know the type of each
			// element in the JFileChooser.
			if (comp[x] instanceof Container)
				setFileChooserFont(((Container) comp[x]).getComponents());

			try {
				if (comp[x] instanceof JList || comp[x] instanceof JTable)
					comp[x].setFont(comp[x].getFont().deriveFont(16f));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

}
