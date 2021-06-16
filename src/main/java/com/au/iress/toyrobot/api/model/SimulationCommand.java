package com.au.iress.toyrobot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimulationCommand {String command;

    public SimulationCommand(@JsonProperty("command") String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
