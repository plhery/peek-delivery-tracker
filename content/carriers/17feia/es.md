---
title: Seguimiento 17FEIA: números FEI…YQ, estados y entrega
description: Sigue un paquete de 17FEIA (一起飞) desde China: cómo es el número FEI…YQ, qué significa cada estado, quién reparte, aduanas y seguimiento en Peek.
slug: seguimiento-17feia
published: 2026-11-21
updated: 2026-11-21
---

17FEIA (一起飞, también Shanghai Hongshan International Logistics, 上海宏杉国际物流) es una empresa logística china. Recibe los paquetes en su almacén de Shenzhen o Shanghái, los envía fuera de China y, tras la aduana de importación, los entrega a un transportista encargado del reparto. Sus números empiezan por `FEI` y terminan en `YQ`. Pega el tuyo en la casilla de arriba o usa el rastreador de 17FEIA en www.17feia.com. El número te lo facilita quien envió el pedido: el vendedor o el agente de envíos desde China.

## Número de seguimiento 17FEIA: cómo es

Peek identifica los números de 17FEIA como `FEI`, dos letras, diez cifras y `YQ`: 17 caracteres en total. Las dos letras tras `FEI` indican la sucursal de 17FEIA, como `SZ` para Shenzhen o `SH` para Shanghái. Todos los ejemplos son inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `FEISZ4829105736YQ` | `FEI`, dos letras, diez cifras y `YQ`: un número de 17FEIA. Peek identifica 17FEIA solo con el número. |
| `FEISZ482910573YQ` | Solo nueve cifras: no encaja con el formato de 17FEIA. Comprueba si falta una cifra. |
| `FEISZ4829105736AB` | Dos letras distintas de `YQ` al final. Peek no señala 17FEIA solo por la forma, pero pregunta a su rastreador si lo conoce. |
| `HHWKG4829105736YQ` | Termina en `YQ`, pero lleva `HHW` delante: es un número de [HuaHan Logistics](carrier:huahan). |

En Peek, las minúsculas, espacios y guiones no importan.

**Dónde encontrarlo.** En el correo de envío, en la página de tu pedido o en tu cuenta del agente de envíos si usaste uno. Si no lo encuentras, mira [cómo encontrar tu número de seguimiento](guide:find-tracking-number). Más adelante, el registro de 17FEIA también puede dar a Peek el número propio del transportista que entrega.

## Estados de seguimiento de 17FEIA explicados

El registro de 17FEIA incluye en la mayoría de líneas una etiqueta corta en chino y un texto, de la más reciente a la más antigua. Peek muestra el texto en inglés y deduce la etapa de la etiqueta china. Las líneas propias de 17FEIA, en el orden habitual:

| Qué indica 17FEIA | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Item information received.` | 17FEIA tiene los datos del envío, pero todavía no el paquete. | «Etiqueta creada» |
| `The package is on the way to the facility.` (`包裹在去设施路上`) | Va camino del almacén de 17FEIA, que aún no lo ha recibido. | «Etiqueta creada» |
| `Warehouse receipt.` (`仓库收货`) | El almacén de 17FEIA en Shenzhen o Shanghái ha recibido el paquete. | «Admitido por el transportista» |
| `The order has information pending customer confirmation.` (`订单待客户确认`) | El almacén espera a que el cliente de 17FEIA que contrató el envío confirme el pedido. | «Admitido por el transportista» |
| `Order Confirmed.` (`订单已确认`) | Pedido confirmado. | «Admitido por el transportista» |
| `Order dispatched from warehouse.` (`订单已出库`) | El paquete ha salido del almacén. | «En tránsito» |
| `Export declaration in progress.` (`出口报关中`) | Se está tramitando la declaración de exportación en China, paso habitual antes del vuelo. No está retenido y no es la aduana de tu país. | «En tránsito» |
| `Flight has departed.` (`航班已起飞`) | El vuelo ha salido de China. | «En tránsito» |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Escala durante el trayecto y salida del siguiente vuelo. | «En tránsito» |
| `Flight Arrived at Clearance Port.` (`航班抵达清关港`) | Ha aterrizado en el lugar donde pasa la aduana de importación. | «En tránsito» |
| `Broker picked up Shipment and delivered to warehouse.` (`清关行提货到仓`) | Un agente de aduanas llevó el envío a su almacén para el despacho (o tras el levante). No es una entrega a tu domicilio. | «En aduanas» (o «En tránsito» si aparece tras `清关完成`) |
| `Customs Clearance is in progress.` (`清关中`) | La aduana de importación lo está revisando. | «En aduanas» |
| `Customs inspection in Progress, Release Pending.` (`海关查验，暂未放行`) | Tras llegar al puerto de despacho, la aduana de importación está inspeccionando el envío y aún no lo ha liberado. | «En aduanas» |
| `Customs Clearance Completed.` (`清关完成`) | La aduana de importación lo ha dejado pasar. | «En tránsito» |
| `Arrived at delivery hub，Awaiting carrie scan.` (`已交仓，等待上网`) | Entregado en el centro del transportista final. Lo siguiente es su primer escaneo («carrie» es tal cual aparece en el rastreador). | «En tránsito» |
| `包裹已做国内退回处理` (`国内退回`) | Devolución nacional en China: tras la confirmación en almacén, el paquete vuelve al remitente en China en lugar de salir del país. | «Devuelto al remitente» |

**Dos líneas que no mueven el estado.** `Estimated departure date:` seguido de una fecha (`预计起飞日期`) es una previsión anotada antes del vuelo, no un escaneo: Peek la descarta. `Flight delayed.` (`航班延误`) indica retraso en el vuelo: Peek mantiene la etapa de la línea anterior.

**La barra de progreso.** La página de seguimiento de 17FEIA muestra también una barra desde `仓库收货` (recepción en almacén) pasando por `飞机起飞` (despegue) y `飞机落地` (aterrizaje) hasta `成功签收` (firmado/entregado). Peek no copia la barra, pero clasifica una línea con `成功签收` como «Entregado».

**Las líneas del transportista que reparte.** Tras el traspaso, el rastreador de 17FEIA puede retransmitir escaneos del transportista final con la etiqueta `运输途中` (en tránsito), en sus propias palabras y con su lugar: por ejemplo DHL (`The international shipment has been processed in the export parcel center`), UPS en Estados Unidos (`Arrived at Facility`, `Departed from Facility`, `On the Way, Delivery will be delayed by one business day.`) o un socio en el Reino Unido (`Shipment received in depot`). Peek los interpreta según su texto: una entrada en almacén sigue en «En tránsito», mientras que el reparto o la entrega hacen avanzar el estado.

**Por qué las horas parecen desordenadas.** Los pasos en almacén en China (incluida una devolución nacional) van en hora de Pekín (UTC+8). Los escaneos retransmitidos del transportista final llevan hora local cuando el lugar define una sola zona horaria. Las líneas de vuelo y aduana no indican lugar y la zona horaria que 17FEIA les asigna no siempre corresponde a la real: guíate por el orden de las líneas.

## Quién entrega los paquetes de 17FEIA

Tras la aduana de importación, 17FEIA entrega el paquete a un transportista que lo lleva hasta ti. Peek no pudo consultar la web de 17FEIA, por lo que esta página no ofrece una lista completa de socios ni zonas de reparto.

**Qué muestra el código de Peek.** Peek reconoce tres nombres que 17FEIA usa para sus socios: `DPD-DE` para [DPD Germany](carrier:dpd-de), `DE-DHL` para [DHL](carrier:dhl) en Alemania y `FEDEX-GROUND` para [FedEx](carrier:fedex). Reconoce cinco destinos en el registro de 17FEIA: Alemania, Francia, Países Bajos, Polonia y Estados Unidos. Un historial del código muestra a DHL en Alemania recibiendo un paquete con destino a Francia para pasarlo a la empresa que reparte allí.

**Entrega fallida.** Los intentos y la recogida dependen de las normas del transportista final: pregúntale con su propio número. Consulta [entrega fallida](guide:missed-delivery).

**Aduanas en la UE.** Los productos comprados por internet fuera de la UE pagan un derecho de aduana temporal de 3 € por artículo en paquetes de hasta 150 € desde el 1 de julio de 2026 y una tasa de tramitación de la Unión de 2 € por artículo, cualquiera que sea el valor, desde el 1 de noviembre de 2026. Un artículo son las mercancías con la misma clasificación arancelaria, descripción y, cuando procede, origen. Peek no pudo comprobar cómo se cobran en un paquete de 17FEIA: pregunta al vendedor o a tu agente de envíos si venían incluidos en el precio. Fuera de la UE, los importes dependen de tu país. Mientras la aduana de importación inspecciona el envío sin liberarlo, 17FEIA muestra `Customs inspection in Progress, Release Pending.` Consulta [paquetes retenidos en aduanas](guide:customs) y, antes de pagar tras recibir un SMS o correo, [SMS falsos de entrega](guide:delivery-scam-texts).

## Paquete de 17FEIA sin novedades, perdido o devuelto

17FEIA trabaja para quien contrató el envío: el vendedor o el agente de envíos que utilizaste. Si el paquete se retrasa, se pierde, llega dañado o se devuelve, habla primero con ellos.

**Número no encontrado.** Si no conoce el número, el rastreador de 17FEIA no muestra avances. Peek muestra «Esperando a 17FEIA» y sigue consultando. Si tras dos días 17FEIA sigue sin reconocer el número y ninguna otra fuente ha respondido, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a comprobarlo una vez al día, y una vez a la semana pasada una semana desde que lo añadiste. Revisa el código frente al mensaje de envío: `FEI`, dos letras, diez cifras, `YQ`.

**Parado en `The order has information pending customer confirmation.`** Si usaste un agente de envíos, revisa tu cuenta o tus mensajes allí. En caso contrario, pregunta al vendedor.

**Sin noticias tras el vuelo.** Después de `Flight has departed.`, pueden pasar días sin una línea nueva. Tras cuatro días sin cambios, Peek lo señala («Sin novedades desde hace cuatro días»). Más en [por qué el seguimiento no se actualiza](guide:tracking-not-updating) y [seguir un paquete desde China](guide:tracking-from-china).

**Entregado, pero no ha llegado.** Revisa alrededor de casa y pregunta a los vecinos; luego contacta con el transportista final y el vendedor. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Perdido o devuelto.** Pide al vendedor o al agente un reembolso o un nuevo envío. Más en [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de 17FEIA

La web de 17FEIA respondió a Peek con una comprobación antibot, por lo que esta página no recoge teléfonos ni correos de 17FEIA. Sobre tu pedido, contacta con el vendedor o el agente de envíos que contrató el transporte: son los clientes de 17FEIA. Para la entrega en destino, dirígete al transportista final.

## Seguir un paquete de 17FEIA con Peek

[Peek](/) identifica 17FEIA al instante a partir de un número `FEI…YQ` («Transportista detectado»): ningún otro transportista seguido por Peek emplea este formato. También sirve un enlace a la página de seguimiento de 17FEIA con el número detrás de `orderNumber=`. No hace falta código postal ni cuenta.

Para cualquier otro número, como el del transportista final, pégalo igualmente: Peek sigue más de 4500 transportistas. Cuando la forma encaja con varios transportistas sin seguridad, Peek muestra «Comprobando servicios de seguimiento…» mientras pregunta; si no encaja con ninguno, lo busca en cuanto lo añades. Para elegir el transportista tú mismo, pulsa «Cambiar» y busca `17FEIA`, `Feia` o `一起飞`, o usa «Cambiar transportista» en un paquete ya añadido. Peek solo pregunta a 17FEIA por números formados por `FEI`, dos letras, diez cifras y dos letras: para cualquier otra forma indica que 17FEIA «no emite números como este».

Peek muestra el estado, hasta las 100 líneas más recientes en el inglés de 17FEIA con el lugar cuando consta y el país de destino. Lee los pasos de almacén en hora de Pekín y los escaneos retransmitidos con un país de zona única (o un estado estadounidense de zona única) en su hora local, convertidos a la zona de tu dispositivo; las líneas sin lugar o sin zona definida (vuelos, aduana o un depósito sin país) conservan la hora local tal como la escribe 17FEIA. Peek deja fuera la barra de progreso, el origen, los códigos postales y el peso. 17FEIA no ofrece fecha prevista de entrega, nombre del servicio, datos de remitente o destinatario ni prueba de entrega.

Cuando 17FEIA nombra a DPD Germany, DHL o FedEx con un número propio de su formato, la tarjeta del paquete lo indica (por ejemplo, «Entrega con DPD Germany») y añade ese número tras el de 17FEIA. Mientras ese transportista solo tenga el anuncio sin escaneo propio, la tarjeta muestra «Anunciado a …, aún no recibido». En cuanto registra un escaneo al menos tan reciente como el último de 17FEIA, Peek sigue el paquete ahí y conserva el historial de 17FEIA. Si no puede contactar con el rastreador de 17FEIA, Peek consulta servicios de seguimiento y muestra su historial en sus propias palabras.

Mientras tienes el paquete abierto en pantalla o el día de la entrega con las notificaciones activadas, y mientras responde el rastreador de 17FEIA, Peek comprueba hasta cada 10 minutos, y hasta cada 2 minutos desde las 6:00 en la hora del transportista el día en que sale a reparto; el resto del tiempo, cada hora. A través de servicios de seguimiento comprueba cada hora, y hasta cada 15 minutos el día de reparto mientras lo tienes abierto o con notificaciones activadas. Tras dos días sin novedades (12 horas mientras sigue en «Etiqueta creada» o «Admitido por el transportista»), Peek consulta una vez por hora. Puede avisarte de «Cada escaneo», «Pasos importantes» o «Solo la entrega»; «Pasos importantes» incluye la aduana en el puerto de despacho, no la declaración de exportación en China. Peek no puede contactar con 17FEIA, el vendedor ni tu agente de envíos por ti.

## Preguntas sobre el seguimiento de 17FEIA

### ¿Qué es 17FEIA (一起飞)?

Una empresa logística china, también llamada Shanghai Hongshan International Logistics (上海宏杉国际物流). Envía paquetes fuera de China desde su almacén en Shenzhen o Shanghái y, tras el despacho aduanero de importación, los entrega a un transportista para el reparto. Sus números empiezan por `FEI` y terminan en `YQ`.

### ¿Dónde puedo seguir un número FEI…YQ?

En la casilla de arriba o en el rastreador de 17FEIA en www.17feia.com. Usa el número completo: `FEI`, dos letras, diez cifras y `YQ`.

### ¿Qué significa «Awaiting carrie scan» en 17FEIA?

La línea completa es `Arrived at delivery hub，Awaiting carrie scan.`: el despacho de importación ha concluido y 17FEIA ha depositado el paquete en el centro del transportista final, que todavía no lo ha escaneado. Su propio seguimiento puede adelantarse pronto al de 17FEIA.

### ¿Quién reparte los paquetes de 17FEIA?

El transportista al que 17FEIA entrega el envío tras la aduana de importación. El registro de 17FEIA puede nombrarlo junto con su número: cuando se trata de DPD Germany, DHL en Alemania o FedEx, la tarjeta de Peek lo muestra. Si no, pregunta al vendedor.

:::sources
- [Universal Parcel Scraper: 17FEIA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/17feia) – nombres, formato `FEI…YQ` y sucursales (`SZ`/`SH`), rastreador y enlace `orderNumber=`, etiquetas chinas, textos ingleses y etapas (incluidas inspección aduanera, `国内退回`, cambio de etapa de `清关行提货到仓` tras `清关完成` y líneas retransmitidas de DHL/UPS/depósito en Reino Unido), barra de progreso, zonas horarias, socios (`DPD-DE`, `DE-DHL`, `FEDEX-GROUND`), destinos y 100 escaneos más recientes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – otros formatos terminados en `YQ` (`HuaHan Logistics`), servicios de seguimiento de respaldo y traspaso al transportista local
- [Comisión Europea: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – derecho de aduana temporal de 3 € por artículo hasta 150 € desde el 1 de julio de 2026, tasa de tramitación de la Unión de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tasa de 2 € por artículo cualquiera que sea el valor, definición de artículo
:::
