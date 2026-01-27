package com.raventool.engine.cli.commands;

import com.raventool.engine.cli.subcommands.Run;
import picocli.CommandLine.Command;

@Command(
    name="raven",
    mixinStandardHelpOptions = true,
    version = "v0.1.0-SNAPSHOT",
    subcommands = {
        Run.class
    }
)
public class Raven implements Runnable{

    @Override
    public void run() {

    }
}
