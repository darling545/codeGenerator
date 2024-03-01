package com.dong;

import com.dong.cli.CommandExecutor;

public class Main {

    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        args = new String[]{"generate","-l"};
        commandExecutor.doExecute(args);
    }
}
