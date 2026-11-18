---
title: Seguimiento de India Post: números Speed Post y estados
description: Rastrea un envío de India Post o Speed Post: formato del número terminado en IN, qué significa cada estado, entrega con Correos u otro correo local y aduanas.
slug: seguimiento-india-post
published: 2026-11-18
updated: 2026-11-18
---

India Post es el Department of Posts, el servicio postal público de la **India**, que cuenta según su web oficial con 164.999 oficinas de correos: la mayor red postal del mundo, afirma. Dentro de la India opera Speed Post, correo certificado e India Post Parcel; hacia el extranjero envía EMS (International Speed Post), paquetes ligeros con seguimiento (International Tracked Packet) y paquetes aéreos. Puedes consultar un envío en el buscador de [indiapost.gov.in](https://www.indiapost.gov.in/), que pide resolver un CAPTCHA, o pegar el código en el cuadro de arriba. India Post lo llama *article barcode number* (número de código de barras del envío); si compraste por internet, pídeselo al vendedor.

## Número de seguimiento de India Post: cómo es

Un número de India Post tiene 13 caracteres en el [formato postal internacional](guide:tracking-number-formats): dos letras, nueve cifras y `IN` al final. La última de las nueve cifras es un dígito de control. Ejemplos con cifras inventadas:

| Ejemplo inventado | Qué identifica |
| --- | --- |
| `EE482910576IN` | Un envío numerado por India Post, tanto nacional dentro de la India como internacional. Las dos letras iniciales cambian según el servicio; lo decisivo es el `IN` final. |
| `EE482910576ES` | Termina en otras letras, aquí `ES`: lo emitió el operador postal de otro país ([Correos](carrier:correos-spain)). Un paquete enviado a la India desde el extranjero conserva el número del correo de origen. |

El localizador de India Post permite seguir Speed Post, envíos certificados y asegurados, Business Parcel y Express Parcel, giros electrónicos (*electronic money orders*) e International EMS. ¿No lo encuentras? Consulta [cómo encontrar el número de seguimiento](guide:find-tracking-number).

## Qué significan los estados de seguimiento de India Post

Las preguntas frecuentes de India Post resumen el recorrido en seis pasos: *booked*, *received*, *bagged*, *dispatched*, *out for delivery* y *delivered*. Estas son las líneas que reconoce Peek en el orden habitual:

| India Post muestra | Qué significa para tu envío |
| --- | --- |
| `Item Booked` | El remitente lo depositó en oficina o lo registró en línea e India Post lo admitió. |
| `Bag Received for Forwarding` | La saca que contiene el envío llegó a una oficina para su reexpedición. |
| `Assigned to load plan` | Está asignado al siguiente transporte programado. |
| `Transport Leg Completed` | Ha finalizado un tramo del transporte. |
| `Transfer to OOE (Otb)` | En camino a la oficina de cambio india (*Office of Exchange*) que despacha el correo al extranjero. |
| `Aircraft Departure` | El vuelo que lo transporta ha despegado. A veces India Post escribe `UPLIFT`. |
| `Bag unloaded at destination Airport` | Su saca ha sido descargada en el aeropuerto del país de destino. |
| `Send item to domestic location (Inb)` | La oficina de cambio de destino lo ha encaminado a la red postal interna de ese país. |
| `Item Delivered` | Entregado. |

La marca `(Otb)` identifica un escaneo del correo emisor en la India y `(Inb)` uno del operador postal del país de destino retransmitido por India Post. En la fuente que lee Peek, India Post envía ahora a menudo códigos como `BAG_DISPATCH`; Peek despliega los que conoce —por ejemplo, `Bag Dispatched` o `Item Presented to Customs`— y convierte el resto de códigos en palabras legibles. Más detalles en [qué significan los estados de seguimiento](guide:tracking-statuses).

## Recibir un envío de India Post dentro de la India

Las normas siguientes rigen para entregas dentro de la **India**:

**Plazos de entrega.** Los estándares publicados por India Post para Speed Post en la India, desde la admisión hasta la entrega, son de 1 a 2 días en envíos locales, de 1 a 3 días entre grandes áreas metropolitanas, de 1 a 4 días entre capitales estatales o dentro del mismo estado, y de 4 a 5 días para el resto del país, más un día adicional cuando reparte una oficina sucursal (*branch office*). El servicio 24 Speed Post entrega al día siguiente en seis metrópolis indias (Nueva Delhi, Bombay, Bangalore, Chennai, Calcuta y Hyderabad), los siete días de la semana; si llega tarde, el remitente puede reclamar en línea la devolución del franqueo en un plazo de 7 días.

**SMS y código OTP.** En un paquete Speed Post dentro de la India, el móvil registrado recibe un SMS cuando el envío llega a la oficina de reparto y otro al entregarse. Algunos envíos Speed Post solo se entregan contra un código de un solo uso (OTP): viene incluido de serie en 24 Speed Post y se contrata como suplemento de pago en el resto de Speed Post.

**Entrega fallida.** El seguimiento de India Post refleja cuándo se emite un aviso (*Delivery Intimation Notice*) indicando que el envío está disponible para su entrega. Consulta también [entrega fallida](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

**Cambio de dirección.** En la India, el remitente que haya registrado el envío en línea desde una cuenta de India Post puede solicitar su recuperación o redirección mientras no se haya entregado ni asignado a un cartero para el reparto.

## Paquetes de India Post al extranjero: quién entrega y aduanas

Al exterior, India Post envía EMS (International Speed Post, hasta 35 kg), International Tracked Packet pensado para comercio electrónico (hasta 5 kg) e International Air Parcel (hasta 20 kg), todos con seguimiento. Una vez que el envío aterriza, los eventos con `(Inb)` proceden del correo de tu país: puedes seguirlo allí con el mismo número terminado en `IN`, por ejemplo en [Correos](carrier:correos-spain) en **España**, [USPS](carrier:usps) en **Estados Unidos**, [Royal Mail](carrier:royal-mail) (Parcelforce para EMS) en el **Reino Unido**, Canada Post en **Canadá** o Australia Post en **Australia**.

**Aduanas en la Unión Europea y España.** Como la India está fuera de la Unión Europea, el paquete pasa por aduanas al llegar. En el territorio aduanero de la **Unión Europea**, desde el 1 de julio de 2026 los bienes comprados en línea fuera de la UE en envíos de hasta 150 € pagan un arancel aduanero de la UE de 3 € por artículo (*per item*); desde el 1 de noviembre de 2026 se suma además una tasa de tramitación de la Unión de 2 € por artículo, cualquiera que sea su valor, aparte del IVA de importación nacional y, por encima de 150 €, de los aranceles ordinarios. Se considera un artículo el conjunto de mercancías de un paquete que comparten clasificación arancelaria, descripción y, cuando se exige, origen. Ten en cuenta que, según las reglas territoriales de la Comisión Europea, en **España** las **Islas Canarias** quedan fuera del territorio del IVA de la UE (allí rige su fiscalidad propia, mientras que sí forman parte del territorio aduanero europeo) y **Ceuta** y **Melilla** quedan fuera tanto del territorio aduanero como del ámbito del IVA de la UE. En países de **América Latina** o en **Estados Unidos**, se aplican los tributos y franquicias de cada país: consulta [paquetes retenidos en aduanas](guide:customs). India Post incluye Delivery Duty Paid (DDP) entre los servicios adicionales para exportadores; pregunta al vendedor si lo contrató.

**Suspensiones de servicio.** Cuando India Post suspende o reanuda los envíos hacia un país —como la suspensión para Canadá en octubre de 2025 o la reanudación para Irak, Kuwait y Líbano en agosto de 2026—, publica un aviso en su sección [News and Updates](https://www.indiapost.gov.in/media/newsandupdates).

**Envíos hacia la India.** Un paquete enviado desde el extranjero a la India viaja con el número del correo de origen. Consúltalo primero allí: las preguntas frecuentes de India Post advierten de que los datos de administraciones postales extranjeras pueden recibirse con retraso.

## El seguimiento de India Post no se actualiza, paquete perdido o devuelto

**Sin escaneos durante días.** India Post señala que la falta de un escaneo no implica que el envío no haya salido: la actualización en línea puede tardar, sobre todo en zonas rurales de la India o cuando depende de un correo extranjero. Si un paquete internacional muestra por último `Aircraft Departure`, ya salió de la India: comprueba el mismo número en la web del correo de tu país. Más información en [por qué no se actualiza el seguimiento](guide:tracking-not-updating).

**Retraso, pérdida o daños (condiciones de India Post).** Ante India Post se puede reclamar una vez vencido el plazo estándar del servicio y dentro de los 60 días siguientes a la operación, indicando el número del envío; el plazo estándar de resolución de India Post es de 90 días. En Speed Post nacional dentro de la India, el retraso da derecho al reembolso de la tarifa abonada, y la pérdida, sustracción parcial o daño se indemniza con el doble de los portes o 1.000 ₹, la cifra menor. En EMS internacional de mercancías, la cobertura llega hasta el valor declarado o 130 DEG (derechos especiales de giro), lo que sea menor, más el franqueo, y una demora superior a 5 días sobre la norma publicada devuelve el 5 % del porte; un International Tracked Packet cubre hasta su valor o 1.000 ₹, el importe inferior. Si compraste en una tienda en línea, avísale también a ella: mira [paquetes perdidos](guide:lost-parcel) y [paquetes dañados](guide:damaged-parcel).

**Figura como entregado pero no aparece.** Reclama por las vías de abajo o, en la India, en el libro de reclamaciones disponible en todas las oficinas postales: consulta [entregado pero no recibido](guide:delivered-not-received).

**Devuelto al remitente.** Al registrar un envío en línea en India Post, el remitente puede fijar una dirección de devolución distinta de la suya. Peek muestra las devoluciones como «Devuelto al remitente»: consulta [devuelto al remitente](guide:returned-to-sender).

## Teléfono de atención al cliente de India Post y reclamaciones

Canales oficiales de India Post:

- **Atención telefónica (gratuita dentro de la India):** `1800 266 6868`. Los agentes atienden de 8:00 a 20:00 (hora de la India, IST); la línea automática (IVRS) funciona 24/7, domingos incluidos, salvo festivos oficiales (*gazetted holidays*) de la India.
- **Reclamaciones:** en línea en la página [File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) de India Post (donde también puedes seguir el expediente), presencialmente en una oficina postal india (ante el *postmaster* o el *Superintendent of Post Offices*) o a través del centro de llamadas. No hace falta cuenta.
- **Escalamiento en la India:** si India Post no resuelve la queja en un plazo razonable, el Directorate of Public Grievances del Gobierno indio (`dpg.gov.in`).

En su portada, India Post advierte contra enlaces falsos que suplantan su identidad: utiliza únicamente `www.indiapost.gov.in` y no compartas nunca códigos OTP ni datos bancarios, de tarjeta o de UPI mediante enlaces no autorizados («do not share OTP, banking, card, UPI or other personal/financial information through unauthorized links»). Consulta [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de India Post con Peek

[Peek](/) reconoce dos letras, nueve cifras e `IN` como India Post cuando el dígito de control cuadra, y muestra «Transportista detectado»; con «Cambiar» puedes elegir otro. Si el dígito de control no coincide (por ejemplo `EE482910577IN`), avisa «El dígito de control de este número no cuadra.»: revisa el código recibido o pulsa «Seguir tal como está». Guardado bajo India Post, el envío muestra «Esperando a India Post» en peektracker.com hasta su primer escaneo.

Peek no consulta directamente el portal de India Post: lee sus escaneos a través de MySpeedPost, un buscador ajeno al dominio de India Post que los obtiene de India Post (o a través de track91, el rastreador del mismo operador, cuando MySpeedPost le deriva la consulta). Si esa copia guardada tiene más de 30 minutos, Peek pide a MySpeedPost que la refresque y conserva la anterior si no lo consigue, por lo que un evento nuevo puede tardar algo más en aparecer.

Peek muestra cada línea con la redacción de India Post, junto con la oficina y, dentro de la India, su código PIN de seis cifras (track91 no lo incluye), y sitúa la oficina en el mapa cuando su localidad está verificada. Un despegue aparece como `Aircraft Departure`, o con el número de vuelo y ambos aeropuertos cuando India Post detalla la ruta. Además agrupa los eventos en sus propias etapas: un intento sin entrega pasa a «Intento de entrega», y un rechazo, daño, extravío o dirección insuficiente a «Incidencia». A través de track91 los textos son más breves: `Dispatched` (expedido al exterior por la oficina de cambio) y `Aircraft arrival in Transit country` (escala en un país de tránsito) se muestran como «En tránsito», y `Delivered` como «Entregado». Peek descarta el nombre del destinatario, su dirección y la firma, y no muestra fecha estimada de entrega. Lee los escaneos en la India en hora india (UTC+5:30) y los del extranjero en el reloj del país de destino desde el aterrizaje o el primer evento `(Inb)`, tanto por MySpeedPost como por track91, convirtiendo cada hora a la zona de tu dispositivo; en peektracker.com y en la app de iPhone, una línea bajo el historial indica esa zona («Horas en tu zona horaria: …»).

Cuando India Post indica el país de destino —o no lo indica y consultas el paquete desde fuera de la India—, Peek pregunta también con el mismo número `IN` al correo que conoce allí, como [Correos](carrier:correos-spain) en España, Correo Argentino en Argentina, [USPS](carrier:usps) en EE. UU., Royal Mail (o Parcelforce en EMS), Canada Post, Australia Post, [DHL](carrier:dhl) en Alemania o [La Poste](carrier:la-poste) en Francia. En cuanto ese operador devuelve escaneos recientes, Peek pasa a seguirlo con él conservando el historial previo de India Post. Peek tiene integración directa para la mayor parte de Europa y varios otros países —incluidos NZ Post en Nueva Zelanda y Qatar Post en Catar—, pero no para México, Chile, Emiratos Árabes Unidos, Kuwait, Omán, Singapur, Malasia o Sri Lanka. Tampoco pregunta a India Post por números emitidos por otro correo: si eliges India Post manualmente para uno de ellos, aparece «no emite números como este».

Mientras tengas abierto el paquete o, el día de su entrega, con las notificaciones activadas, Peek comprueba novedades hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 de la mañana (hora local) el día en que pasa a «En reparto», mientras responda MySpeedPost o el rastreador directo de tu correo de destino (cada 5 minutos si la consulta de ese correo abre navegador, como en DHL, Parcelforce o Australia Post; Royal Mail y Poste Italiane se consultan cada hora, y PostNL y USPS hasta cada 30 minutos; mediante otros servicios de seguimiento solos, cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día del reparto con el paquete abierto o notificaciones activas)—. Fuera de esos casos, así como tras dos días sin novedades (o 12 horas en «Etiqueta creada» o «Admitido por el transportista»), Peek comprueba cada hora. Avisos disponibles: «Cada escaneo», «Pasos importantes» o «Solo la entrega». Si el envío se detiene mientras lo tiene India Post, «Pide ayuda a India Post» abre su página Contact Us. Peek no puede contactar con India Post, tu correo local ni la tienda por ti.

## Preguntas frecuentes sobre el seguimiento de India Post

### ¿Cómo rastreo un envío Speed Post de India Post?

Introduce el código de 13 caracteres terminado en `IN` en indiapost.gov.in o pégalo en Peek, que continúa siguiéndolo hasta la entrega también cuando pasa al correo de tu país.

### ¿Por qué no se actualiza mi seguimiento de India Post?

Los escaneos pueden tardar en publicarse, especialmente desde oficinas rurales de la India o administraciones postales extranjeras. Si el último evento de un envío internacional es `Aircraft Departure`, ya despegó de la India: consulta ese mismo número en el correo de tu país.

### ¿Cuánto tarda Speed Post?

Dentro de la India, el estándar de India Post es de 1 a 3 días entre metrópolis y de 4 a 5 días al resto del país, con un día más si reparte una oficina sucursal; 24 Speed Post entrega al día siguiente en seis metrópolis indias. En envíos internacionales depende de la modalidad y del despacho aduanero en destino.

### ¿Cuál es el teléfono de atención al cliente de India Post?

El `1800 266 6868` (gratuito desde la India): agentes de 8:00 a 20:00 hora de la India y sistema automático las 24 horas salvo festivos oficiales indios. Desde fuera de la India puedes registrar y consultar reclamaciones en línea en indiapost.gov.in sin cuenta.

:::sources
- [India Post: Home](https://www.indiapost.gov.in/) – buscador, teléfono de ayuda y alerta de fraude
- [India Post: Contact Us](https://www.indiapost.gov.in/help&Support/contactus) – número gratuito, horarios y reclamaciones en oficina
- [India Post: Help and FAQs](https://www.indiapost.gov.in/grievance-redressal/help-faq) – código de barras, envíos rastreables, estados, demoras, reclamaciones, redirección y dirección de devolución
- [India Post: About Us](https://www.indiapost.gov.in/ministry/aboutus) – Department of Posts y 164.999 oficinas postales
- [India Post: Speed Post](https://www.indiapost.gov.in/mail&Parcel/mailproducts/premiumservices/speedpost) – OTP, plazos estándar, SMS de Speed Post Parcel e indemnizaciones
- [India Post: 24/48 Speed Post FAQs](https://www.indiapost.gov.in/speedpost/faqs) – entrega al día siguiente en seis metrópolis, OTP y reembolso
- [India Post: India Post Parcel](https://www.indiapost.gov.in/mail&Parcel/mailproducts/domesticservices/indiapostparcel) – India Post Parcel
- [India Post: International Mail](https://www.indiapost.gov.in/mail&Parcel/mailproducts/mailservices/international) – servicios internacionales y opción DDP para exportadores
- [India Post: International services](https://www.indiapost.gov.in/mail&Parcel/mailproducts/internationalservices) – EMS, International Tracked Packet, paquetes aéreos y límites de cobertura
- [India Post: News and Updates](https://www.indiapost.gov.in/media/newsandupdates) – avisos por país
- [India Post: File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) – reclamación en línea y escalamiento ante DPG
- [India Post: Guidelines on Complaint Registration](https://www.indiapost.gov.in/grievance-redressal/guidelines) – plazos y vías para reclamar
- [Comisión Europea: Reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € y tasa de tramitación de 2 €
- [Comisión Europea: Guía sobre el arancel de 3 € y la tasa de tramitación de la Unión (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – aplicación por artículo y qué constituye un artículo
- [Comisión Europea: Ámbito territorial del IVA](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias, Ceuta y Melilla respecto al territorio aduanero y del IVA de la UE
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – textos de escaneo (incluido track91), husos horarios y relevo a correos de destino
:::
