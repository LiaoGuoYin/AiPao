package com.liaoguoyin.aipao;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AipaoUI extends JFrame {
    private JTextField InputIMEICode;
    private JTextArea result;
    private String IMEICode;
    private static Boolean[] flags = {true, false};// Buttons 是否可见
    private AipaoClinet client;
    private JButton check, submitRecorder;

    private AipaoUI() {
        setTitle("Aipao Client - LGY 太强了");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        InputIMEICode = new JTextField("Please insert your IMEICode here");
        InputIMEICode.setColumns(30);
        InputIMEICode.setPreferredSize(new Dimension(100, 50));
        InputIMEICode.setHorizontalAlignment(JTextField.CENTER);
        panel.add(InputIMEICode);

        result = new JTextArea("Waiting for your operation..\n\n", 19, 38);
        result.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(result);
        contentPane.add(jScrollPane, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        contentPane.add(panel2, BorderLayout.SOUTH);
        check = new JButton("Check IMEICode");
        panel2.add(check);
        check.addActionListener(e -> {
            IMEICode = InputIMEICode.getText().trim();

            try {
                checkInput(IMEICode);
                client = new AipaoClinet(IMEICode);
                client.login();
                result.append("IMEICode 有效: " + IMEICode + "\n\n");

                client.getBasicInfo();
                result.append(client.output.toString() + "\n\n");

                flags[1] = true;
                flags[0] = false;
                freshBtns();
            }catch (NullPointerException e1) {
                result.append("IMEICode 已失效\n");
            }catch (RuntimeException e1) {
                result.append("IMEICode 格式错误，正确格式为：5ec0473d294347d7b69f062abf16d111\n\n");
            }catch (IOException ex) {
                result.append("Check Your Network..\n");
            }

        });

        submitRecorder = new JButton("Submit Recorder");
        freshBtns();
        panel2.add(submitRecorder);
        submitRecorder.addActionListener(e -> {
            try {
                client.running();
                client.uploadRecord();
                result.append(client.output.toString());
                result.append("\nClick here to query the recorder: http://sportsapp.aipao.me/Manage/UserDomain_SNSP_Records.aspx/MyResutls?userId=" + client.info.get("userId").toString() + "\n\nSuccess");

                // 重置 flags
                flags[0] = true;
                flags[1] = false;
                freshBtns();
            }catch (IOException ex) {
                result.append("IMEICode 失效\n\n");
            }
        });

        JButton reset = new JButton("Clear");
        panel2.add(reset);
        reset.addActionListener(e -> {
            InputIMEICode.setText("Please insert your IMEICode here");
            result.append("输入新的 IEMICode\n\n");
            flags[0] = true;
            flags[1] = false;
            freshBtns();
        });

    }

    public static void main(String[] args) {
        AipaoUI frame = new AipaoUI();
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void checkInput(String imeiCode) throws RuntimeException {
        String regEx = "^[a-zA-Z0-9]{32}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(imeiCode);
        if (!matcher.matches()) {
            throw new RuntimeException();
        }
    }

    private void freshBtns() {
        check.setEnabled(flags[0]);
        submitRecorder.setEnabled(flags[1]);
    }
}
