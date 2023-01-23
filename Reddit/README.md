# study project using API for Reddit
Humblr for Reddit

Task was to develop Android mobile application - analog of Reddit. 

This application allows registered Reddit users to post links on the information they like 
in the Internet, find interesting content and discuss it.

Full API description: https://www.reddit.com/dev/api

UI made based on given Figma prototype.

Use cases:

New app User sees Onboarding.
Onboarding is displayed immediately on the first launch of the application.

Authorization:
a) Authorization screen is always displayed after onboarding.
b) Can't use application without authorization.
Authentication: https://github.com/pratik98/Reddit-OAuth

See "Subreddits":

Search bar at the top of the screen, the "New/Popular" switch is below.
Offer possibility of subscribing in the list of subreddits.
By clicking on the subreddit, open it's description.

See Subreddit:

Open list of subreddit' posts immediately in the subreddit.
Open the post if clicked.
Fix subreddit name and "Info" button, opens the description of the subreddit, at the top of the window.


See the subreddit description:
General info: # of subscribers, short description. 
Buttons:
"Subscribe"/"unsubscribe".
"Share".


See the posts:

Author, time, post text.
Buttons:
"Save".
"Vote up/down".
Ниже список первой страницы сообщений для комментария, после него кнопка «Показать всё». Кнопка ведёт на отдельную страницу сообщений с пагинацией.


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

See "Profile":

Logout button.
Profile description.
"Clear saved" button.
List of friends button, friends can be seen on separate screen.

