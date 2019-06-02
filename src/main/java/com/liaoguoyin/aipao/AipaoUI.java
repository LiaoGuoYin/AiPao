package com.liaoguoyin.aipao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AipaoUI extends JFrame {
    private JTextField InputIMEICode;
    private JTextArea result;
    private String IMEICode;

    AipaoUI() {
        setTitle("Aipao Client - LGY 太强了");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        InputIMEICode = new JTextField("Please insert your IMEICode here.");
        InputIMEICode.setColumns(30);
        InputIMEICode.setPreferredSize(new Dimension(100, 50));
        InputIMEICode.setHorizontalAlignment(JTextField.CENTER);
        panel.add(InputIMEICode);

        JPanel panel1 = new JPanel();
        contentPane.add(panel1, BorderLayout.CENTER);
        result = new JTextArea();
        result.setColumns(40);
        result.setLineWrap(true);
        result.setPreferredSize(new Dimension(200, 350));
        panel1.add(result);

        JPanel panel2 = new JPanel();
        contentPane.add(panel2, BorderLayout.SOUTH);
        JButton check = new JButton("Check IMEICode");
        panel2.add(check);

        check.addActionListener(e -> {
            IMEICode = InputIMEICode.getText();
            AipaoClinet client = new AipaoClinet(IMEICode);

            try {
                client.login();
                client.getBasicInfo();
                client.running();
                client.uploadRecord();
                result.setText(client.output.toString() + "Success");
            } catch (Exception ex) {
                result.setText("IMEICode 无效，请用 Android 重抓");
            }
        });

        JButton login = new JButton("Login");
        panel2.add(login);

        JButton getBacicInfo = new JButton("GetInfo");
        panel2.add(getBacicInfo);

        JButton running = new JButton("Running");
        running.setEnabled(false);
        panel2.add(running);

        setBounds(200, 200, 500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        AipaoUI frame = new AipaoUI();
        frame.setVisible(true);
    }
}
