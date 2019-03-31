package com.liaoguoyin.aipao;

import com.liaoguoyin.aipao.api.AipaoClinet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RunnerTest {
    public static void main(String[] args) throws IOException {
        AipaoClinet clinet = new AipaoClinet();
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入IMEICode: ");
        clinet.imeiLogin(scanner.nextLine());//4cda88bcc04a4b8ab5fe9cfe39bec6ef
        clinet.getBasicInfo();
        clinet.running();
        clinet.uploadRecord();

        System.out.println("Success, 点击查看所有记录: http://sportsapp.aipao.me/Manage/UserDomain_SNSP_Records.aspx/MyResutls?userId=" + clinet.UserId);
        System.out.println("正在输出本次跑步记录，请按任意键退出...");
        File file = new File("output.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(clinet.output.toString());
        fileWriter.close();
        scanner.nextLine();
    }
}
