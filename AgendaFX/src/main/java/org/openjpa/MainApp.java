package org.openjpa;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.prefs.Preferences;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.openjpa.control.PersonEditDialogController;
import org.openjpa.control.PersonOverviewController;
import org.openjpa.control.RootLayoutController;
import org.openjpa.control.exceptions.EntidadPreexistenteException;
import org.openjpa.entidades.Person;
import org.openjpa.entidades.PersonControl;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Person> personData = FXCollections.observableArrayList();
 

    public ObservableList<Person> getPersonData() {
            return personData;
    }


    public boolean showPersonEditDialog(Person person) 
    {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("control/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
  
    // ... THE REST OF THE CLASS ...
    @Override
    public void start(Stage primaryStage) 
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        // Set the application icon.
        //this.primaryStage.getIcons().add(new Image("file:resources/images/283051_moleskine_notes_note_notebook_diary_icon.png"));
    // Add some sample data
    //        personData.add(new Person("Hans", "Muster"));
    //        personData.add(new Person("Ruth", "Mueller"));
    //        personData.add(new Person("Heinz", "Kurz"));
    //        personData.add(new Person("Cornelia", "Meier"));
    //        personData.add(new Person("Werner", "Meyer"));
    //        personData.add(new Person("Lydia", "Kunz"));
    //        personData.add(new Person("Anna", "Best"));
    //        personData.add(new Person("Stefan", "Meier"));
    //        personData.add(new Person("Martin", "Mueller"));
        initRootLayout();
        showPersonOverview();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("control/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
 * Shows the person overview inside the root layout.
 */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("control/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
        Person person;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaPU");
        PersonControl personControl = new PersonControl(emf);
        //Pedimos datos del autor
        String firstname = leerTexto("Introduce nombre: ");
        String lastname = leerTexto("Introduce apellidos: ");        
       
        person = new Person(firstname, lastname);
        try {
            // Lo añadimos a la BD
            System.out.println("Identificador del autor: " + personControl.insertar(person));
        } catch (EntidadPreexistenteException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

   public File getPersonFilePath() {
       Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
       String filePath = prefs.get("filePath", null);
       if (filePath != null) {
           return new File(filePath);
       } else {
           return null;
       }
   }


   public void setPersonFilePath(File file) {
       Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
       if (file != null) {
           prefs.put("filePath", file.getPath());

           // Update the stage title.
           primaryStage.setTitle("AddressApp - " + file.getName());
       } else {
           prefs.remove("filePath");

           // Update the stage title.
           primaryStage.setTitle("AddressApp");
       }
   }

    public void loadPersonDataFromFile(File file) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void savePersonDataToFile(File personFile) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   public void showBirthdayStatistics() {
//       try {
//           // Load the fxml file and create a new stage for the popup.
//           FXMLLoader loader = new FXMLLoader();
//           loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
//           AnchorPane page = (AnchorPane) loader.load();
//           Stage dialogStage = new Stage();
//           dialogStage.setTitle("Birthday Statistics");
//           dialogStage.initModality(Modality.WINDOW_MODAL);
//           dialogStage.initOwner(primaryStage);
//           Scene scene = new Scene(page);
//           dialogStage.setScene(scene);
//
//           // Set the persons into the controller.
//           BirthdayStatisticsController controller = loader.getController();
//           controller.setPersonData(personData);
//
//           dialogStage.show();
//
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
   }
   
   static private String leerTexto(String mensaje) {
        String texto;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(mensaje);
            texto = in.readLine();
        } catch (IOException e) {
            texto = "Error";
        }
        return texto;
    }
    
    static private int leerNumero(String mensaje) {
        int numero;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(mensaje);
            numero = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            numero = -1;
        }
        return numero;
    }
}