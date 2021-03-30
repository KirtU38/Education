package ru;

public class Main {}
// HTTP - это протокол передачи данных между Клиентом и Сервером.
// HTTP - это стандарт, по которому формируются и интерпретируются сообщения Запроса и Ответа.

// HTTP (HyperText Transfer Protocol) - протокол передачи Гипер текста(HTML)

// HTTP  port: 80
// HTTPS port: 443

// 1) Клиент делает request с каким-то методом наприер GET или POST.
// 2) Сервер получает request и в зависимости от метода этот request попадает в разные end-points
// Контроллера.
// 3) request обрабатывается Контроллером и посылает response в виде HTML, JSON и тд.
// Client ->     request (GET)     -> Server
// Client <- response (HTML, JSON) <- Server

// Request line - строка запроса, в ней указывается метод(GET, POST), потом адрес на который
// делается запрос(здесь "/", значит это корневой адрес), а потом версия HTTP.

// Headers - заголовки.

// Body - после заголовков через пробел идёт "тело" запроса, в нем передается нужная информация на
// сервер.
// В body может быть:
// 1) Ничего, пустое. Если мы ничего не отправляем на сервер, допустим метод GET.
// 2) Параметры запроса.
// 3) Данные (например JSON).
// 4) Файлы (допустим загружаем картинку на сервер, меняем в профиле в ВК допустим).

// Request

// GET / HTTP/1.1                <- Request line
// Host: skillbox.ru
// Connection: keep-alive
// Pragma: no-cache
// Cache-Control: no-cache
// Upgrade-Insecure-Requests: 1
// User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_3)       <- Headers
// Accept: text/html,application/xhtml+xml,application/xml;q=0.9
// Sec-Fetch-Site: same-origin
// Sec-Fetch-Mode: navigate
// Sec-Fetch-User: ?1
// Sec-Fetch-Dest: document
// Referer: https://www.google.com/
// Accept-Encoding: gzip, deflate, br
// Accept-Language: ru-RU,ru;q=0.9

// Здесь body через пробел             <- Body

// Response

// HTTP/1.1 200 OK         <- Response line
// Server: QRATOR
// Date: Tue, 30 Mar 2021 14:10:41 GMT
// Content-Type: text/html; charset=utf-8
// Transfer-Encoding: chunked
// Connection: keep-alive
// Keep-Alive: timeout=15
// ETag: "260b4-dgu39MbhWFk6aPtmAkW8yMDYdXE"        <- Headers
// Accept-Ranges: none
// Vary: Accept-Encoding
// Content-Encoding: gzip
// X-Frame-Options: SAMEORIGIN
// X-XSS-Protection: 1; mode=block
// X-Content-Type-Options: nosniff

// *Какой-то HTML файл*               <- Body




// URL encoding
// Система дресации рассчитана только на Латинские ASCII символы, поэтому Русские буквы на самом
// деле выглядят примерно так %D0%84, это называется URL encoding.

// URL encoding - преобразование НЕ ASCII символов в ASCII символы.

// Пример:
// Если в Google ввести поиск "джава", то он выдаст:
// https://www.google.com/search?q=джава

// Однако если скопировать этот текст и вставить, то получится вот так, это реальный текст,
// просто браузер произвел URL decoding чтобы выглядело красиво:
// https://www.google.com/search?q=%D0%B4%D0%B6%D0%B0%D0%B2%D0%B0
