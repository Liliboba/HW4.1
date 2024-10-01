import java.util.Random;

public class HW2 {
    public static void main(String[] args) {
        System.out.println(permission(5, 0));                          // Номер 3
        System.out.println(permission(15, -10));
        System.out.println(permission(25, 10));
        System.out.println(permission(35, -20));
        System.out.println(permission(45, 30));

        System.out.println(permission(generateRandomAge(), 10));           // Номер 5

    }

    public static String permission(int age , int temperature){                       // Номер 1

        if((age >= 20 && age <= 45) && (temperature >= -20 && temperature <= 30)){    // Номер 2
            return "Можно идти гулять";
        } else if(age <= 20 && (temperature >= 0 && temperature <= 28)){
            return "Можно идти гулять";
        } else if(age > 45 && (temperature >= -10 && temperature <= 25)){
            return "Можно идти гулять";
        } else {
            return "Оставайтесь дома";
        }

    }

    public static int generateRandomAge(){                                             // Номер 4
        Random random = new Random();
        return random.nextInt(101);
    }
}
