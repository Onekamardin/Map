import java.util.*;

public class DeliveryService {
    private Map<Address, Integer> costPerAddress = new HashMap<>();
    private int totalCost = 0;
    private Set<String> deliveredCountries = new HashSet<>();

    public void initialize() {
        costPerAddress.put(new Address("Россия", "Казань"), 200);
        costPerAddress.put(new Address("Россия", "Москва"), 180);
        costPerAddress.put(new Address("Россия", "Санкт-Петербург"), 190);
        costPerAddress.put(new Address("Казахстан", "Алматы"), 300);
        costPerAddress.put(new Address("Беларусь", "Минск"), 250);
    }

    public void processOrder() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nЗаполнение нового заказа.");
            System.out.print("Введите страну (или 'end' для завершения): ");
            String country = scanner.nextLine();

            if (country.equalsIgnoreCase("end")) {
                System.out.println("Работа сервиса завершена.");
                break;
            }

            System.out.print("Введите город: ");
            String city = scanner.nextLine();

            System.out.print("Введите вес (кг): ");
            double weight;
            try {
                weight = Double.parseDouble(scanner.nextLine());
                if (weight <= 0) {
                    System.out.println("Вес должен быть положительным числом.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод веса. Пожалуйста, введите число.");
                continue;
            }

            Address address = new Address(country, city);

            if (!costPerAddress.containsKey(address)) {
                System.out.println("Доставки по этому адресу нет");
                continue;
            }

            int pricePerKg = costPerAddress.get(address);
            int deliveryCost = (int) (pricePerKg * weight);

            totalCost += deliveryCost;
            deliveredCountries.add(country); // Добавляем страну в набор уникальных

            System.out.println("Стоимость доставки составит: " + deliveryCost + " руб.");
            System.out.println("Общая стоимость всех доставок: " + totalCost + " руб.");
            System.out.println("Количество уникальных стран, куда были оформлены доставки: " + deliveredCountries.size());
        }
    }

    public static void main(String[] args) {
        DeliveryService service = new DeliveryService();
        service.initialize();
        service.processOrder();
    }
}
