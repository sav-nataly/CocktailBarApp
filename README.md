# CocktailBarApp

**Реализовано:**

+ чтение списка коктейлей из файла и запись их в файл
+ добавление нового коктейля
+ просмотр карточки коктейля
+ отображение списка коктейлей

**Не реализовано:**

- редактирование коктейля
- шаринг
- контейнер для FAB
- некоторые несоответствия макету (особенно с отображением ингредиентов)
- ошибки для обязательных полей

**С чем были трудности:**
* Долго не получалось придумать, как сделать разный стартовый экран, если список пуст/не пуст. Получился костыль со сплеш-скрином, который не отображается.
* Есть баг с удалением элементов из списка ингредиентов. Если удалять в случайном порядке, может вылететь приложение
* Так и не получилось поменять цвет на кнопке с крестиком и плюсом

Список коктейля хранится в json файле, во внутреннем хранилище (Internal Storage).  Чтение и запись происходят через FileOutputStream. Картинки для коктейлей хранятся тоже во внутреннем хранилище, для каждого коктейля есть поле с именем файла картинки, по которому она потом вставляется во вью.
После чтения списка из памяти он хранится во ViewModel.
Для сериализации/десереалиазации используется kotlin.serialization.
Архитектура как таковая не была использована, но была попытка в clean architecture.

Первый запуск приложения, добавление коктейля в пустой список:
https://github.com/sav-nataly/CocktailBarApp/assets/45975245/545394bf-2d37-4f3d-8f38-c8337c48406b

Открытие карточки коктейля, добавление коктейля без изображения:
https://github.com/sav-nataly/CocktailBarApp/assets/45975245/3baa0d81-6fac-4dbd-b767-921d09a21bf1

Удаление ингредиента из списка, отмена добавления нового ингредиента и нового коктейля
https://github.com/sav-nataly/CocktailBarApp/assets/45975245/a3f40141-c322-4a9b-8881-308792ae46b9

