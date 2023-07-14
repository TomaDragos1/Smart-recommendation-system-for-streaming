package Commands;

import Homework.DataBase;
import Homework.ProiectPOO;
import Homework.Stream;
import Homework.User;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FacadeCommand {
    //parsate the file
    public Invoker createCommands(String[] args) {
        Invoker invoker = new Invoker();
        String path = "";
        path = "src/main/resources/";
        path += args[3];
        try {
            List<String> line = Files.readAllLines(Paths.get(path));

            //here i have a factory for commands that will return
            //the command needed
            line.forEach(currentLine -> {
                String[] data = currentLine.split(" ");
                Command newCommand = new CommandsFactory().createCommand(data);

                //put the command in a list
                invoker.addCommand(newCommand);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return invoker;
    }
}
