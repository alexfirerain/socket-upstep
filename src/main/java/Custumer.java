import java.util.Objects;

public class Custumer {
    private String clientName;
    private int age;
    private String payment;

    public String getClientName() {
        return clientName;
    }

    public int getAge() {
        return age;
    }

    public String getPayment() {
        return payment;
    }

    public Rang getCategory() {
        return category;
    }

    private Rang category;

    public void setAge(int age) {
        this.age = age;
        category = defineRang(age);
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Custumer(String clientName) {
        this.clientName = clientName;
    }

    public Custumer(String clientName, int age, String payment) {
        this.clientName = clientName;
        this.age = age;
        this.payment = payment;
        category = defineRang(age);
    }

    private Rang defineRang(int age) {
        if (age > 70) return Rang.SENIOR;
        if (age > 18) return Rang.ADULT;
        return Rang.KID;
    }

    @Override
    public String toString() {
        return ("""
                *******************************************
                * КАРТОЧКА ПОСЕТИТЕЛЯ *********************
                * Комната %s
                * Имя: %s
                * Возраст: %d
                * Взнос: %s
                *******************************************
                """)
                .formatted(category.toString(),
                        clientName,
                        age,
                        payment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Custumer custumer = (Custumer) o;
        return age == custumer.age &&
                clientName.equals(custumer.clientName) &&
                payment.equals(custumer.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientName, age, payment);
    }
}
