public class HW3 {
    public static void main(String[] args) {
            double[] array = {1.5, 2.3, -3.4, 4.5, -2.2, 3.1, 6.7, -5.4, -1.1, 10.9, -4.6, 7.3, 2.7, -6.8, 5.9};

            boolean foundFirstNegative = false;
            double sumPositives = 0;
            int countPositives = 0;

            for (double num : array) {
                if (num < 0 && !foundFirstNegative) {
                    foundFirstNegative = true;
                } else if (foundFirstNegative && num > 0) {
                    sumPositives += num;
                    countPositives++;
                }
            }

            double average = countPositives > 0 ? sumPositives / countPositives : 0;

            System.out.println("Среднее арифметическое положительных чисел после первого отрицательного: " + average);
    }

}
