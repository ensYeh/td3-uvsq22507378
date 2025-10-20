package fr.uvsq.cprog.collex;

public class QuitterCommand implements Command {
    @Override
    public Object execute(Dns dns) {
        return "Au revoir !";
    }
}