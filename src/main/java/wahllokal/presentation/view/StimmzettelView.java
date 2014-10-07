package wahllokal.presentation.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import wahllokal.business.MainModel;
import wahllokal.presentation.controller.StimmzettelController;


/**
 * GUI fuer den Stimmzettel.
 * @author Johann
 * <p>Zuletzt bearbeitet: 19.06.2011</p>
 */
public class StimmzettelView {

  /** Das Model, von dem die Daten geholt werden. */
  private MainModel mainModel;
  
  /** Standard Hintergrundfarbe jedes Stimmzettels. */
  public static final Color DEFAULTBACKGROUNDCOLOR = Color.WHITE;
  
  /** Standard Farbe, fuer alles was nicht zu Erst-oder Zweitstimme gehoert.*/
  private final Color defaultFontColor = Color.BLACK;
  
  /** Farbe fuer Rahmen und Schrift der Erststimme. */
  private final Color erstStimmeColor = Color.BLACK;
  
  /** Farbe fuer Rahmen und Schrift der Zweitstimme. */
  private final Color zweitStimmeColor = Color.BLUE;
  
  /** Rahmendicke fuer die Auswahlzeilen von Erst-und Zweitstimme. */
  private final int stimmBorderThickness = 1;

  /** Definiert den Abstand zwischen Erst-und Zweitstimme. */
  private final int abstandErstZweitStimme = 20;
  
  /** Anzahl der Auswahlzeilen des Stimmzettels. */
  private final int zeilenAnzahl;
  
  /** Speichert alle JRadiaButtons der Erststimmen dieses Stimmzettels,
   * damit die Controller diesen jeweils einen Listener
   * hinzufuegen koennen. */
  private ArrayList<JRadioButton> zweitStimmeButtonList;
  
  /**
   * Gibt eine Referenz auf die ArrayList mit den
   * RadioButtons zurueck, welche zur Zweitstimme gehoeren.
   * @return the buttonList
   */
  public final ArrayList<JRadioButton> getZweitStimmeButtonList() {
    return this.zweitStimmeButtonList;
  }
  
  /** Speichert alle JRadiaButtons der Erststimmen dieses Stimmzettels,
   * damit die Controller diesen jeweils einen Listener
   * hinzufuegen koennen. */
  private ArrayList<JRadioButton> erstStimmeButtonList;
  
  /**
   * Gibt eine Referenz auf die ArrayList mit den
   * RadioButtons zurueck, welche zur Zweitstimme gehoeren.
   * @return the buttonList
   */
  public final ArrayList<JRadioButton> getErstStimmeButtonList() {
    return this.erstStimmeButtonList;
  }

  /** Der Button zum Bestaetigen der Auswahl. */
  private JButton confirmButton;
  
  /**
   * Gibt dem Bestaetigenbutton zurueck.
   * Diese Methode wird von den Controllern gebraucht.
   * @return the confirmButton
   */
  public final JButton getConfirmButton() {
    return confirmButton;
  }
  /** Speichert den BerechtigungsCode von dem Waehler, der
   * diesen Stimmzettel aufgerufen hat.
   * Dieser Key wird hier verwendet um den Waehler zu
   * identifizieren, damit man diesem nach dem waehlen die
   * Berechtigung zu entziehen.*/
  private String waehlerKey;

  //Das Fenster ist nur zu Testzwecken ein JFrame, sollte das Programm
  //wirklich verteilt programmiert werden, sollte ein JWindow oder 
  //ein undekoriertes JFrame das Fenster darstellen, damit der Benutzer 
  //wirklich nur Waehlen kann und nichts anderes.
  
  /** Das frame der GUI. */
  private JFrame mainFrame;

  /**
   * Liefert die Referenz auf das JFrame, mit dem dieser Stimmzettel
   * aufgebaut ist.
   * Wird von den Controllern gebraucht.
   * @return the mainFrame
   */
  public final JFrame getMainFrame() {

    return this.mainFrame;
  }
  
  /**
   * Einziger Konstruktor dieser Klasse.
   * @param berechtigungsCode Diese View wird immer
   * von genau einem Waehler aufgerufen der durch seinen
   * BerechtigungsCode identifizierbar ist.
   */
  public StimmzettelView(final String berechtigungsCode) {
    
    this.waehlerKey = berechtigungsCode;
    this.mainFrame = new JFrame();

    this.mainModel = MainModel.getInstance();
    this.zeilenAnzahl = this.mainModel.zeilenAnzahl();
    
    this.zweitStimmeButtonList = new ArrayList<JRadioButton>();
    this.erstStimmeButtonList = new ArrayList<JRadioButton>();
    
    this.mainFrame.add(this.createMainPanel(), BorderLayout.CENTER);

    this.mainFrame.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    this.mainFrame.setLocation(0, 0);
    this.mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    
    new StimmzettelController(this); // Controller hier hinzufuegen
     

    this.mainFrame.setVisible(true);  // hier Fenster anzeigen
    
  } //Konstruktor ende
  
  /**
   * Erzeugt das Hauptpanel dieser Klasse.
   * Dieses Panel ist aufgeteilt in ContentPanel
   * und ButtonPanel.
   * @return Gibt das mainPanel zurueck.
   */
  private JPanel createMainPanel() {
    
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
      
    GridBagLayout gbl = new GridBagLayout();
    mainPanel.setLayout(gbl);
    GridBagConstraints gbc;
    
    final int insetsDefault = 2;
    final int insetsBigger = 5;
    final int ipadxContent = 0;
    final int ipadyContent = 0;
    
    JPanel contentPanel = createContentPanel();

    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = ipadxContent;
    gbc.ipady = ipadyContent;
    gbc.insets = new Insets(insetsBigger,  //top
                            insetsBigger,  //left
                            insetsBigger,  //bottom
                            insetsBigger); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(contentPanel, gbc);
    mainPanel.add(contentPanel);
    
    ///////////////////////////
    
    JPanel buttonPanel = createButtonPanel();

    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(buttonPanel, gbc);
    mainPanel.add(buttonPanel);
       
    return mainPanel;   
  }
  
  /**
   * Erzeugt das contentPanel, das wiederum aufgeteilt ist in
   * headerPanel und footerPanel.
   * @return Das contentPanel.
   */
  private JPanel createContentPanel() {
    
    JPanel contentPanel = new JPanel();
    
    contentPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    
    GridBagLayout gbl = new GridBagLayout();
    contentPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 10;
    
    JPanel headerContentPanel = createHeaderContentPanel();

    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(headerContentPanel, gbc);
    contentPanel.add(headerContentPanel);
    
    ///////////////////////////
    
    JPanel footerContentPanel = createFooterContentPanel();

    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;

    gbl.setConstraints(footerContentPanel, gbc);
    contentPanel.add(footerContentPanel);
        
    return contentPanel;    
  }
  
  /**
   * Erzeugt das headerContentPanel, den Kopf des
   * Stimmzettels, in dem die allgemeinen Informationen
   * angezeigt werden.
   * 
   * @return Das headerContentPanel.
   */
  private JPanel createHeaderContentPanel() {
    
    JPanel headerContentPanel = new JPanel();
    headerContentPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    
    GridBagLayout gbl = new GridBagLayout();
    headerContentPanel.setLayout(gbl);
    GridBagConstraints gbc;
    
    final int insetsDefault = 1;
    
    final int fontSizeBig = 14;
    final int fontSizeDefault = 12;
    final int fontSizeHeadline = 18;
    
    int zeile = 0; // jedes JLabel kommt in eine neue Zeile
                   // und diese Variable dient als Zeilenindex
    
    /////////////////////////////  
    String headline = "Stimmzettel";  
    JLabel headlineLabel = new JLabel(headline);
    headlineLabel.setForeground(this.defaultFontColor);
    headlineLabel.setFont(new Font("Arial", Font.BOLD, fontSizeHeadline));
    
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = zeile++;  // gridy = 0
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    
    gbl.setConstraints(headlineLabel, gbc);
    headerContentPanel.add(headlineLabel);
    
    ///////////////////////////
    
    Date date = new Date();
    DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
    String dateString = df.format(date);
    
    String description = 
      
      "für die Wahl zum Schleswig-Holsteinischen" 
      +
      " Landtag am "
      +
        dateString;
    
    JLabel descriptionLabel = new JLabel(description);
    descriptionLabel.setForeground(this.defaultFontColor);
    descriptionLabel.setFont(new Font("Arial", Font.BOLD, fontSizeDefault));
   
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = zeile++;  //gridy = 1
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(descriptionLabel, gbc);
    headerContentPanel.add(descriptionLabel);
    
    ///////////////////////////
    
    String location = 
      "im Wahlkreis " 
     +
       mainModel.getKreisNr();
    
    JLabel locationLabel = new JLabel(location);
    locationLabel.setForeground(this.defaultFontColor);
    locationLabel.setFont(new Font("Arial", Font.BOLD, fontSizeDefault));
    
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = zeile++;  //gridy = 2
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(locationLabel, gbc);
    headerContentPanel.add(locationLabel);
    
    ///////////////////////////
    
    String instruction = "Sie haben 2 Stimmen";
 
    JLabel instructionLabel = new JLabel(instruction);
    instructionLabel.setForeground(this.defaultFontColor);
    instructionLabel.setFont(new Font("Arial", Font.BOLD, fontSizeBig));
    
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = zeile++;  //gridy = 3
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(instructionLabel, gbc);
    headerContentPanel.add(instructionLabel);
    
    ///////////////////////////
    

    JPanel stimmenBeschreibungPanel = 
      createStimmenBeschreibungPanel();
    stimmenBeschreibungPanel.setBackground(
        StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = zeile++;  //gridy = 4
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbl.setConstraints(stimmenBeschreibungPanel, gbc);
    headerContentPanel.add(stimmenBeschreibungPanel);
    
    return headerContentPanel;
  }
  
  /**
   * Erstellt das Panel, in dem die Erst-und Zweitstimme
   * erlaeutert werden.
   * Dieses Panel dient dazu, die Erlaeuterungen
   * in gleicher Groesze zu bekommen, sowie den Abstand
   * zwischen diesen zu definieren.
   * Das Panel unterteilt sich in erstBeschreibungsPanel
   * und ZweitBeschreibungsPanel.
   * @return Das Panel.
   */
  private JPanel createStimmenBeschreibungPanel() {
    
    JPanel stimmenBeschreibungPanel = new JPanel();
    stimmenBeschreibungPanel.setBackground(
        StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    stimmenBeschreibungPanel.setLayout(
        new GridLayout(
                       0,                         //zeilen
                       2,                         //spalten
                     this.abstandErstZweitStimme, //hgap
                     0));                         //vgap
    
    String stimmAnzahl = "hier 1 Stimme";
    String beschreibung = "für die Wahl einer / eines Wahlkreisabgeordneten";
    String stimmArt = "Erststimme";
    
    stimmenBeschreibungPanel.add(
        createBeschreibungPanel(stimmAnzahl,
                                beschreibung,
                                stimmArt,
                                true));
    
    stimmAnzahl = "hier 1 Stimme";
    
    beschreibung = 
      "für die Wahl "
      +
      "einer Landesliste (Partei)"
      +
      " -maßgebende Stimme für die Verteilung"
      +
      " der Sitze auf die einzelnen Parteien-";
    
    stimmArt = "Zweitstimme";
    
    stimmenBeschreibungPanel.add(
        createBeschreibungPanel(stimmAnzahl,
                                beschreibung,
                                stimmArt,
                                false));
    return stimmenBeschreibungPanel;
  }
  
  /**
   * Erzeugt das BeschreibungsPanel, das die Erlaeuterung
   * der Erst-oder Zweitstimme beinhaltet. Die Erlauterung selbst
   * gliedert sich in mehrere JLabels, wobei jedes Label
   * eine Zeile des GridLayouts beansprucht.
   * @param stimmAnzahl Die Anzahl der Stimmen z.B.: "hier 1 Stimme"
   * @param beschreibung Die eigentliche Beschreibung der Stimme
   * @param stimmArt Erst- oder Zweitstimme
   * @param erstStimme Anzeige, ob es sich hier um die Erststimme oder
   * die Zweitstimme handelt.
   * @return Das Panel.
   */
  private JPanel createBeschreibungPanel(String stimmAnzahl,
                                         String beschreibung, 
                                         String stimmArt,
                                         boolean erstStimme) {
    
    JPanel beschreibungPanel = new JPanel();
    
    beschreibungPanel.setBackground(
        StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    beschreibungPanel.setLayout(
        new GridLayout(0,   //die Zeilenzahl wird automatisch ermittelt 
                       1,   //spalten 
                       0,   //hgap
                       0)); //vgap
    
    final int fontSizeSmall = 10;
    final int fontSizeBig = 14;
    
    JLabel stimmZahlLabel = new JLabel(stimmAnzahl);
    stimmZahlLabel.setFont(new Font("Arial", Font.BOLD, fontSizeBig));
    this.beschreibungPanelSub(stimmZahlLabel, erstStimme);
    beschreibungPanel.add(stimmZahlLabel);
   
    
    final int zeileMaxLength = 30;
    
    int lastIndex = 
      this.getZeilenUmbruchPosition(zeileMaxLength, beschreibung);
    
    while (lastIndex != -1) {   
      
      JLabel beschreibungLabel = 
        new JLabel(beschreibung.substring(0, lastIndex));
      beschreibungLabel.setFont(new Font("Arial", Font.PLAIN, fontSizeSmall));
      this.beschreibungPanelSub(beschreibungLabel, erstStimme);
      beschreibungPanel.add(beschreibungLabel);      
     
      beschreibung = beschreibung.substring(lastIndex + 1);
      lastIndex = 
        this.getZeilenUmbruchPosition(zeileMaxLength, beschreibung);
      
    } 
    
    //restString auch noch als Label ausgeben:
    JLabel beschreibungLabel = new JLabel(beschreibung);
    beschreibungLabel.setFont(new Font("Arial", Font.PLAIN, fontSizeSmall));
    this.beschreibungPanelSub(beschreibungLabel, erstStimme);
    beschreibungPanel.add(beschreibungLabel);
    

    JLabel stimmeLabel = new JLabel(stimmArt);
    stimmeLabel.setFont(new Font("Arial", Font.BOLD, fontSizeBig + 1));
    this.beschreibungPanelSub(stimmeLabel, erstStimme);
    beschreibungPanel.add(stimmeLabel);
    
    return beschreibungPanel;   
  }
  
  /**
   * Hilfsmethode von createBeschreibungPanel(), die ein JLabel
   * je nachdem, ob es sich um ein JLabel fuer Erst- oder Zweitstimme handelt,
   * geeignet formatiert.
   * @param label Das Label, welches formatiert werden soll.
   * @param erstStimme Anzeige, ob es sich hier um die Erststimme oder
   * die Zweitstimme handelt.
   */
  private void beschreibungPanelSub(JLabel label, boolean erstStimme) {
    
    if (erstStimme) {
      
      label.setForeground(this.erstStimmeColor);
      label.setHorizontalAlignment(JLabel.LEFT);
      
    } else {
      
      label.setForeground(this.zweitStimmeColor);
      label.setHorizontalAlignment(JLabel.RIGHT);
    }
    
  }
  /**
   * Erzeugt das footerContentPanel, welches mit
   * seinem GridLayout dazu dient, dass Erststimme-
   * und Zweitstimmepanel stets gleich grosz sind.
   * @return das Panel.
   */
  private JPanel createFooterContentPanel() {
    
    JPanel footerContentPanel = new JPanel();
    footerContentPanel.setLayout(
        new GridLayout(
                     0,    //rows
                     2,    //cols
                     this.abstandErstZweitStimme , //hgap
                     0));  //vgap

    footerContentPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    
    JPanel erstStimmeMainPanel = createStimmeMainPanel(true);
    footerContentPanel.add(erstStimmeMainPanel);
    
    JPanel zweitStimmeMainPanel = createStimmeMainPanel(false);
    footerContentPanel.add(zweitStimmeMainPanel);
    
    return footerContentPanel;
    
  }

  /**
   * Erzeugt das mainPanel fuer jeweils die Erststimme und die Zweitstimme.
   * Die Anzahl dieser Panels, ist abhaengig von der Zeilenzahl, welche
   * diese Klasse von der Klasse MainModel bekommt.
   * @param erstStimme Anzeige, ob es sich hier um die Erststimme oder
   * die Zweitstimme handelt.
   * @return Das Panel.
   */
  private JPanel createStimmeMainPanel(boolean erstStimme) {
    
    JPanel stimmeMainPanel = new JPanel();
    
    stimmeMainPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);

    final int rows = this.zeilenAnzahl;
    final int cols = 1;
    final int hgap = 0;
    final int vgap = 2;
    stimmeMainPanel.setLayout(new GridLayout(rows, cols, hgap, vgap));
    
    for (int i = 0; i < rows; i++) {
      
      if (erstStimme) {
        
        stimmeMainPanel.add(createErstStimmePanel(i));
      
      } else {
        
        stimmeMainPanel.add(createZweitStimmePanel(i));
      }
    }

    return stimmeMainPanel;
  }
  
  /**
   * Erzeugt das erstStimmePanel, welches wiederum 3 Panels
   * enthealt fuer jeweils Zeilenangabe, Kandidatenbeschreibung
   * und Ankreuzpanel.
   * @param zeile Angabe der Zeile dieses Panels, damit die Daten
   * korrekt von MainModel geholt werden.
   * @return Das Panel.
   */
  private JPanel createErstStimmePanel(int zeile) {
    
    JPanel erstStimmePanel = new JPanel();
    erstStimmePanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    
    erstStimmePanel.setBorder(
        BorderFactory.createLineBorder(
            this.erstStimmeColor,
            this.stimmBorderThickness));

    GridBagLayout gbl = new GridBagLayout();
    erstStimmePanel.setLayout(gbl);

    GridBagConstraints gbc;

    final int insetsDefault = 0;
    
    //
    // Panel fuer die Zeilenangabe
    //
    
    JPanel zeilePanel = this.createZeileAngabePanel(zeile, true);
    zeilePanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
        
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(zeilePanel, gbc);

    
    erstStimmePanel.add(zeilePanel);
    
    //
    // Panel fuer die Kandidatenanzeige
    //
    
    JPanel kandidatPanel = new JPanel();
    kandidatPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    
    final int rows = 0; //zeilenzahl wird automatisch errechnet
    final int cols = 1; 
    final int hgap = 0;
    final int vgap = 0;
    kandidatPanel.setLayout(new GridLayout(rows, cols, hgap, vgap));
    
    
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(kandidatPanel, gbc);

    String fullName = this.mainModel.getNachNamen(zeile) 
                      +
                      ", "
                      +
                      this.mainModel.getVorNamen(zeile);
    
    final int fontSizeSmall = 10;
    final int fontSizeBig = 13;
    
    JLabel kandidatNameLabel = new JLabel(fullName);
    kandidatNameLabel.setFont(new Font("Arial", Font.BOLD, fontSizeBig));
    kandidatNameLabel.setForeground(this.erstStimmeColor);
    kandidatPanel.add(kandidatNameLabel);
    
    JLabel kandidatBerufLabel = new JLabel(this.mainModel.getBeruf(zeile));
    kandidatBerufLabel.setFont(new Font("Arial", Font.PLAIN, fontSizeSmall));
    kandidatBerufLabel.setForeground(this.erstStimmeColor);
    kandidatPanel.add(kandidatBerufLabel);
    
    JLabel kandidatOrtLabel = new JLabel(this.mainModel.getOrt(zeile));
    kandidatOrtLabel.setFont(new Font("Arial", Font.PLAIN, fontSizeSmall));
    kandidatOrtLabel.setForeground(this.erstStimmeColor);
    kandidatPanel.add(kandidatOrtLabel);
    
    JLabel kandidatParteiLabel = new JLabel(
        this.mainModel.getParteiKurz(zeile));
    kandidatParteiLabel.setFont(new Font("Arial", Font.BOLD, fontSizeBig));
    kandidatParteiLabel.setForeground(this.erstStimmeColor);
    kandidatPanel.add(kandidatParteiLabel);
    
    JLabel dummyLabel = new JLabel(// Label, dass eine Leerzeile inklusive
        "                    "      // Groeszenangabe durch Leerzeichen macht.
        +
        "                    "
        +
        "                    " // 20 Leerzeichen
        +
        "          ");         // 10 Leerzeichen  ; insgesamt 70 Zeichen
     
    kandidatPanel.add(dummyLabel);
    
    
    erstStimmePanel.add(kandidatPanel);
    
    // Panel mit dem Ankreuzpanel
   
    JPanel ankreuzPanel = this.createAnkreuzPanel(zeile, true);
    ankreuzPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
      
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(ankreuzPanel, gbc);

    erstStimmePanel.add(ankreuzPanel);
    
    
    return erstStimmePanel;
  }
  
  /**
   * Erzeugt das zweitStimmePanel, welches wiederum 3 Panels
   * enthealt fuer jeweils Ankreuzpanel, Kandidatenbeschreibung
   * und Zeilenangabe.
   * @param zeile Angabe der Zeile dieses Panels, damit die Daten
   * korrekt von MainModel geholt werden.
   * @return Das Panel.
   */
  private JPanel createZweitStimmePanel(int zeile) {
    
    JPanel zweitStimmePanel = new JPanel();
    zweitStimmePanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);  
    zweitStimmePanel.setBorder(
        BorderFactory.createLineBorder(
            this.zweitStimmeColor,
            this.stimmBorderThickness));

    GridBagLayout gbl = new GridBagLayout();
    zweitStimmePanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 0;   
    final int fontSizeSmall = 10;
    final int fontSizeBig = 12;
    
    /////////////////////////////
    
    JPanel ankreuzPanel = this.createAnkreuzPanel(zeile, false);
    ankreuzPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);  
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(ankreuzPanel, gbc);

    zweitStimmePanel.add(ankreuzPanel);
    
    ///////////////////////////

    JPanel beschreibungPanel = new JPanel();
    beschreibungPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    
    final int rows = 0; //zeilenzahl wird automatisch errechnet
    final int cols = 1; 
    final int hgap = 0;
    final int vgap = 0;
    beschreibungPanel.setLayout(new GridLayout(rows, cols, hgap, vgap));
    
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(beschreibungPanel, gbc);

    //
    // JLabel fuer die Parteiangabe
    //
    JLabel parteiLangLabel = new JLabel(this.mainModel.getParteiLang(zeile));
    parteiLangLabel.setFont(new Font("Arial", Font.BOLD, fontSizeBig));
    parteiLangLabel.setForeground(this.zweitStimmeColor);
    beschreibungPanel.add(parteiLangLabel);
    
    // Hole alle Zweitkandidaten als ein String 
    // und fuege zwischen ihnen ein ", " ein.
    
    String[] array = this.mainModel.getZweitKandidaten(zeile);
    String kandidaten = "";
    for (int i = 0; i < array.length; i++) {
      
      kandidaten = kandidaten.concat(", " + array[i]);
    }
    kandidaten = kandidaten.substring(2); //erstes ", " entfernen      
    
    //
    // Mehrere JLabels fuer die Zweitkandidaten
    //    
    final int zeileMaxLength = 50;
    int lastIndex = this.getZeilenUmbruchPosition(zeileMaxLength, kandidaten);
    
    while (lastIndex != -1) {      
      
      JLabel zweitKandidatenLabel = new JLabel(
          kandidaten.substring(0, lastIndex));
      zweitKandidatenLabel.setFont(
          new Font("Arial", Font.PLAIN, fontSizeSmall));
      zweitKandidatenLabel.setForeground(this.zweitStimmeColor);
      beschreibungPanel.add(zweitKandidatenLabel);
         
      kandidaten = kandidaten.substring(lastIndex + 1);
      lastIndex = this.getZeilenUmbruchPosition(zeileMaxLength, kandidaten);
      
    } 
    JLabel zweitKandidatenLabel = new JLabel(kandidaten);
    zweitKandidatenLabel.setFont(new Font("Arial", Font.PLAIN, fontSizeSmall));
    zweitKandidatenLabel.setForeground(this.zweitStimmeColor);
    beschreibungPanel.add(zweitKandidatenLabel);
    
    JLabel dummyLabel = new JLabel(// Label, dass eine Leerzeile inklusive
        "                    "      // Groeszenangabe macht
        +
        "                    " // 20 Leerzeichen
        +
        "                                        "
        +
        "                    ");
    beschreibungPanel.add(dummyLabel);
    
    zweitStimmePanel.add(beschreibungPanel);
     
    ///////////////////////////
    
    JPanel zeilePanel = this.createZeileAngabePanel(zeile, false);
    zeilePanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);  
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(zeilePanel, gbc);
    
    zweitStimmePanel.add(zeilePanel);
    
    ///////////////////////////
    
    return zweitStimmePanel;
  }
  
  /**
   * Erzeugt das Panel mit der Zeilenangabe.
   * @param zeile Die Zeilennummer, welche angezeigt werden soll.
   * @param erstStimme Angabe ob sich um die Erst- oder Zweitstimme handelt.
   * @return Das Panel.
   */
  private JPanel createZeileAngabePanel(int zeile, boolean erstStimme) {
    
    JPanel zeilePanel = new JPanel();
    
    final int hgap = 5;
    final int vgap = 5;
    zeilePanel.setLayout(new BorderLayout(hgap, vgap));
    
    zeilePanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);

    
    JLabel zeileLabel = new JLabel(new Integer(zeile + 1).toString());
    
    final int fontSize = 30;
    zeileLabel.setFont(new Font("Arial", Font.BOLD, fontSize));
    
    if (erstStimme) {
      
        zeileLabel.setForeground(this.erstStimmeColor);
        
    } else {
      
        zeileLabel.setForeground(this.zweitStimmeColor);
    }
    
    
    zeilePanel.add(zeileLabel, BorderLayout.CENTER);
    
    
    return zeilePanel;
  }
  
  /**
   * Erzeugt das Panel mit dem Kreuz zum markieren.
   * @param zeile Die Zeilennummer, welche angezeigt werden soll.
   * @param erstStimme Angabe ob sich um die Erst- oder Zweitstimme handelt.
   * @return Das Panel.
   */
  private JPanel createAnkreuzPanel(int zeile, boolean erstStimme) {
    
    JPanel zeilePanel = new JPanel();
    
    final int hgap = 5;
    final int vgap = 5;
    zeilePanel.setLayout(new BorderLayout(hgap, vgap));
    
    zeilePanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);

    final Icon uncheckedErstStimme = new ImageIcon(
        StimmzettelView.class.getResource(
            "uncheckedErstStimme.png"));
    final Icon checkedErstStimme = new ImageIcon(
        StimmzettelView.class.getResource(
            "checkedErstStimme.png"));

    final Icon uncheckedZweitStimme = new ImageIcon(
        StimmzettelView.class.getResource(
            "uncheckedZweitStimme.png"));
    final Icon checkedZweitStimme = new ImageIcon(
        StimmzettelView.class.getResource(
            "checkedZweitStimme.png"));
    
    JRadioButton radioButton = new JRadioButton();

    
    if (erstStimme) {
      
      radioButton.setIcon(uncheckedErstStimme);
      radioButton.setSelectedIcon(checkedErstStimme);
      zeilePanel.add(radioButton, BorderLayout.EAST);
      this.erstStimmeButtonList.add(radioButton);
      
    } else {
      
      radioButton.setIcon(uncheckedZweitStimme);
      radioButton.setSelectedIcon(checkedZweitStimme);
      zeilePanel.add(radioButton, BorderLayout.WEST);
      this.zweitStimmeButtonList.add(radioButton);
    }
    
    
    return zeilePanel;
  }
  
  /**
   * Interne Hilfsmethode, welche die Position in einen String
   * ermittelt, an welcher ein Zeilenumbruch erfolgen kann.
   * Der Umbruch findet nur an den Stellen statt, an denen ein
   * Leerzeichen ist. Diese Methode findet das letzte Leerzeichen
   * dessen Index aber immer noch kleiner ist, als der uebergebene
   * limitIndex.
   * @param limitIndex Die maximale Anzahl Zeichen in einer Zeile.
   * @param str Der String, in dem die Zeilenumbruchposition
   * ermittelt werden soll
   * @return Die Position als Index (int) fuer den String str, falls
   * keine geeignete Position gefunden wurde (kein Leerzeichen vorhanden ist)
   * oder der limitIndex groeszer als die Stringlaenge von str ist, gibt
   * es eine -1 zurueck.
   */
  private int getZeilenUmbruchPosition(int limitIndex, String str) {
    
    int aktIndex = 0;
    int lastIndex = 0;
    
    if (str.length() < limitIndex) {
      
      lastIndex = -1;
      
    } else {
      
      do {
        
        lastIndex = aktIndex;
        aktIndex = str.indexOf(" ", lastIndex + 1);
        
      } while (aktIndex <= limitIndex && aktIndex != -1);
    }
    
    return lastIndex;
    
  }
  
  /**
   * Erzeugt das ButtonPanel, in die Schaltflaechen liegen.
   * @return Das Panel.
   */
  private JPanel createButtonPanel() {
    
    JPanel buttonPanel = new JPanel();
    
    buttonPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    
    this.confirmButton = new JButton("Stimmzettel abgeben");
    buttonPanel.add(confirmButton);
    
    return buttonPanel;
    
  }
  
  /**
   * Erhoet den Zaehlerstand eines bestimmten Kandidaten bzw.
   * einer bestimmten Partei. Diese/r Partei/Kandidat wird durch
   * die Zeilenangabe bestimmt.
   * Wird vom Controller gebraucht.
   * @param zeile Zeilenangabe, um die Partei/Kandidat zu bestimmen.
   * @param erstStimme Wenn true, wird der Kandidat in der angegebenen
   * Zeile erhoet, sonst die Partei.
   */
  public final void count(int zeile, boolean erstStimme) {
         
      this.mainModel.count(zeile, erstStimme);
      
  }
  
  /**
   * Entfernt den Waehler aus der Liste der aktiven Waehlern.
   * Das ist der Waehler, der diesen Stimmzettel ausgefuellt hat.
   */
  public final void deleteActiveWaehler() {
    
    this.mainModel.deleteActiveWaehler(this.waehlerKey);
  }
  
} //class end
