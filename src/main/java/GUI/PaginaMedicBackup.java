package GUI;

import Entities.FisaPacient;
import Entities.Medic;
import Entities.Pacient;
import Entities.Programare;
import Repository.FisaPacientRepository;
import Repository.MedicRepository;
import Repository.PacientRepository;
import Repository.ProgramareRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaginaMedicBackup
{
    private static final int constDayMax = 31;
    private static final int constMonthMax = 12;
    private static final int constYearMin = 1900;
    private static final int constYearMax = 2021;

    private static final Color constColorRed = new Color(250, 70, 70);
    private static final Color constColorGreen = new Color(5, 170, 50);

    private static final String constAdaugaFisaMesajIncomplete = "Informatii incomplete";
    private static final String constAdaugaFisaMesajFail = "Operatiunea a esuat";
    private static final String constAdaugaFisaMesajSuccess = "Fisa adaugata cu success";

    private static final String constModificaFisaMesajIncomplete = "Informatii incomplete";
    private static final String constModificaFisaMesajFail = "Operatiunea a esuat";
    private static final String constModificaFisaMesajSuccess = "Fisa modificata cu succes";

    private static final String constModificaProgramareFail = "Operatiunea a esuat";
    private static final String constModificaProgramareSuccess = "Programare modificata cu succes";

    private static JFrame frame;

    private static JPanel panelHeader;
    private static JLabel labelHeader;
    private static JButton buttonLogout;

    private static JPanel panelTop;
    private static JToggleButton buttonListaPacienti;
    private static JToggleButton buttonAdaugaFisa;
    private static JToggleButton buttonModificaFisa;
    private static JToggleButton buttonMarcareProgramare;

    private static JPanel panelBot;

    private static Medic medicPagina;

    public static void afisarePaginaMedic(Medic medic)
    {
        medicPagina = medic;

        frame = new JFrame();
        frame.setTitle("Pagina Medic");
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

        String nume = medicPagina.getNume();
        String prenume = medicPagina.getPrenume();
        String specializare = medicPagina.getSpecializare().getDenumire();
        String userinformation = "Dr. " + nume + " " + prenume + " - " + specializare;

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

        buttonListaPacienti = new JToggleButton("Lista Pacienti");
        panelTop.add(buttonListaPacienti);
        buttonListaPacienti.setLayout(null);
        buttonListaPacienti.setVisible(true);
        buttonListaPacienti.setFont( new Font("Arial", Font.BOLD, 12));
        buttonListaPacienti.setBounds(10, 20, 191, 30);
        buttonListaPacienti.setFocusPainted(false);
        buttonListaPacienti.setRolloverEnabled(false);

        buttonListaPacienti.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!buttonListaPacienti.isSelected())
                {
                    clearPanelBot();
                }
                else
                {
                    afisarePanelBotListaPacienti();
                    buttonAdaugaFisa.setSelected(false);
                    buttonModificaFisa.setSelected(false);
                    buttonMarcareProgramare.setSelected(false);
                }
            }
        });

        buttonAdaugaFisa = new JToggleButton("Adauga Fisa");
        panelTop.add(buttonAdaugaFisa);
        buttonAdaugaFisa.setLayout(null);
        buttonAdaugaFisa.setVisible(true);
        buttonAdaugaFisa.setFont( new Font("Arial", Font.BOLD, 12));
        buttonAdaugaFisa.setBounds(201, 20, 191, 30);
        buttonAdaugaFisa.setFocusPainted(false);
        buttonAdaugaFisa.setRolloverEnabled(false);

        buttonAdaugaFisa.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!buttonAdaugaFisa.isSelected())
                {
                    clearPanelBot();
                }
                else
                {
                    afisarePanelBotAdaugaFisa();
                    buttonListaPacienti.setSelected(false);
                    buttonModificaFisa.setSelected(false);
                    buttonMarcareProgramare.setSelected(false);
                }

            }
        });

        buttonModificaFisa = new JToggleButton("Modifica Fisa");
        panelTop.add(buttonModificaFisa);
        buttonModificaFisa.setLayout(null);
        buttonModificaFisa.setVisible(true);
        buttonModificaFisa.setFont( new Font("Arial", Font.BOLD, 12));
        buttonModificaFisa.setBounds(392, 20, 191, 30);
        buttonModificaFisa.setFocusPainted(false);
        buttonModificaFisa.setRolloverEnabled(false);

        buttonModificaFisa.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!buttonModificaFisa.isSelected())
                {
                    clearPanelBot();
                }
                else
                {
                    afisarePanelBotModificaFisa();
                    buttonListaPacienti.setSelected(false);
                    buttonAdaugaFisa.setSelected(false);
                    buttonMarcareProgramare.setSelected(false);
                }

            }
        });

        buttonMarcareProgramare = new JToggleButton("Marcheaza Programare");
        panelTop.add(buttonMarcareProgramare);
        buttonMarcareProgramare.setLayout(null);
        buttonMarcareProgramare.setVisible(true);
        buttonMarcareProgramare.setFont( new Font("Arial", Font.BOLD, 12));
        buttonMarcareProgramare.setBounds(583, 20, 191, 30);
        buttonMarcareProgramare.setFocusPainted(false);
        buttonMarcareProgramare.setRolloverEnabled(false);

        buttonMarcareProgramare.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!buttonMarcareProgramare.isSelected())
                {
                    clearPanelBot();
                }
                else
                {
                    afisarePanelBotMarcheazaProgramare();
                    buttonListaPacienti.setSelected(false);
                    buttonAdaugaFisa.setSelected(false);
                    buttonModificaFisa.setSelected(false);
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

    private static void afisarePanelBotListaPacienti()
    {
        clearPanelBot();
        List<Pacient> listaPacienti = new ArrayList<>();

        listaPacienti = MedicRepository.getListaPacienti(medicPagina);

        JTable table = new JTable(listaPacienti.size(), 8);

        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setHeaderValue("Index");
        table.getColumnModel().getColumn(1).setHeaderValue("Nume");
        table.getColumnModel().getColumn(2).setHeaderValue("Prenume");
        table.getColumnModel().getColumn(3).setHeaderValue("Nastere");
        table.getColumnModel().getColumn(4).setHeaderValue("Sex");
        table.getColumnModel().getColumn(5).setHeaderValue("Telefon");
        table.getColumnModel().getColumn(6).setHeaderValue("Email");
        table.getColumnModel().getColumn(7).setHeaderValue("Adresa");

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 12, 762, 430);
        panelBot.add(scroll);

        for (int i = 0; i < listaPacienti.size(); i++)
        {
            table.setValueAt(listaPacienti.get(i).getIndex(), i, 0);
            table.setValueAt(listaPacienti.get(i).getNume(), i, 1);
            table.setValueAt(listaPacienti.get(i).getPrenume(), i, 2);
            table.setValueAt(listaPacienti.get(i).getNastere(), i, 3);
            table.setValueAt(listaPacienti.get(i).getSex(), i, 4);
            table.setValueAt(listaPacienti.get(i).getTelefon(), i, 5);
            table.setValueAt(listaPacienti.get(i).getEmail(), i, 6);
            table.setValueAt(listaPacienti.get(i).getAdresa(), i, 7);
        }
    }

    private static void afisarePanelBotAdaugaFisa()
    {
        clearPanelBot();

        JLabel labelPacient = new JLabel("Pacient :");
        panelBot.add(labelPacient);
        labelPacient.setLayout(null);
        labelPacient.setVisible(true);
        labelPacient.setBounds(12, 22, 100, 25);
        labelPacient.setFont(new Font("Arial", Font.BOLD, 13));
        labelPacient.setForeground(new Color(250, 250, 250));

        JComboBox<Pacient> boxPacient = new JComboBox<Pacient>();
        boxPacient.setBounds(110, 22, 300, 25 );
        boxPacient.setVisible(true);
        panelBot.add(boxPacient);

        // Sets text for drop down list items that are objects/not strings
        boxPacient.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Pacient)
                {
                    Pacient pacient = (Pacient) value;
                    String itemText = String.format("(%02d) - %s %s - %s", pacient.getIndex(), pacient.getNume(), pacient.getPrenume(), pacient.getAdresa());
                    setText(itemText);
                }
                return this;
            }
        });

        List<Pacient> listaPacienti;
        listaPacienti = PacientRepository.getListaPacienti();

        for (int i = 0; i < listaPacienti.size(); i++)
        {
            boxPacient.addItem(listaPacienti.get(i));
        }

        JLabel labelDiagnostic = new JLabel("Diagnostic :");
        panelBot.add(labelDiagnostic);
        labelDiagnostic.setLayout(null);
        labelDiagnostic.setVisible(true);
        labelDiagnostic.setBounds(12, 57, 100, 25);
        labelDiagnostic.setFont(new Font("Arial", Font.BOLD, 13));
        labelDiagnostic.setForeground(new Color(250, 250, 250));

        JTextField fieldDiagnostic = new JTextField(10);
        panelBot.add(fieldDiagnostic);
        fieldDiagnostic.setLayout(null);
        fieldDiagnostic.setVisible(true);
        fieldDiagnostic.setBounds(110, 57, 300, 25);

        JLabel labelTratament = new JLabel("Tratament :");
        panelBot.add(labelTratament);
        labelTratament.setLayout(null);
        labelTratament.setVisible(true);
        labelTratament.setBounds(12, 94, 100, 25);
        labelTratament.setFont(new Font("Arial", Font.BOLD, 13));
        labelTratament.setForeground(new Color(250, 250, 250));

        JTextField fieldTratament = new JTextField(10);
        panelBot.add(fieldTratament);
        fieldTratament.setLayout(null);
        fieldTratament.setVisible(true);
        fieldTratament.setBounds(110, 94, 600, 25);

        JButton buttonAdauga = new JButton("Adauga Fisa");
        panelBot.add(buttonAdauga);
        buttonAdauga.setLayout(null);
        buttonAdauga.setVisible(true);
        buttonAdauga.setFont( new Font("Arial", Font.BOLD, 11));
        buttonAdauga.setBounds(110, 131, 120, 25);
        buttonAdauga.setFocusPainted(false);

        JLabel labelStatus = new JLabel("");
        panelBot.add(labelStatus);
        labelStatus.setLayout(null);
        labelStatus.setVisible(false);
        labelStatus.setBounds(240, 131, 200, 25);
        labelStatus.setFont(new Font("Arial", Font.BOLD, 12));
        labelStatus.setForeground(new Color(250, 250, 250));

        buttonAdauga.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FisaPacient fisaPacient = new FisaPacient();
                fisaPacient.setPacient((Pacient) boxPacient.getSelectedItem());
                fisaPacient.setMedic(medicPagina);
                fisaPacient.setDiagnostic(fieldDiagnostic.getText());
                fisaPacient.setTratament(fieldTratament.getText());

                if(!FisaPacientRepository.isInformationComplete(fisaPacient))
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constAdaugaFisaMesajIncomplete);
                    labelStatus.setVisible(true);
                    return;
                }

                if(!FisaPacientRepository.addFisaPacient(fisaPacient))
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constAdaugaFisaMesajFail);
                    labelStatus.setVisible(true);
                    return;
                }

                labelStatus.setForeground(constColorGreen);
                labelStatus.setText(constAdaugaFisaMesajSuccess);
                labelStatus.setVisible(true);

                fieldDiagnostic.setText("");
                fieldTratament.setText("");
            }
        });
    }

    private static void afisarePanelBotModificaFisa()
    {
        clearPanelBot();

        JLabel labelPacient = new JLabel("Pacient :");
        panelBot.add(labelPacient);
        labelPacient.setLayout(null);
        labelPacient.setVisible(true);
        labelPacient.setBounds(12, 22, 100, 25);
        labelPacient.setFont(new Font("Arial", Font.BOLD, 13));
        labelPacient.setForeground(new Color(250, 250, 250));

        JComboBox<Pacient> boxPacient = new JComboBox<Pacient>();
        boxPacient.setBounds(110, 22, 300, 25 );
        boxPacient.setVisible(true);
        panelBot.add(boxPacient);

        // Sets text for drop down list items that are objects/not strings
        boxPacient.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Pacient)
                {
                    Pacient pacient = (Pacient) value;
                    String itemText = String.format("(%02d) - %s %s - %s", pacient.getIndex(), pacient.getNume(), pacient.getPrenume(), pacient.getAdresa());
                    setText(itemText);
                }
                return this;
            }
        });

        List<Pacient> listaPacienti;
        listaPacienti = MedicRepository.getListaPacienti(medicPagina);

        for (int i = 0; i < listaPacienti.size(); i++)
        {
            boxPacient.addItem(listaPacienti.get(i));
        }

        boxPacient.setSelectedIndex(-1);

        JLabel labelFisa = new JLabel("Fisa :");
        panelBot.add(labelFisa);
        labelFisa.setLayout(null);
        labelFisa.setVisible(true);
        labelFisa.setBounds(12, 57, 100, 25);
        labelFisa.setFont(new Font("Arial", Font.BOLD, 13));
        labelFisa.setForeground(new Color(250, 250, 250));

        JComboBox<FisaPacient> boxFisa = new JComboBox<FisaPacient>();
        boxFisa.setBounds(110, 57, 300, 25 );
        boxFisa.setVisible(true);
        panelBot.add(boxFisa);

        // Sets text for drop down list items that are objects/not strings
        boxFisa.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof FisaPacient)
                {
                    FisaPacient fisaPacient = (FisaPacient) value;
                    String itemText = String.format("(%02d) - %s %s - %s", fisaPacient.getIndex(), fisaPacient.getPacient().getNume(),
                            fisaPacient.getPacient().getPrenume(), fisaPacient.getDiagnostic());
                    setText(itemText);
                }
                return this;
            }
        });

        boxPacient.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boxFisa.removeAllItems();
                Pacient pacient = (Pacient) boxPacient.getSelectedItem();

                List<FisaPacient> listaFisaPacient = FisaPacientRepository.getLisaFisaPacientMedic(pacient, medicPagina);

                for (int i = 0; i < listaFisaPacient.size(); i++)
                {
                    boxFisa.addItem(listaFisaPacient.get(i));
                }
                boxFisa.setSelectedIndex(-1);
            }
        });

        JLabel labelDiagnostic = new JLabel("Diagnostic :");
        panelBot.add(labelDiagnostic);
        labelDiagnostic.setLayout(null);
        labelDiagnostic.setVisible(true);
        labelDiagnostic.setBounds(12, 94, 100, 25);
        labelDiagnostic.setFont(new Font("Arial", Font.BOLD, 13));
        labelDiagnostic.setForeground(new Color(250, 250, 250));

        JTextField fieldDiagnostic = new JTextField(10);
        panelBot.add(fieldDiagnostic);
        fieldDiagnostic.setLayout(null);
        fieldDiagnostic.setVisible(true);
        fieldDiagnostic.setBounds(110, 94, 300, 25);

        JLabel labelTratament = new JLabel("Tratament :");
        panelBot.add(labelTratament);
        labelTratament.setLayout(null);
        labelTratament.setVisible(true);
        labelTratament.setBounds(12, 131, 100, 25);
        labelTratament.setFont(new Font("Arial", Font.BOLD, 13));
        labelTratament.setForeground(new Color(250, 250, 250));

        JTextField fieldTratament = new JTextField(10);
        panelBot.add(fieldTratament);
        fieldTratament.setLayout(null);
        fieldTratament.setVisible(true);
        fieldTratament.setBounds(110, 131, 600, 25);

        boxFisa.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FisaPacient fisaPacient = (FisaPacient) boxFisa.getSelectedItem();

                if (fisaPacient != null)
                {
                    fieldDiagnostic.setText(fisaPacient.getDiagnostic());
                    fieldTratament.setText(fisaPacient.getTratament());
                    return;
                }

                fieldDiagnostic.setText("");
                fieldTratament.setText("");
            }
        });

        // Trigger action listener on page load
        //boxFisa.setSelectedIndex(0);

        JButton buttonModifica = new JButton("Modifica Fisa");
        panelBot.add(buttonModifica);
        buttonModifica.setLayout(null);
        buttonModifica.setVisible(true);
        buttonModifica.setFont( new Font("Arial", Font.BOLD, 11));
        buttonModifica.setBounds(110, 168, 120, 25);
        buttonModifica.setFocusPainted(false);

        JLabel labelStatus = new JLabel("");
        panelBot.add(labelStatus);
        labelStatus.setLayout(null);
        labelStatus.setVisible(false);
        labelStatus.setBounds(240, 168, 200, 25);
        labelStatus.setFont(new Font("Arial", Font.BOLD, 12));
        labelStatus.setForeground(new Color(250, 250, 250));

        buttonModifica.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FisaPacient fisaPacient = (FisaPacient) boxFisa.getSelectedItem();

                if (fisaPacient == null)
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constModificaFisaMesajIncomplete);
                    labelStatus.setVisible(true);
                    return;
                }

                fisaPacient.setDiagnostic(fieldDiagnostic.getText());
                fisaPacient.setTratament(fieldTratament.getText());

                if(!FisaPacientRepository.isInformationComplete(fisaPacient))
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constModificaFisaMesajIncomplete);
                    labelStatus.setVisible(true);
                    return;
                }

                if(!FisaPacientRepository.modificaFisaPacient(fisaPacient))
                {
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setText(constModificaFisaMesajFail);
                    labelStatus.setVisible(true);
                    return;
                }

                labelStatus.setForeground(constColorGreen);
                labelStatus.setText(constModificaFisaMesajSuccess);
                labelStatus.setVisible(true);

                boxFisa.updateUI();
            }
        });
    }

    public static void afisarePanelBotMarcheazaProgramare()
    {
        clearPanelBot();

        JLabel labelData = new JLabel("Data :");
        panelBot.add(labelData);
        labelData.setLayout(null);
        labelData.setVisible(true);
        labelData.setBounds(12, 22, 100, 25);
        labelData.setFont(new Font("Arial", Font.BOLD, 13));
        labelData.setForeground(new Color(250, 250, 250));

        JComboBox<String> boxDataDay = new JComboBox<String>();
        boxDataDay.setBounds(110, 22, 70, 25 );
        boxDataDay.setVisible(true);
        panelBot.add(boxDataDay);

        for (int i = 1; i <= constDayMax; i++)
        {
            boxDataDay.addItem(String.format("%02d", i));
        }

        JComboBox<String> boxDataMonth = new JComboBox<String>();
        boxDataMonth.setBounds(182, 22, 70, 25 );
        boxDataMonth.setVisible(true);
        panelBot.add(boxDataMonth);

        for (int i = 1; i <= constMonthMax; i++)
        {
            boxDataMonth.addItem(String.format("%02d", i));
        }

        JComboBox<String> boxDataYear = new JComboBox<String>();
        boxDataYear.setBounds(254, 22, 100, 25 );
        boxDataYear.setVisible(true);
        panelBot.add(boxDataYear);

        for (int i = constYearMax; i >= constYearMin; i--)
        {
            boxDataYear.addItem(String.valueOf(i));
        }

        LocalDate currentDay = LocalDate.now();
        boxDataDay.setSelectedIndex(currentDay.getDayOfMonth() - 1);
        boxDataMonth.setSelectedIndex(currentDay.getMonthValue() - 1);
        boxDataYear.setSelectedIndex(currentDay.getYear() - constYearMax);

        JButton buttonCauta = new JButton("Cauta");
        panelBot.add(buttonCauta);
        buttonCauta.setLayout(null);
        buttonCauta.setVisible(true);
        buttonCauta.setFont( new Font("Arial", Font.BOLD, 11));
        buttonCauta.setBounds(356, 22, 100, 25);
        buttonCauta.setFocusPainted(false);

        JLabel labelProgramare = new JLabel("Programare :");
        panelBot.add(labelProgramare);
        labelProgramare.setLayout(null);
        labelProgramare.setVisible(true);
        labelProgramare.setBounds(12, 57, 100, 25);
        labelProgramare.setFont(new Font("Arial", Font.BOLD, 13));
        labelProgramare.setForeground(new Color(250, 250, 250));

        JComboBox<Programare> boxProgramare = new JComboBox<Programare>();
        boxProgramare.setBounds(110, 57, 244, 25 );
        boxProgramare.setVisible(true);
        panelBot.add(boxProgramare);

        // Sets text for drop down list items that are objects/not strings
        boxProgramare.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Programare)
                {
                    Programare programare = (Programare) value;
                    Pacient pacient = programare.getPacient();

                    String data = String.valueOf(programare.getData());
                    String ora = data.substring(11, 16);

                    String itemText = String.format("%s - %s %s", ora, pacient.getNume(), pacient.getPrenume());
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
                Date data = Date.valueOf(boxDataYear.getSelectedItem() + "-" + boxDataMonth.getSelectedItem() + "-" + boxDataDay.getSelectedItem());

                List<Programare> listaProgramari = MedicRepository.getListaProgramariInCurs(medicPagina, data);

                boxProgramare.removeAllItems();

                for (int i = 0; i < listaProgramari.size(); i++)
                {
                    boxProgramare.addItem(listaProgramari.get(i));
                }
            }
        });

        JCheckBox cboxRealizat = new JCheckBox("Realizat");
        cboxRealizat.setLayout(null);
        cboxRealizat.setVisible(true);
        cboxRealizat.setBounds(357, 57, 98,25);
        cboxRealizat.setEnabled(false);
        cboxRealizat.setFocusPainted(false);
        panelBot.add(cboxRealizat);

        boxProgramare.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Programare programare = (Programare) boxProgramare.getSelectedItem();
                if (programare == null)
                {
                    cboxRealizat.setEnabled(false);
                    return;
                }
                cboxRealizat.setEnabled(true);
                cboxRealizat.setSelected(programare.isRealizata());
            }
        });

        JButton buttonUpdate = new JButton("Update");
        panelBot.add(buttonUpdate);
        buttonUpdate.setLayout(null);
        buttonUpdate.setVisible(true);
        buttonUpdate.setFont( new Font("Arial", Font.BOLD, 12));
        buttonUpdate.setBounds(110, 94, 138, 25);
        buttonUpdate.setFocusPainted(false);

        JLabel labelStatus = new JLabel("");
        panelBot.add(labelStatus);
        labelStatus.setLayout(null);
        labelStatus.setVisible(false);
        labelStatus.setBounds(260, 94, 200, 25);
        labelStatus.setFont(new Font("Arial", Font.BOLD, 12));
        labelStatus.setForeground(new Color(250, 250, 250));


        buttonUpdate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Programare programare = (Programare) boxProgramare.getSelectedItem();

                if (programare == null)
                {
                    labelStatus.setText(constModificaProgramareFail);
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setVisible(true);
                    return;
                }

                programare.setRealizata(cboxRealizat.isSelected());

                if (!ProgramareRepository.modificaProgramare(programare))
                {
                    labelStatus.setText(constModificaProgramareFail);
                    labelStatus.setForeground(constColorRed);
                    labelStatus.setVisible(true);
                    return;
                }

                labelStatus.setText(constModificaProgramareSuccess);
                labelStatus.setForeground(constColorGreen);
                labelStatus.setVisible(true);
            }
        });

    }

    private static void clearPanelBot()
    {
        panelBot.removeAll();
        panelBot.revalidate();
        panelBot.repaint();
    }
}
