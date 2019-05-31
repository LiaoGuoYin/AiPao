package com.liaoguoyin.aipao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AipaoClientTest {
    public static void main(String[] args) {
        int pauseTime = 5000;// Sleep 5s when finishing the script.
        String imeiCode;
        Scanner scanner = new Scanner(System.in);
        System.out.println("LGY 太强了，请输入IMEICode: ");

        if (args.length > 0) {
            imeiCode = args[0];
            pauseTime = 0;
        } else {
            imeiCode = scanner.nextLine();
        }

        try {
            AipaoClinet clinet = new AipaoClinet(imeiCode);
            clinet.login();
            clinet.getBasicInfo();
            clinet.running();
            clinet.uploadRecord();

            System.out.println("Success: http://sportsapp.aipao.me/Manage/UserDomain_SNSP_Records.aspx/MyResutls?userId=" + clinet.info.get("userId").toString());
            System.out.println("OutPuting recorder, and will shutdown in 5s..");

            File file = new File("output.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(clinet.output.toString());
            fileWriter.close();
            Thread.sleep(pauseTime);

        } catch (IOException e) {
            System.out.println("输入输出异常，请检查脚本是否有读写权限");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println(imeiCode + " 空指针异常，可能是IMEICode失效");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
