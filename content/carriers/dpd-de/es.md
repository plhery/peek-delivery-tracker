---
title: Seguimiento DPD Alemania: número, estados y ayuda
description: Sigue un paquete de DPD Alemania hacia España o dentro de Alemania: número de 14 cifras, estados en inglés, Predict, enlace con SEUR, aduanas y contacto.
slug: seguimiento-dpd-alemania
published: 2026-11-17
updated: 2026-11-17
---

DPD en Alemania es DPD Deutschland GmbH (Aschaffenburg), empresa del grupo Geopost con 10.000 Paketshops en territorio alemán. Si compras en una tienda alemana con envío a España o dentro de Alemania, puedes seguir el paquete con el número de 14 cifras en la web de DPD o en la casilla de arriba.

Mientras viaja por Alemania lo gestiona DPD Deutschland; al cruzar a España dentro de Geopost, el reparto pasa a [SEUR](carrier:seur) con esas mismas 14 cifras.

## Número de paquete de DPD Alemania: cómo es y dónde está

Según DPD Deutschland, el número figura en el correo de envío de la tienda, en el aviso por e-mail de DPD o —según la FAQ en inglés— en el SMS de DPD (los SMS reales de DPD Alemania salen solo del `+49 177 1787807`). En una tarjeta de aviso en Alemania (*Paketinfokarte*), el número bajo el código QR es el número de paquete.

| Ejemplo (inventado) | Qué es |
| --- | --- |
| `01482910573649` | Número de paquete de 14 cifras con el que funciona el seguimiento de DPD. |
| `014829105736492` | Mismo número seguido de un 15.º carácter de control (cifra o letra mayúscula). |

DPD no detalla ese 15.º carácter: según el código de Peek, es un carácter de control impreso en etiquetas y tarjetas tras las 14 cifras. Para el seguimiento bastan las 14 cifras. Una referencia de pedido no sirve para buscar el envío: DPD exige siempre el número de paquete (*Paketnummer*). Mira también [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

También usan 14 cifras, entre otros, [SEUR](carrier:seur), [BRT](carrier:brt), [DPD Suiza](carrier:dpd-ch), [Chronopost](carrier:chronopost), [Hermes](carrier:hermes-de), DPD France y DPD UK: revisa qué transportista nombra el correo del vendedor.

## Estados del seguimiento de DPD Alemania

Peek consulta a DPD Alemania en inglés y muestra los escaneos tal como los devuelve DPD, en el orden habitual del envío:

| Mensaje de DPD | Qué significa |
| --- | --- |
| `Order information has been transmitted to DPD.` | Preaviso: datos enviados por el remitente, paquete aún no entregado a DPD (`Auftragsdaten übermittelt` en alemán). |
| `Parcelinfo handed to DPD` | Datos recibidos sin el bulto; Peek muestra «Etiqueta creada» (solo llega por servicios externos). |
| `Parcel handed to DPD` | DPD ya tiene el paquete. |
| `Parcel handed to Pickup parcelshop by consignor.` | Dejado en un Pickup Paketshop por el remitente (*consignor*). |
| `In transit.`, `Your parcel is on its way` | En tránsito por la red de DPD. |
| `At parcel delivery centre.` | En un centro de distribución o delegación DPD (*Paketzustellzentrum*). |
| `Out for delivery.` | En furgoneta de reparto para hoy. |
| `Unsuccessful delivery attempt` | Intento fallido; habrá nuevo intento o desvío a tienda Pickup. |
| `Back at parcel delivery centre after an unsuccessful delivery attempt.` | De vuelta en la delegación tras el intento fallido. |
| `Delivered by driver to DPD Pickup parcelshop/ station.` | Depositado en tienda Pickup o Pickup Station («Listo para recoger»). |
| `Picked up from Pickup parcelshop by consignee.` | Recogido en el punto Pickup por el destinatario (*consignee*, tú): entregado. |
| `Delivered.`, `Parcel has been left in: mail box` | Entregado; la segunda línea indica dónde se dejó (buzón). |
| `At parcel delivery centre. (Return to sender)` | En devolución hacia el remitente. |

Ojo a cuatro mensajes: `Pickup ordered for: …` alude a la recogida al remitente, y `Goods issue at sender.` a la salida del almacén del vendedor antes de entregarlo a DPD. `We're sorry but your parcel couldn't be delivered as arranged.` incluye *delivered*, pero avisa de una entrega fallida. `Delivered. (Return to sender)` indica que la devolución ya llegó al remitente.

En la web alemana, seguir sin código postal solo muestra un estado general; si `Im Paketzustellzentrum` no cambia en varios días laborables, pide al vendedor una investigación oficial (*Nachforschung*).

## Entrega, ventana Predict y puntos Pickup

**En Alemania.** DPD estima de 1 a 2 días laborables tras recibir el paquete (sin plazo garantizado en DPD CLASSIC). Si el vendedor dio tu e-mail o móvil, el día del reparto Predict anuncia una ventana de 2 horas, afinada hasta 30 minutos en directo (no disponible en islas). Desde la app DPD, myDPD o el seguimiento con código postal puedes desviar a un vecino cercano, un Paketshop, un lugar seguro (*Abstell-Okay*, con foto obligatoria y traspaso a ti del riesgo de pérdida o daño) u otro día; vale el último cambio antes del escaneo de reparto. El segundo intento es el mismo día o al siguiente laborable, aunque las condiciones generales permiten ya tras el primer intento dejarlo a un vecino a un máximo de 50 m o en el punto Pickup más cercano (salvo oposición). Para recogerlo en Alemania basta documento oficial con foto (o [autorización firmada](https://www.dpd.com/de/de/support/vollmacht/) y documento de quien acude): la FAQ señala 7 días naturales en Paketshop y 3 en Paketstation (7 días hábiles en las condiciones generales tras intento fallido). El reparto en sábado solo existe contratando DPD 12 Uhr (sin islas), y no se admiten recogidas particulares en delegación.

**Hacia España ([SEUR](carrier:seur)).** En las rutas europeas por carretera de Geopost, [SEUR](carrier:seur) asume el tramo español con las mismas 14 cifras y cuenta con miles de puntos SEUR Pickup y taquillas consultables en seur.com (con gestión de entregas desde miSEUR).

**Devoluciones.** Con etiqueta de devolución de la tienda, entrega gratuita en Alemania en Paketshop, Pickup Station o al repartidor de DPD (también con QR móvil en la app DPD o myDPD); consulta [seguimiento de devoluciones](guide:returns-tracking).

## Aduanas: Canarias, Ceuta, Melilla y fuera de la UE

Entre Alemania (salvo Helgoland y Büsingen) y la España peninsular o Baleares no hay aduanas (unión aduanera e IVA de la UE). Sí pasan despacho:

- **Canarias, Ceuta y Melilla:** Canarias queda fuera del ámbito del IVA de la UE, y Ceuta y Melilla fuera tanto del territorio aduanero como del IVA de la UE.
- **Envíos de fuera de la UE:** DPD Deutschland recuerda que generan aranceles, IVA de importación y tasa administrativa si el remitente no los abonó. En la UE, desde el 1 de julio de 2026 un arancel temporal de 3 € por artículo sustituye la exención hasta 150 €, y desde el 1 de noviembre de 2026 se suma una tasa de tramitación de la Unión de 2 € por artículo distinto, repercutible por vendedor o transportista. Ver [paquete retenido en aduanas](guide:customs).

## Paquete parado, marcado como entregado o dañado

El contrato lo firma el remitente con DPD: según DPD Deutschland, el vendedor gestiona reembolsos, reemplazos y reclamaciones, y según Your Europe responde del pedido en la UE hasta que lo recibes.

**Marcado como entregado pero no está.** Comprueba en el seguimiento vecino o punto Pickup, y revisa entrada, garaje, buzón, cubos de basura y puerta trasera. Avisa luego a la tienda y, si hace falta, al formulario de DPD; mira [entregado pero no recibido](guide:delivered-not-received) y [seguimiento sin cambios](guide:tracking-not-updating).

**Daños y plazos en Alemania.** Fotografía caja y artículo y avisa al vendedor. En envíos sujetos a ley alemana: seguro automático hasta 520 € por paquete (a favor del remitente según condiciones generales, hasta el precio acreditado), acción propia del destinatario (`§ 421 HGB`), y plazos de aviso al transportista (`§ 438 HGB`): en la entrega para daños visibles, 7 días en forma textual para daños ocultos y 21 días por retraso. Para litigios no resueltos sobre pérdida o daño, el *Impressum* recoge el arbitraje de la Bundesnetzagentur (*Schlichtungsstelle Post*).

## Atención al cliente de DPD Alemania y España

- **DPD Alemania:** `06021-150415` (`+49 6021 150 415` desde fuera), `info@dpd.de`, menú «Más / Chat / Ayuda / Contacto» en la app DPD o [formulario de DPD Alemania](https://www.dpd.com/de/de/support/kontakt/) (`06021 8430` en el Impressum es la centralita corporativa).
- **España ([SEUR](carrier:seur)):** área de ayuda de miSEUR en seur.com.

> DPD Deutschland advierte de que sus SMS salen solo del `+49 177 1787807` y sus correos de `@dpd.de` o dominios oficiales de DPD (nunca con adjuntos `.doc`). Ante cobros dudosos, comprueba el envío en dpd.com/de/de/ o consulta en `info@dpd.de`. En España, el INCIBE alertó en febrero de 2021 de SMS falsos que suplantaban a SEUR pidiendo un pequeño pago: no abras el enlace, escribe tú la dirección en el navegador y avisa a tu banco si diste datos de tarjeta.

## Seguir un paquete de DPD Alemania con Peek

[Peek](/) admite las 14 cifras con o sin carácter de control y enlaces de `tracking.dpd.de`, `my.dpd.de` y `dpd.com/de`. El prefijo `01` incluye a DPD Germany en la primera tanda junto con [SEUR](carrier:seur); con otros prefijos en España Peek consulta primero a [SEUR](carrier:seur), [Hermes](carrier:hermes-de), DPD Belgium, [DPD Suiza](carrier:dpd-ch) y DPD Países Bajos y enseguida a DPD Germany, mientras que `10` y `1550` apuntan de entrada a DPD France o DPD UK. En automático DPD Germany solo confirma («DPD Germany tiene este paquete.») mientras DPD ubique el envío en Alemania; si no, toca «Cambiar» (o «Cambiar transportista») y elige DPD Germany bajo «Encaja con este número». Una vez elegido, cuando el paquete pasa a otro país, Peek lo mantiene bajo DPD Germany y lee en paralelo el servicio de grupo y la unidad local ([SEUR](carrier:seur), [BRT](carrier:brt), DPD France).

El campo «Código postal de entrega» es opcional y solo acepta códigos alemanes de 5 cifras (déjalo vacío para España). Sin código postal, Peek lee la app alemana de DPD: escaneos con mapa, peso, dimensiones, fecha o franja prevista y, en espera Pickup, nombre y dirección del punto. Con un código alemán válido de 5 cifras lee el seguimiento verificado: escaneos verificados, remitente, peso y franja (sin dimensiones ni dirección Pickup). Si DPD rechaza el código, Peek vuelve al modo sin código y avisa: «DPD Germany no ha aceptado el código postal …, así que muestra menos detalles.». Justificante descartado, nombre de receptor no catalogado mostrado como `Delivery update` y horas alemanas convertidas («Horas en tu zona horaria: …»).

Peek revisa el estado hasta cada 10 minutos mientras tienes el paquete abierto, o el día de la entrega con las notificaciones activadas, y cada hora en los demás casos (también tras dos días sin cambios o 12 horas en «Etiqueta creada» o «Admitido por el transportista»). En «En reparto» comprueba hasta cada 2 minutos desde las 6:00 locales —o 30 minutos antes de la franja DPD— mientras responda directamente DPD. Mediante servicios externos consulta cada hora, cada 3 horas tras un día sin escaneos y hasta cada 15 minutos el día de la entrega mientras tienes el paquete abierto o las notificaciones activadas («Cada escaneo», «Pasos importantes» o «Solo la entrega»).

## Preguntas frecuentes sobre el seguimiento de DPD Alemania

### ¿Por qué no reconoce mi número de DPD Alemania?

La página de aviso de myDPD cita tres causas: error al escribirlo (`O` por `0` según la FAQ), envío archivado tras 180 días, o paquete aún no entregado a DPD.

### ¿Cuánto tarda en llegar un paquete de DPD Alemania?

En Alemania suele tardar 1–2 días laborables tras la entrega a DPD (no garantizados en tarifa estándar); por carretera en Europa con la red Geopost/SEUR Classic, Packlink sitúa el plazo orientativo en 2 a 4 días.

### ¿DPD Alemania reparte los sábados?

En Alemania solo como suplemento del servicio urgente DPD 12 Uhr (sin islas, entregando el viernes).

### ¿Puedo recoger mi paquete en la delegación de DPD en Alemania?

No: no hay recogida particular en los centros de distribución. Desvía el envío a una tienda consultando el [buscador de Paketshops de DPD](https://www.dpd.com/de/de/pickup-paketshops/paketshop-finden/).

:::sources
- [DPD: Startseite](https://www.dpd.com/de/de/) – 10.000 Paketshops
- [DPD: Paketnummer finden](https://www.dpd.com/de/de/faq/wo-finde-ich-meine-paketnummer/) – e-mail, tarjeta
- [DPD: Parcel number](https://www.dpd.com/de/en/faq/where-do-i-find-my-parcel-number/) – SMS
- [DPD: Nummer unter QR-Code](https://www.dpd.com/de/de/faq/was-bedeutet-die-nummer-unter-dem-qr-code/) – Paketinfokarte
- [DPD: Paketnummer nicht erkannt](https://www.dpd.com/de/de/faq/meine-paketnummer-wird-nicht-erkannt-was-kann-ich-tun/) – 0 y O
- [DPD: Referenznummer](https://www.dpd.com/de/de/faq/was-ist-eine-referenznummer/) – referencia
- [myDPD: Postleitzahl](https://my.dpd.de/myparcels/dataprotection-tracking.aspx) – 180 días
- [DPD: Sendungsverfolgung](https://www.dpd.com/de/de/support/empfaenger/sendungsverfolgung/) – 14 cifras, plazos
- [DPD: PLZ beim Tracking](https://www.dpd.com/de/de/faq/warum-muss-ich-beim-tracking-meine-plz-eingeben/) – sin CP
- [DPD: Auftragsdaten übermittelt](https://www.dpd.com/de/de/faq/was-heisst-auftragsdaten-uebermittelt/) – preaviso
- [DPD: Predict](https://www.dpd.com/de/de/faq/was-ist-predict/) – ventana
- [DPD: Paket empfangen](https://www.dpd.com/de/de/empfangen/) – islas, desvíos
- [DPD: Mehrmals umleiten](https://www.dpd.com/de/de/faq/kann-ich-mein-paket-mehrmals-umleiten/) – último cambio
- [DPD: Abstellbedingungen](https://www.dpd.com/wp-content/uploads/sites/59/2026/09/20260923_Abstellbedingungen_DE_final.pdf) – foto, riesgo
- [DPD: Zweiter Zustellversuch](https://www.dpd.com/de/de/faq/das-paket-konnte-nicht-zugestellt-werden-gibt-es-einen-zweiten-zustellversuch/) – 2.º intento
- [DPD: Paket abholen](https://www.dpd.com/de/de/support/empfaenger/paket-abholen/) – 7 y 3 días
- [DPD: Ohne Paketinfokarte](https://www.dpd.com/de/de/faq/kann-ich-mein-paket-ohne-paketinfokarte-abholen/) – documento
- [DPD: Vollmacht](https://www.dpd.com/de/de/support/vollmacht/) – autorización
- [DPD: Parcel return](https://www.dpd.com/de/en/support/recipients/parcel-return/) – devoluciones
- [DPD: AGB DPD CLASSIC](https://www.dpd.com/wp-content/uploads/sites/59/2026/07/AGB-DPD-CLASSIC_06-2026_DE_END.pdf) – 50 m, 7 d hábiles
- [DPD: Zusatzleistungen](https://www.dpd.com/wp-content/uploads/sites/59/2025/01/Beforderungsbedingungen-Zusatzleistungen-01-2025_DE_END.pdf) – sábado
- [DPD: Delivery center](https://www.dpd.com/de/en/faq/can-i-pick-up-my-parcel-myself-at-the-dpd-delivery-center/) – delegación
- [DPD: Paketshop finden](https://www.dpd.com/de/de/pickup-paketshops/paketshop-finden/) – horarios
- [DPD: DPD oder Versender](https://www.dpd.com/de/de/faq/wann-wende-ich-mich-an-dpd-und-wann-an-den-versender/) – vendedor
- [DPD: Zugestellt, nicht erhalten](https://www.dpd.com/de/de/faq/mein-paket-wurde-laut-tracking-zugestellt-aber-ich-habe-es-nicht-erhalten-was-kann-ich-tun/) – búsqueda
- [DPD: Delivered, not here](https://www.dpd.com/de/en/faq/my-parcel-was-delivered-but-it-is-not-here-where-is-it/) – +49 6021 150 415
- [DPD: Paket beschädigt](https://www.dpd.com/de/de/faq/mein-paket-ist-beschaedigt-angekommen-was-kann-ich-tun/) – daños
- [DPD: Versicherung](https://www.dpd.com/de/de/faq/wie-hoch-ist-mein-paket-versichert/) – 520 €
- [HGB § 421](https://www.gesetze-im-internet.de/hgb/__421.html) – destinatario
- [HGB § 438](https://www.gesetze-im-internet.de/hgb/__438.html) – plazos
- [DPD: Impressum](https://www.dpd.com/de/de/impressum/) – sede, BNetzA
- [DPD: Zustellung und Zoll](https://www.dpd.com/de/de/support/empfaenger/zustellung-und-zoll/) – aduanas
- [DPD: Kontakt](https://www.dpd.com/de/de/support/kontakt/) – teléfono, e-mail
- [DPD: App-Support](https://www.dpd.com/de/de/faq/wie-kontaktiere-ich-den-support-ueber-die-app/) – app
- [DPD: Sicherheit](https://www.dpd.com/de/de/sicherheit/) – SMS, .doc
- [DPD: Phishing-Warnung](https://www.dpd.com/de/de/news/warnung-vor-phishing-mails-und-sms-im-namen-von-dpd/) – @dpd.de
- [Geopost: SEUR](https://www.geopost.com/en/about-us/our-companies-and-partners/seur/) – red SEUR
- [Packlink: SEUR](https://www.packlink.es/seur/) – 2 a 4 días
- [INCIBE: Aviso SMS SEUR](https://www.incibe.es/ciudadania/avisos/circulan-sms-fraudulentos-que-suplantan-seur-que-no-te-enganen) – fraude SMS
- [Your Europe: Entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm) – reglas UE
- [UE: Territorial Scope](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias, Ceuta, Melilla
- [UE: Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € y 2 €
- [Revenue: Handling Fee](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € por artículo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – seguimiento Peek
:::
