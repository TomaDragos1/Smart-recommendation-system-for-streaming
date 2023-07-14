package Homework;

import Commands.FacadeCommand;
import Commands.Invoker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
        Main class where i initiate my database
        and then i call my commands
 */


public class ProiectPOO {

        public static void main(String[] args) {
            if(args == null) {
                System.out.println("Nothing to read here");
                return;
            }
            DataBase.resteDataBase();
            DataBase allData;

            //i do a facade to initiate the database

            Facade newFacade = new Facade();
            allData = newFacade.mainFacade(args);
            Invoker invoker;

            //facade for commands
            FacadeCommand newFacadeForCommands = new FacadeCommand();
            invoker = newFacadeForCommands.createCommands(args);

            //with the invoker i execute all the commands
            invoker.executeCommands();
        }
}
