import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PaginaLogin
{
    private static JFrame frame;
    private static JLabel labelUser;
    private static JLabel labelPass;
    private static JLabel labelStatus;
    private static JTextField fieldUser;
    private static JPasswordField fieldPass;
    private static JButton buttonSignin;
    private static JButton buttonSignup;

    public static void main(String[] args)
    {
        afisarePaginaLogin();
    }

    public static void afisarePaginaLogin()
    {
        frame = new JFrame();
        frame.setTitle("Login");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBounds(0, 0, 300, 200);
        frame.setLocationRelativeTo(null);

        labelUser = new JLabel("Username :");
        frame.add(labelUser);
        labelUser.setLayout(null);
        labelUser.setVisible(true);
        labelUser.setBounds(10, 25, 70, 25);

        fieldUser = new JTextField(10);
        frame.add(fieldUser);
        fieldUser.setLayout(null);
        fieldUser.setVisible(true);
        fieldUser.setBounds(90, 25, 170, 25);

        labelPass = new JLabel("Password :");
        frame.add(labelPass);
        labelPass.setLayout(null);
        labelPass.setVisible(true);
        labelPass.setBounds(10, 55, 70, 25);

        fieldPass = new JPasswordField(10);
        frame.add(fieldPass);
        fieldPass.setLayout(null);
        fieldPass.setVisible(true);
        fieldPass.setBounds(90, 55, 170, 25);

        buttonSignin = new JButton("Sign in");
        frame.add(buttonSignin);
        buttonSignin.setLayout(null);
        buttonSignin.setVisible(true);
        buttonSignin.setBounds(90, 90, 85, 25);

        buttonSignup = new JButton("Sign up");
        frame.add(buttonSignup);
        buttonSignup.setLayout(null);
        buttonSignup.setVisible(true);
        buttonSignup.setBounds(175, 90, 85, 25);

        labelStatus = new JLabel("Credentiale incorecte");
        frame.add(labelStatus);
        labelStatus.setLayout(null);
        labelStatus.setVisible(false);
        labelStatus.setBounds(90, 120, 170, 25);
        labelStatus.setHorizontalAlignment(SwingConstants.CENTER);
        labelStatus.setForeground(new Color(230, 100, 70));

        buttonSignin.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TryLoggingIn();
            }
        });

        buttonSignup.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                PaginaSignup.afisarePaginaSignup();
            }
        });
    }

    private static void TryLoggingIn()
    {
        User user = new User();
        user.setUsername(fieldUser.getText());
        user.setPassword(String.valueOf(fieldPass.getPassword()));

        if(Login.TryLogin(user))
        {
            frame.dispose();
            return;
        }

        labelStatus.setVisible(true);
    }
}
