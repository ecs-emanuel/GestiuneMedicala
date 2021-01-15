package GUI.PaginaPacientContents;

import Entities.Pacient;
import Entities.Medic;
import Entities.Programare;
import Repository.PacientRepository;
import Repository.MedicRepository;
import Repository.ProgramareRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ModificaProgramare
{

    private static final Color constColorRed = new Color(250, 70, 70);
    private static final Color constColorGreen = new Color(5, 170, 50);

    private static final String constModificaProgramareIncomplete = "Informatii incomplete";
    private static final String constModificaProgramareFail = "Operatiunea a esuat";
    private static final String constModificaProgramareSuccess = "Programare modificata cu succes";

    private static final int CONST_DD_MIN = 1;
    private static final int CONST_DD_MAX = 31;
    private static final int CONST_MM_MIN = 1;
    private static final int CONST_MM_MAX = 12;
    private static final int CONST_YY_NO = 2;

    public static void afisareModificaProgramare(JPanel panelBot, Pacient pacient)
    {
        JLabel labelProgramari = new JLabel("Programare :");
        panelBot.add(labelProgramari);
        labelProgramari.setLayout(null);
        labelProgramari.setVisible(true);
        labelProgramari.setBounds(12, 22, 100, 25);
        labelProgramari.setFont(new Font("Arial", Font.BOLD, 13));
        labelProgramari.setForeground(new Color(250, 250, 250));

        JComboBox<Programare> boxProgramare = new JComboBox<>();
        boxProgramare.setBounds(110, 22, 244, 25 );
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

                    LocalDateTime data = programare.getData().toLocalDateTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm");

                    Medic medic = programare.getMedic();
                    String numeMedic = medic.getNume() + " " + medic.getPrenume();

                    String itemText = String.format("[%s]  %s", formatter.format(data), numeMedic);
                    setText(itemText);
                }
                return this;
            }
        });

        List<Programare> listaProgramari;
        listaProgramari = PacientRepository.getListaProgramariInCurs(pacient);

        for(int i = 0; i < listaProgramari.size(); i++ )
        {
            boxProgramare.addItem(listaProgramari.get(i));
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

        JLabel labelOra = new JLabel("Ora :");
        panelBot.add(labelOra);
        labelOra.setLayout(null);
        labelOra.setVisible(true);
        labelOra.setBounds(12, 141, 100, 25);
        labelOra.setFont(new Font("Arial", Font.BOLD, 13));
        labelOra.setForeground(new Color(250, 250, 250));

        JComboBox<Programare> boxOra = new JComboBox<>();
        boxOra.setBounds(110, 141, 244, 25 );
        boxOra.setVisible(true);
        panelBot.add(boxOra);

        boxOra.setRenderer(new DefaultListCellRenderer()
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
                boxOra.removeAllItems();

                Date data = Date.valueOf(boxDataYear.getSelectedItem() + "-" + boxDataMonth.getSelectedItem() + "-" + boxDataDay.getSelectedItem());

                Programare programare = (Programare) boxProgramare.getSelectedItem();
                Medic medic = programare.getMedic();

                List<Programare> listaProgramariLibere = MedicRepository.getListaProgramariLibere(medic, data);

                for (int i = 0; i < listaProgramariLibere.size(); i++)
                {
                    boxOra.addItem(listaProgramariLibere.get(i));
                }
            }
        });

        JButton buttonAdauga = new JButton("Modifica Programare");
        panelBot.add(buttonAdauga );
        buttonAdauga.setLayout(null);
        buttonAdauga.setVisible(true);
        buttonAdauga.setFont( new Font("Arial", Font.BOLD, 11));
        buttonAdauga.setBounds(110, 178, 244, 25);
        buttonAdauga.setFocusPainted(false);

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
                Programare programareLibera = (Programare) boxOra.getSelectedItem();

                if(programareLibera == null)
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constModificaProgramareIncomplete);
                    labelStatus.setVisible(true);
                    return;
                }

                Programare programare = (Programare) boxProgramare.getSelectedItem();

                programareLibera.setIndex(programare.getIndex());
                programareLibera.setPacient(pacient);

                if(!ProgramareRepository.isInformationComplete(programareLibera))
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constModificaProgramareIncomplete);
                    labelStatus.setVisible(true);
                    return;
                }

                if(!ProgramareRepository.modificaProgramare(programareLibera))
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constModificaProgramareFail);
                    labelStatus.setVisible(true);
                    return;
                }

                labelStatus.setForeground(constColorGreen);
                labelStatus.setText(constModificaProgramareSuccess);
                labelStatus.setVisible(true);

                boxOra.removeAllItems();

                int itemIndex = boxProgramare.getSelectedIndex();
                boxProgramare.removeItemAt(itemIndex);
                boxProgramare.insertItemAt(programareLibera, boxProgramare.getSelectedIndex());
                boxProgramare.setSelectedIndex(itemIndex);
            }
        });
    }
}
