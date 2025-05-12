
package com.company.pages.window;

import java.util.List;
import java.util.Random;

public class Dice {

    private int nbFace;
    private List<Motif> diceMotif;
    private final Random random = new Random();
  
    public Dice(int nbFace, List<Motif> diceMotif) {
        this.nbFace = nbFace;
        this.diceMotif = diceMotif;
    }

    public Dice() {
    }
    
    public String showOffMotif(Dice d){
         if (diceMotif == null || diceMotif.isEmpty()) {
            return "(no motifs)";
        }
        StringBuilder motifString = new StringBuilder();
        for (Motif m : diceMotifs) {
            motifString.append(" ").append(m.getMotifName());
        }
        return motifString.toString();
    }

    public Object rollDice() {
        Object object = null;
        if (nbFace == diceMotif.size()) {
            int rand = new Random().nextInt(diceMotif.size());
            System.out.println("num aleatoire obtenu:: " + rand);
            object = diceMotif.get(rand);
        } else {
            System.err.println("Cannot play");
        }
        return object;
    }
    
   
    @Override
    public String toString() {
        return nbFace == 0 ? "Dice not created" : "Dice have " + nbFace + " faces " + " and mofif are: "   +this.showOffMotif(this);
    }

    public int getNbFace() {
        return nbFace;
    }

    public void setNbFace(int nbFace) {
        this.nbFace = nbFace;
    }

    public List<Motif> getDiceMotif() {
        return diceMotif;
    }

    public void setDiceMotif(List<Motif> diceMotif) {
        this.diceMotif = diceMotif;
    }
    
}
