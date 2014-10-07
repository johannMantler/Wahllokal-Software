package wahllokal.presentation.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import wahllokal.business.MainModel;
import wahllokal.presentation.controller.PersonController;

/**
 * Die GUI zum Anzeigen von personenspezifischen Angaben
 * einer wahlberechtigten Person.
 * @author Johann
 * <p>zuletzt bearbeitet: 23.06.2011</p>
 */
public final class PersonView {

  /** Dieser JDialog. */
	private JDialog dialog;

  /**
   * Gibt die Referenz auf den JDialog zurueck.
   * @return the dialog
   */
  public JDialog getDialog() {
    return dialog;
  }

  /** Das Textfeld fuer den Nachnamen des Waehlers. */
  private JTextField nNameTextField;

  /** Das Textfeld fuer den Vornamen des Waehlers. */
  private JTextField vNameTextField;

  /** Das Textfeld fuer das Geburtsdatum des Waehlers. */
  private JTextField gebDatumTextField;
 
  /** Label, welches generelle Infos zu dem Waehler anzeigt,
   * zB ob dieser noch wahlberechtigt ist, usw . */
  private JLabel infoLabel;
  
  /** Das Model, von dem die Personendaten geholt werden. */
  private MainModel model;

  /** Definiert die Standard - Rahmendicke. */
  private final int defaultBorderThickness = 2;
  
  /** Label, das den BerechtigungsCode vom Waehler anzeigt. */
  private JLabel waehlerKey;
  
  /** Label, welches die Beschreibung ueber den Code enthealt. */
  private JLabel waehlerKeyBeschreibung;

  /** Definiert einen Button, mit dem der Nutzer den Dialog abbrechen kann.*/
  private JButton abort;
  
  /** Button, mit dem der Wahlhelfer einen BerechtigungsCode fuer den
   * Waehler anfordern kann, sofern dieser noch keinen besitzt.*/
  private JButton weiter;

  /**
   * Liefert den Weiter-Button als Referenz.
   * @return the weiter
   */
   public JButton getWeiter() {
      return weiter;
    }
  
  /**
   * Liefert den Abbrechen-Button als Referenz.
  * @return the abort
  */
  public JButton getAbort() {
     return abort;
   }
	

  /** Liefere Referenz auf Instanz der Klasse.
   * Dies Methode dient dazu, die uebergegebenen Parameter
   * zuerst zu ueberpruefen, bevor ein Objekt von PersonView
   * zurueckgeben wird. Bei Fehler gibt es eine null zurueck.
  * @param view SearchView zu dem PersonView modal sein soll.
  * @param daten Stringarray welches die Personendaten
  * beinhaltet.
  * @return Referenz auf Instanz der Klasse oder null
  */
  public static PersonView getInstance(final SearchView view,
                                       final String []daten) {
  
    PersonView returnValue = null;
    
    if (null != view && null != daten) {
      
      returnValue = new PersonView(view, daten);
    }
    
    return returnValue; //Falls view null ist, gibts null zurueck
  }
   
   /**
   * Einziger Konstruktor dieser Klasse.
   * @param view Die SearchView, die dieses Objekt aufruft.
   * @param daten Stringarray welches die Personendaten
   * beinhaltet.
   */
  private PersonView(final SearchView view, final String []daten) {
    
    JFrame frame = view.getMainFrame(); //Hole das JFrame der SearchView
    this.dialog = new JDialog(frame, true); //modal zu frame
    this.model = MainModel.getInstance();
    this.dialog.getContentPane().add(createMainPanel(), BorderLayout.CENTER);
    this.setInfoLabel();
    this.dialog.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);

    this.dialog.setTitle("Person");
    
    this.setPersonData(daten);

    final int xSize = 350;
    final int ySize = 360;
    this.dialog.setSize(xSize, ySize);

    this.dialog.setLocationRelativeTo(frame); //zentriert anzeigen
    
    new PersonController(this); //Controller hinzufuegen
    
    this.dialog.setResizable(false);
    this.dialog.setVisible(true); 

  }
  
  /**
   * Dies Methode setzt die Personendaten in
   * die Textfelder.
   * @param daten Stringarray welches die Personendaten
   * beinhaltet.
   */
  private void setPersonData(final String []daten) {
    
    final int nNamePosition = 0;
    final int vNamePosition = 1;
    final int gebDatumPosition = 2;
    
    this.nNameTextField.setText(daten[nNamePosition]);
    this.vNameTextField.setText(daten[vNamePosition]);
    this.gebDatumTextField.setText(daten[gebDatumPosition]);
    
  }
  
  /**
   * Erzeugt ein Panel, auf dem alle Komponenten des
   * ContentPane enthalten sind.
   * Dieses Panel unterteilt sich in 3 Unterpanels, welche mit
   * createInfoPanel(), createInsertPanel() und createButtonPanel
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

    final int insetsDefault = 2;
    final int insetsBigger = 10;
    
    JPanel infoPanel = createInfoPanel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,   //bottom
                            insetsDefault); //right
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(infoPanel, gbc);
    mainPanel.add(infoPanel);

    JPanel personPanel = createPersonPanel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,  //left
                            insetsBigger,   //bottom
                            insetsDefault); //right
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(personPanel, gbc);
    mainPanel.add(personPanel);

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
   * Erzeugt einen Panel der den ist-wahlberechtigt-text enthaelt.
   * @return infoPanel Der Panel
   */
  private JPanel createInfoPanel() {

    JPanel infoPanel = new JPanel();
    infoPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
   // this.setDefaultBorder(infoPanel);
    

    GridBagLayout gbl = new GridBagLayout();
    infoPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 2;
    final int fontSizeSmall = 13;
    final int fontSizeBig = 15;
    
    this.infoLabel = new JLabel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbl.setConstraints(infoLabel, gbc);
    infoPanel.add(infoLabel);
    
    this.waehlerKeyBeschreibung = new JLabel("Code:  ");
    this.waehlerKeyBeschreibung.setFont(new Font("Arial", 
                                        Font.BOLD, 
                                        fontSizeSmall));
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbl.setConstraints(this.waehlerKeyBeschreibung, gbc);
    infoPanel.add(this.waehlerKeyBeschreibung);

    this.waehlerKey = new JLabel();
    this.waehlerKey.setFont(new Font("Arial", Font.BOLD, fontSizeBig));
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(insetsDefault,   //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbl.setConstraints(this.waehlerKey, gbc);
    infoPanel.add(this.waehlerKey);
    
    this.showPersonKey(false);
    
    return infoPanel;
  }
	
  
  /**
   * Erzeugt ein Panel mit allen Komponeten einer Person.
   * @return panel Enthaelt alle Komponenten von einer Person
   */
  private JPanel createPersonPanel() {

    JPanel personPanel = new JPanel();
    personPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);

   personPanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.
        createLineBorder(SearchView.DEFAULTBORDERCOLOR,
            this.defaultBorderThickness),
        "Person",
        TitledBorder.CENTER,
        TitledBorder.CENTER));

    GridBagLayout gbl = new GridBagLayout();
    personPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 1;
    final int insetsBigger = 5;
    final int ipadyDefault = 5;
    final int textFieldLength = 15;
    
    int gridy = -1; //inkrementiert nach jeder neuen Zeile

    JLabel nachnameLabel = new JLabel("Nachname: ");
    nachnameLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
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
    gbl.setConstraints(nachnameLabel, gbc);
    personPanel.add(nachnameLabel);

    nNameTextField = new JTextField(textFieldLength);
    this.setDefaultTextFieldFormat(nNameTextField);
    
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
    gbl.setConstraints(nNameTextField, gbc);
    personPanel.add(nNameTextField);
    
    
    JLabel vornameLabel = new JLabel("Vorname: ");
    vornameLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
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
    gbl.setConstraints(vornameLabel, gbc);
    personPanel.add(vornameLabel);

    
    vNameTextField = new JTextField(textFieldLength);
    this.setDefaultTextFieldFormat(vNameTextField);
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
    gbl.setConstraints(vNameTextField, gbc);
    personPanel.add(vNameTextField);


    JLabel geburtsdatumLabel = new JLabel("Geburtsdatum: ");
    geburtsdatumLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
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
    gbl.setConstraints(geburtsdatumLabel, gbc);
    personPanel.add(geburtsdatumLabel);

    gebDatumTextField = new JTextField(textFieldLength);
    this.setDefaultTextFieldFormat(gebDatumTextField);
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
    gbl.setConstraints(gebDatumTextField, gbc);
    personPanel.add(gebDatumTextField);

    return personPanel;
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

    this.weiter = new JButton("Weiter mit Wählen");
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
    gbl.setConstraints(this.weiter, gbc);
    buttonPanel.add(this.weiter);
    
    this.abort = new JButton("Abbrechen");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
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
   * Methode, die angibt, ob der BerechtigungsCode angezeigt werden
   * soll oder nicht.
   * @param show Bei true, wird der Code angezeigt, bei false nicht.
   */
  public void showPersonKey(boolean show) {
    
    if (show) {
   
      this.waehlerKey.setText(model.getBerechtigungsCode());
      this.waehlerKey.setForeground(Color.ORANGE);
      this.waehlerKeyBeschreibung.setForeground(Color.BLACK);
      
    } else {
      
      //der Key wird nachwievor angezeigt, ist jedoch nicht sichtbar
      this.waehlerKey.setForeground(SearchView.DEFAULTBACKGROUNDCOLOR);
      this.waehlerKeyBeschreibung.setForeground(
             SearchView.DEFAULTBACKGROUNDCOLOR);
    }
  }
  
  /**
   * Ein Aufruf dieser Methode fuegt den Waehler zu der Liste
   * der aktiven Waehler hinzu und er bekommt dabei auch automatisch
   * einen BerechtigungsCode.
   */
  public void setWaehlerActive() {
    
    model.setWaehlerActive();
  }
  
  /**
   * Ein Aufruf dieser Methode setzt den Infotext im Infolabel,
   * entsprechend dem Status des Wählers.
   * Es werden 3 Varianten unterschieden:
   * Entweder der Waehler ist noch wahlberechtigt oder er ist
   * zurzeit aktiv (dh. er waehlt noch) oder er hat bereits
   * gewaehlt.
   */
  public void setInfoLabel() {
    
    if (null != model.getBerechtigungsCode() //wenn der Waehler einen Code
        &&                                   //erhalten hat, aber noch nicht
        !model.getGewaehlt()) {           // gewaehlt hat, ist er aktiv..
      
      this.infoLabel.setText(
          "<html>"
          +
         "<body>"
          +
           "<h2>"
          +
             "Wähler ist zurzeit aktiv"
          +
           "</h2>"
          +
         "</body>"
          +
       "</html>");
      
      this.weiter.setEnabled(false);
      
      //Code nochmals anzeigen, weil was ist wenn die betreffende Person
      // ihren Code verloren hat?
      this.showPersonKey(true);
      
    } else if (null != model.getBerechtigungsCode() //wenn Waehler einen Code
               &&                         //erhalten hat und auch schon
               model.getGewaehlt()) {     //gewaehlt hat..
      
        this.infoLabel.setText(
          "<html>"
          +
           "<body>"
          +
             "<h2>"
          +
               "Hat bereits gewählt"
          +
             "</h2>"
          +
            "</body>"
          +
          "</html>");
        this.weiter.setEnabled(false);
    
    } else if (!model.getGewaehlt()
        &&
        null == model.getBerechtigungsCode()) {
      
      this.infoLabel.setText(
          "<html>"
          +
          "<body>"
           +
            "<h2>"
           +
              "Wahlberechtigt"
           +
            "</h2>"
           +
          "</body>"
           +
        "</html>");
      
      this.weiter.setEnabled(true);
    }
    
  }
  
} //class end
