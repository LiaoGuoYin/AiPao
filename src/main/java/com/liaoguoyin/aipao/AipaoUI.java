package com.liaoguoyin.aipao;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AipaoUI extends JFrame {
    private JTextField InputIMEICode;
    private JTextArea result;
    private String IMEICode;
    private static Boolean[] flags = {true, false, false, false};// BTns 是否可见
    private AipaoClinet client;
    private JButton check, login, getBasicInfo, running;

    AipaoUI() {
        setTitle("Aipao Client - LGY 太强了");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        InputIMEICode = new JTextField("Please insert your IMEICode here.");
        InputIMEICode.setColumns(30);
        InputIMEICode.setPreferredSize(new Dimension(100, 50));
        InputIMEICode.setHorizontalAlignment(JTextField.CENTER);
        panel.add(InputIMEICode);

        result = new JTextArea("Waiting for your operation..\n", 19, 38);
        result.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(result);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(jScrollPane, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        contentPane.add(panel2, BorderLayout.SOUTH);
//
//        ButtonGroup runPattern = new ButtonGroup();
//        JRadioButton singleRun = new JRadioButton("单人");
//        JRadioButton multiRun = new JRadioButton("多人");
//        runPattern.add(singleRun);
//        runPattern.add(multiRun);
//        panel2.add(singleRun, BorderLayout.WEST);
//        panel2.add(multiRun,BorderLayout.EAST);

        check = new JButton("Check IMEICode");
        panel2.add(check);

        check.addActionListener(e -> {
            IMEICode = InputIMEICode.getText();
            client = new AipaoClinet(IMEICode);

            try {
                client.login();
//                client.running();
//                client.uploadRecord();
                result.append("有效，IMEICode: " + IMEICode + "\n");
                flags[1] = true;
                flags[0] = false;
                freshBtns();
            } catch (Exception ex) {
                result.append("IMEICode 无效\n");
            }
        });

        login = new JButton("Login");
        login.setEnabled(false);
        panel2.add(login);

        login.addActionListener(e -> {
            try {
                client.login();
                flags[2] = true;
                freshBtns();
                result.append(client.output.toString() + "\n");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        getBasicInfo = new JButton("GetInfo");
        getBasicInfo.setEnabled(false);
        panel2.add(getBasicInfo);

        getBasicInfo.addActionListener(e -> {
            try {
                client.getBasicInfo();
                result.append(client.output.toString() + "\n");
                flags[3] = true;
                freshBtns();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        running = new JButton("Submit");
        running.setEnabled(false);
        panel2.add(running);

        running.addActionListener(e -> {
            try {
                client.running();
                client.uploadRecord();
                result.append(client.output.toString() + "\nSuccess");
                flags = new Boolean[]{true, false, false, false};// 重置 flags
                freshBtns();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        setSize(500, 500);

        JButton runAll = new JButton("All");
        panel2.add(runAll);
        runAll.addActionListener(e -> {
            try {
                IMEICode = InputIMEICode.getText();
                client = new AipaoClinet(IMEICode);
                client.login();
                result.append(client.output.toString() + "\n");
                client.getBasicInfo();
                result.append(client.output.toString() + "\n");
                client.running();
                result.append(client.output.toString() + "\n");
                client.uploadRecord();
                result.append(client.output.toString() + "\n Success\n");
            } catch (IOException ex) {
                result.append("IMEICode 错误");
                ex.printStackTrace();
            }

        });

    }

    public static void main(String[] args) {
        AipaoUI frame = new AipaoUI();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void freshBtns() {
        check.setEnabled(flags[0]);
        login.setEnabled(flags[1]);
        getBasicInfo.setEnabled(flags[2]);
        running.setEnabled(flags[3]);
    }
}
