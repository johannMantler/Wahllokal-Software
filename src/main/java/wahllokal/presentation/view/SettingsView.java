package wahllokal.presentation.view;

import wahllokal.business.MainModel;
import wahllokal.presentation.controller.SettingsAbortController;
import wahllokal.presentation.controller.SettingsCheckInputController;
import wahllokal.presentation.controller.SettingsController;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * GUI fuer die generellen Einstellungen vom Wahlhelfer.
 * Dieser Dialog wird vor der Eroeffnung des Wahllokals
 * gestartet.
 * @author Johann
 * <p>Zuletzt bearbeitet: 08.06.2011</p>
 */
public final class SettingsView {


  /**Die Klasse baut auf diesem Dialog auf.*/
  private JDialog dialog;

  /** Fuer die eingegebene Kreisnummer.*/
  private JTextField kreisNr;

  /** Fuer die eingegebene Bezirksnummer.*/
  private JTextField bezirkNr;

  /** Fuer die eingegebene Kreisnummer.*/
  private JTextField kabinenZahl;
  
  /** Fuer die Datumsangaben. */
  private Calendar calendar;

  /** der "Confirm"-Button fuer die Einstellungen.*/
  private JButton ok;

  /** Der "Abbrechen"-Button fuer die Einstellungen.*/
  private JButton abort;

  /**
   * Gibt eine Referenz auf den Button zurueck, mit
   * welchem die Nutzereingaben bestaetigt werden.
   * @return the ok
   */
   public JButton getOk() {
     return ok;
   }

   /**
    * Gibt eine Referenz auf den Button zurueck, mit dem
    * dieser Dialog beendet wird.
    * @return the abort
   */
   public JButton getAbort() {
     return abort;
   }

   /**
    * Gibt eine Referenz auf das TextFeld zurueck, das
    * die Wahlkreisnummer enthaelt.
   * @return the kreisNr
   */
   public JTextField getKreisNr() {
     return kreisNr;
   }

   /**
    * Gibt eine Referenz auf das TextFeld zurueck, das
    * die Wahlbenzirknummer enthaelt.
   * @return the bezirkNr
   */
   public JTextField getBezirkNr() {
     return bezirkNr;
   }

   /**
   * @return the kabinenZahl
   */
   public JTextField getKabinenZahl() {
     return kabinenZahl;
   }

   /**
    * Gibt eine Referenz auf das TextFeld zurueck, das
    * die Wahlkabinennummer enthaelt.
   * @return the dialog
   */
   public JDialog getDialog() {
     return dialog;
   }

  
  /** Definiert die Standard-Rahmendicke. */
  private final int defaultBorderThickness = 2;

  /** Default Insetswert der GridBagConstraints. */
  private final int insetsDefault = 2;

  /** Label mit Kurzinformation über den Eingabetextfeld. */
  private JLabel infoLabel; //hierfuer gibt es keine getter oder setter

  /** Referenz auf einzige Instanz. */
  private static SettingsView soleInstance = null;

  /** Liefere Referenz auf einzige Instanz der Klasse.
  * @param view SearchView zu dem SettingsView modal sein soll.
  * @return Referenz auf Instanz der Klasse
  */
  public static synchronized SettingsView getInstance(final SearchView view) {
  
    SettingsView returnValue = SettingsView.soleInstance;
    
    if (null != view) {
    
      if (soleInstance == null) {
        
          soleInstance = new SettingsView(view);
          returnValue = soleInstance;
      }
      
    } else { //wenn der Parameter null war, gibts auch null zurueck
          returnValue = null;
    }
    
    return returnValue;
  }

  /**
   * Einziger Konstruktor, der nur durch getInstance
   * angesprochen wird.
   * @param view SearchView zu dem SettingsView modal sein soll.
   */
  private SettingsView(final SearchView view) {

    JFrame frame = view.getMainFrame();
    dialog = new JDialog(frame, true); //modal zu frame
    dialog.getContentPane().add(this.createMainPanel(), BorderLayout.CENTER);

    new SettingsController(this); //Controller(Steuerungsobjekte) hinzufuegen
    new SettingsAbortController(this);
    new SettingsCheckInputController(this);

    dialog.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);

    dialog.setTitle("Einstellungen");
    
    final int xSize = 350;
    final int ySize = 360;
    dialog.setSize(xSize, ySize);

    dialog.setLocationRelativeTo(frame); //zentriert anzeigen
    
    dialog.setResizable(false);
    dialog.setVisible(true);

  }

  /**
   * Erzeugt ein Panel, auf dem alle Komponenten des
   * ContentPane enthalten sind.
   * Dieses Panel unterteilt sich in 3 Unterpanels, welche mit
   * createGreetingPanel(), createInsertPanel() und createButtonPanel
   * gefuellt werden.
   * @return mainPanel Das Hauptpanel fuer die GUI
   */
  private JPanel createMainPanel() {

    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);

    this.setDefaultBorder(mainPanel);

    GridBagLayout gbl = new GridBagLayout();
    mainPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsBigger  = 10;
    int gridy = -1; //erhoeht sich immer um 1
    
    JPanel greetingPanel = createGreetingPanel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; // gridy = 0
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(greetingPanel, gbc);
    mainPanel.add(greetingPanel);

    JPanel insertPanel = createInsertPanel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; // gridy = 1
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsBigger,   //bottom
                            insetsDefault); //right
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(insertPanel, gbc);
    mainPanel.add(insertPanel);

    JPanel buttonPanel = createButtonPanel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; // gridy = 2
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsBigger,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(buttonPanel, gbc);
    mainPanel.add(buttonPanel);

    JPanel errorPanel = createErrorPanel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = ++gridy; // gridy = 3
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(errorPanel, gbc);
    mainPanel.add(errorPanel);

    return mainPanel;
  }

  /**
   * Erzeugt einen Panel der den Begrueszungstext enthaelt.
   * @return greetingPanel Der Panel
   */
  private JPanel createGreetingPanel() {

    JPanel greetingPanel = new JPanel();
    greetingPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
    greetingPanel.setLayout(
        new GridLayout(0,   //die Zeilenzahl wird automatisch ermittelt 
                       1,   //spalten 
                       0,   //hgap
                       0)); //vgap

    
    final int fontSizeSmall = 11;
    final int fontSizeBig = 14;
    
    JLabel dateLabel = new JLabel(this.getCurrentDate());
    dateLabel.setFont(new Font("Arial", Font.PLAIN, fontSizeSmall));
    dateLabel.setForeground(SearchView.DEFAULTBORDERCOLOR);
    dateLabel.setHorizontalAlignment(JLabel.RIGHT);

    greetingPanel.add(dateLabel);
    
    
    String headLine = "Wahllokal-Software für den Landtag";
    JLabel headLineLabel = new JLabel(headLine);
    headLineLabel.setFont(new Font("Arial", Font.BOLD, fontSizeBig));
    headLineLabel.setForeground(SearchView.DEFAULTBORDERCOLOR);
    headLineLabel.setHorizontalAlignment(JLabel.CENTER);

    greetingPanel.add(headLineLabel);
    
    String description = 
      "<html><body>" 
      +
         "Zur Eröffnung des Wahllokals machen Sie <br/>"
      +
         "bitte zuerst generelle Angaben zum Lokal:"
      + 
      "<br/><br/></body></html>";
    
    JLabel descriptionLabel = new JLabel(description);
    descriptionLabel.setFont(new Font("Arial", Font.PLAIN, fontSizeSmall));
    descriptionLabel.setForeground(SearchView.DEFAULTBORDERCOLOR);
    descriptionLabel.setHorizontalAlignment(JLabel.CENTER);

    greetingPanel.add(descriptionLabel);
  

    return greetingPanel;
  }
  
  /**
   * Erzeugt ein String mit dem aktuellen Datum.
   * oeffentlich, da diese Methode auch vom Controller
   * benutzt wird.
   * @return Der String.
   */
  public String getCurrentDate() {
    
    this.calendar = Calendar.getInstance();
    
    String[] days = { "Sonntag", "Montag", "Dienstag",
                      "Mittwoch", "Donnerstag", "Freitag", "Samstag"};
    String dayOfWeek = days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    
    String dateTime = String.format("%s, %s.%s.%s",
        dayOfWeek,
        calendar.get(Calendar.DATE),
        (calendar.get(Calendar.MONTH) + 1),
        calendar.get(Calendar.YEAR));
    
    return dateTime;
    
  }
  
  /**
   * Erzeugt ein Panel mit den Textfeldern zur Eingabe.
   * @return insertPanel Der Panel
   */
  private JPanel createInsertPanel() {

    String kreisNrString     = "WahlkreisNr.:"; //fuer die Labels
    String bezirkNrString    = "WahlbezirkNr.:";
    String kabinenZahlString = "Wahlkabinenanzahl:";

    JPanel insertPanel = new JPanel();
    insertPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);

    this.setDefaultBorder(insertPanel);

    GridBagLayout gbl = new GridBagLayout();
    insertPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int textFieldSize = 10;
    final int insetsBigger = 7;
    final int ipady = insetsDefault + 1;
    final int ipadxTextField = 25;

    JLabel wahlkreisLabel = new JLabel(kreisNrString);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(insetsBigger,  //top
                            insetsBigger,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.ipadx = 0;
    gbc.ipady = ipady;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(wahlkreisLabel, gbc);
    insertPanel.add(wahlkreisLabel);

    kreisNr = new JTextField(textFieldSize);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(insetsBigger,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsBigger); //right
    gbc.ipadx = ipadxTextField;
    gbc.ipady = ipady;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(kreisNr, gbc);
    insertPanel.add(kreisNr);

    JLabel wahlbezirkLabel = new JLabel(bezirkNrString);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsBigger,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.ipadx = 0;
    gbc.ipady = ipady;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(wahlbezirkLabel, gbc);
    insertPanel.add(wahlbezirkLabel);

    bezirkNr = new JTextField(textFieldSize);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsBigger); //right
    gbc.ipadx = ipadxTextField;
    gbc.ipady = ipady;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(bezirkNr, gbc);
    insertPanel.add(bezirkNr);

    JLabel wahlkabinenLabel = new JLabel(kabinenZahlString);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsBigger,  //left
                            insetsBigger,  //bottom
                            insetsDefault); //right
    gbc.ipadx = 0;
    gbc.ipady = ipady;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(wahlkabinenLabel, gbc);
    insertPanel.add(wahlkabinenLabel);

    kabinenZahl = new JTextField(textFieldSize);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridheight = 1;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsBigger,  //bottom
                            insetsBigger); //right
    gbc.ipadx = ipadxTextField;
    gbc.ipady = ipady;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(kabinenZahl, gbc);
    insertPanel.add(kabinenZahl);

    return insertPanel;
  }

  /**
   * Erstellt ein JPanel mit den Buttons zum
   * Bestaetigen und zum Abbrechen.
   * @return buttonPanel Das JPanel mit den Buttons
   */
  private JPanel createButtonPanel() {

	  JPanel buttonPanel = new JPanel();
	  buttonPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);

    GridBagLayout gbl = new GridBagLayout();
    buttonPanel.setLayout(gbl);
    GridBagConstraints gbc;

    ok = new JButton("Wahllokal eröffnen");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbl.setConstraints(ok, gbc);
    buttonPanel.add(ok);

    abort = new JButton("Abbruch");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbl.setConstraints(abort, gbc);
    buttonPanel.add(abort);

    return buttonPanel;
  }
  /**
   * Erzeugt ein Panel, das extra fuer die Fehlerausgabe
   * bestimmt ist.
   * @return errorPanel Das Panel
   */
  private JPanel createErrorPanel() {
  	
  	JPanel errorPanel = new JPanel();
  	errorPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
  	errorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
  	this.infoLabel = new JLabel();
  	this.setDefaultInfoLabel();
  	
  	errorPanel.add(infoLabel);
  	return errorPanel;
  }
  
  /**
   * Setzt die Default-Einstelltungen vom infoLabel.
   */
  public void setDefaultInfoLabel() {
    this.infoLabel.setForeground(SearchView.DEFAULTBACKGROUNDCOLOR);
    this.infoLabel.setText("unsichtbar :-)");
    this.ok.setEnabled(true);
  }

  /**
   * Wird zur Fehler-Anzeige verwendet.
   * Wird von den Controllern verwendet.
   * Ueberschreibt das infoLabel mit der Information, die im
   * Parameter text uebergeben wird.
   * @param text Die darzustellende Information.
   */
  public void setErrorInfoLabel(final String text) {
    this.infoLabel.setForeground(Color.RED);
    this.infoLabel.setText(text);
    this.ok.setEnabled(false);
  }

  /**
   * Setzt einen Rahmen um einen Panel.
   * Die Methode benutzt dafür die Standard Werte fuer
   * Rahmendicke und Rahmenfarbe.
   * @param panel Das Panel
   */
  private void setDefaultBorder(final JPanel panel) {
    
    panel.setBorder(BorderFactory.
        createLineBorder(SearchView.DEFAULTBORDERCOLOR,
                         this.defaultBorderThickness));
  }
  
  /**
   * Gibt die Daten inklusive Zeitstempel an das 
   * Model weiter.
   * Das sind die Daten die der Nutzer in den Dialog
   * eingegeben hat.
   */
  public void dataToModel() {
    
    String timeString =  String.format("%tT%n", 
        Calendar.getInstance());

    String dateString = String.format("%s", this.getCurrentDate());

    MainModel model = MainModel.getInstance();

    model.setTimeString(timeString);
    model.setDateString(dateString);
    model.setBezirkNr(this.getBezirkNr().getText());
    model.setKabinenZahl(this.getKabinenZahl().getText());
    model.setKreisNr(this.getKreisNr().getText());
    
  }
  
  /**
   * Ein Aufruf dieser Methode, startet soviele Stimmzettel-GUIs
   * wie es Wahlkabinen gibt.
   * Jeder Stimmzettel laeuft, solange dieses keine verteilte
   * Anwendung ist, in einem seperaten Thread.
   */
  public void showStimmzettel() {
    
    for (int i = 0; 
          i < new Integer(this.getKabinenZahl().getText()); i++) {

      new Thread(new Runnable() {

        public void run() {
            new StimmzettelStartView();
        }

      }).start();
    }
  
  }

} // class SettingsView {..
