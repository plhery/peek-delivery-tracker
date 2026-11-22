---
title: Seguimiento de SpeedX: estados, entregas fallidas y contacto
description: Rastrea un paquete de SpeedX en EE. UU.: formato del número SPX, qué significa cada estado, intentos de entrega, foto con código ZIP y atención al cliente.
slug: seguimiento-speedx
published: 2026-11-18
updated: 2026-11-21
---

SpeedX (Speed Xpress Inc., con sede en New Hyde Park, Nueva York) es una empresa de reparto de última milla que entrega compras en línea a domicilio en **Estados Unidos**. Puedes seguir un paquete en [tracking.speedx.io](https://tracking.speedx.io/) o pegar su número en el cuadro de arriba. El código procede de la tienda donde compraste: SpeedX genera la etiqueta para el comercio, de modo que el vendedor ya dispone del número antes de que SpeedX reciba físicamente el paquete.

## Número de seguimiento de SpeedX: formato y dónde encontrarlo

SpeedX indica que todos sus números empiezan por `SPX`. En la práctica se componen de `SPX`, tres letras y después 12 o 18 cifras. Estos son los formatos con caracteres inventados:

| Formato inventado | Qué identifica |
| --- | --- |
| `SPXLGA482910573649` | `SPX`, 3 letras y 12 cifras (18 caracteres). |
| `SPXLGA482910573649182736` | `SPX`, 3 letras y 18 cifras (24 caracteres). |
| `SPXPH482910573649` | `SPXPH` seguido de cifras: no es SpeedX en Estados Unidos, sino SPX Express en Filipinas, otra empresa distinta. |

Peek ignora espacios y minúsculas.

**Dónde está.** Búscalo en el correo de confirmación de envío de la tienda o en la página de tu pedido. Si no aparece, SpeedX recomienda pedírselo al comercio; [cómo encontrar un número de seguimiento](guide:find-tracking-number) reúne otros sitios donde mirar. En Estados Unidos, SpeedX también ofrece avisos por SMS cuando el paquete sale a reparto, a los que te apuntas desde su web.

**Números parecidos.** `SPX` seguido únicamente de cifras no encaja en ninguno de los dos formatos de SpeedX. Si a un código `SPX…` le sobra o le falta un dígito, compáralo con el mensaje del vendedor. Los números de SpeedX tampoco coinciden con los de otros repartidores en Estados Unidos como UniUni, [GOFO](carrier:gofo) o [USPS](carrier:usps). Si aun así el código no figura en el sistema, SpeedX sugiere confirmarlo con la tienda, porque el envío podría viajar con otra empresa de última milla.

## Qué significan los estados de seguimiento de SpeedX

La página de seguimiento de SpeedX está disponible únicamente en inglés. Estos son los estados habituales por orden de aparición:

| Estado de SpeedX | Qué significa para tu paquete | Peek muestra |
| --- | --- | --- |
| `Shipping Label Created` | La tienda creó la etiqueta. SpeedX todavía no tiene el paquete. | «Etiqueta creada» |
| `Pickup Requested` | El comercio solicitó la recogida a SpeedX, que aún no ha recibido el bulto. | «Etiqueta creada» |
| `Parcel Picked Up` | Un conductor de SpeedX recogió el paquete en las instalaciones del remitente. | «Admitido por el transportista» |
| `Processing at Origin Facility` | SpeedX ya lo tiene y lo está clasificando en su primer centro. | «En tránsito» |
| `In transit to destination facility` | En ruta entre almacenes de clasificación de SpeedX en Estados Unidos. | «En tránsito» |
| `Processing at Transit Facility` | Clasificación en un centro intermedio del recorrido. | «En tránsito» |
| `Arrival at Destination Facility` | Ha llegado al centro que atiende tu zona en Estados Unidos. | «En tránsito» |
| `Processing at Designated Facility` | Clasificado allí para asignarlo a la ruta de un repartidor. | «En tránsito» |
| `Out for Delivery` | Lo lleva un conductor para entregarlo hoy. | «En reparto» |
| `Attempted Delivery: No access` | El repartidor no pudo llegar a tu puerta: verja cerrada, edificio de apartamentos sin acceso, obras o mal tiempo. | «Intento de entrega» |
| `Delivered` | Entregado. En la web de SpeedX se añade dónde quedó dejado (por ejemplo, cuarto de correo) y una foto. | «Entregado» |
| `Waiting for collection from Parcelshop` | Tras el reparto, el paquete quedó depositado en un punto *parcelshop* a la espera de que pases a recogerlo. | «Listo para recoger» |
| `Self picked up from Parcelshop` | El paquete ya fue recogido en el *parcelshop*. | «Entregado» |
| `Parcel Void` | SpeedX marcó una incidencia en el envío. Consulta con la tienda. | «Incidencia» |
| `Exception- Parcel not found at the sort facility` | No se localizó el paquete en el centro de clasificación. Habla con el vendedor. | «Incidencia» |
| `Parcel Disposal` | SpeedX registró una baja o incidencia sin detallar el motivo. Pregunta a la tienda los pasos siguientes. | «Incidencia» |

Mientras el seguimiento muestre únicamente `Shipping Label Created`, SpeedX no ha recibido la mercancía: sigue en manos de la tienda o de su almacén logístico.

Un intento fallido puede traer otros motivos, como `Attempted Delivery:Building Closed/Weekend/Holiday` cuando un local comercial estaba cerrado; Peek también lo clasifica como «Intento de entrega». Debajo del intento, SpeedX añade una nota sobre el paso siguiente: `The package is undeliverable and schedule to redelivery` indica que volverá a salir a reparto, mientras que `The package is undeliverable and return to facility` señala que regresa al almacén. Peek muestra esa nota justo después del estado.

Cuando el último evento es un problema (`Parcel Void`, `Parcel Disposal` o paquete no hallado en planta), SpeedX lo clasifica en una categoría como `Last Mile Intercept` o `Last Mile Undelivered`. SpeedX no publica una explicación de esas categorías: acude al vendedor.

## Entrega de SpeedX en Estados Unidos, intentos fallidos y recogida

Todas las condiciones de reparto siguientes corresponden a la red de SpeedX en **Estados Unidos**:

**Paquetes enviados desde el extranjero.** SpeedX también reparte en Estados Unidos pedidos despachados desde otros países. Su servicio transfronterizo los recibe en grandes aeropuertos estadounidenses y tramita el despacho ante la aduana de EE. UU. con su propia correduría aduanera antes de integrarlos en su red de reparto. Sobre aranceles y quién los abona, consulta [paquetes retenidos en aduanas](guide:customs).

**Fines de semana.** En Estados Unidos, SpeedX reparte los sábados de 8:00 a 20:00 (hora local) en todas las zonas que cubre, y los domingos en determinadas áreas con el mismo horario. No ofrece franjas horarias concertadas ni aviso de cercanía del conductor.

**Firma y foto de entrega.** La mayoría de los paquetes de SpeedX en Estados Unidos no requieren firma; los artículos con restricción de edad, como bebidas alcohólicas, pueden exigir la firma de un adulto de 21 años o más. Si no hay nadie en casa, el conductor deja el paquete y toma una fotografía de prueba. Para verla, entra en el seguimiento desde speedx.io e introduce el código postal (ZIP code) de entrega en Estados Unidos.

**Instrucciones y cambio de dirección.** SpeedX recibe instrucciones de entrega (códigos de acceso, lugar seguro donde dejar el paquete, horario comercial o teléfono) a través de Miles, su chatbot, indicando el número de seguimiento; esas notas llegan también al conductor, con quien no es posible hablar directamente. Si avisas a tiempo, SpeedX puede corregir en Estados Unidos la calle o el número de apartamento; para cambiar de ciudad, estado o código ZIP hay que acudir al comercio.

**Intento de entrega fallido.** Si figura un intento fallido con el que no estás de acuerdo, comprueba primero tu dirección en la web de la tienda y envía luego la corrección o las indicaciones de acceso por el chat de Miles. SpeedX señala que el nuevo intento debería realizarse en un plazo de 1 a 2 días hábiles. La empresa no publica cuántos intentos realiza en total ni cuántos días conserva el paquete.

**Recogida.** SpeedX advierte de que en Estados Unidos no permite recoger paquetes en sus propios almacenes: debe entregarlos un repartidor. Aun así, algunos envíos pasan tras la ruta a un comercio colaborador (*parcelshop*): en ese caso el historial muestra `Waiting for collection from Parcelshop` para que acudas a retirarlo allí.

## Paquete de SpeedX sin movimiento, no recibido o perdido

Las gestiones siguientes aplican a envíos gestionados por SpeedX en **Estados Unidos**:

**Sin avances.** SpeedX pide contactar con el vendedor si el estado lleva tres días hábiles sin cambiar. Más detalles en [por qué no se actualiza el seguimiento](guide:tracking-not-updating).

**Marcado como entregado pero no está.** La propia página de soporte de SpeedX reconoce que a veces un paquete queda marcado como entregado antes de llegar físicamente, o se deja en un lugar inesperado de la propiedad. En Estados Unidos, revisa primero la foto de entrega y comprueba el porche delantero y trasero, el buzón o cuarto de paquetería (*mailroom*), el garaje, los laterales de la vivienda y zonas resguardadas, además de preguntar a vecinos o convivientes. Informa después a SpeedX mediante Miles y escribe al comercio para acordar la solución. Consulta también [entregado pero no recibido](guide:delivered-not-received).

**Pérdida, daños o dirección errónea.** SpeedX no tramita reclamaciones presentadas por destinatarios. Para solicitar una reclamación, reembolso o reemplazo debes dirigirte al vendedor con tu número de pedido, el código de seguimiento y las conversaciones mantenidas; en caso de daños, la tienda puede pedirte fotos. SpeedX no publica un plazo límite para ello.

**Devoluciones.** Una vez entregado el paquete, cualquier devolución se gestiona con la tienda donde compraste; SpeedX no recoge devoluciones a domicilio en Estados Unidos. Si todavía no se ha entregado, puedes pedir a Miles que detenga el reparto para que SpeedX devuelva el bulto al remitente.

## Teléfono y chat de atención al cliente de SpeedX

Canales de SpeedX para destinatarios en **Estados Unidos**, según su [página de soporte](https://speedx.io/customer-service-and-support/):

- **Chat:** Miles, el chatbot de SpeedX, todos los días de 8:00 a 20:00 ET (hora del Este de EE. UU.). Abre el seguimiento, introduce tu código y verás el icono del chat en la esquina inferior de la pantalla.
- **Teléfono en EE. UU.:** `1-949-667-6769`, de lunes a viernes de 9:00 a 21:00 EST y fines de semana de 11:00 a 20:00 EST. La misma página afirma en otro apartado que SpeedX no dispone de atención telefónica y remite a Miles, así que es habitual que te deriven al chat.
- **Correo electrónico:** `speedxops@speedx.io`.

## Seguir paquetes de SpeedX con Peek

[Peek](/) identifica a SpeedX al instante a partir de un código `SPX` de cualquiera de los dos formatos (`SPX` + 3 letras + 12 o 18 cifras) o de un enlace de tracking.speedx.io que incluya el número, y muestra «Transportista detectado» (con «Cambiar» si necesitas elegir otro).

Peek lee directamente la página de seguimiento de SpeedX y presenta el estado actual y cada escaneo con su localidad en el mapa en la hora registrada por SpeedX, además de la hora exacta de entrega y, si la página de SpeedX lo indica, el día estimado de entrega. Peek descarta tu dirección y la foto de entrega, por lo que para ver la foto debes entrar en speedx.io con tu código ZIP. Cuando SpeedX añade una nota sobre dónde se dejó el paquete, la línea de entrega la conserva tal cual, en inglés; en un enlace compartido muestra solo «Entregado», sin esa nota. No existe traspaso posterior a otro transportista.

Mientras tengas abierto el paquete en pantalla o, el día de su entrega, con las notificaciones activadas, Peek comprueba novedades hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 de la mañana (hora local) el día en que SpeedX escanea el paquete en reparto (`Out for Delivery`), mientras responda el propio rastreador de SpeedX (mediante servicios de seguimiento externos solos, cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de reparto con el paquete abierto o notificaciones activas)—. Fuera de esos supuestos, y también tras dos días sin novedades (o 12 horas en «Etiqueta creada» o «Admitido por el transportista»), Peek consulta cada hora. Puedes activar avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Si pasan cuatro días sin ningún escaneo nuevo mientras el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek avisa con el día del último escaneo («Sin novedades desde el lun 12 oct») y enlaza a la página de ayuda de SpeedX («Pide ayuda a SpeedX»). Cuando la web de SpeedX no responde, otras fuentes externas rara vez tienen datos de envíos de SpeedX, por lo que puede no haber novedades hasta que vuelva a contestar.

## Preguntas frecuentes sobre el seguimiento de SpeedX

### ¿Es fiable el seguimiento de SpeedX?

Sí. SpeedX es la marca comercial de Speed Xpress Inc., una empresa de reparto de última milla fundada en 2022 en Nueva York que entrega pedidos de comercio electrónico en Estados Unidos.

### ¿Puedo recoger mi paquete de SpeedX en persona?

No en los almacenes de SpeedX en Estados Unidos: la empresa no permite recogidas en sus centros y exige realizar el reparto. Sin embargo, si el historial muestra `Waiting for collection from Parcelshop`, el envío quedó depositado tras la ruta en un punto colaborador (*parcelshop*) y puedes retirarlo allí.

### ¿Cuánto tarda SpeedX en entregar un paquete?

SpeedX no publica plazos garantizados para destinatarios. A los comercios les ofrece entregas en Estados Unidos al día siguiente (*next-day*) y en dos días (*2nd-day*), pero cualquier fecha estimada procede del vendedor y la propia SpeedX advierte de que puede no coincidir con la fecha real de entrega.

### ¿Por qué no funciona mi número de seguimiento de SpeedX?

La web de seguimiento de SpeedX indica que es posible que el remitente aún no haya enviado a SpeedX los datos del paquete o que el código no sea válido («The sender may still need to send SpeedX the package data, or the tracking number is invalid»). SpeedX aconseja verificar el número con la tienda, ya que el pedido podría haberse enviado con otra empresa de reparto.

:::sources
- [SpeedX: Customer Service and Support](https://speedx.io/customer-service-and-support/) – prefijo SPX, glosario, SMS, fines de semana, firma, fotos, instrucciones, cambios de dirección, sin recogida en almacén, demoras, entregas marcadas antes de tiempo, reclamaciones, devoluciones, teléfono, correo y horario de Miles
- [SpeedX: Tracking](https://tracking.speedx.io/) – estados en inglés, notas complementarias, categorías y aviso de número sin datos
- [SpeedX: Tech-Enabled Last Mile Delivery](https://speedx.io/) – reparto de última milla en EE. UU., etiquetas creadas por SpeedX, entregas en 1 y 2 días y fotos de prueba de entrega
- [SpeedX: Cross-Border Shipping](https://speedx.io/cross-border-shipping/) – recepción internacional en aeropuertos de EE. UU., correduría aduanera propia y clasificación hacia su red
- [SpeedX: About Us](https://speedx.io/about-us/) – fundación en 2022 en Nueva York y cobertura en ciudades de EE. UU.
- [SpeedX: Privacy Policy](https://speedx.io/privacy-policy/) – razón social Speed Xpress Inc. y dirección en New Hyde Park
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos reconocidos, lectura de estados y notas de SpeedX y privacidad en enlaces compartidos
:::
