package emporium;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import product.Scoop;
import product.IceCreamFlavor;
import product.MixInFlavor;

import gui.MainWin;

public class Emporium {
    public Emporium() {}
    public Emporium(BufferedReader in) {
        this.mixInFlavors = new ArrayList<>();
        this.iceCreamFlavors = new ArrayList<>();
        this.scoops = new ArrayList<>();

    }
    public void save(BufferedWriter out) {
        try(out) {
            for (IceCreamFlavor icfList : iceCreamFlavors) {
                icfList.save(out);
            }
            for (MixInFlavor mifList : mixInFlavors) {
                mifList.save(out);
            }
            for (Scoop scoopList : scoops) {
                scoopList.save(out);
            }
        } catch (Exception e) {
            System.err.println("Failed to write: " + e);
        }
        
    }
    public void addMixInFlavor(MixInFlavor flavor) {
        mixInFlavors.add(flavor);
    }
    public void addIceCreamFlavor(IceCreamFlavor flavor) {
        iceCreamFlavors.add(flavor);
    }
    public void addScoop(Scoop scoop) {
        scoops.add(scoop);
    }

    public Object[] mixInFlavors() {
        return this.mixInFlavors.toArray();
    } 
    public Object[] iceCreamFlavors() {
        return this.iceCreamFlavors.toArray();
    }
    public Object[] scoops() {
        return this.scoops.toArray();
    }

    
    private ArrayList<MixInFlavor> mixInFlavors = new ArrayList<>();
    private ArrayList<IceCreamFlavor> iceCreamFlavors = new ArrayList<>();
    private ArrayList<Scoop> scoops = new ArrayList<>();
}
