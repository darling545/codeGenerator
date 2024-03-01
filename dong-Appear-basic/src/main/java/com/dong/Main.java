package com.dong;

import com.dong.cli.CommandExecutor;

public class Main {
    public static void main(String[] args) {
        args = new String[]{"generate","-l","-o","-a"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}
