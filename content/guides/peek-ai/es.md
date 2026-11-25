---
title: ¿Cuándo llega mi paquete? La estimación de Peek IA
description: Peek IA es la estimación del propio Peek de cuándo llegará tu paquete, junto a la fecha del transportista. Cuándo aparece, cuánto acierta y cómo desactivarlo.
slug: cuando-llega-mi-paquete
picture: Pip, el paquete con cara, asomado a un móvil en el que la tarjeta de un paquete muestra un pequeño destello y «Peek IA antes de las 16:00» bajo la fecha del transportista.
published: 2026-11-24
updated: 2026-11-25
---

Cuando [Peek](/) tiene una pista clara de cuándo llegará tu paquete, Peek IA lo dice junto a la fecha del transportista, o en su lugar si el transportista no da ninguna: «probablemente antes de las 16:00», «probablemente hoy o mañana», «quizá antes» o «hoy es poco probable». Es una estimación del propio Peek, nunca una promesa del transportista, y la mayoría de los paquetes no muestran ninguna.

Está activado de forma predeterminada, con o sin cuenta, en peektracker.com, en la app para iPhone, iPad y Mac y en la [app de Android](guide:android-app), que se instala desde Google Play, desde el repositorio de F-Droid del propio Peek o desde GitHub.

:::steps
- Mira tu lista | Bajo la fecha del transportista, en la tarjeta de un paquete, o junto al estado en «Lo próximo»: «✦ Peek IA» y unas pocas palabras, como «antes de las 16:00».
- Abre el paquete | Una tarjeta corta dice, por ejemplo, «✦ Peek IA probablemente antes de las 16:00», con una línea que explica por qué.
- Toca (i) | El botón (i) («Sobre Peek IA») abre la hoja «Peek IA»: la estimación, con lo que dice el transportista debajo, como «Según DPD: hoy».
- Lee los detalles | A menudo, los días o las horas probables, sombreados según su probabilidad; luego «Por qué», «Fiabilidad» y el interruptor «Mostrar Peek IA».
:::

El widget «Paquetes» del [iPhone](guide:iphone-app) y de Android la muestra donde cabe, y los [asistentes conectados](guide:ai-assistants) la reciben como estimación de Peek IA, no del transportista. Las [notificaciones](guide:parcel-notifications), los correos de entrega, los pasaportes de tus amigos y la vista previa de un enlace compartido no la incluyen. La [demo de Peek](https://peektracker.com/demo) tiene paquetes de ejemplo con una estimación.

## ¿Cuándo llegará mi paquete? Lo que Peek IA te puede decir

Peek IA solo dice algo cuando tiene una de estas cinco pistas, cada una de las cuales ha acertado al menos 8 de cada 10 veces con los paquetes que Peek ha seguido.

| La pista | Peek IA dice | Por qué lo dice, por ejemplo |
| --- | --- | --- |
| En reparto, sin hora del transportista | «probablemente antes de las 16:00» | «En reparto desde las 09:12. DPD no da hora.» |
| El transportista dice hoy; por la tarde aún no ha salido a reparto | «hoy es poco probable» | «Según DPD, llega hoy, pero aún no ha salido a reparto.» |
| La fecha del transportista era ayer; un escaneo en las últimas 24 horas | «probablemente hoy o mañana» | «La fecha de DPD era ayer, pero vuelve a moverse.» |
| La fecha del transportista está a unos días; los paquetes como este suelen llegar antes | «quizá antes» | «Según DPD, llega el vie 27 nov. Va adelantado.» |
| El transportista no da fecha | «probablemente para el jue 26 nov», «probablemente esta semana» y otras | «UPS no da fecha. Llegó a la aduana ayer.» |

DPD y UPS ocupan el lugar de tu transportista. En una tarjeta, «hoy es poco probable» se queda en «hoy no», o en «poco probable» junto a una fecha del transportista que es hoy. Las horas siguen tu formato de 12 o 24 horas.

Cada estimación desaparece cuando vence, aunque la página siga abierta: «hoy es poco probable» a medianoche, «probablemente antes de las 16:00» a las 16:00, «quizá antes» al llegar la fecha del transportista, y la de un día, cuando ese día termina.

### ¿Por qué mi paquete no muestra ninguna estimación?

Normalmente, porque Peek no tiene una pista clara. Peek IA tampoco dice nada:

- cuando el paquete ya se ha entregado, está archivado, espera en un punto de recogida o está en devolución, o tras un intento de entrega (mira [qué hacer si falla la entrega](guide:missed-delivery));
- antes de su primer escaneo real: una etiqueta sola no basta;
- cuando el transportista incluye la entrega entre sus pasos previstos, o da para hoy una hora o una franja que aún no ha pasado;
- antes de que el paquete salga a reparto, si Peek no lo ha consultado hace poco (en 36 horas, salvo para «hoy es poco probable»);
- cuando la fecha del transportista pasó hace más de un día (mira [cuándo preocuparte](guide:tracking-not-updating));
- para «quizá antes» y las estimaciones sin fecha del transportista, 45 días después del primer escaneo o 30 días después del último.

## ¿Es fiable la fecha de entrega del transportista? «Quizá antes» y «hoy es poco probable»

No siempre, y Peek IA lo dice en dos casos: «quizá antes», cuando los paquetes con escaneos como los del tuyo suelen llegar antes de la fecha del transportista, y «hoy es poco probable», cuando el transportista dice hoy pero por la tarde el paquete aún no ha salido a reparto.

«Quizá antes» puede indicar el primer día probable («quizá antes, a partir de mañana», «quizá antes, a partir del jue 26 nov») y nunca aparece cuando el transportista da un intervalo de días.

## Mi paquete está en reparto y aún no ha llegado: ¿llegará hoy?

Peek IA puede decírtelo. «Probablemente antes de las 16:00» significa que la mayoría de los paquetes de ese transportista que Peek ha seguido llegaron antes de esa hora el día de su entrega; solo aparece cuando el transportista no da hora y el paquete salió antes de esa hora. A última hora del día, «hoy es poco probable» significa que pocos llegan tan tarde: «Sigue en reparto a esta hora. Con DPD, pocos llegan tan tarde.» Con algunos transportistas no dice nada mientras el paquete está en reparto, porque Peek no se fía de sus escaneos «En reparto».

Las horas habituales de cada transportista, y por qué una furgoneta puede seguir en ruta a las 8 de la tarde, están en [a qué hora reparten los paquetes](guide:delivery-times); lo que significa el estado, en [los estados de un envío](guide:tracking-statuses).

## El transportista no da fecha de entrega: ¿cuánto tardará?

Peek IA puede dar un día o un plazo, lo que su modelo vea con suficiente seguridad: «probablemente para el jue 26 nov», «probablemente hoy o mañana» (solo cuando el transportista reparte mañana), «probablemente esta semana» (hasta el domingo) o «probablemente antes de que acabe la semana que viene» (hasta el domingo siguiente).

Para los plazos habituales desde China, mira [cómo seguir un paquete de China](guide:tracking-from-china).

## ¿Cómo calcula Peek IA cuándo llega un paquete y cuánto acierta?

Peek IA es un modelo entrenado más unas tablas de reglas, en el propio servidor de Peek, y solo muestra los tipos de estimación que acertaron al menos 8 de cada 10 veces.

El modelo, un clasificador de gradient boosting formado por árboles de decisión, da la probabilidad de que un paquete haya llegado para cada uno de los próximos días de reparto del transportista. Lee los escaneos y dónde se hicieron, el transportista, el servicio, el peso, la zona horaria y el país de destino declarado, y si el paquete se consultó sin cuenta. Las tablas de reglas guardan la hora de entrega habitual de cada transportista y cuántas veces se cumplió cada tipo de estimación.

El servidor calcula la estimación cada vez que envía los datos del paquete a tu móvil o a tu navegador, sin recurrir a ningún servicio externo; estos solo la muestran. Solo el servidor de Peek tiene el modelo: ni el modelo ni su código de entrenamiento están en [el código abierto de Peek](guide:developers). No es un chatbot.

«Fiabilidad» dice «Acierta 8 de cada 10 veces con paquetes como este.» o, para «hoy es poco probable», «Acierta 8 de cada 10 veces a esta hora.» El número es 8 o 9: Peek IA no muestra nada por debajo de 8 de cada 10, nunca dice más de 9 y redondea hacia abajo.

## ¿Cómo desactivo Peek IA?

Desactiva «Peek IA» en «Preferencias», dentro de los «Ajustes» de Peek, o toca (i) en la tarjeta de Peek IA de un paquete y desactiva «Mostrar Peek IA».

:::steps
- Abre «Ajustes» | En la web, el botón de tu cuenta (tu inicial) o, sin cuenta, el botón del engranaje «Ajustes». En las apps, el botón Cuenta, arriba en Entregas; en un Mac, la ventana de ajustes de Peek, pestaña «General».
- Busca Peek IA | «Peek IA» es la última fila de «Preferencias», con el texto «Cuando Peek tiene una pista clara, te dice cuándo cree que llegará el paquete.»
- Desactívalo | Peek IA desaparece de las tarjetas, de «Lo próximo», de las páginas de los paquetes y de los widgets en ese dispositivo.
:::

Con una cuenta, tu elección te sigue a tus otros dispositivos, y los asistentes conectados dejan de recibir la estimación. Sin cuenta, se queda en ese navegador o en ese dispositivo; [Peek sin cuenta](guide:account-or-guest) compara las dos opciones.

:::sources
- [Peek: Peek IA en el servidor](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAi.ts) – las cinco pistas, 8 de cada 10, 36 horas, cuándo no dice nada
- [Peek: las palabras de Peek IA](https://github.com/plhery/peek-delivery-tracker/blob/main/src/lib/peekAi.ts) – lo que dicen las tarjetas y las páginas, cuándo vence cada estimación, Fiabilidad
- [Peek: la tarjeta y la hoja de Peek IA](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/PeekAi.tsx) – la tarjeta, la hoja de (i), Mostrar Peek IA
- [Peek: el modelo](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAiModel.ts) – árboles de decisión, lo que lee, 45 y 30 días
- [Peek: arquitectura (en inglés)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – cómo funciona Peek IA, dónde no aparece, la elección de la cuenta
- [Peek: textos en español](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/es.json) – cada etiqueta de Peek IA citada aquí
- [Peek: Preferencias en la web](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Preferences.tsx) – el interruptor de Peek IA, que sigue a la cuenta
- [Peek: Ajustes en iPhone, iPad y Mac](https://github.com/plhery/peek-delivery-tracker/blob/main/ios/PeekDeliveryTracker/SettingsViews.swift) – Peek IA en Preferencias, General en un Mac
- [Peek: Ajustes en Android](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – Peek IA en Preferencias
- [Peek: herramientas de los asistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – la estimación en la lista de paquetes de un asistente
- [App Store: Peek](https://apps.apple.com/es/app/id6823615370) – Peek IA en las novedades de la app
- [Peek: notas de Android (en inglés)](https://github.com/plhery/peek-delivery-tracker/blob/main/fastlane/metadata/android/en-US/changelogs/21.txt) – Peek IA en la app de Android
- [Peek: Peek para Android](https://peektracker.com/android) – la app en Google Play, F-Droid y GitHub
- [Peek: demo](https://peektracker.com/demo) – paquetes de ejemplo con una estimación
:::
