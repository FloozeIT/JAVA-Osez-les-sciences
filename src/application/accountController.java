package application;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class accountController implements Initializable{
	
     @FXML
     private JFXButton validate;
     
     @FXML
     private JFXToggleButton orga;

     @FXML
     private JFXToggleButton etud;
     
     @FXML
     protected JFXTextField txtNom, txtPrenom, txtMail, user, pass, id;
     
     @FXML
     private Text txtCheck;
     
     public static JFXTextField userP;
     	
	 public void initialize(URL url, ResourceBundle rb) {
		 userP=user;
	 }
	 
	 public void check(ActionEvent event){
		 if(orga.isSelected()==true){
			 etud.setDisable(true);}
		 if(etud.isSelected()==true){
			 orga.setDisable(true);}
		 if(orga.isSelected()==false && etud.isSelected()==false){
			 orga.setDisable(false);
			 etud.setDisable(false);
		 }
	 }
	 
	 public void sortir(ActionEvent event) throws RowsExceededException, WriteException, IOException, BiffException {
		 
		 if(txtNom.getText().equals("") || txtPrenom.getText().equals("") || txtMail.getText().equals("") || 
				 user.getText().equals("") || pass.getText().equals("")|| (orga.isSelected()==false && etud.isSelected()==false)){
			 txtCheck.setText("Merci de renseigner tous les champs !");
			 txtCheck.setFont(Font.font(14));
			 txtCheck.setFill(Paint.valueOf("#ff0000"));
		 }else{	 
		 if(txtNom.getText().matches("^[a-zA-ZÀ-ÿ]* ?-?[a-zA-ZÀ-ÿ]*$")==false){
			 txtCheck.setText("Nom non valide !");
			 txtCheck.setFont(Font.font(14));
			 txtCheck.setFill(Paint.valueOf("#ff0000"));
		 }else{
			 if(txtPrenom.getText().matches("^[a-zA-ZÀ-ÿ]+([a-zA-ZÀ-ÿ]*-[a-zA-ZÀ-ÿ]+)*$")==false){
				 txtCheck.setText("Prénom non valide !");
				 txtCheck.setFont(Font.font(14));
				 txtCheck.setFill(Paint.valueOf("#ff0000"));
			 }
			 else{
				 if(txtMail.getText().matches("^[_a-z0-9-]+(.[a-z0-9-]+)@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$")==false){
					 txtCheck.setText("Mail non valide !");
					 txtCheck.setFont(Font.font(14));
					 txtCheck.setFill(Paint.valueOf("#ff0000"));
				 }
				 else{
					 if(id.getText().equals("14789")==false && id.getText().equals("789123")==false){
						 txtCheck.setText("ID non valide !");
						 txtCheck.setFont(Font.font(14));
						 txtCheck.setFill(Paint.valueOf("#ff0000"));
					 }
				 
		 else{
		 
		 WritableWorkbook workbook = null;
		 String User = System.getProperty("user.name");
			try {	
				File ins = new File(Main.chemin);
				if(ins.isFile()==false){
					workbook = Workbook.createWorkbook(new File(Main.chemin));
					workbook.createSheet("Login", 0); 
					workbook.createSheet("Création_Lundi", 1);
					workbook.createSheet("Création_Mardi", 2);
					workbook.createSheet("Création_Mercredi", 3);
					workbook.createSheet("Création_Jeudi", 4);
					workbook.createSheet("Création_Vendredi", 5);
					workbook.createSheet("Inscription_Lundi", 6);
					workbook.createSheet("Inscription_Mardi", 7);
					workbook.createSheet("Inscription_Mercredi", 8);
					workbook.createSheet("Inscription_Jeudi", 9);
					workbook.createSheet("Inscription_Vendredi", 10);
					workbook.createSheet("Affectation", 11);
					workbook.write();
				}
			}
			finally {
				if(workbook!=null){
					workbook.close(); 
				}
			}
			int userExist=0;
			Workbook workbook2 = null;
			try {	
					File ins = new File(Main.chemin);
					workbook2 = Workbook.getWorkbook(ins);
					File outFile = new File(Main.cheminTemp);
					WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook2);
					 
					WritableSheet dataSheet = wwb.getSheet(0);
					
					dataSheet.addCell(new Label(0, 0, "ID"));
					dataSheet.addCell(new Label(1, 0, "Username"));
					dataSheet.addCell(new Label(2, 0, "Password"));
					dataSheet.addCell(new Label(3, 0, "Nom"));
					dataSheet.addCell(new Label(4, 0, "Prénom"));
					dataSheet.addCell(new Label(5, 0, "Mail"));
					dataSheet.addCell(new Label(6, 0, "Statut"));
					
					int i = dataSheet.getColumn(0).length;					
					
					for(int o=1; o<=i; o++){
						if(user.getText().equals(dataSheet.getCell(1,o).getContents())){
							userExist = 1;
							txtCheck.setText("Username déjà utilisé !");
							txtCheck.setFont(Font.font(14));
							txtCheck.setFill(Paint.valueOf("#ff0000"));
						}
					}if(userExist==0){
					
					dataSheet.addCell(new Number(0, i, i)); 
					dataSheet.addCell(new Label(1, i, user.getText()));
					dataSheet.addCell(new Label(2, i, encrypt(pass.getText(), "KEYCRYPT")));
					dataSheet.addCell(new Label(3, i, txtNom.getText()));
					dataSheet.addCell(new Label(4, i, txtPrenom.getText()));
					dataSheet.addCell(new Label(5, i, txtMail.getText()));
					if(orga.isSelected()){
						dataSheet.addCell(new Label(6, i, "Organisateur"));}
					if(etud.isSelected()){
						dataSheet.addCell(new Label(6, i, "Etudiant"));}
					
					WritableSheet dataSheet2 = wwb.getSheet(11);
					
					dataSheet2.addCell(new Label(0, 0, "Tuteur"));
					dataSheet2.addCell(new Label(1, 0, "Etudiant"));}
					
					wwb.write();
					wwb.close(); 
					               
					File insb = new File(Main.chemin);
					insb.delete();
					               
					File insc = new File(Main.chemin);
					outFile.renameTo(insc);
			}
			
			finally {
				if(workbook2!=null){
						workbook2.close();
				}
			}
		 
			if(userExist==0)
				retour(event);
		 }}}}}
	 }
	 
	 public void retour(ActionEvent event){
		 LoginController.drawerCreateP.setStyle("-fx-translate-y:-695");
		 LoginController.paneColorP.setOpacity(1);
		 LoginController.paneInfoP.setOpacity(1);
	 }
	 
	 private static String encrypt(String password,String key){
			try{
				Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
				Cipher cipher=Cipher.getInstance("Blowfish");
				cipher.init(Cipher.ENCRYPT_MODE,clef);
				return new String(cipher.doFinal(password.getBytes()));
			}
			catch (Exception e){
				return null;
			}
		}
}
