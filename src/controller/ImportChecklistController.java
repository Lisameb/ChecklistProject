package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.checklist_item.Export;
import view.ImportChecklistView;
import view.MenuView;
import view.UseChecklistView;

public class ImportChecklistController implements ActionListener{

	private ImportChecklistView view;
	
	public ImportChecklistController(ImportChecklistView view) {
		this.view=view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src==view.btnChooseFile) {
			//nicht fertig
			JFileChooser file = new JFileChooser();
			int returnVal = file.showSaveDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	            String path = file.getSelectedFile().getPath();
	            File fileToSave = new File(path);
	            fileToSave.getAbsolutePath();
	        } else {
	        	JOptionPane.showMessageDialog(null,"Path not found :(");
	        }
		}
		
		if (src==view.btnImport) {
			//....
			view.dispose();
			UseChecklistView use = new UseChecklistView();
			use.getFrame().setVisible(true);
			
		}
	}

}
