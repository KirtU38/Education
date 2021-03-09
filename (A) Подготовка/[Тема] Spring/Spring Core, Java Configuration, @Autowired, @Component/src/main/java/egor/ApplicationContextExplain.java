public class ApplicationContextExplain {}

// ApplicationContext - это один из типов IoC-контейнера.
// AppliactionContext это Интерфейс и является Наследником Интерфейса BeanFactory.

// IoC Container - берет на себя контроль над Бинами, описанными в Конфигурации.
// Создание, конфигурация, внедрение зависимостей, жизненный цикл.
// Конфигурации(через XML или аннотации) в Spring-приложении.

// Application context может быть сконфигурирован через XML файл, через Java код, либо через то и
// другое




// BeanFactory - второй тип IoC-Контейнера.
// BeanFactory - является основным интерфейсом(не в понимании interface) доступа к Spring-контейнеру
// и его Бинам.

// BeanFactory -> AplicationContext implements BeanFactory




// ApplicationContext vs BeanFactory

// BeanFactory является более "низкоуровневым" и "легким" Контейнером, что может подойти для
// разработки на платформу с ограниченными ресурсами.

// Всё что может BeanFactory, это читать описание Бинов из Конфигурации и получать к ним доступ
// через getBean(). По сути это всё.

// ApplicationContext может делать то же, что и BeanFactory, однако является более "навороченной"
// версией BeanFactory.
// Он может например считывать текст из .property файлов и тд.
