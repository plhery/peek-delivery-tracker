---
title: Seguimiento TForce Freight: números PRO y reclamaciones
description: Sigue un envío de carga TForce Freight en EE. UU. o Canadá: qué es el número PRO, significado de cada estado, entrega a domicilio y reclamaciones.
slug: seguimiento-tforce-freight
published: 2026-11-22
updated: 2026-11-22
---

TForce Freight es un transportista de carga fraccionada (LTL): mueve palés y mercancía pesada entre sus centros de servicio en Estados Unidos y todo Canadá, además de más de 600 puntos en México. Si un pedido voluminoso u otro envío de carga viene con TForce, síguelo con su número PRO, que te facilita la tienda o la empresa que contrató el flete, pegándolo arriba. El rastreador propio de TForce está en la página Tracking de www.tforcefreight.com.

## Número de seguimiento de TForce Freight: el número PRO

La página de seguimiento de TForce busca envíos por número PRO, o por conocimiento de embarque (BOL) o número de orden de compra (PO) junto con un rango de fechas de envío. Peek rastrea por PRO (dígitos inventados):

| Número (inventado) | Qué es |
| --- | --- |
| `482917363` | El PRO: nueve dígitos. El último es un dígito de control: el resto de dividir los ocho primeros entre 7, así que siempre va de 0 a 6. |
| `48291736` | Un PRO sin el 0 inicial. El rastreador de TForce añade el 0, igual que Peek en cuanto eliges TForce Freight. |
| `2205482917363` | Un número PARS usado por agentes de aduanas para carga hacia Canadá: 2205 seguido del PRO. Pégalo entero: Peek consulta a TForce con el PRO que contiene. |

Un PRO de nueve dígitos terminado en 7, 8 o 9 tiene un error tipográfico. El catálogo de Peek también conoce a TForce Freight como UPS Freight y TForce.

**Dónde encontrarlo.** En el correo de envío de la tienda o en tu página de pedido; en envíos entre empresas, pregúntale al remitente. Consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Misma forma, otros transportistas.** Nueve dígitos con el mismo dígito de control también encajan con [TNT](carrier:tnt) y XPO. TForce Logistics (TForce Final Mile), cuyos números empiezan por `TF`, es un transportista aparte en Peek. Si tu correo nombra alguno de ellos, elige ese transportista. Más en [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de TForce Freight

El rastreador de TForce muestra un estado junto al PRO, como `In Transit` o `Delivered`, y debajo el historial, con lo más reciente arriba, cada línea con localidad y estado del centro de servicio, fecha y hora. Las líneas no llevan código: Peek interpreta su texto. En el orden habitual:

| TForce Freight indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `SHIPMENT HAS BEEN PICKED-UP.` | El conductor recogió la carga del remitente. | «Admitido por el transportista» |
| `SHIPMENT IS AT A TFORCE FREIGHT LOCATION` | Está en el centro de servicio de origen. | «En tránsito» |
| `DEPARTURE` | Ha salido de un centro de servicio. | «En tránsito» |
| `SHIPMENT HAS ARRIVED AT A SERVICE CENTER.` | Llegó a un centro de servicio en ruta o al encargado de repartirte. | «En tránsito» |
| `SENT EMAIL. TFORCE FREIGHT IS ATTEMPTING TO OBTAIN APPOINTMENT INFORMATION` | TForce ha escrito al destinatario para concertar cita de entrega. Respóndelo. | «En tránsito» |
| `A DELIVERY APPOINTMENT HAS BEEN SCHEDULED WITH THE CONSIGNEE.` | Se ha acordado un día de entrega. A veces se registra tras la entrega. | «En tránsito» |
| `OUT FOR DELIVERY` | En el camión de reparto. | «En reparto» |
| `Shipment not delivered. Delivery attempted after normal business hours. Tforce will attempt delivery on next business day we service this area.` | No entregado en esta ruta. Cualquier línea que empiece por `Shipment not delivered.` indica intento fallido con su motivo detrás. | «Intento de entrega» |
| `SHIPMENT HAS BEEN DELIVERED TO THE CONSIGNEE.` | Entregado. La web de TForce muestra quién firmó. | «Entregado» |

Un estado `Delivered` junto al PRO aparece siempre como «Entregado»; en caso contrario decide la línea más reciente reconocida por Peek y el resto se muestra tal como lo escribe TForce. La web de TForce también permite recibir avisos por correo. Más en [significado de los estados](guide:tracking-statuses).

## Cómo entrega TForce Freight

TForce lleva la carga hasta el centro de servicio de tu zona y la entrega en camión. Su rastreador ofrece un día estimado de entrega, que Peek muestra hasta que se entrega, se devuelve o vence la fecha. El código de Peek no traspasa el envío a ningún otro transportista.

La tarifa normativa de TForce (LTL Rules Tariff UPGF 102-T, vigente desde el 17 de agosto de 2026) detalla las condiciones de entrega:

- **Domicilios.** Donde sus camiones pueden circular legalmente, TForce debe acordar primero fecha y hora aproximada contigo o con el remitente. Un negocio en una vivienda cuenta como domicilio. Entrega junto a la entrada en planta baja, puerta de garaje o porche, o bien recoges la carga en su terminal. La entrega residencial tiene un cargo adicional a cuenta de quien la pidió o paga el flete.
- **A pie de camión.** La entrega estándar es descargar junto al camión. La entrada al interior y la plataforma elevadora cuestan aparte: pregunta a quien reservó el envío si están incluidas.
- **Aviso de llegada.** Si los documentos lo solicitan, TForce avisa al destinatario como tarde el siguiente día laborable tras llegar al centro de reparto, normalmente por teléfono o correo electrónico.
- **No entregado.** Si la carga se rechaza o no se localiza al destinatario, TForce comunica al remitente su almacenaje y pueden aplicarse costes de custodia. Un nuevo intento tiene coste salvo que recojas la carga en el centro de servicio; en domicilio, solo si faltaste a una cita acordada.

**Hacia Canadá.** Como explica la página Canadá de TForce, la carga procedente de EE. UU. pasa aduanas en la frontera mediante un agente de aduanas. La carga sin despachar espera en un almacén fiscal en destino con gastos de almacenaje. El importador registrado paga honorarios del agente, aranceles e impuestos.

## Envío de TForce Freight no encontrado, retrasado, dañado o perdido

**No encontrado.** Para un PRO desconocido, el rastreador de TForce muestra `Not Available` y `Please check ProNumber` con el número. Peek muestra entonces «Esperando a TForce Freight» si elegiste TForce Freight, o «Todavía no hay historial de seguimiento» si no, y vuelve a consultar más tarde. Comprueba cada dígito y pregunta al remitente si TForce ya recogió la carga.

**Un dígito incorrecto.** Un PRO cuyo último dígito falla el control no pertenece a TForce y Peek nunca consulta a TForce con él. Si eliges TForce Freight igualmente, Peek indica que «no emite números como este» y muestra «Revisa el número de seguimiento».

**Sin actualizaciones.** Tras cuatro días sin nuevos escaneos en tránsito (fuera de aduana o incidencia marcada), Peek muestra «Sin novedades desde el…», recuerda que «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a TForce Freight.» y ofrece «Pide ayuda a TForce Freight». Pregunta a la tienda o al remitente. Consulta [cuando el seguimiento no se actualiza](guide:tracking-not-updating).

**Entrega fallida.** Tras una línea `Shipment not delivered.`, Peek señala el envío («Consulta las instrucciones del transportista»). Llama a TForce o pide a quien contrató el transporte que programe otro reparto. Más en [entregas fallidas](guide:missed-delivery).

**Faltante o dañado al entregar.** La página Cargo Claims Information de TForce pide revisar siempre la carga y avisar al conductor en el acto:

- Cuenta los bultos frente al recibo de entrega y abre las cajas dañadas con el conductor.
- Anota exactamente qué falta o está dañado tanto en tu copia como en la de TForce.
- No rechaces el envío salvo que el daño deje la mercancía inservible.
- Pide al responsable de Over, Short & Damage (OS&D) de tu centro de servicio local que rastree las piezas faltantes.

**Daño descubierto después.** Comunícalo en un plazo de 5 días laborables tras la entrega y pide una inspección, dejando el embalaje tal como llegó. El informe de inspección no equivale a una reclamación.

**Presentar una reclamación.** Una reclamación es una solicitud escrita de pago del propietario de la mercancía. En compras online, empieza por la tienda que contrató el envío. Reglas de TForce:

- Preséntala dentro de los nueve meses posteriores a la entrega, o desde la fecha de envío si no llegó nada.
- Usa el Standard Claim Form de TForce o una carta indicando envío e importe, con la factura del proveedor, factura de flete, conocimiento de embarque y facturas de reparación si las hay. Envíala por correo postal o fax (abajo), no por ambos.
- TForce acusa recibo en 30 días. Su responsabilidad se limita a 25,00 $ por libra y bulto —menos en ciertos bienes—, salvo cobertura extra contratada en el conocimiento de embarque.

Si consta entregado pero no llegó nada, mira [entregado pero no recibido](guide:delivered-not-received). Más en [paquete dañado](guide:damaged-parcel) y [paquete perdido](guide:lost-parcel).

## Atención al cliente y contacto de TForce Freight

Datos de www.tforcefreight.com (páginas Tracking, Fact Sheet, Rules Tariffs con el LTL Rules Tariff UPGF 102-T, Canada, Cargo Claims Information, Contact Us con Email TForce Freight Customer Support y Service Information):

- **Atención al cliente:** (800) 333-7400, gratuito desde EE. UU., Canadá, Guam, Puerto Rico y las Islas Vírgenes de EE. UU., para seguimiento, prueba de entrega e incidencias. Desde otros países: (804) 231-8555.
- **Correo electrónico:** formulario Email TForce Freight Customer Support desde la página Contact Us de TForce, con campo para el PRO.
- **Departamento de reclamaciones:** (888) 653-9720 para consultar el estado de una reclamación. Los expedientes se envían a Claims Processing, TForce Freight, PO Box 1216, Richmond, VA 23218, o por fax al (866) 580-1944.
- **Tu centro de servicio:** el Service Center Directory, en Service Information de www.tforcefreight.com.

A menudo es más rápido acudir a la tienda o empresa remitente, que tiene la reserva con TForce.

## Seguir un envío de TForce Freight con Peek

La forma de un PRO no basta para identificar a TForce: nueve dígitos encajan también con [TNT](carrier:tnt), XPO y otros transportistas. Pégalo igualmente: Peek sigue más de 4500 transportistas. Mientras aparece «Comprobando servicios de seguimiento…», Peek pregunta a los transportistas compatibles; si solo TForce conoce el envío, verás su nombre junto a «tiene este paquete». Para un número PARS (`2205` y el PRO), TForce va primero; para un PRO de ocho dígitos sin cero inicial, elige TForce Freight con «Cambiar» en «Encaja con este número» (o usa «Cambiar transportista» después). Un enlace al rastreador de TForce (`proNumbers=`) o al de UPS Freight (`TrackProNumber=`) identifica a TForce al instante («Transportista detectado»).

Peek consulta a TForce solo con el PRO. Muestra el estado, cada paso con ciudad y estado, el día estimado de entrega, el país de destino en el mapa, el servicio, el peso en tus unidades, el nombre y localidad del destinatario y quién firmó tras la entrega; omite remitente, cita de entrega, números BOL y PO, número de piezas, remolques, códigos de terminal y documento de prueba de entrega. «Abrir la web de TForce Freight» abre tu número (o el PRO de un PARS) en el rastreador de TForce. Si no responde o pide CAPTCHA, Peek puede consultar [servicios de seguimiento](guide:universal-tracker).

TForce muestra cada paso en la hora local del centro de servicio, sin zona horaria. Peek la interpreta según el reloj de la localidad —si no, del estado o provincia, de una línea vecina o del destino, y en último término en hora del Este— y la muestra en tu zona horaria («Horas en tu zona horaria:»).

Peek consulta directamente a TForce hasta cada 10 minutos, y hasta cada 2 minutos desde las 6:00 según el reloj del último escaneo (hora del Este por defecto) el día en que TForce lo marca en reparto, mientras el envío esté abierto en Peek o el día de entrega con las notificaciones activadas. Tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora. Para las notificaciones puedes elegir «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Peek no puede contactar por ti con TForce, la tienda o el remitente, ni presentar reclamaciones.

## Preguntas frecuentes sobre TForce Freight

### ¿Qué es un número PRO de TForce Freight?

El número de seguimiento de TForce: nueve dígitos donde el último es un dígito de control del 0 al 6. Te lo da la tienda o la empresa que reservó el flete.

### ¿Puedo seguir TForce Freight sin número PRO?

En la web de seguimiento de TForce, sí: por conocimiento de embarque (BOL) o número de orden de compra (PO) con un rango de fechas de envío. Peek solo sigue por PRO.

### ¿TForce Freight entrega a domicilio?

Sí, donde sus camiones pueden entrar legalmente, con un cargo adicional. TForce acuerda primero fecha y hora aproximada contigo o el remitente y entrega junto a la entrada en planta baja, garaje o porche. La entrega interior y la plataforma elevadora también tienen coste extra.

### ¿Cuánto tiempo tengo para reclamar a TForce Freight?

Nueve meses desde la entrega, o desde la fecha de envío si no llegó nada. Los daños descubiertos después de la entrega deben comunicarse en 5 días laborables.

### ¿Cuál es el teléfono de atención al cliente de TForce Freight?

(800) 333-7400, gratuito desde Estados Unidos y Canadá. Desde fuera: (804) 231-8555.

:::sources
- [Universal Parcel Scraper: TForce Freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tforce-freight) – nombres, formatos PRO, enlaces, textos y etapas, mensaje no encontrado, datos conservados por Peek, horarios
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportistas que comparten el formato, sin traspaso, servicios de seguimiento de respaldo
:::
