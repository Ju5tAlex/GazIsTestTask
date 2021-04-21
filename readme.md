# Приложение Users Api
Тестовое приложение, которое предоставляет небольшой набор api
 для получения информации о пользователях.
### Библиотеки
- Lombok
- Fasterxml
- Junit
- Mockito
- Согласно заданию бибилиотека spring не используется.
### База данных
В проекте использовалась h2 database. Взаимодействие с базой
осуществляется посредством jdbc с sql запросами.
### Запуск проекта
Программа запускается с помощью maven посредством выполнения команды
`mvn exec:java`
### Функционал
Приложение предоставляет 4 точки доступа по локальному адресу `localhost:8000`:
- GET запрос `users/finduser?username=имя_пользователя` -
 поиск пользователя по УЗ
- PATCH запрос `/users/changeuserlastname?username=имя_пользователя&lastname=фамилия` -
 изменение фамилии пользователя с указанной УЗ
- GET запрос `users/getallsortedbyage` - получение отсортированного списка
 пользователей по возрасту в порядке возрастания
- GET запрос `users/phonebook` - получения телефонного справочника пользователей
вида "Фамилия" - "Номер телефона". При совпадении фамилии в поле "номер телефона"
добавляется ещё один номер телефона.
### Примеры запросов:
`http://localhost:8000/users/finduser?username=rxihubi1988`
`http://localhost:8000/users/changeuserlastname?username=rxihubi1988&lastname=Hellington`
`http://localhost:8000/users/getallsortedbyage`
`http://localhost:8000/users/phonebook`


