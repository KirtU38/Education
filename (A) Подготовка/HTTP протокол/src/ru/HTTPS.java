package ru;

public class HTTPS {}
// HTTPS - протокол передачи Сообщений между КЛиентом и Сервером, но зашифрованный(безопасный).
// HTTPS - Hyper Text Transfer Protocol Secure

// Процесс:
// 1) Клиент отправляет Запрос Хосту Серверу.
// 2) Сервер в Ответ отправляет свой Сертификат.
// 3) Клиент отправляет Сертификат на проверку подлинности в Центр Сертификации, который этот
// Сертификат выдал Хосту.
// 4) Клиент отправляет свой Сертификат Серверу.
// 5) Сервер теперь обращается в Центр Сертификации и проверяет подлинность Сертификата Клиента.
// 6) Соединение установленно

// Как шифруются и читаются сообщения:
// Шифровка и дешифровка происходит и использованием Пар ключей.
// Публичный для шифрования и Приватный для того, чтобы расшифровать.
// 1) Клиент шифрует сообщение своим Публичным ключом и отправляет.
// 2) Сервер расшифровывает сообщение своим Приватным ключом.
// 3) Сервер шифрует Ответ Публичным ключом КЛИЕНТА.
// 4) Клиент получает сообщение и расшифровывает его СВОИМ Приватным ключом.

// Клиент: Приватный Клиента, Публичный Клиента.
// Сервер: Приватный Сервера, Публичный Клиента.

