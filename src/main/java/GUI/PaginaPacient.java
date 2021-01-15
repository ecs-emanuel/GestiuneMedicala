package GUI;

import GUI.PaginaPacientContents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Entities.Pacient;

public class PaginaPacient
{
    private static JFrame frame;
    private static JPanel panelHeader;
    private static JLabel labelHeader;
    private static JButton buttonLogout;

    private static JPanel panelTop;
    private static JToggleButton buttonAdaugaProgramare;
    private static JToggleButton buttonModificaProgramare;
    private static JToggleButton buttonListaProgramari;
    private static JToggleButton buttonIstoricMedical;

    private static JPanel panelBot;
    private static Pacient pacientPagina;

    public static void afisarePaginaPacient(Pacient pacient)
    {
        pacientPagina = pacient;

        frame = new JFrame();
        frame.setTitle("Pagina Pacient");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBounds(0, 0, 800, 600);
        frame.setLocationRelativeTo(null);

        afisarePanelHeader();
        afisarePanelTop();
        afisarePanelBot();
    }

    private static void afisarePanelHeader()
    {
        panelHeader = new JPanel();
        frame.add(panelHeader);
        panelHeader.setLayout(null);
        panelHeader.setVisible(true);
        panelHeader.setBounds(0, 0, 800, 40);
        panelHeader.setBackground(new Color(120, 120, 120));

        String nume = pacientPagina.getNume();
        String prenume = pacientPagina.getPrenume();
        String sex = pacientPagina.getSex();
        String pronume = sex.equals("Masculin") ? "Dl. " : sex.equals("Feminin") ? "Dna. " : "";
        String userinformation = pronume + nume + " " + prenume + " - " + "Pacient";

        labelHeader = new JLabel(userinformation);
        panelHeader.add(labelHeader);
        labelHeader.setLayout(null);
        labelHeader.setVisible(true);
        labelHeader.setBounds(10, 13, 800, 20);
        labelHeader.setFont(new Font("Arial", Font.BOLD, 13));
        labelHeader.setForeground(new Color(200, 200, 200));

        buttonLogout = new JButton("Logout");
        panelHeader.add(buttonLogout);
        buttonLogout.setLayout(null);
        buttonLogout.setVisible(true);
        buttonLogout.setFont( new Font("Arial", Font.BOLD, 11));
        buttonLogout.setBounds(695, 10, 80, 23);
        buttonLogout.setFocusPainted(false);
        buttonLogout.setRolloverEnabled(false);

        buttonLogout.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                PaginaLogin.afisarePaginaLogin();
            }
        });
    }

    private static void afisarePanelTop()
    {
        panelTop = new JPanel();
        frame.add(panelTop);
        panelTop.setLayout(null);
        panelTop.setVisible(true);
        panelTop.setBounds(0, 40, 800, 65);
        panelTop.setBackground(new Color(90, 90, 90));

        buttonAdaugaProgramare = new JToggleButton("Adauga Programare");
        panelTop.add(buttonAdaugaProgramare);
        buttonAdaugaProgramare.setLayout(null);
        buttonAdaugaProgramare.setVisible(true);
        buttonAdaugaProgramare.setFont( new Font("Arial", Font.BOLD, 12));
        buttonAdaugaProgramare.setBounds(10, 20, 191, 30);
        buttonAdaugaProgramare.setFocusPainted(false);
        buttonAdaugaProgramare.setRolloverEnabled(false);

        buttonAdaugaProgramare.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clearPanelBot();

                if(buttonAdaugaProgramare.isSelected())
                {
                    AdaugaProgramare.afisareAdaugaProgramare(panelBot, pacientPagina);
                    buttonModificaProgramare.setSelected(false);
                    buttonListaProgramari.setSelected(false);
                    buttonIstoricMedical.setSelected(false);
                }
            }
        });

        buttonModificaProgramare = new JToggleButton("Modifica Programare");
        panelTop.add(buttonModificaProgramare);
        buttonModificaProgramare.setLayout(null);
        buttonModificaProgramare.setVisible(true);
        buttonModificaProgramare.setFont( new Font("Arial", Font.BOLD, 12));
        buttonModificaProgramare.setBounds(201, 20, 191, 30);
        buttonModificaProgramare.setFocusPainted(false);
        buttonModificaProgramare.setRolloverEnabled(false);

        buttonModificaProgramare.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clearPanelBot();

                if(buttonModificaProgramare.isSelected())
                {
                    ModificaProgramare.afisareModificaProgramare(panelBot, pacientPagina);
                    buttonAdaugaProgramare.setSelected(false);
                    buttonListaProgramari.setSelected(false);
                    buttonIstoricMedical.setSelected(false);
                }
            }
        });

        buttonListaProgramari = new JToggleButton("Lista Programari");
        panelTop.add(buttonListaProgramari);
        buttonListaProgramari.setLayout(null);
        buttonListaProgramari.setVisible(true);
        buttonListaProgramari.setFont( new Font("Arial", Font.BOLD, 12));
        buttonListaProgramari.setBounds(392, 20, 191, 30);
        buttonListaProgramari.setFocusPainted(false);
        buttonListaProgramari.setRolloverEnabled(false);

        buttonListaProgramari.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clearPanelBot();

                if(buttonListaProgramari.isSelected())
                {
                    ListaProgramari.afisareListaProgramari(panelBot, pacientPagina);
                    buttonAdaugaProgramare.setSelected(false);
                    buttonModificaProgramare.setSelected(false);
                    buttonIstoricMedical.setSelected(false);
                }
            }
        });

        buttonIstoricMedical = new JToggleButton("Istoric Medical");
        panelTop.add(buttonIstoricMedical);
        buttonIstoricMedical.setLayout(null);
        buttonIstoricMedical.setVisible(true);
        buttonIstoricMedical.setFont( new Font("Arial", Font.BOLD, 12));
        buttonIstoricMedical.setBounds(583, 20, 191, 30);
        buttonIstoricMedical.setFocusPainted(false);
        buttonIstoricMedical.setRolloverEnabled(false);

        buttonIstoricMedical.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clearPanelBot();

                if(buttonIstoricMedical.isSelected())
                {
                    IstoricMedical.afisareIstoricMedical(panelBot, pacientPagina);
                    buttonAdaugaProgramare.setSelected(false);
                    buttonModificaProgramare.setSelected(false);
                    buttonListaProgramari.setSelected(false);
                }
            }
        });
    }

    private static void afisarePanelBot()
    {
        panelBot = new JPanel();
        frame.add(panelBot);
        panelBot.setLayout(null);
        panelBot.setVisible(true);
        panelBot.setBounds(0, 105, 800, 495);
        panelBot.setBackground(new Color(120, 120, 120));
    }

    private static void clearPanelBot()
    {
        panelBot.removeAll();
        panelBot.revalidate();
        panelBot.repaint();
    }
}
