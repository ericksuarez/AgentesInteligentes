package com.ventana;

import com.agentes.AgenteOrquestador;
import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiBusquedas extends JFrame {

    private AgenteOrquestador orquestador;
    private JTextField CampoBusq;
    public String busqueda;

    public GuiBusquedas(AgenteOrquestador a) {
        super(a.getLocalName());

        orquestador = a;

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 2));
        p.add(new JLabel("Ingresa tu busqueda:"));
        CampoBusq = new JTextField(15);
        p.add(CampoBusq);
        getContentPane().add(p, BorderLayout.CENTER);

        JButton addButton = new JButton("Buscar !!!");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
//				try {
                busqueda = "";
                busqueda = CampoBusq.getText().trim();
                orquestador.creaConsultor();
                CampoBusq.setText("");
//				}
//				catch (Exception e) {
//					JOptionPane.showMessageDialog(GuiBusquedas.this, "Valor Invalido. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
//				}
            }
        });
        p = new JPanel();
        p.add(addButton);
        getContentPane().add(p, BorderLayout.SOUTH);

		// Make the agent terminate when the user closes 
        // the GUI using the button on the upper right corner	
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
