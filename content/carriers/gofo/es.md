---
title: GOFO Express rastreo: número GFUS, estados y qué hacer
description: Rastrea tu paquete de GOFO Express en Estados Unidos: cómo es el número de rastreo, qué significa cada estado en inglés y qué hacer si tu paquete no avanza.
slug: gofo-express-rastreo
published: 2026-11-02
updated: 2026-11-12
---

GOFO Express es una empresa de paquetería que reparte en Estados Unidos: recoge los paquetes en el almacén del remitente y los lleva de centro en centro hasta la estación desde la que se reparten. Para rastrear tu paquete, pega el número de rastreo en la casilla de arriba; GOFO tiene además su propio rastreador, en gofo.com/us/track. El número te lo da la tienda que te envió el pedido. Si no lo encuentras, mira [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number).

## Número de rastreo de GOFO: cómo es

GOFO usa tres formas de número en Estados Unidos. Aquí van, con cifras inventadas, junto a una que se les parece:

| Número (inventado) | Qué es |
| --- | --- |
| `GFUS12345678901234` | GFUS y 14 cifras: el número de guía de GOFO en Estados Unidos. Si tras GFUS se escribe una letra O en vez de un cero, Peek la lee como cero. |
| `GF1234567890123` | GF y 13 cifras: una serie más antigua que GOFO sigue rastreando. |
| `CR123456789012` | CR y 12 cifras: otra serie antigua de GOFO. |
| `YT1234567890123456` | YT y 16 cifras: es de [YunExpress](carrier:yunexpress), aunque GOFO haga la entrega. |

**Dos números para un mismo paquete.** Algunos paquetes llevan a la vez un número GFUS, la guía, y un número de rastreo CR o MGP (MGP y 13 cifras). Con cualquiera de los dos se encuentra el mismo paquete, en Peek y en el rastreador de GOFO. En los paquetes de DHgate que GOFO entrega por cuenta de DHLink, el número de rastreo es el propio de DHLink, `FC` y 13 cifras, y el rastreador de GOFO también encuentra el paquete con él.

**Números YT.** Cuando GOFO entrega en Estados Unidos un paquete de YunExpress, el rastreo de YunExpress muestra también los escaneos de GOFO, y el rastreador de GOFO acepta el mismo número YT.

**Números de GOFO de otros países.** GOFO tiene redes en otros países, cada una con sus propios números: los que empiezan por `GFCA` son de GOFO Canada; por `GFFR` o `CIFR`, de GOFO France; por `GFIT` o `CIIT`, de GOFO Italy; por `GFES` o `CIES`, de GOFO Spain, y por `GFNL` o `CINL`, de GOFO Netherlands. Peek los reconoce al momento y los rastrea con la red de su país.

## Estados de rastreo de GOFO: qué significa cada uno

Los escaneos de GOFO que lee Peek están en inglés. Estos son los que puede encontrar un paquete, más o menos en orden; no todos pasan por todos:

| GOFO escribe | Qué significa para ti |
| --- | --- |
| `Shipping Label Created` | El remitente creó la etiqueta. GOFO todavía no tiene el paquete. |
| `Pick Up Start` | GOFO salió a recoger el paquete al remitente, pero aún no lo tiene. |
| `Pickup Exception, GOFO has not Received the Package from the Warehouse yet.` | GOFO todavía no ha recibido el paquete del almacén del remitente. |
| `Pick Up Success` | GOFO recogió el paquete. |
| `Arrived at GOFO Regional Origin Hub` | El primer escaneo del paquete en un centro de GOFO. |
| `Arrived at Sorting Center` | Llegó a un centro de clasificación, antes del centro regional. |
| `Arrived at GOFO Regional Hub` | Llegó a un centro regional de GOFO. |
| `Processing at GOFO Facility` | Lo están clasificando en una instalación de GOFO. |
| `Departed GOFO Regional Hub` | Salió del centro regional. |
| `Loaded on Vehicle` | Lo cargaron en un vehículo para el siguiente trayecto. |
| `In Transit to Next Facility` | Va camino de la siguiente instalación de GOFO. |
| `Arrived at GOFO Delivery Station` | Llegó a la estación de GOFO desde la que se reparte. |
| `Departed GOFO Delivery Station` | Salió de la estación de reparto. |
| `Out for Delivery` | Va con el repartidor. |
| `Delivered` | Entregado. |
| `Delivery Exception, Business Closed` | El repartidor no pudo entregarlo porque el negocio estaba cerrado. |
| `Incorrect/Insufficient Address, Additional Details Required for Delivery.` | La dirección es incorrecta o incompleta, a veces al día siguiente de un intento fallido: el paquete espera a que alguien complete los datos que faltan. |
| `Label Cancelled` | Se anuló la etiqueta: con ese número no viene ningún paquete. |

`Delivery Exception` es un intento de entrega fallido, y el motivo que va después de la coma cambia de un intento a otro; `Business Closed` es solo uno. Peek pone entonces el paquete en «Intento de entrega» y copia la línea de GOFO bajo «Lo que dice GOFO Express». Con `Incorrect/Insufficient Address…`, el estado pasa a «Incidencia»: avísale a la tienda para que corrija la dirección. Con `Label Cancelled`, el estado también pasa a «Incidencia» y no llega ningún escaneo más: pídele a la tienda que te confirme si tu pedido salió con otro número.

**Horas.** GOFO fecha cada escaneo con la zona horaria que asigna a cada centro, y la creación de la etiqueta, siempre con la del Pacífico. Peek te muestra cada hora en la zona de tu celular o computadora y en formato de 24 horas (15:40 son las 3:40 p. m.): una etiqueta creada a las 10 p. m., hora del Pacífico, aparece como 01:00 del día siguiente si estás en la hora del Este.

## Paquete de GOFO retrasado, sin movimiento o no entregado: qué hacer

**Solo `Shipping Label Created`, `Pick Up Start` o `Pickup Exception`.** GOFO todavía no tiene el paquete. Si pasan varios días así, pregúntale a la tienda si ya lo envió. Más en [el seguimiento de mi paquete no se actualiza](guide:tracking-not-updating).

**Parado en el camino.** Si pasan cuatro días sin escaneos nuevos antes de que el paquete salga a reparto, avísale a la tienda o usa el enlace de ayuda que entonces te muestra Peek (más abajo).

**Intento de entrega fallido.** Lee el motivo que da GOFO después de la coma. Si depende de ti, como un negocio cerrado a la hora del reparto, o si hay un error en tu dirección, avísale a la tienda.

**Entregado, pero no lo tienes.** Peek no muestra las fotos de entrega que guarda GOFO. Avísale a la tienda y mira [paquete entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Pídele a la tienda un reembolso o un nuevo envío; más en [paquete perdido](guide:lost-parcel) y [paquete devuelto al remitente](guide:returned-to-sender).

## Atención al cliente de GOFO Express

Para cualquier problema con tu pedido, habla primero con la tienda que te lo vendió: es ella quien envió el paquete con GOFO. Si el paquete pasa cuatro días sin escaneos nuevos antes de salir a reparto, aunque solo tenga la etiqueta, Peek muestra un aviso con el enlace «Pide ayuda a GOFO Express», que abre la página de atención al cliente de GOFO en Estados Unidos (gofo.com/us/customer-service).

## Rastrear un paquete de GOFO Express con Peek

Con un número `GFUS`, [Peek](/) reconoce GOFO Express al momento («Transportista detectado»). Con un número `GF` o `CR`, le pregunta a GOFO si tiene el paquete («Comprobando servicios de seguimiento…»); si lo tiene, verás «GOFO Express tiene este paquete.» y Peek lo rastrea con GOFO. Da igual que el número lleve espacios. [Cainiao](carrier:cainiao) sigue algunos pedidos de AliExpress con el número `GFUS` de GOFO: si GOFO solo muestra la etiqueta desde hace dos días, Peek consulta también a Cainiao por el tramo anterior. Más en [pedidos de AliExpress](guide:aliexpress-orders).

Mientras GOFO no conozca un número `GF` o `CR`, Peek sigue comprobando y verás «Esperando el primer escaneo de GOFO Express». Si sabes que es de GOFO, elígelo con «Cambiar». Con un número `GFUS`, o una vez elegido GOFO, verás «Esperando al transportista» hasta el primer escaneo. También puedes pegar un enlace de rastreo de GOFO para Estados Unidos (de gofo.com/us o gofoexpress.com) que lleve `searchID=` y el número.

Peek muestra el estado, cada escaneo con la ciudad y el estado que indica GOFO, esos lugares en un mapa, la hora de entrega y el país de destino. Los escaneos salen en inglés, como los escribe GOFO, salvo la entrega, que aparece como «Entregado», sin el detalle que GOFO añade a veces tras la coma. GOFO calcula una fecha estimada de entrega, que Peek muestra hasta que el paquete sale a reparto, se entrega, se devuelve o llega a un punto de recogida; Peek deja fuera las fotos de entrega, las coordenadas y el peso.

Peek consulta a GOFO cada 30 minutos, también con el paquete en reparto, y espera una hora tras una consulta fallida. Tras dos días sin novedades, o tras 12 horas si el paquete sigue en «Etiqueta creada» o «Admitido por el transportista», Peek pasa a consultar una vez por hora. Puede avisarte con «Cada escaneo», «Pasos importantes» o «Solo la entrega». No puede comunicarse con GOFO ni con la tienda por ti.

## Preguntas sobre el rastreo de GOFO Express

### ¿Qué es GOFO Express?

Es una empresa de paquetería que reparte en Estados Unidos. GOFO tiene también redes en Canadá, Francia, Italia, España y los Países Bajos, cada una con sus propios números.

### ¿Por qué no funciona mi número de rastreo de GOFO?

Comprueba que sea un número de GOFO para Estados Unidos: GFUS, GF, CR o MGP. Los que empiezan por GFCA, GFFR, GFIT, GFES o GFNL son de las redes de otros países, y los YT, de YunExpress. Un paquete de DHgate que entrega GOFO también se puede buscar en el rastreador de GOFO con el número FC de DHLink. Si la tienda acaba de darte el número, puede que GOFO aún no lo conozca: Peek seguirá comprobando. Si dice `Label Cancelled`, con ese número no llegará nada: pídele a la tienda el número correcto.

### ¿Qué significa Shipping Label Created en GOFO?

Que el remitente creó la etiqueta de envío y GOFO todavía no tiene el paquete. Después suelen venir `Pick Up Start`, cuando GOFO sale a recogerlo, y `Pick Up Success`, cuando ya lo tiene.

### ¿Por qué mi pedido de AliExpress tiene un número de GOFO?

Porque Cainiao, la logística de AliExpress, sigue algunos pedidos con el número GFUS de GOFO, que hace la entrega en Estados Unidos. Con un número GFUS, Peek pregunta a GOFO, y también a Cainiao mientras GOFO solo muestra la etiqueta.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – lo que lee y muestra Peek: números de GOFO y de sus redes en otros países, GFUS, CR y MGP del mismo paquete, números FC de DHLink, números YT, rastreador y página de ayuda de GOFO, escaneos y etapas, zonas horarias, lo que Peek deja fuera, Cainiao
:::
