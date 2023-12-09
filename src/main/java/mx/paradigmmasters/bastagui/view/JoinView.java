package mx.paradigmmasters.bastagui.view;

import mx.paradigmmasters.bastagui.control.MainControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class JoinView extends JFrame implements ActionListener {
    private MainControl control;
    private JButton bJoin;
    private JTextField tfUsername;
    public JoinView(MainControl control) throws HeadlessException {
        this.control = control;
        this.setTitle("Basta");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4,1));

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icono.png")));
        setIconImage(icon.getImage());

        this.bJoin = new JButton("Unirse al juego");
        this.bJoin.setFont(new Font("Arial", Font.BOLD, 30));
        Color lightBrown = new Color(201, 172, 139);
        bJoin.setBackground(lightBrown);
        this.bJoin.addActionListener(this);
        this.tfUsername = new JTextField();

        JLabel lTitle = new JLabel("Basta online");
        lTitle.setFont(new Font("Arial", Font.BOLD, 15));
        lTitle.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lUserName = new JLabel("Selecciona un nombre de usuario");
        lUserName.setHorizontalAlignment(SwingConstants.CENTER);
        lUserName.setFont(new Font("Arial", Font.BOLD, 15));

        this.add(lTitle);
        this.add(lUserName);
        this.add(this.tfUsername);
        this.add(this.bJoin);

        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (this.tfUsername.getText().isEmpty()){
            JOptionPane.showMessageDialog(
                    this,
                    "Es obligatorio un nombre de usuario",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }else {
            this.control.startGame(this.tfUsername.getText());
        }
    }

    public void connectionError(int attempts){
        JOptionPane.showMessageDialog(
                this,
                "No fue posible conectarse, intentando de nuevo " + attempts + " veces más",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
