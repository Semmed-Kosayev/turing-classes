package az.edu.turing.module02.part01.lesson01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerApp {
    public static void main(String[] args) {
        List<Player> players = getPlayers();
        printList(players);

        Collections.sort(players, (Player o1, Player o2) -> {
            if (o1.getScore() != o2.getScore()) return Double.compare(o2.getScore(), o1.getScore());
            else {
                if (o1.isFemale() != o2.isFemale()) return Boolean.compare(o2.isFemale(), o1.isFemale());
                else {
                    if (o1.getAge() != o2.getAge()) return Integer.compare(o1.getAge(), o2.getAge());
                    else {
                        if (!o1.getName().equals(o2.getName())) return o2.getName().compareTo(o1.getName());
                        else return Integer.compare(o1.getId(), o2.getId());
                    }

                }
            }
        });
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
