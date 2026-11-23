---
title: Seguimiento DAO: estados, daoSHOP y entregas fallidas
description: Sigue un paquete DAO en Dinamarca: número de 20 dígitos, significado de los estados, entregas nocturnas, recogida en daoSHOP y contacto con DAO.
slug: seguimiento-dao
published: 2026-11-23
updated: 2026-11-23
---

DAO (Dansk Avis Omdeling) es una empresa danesa que reparte periódicos desde 1921 y entrega paquetes de tiendas online en tu puerta durante la noche o en un punto de recogida daoSHOP. Introduce el número de paquete en la página «Track your parcel» de DAO, en la app dao o pégalo en el recuadro de arriba. El número llega en el correo de envío de la tienda o en el SMS y correo de DAO.

## Dónde encontrar tu número de seguimiento DAO

La tienda envía el número en su correo de envío. Cuando un paquete espera en un daoSHOP, DAO te envía por SMS y correo el número del paquete y un código de recogida de 5 dígitos. La app dao añade tus paquetes automáticamente en cuanto se crea el envío.

DAO indica que sus números de paquete tienen 9, 13 o 20 dígitos (dígitos inventados abajo):

| Número | Qué es |
| --- | --- |
| `00057126964829173646` | El número de paquete de 20 dígitos que empieza por `00057`. Es el código de barras de la etiqueta y el que pide atención al cliente de DAO. |
| `7482917364058` | Un número de 13 dígitos que empieza por `7`. |
| `482917364` | Un código de etiqueta electrónica de 9 dígitos: un remitente que contrata el envío con DAO lo escribe en el paquete en lugar de imprimir una etiqueta. |

Los espacios no importan. Los paquetes daneses de otros transportistas [tienen otro aspecto](guide:tracking-number-formats): los números postales de PostNord terminan en `DK` y sus números de 20 dígitos usan otros prefijos, los de GLS tienen 11 o 12 dígitos y los de DPD 14. Si la página de DAO no lo encuentra, comprueba los dígitos y pregunta a la tienda. Consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number) si no recibiste nada.

## Qué significan los estados de seguimiento de DAO

El seguimiento de DAO muestra los estados en inglés en su web inglesa y en danés en dao.as. Aquí tienes los textos en inglés, por orden. A menudo aparece un depósito o terminal tras dos puntos, como en «Parcel arrived at dao depot: dao Erritsø».

| Estado | Qué significa para ti |
| --- | --- |
| `Shipment created, awaiting registration` | La tienda ha [creado la etiqueta](guide:tracking-statuses). DAO todavía no tiene el paquete. |
| `Handed in at daoSHOP` | El remitente dejó el paquete en un daoSHOP. |
| `On the way to terminal` | Ha sido recogido de ese establecimiento. |
| `Parcel has arrived at terminal` | En una terminal de clasificación de DAO, todavía en camino. |
| `Sorted at: Erritsø terminal` | Clasificado en la terminal indicada, como Erritsø o Sjælland. |
| `Parcel arrived at dao depot` | En el depósito local, para entrega a domicilio. |
| `On the way to daoSHOP` | De camino al punto de recogida. |
| `To be delivered today` | En la próxima ruta a domicilio, normalmente esa noche o de madrugada. |
| `Not delivered - other reason` | El repartidor no pudo dejarlo en tu dirección. |
| `Delivery not possible. Redirected to daoSHOP` | Aparece al mismo tiempo: va a un daoSHOP en su lugar. |
| `Ready for pickup` | Espera en el daoSHOP. Tu código llega por SMS y correo electrónico. |
| `Parcel delivered` | Dejado en tu dirección o recogido en el daoSHOP. |

Dos detalles suelen despistar: `Parcel has arrived at terminal` es un paso de clasificación, no el final del viaje; y un `Parcel delivered` a domicilio suele ocurrir de noche, así que mira en el buzón o en el picaporte de la puerta por la mañana. No se ha registrado ningún texto específico de devolución.

## Entregas a domicilio de DAO, daoSHOP y paquetes perdidos

**A domicilio.** DAO entrega de noche y de madrugada, los 365 días del año, sin firma; sus condiciones indican que casi siempre antes de las 7:00 (hora danesa). Deja el paquete en tu buzón si cabe; de lo contrario lo cuelga en la puerta en una bolsa de plástico reciclado o lo deja junto a la puerta, por ejemplo sobre el felpudo. No hay recibo: los registros de GPS, hora y foto de DAO sirven como prueba.

**Por qué fue a un daoSHOP.** Puede que el remitente eligiera un daoSHOP. Si no, DAO redirige un paquete que no pudo dejar con seguridad, uno que supere los 5 kg o las medidas para domicilio (más de 55 cm de largo o 120 cm sumando longitud y perímetro), o uno con error en la dirección. En bloques de pisos, DAO necesita acceso al portal; los residentes pueden darlo mediante el formulario «Grant access» de DAO.

**Recogida.** Lleva el número del paquete y el código de recogida de 5 dígitos del SMS o correo de DAO. Otra persona puede recogerlo si le reenvías el mensaje. Las páginas de DAO para particulares cuentan más de 1.500 daoSHOP y las de empresas más de 1.600; busca uno por código postal, calle o ciudad en el mapa de dao.as con horarios. DAO puede usar un comercio distinto al elegido o, a veces, una taquilla.

**Cuánto espera.** Las condiciones de DAO conceden 7 días naturales para recogerlo, con un aviso y dos recordatorios. Después vuelve al remitente.

DAO no publica ningún medio para reprogramar o redirigir un paquete, ni un número concreto de intentos.

## Paquete DAO desaparecido, retrasado o dañado

El contrato con DAO lo tiene la tienda, pero la página de contacto de DAO también explica qué hacer al destinatario.

**Sin novedades en 48 horas.** Si el paquete no ha sido escaneado en las últimas 48 horas, DAO pide que lo comuniques como desaparecido por correo electrónico o teléfono describiendo el contenido. Recibirás confirmación por correo y DAO dispone de hasta 10 días laborables para buscarlo. Ver [seguimiento que no se actualiza](guide:tracking-not-updating).

**Entregado pero no está.** Revisa el buzón, la puerta y los alrededores, y luego contacta con DAO; si falta contenido, escribe a kundeservice@dao.as. Ver [entregado pero no recibido](guide:delivered-not-received).

**Dañado.** La página de ayuda de DAO indica avisar dentro de los 5 días laborables posteriores a la entrega (5 días en la respuesta para remitentes), con fotos del embalaje exterior e interior y del artículo dañado; las condiciones permiten reclamar por escrito hasta 7 días después de la entrega. El formulario de daños de la página desapareció, así que escribe a kundeservice@dao.as. Las condiciones limitan su responsabilidad al valor documentado o a 2.500 DKK por paquete, lo que sea menor. Los daños causados a tu propiedad por un repartidor se comunican a erstatning@dao.as.

**Inconforme con la respuesta.** Reclama primero a DAO; su licencia postal le da un mes para responder. Si discrepas de su decisión, por ejemplo por retraso o indemnización, acude a Trafikstyrelsen, que otorga la licencia a DAO. Las condiciones citan el Center for Klageløsning de Nævnenes Hus, pero Nævnenes Hus remite las quejas sobre operadores postales con licencia a Trafikstyrelsen.

**Desde fuera de la UE.** Desde el 1 de julio de 2026 los pedidos online de hasta 150 € pagan 3 € de arancel por tipo de producto; desde el 1 de noviembre de 2026 se añade una tasa europea de tramitación de 2 € por tipo de producto. Ver [paquetes retenidos en aduanas](guide:customs).

## Atención al cliente y teléfono de DAO

Datos publicados en la página de contacto de DAO (horarios en hora danesa):

- **Teléfono:** +45 72 33 08 50, de lunes a viernes de 8:00 a 19:00, sábados y domingos de 8:00 a 16:00 (hora danesa).
- **Correo electrónico:** kundeservice@dao.as, indicando tu número de paquete de 20 dígitos.
- **Formularios:** «Need help?» y comentarios, enlazados desde [la página de ayuda y contacto de DAO](https://dao.as/en/contact-dao/); el formulario de daños enlazado ya no existe.
- **Quejas sobre repartidores:** kvalitetabo@dao.as o 44 51 75 70.

> DAO advierte sobre SMS y correos falsos en su nombre por un número de casa ausente, un paquete retenido o una tasa. DAO no envía enlaces de pago. Compruébalo en la app dao o en dao.as. Si diste datos de tu tarjeta, llama a tu banco; si aprobaste una solicitud de MitID que no iniciaste, bloquea MitID.

## Seguir un paquete DAO con Peek

[Peek](/) consulta a la vez a DAO y a los demás transportistas probables —entre ellos PostNord— para un número de 20 dígitos que empiece por `0005712696` o `0005715127`. Cuando solo DAO lo conoce, ves «DAO tiene este paquete.»; cuando PostNord también lo conoce, Peek indica cuántos transportistas lo reconocen y tú eliges el tuyo. Un número de 13 o 9 dígitos requiere «Cambiar» y DAO, o un enlace de seguimiento de dao.as, que funciona con cualquier número. DAO responde entonces con el número de 20 dígitos, que se sigue a partir de ahí.

Los escaneos de DAO no traen zona horaria: Peek los interpreta en la hora local del lugar del escaneo (hora danesa en Dinamarca) y muestra el historial propio de DAO. Puede mostrar el daoSHOP hacia el que va o donde espera el paquete. Los estados conservan la redacción inglesa de DAO. Mientras tengas el paquete abierto en Peek, o el día de su entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos, y desde las 6:00 hora danesa el día en que muestra `To be delivered today`, hasta cada 2 minutos mientras responde el rastreador directo de DAO (hasta cada 5 minutos tras una comprobación por navegador); en caso contrario, cada hora. Tras dos días sin novedades (12 horas mientras siga mostrando «Etiqueta creada» o «Admitido por el transportista»), Peek consulta cada hora, y tras siete días sin novedades en «Etiqueta creada», cada 6 horas.

Si pasan cuatro días sin actualizaciones mientras el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra «Sin novedades desde hace cuatro días» (o «Sin novedades desde el {{date}}»), acompañado de la nota «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a DAO.» y el enlace «Pide ayuda a DAO».

Puedes activar notificaciones para «Cada escaneo», «Pasos importantes» o «Solo la entrega». En un envío al extranjero se muestra el número del socio y, si el enlace de DAO nombra un transportista de la lista de Peek, el paquete puede seguirse allí. DAO no da fecha estimada de llegada ni peso. Quedan fuera los nombres ocultos, tu dirección y código postal, la dirección, horario y ubicación del establecimiento, el código de recogida de 5 dígitos (que DAO envía por SMS/correo o en la app dao) y cualquier firmante. Peek no puede contactar con DAO por ti.

## Preguntas sobre el seguimiento de DAO

### ¿Qué significa «Parcel has arrived at terminal»?

El paquete ha llegado a una de las terminales de clasificación de DAO y sigue en camino.

### ¿Cuánto tarda DAO en entregar?

DAO indica a remitentes particulares de 2 a 4 días en daoSHOP; a tiendas online promete entrega a domicilio en menos de 16 horas y al día siguiente en daoSHOP. Los paquetes a domicilio llegan de noche o de madrugada los 365 días del año.

### ¿Hay una aplicación de seguimiento de DAO?

Sí, la app dao, para iPhone y Android. Añade automáticamente los paquetes dirigidos a ti y muestra el número del paquete y el código de recogida.

### ¿Es real un SMS de DAO que pide un pago?

No. DAO indica que no envía enlaces para pagar ni transferir dinero; comprueba el paquete en la app dao o en dao.as.

:::sources
- [DAO: Track your parcel](https://dao.as/en/track-your-parcel/) – formatos de número, mensaje no encontrado, página de estados
- [DAO: Find din pakke](https://dao.as/find-din-pakke/) – seguimiento y textos en danés
- [DAO: Help and contact](https://dao.as/en/contact-dao/) – teléfono, horarios, correos, formularios, redirección, código de recogida, paquetes perdidos, 5 días para daños
- [DAO: Parcels](https://dao.as/en/private-parcels/) – entregas nocturnas, buzón y puerta, más de 1.500 daoSHOP, recogida, etiqueta electrónica
- [DAO: daoSHOP parcels](https://dao.as/en/private-parcels-daoshop/) – 2 a 4 días para particulares
- [DAO: daoSHOP for businesses](https://dao.as/en/business-parcels-daoshop/) – más de 1.600 daoSHOP, entrega al día siguiente
- [DAO: daoHOME for businesses](https://dao.as/en/business-parcels-daohome/) – menos de 16 horas
- [DAO: Hjemmelevering](https://dao.as/privat-pakker-daohome/) – 365 días, límites de tamaño y peso a domicilio
- [DAO: Terms and Conditions](https://dao.as/wp-content/uploads/2025/10/Terms-and-Conditions-Dansk-Avis-Omdeling_211025.pdf) – sin firma, prueba, plazo de recogida, reclamaciones de 7 días, responsabilidad, quejas
- [DAO: Handelsbetingelser](https://dao.as/wp-content/uploads/2025/10/Handelsbetingelser-for-Dansk-Avis-Omdeling-2025_211025.pdf) – 7 días naturales, prevalece la versión danesa
- [DAO: Find daoSHOP](https://dao.as/en/find-daoshop-en/) – mapa, horarios de apertura
- [DAO: Grant access](https://dao.as/en/grant-access/) – acceso a edificios de pisos
- [DAO: Avoid fraud](https://dao.as/en/avoid-fraud/) – mensajes falsos y qué hacer
- [DAO: dao app](https://dao.as/en/daoapp/) – paquetes automáticos, número de paquete y código de recogida
- [DAO: Om dao](https://dao.as/om-dao/) – empresa, periódicos desde 1921
- [DAO: API documentation](https://api.dao.as/docs/v1) – código de barras GS1-128 de 20 dígitos
- [Trafikstyrelsen: Posttilladelse – DAO – 2024](https://www.trafikstyrelsen.dk/publikationsliste/post-og-koerekort-publikationer/2024/mar/posttilladelse-dao-2024) – respuesta a reclamaciones en un mes
- [Trafikstyrelsen: Klager over postvirksomheder](https://www.trafikstyrelsen.dk/post-og-koerekort/styrelsens-arbejde-paa-postomraadet/klager) – reclamaciones tras la decisión de la empresa
- [Nævnenes Hus: Til køber](https://naevneneshus.dk/naevnsoversigt/maeglingsteamet-for-forbrugerklager-og-forbrugerklagenaevnet/til-koeber/) – quejas postales derivadas a Trafikstyrelsen
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 €, tasa de tramitación de 2 €
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – qué lee Peek
:::
