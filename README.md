# Сервер с рюшечками
Имитация обращения клиента к серверу по адресу *http://netology.homework:8111*

Файлы в **/src/main/java/** таковы:

__Server.java__ – сервер, запускается первым, отвечает на соединение и проводит регистрацию пользователя

__Client.java__ – клиент, запускается вторым, обеспечивает ввод и отправку данных пользователя

__Logger.java__ – служебный класс для протоколирования работы сервера

__Customer.java__ – шаблон данных регистрации пользователя

__Rang.java__ – служебное перечисление возрастных категорий