package mx.paradigmmasters.bastagui.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

import mx.paradigmmasters.bastagui.control.MainControl;
import mx.paradigmmasters.bastagui.model.Constants;

public class GameView extends JFrame implements ActionListener {
    private MainControl mainControl;
    private JPanel pStatus;
    private JLabel lStatus;
    private JLabel lStatusImage;
    private JPanel pAnswers;
    private JTextField tfNombre, tfFlorFruto, tfPais, tfAnimal, tfColor;
    private JTextField tfPtsNombre;
    private JTextField tfPtsFlorFruto;
    private JTextField tfPtsPais;
    private JTextField tfPtsAnimal;
    private JTextField tfPtsColor;
    private JButton bBasta;
    private JPanel pPoints;
    private JTextField tfPoints;

    public GameView(MainControl mainControl) throws HeadlessException {
        this.mainControl = mainControl;
        this.setTitle("Basta");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new GridLayout(4, 1));

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icono.png")));
        setIconImage(icon.getImage());

        // Initialize all JPanels
        this.pStatus = new JPanel(new GridLayout(1, 2));
        this.pAnswers = new JPanel(new GridLayout(6, 3));
        this.pPoints = new JPanel(new GridLayout(1, 2));

        // Initialize components of Status Panel
        this.lStatus = new JLabel("Estado");
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/cargando.png")));
        Image resizedImage = originalIcon.getImage().getScaledInstance(300, 169, Image.SCALE_SMOOTH);
        this.lStatusImage = new JLabel(new ImageIcon(resizedImage));
        this.lStatusImage.setEnabled(true);
        this.pStatus.add(this.lStatus);
        this.pStatus.add(this.lStatusImage);

        // Initialize components of Answers Panel
        this.tfNombre = new JTextField();
        this.tfFlorFruto = new JTextField();
        this.tfPais = new JTextField();
        this.tfAnimal = new JTextField();
        this.tfColor = new JTextField();
        // Initialize components showing points
        this.tfPtsNombre = new JTextField();
        this.tfPtsNombre.setEditable(false);
        this.tfPtsFlorFruto = new JTextField();
        this.tfPtsFlorFruto.setEditable(false);
        this.tfPtsPais = new JTextField();
        this.tfPtsPais.setEditable(false);
        this.tfPtsAnimal = new JTextField();
        this.tfPtsAnimal.setEditable(false);
        this.tfPtsColor = new JTextField();
        this.tfPtsColor.setEditable(false);

        this.pAnswers.add(new JLabel("Categoría"));
        this.pAnswers.add(new JLabel("Respuesta"));
        this.pAnswers.add(new JLabel("Puntos"));
        this.pAnswers.add(new JLabel("Nombre: "));
        this.pAnswers.add(tfNombre);
        this.pAnswers.add(tfPtsNombre);
        this.pAnswers.add(new JLabel("Flor o fruto: "));
        this.pAnswers.add(tfFlorFruto);
        this.pAnswers.add(tfPtsFlorFruto);
        this.pAnswers.add(new JLabel("País: "));
        this.pAnswers.add(tfPais);
        this.pAnswers.add(tfPtsPais);
        this.pAnswers.add(new JLabel("Animal: "));
        this.pAnswers.add(tfAnimal);
        this.pAnswers.add(tfPtsAnimal);
        this.pAnswers.add(new JLabel("Color: "));
        this.pAnswers.add(tfColor);
        this.pAnswers.add(tfPtsColor);

        // Initialize Button component
        this.bBasta = new JButton("¡BASTA!");
        this.bBasta.setFont(new Font("Arial", Font.BOLD, 50));
        Color lightBrown = new Color(201, 172, 139);
        bBasta.setBackground(lightBrown);
        this.bBasta.addActionListener(this);

        // Initialize components of Points Panel
        this.tfPoints = new JTextField();
        this.tfPoints.setEditable(false);
        this.pPoints.add(new JLabel("Puntos totales"));
        this.pPoints.add(this.tfPoints);

        // Add JPanels to frame
        this.add(this.pStatus);
        this.add(this.pAnswers);
        this.add(this.bBasta);
        this.add(this.pPoints);

        this.setSize(400, 700);
        this.setLocationRelativeTo(null);
    }

    public void setStatus(int status) {
        ImageIcon iconResized;
        Image image;
        lStatusImage.setText("");
        lStatusImage.setIcon(null);
        switch (status) {
            case Constants.CARGANDO:
                image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/cargando.png"))).getImage();
                iconResized = new ImageIcon(image.getScaledInstance(300, 169, Image.SCALE_SMOOTH));
                lStatusImage.setIcon(iconResized);
                this.lStatus.setText("Estado");
                break;
            case Constants.LISTO:
                image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/esperandoLetra.png"))).getImage();
                iconResized = new ImageIcon(image.getScaledInstance(300, 169, Image.SCALE_SMOOTH));
                lStatusImage.setIcon(iconResized);
                this.lStatus.setText("Estado");
                this.tfNombre.setText("");
                this.tfFlorFruto.setText("");
                this.tfPais.setText("");
                this.tfAnimal.setText("");
                this.tfColor.setText("");
                break;
            case Constants.FINALIZADO:
                image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/finalizado.png"))).getImage();
                iconResized = new ImageIcon(image.getScaledInstance(300, 169, Image.SCALE_SMOOTH));
                lStatusImage.setIcon(iconResized);
                this.lStatus.setText("Estado");
        }
    }

    public void setTotalPoints(int points) {
        this.tfPoints.setText(String.valueOf(points));
    }

    public void setLetter(String letter) {
        this.lStatus.setText("Letra de la ronda: ");
        this.lStatusImage.setIcon(null);
        this.lStatusImage.setFont(new Font("Arial", Font.BOLD, 40));
        this.lStatusImage.setText(letter);
        this.bBasta.setEnabled(true);
        this.tfNombre.setEditable(true);
        this.tfFlorFruto.setEditable(true);
        this.tfPais.setEditable(true);
        this.tfAnimal.setEditable(true);
        this.tfColor.setEditable(true);
    }

    public void setCalificacion(int nombre, int florFruto, int pais, int animal, int color) {
        this.tfPtsNombre.setText(String.valueOf(nombre));
        this.tfPtsFlorFruto.setText(String.valueOf(florFruto));
        this.tfPtsPais.setText(String.valueOf(pais));
        this.tfPtsAnimal.setText(String.valueOf(animal));
        this.tfPtsColor.setText(String.valueOf(color));
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            this.mainControl.sendAnswers(
                    this.tfNombre.getText(),
                    this.tfFlorFruto.getText(),
                    this.tfPais.getText(),
                    this.tfAnimal.getText(),
                    this.tfColor.getText()
            );
            this.tfNombre.setEditable(false);
            this.tfFlorFruto.setEditable(false);
            this.tfPais.setEditable(false);
            this.tfAnimal.setEditable(false);
            this.tfColor.setEditable(false);
            this.bBasta.setEnabled(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
