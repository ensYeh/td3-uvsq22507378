package fr.uvsq.cprog.collex;

public class Invoker {
    public void submit(Command command){
        command.execute();
    }
}
