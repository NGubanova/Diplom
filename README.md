# Дипломная работа. Сайт для компании Волоть.

Для выпускной работы я разрабатывала сайт для организации, занимающейся оптовой продажей медицинских расходных материалов. Для создания сайта я использовала стек технологий: Java, JavaScript, HTML5, CSS3, MySql. Так же я использовала Spring Framework и Bootstrap. 

Моей целью было разработать сайт, на котором пользователи могут ознакомиться с продукцией компании, узнать о компании, ее местораположении, оставить заявку на заказ, чтобы менеджер в последсвии мог связать и заключить договор.
Мной был реализован следующий функционал: 
* Регистрация нового пользователя по имени, электронной почте, телефону и паролю(Шифрование BCrypt 8). Установлена валидация на пустоту каждого поля, правильность ввода почты, номера телефона и пароля(латинский алфавит, не менее 6 символов, заглавные и строчные буквы, цифры и спец. символы);
![reg](https://sun9-23.userapi.com/impg/5Qw9s0WECtx0MeznOs5up3rQ3Y6jpI835Jd5Kw/DRMKMOrkyGs.jpg?size=1280x678&quality=96&sign=0eb9a6ebc837e6da9cfad6fee07544b3&type=album)
* Авторизация по почте и паролю;
![auth](https://sun9-58.userapi.com/impg/zi1BBzmhRytRXRQUjsigGb1F1aQuadK02lQZRg/9qPpV9tpqd0.jpg?size=1280x683&quality=96&sign=445198c7474e2049e7cf576984283029&type=album)
* Разграничение прав досупа по ролям;
* Добавление и удаление товара в корзину (Было реализовано с помощью кэширования);
![bag](https://sun9-76.userapi.com/impg/fPu3tD0Wts3-oTMNUZMqdzzIlQTGSLKwgYUrPA/Lyj3GFdRu1s.jpg?size=1280x678&quality=96&sign=679620f76f110ffd6aa47e5dd9ac0d3e&type=album)
* Добавление и удаление товара из избранного;
![favorite](https://sun9-72.userapi.com/impg/SjVdV-O0-YrfV0Rn8AOAc9eeOzAw8h5Xh4go2g/eTj8hbEeOpY.jpg?size=1919x1015&quality=96&sign=eba5094e6f1863ebcaf3cb91a514c5cc&type=album)
* Фильтрация и поиск по каталогу;
![catalog](https://sun9-47.userapi.com/impg/m8szkH1H4f6KDVSV-YijhQIxgXfbPTkjE6ehjQ/4VodEmLNfnc.jpg?size=1280x685&quality=96&sign=35924044a3b4a97ec1cd64ad8e72837b&type=album)
![filter](https://sun9-64.userapi.com/impg/nLvqpo0uDUKXD8O9mplPouw9nxztxBgxSISt9g/9rZyZZu-b4o.jpg?size=1280x676&quality=96&sign=98d46f46b4902b404405f0ade1ebdd8f&type=album)
* Офорление заявки на заказ(Отправление уведомления о заказе на почту, указанную в профиле);
![orders](https://sun1-29.userapi.com/impg/3K74ULlweku68iyaGk8EDKJovtdcRIuTTklcwg/IcCPcrqqFP8.jpg?size=1280x681&quality=96&sign=adb518662e32b7ea660908d059c63c3c&type=album)
![email](https://sun9-54.userapi.com/impg/Kfd3tCuDcQgxTRG5RsSI76VgR1wki1XidkECRQ/v5R8UeHWYiA.jpg?size=1060x388&quality=96&sign=db1ed18a6720a25d8dba6420e1ac47f1&type=album)
![]()
* Отмена заказа, в случае, если он еще не обработан;
* Оставить отзыв, после оформления заказа;
![feedback](https://sun9-78.userapi.com/impg/Mz5m0E8hw62Mq0GHI49pTYgIyYW2K3XK_tVMGQ/npbNNfcwYuo.jpg?size=1280x679&quality=96&sign=ad23cc1962fa5a7b77a128034acd5ee5&type=album)
* Изменение личных данных в профиле;
* Удаление профиля (Логическое удаление);
![profile](https://sun9-17.userapi.com/impg/IT4bde23RoCt7aifO-QmgO5zqIy7HoCe66i73Q/hHAGJCxJz6U.jpg?size=1280x678&quality=96&sign=8646c76859572b10ac4ae43227542071&type=album)
* Выход из аккаунта.


