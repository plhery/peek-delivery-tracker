---
title: Seguimiento YTO Express: números YT, estados y ayuda
description: Rastrea un paquete de YTO Express (圆通): cómo es el código YT, qué significan sus estados en chino, plazos a España y América y teléfonos de contacto de YTO.
slug: seguimiento-yto-express
published: 2026-11-18
updated: 2026-11-18
---

YTO Express (圆通速递, Yuantong) es una empresa china de mensajería urgente fundada en Shanghái en el año 2000, con más de 5.000 sucursales repartidas por toda China. Un código de YTO suele corresponder a un paquete que viaja dentro de China: por ejemplo, un pedido de Taobao, Pinduoduo o Douyin —plataformas a las que da servicio YTO— camino del almacén de un transitario o del domicilio de un conocido. Puedes consultarlo en el [rastreador de YTO](https://www.yto.net.cn/ytoExpress/waybill/search), disponible únicamente en chino, o pegándolo en el buscador de arriba. Los envíos de YTO fuera de China corren a cargo de YTO International, que dispone de su propio buscador en [ytoglobal.com](https://www.ytoglobal.com/service).

## Número de seguimiento de YTO Express: cómo es

YTO llama a este código número de albarán o guía (`运单号`). Con cifras inventadas:

| Código | Qué es |
| --- | --- |
| `YT2468135790246` | `YT` y 13 cifras: un albarán de YTO Express. |
| `D24681357902` | `D` y 11 cifras: un formato de YTO, aunque los códigos de Colis Privé pueden tener la misma forma. |
| `G24681357902` | `G` y 11 cifras: otro formato de YTO que también pueden compartir los envíos de Colis Privé. |
| `YT2612468135790246` | `YT` y 16 cifras: no pertenece a YTO, sino a [YunExpress](carrier:yunexpress). |

Las minúsculas y los espacios no importan al pegar el número en [Peek](/). El rastreador propio de YTO admite hasta 10 códigos de una sola vez, separados por comas, barras o espacios.

**Dónde encontrarlo.** El vendedor lo facilita junto con los datos de envío del pedido y además figura impreso en la etiqueta adherida al paquete. Si no das con él, revisa [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Consulta en la web de YTO.** El buscador permanece en chino incluso entrando desde la versión en inglés de la web de YTO: escribe el número en el recuadro y pulsa `查询` (buscar). Según la política de privacidad de YTO, un albarán sigue disponible para consulta entre tres y seis meses según el canal por el que se contrató; transcurrido ese plazo, YTO lo borra del sistema de búsqueda.

## Estados de seguimiento de YTO Express explicados

Cada lectura de los datos de seguimiento de YTO incluye un nombre breve en chino, una marca horaria en el reloj de China (`UTC+8`) y la sucursal o estación que la registró. Así se muestran en Peek:

| Escaneo de YTO | En Peek | Qué ha ocurrido |
| --- | --- | --- |
| `揽收扫描` | `Picked up` | Recogido al remitente. |
| `业务员收件` | `Picked up` | Recogido en mano al remitente por un repartidor de YTO. |
| `装件入车扫描` | `Loaded for transport` | Cargado en un vehículo rumbo a la siguiente escala. |
| `下车扫描` | `Unloaded at a sorting centre` | Descargado en un centro de clasificación o sucursal. |
| `快件城市信息` | «En tránsito» | Paso registrado por una ciudad durante el trayecto. |
| `冬运会安检` | `Security check` | Control de seguridad del paquete en la red de YTO con motivo de un evento de deportes de invierno; después continúa en ruta. |
| `派件扫描` | «En reparto» | Entregado al repartidor para salir a reparto. |
| `入柜\|入库` | `In a parcel locker or pickup station` | Depositado en una taquilla inteligente o registrado en un punto de recogida. |
| `小件员取出` | `Taken out of the locker by the courier` | El repartidor ha vuelto a sacar el paquete de la taquilla o estación. |
| `PDA正常签收扫描` | «Entregado» | Entregado con firma (`签收`), anotado en el terminal portátil del repartidor. |
| `退回件扫描` | `Return to the sender started` | Se ha iniciado la devolución al remitente. |

Las tres filas entre comillas aparecen en español dentro de Peek; las demás filas de la tabla se mantienen en inglés, y cualquier otro escaneo fuera de la tabla se muestra en el chino original de YTO. El estado general del envío sí aparece siempre en español, como «Admitido por el transportista» o «Listo para recoger». Tras `入柜|入库`, el estado pasa a «Listo para recoger»; si el repartidor retira el envío de allí (`小件员取出`), vuelve a «En tránsito».

Cuando la dirección corresponde al almacén de un reexpedidor en China, `PDA正常签收扫描` significa que el almacén firmó la recepción de tu paquete. A partir de ese punto pasa a valer el número de seguimiento propio del reexpedidor.

## Entrega de YTO en China: repartidores, taquillas y estaciones

En **China**, un repartidor de YTO lleva el paquete en mano (`派件扫描`), o bien el envío entra en una taquilla o estación (`入柜|入库`) para que pases a recogerlo. YTO publica el `95161` como su «Courier Outbound Hotline», el número desde el que llaman sus repartidores.

Las normas vigentes en **China** para las empresas de mensajería urgente desde el 1 de marzo de 2024 establecen que:

- YTO debe informarte de que puedes revisar el paquete en persona frente al albarán; si el embalaje presenta daños visibles o el contenido es frágil, puedes examinar el interior o rechazar la entrega.
- Ninguna empresa puede confirmar la recepción en tu nombre ni dejar el paquete en una taquilla inteligente o en una estación de servicio exprés sin tu consentimiento.

Para pedir que devuelvan un paquete o cambiar la dirección antes del reparto (`退回/更址`), YTO menciona su aplicación móvil y su miniprograma entre las vías disponibles. Su política de privacidad indica acudir a atención al cliente para modificar la dirección de entrega o el teléfono.

## YTO International: paquetes enviados al extranjero

YTO International (圆通国际) se remonta a 2006 y declara más de 50 oficinas en 18 países y regiones. Su rastreador en [ytoglobal.com/service](https://www.ytoglobal.com/service) está disponible en inglés («Express Freight Tracking») y en chino (`国际快递查询`), y admite hasta 10 números separados por comas o puntos y comas.

Las páginas de YTO no especifican qué empresa reparte en tu país y solo aluden a alianzas con proveedores logísticos globales de primer nivel («top-notch global logistics service providers»). En International Standard Express, su servicio pensado para particulares, YTO publica a título meramente orientativo («for reference only») estos plazos en días laborables desde su almacén internacional hasta la entrega: 7 a 12 días a España, Portugal, Francia, Alemania, Italia, Polonia, Reino Unido y Canadá (12 a 17 a Bulgaria y Rumanía), 8 a 10 a México, 9 a 12 a Estados Unidos y 10 a 13 a Argentina, Chile, Uruguay, Perú, Colombia y Brasil.

Si no estás en casa, las preguntas frecuentes de YTO International indican seguir el aviso de nueva entrega («Redelivery Notification») para concertar sin coste un segundo intento; si vuelve a fallar, YTO pide instrucciones al remitente. Para pactar de antemano una franja de entrega, su FAQ remite al teléfono `400 60 95554`. En los envíos por avión, la aduana y la seguridad aeroportuaria inspeccionan los paquetes y la aduana puede incautar o incluso destruir envíos con artículos prohibidos.

**Aduanas, territorios y avisos antifraude (UE y España).** Los impuestos de importación dependen del país de destino: consulta [paquetes retenidos en aduanas](guide:customs) y [SMS falsos de entregas](guide:delivery-scam-texts). En la **Unión Europea**, desde el 1 de julio de 2026 las compras a distancia procedentes de fuera del territorio aduanero de la UE en envíos de hasta 150 € están sujetas a un arancel de 3 € por categoría de producto, y desde el 1 de noviembre de 2026 se suma una tasa de tramitación de la Unión de 2 € por categoría de producto cualquiera que sea el valor del envío (una categoría agrupa las mercancías de un envío con la misma clasificación arancelaria, la misma descripción y, cuando deba declararse, el mismo origen, sin importar el número de unidades). En **España**, [Correos](carrier:correos-spain) precisa que tanto el arancel de 3 € como la tasa de 2 € se aplican a envíos destinados a la **Península, Baleares y Canarias** (Canarias forma parte del territorio aduanero de la UE aunque quede fuera del territorio del IVA de la UE), mientras que **Ceuta y Melilla** quedan fuera de ambos territorios y excluidas de esas dos medidas comunitarias. Además, en **España** el INCIBE alerta sobre campañas de *smishing* que suplantan a Correos y otras empresas de paquetería reclamando supuestas tasas de aduanas mediante un enlace, y recomienda no pulsar el enlace y verificar cualquier aviso por los canales oficiales del transportista.

## Paquete de YTO que no llega, perdido o devuelto

**Sin líneas todavía.** Un albarán recién creado no muestra nada hasta que YTO escanea físicamente el paquete. Si continúa vacío, comprueba el código con el vendedor. Un albarán antiguo también puede aparecer en blanco porque YTO ya lo haya eliminado (entre tres y seis meses, ver arriba).

**Sin actualizaciones.** El contrato de servicio de YTO con los remitentes en **China** da un paquete por extraviado si, tras su primera línea, pasa 4 días sin novedades o sigue sin firmarse 7 días después de la fecha prevista. Reclama al vendedor, que contrató el envío y puede gestionar la incidencia ante YTO. Más información en [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating) y [seguimiento de paquetes desde China](guide:tracking-from-china).

**Perdido o dañado.** En un pedido online, quien abonó el porte fue el vendedor: acude primero a él. Bajo ese mismo contrato en **China**, un envío sin valor declarado (`保价`) que se pierda recibe hasta 300 yuanes de indemnización, o 100 yuanes si son documentos; los paquetes asegurados cubren la pérdida real hasta el valor declarado. Las reclamaciones se presentan por teléfono ante la oficina de YTO de origen o en atención al cliente de YTO, y la web de YTO dispone tras iniciar sesión de apartados de quejas (`投诉反馈`) y siniestros asegurados (`保价理赔`). Si la respuesta de YTO no te convence o no llega a tiempo, la normativa china permite recurrir ante el regulador postal, el State Post Bureau (sección `消费者申诉` en su web). Consulta también [paquetes perdidos](guide:lost-parcel).

**Devuelto.** Después de `退回件扫描`, el paquete regresa a origen y los escaneos de reparto posteriores corresponden al trayecto de vuelta al remitente. Habla con el vendedor; revisa [devuelto al remitente](guide:returned-to-sender).

**Entregado pero no recibido.** En **China**, el contrato de YTO permite que firme la entrega alguien que conviva contigo o una persona autorizada por ti, o que el repartidor deposite el envío en una taquilla inteligente o estación exprés cercana a la dirección. Comprueba esos puntos y la conserjería de tu edificio. Consulta [entregado pero no recibido](guide:delivered-not-received).

## Teléfono y atención al cliente de YTO Express

Estos son los números que publica YTO en sus páginas:

- **`95554`**: «Service Hotline» (`服务热线`), atención de YTO Express dentro de China.
- **`400 60 95554`**: «Service Hotline» de YTO International, para envíos internacionales.
- **`95161`**: «Courier Outbound Hotline», la línea desde la que llaman los repartidores de YTO.
- **`021-69777888`**: listado junto a los anteriores, sin etiqueta descriptiva.

YTO no detalla horarios ni idiomas de atención para ninguno de ellos. Su web cuenta también con un botón de atención en línea (`在线客服`), y YTO International ofrece un formulario de contacto. Para gestiones sobre tu compra, dirígete al vendedor.

## Seguir un paquete de YTO Express con Peek

[Peek](/) identifica a YTO Express al instante cuando el código empieza por `YT` seguido de 13 cifras. En cambio, un código `D` o `G` con 11 cifras también encaja con Colis Privé, por lo que Peek no puede distinguirlos solo por su forma. Pégalo igualmente: Peek sigue a más de 3.500 transportistas y consulta los servicios de seguimiento («Comprobando servicios de seguimiento…»). Si no sale YTO Express, toca «Cambiar» y elígelo bajo «Encaja con este número», o usa «Cambiar transportista» dentro del paquete. La consulta de YTO en Peek admite asimismo albaranes de YTO con otros formatos: para ellos, selecciona YTO Express en «Todos los transportistas». Un enlace de `yto.net.cn` que lleve `mailNo=` o `waybillNo=` con el código también apunta directamente a YTO.

Peek lee el seguimiento doméstico de YTO en China, no el de YTO International. Muestra el estado actual, los escaneos devueltos por YTO con la localidad de la sucursal o estación de cada uno (o su nombre en chino cuando Peek no puede situarlo) y la hora de entrega. Las marcas se leen en el huso de China (`UTC+8`: las `20:00` en Pekín equivalen en la España peninsular y Baleares a las `13:00` en invierno y a las `14:00` en verano, una hora menos en Canarias) y se muestran en la zona horaria de tu dispositivo; en peektracker.com y en la app para iPhone el historial cierra con «Horas en tu zona horaria:» seguido de su nombre. Los escaneos repetidos se muestran una sola vez. Justo tras `退回件扫描`, el estado pasa a «Incidencia» y Peek enseña el viaje de regreso en inglés como `Out for delivery back to the sender` e `In a parcel locker or station on its way back`, y la firma del remitente como `Returned to the sender` bajo el estado «Devuelto al remitente».

YTO no facilita fecha estimada de entrega. Peek descarta los nombres y teléfonos de los repartidores, los datos del destinatario y las direcciones de recogida, que quedan únicamente en el rastreador de YTO. Como YTO responde ante cualquier código con formato válido, que aún no haya lecturas no demuestra un fallo: Peek muestra «Esperando a YTO Express» en peektracker.com y sigue comprobando. Si un envío guardado bajo YTO continúa sin rastro en YTO dos días después y ninguna otra fuente ha respondido por él, Peek muestra en peektracker.com «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a consultarlo una vez al día y, cumplida una semana desde que lo añadiste, una vez por semana.

Peek comprueba novedades hasta cada 10 minutos mientras tienes el paquete abierto o en el día de reparto con las notificaciones activadas, y hasta cada 2 minutos desde las 6:00 (hora de China) el día en que YTO escanea el envío en reparto (`派件扫描`) mientras responda el rastreador propio de YTO; a través de servicios de seguimiento, comprueba como máximo cada hora, cada 3 horas tras un día sin escaneos y hasta cada 15 minutos en el día de reparto mientras tengas el paquete abierto o las notificaciones activas. El resto del tiempo, así como tras dos días sin nuevos escaneos (12 horas mientras muestre «Admitido por el transportista»), comprueba cada hora. Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con YTO ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de YTO Express

### ¿Es fiable YTO Express?

Sí. YTO Express se fundó en Shanghái el 28 de mayo de 2000. El grupo cuenta con dos sociedades cotizadas —YTO Express (código 600233) y YTO International (06123.HK)— y declara 550.000 empleados al cierre de 2024.

### ¿Qué significa 签收 en el seguimiento de YTO?

Entregado con firma: el repartidor entregó el paquete y lo dejó registrado en su terminal (`PDA正常签收扫描`), lo que en Peek figura como «Entregado». Puede haberlo recibido tú, alguien autorizado en tu domicilio o el almacén de un transitario. Si nadie lo tiene, consulta [entregado pero no recibido](guide:delivered-not-received).

### ¿Por qué mi código de YTO no muestra seguimiento?

Porque YTO aún no ha escaneado el paquete, el número contiene algún error o han pasado más de los tres a seis meses durante los que YTO conserva la consulta según su política de privacidad. Revisa el código con el vendedor o pégalo en Peek, que seguirá consultándolo.

### ¿YTO Express entrega fuera de China?

Sí, mediante YTO International y su propio rastreador en ytoglobal.com. Sus páginas no detallan qué mensajería realiza el reparto final en tu país y ofrecen sus plazos solo como referencia: 7 a 12 días laborables a España y la mayoría de países de la UE, 8 a 10 a México y 10 a 13 a Argentina, Chile, Colombia o Perú.

### ¿Cuál es el teléfono de YTO Express?

El `95554` dentro de China y el `400 60 95554` para YTO International, sin horarios publicados. Las llamadas desde el `95161` proceden de los repartidores de YTO.

:::sources
- [YTO Express: About YTO (en inglés)](https://www.yto.net.cn/en/aboutYto/about) – fundación el 28 de mayo de 2000 en Shanghái, empresas cotizadas, sucursales y plantilla
- [YTO Express: 我要查件 (consulta de albaranes, en chino)](https://www.yto.net.cn/ytoExpress/waybill/search) – buscador solo en chino, hasta 10 códigos, páginas de quejas e indemnizaciones
- [YTO Express: Domestic Express Services (en inglés)](https://www.yto.net.cn/en/domesticService) – plataformas atendidas, devolución o cambio de dirección antes de la entrega, líneas 95554 y 95161
- [YTO Express: Página de inicio (en chino)](https://www.yto.net.cn/) – atención al cliente en línea `在线客服`
- [YTO Express: 国内快递 (servicios nacionales, en chino)](https://www.yto.net.cn/domesticService) – `退回/更址`, `服务热线`, `快递员外呼热线`
- [YTO Express: 国内快递服务协议 (contrato de servicio exprés nacional, en chino)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=DESA) – plazos de pérdida, indemnización con y sin valor declarado, reclamaciones y quién puede firmar
- [YTO Express: 圆通速递隐私政策 (política de privacidad, en chino)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=PCPrivacyAgreement) – consulta disponible de tres a seis meses y cambio de dirección mediante atención al cliente
- [YTO Express: 寄快递 (enviar un paquete, en chino)](https://www.yto.net.cn/ytoExpress/subCreatePages/create) – compensación de hasta 300 yuanes sin valor declarado
- [YTO Express: International Business Service (en inglés)](https://www.yto.net.cn/en/global/globalProduct) – teléfono de YTO International 4006095554 y 021-69777888
- [YTO International: Company Introduction (en inglés)](https://www.ytoglobal.com/about-us) – trayectoria desde 2006, oficinas y socios
- [YTO International: Express Freight Tracking](https://www.ytoglobal.com/service) – buscador en inglés y chino hasta 10 números
- [YTO International: International Standard Express (en inglés)](https://www.ytoglobal.com/product/express-intl) – servicio para particulares y plazos orientativos a España, México, Sudamérica y otros destinos
- [YTO International: FAQ (en inglés)](https://www.ytoglobal.com/rich/question) – teléfono 400 60 95554, segundo intento gratuito, cita previa e inspección aduanera
- [YTO International: Contact Us (en inglés)](https://www.ytoglobal.com/about-us/contact) – formulario de contacto
- [Ministerio de Transporte de China: 快递市场管理办法 (medidas de administración del mercado exprés, en chino)](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – en vigor desde el 1 de marzo de 2024, revisión en la entrega, consentimiento para taquillas o estaciones y reclamación ante autoridades postales
- [State Post Bureau de China: 国家邮政局主要职责 (funciones principales, en chino)](https://www.spb.gov.cn/gjyzj/c100001/c100006/201306/2722edab189242549b320d06e0dbd382.shtml) – organismo regulador postal y del mercado exprés
- [State Post Bureau de China: portada (en chino)](https://www.spb.gov.cn/) – apartado de reclamaciones de consumidores (`消费者申诉`)
- [Comisión Europea: EU Customs Reform (en inglés)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € por categoría de producto hasta 150 € desde el 1 de julio de 2026 y tasa de tramitación de la Unión de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, en inglés)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – aplicación de los 2 € cualquiera que sea el valor y definición de categoría de producto
- [Comisión Europea: Territorial Scope (en inglés)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias en el territorio aduanero de la UE y fuera del ámbito del IVA; Ceuta y Melilla fuera de ambos
- [Correos: Arancel y tasas para envíos de importación a la UE](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/arancel-para-envios-de-bajo-valor-en-la-ue) – aplicación en España (Península, Baleares y Canarias) y exclusión de Ceuta y Melilla
- [INCIBE: En circulación SMS fraudulentos que suplantan a Correos y otras empresas de paquetería](https://www.incibe.es/ciudadania/avisos/en-circulacion-sms-fraudulentos-que-suplantan-correos-y-otras-empresas-de) – aviso en España sobre SMS fraudulentos (*smishing*) con falsos cobros aduaneros
- [Comisión Europea: Summertime (en inglés)](https://transport.ec.europa.eu/transport-themes/summertime_en) – horario de verano europeo del último domingo de marzo al último domingo de octubre
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos reconocidos, enlaces de `yto.net.cn`, traducción de escaneos de YTO, reloj de China, viaje de devolución y datos mostrados u omitidos en Peek
:::
