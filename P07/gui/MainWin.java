package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JToolBar;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;


import java.awt.Font;
import java.awt.Color;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


import product.IceCreamFlavor;
import product.MixInAmount;
import product.MixInFlavor;
import product.MixIn;
import product.Scoop;

import emporium.Emporium;


public class MainWin extends JFrame{

    private Emporium emporium;

    private JMenuItem createScoop;
    private JLabel display;

    private File filename;

    public MainWin () {
        super("Mavs Ice Cream Emporium");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        filename = new File("untitled.mice");

        // M E N U //////////////////////////////////////////////

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem quit       = new JMenuItem("Quit");

        JMenu     view       = new JMenu("View");
        JMenuItem displayIcf      = new JMenuItem("Ice Cream Flavor");
        JMenuItem displayMif      = new JMenuItem("Mix In Flavor");
        JMenuItem displayScoop      = new JMenuItem("Scoop");
          
        JMenu     create       = new JMenu("Create");
        JMenuItem createIcf      = new JMenuItem("Ice Cream Flavor");
        JMenuItem createMif      = new JMenuItem("Mix In Flavor");
                  createScoop      = new JMenuItem("Scoop");

        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About"); 

        quit        .addActionListener(event -> onQuitClick());

        displayIcf  .addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));
        displayMif  .addActionListener(event -> view(Screen.MIX_IN_FLAVORS));
        displayScoop.addActionListener(event -> view(Screen.SCOOPS));

        createIcf   .addActionListener(event -> onCreateIceCreamFlavorClick());
        createMif   .addActionListener(event -> onCreateMixInFlavorClick());
        createScoop .addActionListener(event -> onCreateScoopClick());
        createScoop .setEnabled(false);
        about       .addActionListener(event -> onAboutClick());

        file.add(quit);
        view.add(displayIcf);
        view.add(displayMif);
        view.add(displayScoop);
        create.add(createIcf);
        create.add(createMif);
        create.add(createScoop);
        help.add(about);
        
        menubar.add(file);
        menubar.add(view);
        menubar.add(create);
        menubar.add(help);

        setJMenuBar(menubar);

        // T O O L B A R
        JToolBar toolbar = new JToolBar("Icecream Controls");

        JButton saveB = new JButton(new ImageIcon("gui/save.png"));
            saveB.setActionCommand("New File");
            saveB.setToolTipText("Save to write all data to the current filename");
            toolbar.add(saveB);
            saveB.addActionListener(event -> onSaveClick());

        JButton saveAsB = new JButton(new ImageIcon("gui/saveAs.png"));
            saveAsB.setActionCommand("New File as (type)");
            saveAsB.setToolTipText("Save As to change the current filename via a FileChooser dialog and then chain to Save");
            toolbar.add(saveAsB);
            saveAsB.addActionListener(event -> onSaveAsClick());

        JButton openB = new JButton(new ImageIcon("gui/open.png"));
            openB.setActionCommand("Open File");
            openB.setToolTipText("Open to select a filename via a FileChooser dialog and then create a new Emporium from it, changing the current filename if successful");
            toolbar.add(openB);
            openB.addActionListener(event -> onOpenClick());

        toolbar.add(Box.createHorizontalStrut(15));

        JButton createIcfB = new JButton(new ImageIcon("gui/createIcf.png"));
            createIcfB.setActionCommand("New Ice Cream Flavor");
            createIcfB.setToolTipText("create a new ice cream flavor");
            toolbar   .add(createIcfB);
            createIcfB.addActionListener(event -> onCreateIceCreamFlavorClick());

        JButton createMifB = new JButton(new ImageIcon("gui/createMif.png"));
            createMifB.setActionCommand("New Mix In Flavor");
            createMifB.setToolTipText("create a new mix in flavor");
            toolbar   .add(createMifB);
            createMifB.addActionListener(event -> onCreateMixInFlavorClick());

        JButton createScoopB = new JButton(new ImageIcon("gui/createScoop.png"));
            createScoopB.setActionCommand("New Scoop");
            createScoopB.setToolTipText("create a new scoop");
            toolbar     .add(createScoopB);
            createScoopB.addActionListener(event -> onCreateScoopClick());

        toolbar.add(Box.createHorizontalStrut(15));

        JButton displayIcfB = new JButton(new ImageIcon("gui/viewIcf.png"));
            displayIcfB.setActionCommand("View Ice Cream Flavors");
            displayIcfB.setToolTipText("View all ice cream flavors in the main window");
            toolbar    .add(displayIcfB);
            displayIcfB.addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));

        JButton displayMifB = new JButton(new ImageIcon("gui/viewMif.png"));
            displayMifB.setActionCommand("View Mix In Flavors");
            displayMifB.setToolTipText("View all mix in flavors in the main window");
            toolbar    .add(displayMifB);
            displayMifB.addActionListener(event -> view(Screen.MIX_IN_FLAVORS));

        JButton displayScoopB = new JButton(new ImageIcon("gui/viewScoop.png"));
            displayScoopB.setActionCommand("view Scoops");
            displayScoopB.setToolTipText("View all scoops in the main window");
            toolbar      .add(displayScoopB);
            displayScoopB.addActionListener(event -> view(Screen.SCOOPS));

        toolbar.add(Box.createHorizontalGlue());

        getContentPane().add(toolbar, BorderLayout.PAGE_START);

        JButton quitB = new JButton("X"); 
            quitB.setActionCommand("Quit");
            quitB.setToolTipText("Exit Program");
            toolbar.add(quitB);
            quitB.addActionListener(event -> onQuitClick());
            toolbar.addSeparator();

        // M A I N  D I S P L A Y
        display = new JLabel();
        display.setFont(new Font("Courier New", Font.BOLD, 18));
        display.setVerticalAlignment(JLabel.TOP);
        add(display, BorderLayout.CENTER);

        // Make everything in the JFrame visible
        setVisible(true);

        emporium = new Emporium();
    }
    protected void onOpenClick() {
        final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
        FileFilter miceFiles = new FileNameExtensionFilter("Mice files", "mice");
        fc.addChoosableFileFilter(miceFiles);         // Add "Nim file" filter
        fc.setFileFilter(miceFiles);                  // Show Nim files only by default
        
        int result = fc.showOpenDialog(this);        // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();        // Obtain the selected File object
            
            try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
                emporium = new Emporium(in); // Open a new game

                view(Screen.ICE_CREAM_FLAVORS);
                view(Screen.MIX_IN_FLAVORS);
                view(Screen.SCOOPS); // Update the user interface
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Unable to open " + filename + '\n' + e, 
                    "Failed", JOptionPane.ERROR_MESSAGE); 
             }
        }
    }
    protected void onSaveClick() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            emporium.save(out);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open " + filename + '\n' + e,
                "Failed", JOptionPane.ERROR_MESSAGE); 
        }
    }
    protected void onSaveAsClick() {
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter miceFiles = new FileNameExtensionFilter("Mice files", "mice");
        fc.addChoosableFileFilter(miceFiles);
        fc.setFileFilter(miceFiles);

        int result = fc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile();
            if(!filename.getAbsolutePath().endsWith(".mice"))
                filename = new File(filename.getAbsolutePath() + ".mice");
            onSaveClick();
        }
    }
    //Exit
    protected void onQuitClick() {
        System.exit(0);
    }
    //create Icecream
    protected void onCreateIceCreamFlavorClick() {
        try {
            emporium.addIceCreamFlavor(new IceCreamFlavor(
                JOptionPane.showInputDialog(this, "Name?", 
                    "Create Ice Cream Flavor", JOptionPane.QUESTION_MESSAGE),
                JOptionPane.showInputDialog(this, "Description?", 
                    "Create Ice Cream Flavor", JOptionPane.QUESTION_MESSAGE),
                Integer.parseInt(JOptionPane.showInputDialog(this, "Price?", 
                    "Create Ice Cream Flavor", JOptionPane.QUESTION_MESSAGE)),
                Integer.parseInt(JOptionPane.showInputDialog(this, "Cost?", 
                    "Create Ice Cream Flavor", JOptionPane.QUESTION_MESSAGE))
            ));
        } catch(Exception e) {
        }
        createScoop.setEnabled(true);
        view(Screen.ICE_CREAM_FLAVORS);
    }
    protected void onCreateMixInFlavorClick() {
        try {
            emporium.addMixInFlavor(new MixInFlavor(
                JOptionPane.showInputDialog(this, "Name?", 
                    "Create Mix In Flavor", JOptionPane.QUESTION_MESSAGE),
                JOptionPane.showInputDialog(this, "Description?", 
                    "Create Mix In Flavor", JOptionPane.QUESTION_MESSAGE),
                Integer.parseInt(JOptionPane.showInputDialog(this, "Price?", 
                    "Create Mix In Flavor", JOptionPane.QUESTION_MESSAGE)),
                Integer.parseInt(JOptionPane.showInputDialog(this, "Cost?", 
                    "Create Mix In Flavor", JOptionPane.QUESTION_MESSAGE))
            ));   
        } catch(Exception e) {
        }
        view(Screen.MIX_IN_FLAVORS);  
    }

    protected void onCreateScoopClick() {
        IceCreamFlavor icf = (IceCreamFlavor) JOptionPane.showInputDialog(
            this,
            "Choose your ice cream flavor to create a scoop",
            "Create Scoop",
            JOptionPane.QUESTION_MESSAGE,
            null,
            emporium.iceCreamFlavors(),
            null);
        if (icf != null) {
            Scoop scoop = new Scoop(icf);
            if(emporium.mixInFlavors().length > 0) {
                String prompt = "<html>" + scoop + "<br/>Add a mix in?</html>";
                while(true) {
                    MixInFlavor mif = (MixInFlavor) JOptionPane.showInputDialog(this, prompt, 
                        "Add Mix In", JOptionPane.QUESTION_MESSAGE, null, 
                        emporium.mixInFlavors(), null);
                    if(mif == null) break;
                    MixInAmount mia = (MixInAmount) JOptionPane.showInputDialog(this, prompt, 
                        "Add Mix In", JOptionPane.QUESTION_MESSAGE, null, 
                        MixInAmount.values(), MixInAmount.Normal);
                    scoop.addMixIn(new MixIn(mif, mia));
                    prompt = "<html>" + scoop + "<br/>Add another mix in?</html>";
                }
            }
            emporium.addScoop(scoop);
            view(Screen.SCOOPS);  
        }
    }
    protected void onAboutClick() {
        JDialog about = new JDialog(this, "Mav’s Ice Cream Emporium");
        about.setLayout(new BoxLayout(about.getContentPane(), BoxLayout.PAGE_AXIS));
        
        // BufferedImage myPicture = ImageIO.read(new File("gui/about.png"));
        about.add(new Canvas());
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>MICE</font></p>"
          + "<p><font size=+2>Mav’s Ice Cream Emporium</font></p>"
          + "</html>");
        about.add(title);

        JLabel artists = new JLabel("<html>"
          + "<br/><p>Version 0.2</p>"
          + "<p>Copyright 2022 by Hyunho Kim</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<p>Logo by Hyunho Kim</p>"
          + "<br/></html>");
        about.add(artists);

        JPanel panel = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        panel.add(ok);
        about.add(panel);
        
        about.add(Box.createVerticalStrut(10));
        
        about.pack();
        about.setVisible(true);
    }
     
    private void view (Screen screen) {
        String title = "";
         StringBuilder s = new StringBuilder();
         switch(screen) {
             case ICE_CREAM_FLAVORS: 
                 title = "Ice Cream Flavors";
                 for(var t : emporium.iceCreamFlavors()) s.append(t.toString() + "<br/>");
                 break;
             case MIX_IN_FLAVORS: 
                 title = "Mix In Flavors";
                 for(var t : emporium.mixInFlavors()) s.append(t.toString() + "<br/>");
                 break;
             case SCOOPS: 
                 title = "Scoops";
                 for(var t : emporium.scoops()) s.append(t.toString() + "<br/>");
                 break;
             default:
                 display.setText("PANIC: Invalid Displays type: " + display);
         }
         display.setText("<html><font size=+4>" + title + 
                         "<br/></font><font size=+2>" + s.toString() + 
                         "</font></html>");
    }
}
