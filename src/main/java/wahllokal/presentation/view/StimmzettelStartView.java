package wahllokal.presentation.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import wahllokal.business.MainModel;
import wahllokal.presentation.controller.SStartCheckInputController;
import wahllokal.presentation.controller.StimmzettelStartController;

/**
 * GUI, die vor der Stimmzettel GUI (StimmzettelView)
 * erscheint und den Nutzer dafür vorbereitet.
 * Hier muss der Nutzer sein Authentifikationscode
 * eingeben, welcher er zuvor vom Wahlhelfer
 * bekommen hat -> Damit nicht jeder einfach loswaehlen kann
 * @author Johann
 * <p>Zuletzt bearbeitet: 22.06.2011</p>
 */
public class StimmzettelStartView {

  /** Das Textfeld in dem die Codeeingabe erfolgt. */
  private JTextField codeInputField;
  
  /**
   * Gibt eine Referenz auf das TextFeld zurueck, in
   * dem die Codeeingabe stattfindet.
   * @return the codeInputField
   */
  public final JTextField getCodeInputField() {
    return codeInputField;
  }

  /** Zeigt Fehlerinformation fuer das Textfeld an. */
  private JLabel errorLabel;
  
  /**Enthaelt den Begrueszungstext. */
  private String greeting =
    "<html>"
       +
      "<body>"
       +
        "<h1>"
       +
          "Herzlich Willkommen!"
       +
        "</h1>"
       +
        "<h4>"
       +
          "Bitte geben Sie zur Überprüfung ihrer Wahlberechtigung" 
       +
          " den Berechtigungscode ein, <br/>"
       +
          "den Sie zuvor vom Wahlhelfer erhalten haben."
       +
        "</h4>"
       +
      "</body>"
       +
    "</html>";
  
  /** Ein Button der die Stimmzettel-GUI starten soll. */
  private JButton weiter;
   
  /**
   * Gibt eine Referenz auf den Button zurueck, mit dem
   * die Codeeingabe bestaetigt wird.
   * @return the weiter
   */
  public final JButton getWeiter() {
    return weiter;
  }

  //Das Fenster ist nur zu Testzwecken ein JFrame, sollte das Programm
  //wirklich verteilt programmiert werden, sollte ein JWindow oder 
  //ein undekoriertes JFrame das Fenster darstellen, damit der Benutzer 
  //wirklich nur Waehlen kann und nichts anderes.
  
  /** Das Frame der GUI. */
  private JFrame mainFrame;

  /**
   * Liefert den Hauptcontainer frame dieser Klasse.
   * Diese Methode kann von den Controllern verwendet werden.
   * @return the mainFrame
   */
  public final JFrame getMainFrame() {

    return this.mainFrame;
  }
  
  /**
   * Einziger Konstruktor dieser Klasse.
   */
  public StimmzettelStartView() {
    
    this.mainFrame = new JFrame();

    this.mainFrame.add(this.createMainPanel(), BorderLayout.CENTER);

    this.mainFrame.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    this.mainFrame.setLocation(0, 0);
    this.mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

    // Controller hier hinzufuegen
    new StimmzettelStartController(this);
    new SStartCheckInputController(this);

    this.mainFrame.setVisible(true);  // hier Fenster anzeigen

    
  } //Konstruktor ende
  
  /**
   * Erzeugt das MainPanel, welches als direkter Untercontainer
   * von window fungiert. Alle Komponenten befinden sich
   * in diesem Panel.
   * @return das MainPanel.
   */
  private JPanel createMainPanel() {
    
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    
    GridBagLayout gbl = new GridBagLayout();
    mainPanel.setLayout(gbl);

    GridBagConstraints gbc;
    
    final int insetsDefault = 2;
    final int insetsBigger = 10;
    
    JPanel contentPanel = createContentPanel();

    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsBigger,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(contentPanel, gbc);
    mainPanel.add(contentPanel);

    
    return mainPanel; 
  }
  
  /**
   * Erzeugt ein Panel, indem der ganze Inhalt, z.B.
   * Begrueszungstexte, liegt.
   * @return das Panel.
   */
  private JPanel createContentPanel() {
    
    JPanel contentPanel = new JPanel();
    
    contentPanel.setBackground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
    
    GridBagLayout gbl = new GridBagLayout();
    contentPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 1;
    final int insetsBig = 3;
    final int insetsBigger = 10;
    final int textFieldSize = 20;
    final int zeile3 = 3;
    final int textFieldHeight = 10;
    final int fontSizeSmall = 12;
    final int fontSizeBig = 15;
    
    JLabel greetingLabel = new JLabel(greeting);
    greetingLabel.setForeground(Color.BLACK);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 2;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsBigger,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(greetingLabel, gbc);
    contentPanel.add(greetingLabel);
    
    this.errorLabel = new JLabel();
    this.errorLabel.setFont(new Font("Arial", Font.BOLD, fontSizeSmall));
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 2;
    gbc.ipadx = 0;
    gbc.ipady = textFieldHeight;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(this.errorLabel, gbc);
    contentPanel.add(this.errorLabel);
    
    this.showErrorLabel("You don't see me", false);
    
    JLabel textFieldBeschreibung = new JLabel("Berechtigungscode:");
    textFieldBeschreibung.setForeground(Color.BLACK);
    textFieldBeschreibung.setFont(new Font("Arial", Font.BOLD, fontSizeBig));
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = textFieldHeight;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsBigger,  //bottom
                            insetsBig); //right
    gbc.anchor = GridBagConstraints.EAST;
    gbl.setConstraints(textFieldBeschreibung, gbc);
    contentPanel.add(textFieldBeschreibung);
    
    this.codeInputField = new JTextField(textFieldSize);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = textFieldHeight;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsBig,  //left
                            insetsBigger,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(this.codeInputField, gbc);
    contentPanel.add(this.codeInputField);
    
    
    this.weiter = new JButton("Stimmzettel ausfüllen");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = zeile3;
    gbc.gridheight = 1;
    gbc.gridwidth = 2;
    gbc.ipadx = 0;
    gbc.ipady = textFieldHeight;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(this.weiter, gbc);
   
    contentPanel.add(this.weiter);
    this.weiter.setEnabled(false); //anfangs nicht klickbar
                                   //da noch nichts eingegeben wurde
    
    return contentPanel;
    
  }

  
  /**
   * Zeigt das ErrorLabel mit der Message neben dem JTextField an.
   * @param message Der Text der angezeigt werden soll.
   * @param error Angabe, ob man das Label sehen soll (true) oder
   * nicht sehen soll (false).
   */
  public final void showErrorLabel(String message, boolean error) {
    
    this.errorLabel.setText(message);
    
    if (error) {
      
      this.errorLabel.setForeground(Color.RED);
      
    } else {
      
      this.errorLabel.setForeground(StimmzettelView.DEFAULTBACKGROUNDCOLOR);
      
    }
    
  }
  
  /**
   * Sucht nach einem aktiven Waehler mit Hilfe des im Parameter
   * uebergeben Schluessels.
   * @param key Der Schluessel des Waehlers, mit dessen Hilfe der 
   * Waehler gesucht wird.
   * @return true, wenn gefunden, sonst false.
   */
  public final boolean searchActiveWaehler(String key) {
    
    return MainModel.getInstance().searchActiveWaehler(key);
    
  }
  
  /**
   * Zeigt den Stimmzettel an. 
   * Diese Methode wird dann aufgerufen, wenn der 
   * Berechtigungscode, den der Waehler eingegeben hat,
   * auch valide ist.
   */
  public final void showStimmzettel() {
    
    new StimmzettelView(this.getCodeInputField().getText());
  }
  
} //class end
