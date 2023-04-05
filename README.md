![GitHub last commit](https://img.shields.io/github/last-commit/maiow/reddit?logo=GitHub)

### Reddit client - project is using API for Reddit
Humblr for Reddit. Android Kotlin, Single activity mobile application project with: Clean, MVVM, Hilt DI, coroutines, OAuth2, OkHttp Interceptor, Retrofit, Moshi (including PolymorphicJsonAdapterFactory & ScalarsConverterFactory), DelegateAdapters, Onboarding with ViewPager2 & TabLayout.
Full support of Russian and English locales.

Check Readme file inside the project for description in English .

Onboarding, authorization through Reddit:

![onb-auth](https://user-images.githubusercontent.com/113892176/224336482-667c6668-2bd1-47c5-a922-54c0ed47e1a6.gif)
![auth](https://user-images.githubusercontent.com/113892176/224337012-08200147-1788-4ea3-a479-ce5d59ec4f2a.gif)

New/Popular Subreddits listings with Search function. Subscribe/unsunscribe, share link on subreddit. Open subreddit, upvote/downvote post, download, save to online collection.

![subreddits](https://user-images.githubusercontent.com/113892176/224339682-dd7053a3-05fb-4533-bc1d-2e59e1fa3468.gif)

Favorites screen: Subreddits - Posts and All - Saved switchers.

![favorites](https://user-images.githubusercontent.com/113892176/224341512-7c551d30-cae6-453a-b5de-89e521686ac1.gif)

My Profile screen: logged user's friends list, Clear saved posts, Logout.

![profile](https://user-images.githubusercontent.com/113892176/224345007-95747980-2069-4190-b3da-930d42867458.gif)

Click on any Reddit user -> User screen with his Posts. Making Friends:

![user](https://user-images.githubusercontent.com/113892176/224343901-2ba158f0-6f67-4880-be6e-03530c3d7049.gif)


### Скачал - поставь 🌟

### Для работы приложения необходимо:
- зарегистрировать в качестве разработчика на Reddit ваше собственное приложение (https://github.com/reddit-archive/reddit/wiki/OAuth2)
и вписать данные в файл AuthConst.kt:
- const val CLIENT_ID = "your-client-id"
- const val REDIRECT_URI = "your-redirect-uri"

~~~~~~~
Задание
Вам предстоит разработать мобильное приложение-аналог Reddit. 

Это приложение, которое позволяет зарегистрированным пользователям размещать ссылки на понравившуюся информацию в интернете, 
находить интересный контент и обсуждать его.

Полное описание API: https://www.reddit.com/dev/api

Прототип Figma

Сценарии использования:

Пользователь знакомится с основными функциями приложения на экране онбординга.
Онбординг отображается сразу при первом запуске приложения.

Авторизация

А) Экран авторизации отображается всегда следующим экраном после онбординга.
Б) Пользоваться приложением без авторизации нельзя.

Аутентификация: https://github.com/pratik98/Reddit-OAuth

Посмотреть вкладку «Сабреддиты»

Вверху строка поиска, переключатель «Новое/Популярное».
Предлагать возможность подписки прямо в списке сабреддитов.
По нажатию на сабреддит открывать описание сабреддита.


Посмотреть сабреддит

В сабреддите открывать сразу список комментариев.
При клике на комментарий открыть его.
Вверху окна зафиксировать название сабреддита и кнопку «Инфо», открывающую описание сабреддита.


Посмотреть описание сабреддита

Общая информация о сабреддите.
Кнопки:
«Подписка/отписка на сабреддит».
«Поделиться».


Посмотреть комментарии

Автор, время, текст комментария.
Кнопки:
«Сохранение».
«Локальная загрузка».
Кнопки для голосования.
Ниже список первой страницы сообщений для комментария, после него кнопка «Показать всё».
Кнопка ведёт на отдельную страницу сообщений с пагинацией.


Посмотреть профиль юзера

Открывает профиль любого юзера.
Краткая информация.
Кнопка «Зафрендиться».
Комментарии.


Посмотреть вкладку «Избранное»

Вверху два переключателя: «Сабреддиты»/«Комментарии»,  «Все»/«Сохранённые».
Ниже списки сабреддитов и комментариев.
Переходы при сохранённом:
Пытаться загрузить онлайн.
Если информация доступна, отображаем.
Нет ― пишем, что информация недоступна или удалена.
Пытаться загрузить локально. (//реализация не требуется)
Пишем, что информация недоступна.


Добавить в друзья

Можно добавить любого юзера.
Список друзей лежит в профиле.

Посмотреть вкладку «Профиль»

Кнопка разлогина.
Описание профиля.
Кнопка «Очистить сохранённые».
Список друзей. //открывается на одельном экране

