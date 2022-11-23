package gui;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerModel;
import javax.swing.JComboBox;

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
import person.Customer;
import person.Person;

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

    // private final String NAME = "MICE";
    private final String EXTENSION = "mice";
    // private final String VERSION = "0.3";
    // private final String FILE_VERSION = "1.0";
    // private final String MAGIC_COOKIE = "Mïċẹ🍦🍨";

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
        JMenuItem displayCustomer= new JMenuItem("Customer");
          
        JMenu     create         = new JMenu("Create");
        JMenuItem createIcf      = new JMenuItem("Ice Cream Flavor");
        JMenuItem createMif      = new JMenuItem("Mix In Flavor");
        JMenuItem createOrder    = new JMenuItem("Order");
        JMenuItem createContainer= new JMenuItem("Container");
        JMenuItem createCustomer= new JMenuItem("Customer");

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
        displayCustomer.addActionListener(event -> view(Screen.CUSTOMERS));

        createIcf   .addActionListener(event -> onCreateIceCreamFlavorClick());
        createMif   .addActionListener(event -> onCreateMixInFlavorClick());
        createOrder .addActionListener(event -> onCreateOrderClick());
        createContainer.addActionListener(event -> onCreateContainerClick());
        createCustomer.addActionListener(event -> onCreateCustomerClick());

        about       .addActionListener(event -> onAboutClick());

        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(quit);
        view.add(displayCustomer);
        view.add(displayContainer);
        view.add(displayIcf);
        view.add(displayMif);
        view.add(displayOrder);
        create.add(createCustomer);
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
        
        JButton createCustomerB = new JButton(new ImageIcon("gui/createCustomer.png"));
            createCustomerB.setActionCommand("New Customer");
            createCustomerB.setToolTipText("create a new Customer");
            toolbar     .add(createCustomerB);
            createCustomerB.addActionListener(event -> onCreateCustomerClick());
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

        JButton displayCustomerB = new JButton(new ImageIcon("gui/viewCustomer.png"));
            displayCustomerB.setActionCommand("view Customers");
            displayCustomerB.setToolTipText("View all Customers in the main window");
            toolbar      .add(displayCustomerB);
            displayCustomerB.addActionListener(event -> view(Screen.CONTAINERS));
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
    //create Customer
    protected void onCreateCustomerClick() {
        try {
            JLabel name = new JLabel("<html>Name</html>");
            JTextField names = new JTextField(20);
            JLabel phone = new JLabel("<html><br/>Phone number</html>");
            JTextField phones = new JTextField(20);
            
            Object[] objects = { // Array of widgets to display
                name,   names,
                phone,  phones};
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "New Customer", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/createCustomer.png"));
            if(button == JOptionPane.OK_OPTION) {
                emporium.addCustomer(new Customer(
                    names.getText(), phones.getText()));
                view(Screen.CUSTOMERS);         
           }
        } catch(Exception e) {
            System.err.println("onCreateCustomerClick exception: " + e);
        }
    }
    
    //create Container
    protected void onCreateContainerClick() {
        try {
            JLabel name = new JLabel("<html>Name</html>");
            JTextField names = new JTextField(20);
            JLabel desc = new JLabel("<html><br/>Description</html>");
            JTextField descs = new JTextField(20);
            JLabel scoop = new JLabel("<html><br/>Max Scoops</html>");
            SpinnerModel range = new SpinnerNumberModel(1, 0, 15, 1);
            JSpinner scoops = new JSpinner(range);
            
            Object[] objects = { // Array of widgets to display
                name,   names,
                desc,   descs,
                scoop,  scoops};
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "New Container", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/createContainer.png"));
            if(button == JOptionPane.OK_OPTION) {
                emporium.addContainer(new Container(
                    names.getText(), descs.getText(), (Integer) scoops.getValue()));
                view(Screen.CONTAINERS);         
           }
        } catch(Exception e) {
            System.err.println("onCreateContainerClick exception: " + e);
        }
    }
    //create Serving
    protected Serving onCreateServing() {
        Serving serving = null;
        try {
            Container container = (Container) JOptionPane.showInputDialog(this, "Container?", "New Container", JOptionPane.QUESTION_MESSAGE, null, emporium.containers(), null);
            if(container != null) {
                serving = new Serving(container);
                Scoop scoop = null;
                boolean noScoop = true;
                while((scoop = onCreateScoop()) != null && serving.numScoops() <= container.maxScoops()) {
                    serving.addScoop(scoop);
                    noScoop = false;
                    int result = JOptionPane.showConfirmDialog(
                        this, serving, "Add another scoop?", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(result == JOptionPane.CANCEL_OPTION) return null;
                    if(result == JOptionPane.NO_OPTION) break;
                }
                if(noScoop) return null; 
                if(emporium.mixInFlavors().length > 0) {
                    String prompt = "<html>" + serving + "<br/>Add a topping?</html>";
                    MixIn topping = null;
                    while((topping = onCreateMixIn(prompt)) != null) {
                        serving.addTopping(topping);
                        prompt = "<html>" + serving + "<br/>Add another topping?</html>";
                    }
                }
                // if(emporium.favoriteServings().length > 0) {
                //     String prompt = "<html>" + serving + "<br/>Add a topping?</html>";
                //     MixIn topping = null;
                //     while((topping = onCreateMixIn(prompt)) != null) {
                //         serving.addTopping(topping);
                //         prompt = "<html>" + serving + "<br/>Add another topping?</html>";
                //     }
                // }  
            }
        } catch(Exception e) {
            System.err.println("onCreateScoop exception: " + e);
            return null;
        }
        return serving;
    }
    //create Order
    protected void onCreateOrderClick() {
        Order order = null;
        try {
            JLabel customer = new JLabel("<html><br/>Customer</html>");
            JComboBox<Object> customers = new JComboBox<>(emporium.customers());
            Object[] objects = { // Array of widgets to display
                customer, customers};
                
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "New Customer", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/createCustomer.png"));
            if(button == JOptionPane.YES_OPTION) 
                order = new Order((Customer) customers.getSelectedItem());
            Serving serving = null;
            // JLabel favorite = new JLabel("<html><br/>favorite</html>");
            //     JComboBox<Object> favorites = new JComboBox<>(emporium.favoriteServings());
            //     Object[] FavObjects = { // Array of widgets to display
            //         favorite, favorites};
                    
            //     int FavButton = JOptionPane.showConfirmDialog( // Show the dialog
            //         this, FavObjects, "Selecte favorite serving?", JOptionPane.YES_NO_OPTION,
            //             JOptionPane.QUESTION_MESSAGE, null);
            //     if(FavButton == JOptionPane.YES_OPTION) {
            //         serving = new Order((Customer) customers.getSelectedItem());
            //         return;
            //     }
            while((serving = onCreateServing()) != null) {
                if(order == null) return; 
                order.addServing(serving);
                int result = JOptionPane.showConfirmDialog(
                    this, order, "Add Another Serving?", JOptionPane.YES_NO_CANCEL_OPTION);
                if(result == JOptionPane.CANCEL_OPTION) return;
                if(result == JOptionPane.NO_OPTION) return;
            }
            if(order != null) emporium.addOrder(order);
            view(Screen.ORDERS);
        } catch(Exception e) {
            System.err.println("onCreateOrderClick exception: " + e);
        }
    }
    //create Icecream
    protected void onCreateIceCreamFlavorClick() {
        try {
            JLabel name = new JLabel("<html>Name</html>");
            JTextField names = new JTextField(20);
            JLabel desc = new JLabel("<html><br/>Description</html>");
            JTextField descs = new JTextField(20);
            JLabel price = new JLabel("<html><br/>Price</html>");
            SpinnerModel priceRange = new SpinnerNumberModel(1, 0, 15, 1);
            JSpinner prices = new JSpinner(priceRange);
            JLabel cost = new JLabel("<html><br/>Cost</html>");
            SpinnerModel costRange = new SpinnerNumberModel(1, 0, 15, 1);
            JSpinner costs = new JSpinner(costRange);
            
            Object[] objects = { // Array of widgets to display
                name,   names,
                desc,   descs,
                price,  prices,
                cost,   costs};
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "New Ice Cream Flavor", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/createIcf.png"));
            if(button == JOptionPane.OK_OPTION) {
                emporium.addIceCreamFlavor(new IceCreamFlavor(
                    names.getText(), descs.getText(), 
                    (Integer) prices.getValue(), (Integer) costs.getValue()));
                view(Screen.ICE_CREAM_FLAVORS);         
           }
        } catch(Exception e) {
        }
        try {
            view(Screen.ICE_CREAM_FLAVORS);         
        } catch(Exception e) {
            System.err.println("onCreateIceCreamFlavorClick exception: " + e);
        }
    }
    protected void onCreateMixInFlavorClick() {
        try {
            JLabel name = new JLabel("<html>Name</html>");
            JTextField names = new JTextField(20);
            JLabel desc = new JLabel("<html><br/>Description</html>");
            JTextField descs = new JTextField(20);
            JLabel price = new JLabel("<html><br/>Price</html>");
            SpinnerModel priceRange = new SpinnerNumberModel(1, 0, 150, 1);
            JSpinner prices = new JSpinner(priceRange);
            JLabel cost = new JLabel("<html><br/>Cost</html>");
            SpinnerModel costRange = new SpinnerNumberModel(1, 0, 150, 1);
            JSpinner costs = new JSpinner(costRange);
            
            Object[] objects = { // Array of widgets to display
                name,   names,
                desc,   descs,
                price,  prices,
                cost,   costs};
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "Create Mix In Flavor", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/createMif.png"));
            if(button == JOptionPane.OK_OPTION) {
                emporium.addMixInFlavor(new MixInFlavor(
                    names.getText(), descs.getText(), 
                    (Integer) prices.getValue(), (Integer) costs.getValue()));
                view(Screen.MIX_IN_FLAVORS);         
           }
            view(Screen.MIX_IN_FLAVORS);         
        } catch(Exception e) {
            System.err.println("onCreateMixInFlavorClick exception: " + e);
        }
    }
    //create Container
    protected Scoop onCreateScoop() {
        Scoop scoop = null;
        try {
            IceCreamFlavor icf = (IceCreamFlavor) JOptionPane.showInputDialog(this, "Ice Cream Flavor?", "New Scoop", JOptionPane.QUESTION_MESSAGE, null, emporium.iceCreamFlavors(), null);
            if(icf != null) {
                scoop = new Scoop(icf);
                if(emporium.mixInFlavors().length > 0) {
                    String prompt = "<html>" + scoop + "<br/>Add a mix in?</html>";
                    MixIn mixin = null;
                    while((mixin = onCreateMixIn(prompt)) != null) {
                        scoop.addMixIn(mixin);
                        prompt = "<html>" + scoop + "<br/>Add another mix in?</html>";
                    }
                }  
            }
        } catch(Exception e) {
            System.err.println("onCreateScoopClick exception: " + e);
            scoop = null;
        }
        return scoop;
    }
    protected MixIn onCreateMixIn(String prompt) {
        MixIn mixin = null;
        try {
            JLabel status = new JLabel(prompt);
            JLabel flavor = new JLabel("<html><br/>MixIn Flavor</html>");
            JComboBox<Object> flavors = new JComboBox<>(emporium.mixInFlavors());
            JLabel amount = new JLabel("<html><br/>MixIn Amount</html>");
            JComboBox<MixInAmount> amounts = new JComboBox<>(MixInAmount.values());
            amounts.setSelectedItem(MixInAmount.Normal); // default value
          
            Object[] objects = { // Array of widgets to display
                status,
                flavor, flavors,
                amount, amounts};
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "New MixIn", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/MixInIcon.png"));
            if(button == JOptionPane.YES_OPTION) 
                mixin = new MixIn((MixInFlavor) flavors.getSelectedItem(), 
                                  (MixInAmount) amounts.getSelectedItem());         
        } catch(Exception e) {
            System.err.println("onCreateMixIn exception: " + e);
        }
        return mixin;
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
             case CUSTOMERS: 
                 title = "Customers";
                 for(var t : emporium.customers()) s.append(t.toString() + "<br/>");
                 break;
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
                 int i=1;
                 for(var t : emporium.orders()) {
                    s.append("Order " + i
                    + t.toString() + "<br/>");
                    i++;
                }
                 break;
             default:
                 display.setText("PANIC: Invalid Displays type: " + display);
         }
         display.setText("<html><font size=+4>" + title + 
                         "<br/></font><font size=+2>" + s.toString() + 
                         "</font></html>");
    }
}
