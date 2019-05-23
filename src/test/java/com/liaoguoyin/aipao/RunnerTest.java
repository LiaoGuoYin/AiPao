package com.liaoguoyin.aipao;

import com.liaoguoyin.aipao.api.AipaoClinet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RunnerTest {
    public static void main(String[] args) {
        AipaoClinet clinet = new AipaoClinet();
        Scanner scanner = new Scanner(System.in);
        System.out.println("LGY 太强了，请输入IMEICode: ");

        try {
            clinet.imeiLogin(scanner.nextLine());
            clinet.getBasicInfo();
            clinet.running();
            clinet.uploadRecord();

            System.out.println("Success, 点击查看所有记录: http://sportsapp.aipao.me/Manage/UserDomain_SNSP_Records.aspx/MyResutls?userId=" + clinet.UserId);
            System.out.println("正在输出本次跑步记录，请按任意键退出...");
            File file = new File("output.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(clinet.output.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("输入输出异常，请检查脚本是否有读写权限");
        } catch (NullPointerException e) {
            System.out.println("空指针异常，可能是IMEICode失效");
        }

        scanner.nextLine();

    }
}
