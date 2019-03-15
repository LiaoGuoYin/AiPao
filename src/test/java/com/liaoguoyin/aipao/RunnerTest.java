package com.liaoguoyin.aipao;

import com.liaoguoyin.aipao.api.AipaoClinet;

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

        System.out.println("按任意键退出...");

    }
}
