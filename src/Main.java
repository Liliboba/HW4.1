import java.util.Random;

public class Main {
    public static int bossHealth = 7000;//я увеличил количесво Hp боссас чтобы видеть результат получше
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {280, 270, 240, 1000, 200, 500, 150}; // Добавлены Golem, Medic, Lucky, Witcher, Thor
    public static int[] heroesDamage = {20, 15, 10, 5, 0, 10, 25}; // Уроны Golem, Medic (0), Lucky, Witcher, Thor
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Golem", "Medic", "Lucky", "Witcher", "Thor"};
    public static boolean bossStunned = false; // Переменная для оглушения босса
    public static boolean witcherSacrificeUsed = false; // Проверка, использовал ли Witcher свою способность
    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameOver()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        if (!bossStunned) {
            bossAttacks();
        } else {
            System.out.println("Boss is stunned and skips this round!");
            bossStunned = false; // Босс пропускает только один раунд
        }
        heroesAttack();
        medicHeals();
        printStatistics();
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomInd = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomInd];
    }

    public static void heroesAttack() {
        Random random = new Random();
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];

                if (i == 6 && random.nextBoolean()) { // Thor оглушает босса
                    bossStunned = true;
                    System.out.println("Thor stunned the boss!");
                }

                if (heroesAttackType[i].equals(bossDefence)) {
                    int coefficient = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage *= coefficient;
                    System.out.println("Critical damage: " + damage);
                }

                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth -= damage;
                }
            }
        }
    }

    public static void bossAttacks() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                int damage = bossDamage;

                if (i != 3 && heroesHealth[3] > 0) { // 3 - это индекс Golem
                    damage -= bossDamage / 5;
                    heroesHealth[3] -= bossDamage / 5;
                    if (heroesHealth[3] < 0) {
                        heroesHealth[3] = 0;
                    }
                }

                if (i == 4 && new Random().nextBoolean()) {  // Lucky уклоняется от атаки
                    System.out.println("Lucky dodged the attack!");
                    continue;
                }

                if (heroesHealth[i] - damage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] -= damage;
                }
            }
        }

        if (!witcherSacrificeUsed && heroesHealth[5] <= 0) { // Witcher жертвует собой
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] <= 0 && i != 5) {
                    heroesHealth[i] = heroesHealth[5];
                    heroesHealth[5] = 0;
                    witcherSacrificeUsed = true;
                    System.out.println("Witcher sacrificed himself to revive " + heroesAttackType[i]);
                    break;
                }
            }
        }
    }

    public static void medicHeals() {
        if (heroesHealth[4] <= 0) {
            return; // Если медик мертв, он не лечит
        }
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i != 4 && heroesHealth[i] > 0 && heroesHealth[i] < 100) { // Лечит только одного героя с низким здоровьем
                heroesHealth[i] += 25; // Лечение на 30 единиц здоровья
                System.out.println("Medic healed " + heroesAttackType[i] + " for 25 health.");
                break;
            }
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ------------");
        System.out.println("BOSS health: " + bossHealth + " damage: " + bossDamage +
                " defence: " + (bossDefence == null ? "No Defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + " damage: " + heroesDamage[i]);
        }
    }
}