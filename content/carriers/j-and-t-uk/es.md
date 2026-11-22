---
title: Seguimiento J&T Express UK: números JTUK y estados
description: Sigue un paquete de J&T Express UK: cómo es un número JTUK, qué significa cada línea de seguimiento, aduanas en Reino Unido y qué hacer si se retrasa.
slug: seguimiento-jt-express-uk
published: 2026-11-21
updated: 2026-11-21
---

J&T Express UK reparte paquetes en el Reino Unido. Si una tienda envió tu pedido con J&T, el número de seguimiento empieza por `JTUK`: pégalo en la casilla de arriba o consúltalo en el rastreador de J&T en www.jtexpress.uk/tracking. El número está en el correo de envío o en la página del pedido de la tienda. Como Peek no pudo leer la web de J&T Express UK, esta página se basa en lo que el código de Peek conoce sobre el rastreador de J&T y en fuentes oficiales británicas.

## Número de seguimiento J&T Express UK: cómo es

El número de envío de J&T Express UK se compone de `JTUK` seguido de 12 cifras, 16 caracteres en total. Peek no conoce ningún otro formato para este transportista. Ejemplos con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `JTUK482910573628` | `JTUK` y 12 cifras: un paquete de J&T Express UK. Peek identifica J&T Express UK al instante. |
| `JTUK48291057362` | Falta una cifra: no encaja con J&T Express UK y Peek no reconoce ningún transportista. Comprueba si falta una cifra. |
| `JTDE482910573628` | `JTDE` y 12 cifras: [J&T Express Germany](carrier:j-and-t-de), no la red británica. |
| `JTAU482910573628` | `JTAU` y 12 cifras: [J&T Express Australia](carrier:j-and-t-au). |
| `JTE482910573628` | `JTE` y 12 cifras: [J&T Express Middle East](carrier:j-and-t-middle-east). |
| `JT4829105736284` | `JT` y 13 cifras: redes de J&T en el Sudeste Asiático y en China ([J&T Express China](carrier:j-and-t-cn)). |

En peektracker.com, un número `JTUK` al que le falta o le sobra un carácter muestra un aviso como: «Empieza como un número de seguimiento de J&T Express UK, pero le falta un carácter: puede que tenga una errata. Compáralo con tu mensaje de envío.» En Peek los espacios, guiones y minúsculas no importan: `jtuk 4829 1057 3628` equivale a la primera línea. El catálogo de Peek también lo recoge como J&T Express (UK), J&T Express UK Ltd y J&T UK.

**Dónde encontrarlo.** En el correo de envío de la tienda, en la página del pedido de la tienda o marketplace, o en cualquier mensaje sobre la entrega. Si no aparece, consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number) o pregunta a la tienda.

## Estados de seguimiento de J&T Express UK explicados

El rastreador de J&T muestra cada escaneo con su hora y una frase, sin lugar. Estas son las frases vistas en el código de Peek en envíos reales de J&T Express UK, en el orden en que un paquete pasa por ellas:

| Qué indica J&T | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `The parcel has been successfully picked up and is being prepared for transportation` | J&T ha recogido el paquete del remitente y lo prepara para el transporte. | «Admitido por el transportista» |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination` | Ha llegado a un centro de clasificación de J&T. | «En tránsito» |
| `The parcel is dispatched from the transit center to the next destination` | Ha salido de ese centro hacia el siguiente punto. | «En tránsito» |
| `The parcel has been collected and is on its way` | El repartidor ha recogido el paquete para la ruta de entrega. Pese al texto, no es la recogida inicial: J&T lo clasifica como escaneo de reparto. | «En reparto» |
| `The parcel has been successfully delivered to the recipient` | Entregado. | «Entregado» |
| `The delivery address is incomplete or incorrect.` | Intento fallido: el repartidor no pudo entregar con la dirección indicada. En los historiales del código de Peek aparece horas después de salir a reparto, el mismo día. | «Intento de entrega» |
| `Delivery was attempted, but no one was available to receive the package at the address; the package was taken to a pickup location for the customer to collect.` | No había nadie para recibir el paquete y se llevó a un punto de recogida para que puedas retirarlo. | «Intento de entrega» |

Cualquier frase fuera de esta tabla sigue las reglas generales de Peek: si Peek no la reconoce, se muestra tal como la escribió J&T y cuenta como «En tránsito», salvo que el paquete ya esté en reparto o en una etapa posterior, en cuyo caso mantiene su estado.

Si una frase termina con un teléfono de atención al cliente o una invitación a llamar, Peek la omite. También elimina texto entre paréntesis, teléfonos y nombres de quien firmó, recibió o recogió el paquete, así como de repartidores, destinatarios o remitentes, conservando el resto.

## Entrega de J&T Express UK: qué indica el seguimiento

En los historiales del código de Peek, el repartidor de J&T sale a reparto y entrega el paquete, informa de un problema con la dirección o lo deja en un punto de recogida si no hay nadie. El código de Peek no muestra traspasos a otro transportista: el rastreador de J&T sigue el envío hasta tu puerta.

**En reparto.** `The parcel has been collected and is on its way` indica que el paquete va en la ruta de hoy. El rastreador de J&T no ofrece franja horaria ni día previsto, así que Peek tampoco puede mostrarlos.

**Problema de dirección.** Tras `The delivery address is incomplete or incorrect.`, comprueba la dirección del pedido. Si falta algo (como el número de piso o el código postal), avisa enseguida a la tienda que contrató el envío. Consulta [cómo cambiar la dirección de entrega](guide:change-delivery-address) y [entrega fallida](guide:missed-delivery).

**Intentos, lugares seguros y puntos de recogida.** Como Peek no pudo leer la web de J&T Express UK, esta página no indica cuántos intentos realiza J&T, si deja paquetes a vecinos o en un lugar seguro ni cuántos días guarda un paquete no entregado. Pregunta a la tienda o revisa tus mensajes de entrega.

**Pedidos desde fuera del Reino Unido.** Según GOV.UK, si lo que compraste procede del extranjero, la empresa que reparte te contactará si corresponde pagar IVA, aranceles o gastos de entrega, con una factura que detalla exactamente qué pagar. Suele guardar el paquete unas 3 semanas y devolverlo al remitente si no se abona. En compras propias de hasta 135 £ (salvo artículos sujetos a impuestos especiales como alcohol, tabaco o vapeadores), el vendedor ya incluyó el IVA en el precio. Por encima de 135 £ pagas el IVA a la empresa de reparto antes de la entrega o al recogerlo, además de posibles derechos de aduana según el producto y su origen. Irlanda del Norte tiene normas propias. Peek no pudo comprobar cómo factura J&T estos cargos. Más en [paquetes retenidos en aduanas](guide:customs).

## Paquete de J&T Express UK retrasado, perdido o devuelto

La tienda eligió a J&T, de modo que en una compra online tu primer contacto es la tienda. Según la sección 29 de la Consumer Rights Act 2015, los bienes comprados a un comerciante permanecen bajo el riesgo del vendedor hasta que llegan a tus manos o a las de una persona designada por ti, salvo que contrataras por tu cuenta a un transportista no ofrecido por el vendedor.

**Sin movimiento.** Un paquete en «Admitido por el transportista» o «En tránsito» que pase cuatro días sin nuevas líneas recibe un aviso en Peek con el día del último escaneo («Sin novedades desde el …») y un enlace a la página de contacto de J&T Express UK («Pide ayuda a J&T Express UK»). Si sigue igual, pregunta a la tienda. Más en [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Todavía sin seguimiento.** Un número que el rastreador de J&T aún no conoce aparece como «Esperando a J&T Express UK». Una tienda puede generar el número antes de entregar el paquete. Si dos días después J&T sigue sin reconocerlo y ninguna otra fuente ha respondido, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a consultarlo a diario, y semanalmente tras una semana desde que lo añadiste. Confirma el número con la tienda y pregunta si ya salió el pedido.

**Entregado, pero no ha llegado.** Revisa alrededor de tu puerta, en casa y con los vecinos, y avisa a la tienda: conforme a esa ley, el riesgo sigue siendo suyo hasta que lo recibas, así que pide reembolso o reenvío. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Perdido o dañado.** Solicita reembolso o sustitución a la tienda indicando el número de seguimiento. Peek no pudo leer las normas de reclamación de J&T; consulta [paquete perdido](guide:lost-parcel) y [paquete dañado](guide:damaged-parcel).

**Devuelto.** Si el paquete regresa a la tienda tras un intento fallido, la tienda decide sobre el reembolso o un nuevo envío. Más en [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de J&T Express UK

Como Peek no pudo leer la web de J&T Express UK, esta página no recoge teléfono, correo ni chat: Peek solo ofrece contactos leídos en las propias páginas del transportista. Companies House registra como domicilio social `C/O Tmf Group 13th Floor, One Angel Court, London, United Kingdom, EC2R 7HJ`: es la dirección legal de la sociedad en manos de otra firma, no un mostrador de paquetes.

Empieza por la tienda que contrató el reparto o, si es un marketplace, usa la ayuda de la página del pedido. El rastreador propio de J&T está en www.jtexpress.uk/tracking.

## Seguir un paquete de J&T Express UK con Peek

[Peek](/) identifica J&T Express UK al instante a partir de un número `JTUK` («Transportista detectado») y consulta el rastreador de J&T solo con el número, sin código postal ni cuenta. También sirve un enlace www.jtexpress.uk/tracking con `?q=` y el número; si incluye otro tipo de código, Peek lo trata como si lo hubieras pegado solo. Si tu código no es `JTUK`, pégalo igualmente: Peek sigue más de 4500 transportistas. Con un código `JT` de 13 cifras, Peek muestra «Comprobando servicios de seguimiento…» mientras pregunta. Elegir J&T Express UK con «Cambiar» para otro formato no funciona: Peek avisa de que «no emite números como este».

Peek muestra el estado, cada escaneo con su hora exacta y la hora de entrega. J&T devuelve cada escaneo como un instante exacto, así que Peek lo muestra en la zona horaria de tu dispositivo tanto en verano como en invierno; en peektracker.com y en las apps para iPhone y Android, el historial termina con «Horas en tu zona horaria:» y su nombre. Los escaneos aparecen en el inglés de J&T. Como J&T no da lugar, día previsto ni peso, Peek no muestra mapa. El rastreador de J&T puede mostrar prueba de entrega, que Peek descarta. «Abrir la web de J&T Express UK», en la página del paquete, abre tu número en el rastreador de J&T.

Hasta que J&T escanee el envío, Peek sigue consultando con «Esperando a J&T Express UK» (o pide revisar el número tras dos días). Cuando el rastreador de J&T no responde o no conoce el número, Peek acude a servicios de seguimiento y muestra su historial en sus propias palabras.

Mientras tienes el paquete abierto en pantalla o el día de la entrega con las notificaciones activadas, y mientras responde el rastreador de J&T, Peek comprueba hasta cada 10 minutos, y hasta cada 2 minutos desde las 6:00 (hora local) el día en que J&T lo escanea en reparto; el resto del tiempo, cada hora. A través de servicios de seguimiento comprueba cada hora (cada 3 horas tras un día sin novedades), y hasta cada 15 minutos el día de reparto mientras lo tienes abierto o con notificaciones activadas. Tras dos días sin un nuevo escaneo (12 horas mientras figura en «Admitido por el transportista»), comprueba cada hora. Puede notificarte de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con J&T ni con la tienda por ti, cambiar la dirección ni concertar una nueva entrega.

## Preguntas sobre el seguimiento de J&T Express UK

### ¿Es fiable J&T Express UK?

Sí. Companies House recoge a `J&T EXPRESS UK LTD` como sociedad limitada activa constituida el 18 de diciembre de 2025, con actividades declaradas que incluyen transporte de mercancías por carretera, almacenamiento y actividades postales. Sus números empiezan por `JTUK` y Peek lee su rastreador directamente.

### ¿Qué significa «The parcel has been collected and is on its way» en J&T?

Que tu paquete está en reparto. El mensajero lo ha recogido para su ruta de entrega; J&T registra esta línea como un escaneo de reparto y Peek muestra «En reparto». No significa que acabe de salir del remitente.

### ¿Por qué dice J&T que mi dirección está incompleta o es incorrecta?

El repartidor no pudo entregar porque faltaba algún dato o no coincidía. Revisa la dirección de tu pedido y avisa de inmediato a la tienda indicando la dirección completa y tu número de seguimiento.

### ¿Por qué no aparece mi número de seguimiento de J&T?

Comprueba que empiece por `JTUK` seguido de 12 cifras: los códigos de otras redes de J&T, como `JTDE` en Alemania, no pertenecen a la red del Reino Unido. Si el código es correcto, puede que J&T aún no tenga el paquete y Peek muestre «Esperando a J&T Express UK». Si tras dos días sigue sin reconocerlo y ninguna otra fuente ha respondido, Peek te pedirá revisarlo, elegir otro transportista o eliminar el paquete. Si tras varios días no hay rastro, pregunta a la tienda.

### ¿Cómo contacto con J&T Express UK?

Pregunta primero a la tienda que contrató el envío. Como Peek no pudo leer la web de J&T Express UK, esta página no incluye teléfono ni correo electrónico.

:::sources
- [Universal Parcel Scraper: J&T Express UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-uk) – nombres, formato `JTUK`, enlaces de seguimiento, página de ayuda `jtexpress.uk/contact`, textos de escaneo y etapas (incluido el traslado a punto de recogida tras ausencia), horas exactas, sin lugar/día previsto/peso, prueba de entrega descartada
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de otras redes J&T, filtrado de líneas de atención al cliente, paréntesis, nombres y teléfonos, sin traspaso a otro transportista, servicios de seguimiento de respaldo
- [Companies House: J&T EXPRESS UK LTD](https://find-and-update.company-information.service.gov.uk/company/16920174) – sociedad limitada activa, constituida el 18 de diciembre de 2025, objeto social, domicilio social
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – factura de la empresa de reparto, guarda de unas 3 semanas, IVA hasta 135 £ y superior, aranceles, Irlanda del Norte
- [UK legislation: Consumer Rights Act 2015, section 29](https://www.legislation.gov.uk/ukpga/2015/15/section/29) – riesgo del vendedor hasta la posesión física por el consumidor
:::
