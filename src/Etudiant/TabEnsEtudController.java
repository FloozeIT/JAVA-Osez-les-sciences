package Etudiant;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import Organisateur.Cr�ationCoursController;
import Organisateur.contenuCr�ationController;
import Organisateur.TabEnsController.donn�esEns;
import Tuteur.contenuTuteurController;
import application.LoginController;
import application.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class TabEnsEtudController implements Initializable {

    @FXML
    private FlowPane main;
    @FXML
    private JFXTreeTableView<donn�esEns> treeView;
    
    String User = System.getProperty("user.name");
    
    private ObservableList<donn�esEns> donn�esEnss = FXCollections.observableArrayList();

    @SuppressWarnings("unchecked")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	contenuInscriptionController.suprP.setDisable(true);
        JFXTreeTableColumn<donn�esEns, String> cren = new JFXTreeTableColumn<>("Cr�neaux");
        cren.setPrefWidth(146);
        cren.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().creneaux;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> inti = new JFXTreeTableColumn<>("Intitul�");
        inti.setPrefWidth(146);
        inti.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().intitul�;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> men = new JFXTreeTableColumn<>("Mention");
        men.setPrefWidth(146);
        men.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().mention;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> ens = new JFXTreeTableColumn<>("Enseignant");
        ens.setPrefWidth(146);
        ens.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().enseignant;
            }
        });
        
        JFXTreeTableColumn<donn�esEns, String> RUC = new JFXTreeTableColumn<>("RU");
        RUC.setPrefWidth(146);
        RUC.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().RU;
            }
        });

        Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(Main.chemin));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheet("Inscription_" + InterfaceGlobaleEtudController.drawerNameP);
        for(int i=1; i<sheet.getColumn(0).length;i++){
        	if((LoginController.name+" "+LoginController.username).equals(sheet.getCell(6,i).getContents()+ " "+sheet.getCell(7,i).getContents())){
        		donn�esEnss.add(new donn�esEns(sheet.getCell(1,i).getContents(),sheet.getCell(2,i).getContents(),sheet.getCell(3,i).getContents(),sheet.getCell(4,i).getContents(),sheet.getCell(5,i).getContents()));   
        } }      
        final TreeItem<donn�esEns> root = new RecursiveTreeItem<donn�esEns>(donn�esEnss, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(cren, inti,men,ens,RUC);
        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }

    
    @FXML
    private void del(MouseEvent event) throws IOException {
    	if(event.getClickCount() == 1){
    		contenuInscriptionController.suprP.setDisable(false);
    		int indSel = treeView.getSelectionModel().getSelectedIndex();    	
    		contenuInscriptionController.suprP.setOnAction(new EventHandler<ActionEvent>() {
	              @Override
	              public void handle(ActionEvent event) {
	            	 donn�esEnss.remove(indSel);	
	      			Workbook workbook = null;
	  				try {	
	  					File ins = new File(Main.chemin);
	  					workbook = Workbook.getWorkbook(ins);
	  					File outFile = new File(Main.cheminTemp);
	  					WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
	  						 
	  					WritableSheet dataSheet = wwb.getSheet("Inscription_" + InterfaceGlobaleEtudController.drawerNameP);
	  						
	  					dataSheet.removeRow(indSel+1);
	  						
	  					wwb.write();
	  					wwb.close(); 
	  						               
	  					File insb = new File(Main.chemin);
	  					insb.delete();
	  						               
	  					File insc = new File(Main.chemin);
	  					outFile.renameTo(insc);
	  				} catch (BiffException e) {
	  					e.printStackTrace();
	  				} catch (IOException e) {
	  					e.printStackTrace();
	  				} catch (WriteException e) {
	  					e.printStackTrace();
	  				}	
	  				finally {
	  					if(workbook!=null){
	  							workbook.close();
	  					}
	  				}
	  				FlowPane contenuL;
					try {
						contenuL = FXMLLoader.load(getClass().getResource("contenuInscription.fxml"));
						InterfaceGlobaleEtudController.drawerRefresh.setSidePane(contenuL);
		  	    		InterfaceGlobaleEtudController.drawerRefresh.requestFocus();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	              }
	              
	          });
    		
    			
    		}   
    
    }

    public class donn�esEns extends RecursiveTreeObject<donn�esEns> {

        public StringProperty creneaux;
        public StringProperty intitul�;
        public StringProperty mention;
        public StringProperty enseignant;
        public StringProperty RU;
        

        public donn�esEns(String creneaux, String intitul�,String mention,String enseignant, String RU) {
            this.creneaux = new SimpleStringProperty(creneaux);
            this.intitul� = new SimpleStringProperty(intitul�);
            this.mention = new SimpleStringProperty(mention);
            this.enseignant = new SimpleStringProperty(enseignant);
            this.RU = new SimpleStringProperty(RU);
        }

    }
}
