---
title: Seguimiento de JD Logistics: números JD, estados y contacto
description: Rastrea un paquete de JD Logistics (京东物流): formatos JD y VG, significado de los estados en inglés y chino, almacenes en Europa, aduanas y atención al cliente.
slug: seguimiento-jd-logistics
published: 2026-11-18
updated: 2026-11-21
---

JD Logistics (京东物流, Jingdong Logistics) es la filial logística del grupo JD.com, cotizada en la Bolsa de Hong Kong (`HKEX: 2618`). Fuera de China opera bajo las marcas JoyLogistics y JoyExpress: envía mercancía almacenada por vendedores en sus centros logísticos de **Francia**, **Alemania**, **Países Bajos**, **Polonia**, **Reino Unido**, **Estados Unidos** o **Australia**, y transporta paquetes de comercio electrónico desde **China**. Puedes rastrear tu envío en inglés en el [localizador internacional de JD Logistics](https://www.jingdonglogistics.com/Tracking) o pegar el código en el buscador superior. El número te lo facilita la tienda que envió tu pedido.

## Número de seguimiento de JD Logistics: formatos y ejemplos

Estas son las estructuras que Peek reconoce como JD Logistics, junto con una muy parecida que corresponde a otro operador (con cifras inventadas):

| Número | Qué es |
| --- | --- |
| `JD4829105736491` | `JD` y 13 dígitos: albarán de JD Logistics. |
| `JDA482910573649` | `JD`, una letra y 12 dígitos: también JD Logistics. |
| `JDAP48291057364` | `JD`, dos letras y 11 dígitos: también JD Logistics. |
| `VG48291057364` | `VG` y 11 dígitos: usado por JD Logistics, aunque coincide con un formato de [La Poste](carrier:la-poste), por lo que Peek no asigna JD de inmediato (ver más abajo). |
| `JD482910573649182736` | `JD` y 18 dígitos: no es de JD Logistics. Peek consulta a [DHL Paket](carrier:dhl), que emplea este formato. |

Las minúsculas, los espacios y los guiones no afectan en Peek. El rastreador internacional de JD admite hasta 100 números en una misma consulta separados por espacios: pulsa Intro o el botón «Track».

**Dónde encontrarlo.** La tienda que contrató a JD lo incluye en el correo de confirmación de envío o en la página de tu pedido. Si no das con él, revisa [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Paquete dentro de China.** Si el envío viaja dentro de **China** (por ejemplo, camino del almacén de un transitario), se consulta en la web china [jdl.com](https://www.jdl.com/orderSearch): introduce el número de albarán o paquete en `运单查询` y pulsa `立即查询` (admite varios códigos separados por comas, espacios o saltos de línea).

## Estados de seguimiento de JD Logistics explicados

El seguimiento internacional de JD muestra cada evento en inglés junto con la localidad donde se produjo. Estos son los textos literales de JD que registra Peek, en el orden habitual de un pedido enviado desde un almacén de JD fuera de China:

| Lo que dice JD | Estado en Peek | Qué significa para ti |
| --- | --- | --- |
| `Picking Completed.` | «Admitido por el transportista» | Tu pedido se ha preparado en un almacén de JD donde el vendedor ya tenía stock depositado. |
| `Weighed at Warehouse.` | «Admitido por el transportista» | Pesado en ese mismo almacén antes de salir. |
| `Departed from Warehouse.` | «En tránsito» | Ha salido del almacén de JD Logistics. |
| `Handed over to Carrier` | «En tránsito» | JD lo ha entregado a la empresa encargada del reparto final en destino. |
| `Shipping Label Created` | sin retroceso de estado | Etiqueta del transportista local transmitida por JD; un paquete que ya figura en tránsito mantiene su estado. |
| `Arrived at GOFO Regional Hub` | «En tránsito» | En **Estados Unidos**: [GOFO Express](carrier:gofo), el repartidor que aparece allí en el historial de JD, lo escaneó en su centro regional. |

**Un paquete enviado desde China** pasa por otros hitos en este orden: primero `Shipment Picked up` (recogido al vendedor), y luego `Arrived at Warehouse`, `Weighed at Warehouse` y `Consolidation Completed` en el almacén chino de JD hasta su salida —todos ellos «Admitido por el transportista» (`Consolidation Completed` significa agrupado con otros paquetes para el vuelo, no entregado)—. Después vienen, todos como «En tránsito»: `Departed from Warehouse.`, `Arrive at airport`, `Customs released from origin port` (aduana de exportación superada), `Departure from origin port`, `Arrive at destination port`, `Cleanrance agency picked` (errata de JD: el agente de aduanas recogió el envío despachado, no tú) y `Handover to local carrier`. En **Estados Unidos** continúan los pasos de GOFO bajo códigos de JD: `Arrived at GOFO Regional Hub`, `In Transit to Next Facility`, `Loaded on Vehicle` (cargado en un centro de GOFO) y `Arrived at GOFO Delivery Station` («En tránsito»), seguidos de `Out for Delivery` («En reparto»).

**El código del transportista local.** En los envíos desde China, la línea `Departed from Warehouse.` del rastreador de JD termina a veces con `Tracking no` y el número asignado por la empresa que reparte en tu zona (en **Estados Unidos**, un código `GFUS` de 14 cifras como `GFUS48291057364918` de GOFO Express). Peek retira ese código del texto de la línea, pero consulta con él al transportista local cuando su formato lo identifica: en cuanto ese operador muestra un escaneo al menos tan reciente como el último de JD, Peek sigue la entrega allí conservando el historial de JD e indica «Entrega con GOFO Express». Si el código local no identifica a un operador conocido por Peek, búscalo en la web del repartidor local.

Peek interpreta además el resto de eventos de JD por su código interno: orden creada («Etiqueta creada»); despacho aduanero iniciado («En aduanas»); aduana finalizada, carga o descarga en centro de clasificación, descarga en destino o envío a un centro de clasificación exterior («En tránsito»); repartidor de JD en camino («En reparto»); firmado («Entregado»). Cuando el evento de reparto de JD incluye el nombre y el teléfono del mensajero entre corchetes `【…】`, Peek omite ambos datos.

**Líneas del rastreador chino (`jdl.com`).** Para envíos internos en **China**, los mensajes aparecen en chino, entre ellos `京东快件已取件` (recogido al remitente), `已到达【…】` (llegada al centro de clasificación o estación de reparto indicada entre corchetes), `正在派送中` (en reparto), `已与收件人约定新的派送时间` (nueva franja de entrega acordada con el destinatario) e `已送达` (entregado).

## Reparto de JD Logistics en Europa, América y otros mercados

**Paquetes desde China.** En su página «Cross-Border Parcels», JD presenta su servicio transfronterizo desde **China** para vendedores online hacia **Estados Unidos**, **Reino Unido**, **Australia**, **Canadá**, **México**, **Rusia** y otros países en expansión, con modalidades estándar, exprés o económica. JD envía la carga por vía aérea y cede la última milla a socios locales con un plazo medio anunciado de 3 a 6 días laborables para ese tramo local, sin detallar qué empresas son (en **Estados Unidos**, el propio historial de JD muestra a [GOFO Express](carrier:gofo)). En **Europa**, JD.com lanzó además en abril de 2025 su marca propia de paquetería exprés JoyExpress.

**Paquetes desde un almacén europeo o internacional de JD.** JD Logistics gestiona también almacenes fuera de China: en **Francia** cerca de París (`FR-JD-PAR1` en Dugny, con cobertura para Francia y toda la UE), en **Alemania** junto a Fráncfort (`DE-JD-FRA1` en Buseck) y Dortmund (`DE-JD-DTM1` en Unna), en los **Países Bajos** en Venray (`NL-JD-VEN1`) y Tilburg (`NL-JD-TIL1`), en **Polonia** cerca de Varsovia (`PL-JD-WAW1` en Urzut y `PL-JD-WAW2` en Brwinów), en el **Reino Unido** (`GB-JD-BHM1` en Lutterworth), en **Estados Unidos** (Nueva Jersey, área de Los Ángeles y Georgia) y en **Australia** (Sídney). Si el comercio envía desde ese inventario local, el seguimiento arranca con `Picking Completed.` y `Weighed at Warehouse.` en lugar de `Shipment Picked up`.

Las páginas de JD no especifican cuántos intentos de entrega realiza el operador local ni dónde queda depositado un envío ausente: se aplican las condiciones del transportista de tu país. Consulta [entrega fallida](guide:missed-delivery).

## Aduanas, aranceles y avisos de pago fraudulentos

Las normas del servicio exprés internacional de JD Logistics desde **China continental** ([jdl.com/serviceDetails](https://www.jdl.com/serviceDetails), pestaña `国际`) señalan que durante el despacho aduanero transfronterizo pueden generarse impuestos y aranceles, que corren a cargo del remitente o del destinatario y no están incluidos en la tarifa de transporte de JD. Confirma con la tienda desde dónde salió tu pedido y si el precio ya incluía el IVA y los tributos de importación. Más información en [paquetes retenidos en aduanas](guide:customs) y [cómo detectar SMS falsos de paquetería](guide:delivery-scam-texts).

- **Unión Europea.** Desde el 1 de julio de 2026, los bienes comprados a distancia fuera del territorio aduanero de la UE en envíos con valor intrínseco de hasta 150 € están sujetos en la **Unión Europea** a un derecho de aduana de 3 € por categoría de artículos, y desde el 1 de noviembre de 2026 se añade una tasa de tramitación de la Unión de 2 € por categoría de artículos con independencia del valor del envío (una categoría reúne las mercancías con la misma clasificación arancelaria, descripción y, cuando proceda, origen, sin importar el número de unidades).
- **España.** En **España**, [Correos](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/arancel-para-envios-de-bajo-valor-en-la-ue) detalla que tanto el arancel de 3 € como la tasa de 2 € se aplican a envíos dirigidos a la **Península, Baleares y Canarias** (Canarias pertenece al territorio aduanero de la UE aunque quede fuera del territorio del IVA de la UE), mientras que **Ceuta y Melilla** quedan fuera de ambos territorios y excluidas de dichas medidas comunitarias. Asimismo, en **España** el Instituto Nacional de Ciberseguridad ([INCIBE](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de)) advierte sobre campañas de *smishing* que suplantan a Correos y a otras empresas de paquetería pidiendo supuestos gastos de aduana mediante un enlace, y recomienda no abrir el enlace y, en caso de duda, consultar directamente con la entidad implicada a través de sus canales oficiales.

## Paquete de JD Logistics sin actualizar, retrasado o devuelto

**Aún no aparece nada.** Si el buscador de JD todavía no devuelve datos para tu número, confírmalo con el vendedor. Peek muestra «Esperando a JD Logistics» y sigue comprobando; si pasados dos días sigue sin reconocerse, aparece «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.».

**Sin novedades durante varios días.** Cuando un envío en camino lleva cuatro días sin escaneos nuevos, Peek muestra el día del último escaneo («Sin novedades desde el lun 12 oct»), recuerda que es habitual quedarse sin lecturas en vuelos largos, en aduanas o en fin de semana (pasadas tres semanas sin escaneos, aconseja preguntar al vendedor), y ofrece un enlace «Pide ayuda a JD Logistics» hacia su página de contacto (o hacia el transportista local cuando un operador como GOFO ya se ha hecho cargo). Más detalles en [por qué no se actualiza el seguimiento](guide:tracking-not-updating) y [seguimiento de paquetes desde China](guide:tracking-from-china).

**Retrasado o perdido.** Quien contrató a JD fue el comercio, así que reclama primero al vendedor. Para su exprés internacional desde **China continental**, el reglamento de JD advierte que el plazo estimado de entrega del albarán es únicamente orientativo: JD no devuelve los portes ni abona indemnización por demora. Consulta [paquetes perdidos](guide:lost-parcel).

**Devuelto.** Las reglas del exprés internacional de JD desde **China continental** no contemplan servicio de logística inversa una vez que la mercancía ha salido de China continental. Para tramitar una devolución, pregunta a la tienda. Consulta [devuelto al remitente](guide:returned-to-sender).

**Figura como entregado pero no está.** Habla con el vendedor, pregunta a vecinos o conserjería y revisa [entregado pero no recibido](guide:delivered-not-received).

## Atención al cliente y contacto de JD Logistics

La [página internacional Contact Us de JD Logistics](https://www.jingdonglogistics.com/contact) publica sus buzones de soporte por país (cerrado fines de semana y festivos salvo indicación contraria, sin zona horaria especificada):

- **Alemania, Países Bajos y Reino Unido:** `org.cs.eu1@jd.com`, de lunes a viernes de 09:00 a 18:00.
- **Francia:** `org.cs.eu1@jd.com`, de lunes a viernes de 09:00 a 17:00.
- **Polonia:** `org.cs.intl1@jd.com`, de lunes a viernes de 09:00 a 17:00.
- **Estados Unidos:** `org.cs.us1@jd.com`, de lunes a viernes de 08:00 a 17:00.
- **Australia:** `+61 (02)87239755` y `org.cs.intl1@jd.com`, de lunes a viernes de 09:00 a 18:00 (cerrado domingos y festivos).

JD no publica un buzón específico para España, México ni Canadá en esa lista. En **China**, el teléfono central de JD Logistics es el `950616` (`0086 950616` desde fuera de China en su página International Express), y la [página Contact Us de jdl.com](https://www.jdl.com/en/contactUs) incluye un botón «Online Consultation». Sobre tu compra o un reembolso, acude siempre primero a la tienda.

## Seguir un paquete de JD Logistics con Peek

[Peek](/) detecta JD Logistics al instante a partir de los formatos `JD…` de la tabla superior («Transportista detectado»). Como un código `VG` de 11 dígitos también encaja con La Poste, Peek lo comprueba primero con La Poste y otros servicios de seguimiento («Comprobando servicios de seguimiento…»); si no lo localizan, toca «Cambiar» y elige JD Logistics. Un enlace de `jingdonglogistics.com` con `waybillCodes=` registra el envío bajo JD Logistics sea cual sea el código; de un enlace de `jdl.com`, Peek extrae el número cuando sigue uno de los formatos `JD…`. Si tu código de JD tiene otro aspecto, pégalo igualmente: Peek sigue a más de 4500 transportistas, y puedes tocar «Cambiar» y elegir JD Logistics en «Todos los transportistas» (también responde al buscar `Jingdong` o `京东物流`), o usar «Cambiar transportista» en un envío ya guardado.

Peek lee el seguimiento internacional de JD: el estado actual, cada paso con las palabras de JD junto con su ciudad y país en inglés (incluso cuando JD anota el país en chino) y la hora de entrega. Las horas se leen en el reloj del lugar del escaneo y se convierten a la zona horaria de tu dispositivo («Horas en tu zona horaria: …» en peektracker.com y en las apps de iPhone y Android). Peek no muestra fecha estimada de entrega en envíos de JD y descarta los datos del destinatario, las fotos de entrega que pueda guardar JD, el nombre y teléfono del repartidor y el código del socio local pegado al final de la línea de salida.

En las series de números propias del **mercado interno chino** (`JD` y 13 cifras empezando por `0`; `JDJ`, `JDK`, `JDV` o `JDX` y 12 cifras; `JDVA`, `JDVB`, `JDVC` o `JDAZ` y 11 cifras), Peek abre directamente el rastreador chino en `jdl.com`. Con `JDW` y 12 cifras o `JDAP` y 11 consulta únicamente el internacional; con los demás códigos `JD`, prueba primero el internacional y pasa al chino si aquel no devuelve nada. Las líneas chinas se muestran en chino sin nombre ni teléfono del mensajero ni de quien firmó. Como `jdl.com` suele pedir una verificación visual que Peek no puede completar, tras un rechazo Peek deja en pausa la apertura de `jdl.com` para ese código durante 6 horas o más, hasta un día: si un envío dentro de China no muestra avances en Peek, consúltalo en `jdl.com`.

Mientras tienes el paquete abierto, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos —y desde las 6:00 en hora local el día en que JD escanea el paquete en reparto, hasta cada 2 minutos mientras responda el rastreador internacional de JD (cada 5 minutos cuando abre el rastreador chino en `jdl.com`)—; mediante servicios de seguimiento únicamente, comprueba cada hora, cada 3 horas tras un día sin movimientos y hasta cada 15 minutos el día de entrega mientras el paquete esté abierto o con alertas activas. El resto del tiempo, así como tras dos días sin novedades (12 horas en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Cuando un transportista local asume el reparto, Peek pasa a seguir el ritmo de ese operador (en **Estados Unidos**, GOFO Express: hasta cada 30 minutos, o cada hora tras un fallo, también el día de entrega). Puedes activar avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con JD Logistics ni con la tienda por ti.

## Preguntas frecuentes sobre el seguimiento de JD Logistics

### ¿Es fiable JD Logistics?

Sí. JD.com empezó a levantar su red logística propia en 2007, constituyó JD Logistics como grupo de negocio en abril de 2017 y la sacó a bolsa en Hong Kong en mayo de 2021 (código `2618`). Según su página corporativa, opera más de 1600 almacenes propios y 2000 almacenes en la nube de terceros en 26 mercados. A escala mundial, JD Logistics gestiona más de 200 almacenes aduaneros, de distribución directa internacional y en el extranjero.

### ¿Cómo rastreo un paquete de JD Logistics?

En `jingdonglogistics.com/Tracking` (hasta 100 números separados por espacios), en `jdl.com/orderSearch` si el paquete circula dentro de China o pegando el código en el cuadro superior de esta página.

### ¿Quién reparte los paquetes de JD Logistics en España y Europa?

JD no publica el listado de empresas de última milla para sus envíos desde China, pero en Europa cuenta desde abril de 2025 con su marca exprés JoyExpress y con almacenes cerca de París (Dugny, con envíos a toda la UE), Fráncfort, Dortmund, Venray, Tilburg, Varsovia y en Inglaterra. En Estados Unidos, su propio historial muestra a GOFO Express. Si la línea `Departed from Warehouse.` incluye tras `Tracking no` el código de un operador local reconocido por Peek, Peek pasa a seguir ese código en cuanto registra escaneos.

### ¿Por qué no se actualiza mi seguimiento de JD Logistics?

Puede que JD aún no haya recibido el paquete del vendedor, que esté volando o en despacho aduanero, o que el operador local todavía no haya comunicado lecturas. Tras cuatro días sin movimientos, consulta a la tienda o escribe al soporte europeo de JD (`org.cs.eu1@jd.com`). Si es un envío interno en China, compruébalo en `jdl.com`.

### ¿Cómo contacto con JD Logistics?

A través de los correos publicados en `jingdonglogistics.com/contact` (`org.cs.eu1@jd.com` para Europa occidental e `org.cs.us1@jd.com` para EE. UU., sin buzón propio listado para España o México). En China, su teléfono de atención es el `950616` (`0086 950616` desde el extranjero). Para incidencias del pedido o reembolsos, habla primero con el comercio.

:::sources
- [JD Logistics: Track your shipment (en inglés)](https://www.jingdonglogistics.com/Tracking) – rastreador internacional en inglés, hasta 100 códigos separados por espacios, Intro o botón Track
- [JD Logistics: About JINGDONG Logistics (en inglés)](https://www.jingdonglogistics.com/about) – división logística de JD.com (HKEX: 2618), marcas JoyLogistics y JoyExpress, más de 1600 almacenes propios, 2000 en la nube y más de 200 internacionales en 26 mercados
- [JD Logistics: Contact Us (en inglés)](https://www.jingdonglogistics.com/contact) – correos de atención al cliente, teléfono en Australia, horarios y festivos por país y línea 950616
- [JD Logistics: Cross-Border Parcels (en inglés)](https://www.jingdonglogistics.com/ser/CrossExport) – países servidos, modalidades estándar/exprés/económica, transporte aéreo y última milla en 3 a 6 días laborables de media
- [JD Logistics: JINGDONG Logistics (France) (en inglés)](https://www.jingdonglogistics.com/ser/GlobalWarehouseFR) – almacén FR-JD-PAR1 en Dugny y distribución para Francia y la UE
- [JD Logistics: JINGDONG Logistics (Germany) (en inglés)](https://www.jingdonglogistics.com/ser/GlobalWarehouseDE) – almacenes DE-JD-FRA1 en Buseck y DE-JD-DTM1 en Unna
- [JD Logistics: JINGDONG Logistics (The Netherlands) (en inglés)](https://www.jingdonglogistics.com/ser/GlobalWarehouseNL) – almacenes NL-JD-VEN1 en Venray y NL-JD-TIL1 en Tilburg
- [JD Logistics: JINGDONG Logistics (Poland) (en inglés)](https://www.jingdonglogistics.com/ser/GlobalWarehousePL) – almacenes PL-JD-WAW1 en Urzut y PL-JD-WAW2 en Brwinów cerca de Varsovia
- [JD Logistics: JINGDONG Logistics (United Kingdom) (en inglés)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUK) – almacén GB-JD-BHM1 en Lutterworth (Inglaterra)
- [JD Logistics: JINGDONG Logistics (USA) (en inglés)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUS) – almacenes en Nueva Jersey, área de Los Ángeles y Georgia
- [JD Logistics: JINGDONG Logistics (Australia) (en inglés)](https://www.jingdonglogistics.com/ser/GlobalWarehouseAS) – almacenes en Sídney
- [JDL: 运单查询 (búsqueda de albaranes, en chino)](https://www.jdl.com/orderSearch) – rastreador chino por albarán o paquete, admite varios códigos
- [JDL: 服务细则 (reglas de servicio, en chino)](https://www.jdl.com/serviceDetails) – pestaña internacional: impuestos y aranceles no incluidos en el flete, plazo estimado solo orientativo sin reembolso ni indemnización y ausencia de devoluciones fuera de China continental
- [JDL: International Express (en inglés)](https://www.jdl.com/en/crossBorder/internationalC2C) – servicio exprés desde China continental al extranjero y teléfono 0086 950616
- [JDL: Contact Us (en inglés)](https://www.jdl.com/en/contactUs) – teléfono 950616 y botón Online Consultation
- [JDL: Company Profile (en inglés)](https://www.jdl.com/en/profile) – creación en abril de 2017, salida a bolsa en Hong Kong en mayo de 2021 y lanzamiento de JoyExpress en Europa en abril de 2025
- [Comisión Europea: EU Customs Reform (en inglés)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € por categoría de artículos hasta 150 € desde el 1 de julio de 2026 y tasa de tramitación de la Unión de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, en inglés)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tasa de 2 € con independencia del valor y definición de categoría de artículos
- [Correos: arancel para envíos de bajo valor en la UE](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/arancel-para-envios-de-bajo-valor-en-la-ue) – aplicación en Península, Baleares y Canarias y exclusión de Ceuta y Melilla
- [INCIBE: SMS fraudulentos que suplantan a Correos y empresas de paquetería](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de) – alerta contra enlaces de pago aduanero falsos en España
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos `JD` y `VG`, textos y códigos de JD, traspaso a GOFO Express, consulta en `jdl.com` y visualización en Peek
:::
