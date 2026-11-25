---
title: Peek para Android en Google Play, con el widget Paquetes
description: Peek para Android está en Google Play, y también en el repositorio de F-Droid del propio Peek y en GitHub. Sin cuenta. Añade el widget y comparte enlaces con Peek.
slug: app-seguimiento-paquetes-android
picture: Un móvil Android con el widget Paquetes en la pantalla de inicio, y Pip, el paquete con cara, asomándose por encima del móvil.
published: 2026-11-19
updated: 2026-11-25
---

Peek para Android está en [Google Play](https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker), y también en el repositorio de F-Droid del propio Peek y en GitHub. Necesita Android 12 o posterior, no pide cuenta y añade un widget «Paquetes» a tu pantalla de inicio y «Añadir a Peek» a tu menú de compartir.

Sigue paquetes de más de 4500 transportistas: pega un número de seguimiento, el enlace de un transportista o un correo de envío entero, y [Peek](/) encuentra el transportista y muestra el trayecto en un mapa. Es gratis, sin compras dentro de la app ni anuncios. Para un iPhone, un iPad o un Mac, mira [Peek para iPhone, iPad y Mac](guide:iphone-app).

## ¿Está Peek en Google Play?

Sí. En tu móvil Android, abre [Peek en Google Play](https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker) e instálalo. El botón «Instalar Peek» de [peektracker.com/android](https://peektracker.com/android), «Peek para Android», también lleva allí, y la misma página explica cómo instalarlo sin Google Play.

### ¿Cómo instalo Peek sin Google Play?

Añade a mano el repositorio propio de Peek en la app F-Droid, o descarga la app de GitHub. Peek no está en el catálogo principal de F-Droid.

:::steps
- Copia la dirección | En [peektracker.com/android](https://peektracker.com/android), bajo «Sin Google Play», toca «Copiar la dirección».
- Añade el repositorio | En la app F-Droid, ve a **Ajustes** › **Repositorios** › **+** y pega la dirección completa.
- Instala Peek | Toca el repositorio nuevo para ver sus apps e instala «Peek». F-Droid no instala nada hasta que lo elijas tú.
:::

O toca «Abrir GitHub» en la misma página: en la versión más reciente de [las versiones de Peek en GitHub](https://github.com/plhery/peek-delivery-tracker/releases), toca el archivo .apk y deja que tu navegador lo instale. Allí, las versiones de Peek llevan la marca «Pre-release».

Esta versión la firma Peek, no Google, así que no se puede actualizar encima de una copia de Google Play, ni al revés: si tienes Peek de Google Play, o una copia antigua del repositorio o de GitHub, desinstálala antes. Las actualizaciones llegan por el repositorio de F-Droid de Peek.

### ¿Qué diferencia hay entre la versión de Google Play y la de F-Droid?

| Qué | Versión de Google Play | Versión de F-Droid y GitHub |
| --- | --- | --- |
| Servicios de Google | Firebase, ML Kit, Play Integrity | Ninguno |
| Lector de códigos de barras | Sí | No, y no pide permiso de cámara |
| Notificaciones | A través de los servicios de Google Play | A través de una app UnifiedPush como ntfy, o Peek mira si hay novedades cada 15 minutos más o menos |
| Firmada y actualizada por | Google, a través de Google Play | Peek, a través de su repositorio de F-Droid |

Las notificaciones de cada versión se explican en [las notificaciones de entrega de paquetes](guide:parcel-notifications).

## ¿Qué versión de Android necesita Peek?

Android 12 o posterior, en las dos versiones. Un dispositivo sin cámara trasera también puede instalarlo, solo que sin el lector.

Está en diez idiomas: inglés, alemán, francés, italiano, español, portugués, portugués de Brasil, polaco, ruso y chino simplificado. Elige uno en «Ajustes» › «Preferencias» › «Idioma» (los ajustes se abren desde el botón Cuenta, arriba en Entregas) o en el ajuste de idioma por app del propio Android. Si lo eliges en los ajustes de Peek, «Añadir a Peek», el nombre del widget en el selector y el acceso directo «Añadir un paquete» siguen en el idioma de tu móvil; los textos del propio widget siguen el de Peek.

## ¿Puedo usar Peek en Android sin cuenta?

Sí. Pega un número de seguimiento en la primera pantalla y Peek lo sigue, y el paquete aparece solo en ese móvil; el widget y el acceso directo al próximo paquete también funcionan. Entregas muestra «Todos tus paquetes en un solo lugar», con «Solo en este dispositivo, por ahora.» y un botón «Iniciar sesión».

Peek olvida un paquete buscado 30 días después de la entrega; la app guarda entonces su última respuesta, marcada «Ya no se actualiza», hasta que lo borres. «Ajustes» › «En este dispositivo» › «Olvidar todos» los olvida todos de una vez. Las copias de seguridad del propio móvil guardan los paquetes que sigue y las preferencias de Peek.

Una cuenta añade la misma lista en todos tus dispositivos y en peektracker.com, el pasaporte y los amigos, los correos de entrega y «Añadir paquetes por correo» ([cómo añadir paquetes por correo](guide:add-parcels-by-email)). Inicia sesión con «Continuar con Google» o «Acceder con correo»; en Android no hay inicio de sesión con Apple. «Explorar la demo», en la pantalla para iniciar sesión, tampoco necesita cuenta. [Usar Peek sin cuenta](guide:account-or-guest) compara las dos opciones.

## ¿Cómo añado el widget de Peek a la pantalla de inicio?

Añade «Paquetes», el único widget de Peek, desde el selector de widgets, donde se presenta así: «Ve de un vistazo los paquetes en camino, el próximo primero.» Los pasos de Google:

:::steps
- Abre los widgets | En una pantalla de inicio, mantén pulsado un espacio vacío y toca **Widgets**.
- Busca Peek | Toca Peek para ver su widget, «Paquetes».
- Colócalo | Mantén pulsado el widget, deslízalo hasta donde quieras y levanta el dedo.
:::

Google avisa de que «Algunos de estos pasos solo funcionan en Android 14 y versiones posteriores.»

«Paquetes» empieza con un tamaño de cuatro celdas por dos y se puede reducir hasta dos por dos. Muestra los paquetes en camino y luego los entregados en el último día, en el orden de Peek. Cada paquete abre su página; el resto del widget abre tu lista. Se redibuja al menos cada 20 minutos, y sus días cambian a medianoche aunque la app esté cerrada. Cuando [Peek IA](guide:peek-ai) tiene una estimación para un paquete, el widget añade «✦ Peek IA» y unas pocas palabras junto a la fecha, donde caben; «Ajustes» › «Preferencias» › «Peek IA» lo desactiva.

| El widget muestra | Qué significa |
| --- | --- |
| «No hay paquetes en camino» | Ningún paquete está en camino: «Añade un número o enlace de seguimiento para empezar.» |

«Paquetes» es solo para la pantalla de inicio: no hay widget para la pantalla de bloqueo ni nada parecido a las Actividades en directo.

### ¿Tiene Peek accesos directos en el icono de la app?

Sí. Mantén pulsado el icono de Peek para ver «Añadir un paquete», que abre la pantalla para añadir, y un acceso directo con el nombre de tu próximo paquete en camino, que lo abre. Para fijar uno en la pantalla de inicio, mantenlo pulsado y deslízalo a su sitio. Peek no tiene frases de voz en Android: estos accesos directos sustituyen a las acciones de Siri del iPhone.

## ¿Cómo comparto un enlace de seguimiento con Peek en Android?

Compártelo y elige «Añadir a Peek», que acepta cualquier texto o enlace compartido.

:::steps
- Comparte | Comparte el número de seguimiento, el enlace o la página desde cualquier app y elige «Añadir a Peek».
- Guarda | En la tarjeta que se abre, toca «Guardar datos de seguimiento». Dice «Guardado. Abre Peek en los próximos 10 minutos para revisar y añadir tu paquete.»
- Abre Peek | Dentro de esos 10 minutos, la pantalla para añadir se abre con los datos ya puestos.
:::

Los enlaces de paquetes de Peek (peektracker.com/p/…) se abren en la app una vez instalada. Para enviarle un paquete a otra persona, mira [cómo compartir el seguimiento de un paquete](guide:share-parcel).

### ¿Puede Peek escanear un código de barras en Android?

Solo la versión de Google Play. Toca el icono de código de barras al final del campo de seguimiento (que se lee en voz alta como «Escanear código de barras»), en la primera pantalla o en la de añadir; con la lista vacía, también hay un botón «Escanear código de barras». «Escanear un código de seguimiento» se abre con la cámara trasera y sigue el código de barras más cercano al centro. Si rechazas la cámara, el lector desaparece hasta que la permitas en los ajustes de Android. ¿No sabes cuál es el número de seguimiento? Mira [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

:::sources
- [Peek: Peek para Android](https://peektracker.com/android) – Google Play, Android 12, el repositorio de F-Droid, GitHub, la firma
- [Google Play: Peek](https://play.google.com/store/apps/details?id=com.plhery.peekdeliverytracker) – la ficha de la app
- [Peek: app de Android (en inglés)](https://github.com/plhery/peek-delivery-tracker/blob/main/android/README.md) – las dos versiones, contenido y actualización del widget, accesos directos, claves de firma, versiones previas
- [Peek: ficha de F-Droid (en inglés)](https://github.com/plhery/peek-delivery-tracker/blob/main/android/fdroid/com.plhery.peekdeliverytracker.yml) – nombre, qué copias anteriores desinstalar
- [F-Droid: Cómo añadir repositorios a F-Droid](https://f-droid.org/es/tutorials/add-repo/) – Ajustes › Repositorios › +, y luego elegir las apps que instalar
- [Peek: versiones en GitHub](https://github.com/plhery/peek-delivery-tracker/releases) – los archivos .apk, marcados como Pre-release
- [Peek: textos en español](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/es.json) – cada etiqueta de Peek citada aquí
- [Peek: manifiesto de Android](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/AndroidManifest.xml) – Añadir a Peek, enlaces de paquetes, cámara, copias de seguridad
- [Peek: el widget Paquetes](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/res/xml/parcels_widget.xml) – solo pantalla de inicio, sus tamaños
- [Peek: Peek IA en el widget](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/widget/WidgetPeekAi.kt) – «✦ Peek IA» junto a la fecha de un paquete, donde cabe
- [Peek: Ajustes](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – Olvidar todos, Idioma, sin Actividades en directo, Peek IA
- [Peek: el lector de la versión de Google Play](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/play/kotlin/com/plhery/peekdeliverytracker/platform/CameraLabelScanner.kt) – códigos de barras, permiso de cámara
- [Peek: ayuda (en inglés)](https://peektracker.com/support.html) – sin cuenta, Forget all
- [Ayuda de Android: Añadir aplicaciones, accesos directos y widgets a las pantallas de inicio](https://support.google.com/android/answer/9450271?hl=es) – añadir un widget (Widgets), fijar un acceso directo, Android 14
:::
