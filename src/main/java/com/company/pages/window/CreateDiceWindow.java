/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.pages.window;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class CreateDiceWindow extends JFrame {
    
    private List<Image> images = new ArrayList<>();
    
    public CreateDiceWindow(String title ,  List<Image>image) {
        super(title);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(800, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        image = images;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = 220;
        for (Image im : images) {
            g.drawImage(im, x, 160, this);
            x += 140;
        }
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
 
}
