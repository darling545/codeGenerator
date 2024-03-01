package com.dong.cli.example;

import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class Login implements Callable<Integer> {

    @Option(names = {"-u", "--user"}, required = true, description = "username")
    String user;

    @Option(names = {"-p", "--password"}, arity = "0..1",interactive = true, description = "password")
    String password;

    @Option(names ={"-cp", "--check-password"},arity = "0..1", interactive = true, description = "check password")
    String checkPassword;

    @Override
    public Integer call() throws Exception {
        System.out.println("password = " + password);
        System.out.println("checkPassword = " + checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u","user123","-p","-cp");
    }
}
