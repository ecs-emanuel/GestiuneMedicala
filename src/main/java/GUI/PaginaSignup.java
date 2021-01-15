package GUI;

import Auth.Signup;
import Entities.Medic;
import Entities.Pacient;
import Entities.Specializare;
import Entities.User;
import Repository.SpecializareRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class PaginaSignup
{
    private static final String constMesajSuccess = "Inregistrare reusita";
    private static final String constMesajFailExists = "Username already in use";
    private static final String constMesajFailIncomplete = "Information incomplete";
    private static final String constMesajFailGeneral = "Inregistrare nereusita";

    private static final Color constColorRed = new Color(230, 70, 50);
    private static final Color constColorGreen = new Color(5, 170, 50);

    private static final int constDayMax = 31;
    private static final int constMonthMax = 12;
    private static final int constYearMin = 1900;
    private static final int constYearMax = 2021;

    private static JFrame frame;

    private static JLabel labelUser;
    private static JLabel labelPass;
    private static JLabel labelAccess;
    private static JLabel labelSpecializare;

    private static JLabel labelNume;
    private static JLabel labelPrenume;
    private static JLabel labelNastere;
    private static JLabel labelSex;

    private static JLabel labelAdresa;
    private static JLabel labelEmail;
    private static JLabel labelTelefon;
    private static JLabel labelStatus;

    private static JTextField fieldUser;
    private static JTextField fieldPass;
    private static JComboBox<String> boxAccess;
    private static JComboBox<Specializare> boxSpecializare;

    private static JTextField fieldNume;
    private static JTextField fieldPrenume;
    private static JComboBox<String> boxNastereDay;
    private static JComboBox<String> boxNastereMonth;
    private static JComboBox<String> boxNastereYear;
    private static JComboBox<String> boxSex;

    private static JTextField fieldAdresa;
    private static JTextField fieldEmail;
    private static JTextField fieldTelefon;

    private static JButton buttonSignup;
    private static JButton buttonSignin;

    private static List<Specializare> listaSpecializari = new ArrayList<>();

    public static void afisarePaginaSignup()
    {
        frame = new JFrame();
        frame.setTitle("Sign up");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBounds(0, 0, 330, 520);
        frame.setLocationRelativeTo(null);

        labelUser = new JLabel("Username :");
        frame.add(labelUser);
        labelUser.setLayout(null);
        labelUser.setVisible(true);
        labelUser.setBounds(10, 25, 90, 25);

        fieldUser = new JTextField(10);
        frame.add(fieldUser);
        fieldUser.setLayout(null);
        fieldUser.setVisible(true);
        fieldUser.setBounds(100, 25, 170, 25);

        labelPass = new JLabel("Password :");
        frame.add(labelPass);
        labelPass.setLayout(null);
        labelPass.setVisible(true);
        labelPass.setBounds(10, 55, 90, 25);

        fieldPass = new JTextField(10);
        frame.add(fieldPass);
        fieldPass.setLayout(null);
        fieldPass.setVisible(true);
        fieldPass.setBounds(100, 55, 170, 25);

        labelAccess = new JLabel("Atributii :");
        frame.add(labelAccess);
        labelAccess.setLayout(null);
        labelAccess.setVisible(true);
        labelAccess.setBounds(10, 85, 90, 25);

        boxAccess = new JComboBox<String>();
        boxAccess.setVisible(true);
        boxAccess.setBounds(100, 85, 170, 25);
        boxAccess.addItem(User.constUserAccessPacient);
        boxAccess.addItem(User.constUserAccessMedic);
        frame.add(boxAccess);

        // Enable/disable drop down list for specializare when account type does/doesn't require it
        boxAccess.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String access = boxAccess.getSelectedItem().toString();
                boxSpecializare.setEnabled(access.equals(User.constUserAccessMedic));
            }
        });

        labelSpecializare = new JLabel("Specializare :");
        frame.add(labelSpecializare);
        labelSpecializare.setLayout(null);
        labelSpecializare.setVisible(true);
        labelSpecializare.setBounds(10, 115, 90, 25);

        boxSpecializare = new JComboBox<Specializare>();
        boxSpecializare.setVisible(true);
        boxSpecializare.setEnabled(false);
        boxSpecializare.setBounds(100, 115, 170, 25);
        frame.add(boxSpecializare);

        // Sets text for drop down list items that are objects/not strings
        boxSpecializare.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Specializare)
                {
                    Specializare specializare = (Specializare) value;
                    setText(specializare.getDenumire());
                }
                return this;
            }
        });

        listaSpecializari = SpecializareRepository.getListaSpecializari();

        for (int i = 0; i < listaSpecializari.size(); i++)
        {
            boxSpecializare.addItem(listaSpecializari.get(i));
        }

        labelNume = new JLabel("Nume :");
        frame.add(labelNume);
        labelNume.setLayout(null);
        labelNume.setVisible(true);
        labelNume.setBounds(10, 165, 90, 25);

        fieldNume = new JTextField(10);
        frame.add(fieldNume);
        fieldNume.setLayout(null);
        fieldNume.setVisible(true);
        fieldNume.setBounds(100, 165, 170, 25);

        labelPrenume = new JLabel("Prenume :");
        frame.add(labelPrenume);
        labelPrenume.setLayout(null);
        labelPrenume.setVisible(true);
        labelPrenume.setBounds(10, 195, 90, 25);

        fieldPrenume = new JTextField(10);
        frame.add(fieldPrenume);
        fieldPrenume.setLayout(null);
        fieldPrenume.setVisible(true);
        fieldPrenume.setBounds(100, 195, 170, 25);

        labelNastere = new JLabel("Nastere :");
        frame.add(labelNastere);
        labelNastere.setLayout(null);
        labelNastere.setVisible(true);
        labelNastere.setBounds(10, 225, 90, 25);

        boxNastereDay = new JComboBox<String>();
        boxNastereDay.setVisible(true);
        boxNastereDay.setBounds(100, 225, 45, 25);
        frame.add(boxNastereDay);

        for (int i = 1; i <= constDayMax; i++)
        {
            boxNastereDay.addItem(String.format("%02d", i));
        }

        boxNastereMonth = new JComboBox<String>();
        boxNastereMonth.setVisible(true);
        boxNastereMonth.setBounds(145, 225, 45, 25);
        frame.add(boxNastereMonth);

        for (int i = 1; i <= constMonthMax; i++)
        {
            boxNastereMonth.addItem(String.format("%02d", i));
        }

        boxNastereYear = new JComboBox<String>();
        boxNastereYear.setVisible(true);
        boxNastereYear.setBounds(190, 225, 80, 25);
        frame.add(boxNastereYear);

        for (int i = constYearMax; i >= constYearMin; i--)
        {
            boxNastereYear.addItem(String.valueOf(i));
        }

        labelSex = new JLabel("Sex :");
        frame.add(labelSex);
        labelSex.setLayout(null);
        labelSex.setVisible(true);
        labelSex.setBounds(10, 255, 90, 25);

        boxSex = new JComboBox<String>();
        boxSex.setVisible(true);
        boxSex.setBounds(100, 255, 170, 25);
        frame.add(boxSex);
        boxSex.addItem(Pacient.constSexMasculin);
        boxSex.addItem(Pacient.constSexFeminin);

        labelTelefon = new JLabel("Telefon :");
        frame.add(labelTelefon);
        labelTelefon.setLayout(null);
        labelTelefon.setVisible(true);
        labelTelefon.setBounds(10, 305, 90, 25);

        fieldTelefon = new JTextField(10);
        frame.add(fieldTelefon);
        fieldTelefon.setLayout(null);
        fieldTelefon.setVisible(true);
        fieldTelefon.setBounds(100, 305, 170, 25);

        labelEmail = new JLabel("Email :");
        frame.add(labelEmail);
        labelEmail.setLayout(null);
        labelEmail.setVisible(true);
        labelEmail.setBounds(10, 335, 90, 25);

        fieldEmail = new JTextField(10);
        frame.add(fieldEmail);
        fieldEmail.setLayout(null);
        fieldEmail.setVisible(true);
        fieldEmail.setBounds(100, 335, 170, 25);

        labelAdresa = new JLabel("Adresa :");
        frame.add(labelAdresa);
        labelAdresa.setLayout(null);
        labelAdresa.setVisible(true);
        labelAdresa.setBounds(10, 365, 90, 25);

        fieldAdresa = new JTextField(10);
        frame.add(fieldAdresa);
        fieldAdresa.setLayout(null);
        fieldAdresa.setVisible(true);
        fieldAdresa.setBounds(100, 365, 170, 25);

        buttonSignup = new JButton("Sign up");
        frame.add(buttonSignup);
        buttonSignup.setLayout(null);
        buttonSignup.setVisible(true);
        buttonSignup.setBounds(100, 400, 85, 25);

        buttonSignin = new JButton("Sign in");
        frame.add(buttonSignin);
        buttonSignin.setLayout(null);
        buttonSignin.setVisible(true);
        buttonSignin.setBounds(185, 400, 85, 25);

        labelStatus = new JLabel();
        frame.add(labelStatus);
        labelStatus.setLayout(null);
        labelStatus.setVisible(false);
        labelStatus.setBounds(100, 430, 170, 25);
        labelStatus.setHorizontalAlignment(SwingConstants.CENTER);


        buttonSignup.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TrySingingUp();
            }
        });

        buttonSignin.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                PaginaLogin.afisarePaginaLogin();
            }
        });
    }

    public static void TrySingingUp()
    {
        User user = formatUser();

        // Determine account type
        switch(user.getAccess())
        {
            case User.constUserAccessMedic:
                Medic medic = formatMedic();
                afiseazaMesajStatus(Signup.TrySignUp(user, medic, null));
                break;

            case User.constUserAccessPacient:
                Pacient pacient = formatPacient();
                afiseazaMesajStatus(Signup.TrySignUp(user, null, pacient));
                break;
        }
    }

    private static User formatUser()
    {
        User user = new User();
        user.setUsername(fieldUser.getText());
        user.setPassword(fieldPass.getText());
        user.setAccess(boxAccess.getSelectedItem().toString());

        return user;
    }

    private static Medic formatMedic()
    {
        Medic medic = new Medic();
        medic.setNume(fieldNume.getText());
        medic.setPrenume(fieldPrenume.getText());
        medic.setSpecializare((Specializare)boxSpecializare.getSelectedItem());
        Date nastere = Date.valueOf(boxNastereYear.getSelectedItem() + "-" + boxNastereMonth.getSelectedItem() + "-" + boxNastereDay.getSelectedItem());

        medic.setNastere(nastere);
        medic.setSex(boxSex.getSelectedItem().toString());
        medic.setTelefon(fieldTelefon.getText());
        medic.setEmail(fieldEmail.getText());
        medic.setAdresa(fieldAdresa.getText());

        return medic;
    }

    private static Pacient formatPacient()
    {
        Pacient pacient = new Pacient();
        pacient.setNume(fieldNume.getText());
        pacient.setPrenume(fieldPrenume.getText());
        Date nastere = Date.valueOf(boxNastereYear.getSelectedItem() + "-" + boxNastereMonth.getSelectedItem() + "-" + boxNastereDay.getSelectedItem());

        pacient.setNastere(nastere);
        pacient.setSex(boxSex.getSelectedItem().toString());
        pacient.setTelefon(fieldTelefon.getText());
        pacient.setEmail(fieldEmail.getText());
        pacient.setAdresa(fieldAdresa.getText());

        return pacient;
    }


    private static void afiseazaMesajStatus(int result)
    {
        String mesaj = "";
        Color color = constColorRed;

        switch(result)
        {
            case Signup.constResultSuccess:
                mesaj = constMesajSuccess;
                color = constColorGreen;
                break;

            case Signup.constResultFailExists:
                mesaj = constMesajFailExists;
                //color = constColorRed;
                break;

            case Signup.constResultFailIncomplete:
                mesaj = constMesajFailIncomplete;
                //color = constColorRed;
                break;

            case Signup.constResultFailGeneral:
                mesaj = constMesajFailGeneral;
                //color = constColorRed;
                break;
        }

        labelStatus.setText(mesaj);
        labelStatus.setForeground(color);
        labelStatus.setVisible(true);
    }
}
