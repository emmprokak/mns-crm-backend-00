package com.unipi.mns.mnscrm00.process.lead_conversion;

import java.util.ArrayList;
import java.util.List;

public class LeadConversionInvoker {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
    }
}
