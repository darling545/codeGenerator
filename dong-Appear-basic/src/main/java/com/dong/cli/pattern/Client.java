package com.dong.cli.pattern;

public class Client {
    public static void main(String[] args) {
        Device tv = new Device("TV");
        Device app = new Device("APP");

        TurnOnCommand turnOnCommand = new TurnOnCommand(tv);
        TurnOffCommand turnOffCommand = new TurnOffCommand(app);

        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(turnOnCommand);
        remoteControl.buttonWasPressed();

        remoteControl.setCommand(turnOffCommand);
        remoteControl.buttonWasPressed();
    }
}
