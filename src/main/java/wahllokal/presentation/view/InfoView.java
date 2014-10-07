package wahllokal.presentation.view;


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import wahllokal.business.MainModel;
import wahllokal.presentation.controller.InfoController;

/**
 * Diese Klasse dient dazu, die GUI, welche die Informationen
 * ueber das Wahllokal anzeigt, zu erstellen.
 * @author Johann
 *<p>zuletzt bearbeitet: 26.06.2011</p>
 */
public class InfoView {

  /**Die Klasse baut auf diesem Frame auf.*/
  private JFrame mainFrame;
  
  /**
   * Gibt die Referenz auf den JFrame zurueck.
   * @return the mainFrame
   */
  public final JFrame getMainFrame() {
    return this.mainFrame;
  }
  
  /** Definiert einen Button, mit dem der Nutzer den Dialog abbrechen kann.*/
  private JButton abort;
  
  /**
   * Liefert den Abbrechen-Button als Referenz.
  * @return the abort
  */
  public final JButton getAbort() {
     return abort;
   }
  
  /** definiert die Standard Dicke der Rahmen. */
  private final int defaultBorderThickness = 2;
  
  /** Referenz auf das Model, von dem die Daten geholt werden. */
  private MainModel model = MainModel.getInstance();
  
  /** Speichert das Datum, wann das Lokal eroeffnet wurde. */
  private String datum = model.getDate();
  
  /** Speichert die Zeit, wann das Lokal eroeffnet wurde. */
  private String time = model.getTime(); 
  
  /** Speichert die WahlkreisNummer des Lokals. */
  private String wkNr = model.getKreisNr();
  
  /** Speichert die WahlbezirkNummer des Lokals. */
  private String wbNr = model.getBezirkNr();
  
  /** Speichert die kabinenAnzahl des Lokals. */
  private String kabinenAnzahl = model.getKabinenZahl();
  
  /** Gibt die Anzahl aller regestrierten Waehler wieder. */
  private String regWaehlerAnzahl = new Integer(
      model.getGesamtWaehlerAnzahl()).toString();
  
  /** Gibt die Anzahl, aller bereits gewaehlten Waehler wieder. */
  private String gewaehltAnzahl = new Integer(
      model.getGewaehltAnzahl()).toString();
  
  /** Gibt die Anzahl aller aktiven Waehler wieder. */
  private String aktWaehlerAnzahl = new Integer(
      model.getAktiveWaehlerAnzahl()).toString();
  

  /**
   * Einziger Konstruktor dieser Klasse.
   * @param view Das SearchView, auf dem die InfoView mittig positioniert
   * werden soll.
   */
  public InfoView(SearchView view) {
    
    this.mainFrame = new JFrame("Information");
    this.mainFrame.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
    this.mainFrame.add(createMainPanel(), BorderLayout.CENTER);
    
    final int xSize = 350;
    final int ySize = 400;
    this.mainFrame.setSize(xSize, ySize);
    this.mainFrame.setLocationRelativeTo(view.getMainFrame());
    
    //Controller hier hinzufuegen
    new InfoController(this);
    
    this.mainFrame.setResizable(false);
    this.mainFrame.setVisible(true);
    
  }
  
  /**
   * Erzeugt ein Panel, auf dem alle Komponenten des
   * GUI enthalten sind.
   * Dieses Panel unterteilt sich in 3 Unterpanels, welche mit
   * createLokalPanel(), createWaehlerPanel() und createButtonPanel
   * gefuellt werden.
   * @return mainPanel Das Hauptpanel fuer die GUI
   */
  private JPanel createMainPanel() {

    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
    
    final int borderThickness = 1;
    mainPanel.setBorder(
        BorderFactory.createLineBorder(
            SearchView.DEFAULTBORDERCOLOR,
            borderThickness));

    GridBagLayout gbl = new GridBagLayout();
    mainPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 2;
    final int insetsBigger = 10;
    
    JPanel lokalPanel = createLokalPanel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsBigger,   //bottom
                            insetsDefault); //right
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(lokalPanel, gbc);
    mainPanel.add(lokalPanel);

    JPanel waehlerPanel = createWaehlerPanel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsBigger,   //top
                            insetsDefault,  //left
                            insetsBigger,   //bottom
                            insetsDefault); //right
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(waehlerPanel, gbc);
    mainPanel.add(waehlerPanel);

    JPanel buttonPanel = createButtonPanel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsBigger,   //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(buttonPanel, gbc);
    mainPanel.add(buttonPanel);

    return mainPanel;
  }
  
  /**
   * Erzeugt einen Panel der die allg. Infos uber das Lokal anzeigt.
   * @return lokalPanel Der Panel
   */
  private JPanel createLokalPanel() {

    JPanel lokalPanel = new JPanel();
    lokalPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
   
    lokalPanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.
        createLineBorder(SearchView.DEFAULTBORDERCOLOR,
            this.defaultBorderThickness),
        "Wahllokal",
        TitledBorder.CENTER,
        TitledBorder.CENTER));

    GridBagLayout gbl = new GridBagLayout();
    lokalPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 1;
    final int insetsBigger = 5;
    final int ipadyDefault = 5;
    final int textFieldLength = 15;
    
    int gridy = -1; //inkrementiert nach jeder neuen Zeile

    JLabel dateLabel = new JLabel("Eröffnet am: ");
    dateLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; //neue Zeile, gridy = 0
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsBigger,    //left
                            insetsDefault,   //bottom
                            insetsDefault);  //right
    gbc.anchor = GridBagConstraints.EAST;
    gbl.setConstraints(dateLabel, gbc);
    lokalPanel.add(dateLabel);

    JTextField dateField = new JTextField(textFieldLength);
    dateField.setText(this.datum);
    this.setDefaultTextFieldFormat(dateField);
    
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = gridy; // gridy = 0
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsBigger);  //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(dateField, gbc);
    lokalPanel.add(dateField);
    
    
    JLabel timeLabel = new JLabel("um: ");
    timeLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; //neue Zeile, gridy = 1
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsBigger,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.EAST;
    gbl.setConstraints(timeLabel, gbc);
    lokalPanel.add(timeLabel);

    
    JTextField timeField = new JTextField(textFieldLength);
    timeField.setText(this.time);
    this.setDefaultTextFieldFormat(timeField);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = gridy; // gridy = 1
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsBigger); //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(timeField, gbc);
    lokalPanel.add(timeField);

    this.lokalSub(lokalPanel, gbl);

    return lokalPanel;
  }
  
  /**
   * interne Hilfsmethode fuer die Methode createLokalPanel(),
   * welche den unteren Teil der Komponenten definiert.
   * @param lokalPanel das Panel, auf dem die Komponenten liegen
   * sollen.
   * @param gbl der GridBagLayout vom lokalPanel.
   */
  private void lokalSub(JPanel lokalPanel, GridBagLayout gbl) {

    GridBagConstraints gbc;

    final int insetsDefault = 1;
    final int insetsBigger = 5;
    final int ipadyDefault = 5;
    final int textFieldLength = 15;
    
    int gridy = 2; //inkrementiert nach jeder neuen Zeile

    JLabel wkLabel = new JLabel("WahlkreisNr.: ");
    wkLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; //neue Zeile, gridy = 2
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsBigger,   //left
                            insetsDefault,   //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.EAST;
    gbl.setConstraints(wkLabel, gbc);
    lokalPanel.add(wkLabel);

    JTextField wkField = new JTextField(textFieldLength);
    wkField.setText(this.wkNr);
    this.setDefaultTextFieldFormat(wkField);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = gridy; // gridy = 2
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsBigger); //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(wkField, gbc);
    lokalPanel.add(wkField);

    
    JLabel wbLabel = new JLabel("WahlbezirkNr.: ");
    wbLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; //neue Zeile, gridy = 3
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsBigger,   //left
                            insetsDefault,   //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.EAST;
    gbl.setConstraints(wbLabel, gbc);
    lokalPanel.add(wbLabel);

    JTextField wbField = new JTextField(textFieldLength);
    wbField.setText(this.wbNr);
    this.setDefaultTextFieldFormat(wbField);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = gridy; // gridy = 3
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsBigger); //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(wbField, gbc);
    lokalPanel.add(wbField);
    
    JLabel kabinenAnzahlLabel = new JLabel("Kabinenanzahl: ");
    wkLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; //neue Zeile, gridy = 4
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsBigger,   //left
                            insetsBigger,   //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.EAST;
    gbl.setConstraints(kabinenAnzahlLabel, gbc);
    lokalPanel.add(kabinenAnzahlLabel);

    JTextField kabinenAnzahlField = new JTextField(textFieldLength);
    kabinenAnzahlField.setText(this.kabinenAnzahl);
    this.setDefaultTextFieldFormat(kabinenAnzahlField);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = gridy; // gridy = 4
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,  //left
                            insetsBigger,  //bottom
                            insetsBigger); //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(kabinenAnzahlField, gbc);
    lokalPanel.add(kabinenAnzahlField);
    
   
  }
  
  /**
   * Erzeugt einen Panel der die Infos ueber die Waehler anzeigt.
   * @return waehlerPanel Der Panel
   */
  private JPanel createWaehlerPanel() {

    JPanel waehlerPanel = new JPanel();
    waehlerPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
   
    waehlerPanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.
        createLineBorder(SearchView.DEFAULTBORDERCOLOR,
            this.defaultBorderThickness),
        "Wähler",
        TitledBorder.CENTER,
        TitledBorder.CENTER));

    GridBagLayout gbl = new GridBagLayout();
    waehlerPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 1;
    final int insetsBigger = 5;
    final int ipadyDefault = 5;
    final int textFieldLength = 15;
    
    int gridy = -1; //inkrementiert nach jeder neuen Zeile

    JLabel regWaehlerLabel = new JLabel("Registrierte Wähler: ");
    regWaehlerLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; //neue Zeile, gridy = 0
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsBigger,    //left
                            insetsDefault,   //bottom
                            insetsDefault);  //right
    gbc.anchor = GridBagConstraints.EAST;
    gbl.setConstraints(regWaehlerLabel, gbc);
    waehlerPanel.add(regWaehlerLabel);

    JTextField regWaehlerField = new JTextField(textFieldLength);
    regWaehlerField.setText(this.regWaehlerAnzahl);
    this.setDefaultTextFieldFormat(regWaehlerField);
    
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = gridy; // gridy = 0
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsBigger);  //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(regWaehlerField, gbc);
    waehlerPanel.add(regWaehlerField);
    
    
    JLabel gewaehltLabel = new JLabel("davon haben gewählt: ");
    gewaehltLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; //neue Zeile, gridy = 1
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsBigger,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.EAST;
    gbl.setConstraints(gewaehltLabel, gbc);
    waehlerPanel.add(gewaehltLabel);

    
    JTextField gewaehltField = new JTextField(textFieldLength);
    gewaehltField.setText(this.gewaehltAnzahl);
    this.setDefaultTextFieldFormat(gewaehltField);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = gridy; // gridy = 1
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsBigger); //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(gewaehltField, gbc);
    waehlerPanel.add(gewaehltField);

    JLabel aktivLabel = new JLabel("Zurzeit aktiv: ");
    aktivLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; //neue Zeile, gridy = 2
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsBigger,   //left
                            insetsBigger,   //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.EAST;
    gbl.setConstraints(aktivLabel, gbc);
    waehlerPanel.add(aktivLabel);

    JTextField aktivField = new JTextField(textFieldLength);
    aktivField.setText(this.aktWaehlerAnzahl);
    this.setDefaultTextFieldFormat(aktivField);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = gridy; // gridy = 2
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipady = ipadyDefault;
    gbc.ipadx = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,  //left
                            insetsBigger,  //bottom
                            insetsBigger); //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(aktivField, gbc);
    waehlerPanel.add(aktivField);
    
    return waehlerPanel;
  }
  
  /**
   * Formatiert ein JTextField, sodass das Field nur
   * zur Anzeige von Daten da ist, dh. es koennen
   * keine Nutzereingaben, auch nicht per Drag and Drop oder
   * copy/paste gemacht werden.
   * @param field Das JTextField welches formatiert werden soll.
   */
  private void setDefaultTextFieldFormat(JTextField field) {
    
    field.setEditable(false);
    field.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
    
    field.setBorder(BorderFactory.createEtchedBorder());
    
  }
  
  /**
   * Erstellt ein JPanel mit den Buttons.
   * @return buttonPanel Das JPanel mit den Buttons
   */
  private JPanel createButtonPanel() {

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);

    GridBagLayout gbl = new GridBagLayout();
    buttonPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 2;

    this.abort = new JButton("Abbrechen");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,   //left
                            insetsDefault,   //bottom
                            insetsDefault);  //right
    gbl.setConstraints(this.abort, gbc);
    buttonPanel.add(this.abort);

    return buttonPanel;
  }
  
  
} //class end
