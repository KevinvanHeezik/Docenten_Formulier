
package docenten_formulier;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    Connection conn;
    private File selectedfile;
    static int aantal_klassen_int;
    Student studentA = new Student();
    Student studentB = new Student();
    Student studentC = new Student();
    Student studentD = new Student();

    PrintStream printstreamA;
    PrintStream printstreamB;
    PrintStream printstreamC;
    PrintStream printstreamD;

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/klassenindeling";
    String username = "root";
    String password = "root";

    @FXML
    private Button button;
    @FXML
    private Text label;
    @FXML
    private CheckBox leeftijd;
    @FXML
    private CheckBox vooropleiding;
    @FXML
    private Button opslaan;
    @FXML
    private TextField bestandsnaam;
    @FXML
    private TextField aantal_klassen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Kies een bestand");

        FileChooser filechooser = new FileChooser();
        selectedfile = filechooser.showOpenDialog(null);
        if (selectedfile != null) {
            System.out.println(selectedfile.getAbsolutePath());
            label.setText(selectedfile.getName());
        }

    }

    @FXML
    private void handleDoneAction(ActionEvent event) {

        aantal_klassen_int = Integer.parseInt(aantal_klassen.getText());

        try {

            if (aantal_klassen_int >= 2) {
                printstreamB = new PrintStream(new BufferedOutputStream(new FileOutputStream(bestandsnaam.getText() + "KlasB.csv")), true);
                System.setOut(printstreamB);
                System.out.println("Studenten_Nummer; Voornaam; Tussenvoegsel; Achternaam; Straat; Nummer; Toevoeging; Postcode; Plaats; Vooropleiding; Geboortedatum; Medestudent; Overig");
            }

            if (aantal_klassen_int >= 3) {
                printstreamC = new PrintStream(new BufferedOutputStream(new FileOutputStream(bestandsnaam.getText() + "KlasC.csv")), true);
                System.setOut(printstreamC);
                System.out.println("Studenten_Nummer; Voornaam; Tussenvoegsel; Achternaam; Straat; Nummer; Toevoeging; Postcode; Plaats; Vooropleiding; Geboortedatum; Medestudent; Overig");
            }

            if (aantal_klassen_int >= 4) {
                printstreamD = new PrintStream(new BufferedOutputStream(new FileOutputStream(bestandsnaam.getText() + "KlasD.csv")), true);
                System.setOut(printstreamD);
                System.out.println("Studenten_Nummer; Voornaam; Tussenvoegsel; Achternaam; Straat; Nummer; Toevoeging; Postcode; Plaats; Vooropleiding; Geboortedatum; Medestudent; Overig");
            }

        } catch (Exception e) {
        }

        //
        //
        //
        //  SORTEREN OP LEEFTIJD
        //
        //
        //
        
        if (leeftijd.isSelected() && !vooropleiding.isSelected()) {

            try {

                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);

                String query = "SELECT * FROM studenten ORDER BY Geboortedatum";

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                
                printstreamA = new PrintStream(new BufferedOutputStream(new FileOutputStream(bestandsnaam.getText() + "KlasA.csv")), true);
                System.setOut(printstreamA);
                System.out.println("Studenten_Nummer; Voornaam; Tussenvoegsel; Achternaam; Straat; Nummer; Toevoeging; Postcode; Plaats; Vooropleiding; Geboortedatum; Medestudent; Overig");

                ArrayList<Student> studentenA = new ArrayList<Student>();
                ArrayList<Student> studentenB = new ArrayList<Student>();
                ArrayList<Student> studentenC = new ArrayList<Student>();
                ArrayList<Student> studentenD = new ArrayList<Student>();

                while (rs.next()) {

                    System.setOut(printstreamA);

                    studentA.id = rs.getString("Studenten_Nummer");
                    studentA.voornaam = rs.getString("Voornaam");
                    studentA.tussenvoegsel = rs.getString("Tussenvoegsel");
                    studentA.achternaam = rs.getString("Achternaam");
                    studentA.straat = rs.getString("Straat");
                    studentA.nummer = rs.getInt("Nummer");
                    studentA.toevoeging = rs.getString("Toevoeging");
                    studentA.postcode = rs.getString("Postcode");
                    studentA.plaats = rs.getString("Plaats");
                    studentA.vooropleiding = rs.getString("Vooropleiding");
                    studentA.geboortedatum = rs.getDate("Geboortedatum");
                    studentA.medestudent = rs.getString("Medestudent");
                    studentA.overig = rs.getString("Overig");

                    studentenA.add(studentA);
                    System.out.println(studentA);

                    if (aantal_klassen_int >= 2) {

                        try {
                            rs.next();
                            System.setOut(printstreamB);

                            studentB.id = rs.getString("Studenten_Nummer");
                            studentB.voornaam = rs.getString("Voornaam");
                            studentB.tussenvoegsel = rs.getString("Tussenvoegsel");
                            studentB.achternaam = rs.getString("Achternaam");
                            studentB.straat = rs.getString("Straat");
                            studentB.nummer = rs.getInt("Nummer");
                            studentB.toevoeging = rs.getString("Toevoeging");
                            studentB.postcode = rs.getString("Postcode");
                            studentB.plaats = rs.getString("Plaats");
                            studentB.vooropleiding = rs.getString("Vooropleiding");
                            studentB.geboortedatum = rs.getDate("Geboortedatum");
                            studentB.medestudent = rs.getString("Medestudent");
                            studentB.overig = rs.getString("Overig");

                            studentenB.add(studentB);
                            System.out.println(studentB);

                        } catch (Exception e) {
                        }
                    }

                    if (aantal_klassen_int >= 3) {

                        try {
                            rs.next();
                            System.setOut(printstreamC);

                            studentC.id = rs.getString("Studenten_Nummer");
                            studentC.voornaam = rs.getString("Voornaam");
                            studentC.tussenvoegsel = rs.getString("Tussenvoegsel");
                            studentC.achternaam = rs.getString("Achternaam");
                            studentC.straat = rs.getString("Straat");
                            studentC.nummer = rs.getInt("Nummer");
                            studentC.toevoeging = rs.getString("Toevoeging");
                            studentC.postcode = rs.getString("Postcode");
                            studentC.plaats = rs.getString("Plaats");
                            studentC.vooropleiding = rs.getString("Vooropleiding");
                            studentC.geboortedatum = rs.getDate("Geboortedatum");
                            studentC.medestudent = rs.getString("Medestudent");
                            studentC.overig = rs.getString("Overig");

                            studentenC.add(studentC);
                            System.out.println(studentC);

                        } catch (Exception e) {
                        }
                    }

                    if (aantal_klassen_int >= 4) {

                        try {
                            rs.next();
                            System.setOut(printstreamD);

                            studentD.id = rs.getString("Studenten_Nummer");
                            studentD.voornaam = rs.getString("Voornaam");
                            studentD.tussenvoegsel = rs.getString("Tussenvoegsel");
                            studentD.achternaam = rs.getString("Achternaam");
                            studentD.straat = rs.getString("Straat");
                            studentD.nummer = rs.getInt("Nummer");
                            studentD.toevoeging = rs.getString("Toevoeging");
                            studentD.postcode = rs.getString("Postcode");
                            studentD.plaats = rs.getString("Plaats");
                            studentD.vooropleiding = rs.getString("Vooropleiding");
                            studentD.geboortedatum = rs.getDate("Geboortedatum");
                            studentD.medestudent = rs.getString("Medestudent");
                            studentD.overig = rs.getString("Overig");

                            studentenD.add(studentD);
                            System.out.println(studentD);

                        } catch (Exception e) {
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        //
        //
        //
        //  SORTEREN OP VOOROPLEIDING
        //
        //
        //
        
        if (vooropleiding.isSelected() && !leeftijd.isSelected()) {

            try {

                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);

                String query = "SELECT * FROM studenten ORDER BY Vooropleiding";

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                
                printstreamA = new PrintStream(new BufferedOutputStream(new FileOutputStream(bestandsnaam.getText() + "KlasA.csv")), true);
                System.setOut(printstreamA);
                System.out.println("Studenten_Nummer; Voornaam; Tussenvoegsel; Achternaam; Straat; Nummer; Toevoeging; Postcode; Plaats; Vooropleiding; Geboortedatum; Medestudent; Overig");

                ArrayList<Student> studentenA = new ArrayList<Student>();
                ArrayList<Student> studentenB = new ArrayList<Student>();
                ArrayList<Student> studentenC = new ArrayList<Student>();
                ArrayList<Student> studentenD = new ArrayList<Student>();

                while (rs.next()) {

                    System.setOut(printstreamA);

                    studentA.id = rs.getString("Studenten_Nummer");
                    studentA.voornaam = rs.getString("Voornaam");
                    studentA.tussenvoegsel = rs.getString("Tussenvoegsel");
                    studentA.achternaam = rs.getString("Achternaam");
                    studentA.straat = rs.getString("Straat");
                    studentA.nummer = rs.getInt("Nummer");
                    studentA.toevoeging = rs.getString("Toevoeging");
                    studentA.postcode = rs.getString("Postcode");
                    studentA.plaats = rs.getString("Plaats");
                    studentA.vooropleiding = rs.getString("Vooropleiding");
                    studentA.geboortedatum = rs.getDate("Geboortedatum");
                    studentA.medestudent = rs.getString("Medestudent");
                    studentA.overig = rs.getString("Overig");

                    studentenA.add(studentA);
                    System.out.println(studentA);

                    if (aantal_klassen_int >= 2) {

                        try {
                            rs.next();
                            System.setOut(printstreamB);

                            studentB.id = rs.getString("Studenten_Nummer");
                            studentB.voornaam = rs.getString("Voornaam");
                            studentB.tussenvoegsel = rs.getString("Tussenvoegsel");
                            studentB.achternaam = rs.getString("Achternaam");
                            studentB.straat = rs.getString("Straat");
                            studentB.nummer = rs.getInt("Nummer");
                            studentB.toevoeging = rs.getString("Toevoeging");
                            studentB.postcode = rs.getString("Postcode");
                            studentB.plaats = rs.getString("Plaats");
                            studentB.vooropleiding = rs.getString("Vooropleiding");
                            studentB.geboortedatum = rs.getDate("Geboortedatum");
                            studentB.medestudent = rs.getString("Medestudent");
                            studentB.overig = rs.getString("Overig");

                            studentenB.add(studentB);
                            System.out.println(studentB);

                        } catch (Exception e) {
                        }
                    }

                    if (aantal_klassen_int >= 3) {

                        try {
                            rs.next();
                            System.setOut(printstreamC);

                            studentC.id = rs.getString("Studenten_Nummer");
                            studentC.voornaam = rs.getString("Voornaam");
                            studentC.tussenvoegsel = rs.getString("Tussenvoegsel");
                            studentC.achternaam = rs.getString("Achternaam");
                            studentC.straat = rs.getString("Straat");
                            studentC.nummer = rs.getInt("Nummer");
                            studentC.toevoeging = rs.getString("Toevoeging");
                            studentC.postcode = rs.getString("Postcode");
                            studentC.plaats = rs.getString("Plaats");
                            studentC.vooropleiding = rs.getString("Vooropleiding");
                            studentC.geboortedatum = rs.getDate("Geboortedatum");
                            studentC.medestudent = rs.getString("Medestudent");
                            studentC.overig = rs.getString("Overig");

                            studentenC.add(studentC);
                            System.out.println(studentC);

                        } catch (Exception e) {
                        }
                    }

                    if (aantal_klassen_int >= 4) {

                        try {
                            rs.next();
                            System.setOut(printstreamD);

                            studentD.id = rs.getString("Studenten_Nummer");
                            studentD.voornaam = rs.getString("Voornaam");
                            studentD.tussenvoegsel = rs.getString("Tussenvoegsel");
                            studentD.achternaam = rs.getString("Achternaam");
                            studentD.straat = rs.getString("Straat");
                            studentD.nummer = rs.getInt("Nummer");
                            studentD.toevoeging = rs.getString("Toevoeging");
                            studentD.postcode = rs.getString("Postcode");
                            studentD.plaats = rs.getString("Plaats");
                            studentD.vooropleiding = rs.getString("Vooropleiding");
                            studentD.geboortedatum = rs.getDate("Geboortedatum");
                            studentD.medestudent = rs.getString("Medestudent");
                            studentD.overig = rs.getString("Overig");

                            studentenD.add(studentD);
                            System.out.println(studentD);

                        } catch (Exception e) {
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        //
        //
        //
        //  SORTEREN OP LEEFTIJD EN VOOROPLEIDING
        //
        //
        //

        if (leeftijd.isSelected() && vooropleiding.isSelected()) {

            try {

                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);

                String query = "SELECT * FROM studenten ORDER BY Geboortedatum";

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                
                printstreamA = new PrintStream(new BufferedOutputStream(new FileOutputStream(bestandsnaam.getText() + "KlasA.csv")), true);
                System.setOut(printstreamA);
                System.out.println("Studenten_Nummer; Voornaam; Tussenvoegsel; Achternaam; Straat; Nummer; Toevoeging; Postcode; Plaats; Vooropleiding; Geboortedatum; Medestudent; Overig");

                ArrayList<Student> studentenA = new ArrayList<Student>();
                ArrayList<Student> studentenB = new ArrayList<Student>();
                ArrayList<Student> studentenC = new ArrayList<Student>();
                ArrayList<Student> studentenD = new ArrayList<Student>();

                while (rs.next()) {

                    System.setOut(printstreamA);

                    studentA.id = rs.getString("Studenten_Nummer");
                    studentA.voornaam = rs.getString("Voornaam");
                    studentA.tussenvoegsel = rs.getString("Tussenvoegsel");
                    studentA.achternaam = rs.getString("Achternaam");
                    studentA.straat = rs.getString("Straat");
                    studentA.nummer = rs.getInt("Nummer");
                    studentA.toevoeging = rs.getString("Toevoeging");
                    studentA.postcode = rs.getString("Postcode");
                    studentA.plaats = rs.getString("Plaats");
                    studentA.vooropleiding = rs.getString("Vooropleiding");
                    studentA.geboortedatum = rs.getDate("Geboortedatum");
                    studentA.medestudent = rs.getString("Medestudent");
                    studentA.overig = rs.getString("Overig");

                    studentenA.add(studentA);
                    System.out.println(studentA);

                    if (aantal_klassen_int >= 2) {

                        try {
                            rs.next();
                            System.setOut(printstreamB);

                            studentB.id = rs.getString("Studenten_Nummer");
                            studentB.voornaam = rs.getString("Voornaam");
                            studentB.tussenvoegsel = rs.getString("Tussenvoegsel");
                            studentB.achternaam = rs.getString("Achternaam");
                            studentB.straat = rs.getString("Straat");
                            studentB.nummer = rs.getInt("Nummer");
                            studentB.toevoeging = rs.getString("Toevoeging");
                            studentB.postcode = rs.getString("Postcode");
                            studentB.plaats = rs.getString("Plaats");
                            studentB.vooropleiding = rs.getString("Vooropleiding");
                            studentB.geboortedatum = rs.getDate("Geboortedatum");
                            studentB.medestudent = rs.getString("Medestudent");
                            studentB.overig = rs.getString("Overig");

                            studentenB.add(studentB);
                            System.out.println(studentB);

                        } catch (Exception e) {
                        }
                    }

                    if (aantal_klassen_int >= 3) {

                        try {
                            rs.next();
                            System.setOut(printstreamC);

                            studentC.id = rs.getString("Studenten_Nummer");
                            studentC.voornaam = rs.getString("Voornaam");
                            studentC.tussenvoegsel = rs.getString("Tussenvoegsel");
                            studentC.achternaam = rs.getString("Achternaam");
                            studentC.straat = rs.getString("Straat");
                            studentC.nummer = rs.getInt("Nummer");
                            studentC.toevoeging = rs.getString("Toevoeging");
                            studentC.postcode = rs.getString("Postcode");
                            studentC.plaats = rs.getString("Plaats");
                            studentC.vooropleiding = rs.getString("Vooropleiding");
                            studentC.geboortedatum = rs.getDate("Geboortedatum");
                            studentC.medestudent = rs.getString("Medestudent");
                            studentC.overig = rs.getString("Overig");

                            studentenC.add(studentC);
                            System.out.println(studentC);

                        } catch (Exception e) {
                        }
                    }

                    if (aantal_klassen_int >= 4) {

                        try {
                            rs.next();
                            System.setOut(printstreamD);

                            studentD.id = rs.getString("Studenten_Nummer");
                            studentD.voornaam = rs.getString("Voornaam");
                            studentD.tussenvoegsel = rs.getString("Tussenvoegsel");
                            studentD.achternaam = rs.getString("Achternaam");
                            studentD.straat = rs.getString("Straat");
                            studentD.nummer = rs.getInt("Nummer");
                            studentD.toevoeging = rs.getString("Toevoeging");
                            studentD.postcode = rs.getString("Postcode");
                            studentD.plaats = rs.getString("Plaats");
                            studentD.vooropleiding = rs.getString("Vooropleiding");
                            studentD.geboortedatum = rs.getDate("Geboortedatum");
                            studentD.medestudent = rs.getString("Medestudent");
                            studentD.overig = rs.getString("Overig");

                            studentenD.add(studentD);
                            System.out.println(studentD);

                        } catch (Exception e) {
                        }
                    }
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        //CSVReader();
        Stage stage = (Stage) opslaan.getScene().getWindow();
        stage.close();
    }

    public void CSVReader() {

        String csvFile = selectedfile.getAbsolutePath();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/klassenindeling";
            String username = "root";
            String password = "root";
            Class.forName(driver);

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] stud_data = line.split(cvsSplitBy);

                //spatie verwijderen
                for (int i = 0; i < stud_data.length; i++) {
                    stud_data[i] = stud_data[i].trim();
                }

                Student student = new Student();

                student.id = stud_data[0];
                student.voornaam = stud_data[4];
                student.tussenvoegsel = stud_data[2];
                student.achternaam = stud_data[1];
                student.straat = stud_data[6];
                student.nummer = Integer.parseInt(stud_data[7]);
                student.toevoeging = stud_data[8];
                student.postcode = stud_data[10];
                student.plaats = stud_data[11];

                student.insert(conn);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
