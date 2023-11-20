package com.company.pages.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class PlayWindow extends JFrame {

    private JButton lancer;
    private JButton addBtn;
    private JButton editerBtn;
    private JButton deleteBtn;
    private JButton createDiceBtn;
    private JComboBox<Dice> jComboBox1;
    private JLabel jLabel2;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable table;
    private List<Dice> listDiceToPrint;
    private CreateDiceWindow fen;
    private CreateDiceWindow fenEdit;
    private List<Image> images = new ArrayList<>();
    private JPanel contentPaneDice;
    private JPanel contentPaneEditedDice;
    private JTextField num = null;
    private JLabel resultLabel = null;
    private List<JTextField> listOfTextFields = new ArrayList<>();
    private JLabel label = null;
    private JButton save = new JButton();
    private JButton move = new JButton();
    private Motif<Image> imageName = null;
    private List<Motif> listesMotifs = new ArrayList<>();
    private List<Motif> manualMotifs = new ArrayList<>();
    private List<JLabel> listOfLabels = new ArrayList<>();
    private List<Dice> listOfDiceRolls = new ArrayList<>();
    private JButton btnRelancer;

    // object elt
    private boolean isDiceCreatedImage = false;
    private boolean isDiceCreatedManual = false;
    private Motif<String> ob;
    private Motif<String> red;
    private List<Motif> listes;
    private List<Motif> listMot = new ArrayList<>();
    private Dice de;
    private Dice diceEdited;
    private List<Dice> diceList = new ArrayList<>();
    private Dice[][] listDices = new Dice[diceList.size()][1];
    private int[] indiceDiceSelected;
    private DefaultListModel<String> listModelLabels = new DefaultListModel<>();
    private static PlayWindow win;

    public PlayWindow() {
        super("JEU DE DE");
        initComponents();
    }

    public void deleteDice(Dice d) {
        if (diceList.contains(d)) {
            diceList.remove(d);
        } else {
            System.out.println("deleted couldn't made");
        }
    }

    public void manyDice(List<Dice> list) {
        list.forEach(d -> {
            deleteDice(d);
        });
    }

    public void listAllDice() {
        diceList.forEach(d -> {
            System.out.println("nb face::" + d.getNbFace() + "motif::" + d.getDiceMotif());
        });
    }

    public void setUpComboBoxItems(List<Dice> dices) {
        int count = 0;
        Dice[] obj = new Dice[dices.size()];
        for (Dice d : dices) {
            obj[count] = d;
            count++;
        }
        jComboBox1.setModel(new DefaultComboBoxModel<>(obj));
    }

    private void initComponents() {

        listes = new ArrayList<>();
        listDiceToPrint = new ArrayList<>();

        jScrollPane1 = new JScrollPane();
        table = new JTable();
        jPanel1 = new JPanel();
        lancer = new JButton();
        btnRelancer = new JButton();
        addBtn = new JButton();
        editerBtn = new JButton();
        deleteBtn = new JButton();
        createDiceBtn = new JButton();
        jLabel2 = new JLabel();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenu2 = new JMenu();
        jComboBox1 = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new DefaultTableModel(listDices,
                new String[] {
                        "Dés"
                }));
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        table.setAlignmentX(0.1F);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        jScrollPane1.setViewportView(table);

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 477, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));

        lancer.setBackground(new Color(51, 255, 51));
        lancer.setText("Lancer");
        lancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                lancerActionPerformed(evt);
            }
        });

        setUpComboBoxItems(diceList);
        addBtn.setText("Ajouter");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        editerBtn.setForeground(new Color(153, 153, 255));
        editerBtn.setText("Editer");
        editerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws UnsupportedOperationException {
                editerBtnActionPerformed(e);
            }
        });

        deleteBtn.setForeground(new Color(255, 0, 0));
        deleteBtn.setText("Supprimer");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws UnsupportedOperationException {
                deleteBtnActionPerformed(e);
            }
        });

        createDiceBtn.setForeground(new Color(102, 255, 102));
        createDiceBtn.setText("Créer");
        createDiceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws UnsupportedOperationException {
                createDiceFrame();
            }
        });

        btnRelancer.setText("Relancer");
        btnRelancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                relancerBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("\t Résultats des lancés");

        jMenu1.setText("Fichier");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Aide");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lancer, GroupLayout.PREFERRED_SIZE, 126,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(editerBtn, GroupLayout.PREFERRED_SIZE, 90,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 100,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(createDiceBtn, GroupLayout.PREFERRED_SIZE, 98,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 277,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 157,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(68, 68, 68)
                                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 332,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addBtn)
                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lancer)
                                        .addComponent(editerBtn)
                                        .addComponent(deleteBtn)
                                        .addComponent(createDiceBtn))
                                .addContainerGap()));

        pack();
    }

    private Dice createDice(int nbFace, List<Motif> list) {
        return list.size() == nbFace ? new Dice(nbFace, list) : null;
    }

    /**
     * creer un motif d'une avec une image
     */
    private Motif<Image> createImageMotif(String imagesPath) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image im = t.getImage(imagesPath);
        Motif<Image> i = new Motif<>(im);
        return i;
    }

    private Image displayImageToFrame(String imagesPath) {
        Toolkit t = Toolkit.getDefaultToolkit();
        return t.getImage(imagesPath);
    }

    /**
     * permet de lancer plusieurs dés
     * et renvoyer les resultats obtenues.
     * 
     * @param diceList
     */
    private List<String> rollManyDiceByIndice(List<Dice> diceList) {
        boolean notEmpty = !diceList.isEmpty();
        List<String> result = new ArrayList<>();
        if (notEmpty) {
            diceList.forEach((Dice d) -> {
                result.add(String.valueOf(d.rollDice()));
            });
        }
        return result;
    }

    public CreateDiceWindow createFrame(String title, List<Image> images) {
        CreateDiceWindow fenetre = new CreateDiceWindow(title, images);
        fenetre.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(fenetre,
                        "Etes vous sure de vouloir quitter?") == JOptionPane.OK_OPTION) {
                    fenetre.setVisible(false);
                    fenetre.dispose();
                }
            }
        });
        return fenetre;
    }

    /**
     * permet de mettre a jour une fenetre.
     * 
     * @param j
     */
    public void updateFrame(JFrame j) {
        try {
            j.invalidate();
            j.validate();
            j.repaint();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void createRegisterButton(int positionY) {
        save = new JButton("ENREGISTRER DE");
        save.setBackground(Color.BLUE);
        save.setBounds(100, positionY, 160, 30);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws UnsupportedOperationException {
                createManualMotifs();
                if (isDiceCreatedImage) {
                    if (!diceList.contains(createDice(listesMotifs.size(), listesMotifs))) {
                        diceList.add(createDice(listesMotifs.size(), listesMotifs));
                        isDiceCreatedImage = false;
                    } 
                } else if (isDiceCreatedManual) {
                    if (!diceList.contains(createDice(manualMotifs.size(), manualMotifs))) {
                        diceList.add(createDice(manualMotifs.size(), manualMotifs));
                        isDiceCreatedManual = false;
                    } 
                }
                setUpComboBoxItems(diceList);
                updateFrame(win);
            }
        });
        contentPaneDice.add(save);
    }

    public void createRegisterButton(int positionY, CreateDiceWindow fen, JPanel p) {
        save = new JButton("ENREGISTRER DE");
        save.setBackground(Color.BLUE);
        save.setBounds(100, positionY, 160, 30);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws UnsupportedOperationException {
                createManualMotifs();
                diceEdited.setDiceMotif(manualMotifs);
                setUpComboBoxItems(diceList);
                table.setModel(new DefaultTableModel(listDices,
                        new String[] {
                                "Dés"
                        }));
                updateFrame(fen);
            }
        });
        p.add(save);
    }

    private void createMotifComponent(int numberComponent, CreateDiceWindow fen, JPanel contentPaneDice) {
        new Thread(new Runnable() {
            int y = 60;
            int yTetx = 96;

            @Override
            public void run() throws UnsupportedOperationException {
                listOfTextFields = new ArrayList<>();
                for (int i = 0; i < numberComponent; i++) {
                    try {
                        label = new JLabel("Motif " + i + ":");
                        label.setBounds(100, y, 100, 100);
                        num = new JTextField(20);
                        num.setBounds(210, yTetx, 160, 30);
                        num.putClientProperty("id", i);
                        y += 50;
                        yTetx += 48;
                        contentPaneDice.add(label);
                        contentPaneDice.add(num);
                        listOfTextFields.add(num);

                        Thread.sleep(1000 / 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                createRegisterButton(y + 60);
                updateFrame(fen);
            }
        }).start();
    }

    private void createMotifComponent(int numberComponent, CreateDiceWindow fen, JPanel contentPaneDice,
            List<Motif> motifs) {
        new Thread(new Runnable() {
            int y = 60;
            int yTetx = 96;

            @Override
            public void run() throws UnsupportedOperationException {
                listOfTextFields = new ArrayList<>();
                int count = 0;
                if (!motifs.isEmpty()) {
                    for (int i = 0; i < numberComponent; i++) {
                        try {
                            label = new JLabel("Motif " + i + ":");
                            label.setBounds(100, y, 100, 100);
                            num = new JTextField(20);
                            num.setBounds(210, yTetx, 160, 30);
                            num.putClientProperty("id", i);
                            num.setText(motifs.get(count).toString());
                            y += 50;
                            yTetx += 48;
                            count++;
                            contentPaneDice.add(label);
                            contentPaneDice.add(num);
                            listOfTextFields.add(num);

                            Thread.sleep(1000 / 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    createRegisterButton(y + 60, fen, contentPaneDice);
                }
                updateFrame(fen);
            }
        }).start();
    }

    /**
     * permet de recuperer tous les motifs
     * entrer par le user.
     */
    private void createManualMotifs() {
        manualMotifs = new ArrayList<>();
        System.out.println("list of text" + listOfTextFields);
        if (!listOfTextFields.isEmpty()) {
            isDiceCreatedManual = true;
            for (JTextField j : listOfTextFields) {
                manualMotifs.add(new Motif<>(j.getText()));
            }
        }
    }

    private void createMoveButton() {
        move = new JButton("RETIRER LES IMAGES");
        move.setBackground(Color.RED);
        move.setBounds(300, 90, 160, 30);

        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws UnsupportedOperationException {
                images = new ArrayList<>();
                updateFrame(fen);
            }
        });
        contentPaneDice.add(move);
    }

    /**
     * permet de choisir les images comme
     * motifs pour les des
     * 
     * @return
     */
    private JButton createChooseFileButton() {
        JPanel buttonPane = new JPanel();
        JButton chooseImage = new JButton("CHOISIR IMAGES DES DES");

        chooseImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws UnsupportedOperationException {
                images = new ArrayList<>();
                listesMotifs = new ArrayList<>();
                isDiceCreatedImage = false;
                System.out.println("listes motif" + listesMotifs + "listes images " + images);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif",
                        "jpeg");
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(filter);
                fc.setMultiSelectionEnabled(true);
                fc.showOpenDialog(buttonPane);
                File[] selFiles = fc.getSelectedFiles();

                if (selFiles.length > 0) {
                    isDiceCreatedImage = true;
                    for (File f : selFiles) {
                        imageName = createImageMotif(f.toString());
                        images.add(displayImageToFrame(f.toString()));
                        listesMotifs.add(imageName);
                    }

                    createRegisterButton(90);
                    createMoveButton();
                    fen.setImages(images);
                    fen.repaint();
                }
            }
        });

        return chooseImage;
    }

    /**
     * permet de creer les dés avec les motifs images et les afficher pour
     * visualiser..
     */
    private void createDiceFrame() {
        fen = createFrame("CREATION DE DE", images);
        contentPaneDice = (JPanel) fen.getContentPane();
        contentPaneDice.setLayout(null);
        JLabel facesNum = new JLabel("Nombre de faces:");
        facesNum.setBounds(100, 15, 100, 100);
        JTextField num = new JTextField(20);
        num.setBounds(210, 48, 160, 30);

        num.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws UnsupportedOperationException {
                System.out.println("Text=" + num.getText());
                num.setText("");
            }
        });

        JButton addMotif = new JButton("AJOUTER MOTIF");
        addMotif.setBounds(380, 48, 160, 30);
        addMotif.setBackground(Color.GREEN);

        addMotif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws UnsupportedOperationException {
                if (!"".equals(num.getText())) {
                    createMotifComponent(Integer.parseInt(num.getText()), fen, contentPaneDice);
                } else {
                    JOptionPane.showMessageDialog(fen, "Veuillez entrer le nombre de face", "face warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton chooseIm = createChooseFileButton();
        chooseIm.setBounds(555, 48, 160, 30);
        contentPaneDice.add(facesNum);
        contentPaneDice.add(num);
        contentPaneDice.add(addMotif);
        contentPaneDice.add(chooseIm);
        fen.setVisible(true);
    }

    /**
     * permet de lancer les dés selectionnés
     * lors du clic sur le bouton lancer.
     *
     */
    private void lancerActionPerformed(ActionEvent evt) {
        indiceDiceSelected = table.getSelectedRows();
        List<Dice> listDiceSelected = new ArrayList<>();
        List<Dice> listDiceAlreadyRoll = new ArrayList<>();
        for (int i = 0; i < indiceDiceSelected.length; i++) {
            listDiceSelected.add(listDices[indiceDiceSelected[i]][0]);
        }

        if (!listOfLabels.isEmpty()) {
            for (JLabel label : listOfLabels) {
                if (jPanel1.isAncestorOf(label)) {
                    jPanel1.remove(label);
                    jPanel1.revalidate();
                    jPanel1.repaint();
                }
            }
        }

        displayResults(rollManyDiceByIndice(listDiceSelected));
    }

    private void relancerBtnActionPerformed(ActionEvent e) {
        System.out.println("to write here");
    }

    /**
     * permet d'afficher le resultat
     * d'un lance a l'ecran
     * 
     * @param results
     */
    private void displayResults(List<String> results) {
        int position = 20;
        int index = 0;
        listOfLabels = new ArrayList<>();
        for (String str : results) {
            resultLabel = new JLabel(str);
            resultLabel.setFont(new Font("Verdana", 1, 20));
            resultLabel.setBounds(position, 20, 200, 200);
            resultLabel.putClientProperty("id", index);
            position += 30;
            index++;
            jPanel1.add(resultLabel);
            listOfLabels.add(resultLabel);
        }
        updateFrame(win);
    }

    /**
     * permet d'afficher tous
     * les details d'un de dans le tableau
     */
    private Dice[][] getAllDetails(List<Dice> dices) {
        int i = 0;
        Dice[] valuesInt = new Dice[dices.size()];
        Dice[][] obj = new Dice[dices.size()][1];
        for (Dice d : dices) {
            valuesInt[i] = d;
            i++;
        }

        for (int k = 0; k < valuesInt.length; k++) {
            obj[k][0] = valuesInt[k];
        }
        return obj;
    }

    /**
     * permet de supprimer un de
     * de la liste des des.
     */
    public void deleteBtnActionPerformed(ActionEvent evt) {
        indiceDiceSelected = table.getSelectedRows();
        List<Dice> listDiceSelected = new ArrayList<>();
        for (int i = 0; i < indiceDiceSelected.length; i++) {
            listDiceSelected.add(listDices[indiceDiceSelected[i]][0]);
        }

        for (Dice d : listDiceSelected) {
            for (int j = 0; j < listDiceSelected.size(); j++) {
                if (d == listDices[j][0]) {
                    if (listDiceToPrint.contains(d)) {
                        listDiceToPrint.remove(d);
                    }
                }
            }
        }

        listDices = getAllDetails(listDiceToPrint);
        table.setModel(new DefaultTableModel(listDices,
                new String[] {
                        "Dés"
                }));
        setUpComboBoxItems(listDiceToPrint);
        win.invalidate();
        win.validate();
        win.repaint();
    }

    private void addBtnActionPerformed(ActionEvent evt) {
        Dice d = (Dice) jComboBox1.getSelectedItem();
        System.out.println("liste of dice add::" + listDiceToPrint);
        if (!listDiceToPrint.contains(d)) {
            listDiceToPrint.add(d);
            listDices = getAllDetails(listDiceToPrint);
            table.setModel(new DefaultTableModel(listDices,
                    new String[] {
                            "Dés"
                    }));
        } else {
            System.out.println("deja ajouté");
        }
    }

    /**
     * cree une fenetre d'edition des des
     */
    private void createEditedFrame(int numberFields, List<Motif> motifs) {
        System.out.println("hello");
        fenEdit = createFrame("EDITION DE DE", images);
        contentPaneEditedDice = (JPanel) fenEdit.getContentPane();
        contentPaneEditedDice.setLayout(null);
        createMotifComponent(numberFields, fenEdit, contentPaneEditedDice, motifs);
        fenEdit.setVisible(true);
    }

    /**
     * permet de modfier les motifs
     * d'un de
     */
    private void editerBtnActionPerformed(ActionEvent evt) {
        indiceDiceSelected = table.getSelectedRows();
        List<Motif> motifs = new ArrayList<>();
        int numberOfFace = 0;
        diceEdited = null;

        if (indiceDiceSelected.length > 1) {
            System.out.println("Veuillez choisr un seul objet");
        } else {
            for (int i = 0; i < indiceDiceSelected.length; i++) {
                numberOfFace = listDices[indiceDiceSelected[i]][0].getNbFace();
                motifs = listDices[indiceDiceSelected[i]][0].getDiceMotif();
                diceEdited = listDices[indiceDiceSelected[i]][0];
            }
            createEditedFrame(numberOfFace, motifs);
        }
    }

    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        SwingUtilities.invokeLater(() -> {
            win = new PlayWindow();
            win.setVisible(true);
        });
    }
}
