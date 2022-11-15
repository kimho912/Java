package gui;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.Box;

import java.awt.BorderLayout;
import java.awt.Font;

import java.io.File;
import java.io.IOException;

import product.Item;
import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Container;
import product.Serving;
import product.Order;

import emporium.Emporium;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class MainWin extends JFrame{

    private final String NAME = "MICE";
    private final String EXTENSION = "mice";
    private final String VERSION = "0.3";
    private final String FILE_VERSION = "1.0";
    private final String MAGIC_COOKIE = "Mïċẹ🍦🍨";

    private Emporium emporium;
    private File filename;
    private JLabel display;

    
    public MainWin () {
        super("Mavs Ice Cream Emporium");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 800);

        // M E N U //////////////////////////////////////////////

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file           = new JMenu("File");
        JMenuItem open           = new JMenuItem("Open");
        JMenuItem save           = new JMenuItem("Save");
        JMenuItem saveAs         = new JMenuItem("Save As");
        JMenuItem quit           = new JMenuItem("Quit");
        JMenu     view           = new JMenu("View");
        JMenuItem displayIcf     = new JMenuItem("Ice Cream Flavor");
        JMenuItem displayMif     = new JMenuItem("Mix In Flavor");
        JMenuItem displayOrder   = new JMenuItem("Order");
        JMenuItem displayContainer= new JMenuItem("Container");
          
        JMenu     create         = new JMenu("Create");
        JMenuItem createIcf      = new JMenuItem("Ice Cream Flavor");
        JMenuItem createMif      = new JMenuItem("Mix In Flavor");
        JMenuItem createOrder    = new JMenuItem("Order");
        JMenuItem createContainer= new JMenuItem("Container");

        JMenu     help           = new JMenu("Help");
        JMenuItem about          = new JMenuItem("About"); 

        open       .addActionListener(event -> onOpenClick());
        save       .addActionListener(event -> onSaveClick());
        saveAs     .addActionListener(event -> onSaveAsClick());
        quit        .addActionListener(event -> onQuitClick());

        displayIcf  .addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));
        displayMif  .addActionListener(event -> view(Screen.MIX_IN_FLAVORS));
        displayContainer.addActionListener(event -> view(Screen.CONTAINERS));
        displayOrder.addActionListener(event -> view(Screen.ORDERS));

        createIcf   .addActionListener(event -> onCreateIceCreamFlavorClick());
        createMif   .addActionListener(event -> onCreateMixInFlavorClick());
        createOrder .addActionListener(event -> onCreateOrderClick());
        createContainer.addActionListener(event -> onCreateContainerClick());

        about       .addActionListener(event -> onAboutClick());

        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(quit);
        view.add(displayContainer);
        view.add(displayIcf);
        view.add(displayMif);
        view.add(displayOrder);
        create.add(createContainer);
        create.add(createIcf);
        create.add(createMif);
        create.add(createOrder);
        help.add(about);
        
        menubar.add(file);
        menubar.add(view);
        menubar.add(create);
        menubar.add(help);

        setJMenuBar(menubar);

        // T O O L B A R
        JToolBar toolbar = new JToolBar("Mice Controls");

        JButton saveButton = new JButton(new ImageIcon("gui/save.png"));
            saveButton.setActionCommand("New File");
            saveButton.setToolTipText("Save to write all data to the current filename");
            saveButton.addActionListener(event -> onSaveClick());
            toolbar.add(saveButton);

        JButton saveAsButton = new JButton(new ImageIcon("gui/saveAs.png"));
            saveAsButton.setActionCommand("New File as (type)");
            saveAsButton.setToolTipText("Save As to change the current filename via a FileChooser dialog and then chain to Save");
            saveAsButton.addActionListener(event -> onSaveAsClick());
            toolbar.add(saveAsButton);

        JButton openButton = new JButton(new ImageIcon("gui/open.png"));
            openButton.setActionCommand("Open File");
            openButton.setToolTipText("Open to select a filename via a FileChooser dialog and then create a new Emporium from it, changing the current filename if successful");
            openButton.addActionListener(event -> onOpenClick());
            toolbar.add(openButton);

        toolbar.add(Box.createHorizontalStrut(25));

        JButton createContainerB = new JButton(new ImageIcon("gui/createContainer.png"));
            createContainerB.setActionCommand("New Container");
            createContainerB.setToolTipText("create a new Container");
            toolbar     .add(createContainerB);
            createContainerB.addActionListener(event -> onCreateContainerClick());
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

        JButton createOrderB = new JButton(new ImageIcon("gui/createOrder.png"));
            createOrderB.setActionCommand("New Order");
            createOrderB.setToolTipText("create a new order");
            toolbar     .add(createOrderB);
            createOrderB.addActionListener(event -> onCreateOrderClick());        
        
        toolbar.add(Box.createHorizontalStrut(25));

        JButton displayContainerB = new JButton(new ImageIcon("gui/viewContainer.png"));
            displayContainerB.setActionCommand("view Containers");
            displayContainerB.setToolTipText("View all Containers in the main window");
            toolbar      .add(displayContainerB);
            displayContainerB.addActionListener(event -> view(Screen.CONTAINERS));
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

        JButton displayOrderB = new JButton(new ImageIcon("gui/viewOrder.png"));
            displayOrderB.setActionCommand("view Orders");
            displayOrderB.setToolTipText("View all Orders in the main window");
            toolbar      .add(displayOrderB);
            displayOrderB.addActionListener(event -> view(Screen.ORDERS));

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
        filename = new File("untitled." + EXTENSION);
        view(Screen.ICE_CREAM_FLAVORS);

    }
    //Exit
    protected void onQuitClick() {
        System.exit(0);
    }

    protected void onOpenClick() {
        final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
        FileFilter miceFiles = new FileNameExtensionFilter("Mice files", EXTENSION);
        fc.addChoosableFileFilter(miceFiles);         // Add "Nim file" filter
        fc.setFileFilter(miceFiles);                  // Show Nim files only by default
        
        int result = fc.showOpenDialog(this);        // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();        // Obtain the selected File object
            
            try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
                emporium = new Emporium(in); // Open a new game
                view(Screen.CONTAINERS); // Update the user interface
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
        FileFilter miceFiles = new FileNameExtensionFilter("Mice files", EXTENSION);
        fc.addChoosableFileFilter(miceFiles);
        fc.setFileFilter(miceFiles);

        int result = fc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile();
            if(!filename.getAbsolutePath().endsWith("." + EXTENSION))
                filename = new File(filename.getAbsolutePath() + "." + EXTENSION);
            onSaveClick();
        }
    }
    //create Container
    protected void onCreateContainerClick() {
        try {
            emporium.addContainer(new Container(
                JOptionPane.showInputDialog(this, "Name?", 
                    "Create Container", JOptionPane.QUESTION_MESSAGE),
                JOptionPane.showInputDialog(this, "Description?", 
                    "Create Container", JOptionPane.QUESTION_MESSAGE),
                Integer.parseInt(JOptionPane.showInputDialog(this, "Max Scoops?", 
                    "Create Container", JOptionPane.QUESTION_MESSAGE))
            ));
            view(Screen.CONTAINERS);       
        } catch(Exception e) {
            System.err.println("onCreateContainerClick exception: " + e);
        }
    }
    //create Serving
    protected Serving onCreateServing() {
        Container con = (Container) JOptionPane.showInputDialog(
            this,
            "Choose your container to create a serving",
            "Create serving",
            JOptionPane.QUESTION_MESSAGE,
            null,
            emporium.containers(),
            null);
        if (con != null) {
            Serving serving = new Serving(con);
            String prompt = "<html>" + serving + "<br/>Add a scoop?</html>";
            while(true) {
                Scoop scoop = onCreateScoop();
                if(scoop == null) break;
                serving.addScoop(scoop);
                prompt = "<html>" + serving + "<br/>Add another scoop?</html>";
            }
            if(emporium.mixInFlavors().length > 0) {
                prompt = "<html>" + serving + "<br/>Add a Topping?</html>";
                while(true) {
                    MixInFlavor mif = (MixInFlavor) JOptionPane.showInputDialog(this, prompt, 
                        "Add Topping", JOptionPane.QUESTION_MESSAGE, null, 
                        emporium.mixInFlavors(), null);
                    if(mif == null) break;
                    MixInAmount mia = (MixInAmount) JOptionPane.showInputDialog(this, prompt, 
                        "Add Topping", JOptionPane.QUESTION_MESSAGE, null, 
                        MixInAmount.values(), MixInAmount.Normal);
                    serving.addTopping(new MixIn(mif, mia));
                    prompt = "<html>" + serving + "<br/>Add another Topping?</html>";
                }
            }
            return serving;
        }
        else {return null;}
    }
    //create Order
    protected void onCreateOrderClick() {
        try {
            Order order = new Order();
            while(true) {
                Serving ser = onCreateServing();
                if(ser == null) break;
                order.addServing(ser);
            }
            emporium.addOrder(order);
            view(Screen.ORDERS);  

        } catch(Exception e) {
            System.err.println("onCreateOrderClick exception: " + e);
        }
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
            view(Screen.ICE_CREAM_FLAVORS);         
        } catch(Exception e) {
            System.err.println("onCreateIceCreamFlavorClick exception: " + e);
        }
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
            view(Screen.MIX_IN_FLAVORS); 
        } catch(Exception e) {
            System.err.println("onCreateMixInFlavorClick exception: " + e);
        }
    }
    //create Container
    protected Scoop onCreateScoop() {
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
            return scoop;
        }
        else {return null;}
    }
    protected void onAboutClick() {
        JDialog about = new JDialog(this, "Mav’s Ice Cream Emporium");
        about.setLayout(new BoxLayout(about.getContentPane(), BoxLayout.PAGE_AXIS));
        
        // BufferedImage myPicture = ImageIO.read(new File("gui/about.png"));
        Canvas logo = new Canvas("gui/about.png");
        logo.setAlignmentX(Canvas.LEFT_ALIGNMENT);
        about.add(logo);

        JPanel text = new JPanel();
        text.setLayout(new BoxLayout(text, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("<html>"
          + "<br/><p><font size=+4>MICE</font></p>"
          + "</html>", JLabel.CENTER);
        text.add(title);

        JLabel subTitle = new JLabel("<html>"
          + "<br/><p><font size=+1>Mavs Ice Cream Emporium</font></p>"
          + "</html>", JLabel.CENTER);
        text.add(subTitle);

        JLabel artists = new JLabel("<html>"
          + "<br/><p>Version 0.3</p>"
          + "<p>Copyright 2022 by Hyunho Kim</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<p>Logo by Hyunho Kim</p>"
          + "<br/></html>", JLabel.CENTER);
        text.add(artists);
        about.add(text);
        
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
             case CONTAINERS: 
                 title = "Containers";
                 for(var t : emporium.containers()) s.append(t.toString() + "<br/>");
                 break;
             case ICE_CREAM_FLAVORS: 
                 title = "Ice Cream Flavors";
                 for(var t : emporium.iceCreamFlavors()) s.append(t.toString() + "<br/>");
                 break;
             case MIX_IN_FLAVORS: 
                 title = "Mix In Flavors";
                 for(var t : emporium.mixInFlavors()) s.append(t.toString() + "<br/>");
                 break;
             case ORDERS: 
                 title = "Orders";
                 for(var t : emporium.orders()) s.append(t.toString() + "<br/>");
                 break;
             default:
                 display.setText("PANIC: Invalid Displays type: " + display);
         }
         display.setText("<html><font size=+4>" + title + 
                         "<br/></font><font size=+2>" + s.toString() + 
                         "</font></html>");
    }
}
