package wahllokal.presentation.view;


import wahllokal.business.MainModel;
import wahllokal.presentation.controller.SearchCheckInputController;
import wahllokal.presentation.controller.SearchClosingController;
import wahllokal.presentation.controller.SearchController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * GUI-Hauptklasse fuer den Wahlhelfer.
 * Singelton-Klasse.
 * @author Johann
 * <p>Zuletzt bearbeitet: 08.06.2011</p>
 */
public final class SearchView {


  /** Standard - Hintergrundfarbe der GUI. */
  public static final Color DEFAULTBACKGROUNDCOLOR = Color.WHITE;

  /** Definiert die Standard Rahmenfarbe. */
  public static final Color DEFAULTBORDERCOLOR = Color.DARK_GRAY;

  /** Definiert die Standart Rahmendicke. */
  private final int defaultBorderThickness = 5;

  /** Das Textfeld fuer die Suche eines Waehlers. */
  private JTextField inputTextField;


  /** Liefert das Eingabetextfeld.
   * Wird von den Controllern gebraucht, um u.a DocumentListener
   * anzuhaengen.
   * @return the inputTextField
	 */
	public JTextField getInputTextField() {

		return inputTextField;
	}

	/** Der Button zum suchen,
	 *  nachdem man die PersonenNr eingetragen hat. */
  private JButton checkButton;


	/**
	 * Liefert den Pruefe-Button.
	 * Wird von den Controllern gebraucht, um u.a. ActionListener
	 * anzuhaengen
	 * @return the checkButton
	 */
	public JButton getCheckButton() {

		return checkButton;
	}


	/** Button, wo der Nutzer draufklicken kann um Hilfe zu erhalten. */
  private JButton helpButton;

	/**Liefert den Hilfe-Button.
	 * Wird von den Controllern gebraucht, um u.a. ActionListener
	 * anzuhaengen.
	 * @return the helpButton
	 */
	public JButton getHelpButton() {

		return helpButton;
	}

	/** Button, zum Schlieszen des Wahllokals. */
  private JButton closeButton;

  /**Liefert den Schlieszen-Button.
	 * Wird von den Controllern gebraucht, um u.a. ActionListener
	 * anzuhaengen
	 * @return the closeButton
	 */
	public JButton getCloseButton() {

		return closeButton;
	}
	
	/** Button, wo der Nutzer draufklicken kann, um Informationen zu erhalten. */
  private JButton infoButton;

  /**Liefert den Information-Button.
   * Wird von den Controllern gebraucht, um u.a. ActionListener
   * anzuhaengen.
   * @return the infoButton
   */
  public JButton getInfoButton() {

    return infoButton;
  }

	/** Label mit Kurzinformation über den Eingabetextfeld. */
  private JLabel infoLabel;

  
  /** Das Frame der GUI. */
  private JFrame mainFrame;

	/**
	 * Wird von den Controllern gebraucht.
	 * @return the mainFrame
	 */
	public JFrame getMainFrame() {

		return mainFrame;
	}

	/** Referenz auf einzige Instanz. */
	private static SearchView soleInstance = null;

	/** Liefere Referenz auf einzige Instanz der Klasse.
	* @return Referenz auf Instanz der Klasse
	*/
	public static synchronized SearchView getInstance() {

	  if (soleInstance == null) {

	    soleInstance = new SearchView();

	  }
	  return soleInstance;
	}

	/**
   * Einziger Konstruktor dieser Klasse, der nur
   * durch getInstance aufgerufen wird.
   */
  private SearchView() {

    this.mainFrame = new JFrame("Wahllokal-Software");

    this.mainFrame.add(this.createMainPanel(), BorderLayout.CENTER);
    this.mainFrame.add(this.createButtonPanel(), BorderLayout.NORTH);

    //das Beenden der Software, uebernimmt der SearchClosingController
    this.mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    this.mainFrame.setLocation(0, 0);
    this.mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

    // Controller hier hinzufuegen
    new SearchClosingController(this);
    new SearchController(this);
    new SearchCheckInputController(this);

    this.mainFrame.setVisible(true);  // hier Fenster anzeigen

    SettingsView.getInstance(this);      //Fenster fuer Einstellungen anzeigen
    
  } // Konstruktor ende


  /**
   * Erstellt das Mainpanel, welches alle Komponenten der GUI traegt.
   * @return panel Das MainPanel der GUI.
   */
  private JPanel createMainPanel() {

    JPanel panel = new JPanel();
    panel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
    panel.setBorder(BorderFactory.
    		createLineBorder(SearchView.DEFAULTBORDERCOLOR,
    		                 this.defaultBorderThickness));

    GridBagLayout gbl = new GridBagLayout();
    panel.setLayout(gbl);

    GridBagConstraints gbc;

    final int inputTextFieldLength = 40;
    final int inputTextFieldWidth = 10;
    final int insetsDefault = 2;
    final int checkButtonLength = 50;
    final int checkButtonWidth = 5;
    final int gridwidthMaxSize = 3;  //zeigt die maximale Spaltenanzahl an

    this.infoLabel = new JLabel();
    this.infoLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = gridwidthMaxSize;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(this.infoLabel, gbc);
    panel.add(this.infoLabel);

    this.inputTextField = new JTextField(inputTextFieldLength);
    this.inputTextField.setToolTipText("PersonalNr eingeben");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = gridwidthMaxSize;
    gbc.ipadx = 0;
    gbc.ipady = inputTextFieldWidth;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbl.setConstraints(this.inputTextField, gbc);
    panel.add(this.inputTextField);

    this.checkButton = new JButton("prüfen");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 2;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = checkButtonLength;
    gbc.ipady = checkButtonWidth;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,  //bottom
                            insetsDefault); //right
    gbl.setConstraints(this.checkButton, gbc);
    panel.add(this.checkButton);

    this.setDefaultInfoLabel(); //anfangs Defaulteinstellungen
    return panel;
  }

  /**
   * Erzeugt ein Panel mit den Buttons unter dem Systemmenu.
   * @return buttonPanel Das Panel.
   */
  private JPanel createButtonPanel() {

    JPanel buttonPanel = new JPanel();

    //Die Hintergrundfarbe von buttonPanel ist gleich der Farbe des Rahmens
    buttonPanel.setBackground(SearchView.DEFAULTBORDERCOLOR);
    buttonPanel.setBorder(BorderFactory.
    		createEmptyBorder(this.defaultBorderThickness,   //top
    		                  this.defaultBorderThickness,   //left
    		                  this.defaultBorderThickness,   //bottom
    		                  this.defaultBorderThickness)); //right

    final int rows = 0;
    final int cols = 7;
    final int hgap = 2;
    final int vgap = 2;
    buttonPanel.setLayout(new GridLayout(rows, cols, hgap, vgap));

    this.closeButton = new JButton("Wahllokal schließen");
    this.infoButton = new JButton("Information");
    this.helpButton = new JButton("Hilfe");

    buttonPanel.add(this.closeButton);
    buttonPanel.add(this.infoButton);
    buttonPanel.add(this.helpButton);

    return buttonPanel;
  }

  /**
   * Setzt die Default-Einstelltungen vom infoLabel.
   */
  public void setDefaultInfoLabel() {
    this.infoLabel.setText("Prüfe Wahlberechtigung:");
    this.infoLabel.setForeground(Color.BLACK);
    this.checkButton.setEnabled(true);
  }

  /**
   * Wird zur Ist-Berechtigt-Anzeige verwendet.
   * Wird von den Controllern aufgerufen.
   * Ueberschreibt das infoLabel mit der Information, die im
   * Parameter text uebergeben werden.
   * @param text Die darzustellende Information.
   * @param berechtigt boolscher wert, um die berechtigt oder
   * nicht berechtigt Auswahl zu treffen.
   */
  public void setBerechtigtInfoLabel(final String text,
                                     final boolean berechtigt) {
    this.infoLabel.setText(text);
    this.checkButton.setEnabled(true);
    if (berechtigt) {

      this.infoLabel.setForeground(Color.GREEN);

    } else {

      this.infoLabel.setForeground(Color.RED);

    }
  }

  /**
   * Wird zur Fehler-Anzeige verwendet.
   * Wird von den Controllern verwendet.
   * Ueberschreibt das infoLabel mit der Information, die im
   * Parameter text uebergeben wird.
   * @param text Die darzustellende Information.
   */
  public void setErrorInfoLabel(final String text) {
    this.infoLabel.setText(text);
    this.infoLabel.setForeground(Color.RED);
    this.checkButton.setEnabled(false);
  }

  /**
   * Diese Methode zeigt die Personendaten an.
   * Dazu wird eine extra View mit den, im Parameter
   * uebergebenen Daten aufgerufen.
   * @param daten enthaelt die Personendaten, die angezeigt
   * werden sollen als String[].
   */
  public void showPerson(final String []daten) {
    
    PersonView.getInstance(this, daten);
    
  }
  
  /**
   * Zeigt das Hilfefenster an.
   * Wird vom Controller aufgerufen.
   */
  public void showHelp() {
    
    new HelpView(this);
  }
  
  /**
   * Zeigt das Informationsfenster an.
   * Wird vom Controller aufgerufen.
   */
  public void showInfo() {
    
    new InfoView(this);
  }
  
  /**
   * Holt die Waehlerdaten vom Model.
   * Wird vom Controller gebraucht.
   * @return die Daten als Stringarray
   */
  public String[] getWaehlerDaten() {
    
    return MainModel.getInstance().getWaehlerDaten(
        getInputTextField().getText());
    
  }
  
  /**
   * Veranlasst die Endergebnissauswertung.
   * Wird vom Controller gebraucht.
   */
  public void closeWahllokal() {
    
    MainModel.getInstance().lokalSchluss();
  }
  
} // class end
