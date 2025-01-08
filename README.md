# Задание Coroutines

В этом репозитории находится задание по теме Coroutines.
Каждое из заданий выполняется в отдельном фрагменте главного экрана.

## Задание 1. Таймер

![Таймер](/readme/timer.png)

В этом задании вам нужно сделать таймер при помощи `coroutines`.
В коде [TimerFragment.kt](app/src/main/kotlin/ru/otus/coroutineshomework/ui/timer/TimerFragment.kt) есть функции `startTimer` и `stopTimer` для 
запуска и останова таймера.
Напишите код корутины, которая будет увеличивать счетчик раз в несколько миллисекунд (по вашему выбору) и обновлять значение переменной `time`.
Используйте scope, привязанный ко view фрагмента.

## Задание 2. Login

![Login](/readme/login.png)

В этом задании вам нужно сделать форму логина при помощи `coroutines`.
Состояние [LoginFragment.kt](app/src/main/kotlin/ru/otus/coroutineshomework/ui/login/LoginFragment.kt) определяется в [LoginViewModel.kt](app/src/main/kotlin/ru/otus/coroutineshomework/ui/login/LoginViewModel.kt) 
при помощи свойства `state` и может принимать одно из следующих значений [LoginViewState](app/src/main/kotlin/ru/otus/coroutineshomework/ui/login/LoginViewState.kt):

- `LoginViewState.Login` - форма входа. Опционально содержит ошибку входа
- `LoginViewState.LoggingIn` - процесс входа
- `LoginViewState.Content` - контент после входа
- `LoginViewState.LoggingOut` - процесс выхода

Реализуйте функции `login` и `logout` в коде [LoginViewModel.kt](app/src/main/kotlin/ru/otus/coroutineshomework/ui/login/LoginViewModel.kt).
Требования:

- Используйте экземпляр [LoginApi](app/src/main/kotlin/ru/otus/coroutineshomework/ui/login/LoginApi.kt) для запуска операций входа и выхода.
- Обратите внимание, что если запустить сетевую операцию на основном потоке, то приложение выдаст ошибку.
- Используйте scope, привязанный к viewModel.
- При входе и выходе показывайте индикатор загрузки, переключая состояние на `LoggingIn` и `LoggingOut` соответственно.
- При успешном входе переключайте состояние на `Content`.
- При ошибке входа переключайте состояние на `Login` и передавайте ошибку в состоянии.

## Задание 3. Speed-test

![Speed-test](/readme/speedtest.png)

В этом задании вам нужно сделать эмулятор speed-test при помощи `coroutines`.
Мы будем тренироваться запускать несколько корутин одновременно и усреднять их время выполнения.
В коде [NetworkViewModel.kt](app/src/main/kotlin/ru/otus/coroutineshomework/ui/network/NetworkViewModel.kt) определена функция `emulateBlockingNetworkRequest`, 
которая эмулирует сетевой запрос. Функция возвращает время выполнения запроса в миллисекундах или ошибку.
Вам нужно реализовать метод модели `startTest`, который запускает несколько запросов одновременно и усредняет время выполнения.

- Используйте scope, привязанный к viewModel.
- Запускайте несколько параллельных корутин.
- Отфильтруйте удачные результаты.
- Усредните время выполнения запросов.
- Результат поместите в переменную `_result`.
- На время выполнения операции, показывайте индикатор загрузки, устанавливая значение `_running` в `true`.