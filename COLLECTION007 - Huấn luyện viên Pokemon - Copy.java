import java.util.*;

class Pokemon {
    String name, element;
    int health;

    public Pokemon(String name, String element, int health) {
        this.name = name;
        this.element = element;
        this.health = health;
    }
}

class Trainer {
    String name;
    int numberOfBadges = 0;
    List<Pokemon> pokemons = new ArrayList<>();

    public Trainer(String name) {
        this.name = name;
    }

    public void addPokemon(Pokemon p) {
        pokemons.add(p);
    }

    public boolean hasElement(String element) {
        for (Pokemon p : pokemons) {
            if (p.element.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void damagePokemons() {
        pokemons.removeIf(p -> {
            p.health -= 10;
            return p.health <= 0;
        });
    }

    public int getPokemonCount() {
        return pokemons.size();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Trainer> trainers = new LinkedHashMap<>();

        // Nhập dữ liệu ban đầu cho đến khi gặp "Tournament"
        while (true) {
            String line = sc.nextLine();
            if (line.equals("Tournament")) break;

            String[] parts = line.split(" ");
            String trainerName = parts[0];
            String pokemonName = parts[1];
            String element = parts[2];
            int health = Integer.parseInt(parts[3]);

            trainers.putIfAbsent(trainerName, new Trainer(trainerName));
            trainers.get(trainerName).addPokemon(new Pokemon(pokemonName, element, health));
        }

        // Vòng thi đấu
        while (true) {
            String element = sc.nextLine();
            if (element.equals("End")) break;

            for (Trainer t : trainers.values()) {
                if (t.hasElement(element)) {
                    t.numberOfBadges++;
                } else {
                    t.damagePokemons();
                }
            }
        }

        // Sắp xếp và in kết quả
        List<Trainer> result = new ArrayList<>(trainers.values());
        result.sort((a, b) -> b.numberOfBadges - a.numberOfBadges);

        for (Trainer t : result) {
            System.out.println(t.name + " " + t.numberOfBadges + " " + t.getPokemonCount());
        }

        sc.close();
    }
}
