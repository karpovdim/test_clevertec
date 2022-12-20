используемый стак

javax.validation
hibernate

spring

liquibase

lombok

MYSQL

JDK17
база данных накатываеться автоматически.
ДЛЯ БЫСТРОГО ЗАПУСКА запустить TestClevertecApplication ЗАТЕМ НАЙТИ В
КОРНЕВОМ КАТАЛОГЕ http/order.http и выполнить request для создания заказа
после выполнения файл с чеком сохраниться в корневой каталог


POST http://localhost:8080/shopping  
Content-Type: application/json  

{  
"discountCardNumber": "12835AW",         
"ShoppingCart": {  
"1": "3",   
"2": "5"  
}  
}

  где  "1": "3"   1- id продукта  3- количество продуктов в корзине
  
 где  "2": "5"   2- id продукта  5- количество продуктов в корзине


   Карпов Дмитрий karpov83d@gmail.com