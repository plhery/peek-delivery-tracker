---
title: Seguimiento Poczta Polska: números, estados y recogidas
description: Sigue un paquete de Poczta Polska o Pocztex: formatos de número, significado de los estados, avisos, puntos, taquillas, aduanas y contacto.
slug: seguimiento-poczta-polska
published: 2026-11-22
updated: 2026-11-22
---

Poczta Polska es el operador postal estatal de Polonia, y Pocztex es su servicio de mensajería. Puedes seguir un paquete o carta certificada en la página eMonitoring de Poczta Polska, que cuenta con versión en inglés, o pegar el número en la casilla de arriba. El número aparece en el justificante de admisión del remitente —por lo que la tienda lo tiene— y se repite en los SMS y avisos de Poczta Polska.

## Número de seguimiento de Poczta Polska: dónde encontrarlo

Escríbelo sin espacios ni paréntesis. El seguimiento cubre todos los envíos admitidos con justificante de envío, excepto las cartas con valor declarado; las cartas certificadas muestran únicamente las fechas de admisión y entrega.

| Formato (inventado) | Qué es |
| --- | --- |
| `PX4829105730` | `PX` y 10 cifras. |
| `0045900773482910573` | 19 cifras. |
| `00459007734829105736` | 20 cifras con `5900773` tras las tres primeras (o 18 cifras sin los dos ceros `00` iniciales). |
| `RR482910576PL` | Formato postal internacional: dos letras, nueve cifras y `PL`. |

Dónde suele aparecer:

- **El justificante de admisión del remitente**: pregunta a la tienda si tu correo de compra no trae ningún número.
- **El mensaje de recogida** para un punto o taquilla Pocztex, con el número, el lugar, la fecha límite y un código de un solo uso.
- **El SMS, correo o aviso en la app tras una entrega fallida**, con el número, la fecha límite y la dirección de la oficina postal.

Si eMonitoring responde «The given package number is incorrect», revisa cada carácter y confirma qué transportista nombró la tienda. ¿Sin ningún número? Mira [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Qué significan los estados de seguimiento de Poczta Polska

eMonitoring se abre en polaco; la versión inglesa muestra estas frases, con fecha, hora y oficina postal en cada paso:

| Estado de Poczta Polska | Qué significa para ti |
| --- | --- |
| `Electronic item data received` | El remitente ha registrado el envío. Poczta Polska todavía no lo tiene. |
| `Posting/collection`, `Item arrived at the shipping point` | Admitido en una oficina o punto, o recogido al remitente. |
| `In transport` | En camino entre centros de clasificación. |
| `Departure from Poland` | Ha salido de Polonia hacia otro país. |
| `Arrival at inward office of exchange (Poland)` | Un paquete del extranjero ha llegado a la oficina de cambio de Polonia. |
| `Customs service` | Gestión aduanera. |
| `Item returned from import customs` | Liberado por aduanas de importación. |
| `In delivery` | En reparto, o un intento fallido (mira abajo). |
| `Unsuccessful (physical) delivery` | No había nadie. Se dejó aviso y el envío va a la oficina postal o vuelve a reparto. |
| `Attempted delivery - redirected` | Intento tras el cual el envío se reenvía. |
| `Delivery at sender's request - delivery attempt` | Intento de una entrega solicitada por el remitente. |
| `Unsuccessful (physical) delivery - lost` | Perdido durante la entrega. |
| `Ready for pick-up at the Post Office`, `Added to postal notice book - Item can be collected`, `Ready for pick up at the point` | Te espera en la oficina o punto nombrado en el escaneo. |
| `Collected at the post office`, `Collected at the delivery point` | Recogido en la oficina o punto. |
| `Final delivery`, `Delivered to the PO box`, `Delivery at sender's request - delivered` | Entregado. |
| `Returned`, `Returned to sender`, `Not collected at the delivery point` | De vuelta al remitente. |

`Posting/collection` es el paso del remitente, no tu recogida. Poczta Polska llama también `In delivery` a uno de sus eventos de entrega fallida: Peek distingue ambos por el código del evento y muestra «Intento de entrega» en ese caso en lugar de «En reparto». Una línea `Customs service` puede aparecer tras la entrega para contabilizar derechos cobrados a la entrega. Sin «(Poland)», `Arrival at inward office of exchange` significa que un envío salido de Polonia ha llegado al país de destino. En la página polaca, una entrega fallida figura como `Awizowana` y un envío esperando en oficina como `Oczekuje na odbiór`. Poczta Polska advierte que los nombres de los eventos pueden cambiar.

## Entrega fallida de Poczta Polska: avisos, puntos y taquillas

**Paquetes de mensajería Pocztex.** El repartidor entrega el paquete a ti, a un adulto de tu hogar o a una persona autorizada. Si estás ausente, te avisan por SMS, correo o app cuando el remitente dio tus datos, o con un aviso en el buzón, y llega un segundo mensaje a los dos días. El paquete espera 7 días en la oficina postal desde el día siguiente al aviso y después vuelve al remitente. También puedes llamar al repartidor para pedir que lo deje junto a tu dirección (no válido para contrarreembolso, valor declarado, porte debido ni entrega «do rąk własnych»), bajo tu responsabilidad.

**Puntos de recogida y taquillas.** En un punto Pocztex (`PUNKT`) elegido por el remitente, el paquete espera 7 días en una oficina postal o 3 días en un comercio asociado como Żabka, ABC o Lewiatan. Las taquillas (`AUTOMAT`), situadas en oficinas postales, gasolineras y tiendas como Biedronka, lo guardan 72 horas, con recordatorios a las 24 y 48 horas. Se abren con tu teléfono y el código o mediante la app Pocztex Mobile. El plazo no se puede ampliar ni mover el paquete a otro punto, pero cualquiera con el código puede recogerlo.

**Cartas certificadas y paquetes postales.** Se deja aviso (*awizo*) en el buzón o se envía de forma electrónica si se pidió. A los 7 días llega un segundo aviso y el envío se devuelve si no se recoge en los 14 días siguientes al primero. Pagando una tasa puedes pedir en oficina o en poczta-polska.pl hasta 14 días adicionales de custodia.

## Paquete de Poczta Polska retrasado, perdido, dañado o en aduanas

El seguimiento de Poczta Polska es meramente informativo y no sirve de base para reclamaciones, y no publica pautas si el seguimiento se detiene: pregunta al remitente y consulta [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Quién reclama.** El remitente, o tú una vez entregado el envío o cuando el remitente te haya cedido la reclamación: online en poczta-polska.pl (eReklamacje), por escrito o en persona en cualquier oficina postal.

**Plazos** para envíos nacionales según las condiciones de Poczta Polska:

- Hasta 12 meses desde la admisión.
- Daño visible: en la entrega, mediante acta. Puedes comprobar el contenido de un paquete aparentemente dañado, salvo en puntos y taquillas.
- Daño oculto o falta de contenido: en los 7 días siguientes a la recepción.
- Pérdida: un paquete Pocztex se considera perdido a los 30 días de la admisión; la pérdida de un paquete postal se puede reclamar a los 14 días de la admisión, sin contar domingos ni festivos.
- Poczta Polska responde en 30 días y puedes recurrir en 14 días.

Envíos internacionales: en un plazo de seis meses desde el día siguiente a la admisión.

**Aduanas.** Desde el 1 de julio de 2026, los envíos de fuera de la UE de hasta 150 € llevan un arancel europeo de 3 € por categoría de producto, cobrado por Poczta Polska; los regalos entre particulares hasta 45 € están exentos. Desde el 1 de noviembre de 2026 se suma una tasa europea de tramitación de 2 € por partida arancelaria en compras online enviadas desde fuera de la UE, con independencia del valor: Poczta Polska la cobra al destinatario y la transfiere a la administración tributaria polaca (KAS). Pueden sumarse el IVA no pagado y la tarifa de declaración aduanera de Poczta Polska (20 PLN, o 25 PLN si tramitas la aduana tú mismo), pagaderos en la entrega o antes de recoger. Puedes rechazar el paquete. Más en [paquetes retenidos en aduanas](guide:customs).

## Teléfono y atención al cliente de Poczta Polska

Según las páginas oficiales en inglés y polaco de Poczta Polska y Pocztex:

- **Teléfono general:** +48 438 420 600, de lunes a viernes de 8:00 a 20:00, según tarifa de tu operador, además de un formulario online en la [página de contacto](https://www.poczta-polska.pl/en/kontakt/).
- **Pocztex:** 804 104 104 desde fijos polacos y +48 43 842 0 842 desde móviles y el extranjero, de lunes a viernes de 8:00 a 20:00 y sábados de 8:00 a 16:00.
- **Dudas sobre seguimiento:** por correo a kontakt@poczta-polska.pl.
- **Problemas en puntos de recogida** (paquete ausente, punto cerrado): Contact Center 801 333 444 desde fijos o +48 438 420 600.

> Poczta Polska advierte sobre SMS falsos de Pocztex acerca de entregas fallidas con enlaces a webs fraudulentas. Pocztex nunca envía enlaces de pago ni pide datos de tarjeta por SMS. Reenvía cualquier SMS sospechoso al 8080.

## Seguir envíos de Poczta Polska con Peek

[Peek](/) sigue más de 4500 transportistas. Reconoce al instante los números `PX`, los de 20 cifras con `5900773` tras las tres primeras y dígito de control válido, y los de 18 cifras sin los dos ceros `00` iniciales (que Peek antepone solo). Para los de 19 cifras que empiezan por `00`, pregunta solo a Poczta Polska añadiendo el dígito de control como la web oficial. Un número internacional válido acabado en `PL` que empiece por R, E, L o C (salvo CV), como `RR`, `EE` o `CP`, pasa directamente a Poczta Polska; otro válido acabado en `PL`, como uno de `VV`, aparece como «Unknown postal carrier» mientras Peek pregunta a Poczta Polska y a [Chronopost](carrier:chronopost), y Poczta Polska gana cuando lo reconoce. También funcionan los enlaces de eMonitoring con `numer=`, sin código postal.

Peek muestra los textos ingleses tal cual (o los polacos si usas Peek en polaco), cada oficina en el mapa, el peso, el tipo de servicio, el país de destino y la oficina que guarda un paquete listo para recoger. No hay fecha prevista de entrega. Descarta direcciones y horarios de oficinas, justificantes de pago y servicios adicionales. Los escaneos reenviados desde el extranjero no llevan zona horaria: Peek los lee según el reloj del país de procedencia. No se pueden seguir palés, envíos agrupados ni correo no certificado. Cuando un envío lleva cuatro días sin novedades, Peek indica «Sin novedades desde hace cuatro días» y muestra «Pide ayuda a Poczta Polska».

Mientras tienes el paquete abierto en pantalla, o el día de la entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 (hora local) el día en que Poczta Polska lo escanea en reparto— mientras responda su seguimiento propio; mediante servicios de seguimiento, hasta cada 15 minutos. El resto del tiempo, y tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora. Puedes recibir avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Un paquete enviado desde el extranjero a Polonia puede seguirse hasta Poczta Polska.

## Preguntas sobre el seguimiento de Poczta Polska

### ¿Qué es Pocztex?

Pocztex es el servicio de mensajería de Poczta Polska, con web propia en pocztex.pl y la app Pocztex Mobile. Sus paquetes usan el mismo seguimiento eMonitoring.

### ¿Puedo seguir Poczta Polska en inglés?

Sí. eMonitoring abre en polaco pero dispone de versión en inglés.

### ¿Cuánto tarda Pocztex en entregar?

Poczta Polska garantiza la entrega de Pocztex Kurier como máximo el quinto día laborable tras la admisión, o el séptimo para paquetes 2XL y no estándar. Los días laborables son de lunes a viernes salvo festivos.

### ¿Puedo redirigir un paquete de Poczta Polska?

No como destinatario. Las condiciones de Poczta Polska reservan los cambios de dirección o destinatario al remitente, y los paquetes enviados a un punto o taquilla Pocztex no pueden trasladarse a otro.

:::sources
- [Poczta Polska: Track your shipment](https://www.poczta-polska.pl/en/sledzenie-przesylek/) – formato de números, alcance, puntos de recogida, operadores extranjeros
- [Poczta Polska: Śledź przesyłkę](https://www.poczta-polska.pl/sledzenie-przesylek/) – lo mismo en polaco
- [Poczta Polska: eMonitoring (inglés)](https://emonitoring.poczta-polska.pl/?lang=en) – ejemplos, mensaje de error, carácter informativo, kontakt@
- [Poczta Polska: eMonitoring](https://emonitoring.poczta-polska.pl/) – versión polaca, textos de estados
- [Pocztex: Śledzenie przesyłek](https://www.pocztex.pl/sledzenie-przesylek/) – seguimiento Pocztex
- [Poczta Polska: Regulamin usługi Pocztex](https://www.poczta-polska.pl/wp-content/uploads/2026/08/Regulamin-swiadczenia-uslugi-Pocztex-w-obrocie-krajowym-dla-klienta-detalicznego-obowiazuje-od-24.08.2026-r.pdf) – entrega, avisos, puntos, taquillas, plazos, reclamaciones, garantía
- [Poczta Polska: Regulamin usług powszechnych](https://www.poczta-polska.pl/wp-content/uploads/2026/07/Regulamin-swiadczenia-uslug-powszechnych-tekst-jednolity.pdf) – awizo, custodia, pérdida, reclamaciones internacionales
- [Poczta Polska: Lista zdarzeń / Events list](https://www.poczta-polska.pl/wp-content/uploads/2025/02/PPSA_events_lists.pdf) – nombres de eventos y posibles cambios
- [Poczta Polska: Pocztex PUNKT and AUTOMAT FAQ](https://odbiorwpunkcie.poczta-polska.pl/en/faq/) – tiendas asociadas, taquillas, sin redirección, Contact Center
- [Pocztex: FAQ](https://www.pocztex.pl/en/faq-en/) – avisos, puntos de recogida, acta de daños
- [Poczta Polska: eReklamacje, paczka pocztowa](https://ereklamacje.poczta-polska.pl/sub/reklamacje-krajowe/paczka-pocztowa) – quién reclama, plazos, 30 días
- [Poczta Polska: Contact](https://www.poczta-polska.pl/en/kontakt/) – teléfono general, horarios, formulario
- [Poczta Polska: Kontakt](https://www.poczta-polska.pl/kontakt/) – versión polaca, formulario de reclamación
- [Pocztex: Kontakt](https://www.pocztex.pl/kontakt/) – líneas Pocztex y horarios
- [Poczta Polska: Beware of fake "Pocztex" messages](https://www.poczta-polska.pl/en/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – SMS falsos, 8080
- [Poczta Polska: Uwaga na fałszywe wiadomości „Pocztex”](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – versión polaca, Contact Center
- [Poczta Polska: Changes in fees for shipments from outside the EU](https://www.poczta-polska.pl/en/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – arancel de 3 €, regalos hasta 45 €, rechazo
- [Poczta Polska: Nowe przepisy Unii Europejskiej. Tak działa nowa opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – tasa europea de tramitación de 2 € por partida a cargo del destinatario, transferida a KAS
- [Poczta Polska: Cennik opłat dodatkowych](https://www.poczta-polska.pl/wp-content/uploads/2025/04/cennik-oplat-dodatkowych.pdf) – tarifas de declaración aduanera
- [Poczta Polska: Poczta Polska SA Group](https://www.poczta-polska.pl/en/grupa-pp/poczta-polska/) – empresa estatal
- [Pocztex Mobile en App Store](https://apps.apple.com/pl/app/pocztex-mobile/id1661761150) – la aplicación
- [Universal Parcel Scraper: Poczta Polska](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/poczta-polska) – detección, números de 18/19/20 cifras, campos leídos y omitidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – qué lee Peek
:::
