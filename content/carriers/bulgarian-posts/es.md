---
title: Seguimiento Bulgarian Posts: códigos PS/BG y aduanas
description: Rastrea un paquete de Bulgarian Posts: códigos PS y S10 BG, estados en inglés y búlgaro, custodia de 20 días, tasas aduaneras UE y contacto.
slug: seguimiento-bulgarian-posts
published: 2026-11-24
updated: 2026-11-24
---

Bulgarian Posts (*Български пощи ЕАД*) es el operador postal universal nacional de Bulgaria, encargado de cartas, e-paquetes y paquetería nacional, así como de envíos internacionales de la Unión Postal Universal (UPU) y entregas urgentes EMS / Bulpost. Puedes rastrear un envío en la [página de seguimiento de Bulgarian Posts](https://www.bgpost.bg/en/postal-services/track-package), o pegar el número de seguimiento en el cuadro superior.

## Números de seguimiento de Bulgarian Posts: formatos PS y S10

Busca el código de barras de 13 caracteres en el resguardo de admisión, en el correo de envío de la tienda online o en el aviso de entrega en papel (*служебно известие*). En [bgpost.bg/en/postal-services/track-package](https://www.bgpost.bg/en/postal-services/track-package), Bulgarian Posts indica dos formatos con seguimiento:

| Formato (inventado) | Qué es |
| --- | --- |
| `PS48291736405` | Envío nacional o e-paquete: `PS` seguido de 11 letras y dígitos (13 caracteres en total). |
| `RR482917367BG` | Carta certificada o pequeño paquete internacional (`R`, `C`, `E`, `L` o `V` + segunda letra + 9 dígitos + `BG`). |
| `CP482917367BG` | Paquete postal internacional enviado desde Bulgaria (`CP` + 9 dígitos + `BG`). |

Los paquetes entrantes enviados desde el extranjero mantienen el código UPU S10 del correo de origen (terminado en el código de dos letras de ese país, como `DE`, `CN` o `US`). Bulgarian Posts señala que los envíos internacionales salientes se siguen en su web hasta salir de Bulgaria y luego en la web del correo de destino, mientras que los entrantes aparecen en Bulgarian Posts al llegar a Bulgaria. Para otros códigos de mensajería en Bulgaria, consulta [Sameday](carrier:sameday), [BOX NOW](carrier:box-now) o [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Bulgarian Posts

El seguimiento de Bulgarian Posts muestra eventos UPU IPS en inglés en los tramos internacionales — etiquetados `(Otb)` en salida o `(Inb)` en entrada — junto con descripciones en búlgaro para los escaneos nacionales (incluso en la versión inglesa de la web).

| Bulgarian Posts muestra | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Receive item from customer (Otb)`, `Получаване пратка от клиент (А10)`, primer `Приемане` | El remitente lo entregó en una oficina de correos. | «Admitido por el transportista» |
| `Insert item into bag (Otb) and Send item abroad`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Embolsado para exportación, expedido al extranjero o recibido en la oficina internacional de cambio de Bulgaria. | «En tránsito» |
| `Receive item at sorting centre (Inb)`, `Insert item into domestic bag`, `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Приемане` posterior | Avanzando por los centros de clasificación búlgaros hacia tu oficina de correos local. | «En tránsito» |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Presentado a la aduana búlgara o a la espera de documentos/valoración aduanera (a menudo con una nota indicando espera de presentación a un agente de aduanas). | «En aduanas» |
| `Return item from customs (Inb)` | Liberado por aduanas y devuelto a la red de Bulgarian Posts (no es una devolución al remitente). | «En tránsito» |
| `Handover item to delivery agent` | En ruta con el cartero para entrega a domicilio hoy. | «En reparto» |
| `Unsuccessful item delivery attempt (Inb)`, `Първо посещение` | Primer intento de entrega fallido; se dejó aviso escrito y el envío pasa a tu oficina de correos. | «Intento de entrega» |
| `Receive item at collection point for pick-up (Inb)`, `За получаване в ПС`, `Второ посещение` | Esperando recogida en tu oficina de correos local (`Второ посещение` marca el segundo aviso escrito enviado tras 10 días naturales). | «Listo para recoger» |
| `Deliver item (Inb)`, `Доставена`, `Доставена пратка` | Entregado y firmado. | «Entregado» |

Ten en cuenta que `Създаване` (cuando Bulgarian Posts crea su registro nacional justo después de un escaneo de entrada en la oficina de cambio) es una anotación administrativa sin movimiento físico, así que Peek conserva la fase del escaneo anterior. Del mismo modo, `Второ посещение` no es un intento fallido a domicilio: registra el segundo aviso de recogida mientras tu paquete sigue esperando en la oficina.

## Recogida en oficina, plazos de custodia y almacenaje

Según las [Condiciones Generales de Bulgarian Posts (ОУ 2023)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) (puntos 43, 45 y 46, enlazadas desde [General Terms](https://www.bgpost.bg/en/about_us/conditions)):

- **Envíos certificados y paquetes:** se entregan en mano al destinatario o a una persona autorizada contra firma y documento de identidad con foto. Los paquetes postales (*пощенски колети*) se entregan normalmente en la oficina de correos de destino tras dejar un aviso escrito (*писмено служебно известие*), salvo que se haya pagado aparte la entrega a domicilio.
- **Plazo estándar de custodia — 20 días naturales:** tanto los envíos certificados tras un intento fallido como los paquetes en oficina permanecen **20 días naturales** desde su llegada a la oficina distribuidora antes de devolverse al remitente.
- **Segundo aviso a los 10 días:** si no recoges el envío en **10 días naturales**, Bulgarian Posts envía un segundo aviso escrito (`Второ посещение`).
- **Tasa de almacenaje tras 9 días:** para paquetes postales no recogidos en los **9 días naturales** posteriores al aviso, Bulgarian Posts aplica una tasa de almacenaje (*магазинаж*) según tarifa vigente.
- **Envíos despachados en aduana — 45 días naturales:** según [las normas aduaneras de Bulgarian Posts](https://www.bgpost.bg/en/customs), **todos los envíos que pasan por aduana se recogen únicamente en la oficina de correos designada** (nunca se entregan a domicilio) y se guardan **45 días naturales** antes de ser devueltos al remitente.

## Paquetes de fuera de la UE, tasas aduaneras y arancel de 3 € / tasa UE de 2 €

Los envíos que viajan dentro de la UE no pasan aduanas. Para compras online y regalos que llegan a Bulgaria desde fuera de la UE, [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs) y la [Comisión Europea](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) detallan los cargos vigentes:

- **Arancel fijo y tasa de tramitación de la UE:** se aplica un **arancel aduanero fijo de 3 € por artículo** a compras online B2C de hasta 150 € (en vigor desde el 1 de julio de 2026) y una **tasa de tramitación de la Unión (UHF) de 2 € por artículo individual** a compras online fuera de la UE sea cual sea su valor (en vigor desde el 1 de noviembre de 2026; incluida en el valor en aduana a efectos de IVA; exenta para regalos privados C2C). Tanto el arancel de 3 € (hasta 150 €) como la tasa UHF de 2 € **no son reembolsables** aunque devuelvas productos defectuosos.
- **Tarifas de representación aduanera de Bulgarian Posts (IVA incluido, en vigor desde el 2 de noviembre de 2026):**
  - B2C hasta 150 € con IOSS (declaración H7): **2,70 €** (más arancel si la tienda no lo cobró en el pago).
  - B2C hasta 150 € sin IOSS (Régimen Especial, H7): **3,80 €** (más IVA y arancel).
  - B2C de 150 € a 1.000 € (H6): **12,00 €** (más IVA y arancel).
  - B2C superior a 1.000 € (H1): **20,00 €** por el primer tipo de artículo más **5,00 €** por cada tipo adicional.
  - Regalos privados C2C: **2,70 €** hasta 45 € (sin IVA ni arancel), **3,80 €** de 45 € a 1.000 €, o **20,00 €** (+ 5,00 € por tipo adicional, requiere EORI) por encima de 1.000 €.
- **Documentos exigidos:** declaración de consentimiento, declaración de valor y, en compras, pedido/factura coincidente con el número de seguimiento y justificante de pago (extracto bancario, tarjeta o PayPal).

Consulta [paquetes retenidos en aduanas](guide:customs) para orientación general sobre aduanas en la UE.

## Paquetes extraviados, retrasados o dañados y derechos del consumidor

Cuando compras en una tienda online, el comercio responde de la entrega y los reembolsos. Según la Comisión de Protección al Consumidor de Bulgaria ([normas de compra online de la KZP](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping)), los pedidos online deben entregarse en un plazo máximo de 30 días salvo acuerdo distinto, y dispones de 14 días de desistimiento desde la recepción.

**Presentar una reclamación ante Bulgarian Posts.** Conforme a [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) y los puntos 56–59 de las Condiciones Generales, antes de la entrega es el **remitente** quien presenta la reclamación: el destinatario solo puede reclamar antes de recibir el envío con consentimiento escrito del remitente. El plazo es de **6 meses** desde la admisión (en cualquier oficina postal o en línea mediante el portal RQMS) adjuntando copia del resguardo de admisión. Bulgarian Posts debe responder en **1 mes** para envíos nacionales y **3 meses** para internacionales, abonando la indemnización aprobada en **1 mes** tras la notificación.

**Escalado ante el regulador.** Las disputas postales sin resolver pueden elevarse a la Comisión de Regulación de Comunicaciones de Bulgaria ([reclamaciones CRC / КРС](https://crc.bg/bg/rubriki/271/jalbi)), que responde en 30 días (hasta 2 meses si requiere pruebas adicionales). Ante retrasos o incidencias, consulta [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating), [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contactos de Bulgarian Posts

Contactos publicados en [bgpost.bg/en/contacts](https://www.bgpost.bg/en/contacts), [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) y [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs):

- **Atención general al cliente:** `*7678` o `+359 2 962 50 50` (días laborables 08:00–17:30), correo `info@bgpost.bg`.
- **Mensajería EMS / Bulpost:** `*6666` o `0700 19 666` (días laborables 07:00–19:00).
- **Servicio de aduanas (1900 Sofía BESC):** `customerservice@bgpost.bg` (lun–vie 08:00–16:00); envíos EMS que empiezan por `E`: `emscustomerservice@bgpost.bg` (lun–vie 08:30–16:00); oficina aduanera de Varna `psm.varna@bgpost.bg` / `052 604 143`.
- **Teléfonos de reclamaciones (días laborables 09:00–17:30):** nacional `+359 2 949 3326`, internacional entrante `+359 2 949 3121`, internacional saliente `+359 2 949 3343`, soporte portal RQMS `02 949 31 90`.
- **Sede central:** 1, Akademik Stefan Mladenov Str., Bl. 31, 1700 Sofía, Bulgaria.

> Desconfía de SMS o correos no solicitados que pidan pagar una tasa con tarjeta en línea para «concertar la entrega» de un paquete de Bulgarian Posts: los envíos con despacho aduanero se pagan y recogen en la oficina de correos asignada. Comprueba tu código directamente en [bgpost.bg](https://www.bgpost.bg/en/postal-services/track-package). Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Sigue un paquete de Bulgarian Posts con Peek

[Peek](/) identifica directamente los códigos `PS` de 13 caracteres (`PS48291736405`, dígitos inventados) como Bulgarian Posts con alta confianza, y reconoce enlaces pegados de `bgpost.bg/postal-services/track-package?itemid=` (incluido `/en/`) y los antiguos `IPSWeb_item_events.asp?itemid=`. Para códigos internacionales UPU S10 terminados en `BG` (como `RR482917367BG` o `CP482917367BG`), Peek sigue automáticamente a Bulgarian Posts como correo de origen preferente; también puedes elegir Bulgarian Posts para códigos S10 entrantes del extranjero pulsando «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete (buscar «Български пощи», «Bulgaria Post» o «BG Post» también lo encuentra). Ten en cuenta que si un código pertenece a Bulpost (`EMS/Bulpost`), Bulgarian Posts redirige a la página propia de Bulpost.

Peek consulta directamente la API pública de seguimiento de Bulgarian Posts y muestra la fase («Admitido por el transportista», «En tránsito», «En aduanas», «En reparto», «Intento de entrega», «Listo para recoger», «Entregado»), las líneas IPS en inglés (junto con líneas nacionales búlgaras y notas aduaneras), oficinas/países (sin códigos postales) y horas de entrega en oficinas búlgaras. Los escaneos en oficinas búlgaras usan hora de Sofía y los de un correo extranjero la zona horaria de ese lugar (por ejemplo hora alemana para Fráncfort); Peek los adapta a la hora de tu dispositivo. El seguimiento de Bulgarian Posts no ofrece fecha estimada de entrega, peso, nombre del servicio, nombre de remitente/destinatario ni foto de prueba de entrega.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que Bulgarian Posts muestra `Handover item to delivery agent`, cada 2 minutos desde las 6:00 hora búlgara, mientras su propio sistema responda. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, comprueba cada hora sea cual sea tu forma de seguirlo, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el seguimiento de Bulgarian Posts no responde y Peek sigue el paquete únicamente mediante servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de reparto mientras lo tengas abierto o tengas las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con la fecha de ese escaneo, por ejemplo «Sin novedades desde el lun 12 oct». La ficha muestra entonces: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Bulgarian Posts.», junto con un enlace «Pide ayuda a Bulgarian Posts» a su página de contacto. Tras tres semanas sin ningún escaneo nuevo, dice: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Bulgarian Posts.»

Puedes elegir los avisos «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa el seguimiento y no puede presentar declaraciones aduaneras ni reclamaciones ante Bulgarian Posts por ti.

## Preguntas sobre el seguimiento de Bulgarian Posts

### ¿Cuánto tiempo guarda Bulgarian Posts un paquete en la oficina de correos?

Los paquetes estándar y envíos certificados permanecen **20 días naturales** desde la llegada a la oficina distribuidora (con segundo aviso a los 10 días y tasa de almacenaje para paquetes tras 9 días). Los envíos despachados en aduana se conservan **45 días naturales** en la oficina de correos designada.

### ¿Qué significa `Второ посещение` en el seguimiento de Bulgarian Posts?

Significa que Bulgarian Posts emitió el segundo aviso escrito de recogida tras 10 días naturales sin retirarlo. Tu paquete sigue esperando en la oficina de correos: Peek lo mantiene en «Listo para recoger».

### ¿Qué significa `Return item from customs (Inb)`?

Indica que la aduana búlgara despachó el envío y lo devolvió a la red de Bulgarian Posts para llevarlo a tu oficina de correos. No significa que el paquete se devuelva al remitente.

### ¿Cuánto cobra Bulgarian Posts por la gestión aduanera?

Con el tarifario en vigor desde el 2 de noviembre de 2026, la tarifa de representación de Bulgarian Posts (IVA incluido) es de 2,70 € para compras IOSS hasta 150 € (o regalos C2C hasta 45 €), 3,80 € para compras sin IOSS hasta 150 € (o regalos de 45 € a 1.000 €), 12,00 € para compras de 150 € a 1.000 € y 20,00 € (+ 5,00 € por tipo de artículo adicional) por encima de 1.000 €, además del arancel UE aplicable (3 € hasta 150 €), la tasa UE de 2 € y el IVA.

:::sources
- [Bulgarian Posts: Home](https://www.bgpost.bg/en) – presentación del operador postal nacional
- [Bulgarian Posts: Item Tracking](https://www.bgpost.bg/en/postal-services/track-package) – reglas de códigos S10 y `PS` de 13 caracteres, alcance del seguimiento entrante/saliente
- [Bulgarian Posts: Customs](https://www.bgpost.bg/en/customs) – arancel de 3 €, tasa UHF de 2 €, tabla de representación aduanera vigente desde el 2 nov 2026 (2,70 € / 3,80 € / 12 € / 20 €), recogida exclusiva en oficina y custodia aduanera de 45 días naturales
- [Bulgarian Posts: Condiciones Generales (ОУ 2023 PDF)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) – puntos 43, 45, 46 (DNI/firma, custodia 20 días naturales, segundo aviso a los 10 días, almacenaje tras 9 días) y puntos 56–59 (reclamaciones)
- [Bulgarian Posts: General Terms Overview](https://www.bgpost.bg/en/about_us/conditions) – servicio postal universal y condiciones EMS/Bulpost
- [Bulgarian Posts: Claims and Inquiries](https://www.bgpost.bg/en/complaints) – regla de reclamación por el remitente, plazo de 6 meses, resolución en 1/3 meses, portal RQMS y teléfonos de reclamaciones
- [Bulgarian Posts: Contacts](https://www.bgpost.bg/en/contacts) – `*7678`, `+359 2 962 50 50`, `info@bgpost.bg`, EMS `*6666` / `0700 19 666`, sede en Sofía
- [Bulgarian Posts: FAQ](https://www.bgpost.bg/en/faq) – preguntas frecuentes de destinatarios
- [Comisión Europea: Reforma Aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € y tasa europea de 2 €
- [KZP: Compras Online](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping) – plazo de entrega de 30 días y desistimiento de 14 días
- [CRC (КРС): Reclamaciones](https://crc.bg/bg/rubriki/271/jalbi) – reclamaciones ante el regulador postal y respuesta en 30 días
- [Universal Parcel Scraper: bulgarian-posts](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bulgarian-posts) – detección `PS` y S10 `BG`, reglas de URL `bgpost.bg`, mapeo de estados IPS inglés/búlgaro, redirección Bulpost, hora de Sofía y enlace de ayuda
:::
