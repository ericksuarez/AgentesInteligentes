package com.ventana;

import com.agentes.AgenteOrquestador;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiBusquedas extends JFrame {

    private AgenteOrquestador orquestador;
    private JTextField CampoBusq;
    public static String busqueda;

    public GuiBusquedas(AgenteOrquestador a) {
        super(a.getLocalName());

        orquestador = a;

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 2));
        p.add(new JLabel("Ingresa tu busqueda:"));
        CampoBusq = new JTextField(35);
        p.add(CampoBusq);
        getContentPane().add(p, BorderLayout.CENTER);

        JButton addButton = new JButton("Buscar !!!");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                busqueda = "";
                busqueda = CampoBusq.getText().trim();
                orquestador.creaConsultor();
                CampoBusq.setText("");

            }
        });
        p = new JPanel();
        p.add(addButton);
        getContentPane().add(p, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                orquestador.doDelete();
            }
        });

        setResizable(false);
    }

    public void showGui() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.setVisible(true);
    }
}
