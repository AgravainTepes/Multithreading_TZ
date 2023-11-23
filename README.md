
<h2 align="center">Тестовое задание Junior Java Developer</h2>
<hr>
<h3>Общее описание</h3>
<h5>Необходимо разработать приложение в соответствии с изложенными ниже требованиями.</h5>
<h2>Требования</h2>
<h3>Общие требования</h3>
<h5>Архитектура - Java SE 8.0 (или выше), использование библиотек и фреймворков на усмотрение исполнителя.
<p>Должна быть система логирования (на основе готового решения, например Log4j).</p> Приложение должно логировать в файл любые действия, приводящие к изменению данных. Приложение должно корректно обрабатывать и логировать ошибки.
</h5>
<h3>Структура данных</h3>
<h5>В приложении должна быть сущность Account (счет) содержащая поля:
ID (строковое) - идентификатор счета
Money (целочисленное) - сумма средств на счете
</h5>
<h3>Функциональные требования</h3>
<h5><p>При запуске приложение должно создать четыре (или более) экземпляров объекта Account со случайными значениями ID и значениями money равным 10000.
</p><p>В приложении запускается несколько (не менее двух) независимых потоков. Потоки должны просыпаться каждые 1000-2000 мс. Время на которое засыпает поток выбирается случайно при каждом исполнении.
Потоки должны выполнять перевод средств с одного счета на другой.</p> Сумма списания или зачисления определяется случайным образом. Поле money не должно становиться отрицательным, сумма money на всех счетах не должна меняться.
Решение должно быть масштабируемым по количеству счетов и потоков и обеспечивать возможность одновременного 
</h5>
<h3>Комментарий выполняющего:</h3>

<h4>Настройка параметров осуществляется с помощью конфигурационного файла src/main/resources/application.properties

threads.numbers - количество выполняющих "транзакции потоков"

accounts.numbers - количество участвующих в транзакциях аккаунтов 

transactions.numbers - конечное число транзакций

money.defaultValue - количество денег на счете при его создании </h4>

