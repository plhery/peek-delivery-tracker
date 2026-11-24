---
title: Seguimiento Liccardi Express Courier: códigos LDV y ayuda
description: Sigue un paquete de Liccardi Express Courier (F.lli Liccardi) en Italia: códigos 660 y LDV, escaneos en sucursal y taquilla, giacenza y soporte.
slug: seguimiento-liccardi
published: 2026-11-24
updated: 2026-11-24
---

**Liccardi Express Courier** (*F.lli Liccardi S.r.l.*) es una empresa italiana de mensajería urgente nacional con sede en Casandrino, cerca de Nápoles ([liccarditrasporti.com](https://www.liccarditrasporti.com/)). Lanzada en su forma actual en 1992 a partir de un negocio familiar de transporte fundado treinta años antes por Giovanni Liccardi, evolucionó desde el transporte de documentos bancarios en los años noventa hacia la distribución nacional B2C y de comercio electrónico, gestionando más de 5,9 millones de envíos al año a través de más de 20 sucursales y hubs italianos como Nápoles, Roma, Milán, Bolonia, Turín, Florencia, Verona, Bari y Salerno. Puedes seguir un envío en el sitio web de Liccardi o pegar tu código de etiqueta `660` de 13 dígitos o tu *lettera di vettura* (LDV) de 7 u 8 dígitos en el cuadro de arriba.

## Números de seguimiento de Liccardi Express Courier (etiqueta `660` y LDV)

Liccardi identifica cada envío mediante dos referencias numéricas: un código de barras de bulto de 13 dígitos que empieza por `66` (normalmente `660`) y un número de envío más corto de 7 u 8 dígitos (*lettera di vettura* / LDV):

| Formato (inventado) | Qué es |
| --- | --- |
| `6604829173605` | Código de barras de bulto Liccardi de 13 dígitos (`660` + número de envío de 8 dígitos + 2 dígitos de control/bulto). |
| `48291736` | Número de envío Liccardi de 7 u 8 dígitos (*LDV* / *lettera di vettura*). |

Revisa el correo de envío de tu tienda o la notificación de entrega de Liccardi para encontrar cualquiera de los dos códigos; ambos corresponden al mismo envío en el sistema de Liccardi. Consulta los [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Liccardi Express Courier

El localizador de Liccardi publica códigos numéricos de resultado (*codici esito*) junto con descripciones de sucursal en italiano. En envíos multibulto (*colli*), Liccardi registra los eventos por bulto; Peek mantiene activo el envío hasta que todos los bultos hayan sido entregados:

| Liccardi muestra (código) | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `DATI SPEDIZIONE TRASMESSI A LICCARDI` (`50`), `SERVIZIO PROGRAMMATO` (`58`), `RITIRO ASSEGNATO AD AUTISTA` (`36`), `IN RITIRO` (`92`) | El remitente transmitió los datos del envío o una recogida por conductor está programada o en curso antes de la admisión. | «Etiqueta creada» |
| `SPEDIZIONE RITIRATA` (`37`), `COLLO PRESO IN CARICO` (`3`), `SPEDIZIONE PRESA IN CARICO` (`4`) | Paquete recogido al remitente y admitido en la red de Liccardi. | «Admitido por el transportista» |
| `ARRIVATO IN FILIALE` (`47`), `IN TRANSITO` (`48`), `SPEDIZIONE PARTITA` (`91`), `ARRIVO SU FILIALE DI DESTINO` (`72`), `IN GESTIONE` (`49`), `GIACENZA SVINCOLATA` (`70`), `IN PRENOTAZIONE` (`33`) | Circulando entre hubs, llegado a la sucursal de destino, concertando cita o liberado de una retención. | «En tránsito» |
| `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`), `IN CONSEGNA` (`52`) | Cargado en la furgoneta local de reparto y en entrega hoy. | «En reparto» |
| `CONSEGNATA AL PUDO` (`6`), `CHECK-IN AL PUDO` (`80`), `SPEDIZIONE DEPOSITATA NEL LOCKER` (`11`), `FERMO DEPOSITO` (`32`) | Listo para recoger en un punto PUDO, taquilla o sucursal local de Liccardi (*fermo deposito*). | «Listo para recoger» |
| `ASSENTE` (`28`), `INDIRIZZO ERRATO` (`40`), `CONSEGNA NON ESEGUITA` (`43`), `MANCA CONTRASSEGNO` (`44`), `INDIRIZZO NON TROVATO` (`45`), `CHIUSO` (`46`), `GIRO DI CONSEGNA NON ULTIMATO` (`67`) | No se pudo completar la entrega (destinatario ausente, error de dirección, contrarreembolso no listo, cerrado o ruta sin completar). | «Intento de entrega» |
| `IN GIACENZA` (`35`), `COLLO DANNEGGIATO` (`30`), `MERCE RESPINTA` (`42`), `SPEDIZIONE SMARRITA` (`54`), `COLLO DISGUIDATO` (`68`), `ORDINE SOSPESO: IN ATTESA DI ISTRUZIONI MITTENTE` (`76`), `DISCONOSCIMENTO FIRMA` (`103`), `CONTATTARE ASSISTENZA` (`107`) | Paquete retenido en almacén esperando instrucciones del remitente (*giacenza*), dañado, rechazado, extraviado, mal encaminado o que requiere soporte. | «Incidencia» |
| `RIENTRO AL MITTENTE` (`31`), `IN CONSEGNA AL MITTENTE` (`53`), `RESO A MITTENTE` (`8`) | En camino de vuelta o devuelto al remitente. | «Devuelto al remitente» |
| `SPEDIZIONE CONSEGNATA` (`1`), `SPEDIZIONE CONSEGNATA A COMMITTENTE` (`5`), `SPEDIZIONE RITIRATA DA LOCKER DAL DESTINATARIO` (`12`) | Entregado al destinatario o recogido en la taquilla de paquetería. | «Entregado» |

Ten en cuenta que la barra de progreso de Liccardi trata los depósitos en taquilla (`11`) como entregados, mientras que Peek clasifica los escaneos PUDO, taquilla y *fermo deposito* como **«Listo para recoger»** hasta que el destinatario recoge realmente el paquete (`12`).

## Plazos de entrega, servicios y red de sucursales en Italia

Según [Servicios Liccardi](https://www.liccarditrasporti.com/servizi/), [Empresa](https://www.liccarditrasporti.com/azienda/) y [Sucursales](https://www.liccarditrasporti.com/filiali/):

- **Plazos de tránsito:** Liccardi recoge los pedidos en los comercios en un plazo de 48 horas, pesa, mide y fotografía cada bulto en sus hubs de clasificación y anuncia entregas nacionales en **24–48 horas** (cubriendo estadísticamente todo el ámbito nacional en **72 horas**, pudiendo tardar más las zonas remotas — *località disagiate* — y los picos estacionales).
- **Opciones de entrega:** exprés B2C estándar, entrega en piso (*consegne al piano*), citas concertadas (*consegne tassative*), entrega en el mismo día, pago contrarreembolso (*contrassegno*), recogida en sucursal (*fermo deposito*), recogidas de cambio/devolución y seguro adicional opcional.
- **Red de sucursales:** más de 20 hubs y delegaciones operativas en Italia, incluidos el hub central Casandrino/Nápoles (`NA1`), Roma (`RM1`, `RM3`), Milán (`MI1`), Bolonia (`BO1`), Turín (`TO2`), Florencia (`FI1`), Verona (`VR1`), Bari (`BA1`), Salerno (`SA1`), Avellino (`AV1`), Frosinone (`FR1`), Latina (`LT1`), Módena (`MO1`), Parma (`PR1`), Foggia (`FG1`), Lecce (`LE2`), Matera (`MT1`), Potenza (`PZ1`), Cosenza (`CS1`), Catanzaro (`CZ1`) y Regio de Calabria (`RC1`). Cuando otro mensajero asume un paquete, el localizador de Liccardi puede mostrar la carta de porte (LDV) de ese mensajero.

## Entrega fallida, retenciones *giacenza* y reclamaciones

- **`IN GIACENZA` (`35`) o entrega fallida (`28`, `40`, `45`):** tras un intento sin éxito o problema de dirección, Liccardi coloca el envío en *giacenza* (retención en almacén) y espera instrucciones de liberación (*svincolo giacenza*) del **remitente**. Contacta primero con tu tienda en línea para que emita la liberación, o usa el portal de destinatarios en [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) para actualizar instrucciones de entrega, comunicar un error de dirección o solicitar recogida en sucursal (*ritiro in filiale*).
- **Impugnar un resultado (`Contestazione Esito`) o paquete desaparecido:** la guía de reclamaciones de Liccardi te permite denunciar un estado incorrecto, un escaneo de destinatario ausente estando en casa o un paquete marcado como entregado que no recibiste, y facilita los formularios descargables *Modulo reclamo*, *Modulo conciliazione* y *Formulario CP*. Avisa también siempre a tu vendedor. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel), [devuelto al remitente](guide:returned-to-sender) y [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

## Atención al cliente y contactos de Liccardi Express Courier

Datos oficiales publicados en [Liccardi Contatti](https://www.liccarditrasporti.com/contatti/) y [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/):

- **Número gratuito (*Numero Verde* en Italia):** `800 029 764` (`800029764`).
- **Teléfono:** `+39 081 5053642` (fax `081 5053071`).
- **Correo de atención al cliente:** `assistenzaclienti@liccarditrasporti.com` (consultas generales: `info@liccarditrasporti.com`; PEC: `flliliccardi@legalmail.it`).
- **Portal de soporte y reclamaciones para destinatarios:** [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).
- **Sede y hub central:** *F.lli Liccardi S.r.l.* (P.IVA `06588280633`), Via Paolo Borsellino 133 (sede social 123), 80025 Casandrino (NA), Italia.

> Desconfía de SMS falsos que piden un pequeño pago en línea para reprogramar una entrega de mensajería en Italia. Comprueba siempre cualquier aviso directamente con tu tienda o en la web oficial y canales de atención de Liccardi. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Liccardi Express Courier con Peek

Cuando introduces un código de barras `660` de 13 dígitos (`6604829173605`, inventado), [Peek](/) pregunta primero a Liccardi Express Courier desde cualquier país salvo Rusia, donde consulta primero a Ozon, y muestra «Liccardi Express Courier tiene este paquete.» en cuanto el localizador de Liccardi lo confirma. Peek reconoce también como Liccardi un enlace pegado `my.liccarditrasporti.com/segui?ldv=`. Un LDV de 7 u 8 dígitos (`48291736`, inventado) no señala a ningún transportista por sí solo: pega el enlace o toca «Cambiar» al añadir el paquete o «Cambiar transportista» en su pantalla y elige **Liccardi Express Courier** (buscar «Liccardi Trasporti», «Liccardi Express» o «F.lli Liccardi» también lo encuentra).

Peek lee el localizador de Liccardi y muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En reparto», «Listo para recoger», «Intento de entrega», «Incidencia», «Devuelto al remitente», «Entregado»), el texto italiano de Liccardi y la sucursal. Los códigos `codEsito` determinan la fase pero ninguna app de Peek los muestra. Peek fecha los escaneos en hora italiana y los muestra en la hora de tu dispositivo. También muestra el día estimado de entrega cuando Liccardi lo facilita (sin la franja horaria), la hora de entrega una vez entregado y el estado resumen antes del primer escaneo del bulto. Cuando Liccardi proporciona la carta de porte de otro mensajero y Peek lo reconoce, Peek comprueba que tiene el paquete: la pantalla muestra entonces «Entrega con» seguido del nombre del transportista y ambos números. En envíos con varios bultos, Peek fusiona los escaneos repetidos de sucursal producidos en menos de un minuto y mantiene el envío abierto hasta que cada bulto se entrega. Peek no muestra la localidad ni el código postal del destinatario, ni los nombres de los repartidores ni las firmas de entrega.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que Liccardi muestra `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`) o `IN CONSEGNA` (`52`), cada 2 minutos desde las 6:00 hora italiana mientras el localizador propio de Liccardi responda. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de Liccardi no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Liccardi Express Courier.», con un enlace «Pide ayuda a Liccardi Express Courier» a la página de asistencia y reclamaciones de Liccardi. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Liccardi Express Courier.»

Puedes elegir ajustes de alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede liberar una *giacenza*, cambiar una dirección de entrega ni contactar con Liccardi Express Courier por ti.

## Preguntas sobre el seguimiento de Liccardi Express Courier

### ¿Qué aspecto tiene un número de seguimiento de Liccardi?

Liccardi usa un código de barras de etiqueta de 13 dígitos que empieza por `66` (normalmente `660`, por ejemplo `6604829173605`, inventado) o un número de envío de 7 u 8 dígitos (*LDV*, por ejemplo `48291736`, inventado).

### ¿Qué significa `IN GIACENZA` en el seguimiento de Liccardi?

`IN GIACENZA` (código `35`) indica que el paquete está retenido en una sucursal de Liccardi tras una entrega fallida o incidencia de dirección y requiere instrucciones de liberación (*svincolo*) del remitente. Contacta con tu vendedor y envía los datos de entrega actualizados a través de [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

### ¿Cuánto tarda Liccardi Express Courier en entregar en Italia?

Liccardi anuncia entregas nacionales en **24–48 horas** tras la recogida (estadísticamente en **72 horas** en toda su red italiana), con posible tiempo adicional para zonas remotas (*località disagiate*) y picos estacionales.

### ¿Cómo contacto con la atención al cliente de Liccardi Express Courier?

Llama al número gratuito en Italia `800 029 764` o al `+39 081 5053642`, escribe a `assistenzaclienti@liccarditrasporti.com` o envía una consulta o reclamación de destinatario en [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

:::sources
- [Liccardi Express Courier: sitio oficial](https://www.liccarditrasporti.com/) – *F.lli Liccardi S.r.l.*, 20+ sucursales, más de 5,9 millones de envíos anuales, sede en Casandrino (NA) y contactos
- [Liccardi Express Courier: Azienda](https://www.liccarditrasporti.com/azienda/) – historia desde 1992 (origen en los años sesenta), expansión B2C desde 2004 y pertenencia a Unione Industriali
- [Liccardi Express Courier: Servizi](https://www.liccarditrasporti.com/servizi/) – entrega 24/48 h (hasta 72 h), recogida en 48 h, *contrassegno*, *fermo deposito* y clasificación fotográfica automatizada
- [Liccardi Express Courier: Filiali](https://www.liccarditrasporti.com/filiali/) – lista completa de los 20+ hubs y sucursales italianas (NA1, RM1, MI1, BO1, TO2, FI1, VR1, BA1, etc.)
- [Liccardi Express Courier: Contatti](https://www.liccarditrasporti.com/contatti/) – `800 029 764`, `+39 081 5053642`, correos, PEC y dirección en Casandrino
- [Liccardi Express Courier: Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) – categorías de soporte a destinatarios, impugnaciones y formularios de reclamación/conciliación (URL de ayuda enlazada por Peek)
- [Universal Parcel Scraper: liccardi](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/liccardi) – reglas `660`/LDV, regla de enlace `my.liccarditrasporti.com`, mapa completo `codEsito`, gestión multibulto, día estimado de entrega y extracción de LDV de socio
:::
