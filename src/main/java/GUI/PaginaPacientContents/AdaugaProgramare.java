package GUI.PaginaPacientContents;

import Entities.Pacient;
import Entities.Medic;
import Entities.Specializare;
import Entities.Programare;
import Repository.MedicRepository;
import Repository.ProgramareRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AdaugaProgramare
{
    private static final Color constColorRed = new Color(250, 70, 70);
    private static final Color constColorGreen = new Color(5, 170, 50);

    private static final String constAdaugaProgramareIncomplete = "Informatii incomplete";
    private static final String constAdaugaProgramareFail = "Operatiunea a esuat";
    private static final String constAdaugaprogramareSuccess = "Programare adaugata cu succes";

    private static final int CONST_DD_MIN = 1;
    private static final int CONST_DD_MAX = 31;
    private static final int CONST_MM_MIN = 1;
    private static final int CONST_MM_MAX = 12;
    private static final int CONST_YY_NO = 2;

    public static void afisareAdaugaProgramare(JPanel panelBot, Pacient pacient)
    {
        JLabel labelMedic = new JLabel("Medic :");
        panelBot.add(labelMedic);
        labelMedic.setLayout(null);
        labelMedic.setVisible(true);
        labelMedic.setBounds(12, 22, 100, 25);
        labelMedic.setFont(new Font("Arial", Font.BOLD, 13));
        labelMedic.setForeground(new Color(250, 250, 250));

        JComboBox<Medic> boxMedic = new JComboBox<>();
        boxMedic.setBounds(110, 22, 244, 25 );
        boxMedic.setVisible(true);
        panelBot.add(boxMedic);

        boxMedic.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Medic)
                {
                    Medic medic = (Medic) value;
                    Specializare specializare = medic.getSpecializare();

                    String nume = medic.getNume() + " " + medic.getPrenume();
                    String departament = specializare.getDenumire();

                    String itemText = String.format("%s - %s", nume, departament);
                    setText(itemText);
                }
                return this;
            }
        });

        List<Medic> listaMedici = MedicRepository.getListaMedici();

        for (int i = 0; i < listaMedici.size(); i++)
        {
            boxMedic.addItem(listaMedici.get(i));
        }

        JLabel labelData = new JLabel("Data :");
        panelBot.add(labelData);
        labelData.setLayout(null);
        labelData.setVisible(true);
        labelData.setBounds(12, 57, 100, 25);
        labelData.setFont(new Font("Arial", Font.BOLD, 13));
        labelData.setForeground(new Color(250, 250, 250));

        JComboBox<String> boxDataDay = new JComboBox<String>();
        boxDataDay.setBounds(110, 57, 70, 25 );
        boxDataDay.setVisible(true);
        panelBot.add(boxDataDay);

        for (int i = CONST_DD_MIN; i <= CONST_DD_MAX ; i++)
        {
            boxDataDay.addItem(String.format("%02d", i));
        }

        JComboBox<String> boxDataMonth = new JComboBox<String>();
        boxDataMonth.setBounds(182, 57, 70, 25 );
        boxDataMonth.setVisible(true);
        panelBot.add(boxDataMonth);

        for (int i = CONST_MM_MIN; i <= CONST_MM_MAX; i++)
        {
            boxDataMonth.addItem(String.format("%02d", i));
        }

        JComboBox<String> boxDataYear = new JComboBox<String>();
        boxDataYear.setBounds(254, 57, 100, 25 );
        boxDataYear.setVisible(true);
        panelBot.add(boxDataYear);

        LocalDate currentDate = LocalDate.now();

        int yearMin = currentDate.getYear();
        int yearMax = yearMin + CONST_YY_NO;

        for (int i = yearMin; i < yearMax; i++)
        {
            boxDataYear.addItem(String.valueOf(i));
        }

        JButton buttonCauta = new JButton("Verifica Disponibilitate");
        panelBot.add(buttonCauta);
        buttonCauta.setLayout(null);
        buttonCauta.setVisible(true);
        buttonCauta.setFont( new Font("Arial", Font.BOLD, 11));
        buttonCauta.setBounds(110, 94, 244, 25);
        buttonCauta.setFocusPainted(false);

        JLabel labelProgramari = new JLabel("Ora :");
        panelBot.add(labelProgramari);
        labelProgramari.setLayout(null);
        labelProgramari.setVisible(true);
        labelProgramari.setBounds(12, 141, 100, 25);
        labelProgramari.setFont(new Font("Arial", Font.BOLD, 13));
        labelProgramari.setForeground(new Color(250, 250, 250));

        JComboBox<Programare> boxProgramare = new JComboBox<>();
        boxProgramare.setBounds(110, 141, 244, 25 );
        boxProgramare.setVisible(true);
        panelBot.add(boxProgramare);

        boxProgramare.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Programare)
                {
                    Programare programare = (Programare) value;

                    Timestamp data = programare.getData();
                    LocalTime ora = data.toLocalDateTime().toLocalTime();

                    String itemText = String.format("%s", ora);
                    setText(itemText);
                }
                return this;
            }
        });

        buttonCauta.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boxProgramare.removeAllItems();

                Medic medic = (Medic) boxMedic.getSelectedItem();
                Date data = Date.valueOf(boxDataYear.getSelectedItem() + "-" + boxDataMonth.getSelectedItem() + "-" + boxDataDay.getSelectedItem());

                List<Programare> listaProgramariLibere = MedicRepository.getListaProgramariLibere(medic, data);

                for (int i = 0; i < listaProgramariLibere.size(); i++)
                {
                    boxProgramare.addItem(listaProgramariLibere.get(i));
                }
            }
        });

        JButton buttonAdauga = new JButton("Adauga Programare");
        panelBot.add(buttonAdauga );
        buttonAdauga .setLayout(null);
        buttonAdauga .setVisible(true);
        buttonAdauga .setFont( new Font("Arial", Font.BOLD, 11));
        buttonAdauga .setBounds(110, 178, 244, 25);
        buttonAdauga .setFocusPainted(false);

        JLabel labelStatus = new JLabel("");
        panelBot.add(labelStatus);
        labelStatus.setLayout(null);
        labelStatus.setVisible(false);
        labelStatus.setBounds(110, 215, 246, 25);
        labelStatus.setHorizontalAlignment(SwingConstants.CENTER);
        labelStatus.setFont(new Font("Arial", Font.BOLD, 12));
        labelStatus.setForeground(new Color(250, 250, 250));

        buttonAdauga.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Programare programare = (Programare) boxProgramare.getSelectedItem();

                if(programare == null)
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constAdaugaProgramareIncomplete);
                    labelStatus.setVisible(true);
                    return;
                }

                programare.setPacient(pacient);

                if(!ProgramareRepository.isInformationComplete(programare))
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constAdaugaProgramareIncomplete);
                    labelStatus.setVisible(true);
                    return;
                }

                if(ProgramareRepository.adaugaProgramare(programare) == ProgramareRepository.CONST_ADDPROGRAMARE_FAIL)
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constAdaugaProgramareFail);
                    labelStatus.setVisible(true);
                    return;
                }

                labelStatus.setForeground(constColorGreen);
                labelStatus.setText(constAdaugaprogramareSuccess);
                labelStatus.setVisible(true);

                boxProgramare.removeItem(programare);
                boxProgramare.updateUI();
            }
        });
    }
}
