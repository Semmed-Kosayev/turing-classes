package az.edu.turing.module02.part01.lesson01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerApp {
    public static void main(String[] args) {
        List<Player> players = getPlayers();
        printList(players);

        players.sort(
                Comparator.comparing(Player::getScore, Comparator.reverseOrder())
                        .thenComparing(Player::isFemale, Comparator.reverseOrder())
                        .thenComparing(Player::getAge)
                        .thenComparing(Player::getName, Comparator.reverseOrder())
                        .thenComparing(Player::getId)
        );

        printList(players);
    }

    public static void printList(List<Player> players) {
        players.forEach(System.out::println);
        System.out.println();
    }

    public static List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();

        Player semmed = new Player(1, 19, "Semmed", 70.01, false);
        Player behruz = new Player(2, 25, "Behruz", 76.4, false);
        Player aysel = new Player(3, 23, "Aysel", 80.01, true);
        Player leyla = new Player(4, 19, "Leyla", 96.70, true);
        Player turqut = new Player(5, 23, "Turqut", 80.01, false);

        players.add(aysel);
        players.add(behruz);
        players.add(turqut);
        players.add(semmed);
        players.add(leyla);

        return players;
    }
}
