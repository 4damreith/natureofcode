package com.natureofcode.scene;

import javax.swing.JFrame;

/**
 *
 * @author syjebrane
 */
public final class Fenetre extends JFrame {
    
    private PanelAnimation panel;
    
    public Fenetre(PanelAnimation panel){
        this.panel = panel;
        this.setLayout();
    }
    
    private void setLayout() {
        this.add(this.panel);
        this.pack();
    }
    
}
