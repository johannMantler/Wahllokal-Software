package wahllokal.presentation.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import wahllokal.presentation.controller.HelpController;

/**
 * GUI, die zur Darstellung des Benutzerhandbuchs dient.
 * Sobald dieses geschrieben ist, kannn es hier eingebunden
 * werden.
 * @author Johann
 *<p>zuletzt bearbeitet: 24.06.2011</p>
 */
public class HelpView {

  /** Dieser JFrame. */
  private JFrame mainFrame;

  /**
   * Gibt die Referenz auf den JFrame zurueck.
   * @return the mainFrame
   */
  public final JFrame getMainFrame() {
    return mainFrame;
  }
  
  /** Zeigt den Hilfetext an. */
  private JLabel helpTextLabel;
  
  /** Definiert die Standard - Rahmendicke. */
  private final int defaultBorderThickness = 2;
  
  /** Der String der die Ueberschrift darstellt. */
  private final String headLine = "Hilfe";
  
  /** Definiert die Schriftgroesze der Ueberschrift. */
  private final int headLineFontSize = 17;
  
  /** Definiert einen Button, mit dem der Nutzer den Dialog abbrechen kann.*/
  private JButton abort;
  
  /**
   * Liefert den Abbrechen-Button als Referenz.
  * @return the abort
  */
  public final JButton getAbort() {
     return abort;
   }
  
  /**
   * Einziger Konstruktor dieser Klasse.
   * @param view Das SearchView, um das Hilfefenster mittig
   * auf das SearchViewfenster zu positionieren.
   */
  public HelpView(SearchView view) {
    
    this.mainFrame = new JFrame("Hilfe");
    this.mainFrame.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
    this.mainFrame.add(this.createMainPanel(), BorderLayout.CENTER);
    
    final int xSize = 350;
    final int ySize = 360;
    this.mainFrame.setSize(xSize, ySize);
    this.mainFrame.setLocationRelativeTo(view.getMainFrame());
    
    new HelpController(this);
    
    this.mainFrame.setVisible(true);
  }
  
  /**
   * Erzeugt das Hauptpanel, welches direkt auf dem mainFrame
   * dieser GUI liegt und gleichzeigt selbst alle weiteren
   * Komponenten aufnimmt.
   * @return Das Panel.
   */
  private JPanel createMainPanel() {
    
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
    mainPanel.setBorder(BorderFactory.createLineBorder(
        SearchView.DEFAULTBORDERCOLOR,
        this.defaultBorderThickness));
    
    GridBagLayout gbl = new GridBagLayout();
    mainPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 2;
    final int insetsBigger = 10;
    
    JPanel headLinePanel = this.createHeadLinePanel();
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
    
    gbl.setConstraints(headLinePanel, gbc);
    mainPanel.add(headLinePanel);
    
    JPanel helpTextPanel = this.createHelpTextPanel();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsBigger,   //bottom
                            insetsDefault); //right
    
    gbl.setConstraints(helpTextPanel, gbc);
    mainPanel.add(helpTextPanel);
    
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
   * Erzeigt ein Panel das lediglich zur Anzeige der Ueberschrift
   * (Headline) dient. Das HeadLineLabel wird extra nochmals
   * in ein Panel gelegt, damit man spaeter bei Bedarf neu
   * formatieren kann.
   * @return Das Panel mit der Ueberschrift (headLine).
   */
  private JPanel createHeadLinePanel() {
    
    JPanel headLinePanel = new JPanel(new FlowLayout());
    headLinePanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
   
    JLabel headLineLabel = new JLabel(this.headLine);
    headLineLabel.setFont(new Font("Arial", Font.BOLD, 
                                this.headLineFontSize));
    headLineLabel.setForeground(SearchView.DEFAULTBORDERCOLOR);
    headLineLabel.setHorizontalAlignment(JLabel.CENTER);
    
    headLinePanel.add(headLineLabel);
    
    return headLinePanel;
  }
  
  /**
   * Erzeugt ein Panel, welches nur zur Anzeige des Hilfetextes
   * dienlich ist.
   * @return Das Panel.
   */
  private JPanel createHelpTextPanel() {
    
    JPanel helpTextPanel = new JPanel();
    helpTextPanel.setBackground(SearchView.DEFAULTBACKGROUNDCOLOR);
    helpTextPanel.setBorder(BorderFactory.createLineBorder(
        SearchView.DEFAULTBORDERCOLOR,
        this.defaultBorderThickness));
    
    GridBagLayout gbl = new GridBagLayout();
    helpTextPanel.setLayout(gbl);
    GridBagConstraints gbc;

    final int insetsDefault = 6;
    String helpText = 
      "<html>" 
      +
        "<body>" 
      +
          "<h2>"
      +
            "Hallo, <br/>"
      +
          "</h2>"
      +
          "<h5>" 
      +
            "Hier erhalten Sie Hilfe" 
      +
            "<br/>" 
      +
            "(oder auch nicht)" 
      +
          "</h5>" 
      +
        "</body>" 
      +
      "</html>";
    this.helpTextLabel = new JLabel(helpText);
    this.helpTextLabel.setForeground(SearchView.DEFAULTBORDERCOLOR);
    this.helpTextLabel.setHorizontalAlignment(JLabel.CENTER);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(insetsDefault,  //top
                            insetsDefault,  //left
                            insetsDefault,   //bottom
                            insetsDefault); //right
    gbl.setConstraints(this.helpTextLabel, gbc);
    
    final JScrollBar sb = new JScrollBar();
    //this.helpTextLabel.add(sb);
    helpTextPanel.add(this.helpTextLabel);
    helpTextPanel.add(sb);
    
    return helpTextPanel;
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

    final int insetsDefault = 2;
    this.abort = new JButton("OK");
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
    gbl.setConstraints(abort, gbc);
    buttonPanel.add(abort);

    return buttonPanel;
  }
  
  
} //class end
