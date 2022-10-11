package gui;
import emporium.Emporium;
import product.IceCreamFlavor;
import product.MixInAmount;
import product.MixInFlavor;
import product.MixIn;
import product.Scoop;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;

public class MainWin extends JFrame{

    private Emporium emporium;
    private JLabel display;

    public MainWin (String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        emporium = new Emporium();

        // M E N U //////////////////////////////////////////////

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem quit       = new JMenuItem("Quit");

        quit.addActionListener(event -> onQuitClick());

        JMenu     view       = new JMenu("View");
        JMenuItem displayIcf      = new JMenuItem("Ice Cream Flavor");
        JMenuItem displayMif      = new JMenuItem("Mix In Flavor");
        JMenuItem displayScoop      = new JMenuItem("Scoop");

        displayIcf.addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));
        displayMif.addActionListener(event -> view(Screen.MIX_IN_FLAVORS));
        displayScoop.addActionListener(event -> view(Screen.SCOOPS));
          
        JMenu     create       = new JMenu("Create");
        JMenuItem createIcf      = new JMenuItem("Ice Cream Flavor");
        JMenuItem createMif      = new JMenuItem("Mix In Flavor");
        JMenuItem createScoop      = new JMenuItem("Scoop");

        createIcf.addActionListener(event -> onCreateIceCreamFlavorClick());
        createMif.addActionListener(event -> onCreateMixInFlavorClick());
        createScoop.addActionListener(event -> onCreateScoopClick());

        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");

        about.addActionListener(event -> onAboutClick());

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

        display = new JLabel();
        add(display);
        // Make everything in the JFrame visible
        setVisible(true);
    }
    //Exit
    public void onQuitClick() {
        System.exit(0);
    }
    //create Icecream
    public void onCreateIceCreamFlavorClick() {
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField names = new JTextField(20);

        JLabel description = new JLabel("<HTML><br/>Description</HTML>");
        JTextField descriptions = new JTextField(40);

        JLabel price = new JLabel("<HTML><br/>Price</HTML>");
        JTextField prices = new JTextField(20);

        JLabel cost = new JLabel("<HTML><br/>cost</HTML>");
        JTextField costs = new JTextField(20);

        Object[] objects = {
            name,        names,
            description, descriptions,
            price,       prices,
            cost,        costs};
        int button = JOptionPane.showConfirmDialog(
            this,
            objects,
            "Create Ice Cream Flavor",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if(button == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(
                this,
                names.getText() + " ("
                + descriptions.getText() + " "
                + Integer.parseInt(costs.getText()) + " "
                + Integer.parseInt(prices.getText()) + ") is added.");
            
            IceCreamFlavor newIceCreamFlavor = new IceCreamFlavor(names.getText(), descriptions.getText(),
                Integer.parseInt(costs.getText()), Integer.parseInt(prices.getText()));
            
            emporium.addIceCreamFlavor(newIceCreamFlavor);
            view(Screen.ICE_CREAM_FLAVORS);
        }
        else if(button == JOptionPane.CANCEL_OPTION) {}
    }
    
    public void onCreateMixInFlavorClick() {
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField names = new JTextField(20);

        JLabel description = new JLabel("<HTML><br/>Description</HTML>");
        JTextField descriptions = new JTextField(40);

        JLabel price = new JLabel("<HTML><br/>Price</HTML>");
        JTextField prices = new JTextField(20);

        JLabel cost = new JLabel("<HTML><br/>cost</HTML>");
        JTextField costs = new JTextField(20);

        Object[] objects = {
            name,        names,
            description, descriptions,
            price,       prices,
            cost,        costs};
        int button = JOptionPane.showConfirmDialog(
            this,
            objects,
            "Create Mix In Flavor",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if(button == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(
                this,
                names.getText() + " ("
                + descriptions.getText() + " "
                + Integer.parseInt(costs.getText()) + " "
                + Integer.parseInt(prices.getText()) + ") is added.");
            
            MixInFlavor newMixInFlavor = new MixInFlavor(names.getText(), descriptions.getText(),
                Integer.parseInt(costs.getText()), Integer.parseInt(prices.getText()));
            
            emporium.addMixInFlavor(newMixInFlavor);
            view(Screen.MIX_IN_FLAVORS);
        }
        else if(button == JOptionPane.CANCEL_OPTION) {}
    }

    public void onCreateScoopClick() {
        Object[] icfList = emporium.iceCreamFlavors();
        IceCreamFlavor icf = (IceCreamFlavor) JOptionPane.showInputDialog(
            this,
            "Choose your ice cream flavor to create a scoop",
            "Create Scoop",
            JOptionPane.QUESTION_MESSAGE,
            null,
            icfList,
            icfList[0]);
        if (icf == null) {
            display.setText("choose one flavor");
            return;
        }
        Scoop scoop = new Scoop(icf);
    
        Object[] mixList = emporium.mixInFlavors();
        MixInFlavor mixf = (MixInFlavor) JOptionPane.showInputDialog(
            this,
            "Choose your mix in flavor to create a scoop",
            "Create Scoop",
            JOptionPane.QUESTION_MESSAGE,
            null,
            mixList,
            mixList[0]);
        if (mixf == null) {
            display.setText("choose one mixin");
            return;
        }
        Object[] mixAmountList = MixInAmount.values();
        MixInAmount mia = (MixInAmount) JOptionPane.showInputDialog(
            this,
            "Choose your mix amount to create a scoop",
            "Create Scoop",
            JOptionPane.QUESTION_MESSAGE,
            null,
            mixAmountList,
            mixAmountList[0]);
        if (mia == null) {
            display.setText("choose amount");
            return;
        }
        while(mixf != null) {
            MixIn mixAdd = new MixIn(mixf,mia);
            scoop.addMixIn(mixAdd);

            mixf = (MixInFlavor) JOptionPane.showInputDialog(
            this,
            "Choose your mix in flavor to create a scoop",
            "Create Scoop",
            JOptionPane.QUESTION_MESSAGE,
            null,
            mixList,
            mixList[0]);
            if (mixf == null) {
                break;
            }
            mia = (MixInAmount) JOptionPane.showInputDialog(
            this,
            "Choose your mix amount to create a scoop",
            "Create Scoop",
            JOptionPane.QUESTION_MESSAGE,
            null,
            mixAmountList,
            mixAmountList[0]);
        }
        emporium.addScoop(scoop);
        view(Screen.SCOOPS);
    }
    public void onAboutClick() {
        JDialog about = new JDialog();
        about.setLayout(new FlowLayout());
        about.setTitle("Mav’s Ice Cream Emporium");
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("icecream.png"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            about.add(logo);
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>MICE</font></p>"
          + "<p><font size=+2>Mav’s Ice Cream Emporium</font></p>"
          + "</html>");
        about.add(title);

        JLabel artists = new JLabel("<html>"
          + "<p>Version 0.2</p>"
          + "<p>Copyright 2022 by Hyun Ho Kim</p>"
          + "<p>Free for commercial use WITH ATTRIBUTION license</p>"
          + "<p>Logo by Catalyststuff - Freepik.com</p>"
          + "<p><font size=-2>https://www.freepik.com/free-vector/ice-cream-cone-cartoon-icon-illustration-sweet-food-icon- concept-isolated-flat-cartoon-style_13417968.htm</font></p>"
          + "</html>");
        about.add(artists);

        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        about.add(ok);
        
        about.setSize(750,500);
        about.setVisible(true);
    }
     
    
    private void view (Screen screen) {
        if(screen.equals(Screen.ICE_CREAM_FLAVORS)) {
            for (Object ice : emporium.iceCreamFlavors())
                display.setText(ice.toString());
        }
        else if(screen.equals(Screen.MIX_IN_FLAVORS)) {
            for (Object mix : emporium.mixInFlavors())
                display.setText(mix.toString());
        }
        else if(screen.equals(Screen.SCOOPS)) {
            for (Object sc : emporium.scoops())
                display.setText(sc.toString());
        }
    }
}
