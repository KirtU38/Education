package egor;

public class Annotations {
}
// @Component - Спринг создаст Бин из этого Класса, эту аннотацию Спринг ищет когда сканирует все наши Классы.
// Можно указать id типа @Component("beanObject"), если не указывать, то id Бина будет имя Класса с мал буквы.

// @Autowired - это и есть "Внедрение зависимостей", то есть мы лишь указываем Спрингу на Обьекте в конструкторе
// "Мне нужен этот Обьект(эта зависимость) для работы, Спринг - найди из Бинов подходящий, и внедри его мне".
// @Autowired можно использовать на Переменных, Сеттерах и Конструкторах.
// Даже есди мы используем @Autowired на private поле, и в Классе нет ни Сеттера ни Конструктора,
// то всё равно зависимость внедряется(из-за "рефлексии" или Reflecton API)
// @Autowired может внедрять не только Одну зависимость, а хоть сколько(MusicPlayerAutowiredTwoFields)

// Принцип работы @Autowired:
// 1) Спринг сканирует все директории и создает Бины из помеченных Классов
// 2) Спринг сканирует все созданные Бины и проверяет подходит ли хоть один Бин другому в качестве Зависимости
// (то есть проверяет если какой-нибудь Бин нужен при создании другого)
// 3) Если находится ТОЛЬКО ОДИН Бин, который подходит к Классу где указан Autowired,
// то он внедряется(MusicPlayerAutowiredSingle)
// 4) Если находятся несколько удовлетворяющих Бинов, то нужно использовать @Qualifier

// @Qualifier "уточнитель" - эта аннотация указывает четко на Бин, который должен быть внедрен, когда несколько Бинов
// подходят для внедрения одновременно(MusicPlayer)

