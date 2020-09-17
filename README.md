# Test_Java_Junior_Developer

Инструкция по запуску:
1. Загрузить репозиторий: 
	1.1 Используя SSH: git clone git@github.com:grig-ar/Test_Java_Junior_Developer.git
	1.2 Используя HTTPS: git clone https://github.com/grig-ar/Test_Java_Junior_Developer.git
	1.3 Скачать zip архив
2. Собрать проект, используя Maven: mvn clean compile assembly:single
3. Восстановить базу данных из файла ShopDB
4. Поместить файл конфигурации hikari.properties в директорию с исполняемым jar файлом
5. Указать необходимые данные (название базы данных, имя пользователя, пароль) в файле конфигурации
6. Пример запуска программы для поиска в базе данных: java -jar Task-1.0-jar-with-dependencies.jar search inputSearch.json outputSearch.json
7. Пример запуска программы для сбора статистики в базе данных: java -jar Task-1.0-jar-with-dependencies.jar stat inputStat.json outputStat.json