# Movie Trivia

<br/>

## ¿Qué tecnologías usa?

![Kotlin Top](https://img.shields.io/badge/Kotlin-100%25-blue)
![MVVM Top](https://img.shields.io/badge/MVVM-100%25-violet)
![Android Top](https://img.shields.io/badge/Android-100%25-green)
![Retrofit Top](https://img.shields.io/badge/Retrofit-100%25-black)
<br/>
<br/>

## Acerca de la app.
<br/>

#### Ésta app usa el patrón de arquitectura MVVM, donde se separan las funcionalidades de la presentation y la lógica de diseño, en capas, básicamente con el objetivo de que no se convierta en código spaghetti y tratando de que sea un sistema de código limpio. Se hizo mucho hincapié en seguir los principios básicos de SOLID y haciendo que la app sea fácilmente legible para cualquier programador conocedor del patrón de arquitectura.
<br/>
<br/>


## Librerías que usa la app.

<br/>

#### La app usa algunas librerías básicas para su funcionamiento, como ser retrofit2, Gson, Okhttp, room, coroutines, navigation component, androidx, coroutines,dagger-hilt.

<br/>
<br/>

## Explicando algunas librerías.

#### Retrofit2 , Gson y okhttp se utilizan para obtener y procesar los datos que provienen de la Api REST con la que se conecta.
<br/>

#### Las coroutines se usan para toda la parte de las tareas asíncronas que necesita la app, como la consulta de los datos a la base de datos en room o la consulta a la base de datos de la api REST.
<br/>

#### Room se utiliza como la verdadera fuente de información. Es decir, room es de donde nuestro viewmodel saca la info para para poder mostrarla en la vista. 
<br/>

#### Se utilizó dagger-hilt para la inyección de dependencias de las datasource en los viewmodel.
<br/>


<br/>
<br/>
<br/>


## A realizar.
<br/>

#### Pude obtener el link completo del trailer para su visualización, pero no pude implementar por completo la libreria de visualización de video de youtube https://androidrepo.com/repo/PierfrancescoSoffritti-android-youtube-player-android-kotlin , mi android studio no me dejaba traerla, para resolver este problema se me ocurrió importarla como un módulo más de la aplicación y luego implementarla con los pasos correspondientes de su documentación.

<br/>
