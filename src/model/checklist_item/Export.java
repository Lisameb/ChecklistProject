package model.checklist_item;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.*;

import model.DaoFactory;
import model.checklist.ChecklistDao_DB;
import model.checklist.ChecklistVo;
import model.item.ItemDao_DB;
import model.item.ItemVo;


//Source: https://www.baeldung.com/java-pdf-creation
// https://stackoverflow.com/questions/14602821/how-to-check-a-check-box-in-pdf-form-using-java-pdfbox-api


public class Export {
	private String checklist;
	private String path;
	private ArrayList<String> allItems;

	private ChecklistDao_DB checkDao;
	private Checklist_itemDao_DB checkItemDao;
	private ItemDao_DB itemDao;
	private DaoFactory daofactory = DaoFactory.getInstance();
	private PDCheckBox checkBox;

	public Export(String checklist, String path) {
		this.checklist = checklist;
		this.path = path;
		this.checkDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		this.itemDao = (ItemDao_DB) daofactory.getItemDao();
		this.checkItemDao = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
		this.createPDF();
	}

	public void createPDF() {
		ChecklistVo checklistVo = new ChecklistVo(checklist, daofactory.getCurrent_user());
		checklistVo.setChecklistID(checkDao.getChecklistID(checklistVo));

		allItems = checkItemDao.getItemsC(checklistVo.getChecklistID());

		try {
			PDDocument document = new PDDocument();

			PDPage firstPage = new PDPage();
			document.addPage(firstPage);

			PDAcroForm acroForm = new PDAcroForm(document);
			document.getDocumentCatalog().setAcroForm(acroForm);

			PDPageContentStream content = new PDPageContentStream(document, firstPage );
			content.beginText();
			content.setFont(PDType1Font.HELVETICA_BOLD, 18);
			content.newLineAtOffset(50, 700);

			content.showText(checklist);
			content.endText();
			ArrayList<PDAnnotationWidget> widgets = new ArrayList<>();

			int j = 650;
			for(int i = 0; i < allItems.size(); i++) {
				
				ItemVo item = new ItemVo(allItems.get(i));
				item.setItemID(itemDao.getItemID(item));
				Checklist_itemVo checkItem = new Checklist_itemVo(checklistVo.getChecklistID(), item.getItemID());
				int amount = checkItemDao.getAmount(checkItem);
				String itemName = allItems.get(i);
				boolean checked = checkItemDao.getChecked(checkItem);
			

				checkBox = new PDCheckBox(acroForm);
				checkBox.setPartialName(allItems.get(i));
				acroForm.getFields().add(checkBox);
				PDAnnotationWidget widget = checkBox.getWidgets().get(0);
				
				widget.setPage(firstPage);
				checkBox.check();
				firstPage.getAnnotations().add(widget);
				PDRectangle rect = new PDRectangle(50, j-1, 10, 10);
				//widget.setAnnotationFlags(4);
				widget.setRectangle(rect);
			
				widgets.add(widget);
//				PDAppearanceCharacteristicsDictionary appearanceCharacteristics = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
//	            appearanceCharacteristics.setBorderColour(new PDColor(new float[]{1, 1, 0}, PDDeviceRGB.INSTANCE));
//	            appearanceCharacteristics.setNormalCaption("4");
//	            widget.setAppearanceCharacteristics(appearanceCharacteristics);
	            
				if(checked==true) {
					checkBox.check();
				} else {
					checkBox.unCheck();
				}
			
				content.beginText();
				content.setFont(PDType1Font.HELVETICA, 12);
				content.newLine();
				content.newLineAtOffset(75, j);

				
				content.showText(itemName + " x" + amount);
				j-=25;
				content.endText();
			}
			content.close();
			document.save(path);
			document.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
