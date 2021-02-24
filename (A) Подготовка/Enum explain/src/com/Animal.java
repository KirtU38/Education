package com;

// Задаём животных и перевод этих животных на Русский
// Сам по себе Обьект Класса Animal создать НЕЛЬЗЯ, Animal a = new Animal() - не сработает
public enum Animal {

    // DOG то же самое, что new Dog("Собака")
    DOG("Собака"),
    CAT("Кошка"),
    FROG("Лягушка");

    // Можно сделать и public, тогда к ней будет доступ прям с Animal.DOG.translation
    // Но сейчас работаем через Getter
    private final String translation;

    // Конструктор означает, что наши Enum`ы нужно будет указывать с параметрами, типа DOG("Собака")
    // Конструктор может быть только private
    private Animal(String translation) {
        this.translation = translation;
    }

    public static void printAnimal(Animal animal) {

        switch (animal) {
            case DOG:
                System.out.println("Dog");
                break;
            case CAT:
                System.out.println("Cat");
                break;
            case FROG:
                System.out.println("Frog");
                break;
            default:
                System.out.println("Not an animal");
        }
    }

    // Можно так
    public void printTranslation() {
        System.out.println(translation);
    }

    // Можно sout через Getter
    public String getTranslation() {
        return translation;
    }

    // Можно toString сделать чтобы он перевод печатал
    @Override
    public String toString() {
        return translation;
    }
}
