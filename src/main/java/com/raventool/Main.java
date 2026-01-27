package com.raventool;

import com.raventool.engine.cli.commands.Raven;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Raven()).execute(args);
        System.exit(exitCode);
    }
}