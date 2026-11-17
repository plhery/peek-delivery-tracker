---
title: Seguimiento Poste Italiane: códigos 1UW y 3UW y estados
description: Rastrear un paquete de Poste Italiane en España: cómo es el código de envío (1UW, 3UW, 8UW, 5P), qué significan los estados italianos, plazos y contacto.
slug: seguimiento-poste-italiane
published: 2026-11-16
updated: 2026-11-16
---

Poste Italiane es el operador postal público de Italia y reparte también, mediante sus redes Crono y SDA, compras en tiendas online italianas, Vinted o eBay. Puedes seguir tu envío en «Cerca Spedizioni» dentro de poste.it (antiguo Dove Quando), en la app Poste Italiane o pegando el código arriba. En envíos de Italia a España, poste.it muestra los pasos hasta el centro de cambio internacional; en códigos postales terminados en `IT`, el reparto pasa después al operador español.

## Código de seguimiento de Poste Italiane: dónde está y cómo es

Poste lo llama *codice spedizione*. En el resguardo o carta de porte (*lettera di vettura*) aparece bajo el código de barras: escríbelo sin espacios ni guiones. En compras online figura en el correo de la tienda (o en el aviso Scegli Tu de Poste); si estás en Italia, el aviso de llegada (*avviso di giacenza*) indica el número del certificado, la oficina postal y si admite Ritiro Digitale. El «Codice Prenotazione Ritiro» (`CP` más cifras) solo sirve al remitente para una recogida a domicilio, no para el rastreo. ¿No tienes código? Mira [dónde encontrar el número de seguimiento](guide:find-tracking-number).

Todos los ejemplos siguientes son inventados:

| Código (inventado) | Qué identifica |
| --- | --- |
| `RA00000000000` | `RA` y 11 cifras: envío y correo certificado. |
| `3UW0000000000` | `1UW`, `3UW` u `8UW` y 10 letras/cifras: Poste Delivery Business. |
| `5PA0000000000` | `5P` y 11 letras/cifras: paquetería Crono. |
| `JF00000000000` | `EF`, `EG`, `GF`, `JF`, `JG` o `LF` y 11 cifras: Poste Delivery Web y MyPoste. |
| `JJITA0000000000000` | `JJITA` y 13 letras/cifras: matrícula italiana ISO. |
| `2IMA0000000000` | `2IMA` y 10 cifras (o `2IVC`, 4 cifras, letra/cifra, 5 cifras): etiqueta Posta1. |
| `000000A000000` | Cifras y letras intercaladas (6 cifras, letra, 6 cifras, o inicio `1C`, `3C`, `9C`, `F`, `D`): estilo SDA y Crono. |
| `050000201242` | 12 cifras con control válido: ejemplo de poste.it para certificados, asegurados, actos judiciales y paquetes. |
| `RR000000005IT` | Dos letras, nueve cifras e `IT` (`RR…IT`, `CW…IT`, `ZC…IT`): envío internacional desde Italia. |

Algunos formatos se comparten: `5P` con once cifras que cumplen el control de Colissimo puede ser de [La Poste](carrier:la-poste); las 12 cifras existen en BRT o [Sending](carrier:sending), y `000A00000000B`, `9C0000I000000` o `9C0000J000000` en [AliExpress / Cainiao](carrier:cainiao). Consulta [formatos de números de seguimiento](guide:tracking-number-formats).

**Qué tiene seguimiento:** certificados, asegurados, notificaciones judiciales y paquetes (desde el día laborable siguiente en Poste Delivery Express, antiguo Paccocelere, y Standard, antiguo *pacco ordinario*). Poste Delivery International Express ofrece rastreo hasta destino (salvo Túnez, Groenlandia, islas Feroe y ciertos códigos postales suizos); International Standard y Postapriority Internazionale terminan en el centro de cambio italiano en poste.it. Posta Ordinaria (antes Posta4) y Postamail Internazionale no ofrecen seguimiento; Posta1 cesó el 1 de mayo de 2026. Consulta disponible 2 meses en paquetes y 24 meses en correo.

## Estados de Poste Italiane en italiano

Cada lectura muestra una frase en italiano de la *Legenda degli stati di spedizione*, que Peek reproduce tal cual:

| Mensaje de Poste Italiane | Significado |
| --- | --- |
| `Presa in carico` | Envío admitido y encaminado hacia destino. |
| `In attesa di sdoganamento` | En revisión aduanera; pueden pedirte documentación. |
| `completata la fase di verifica per lo svincolo…` | Control aduanero terminado, envío liberado. |
| `In transito` | En ruta entre centros operativos de Poste Italiane. |
| `In transito presso il Centro di lavorazione Internazionale` | Ha salido de Italia hacia un operador extranjero (último evento en Postapriority Internazionale e International Standard). |
| `presso il Centro di lavorazione Internazionale` | Ese mismo paso en un certificado, en línea aparte tras `In transito`. |
| `In consegna` | En reparto. |
| `consegna non andata a buon fine…` | Intento fallido, p. ej. por dirección errónea o incompleta. |
| `disponibile per il ritiro dal giorno lavorativo successivo alla data indicata` | Listo en la oficina postal desde el día laborable siguiente a la fecha indicada. |
| `disponibile per il ritiro entro i termini indicati su SMS/email` | Listo en un Punto Poste o taquilla (*locker*) dentro del plazo del SMS/email. |
| `la spedizione è stata consegnata` | Entregado. |
| `sono in corso delle verifiche sulla spedizione. contatta assistenza` | Incidencia en revisión: contacta con asistencia. |
| `in restituzione al mittente…` | De vuelta al remitente. |
| `Resa al mittente` | No entregado (plazo vencido, dirección incorrecta o rechazo) y devuelto. |
| `Tracciatura non disponibile` | Recién admitido o caducado (más de 2 meses en paquetes o 24 en correo). |

`In consegna` puede seguir visible tras la entrega o cuando ya espera en oficina o Punto Poste, y tras un fallo el envío vuelve a `In transito`. Toda fecha prevista es «meramente orientativa» (*meramente indicativa*). Más en [estados de seguimiento](guide:tracking-statuses).

## Entrega en España y recogida en Italia

**Entre Italia y España.** Un envío postal `…IT` pasa al operador español (como [Correos](carrier:correos-spain)) tras salir del centro internacional italiano; a la inversa, un envío hacia Italia solo aparece en poste.it al entrar en la red italiana. Dentro de la unión aduanera de la UE no hay aranceles entre Italia y España.

**En Italia (*giacenza* y Punto Poste).** Poste hace 2 intentos a pie de calle (*piano strada*): deja aviso tras el primero, repite al día laborable siguiente en Crono y Poste Delivery Business y, si vuelve a fallar, guarda gratis el paquete en *giacenza* en la oficina postal; mira [paquete no recogido](guide:uncollected-parcel):

- **Plazos y autorización:** de 3 días laborables en locker a 10 en oficina postal (Poste Delivery Web: 10 oficina, 7 Punto Poste, 3 locker; Crono: 10 en Punto Poste; certificados: 30 días, Raccomandata 1: 15 días). Cita previa con «Prenota» o en el 06 4526 4526; un tercero retira en oficina con delegación específica, su documento y copia del tuyo, y en Punto Poste/locker con el PIN.
- **Scegli Tu y PostePlus:** con Scegli Tu puedes cambiar una vez día, dirección en Italia, oficina/Punto Poste, vecino, lugar seguro o vacaciones; PostePlus es para residentes en Italia.
- **Aduanas extracomunitarias en Italia:** se abonan al cartero, en oficina o más barato en www.poste.it pulsando «Paga» (despacho Poste comercial: 3 € hasta 22 €, 7 € hasta 150 €, 21 € encima; online: 1 € hasta 150 €, 18 € encima). Según la Comisión Europea, desde el 1 de julio de 2026 se suma un arancel temporal de 3 € por artículo hasta 150 € y, desde el 1 de noviembre de 2026, la tasa de gestión de la Unión de 2 € por artículo en compras online. Poste advierte además de ralentizaciones importantes (*significativi rallentamenti*) por incidencias técnicas en el despacho extracomunitario, sin plazo estimado de normalización; ver [paquete en aduanas](guide:customs).

## Paquete parado, perdido o dañado

En compras online acude primero a la tienda (en la UE debe entregar en 30 días y responde hasta la entrega); ver [seguimiento sin cambios](guide:tracking-not-updating), [paquete perdido](guide:lost-parcel) y [entregado pero no recibido](guide:delivered-not-received). Ante Poste pueden reclamar remitente o destinatario, pero la indemnización va al remitente salvo renuncia formal (si salió del extranjero, a través del correo de origen). Haz constar daños visibles al recibir (*consegna cautelare* o rechazo); ver [paquete dañado](guide:damaged-parcel).

**Plazos:** 20 días naturales desde la recogida en Crono/Business; del 6.º día laborable a 3 meses en Standard. Vías: [Reclami](https://www.poste.it/assistenza/reclami), PEC `reclamiretail@postecert.it`, certificado a Casella Postale 160, 00144 Roma u oficina postal (respuesta en 45 días naturales, luego conciliación y AGCOM).

## Teléfono y contacto de Poste Italiane

Consultado en poste.it el 16 de noviembre de 2026:

- **Desde España, extranjero o móvil:** `+39 06 4526 3160`, lun.–sáb. salvo festivos italianos, de 8:00 a 20:00, según tarifa de tu operador.
- **Fijo en Italia:** `803 160` (gratuito, mismo horario). Solo informan al titular registrado.
- **Escrito y chat:** `assistenza.spedizioni@posteitaliane.it`, [Scrivici](https://www.poste.it/assistenza/scrivici), [Contact us](https://www.poste.it/assistenza/scrivici-en) y *Assistente Digitale* 24/7.

> Hay SMS falsos que entran por *spoofing* en el hilo real de Poste Italiane. No abras enlaces: escribe `www.poste.it`. Poste nunca pide tarjeta, OTP ni claves; reenvía sospechosos a `antiphishing@posteitaliane.it` y revisa [SMS falsos de entregas](guide:delivery-scam-texts).

## Seguir un paquete de Poste Italiane con Peek

[Peek](/) reconoce al instante a Poste Italiane en `RA`, `1UW`, `3UW`, `8UW`, `JJITA`, `2IMA`, `2IVC`, `EF`, `EG`, `GF`, `JF`, `JG`, `LF` y `ZC…IT`, en códigos postales válidos acabados en `IT` que empiezan por `C`, `E`, `L` o `R`, y en `5P` (salvo once dígitos tras `5P` válidos en Colissimo: entonces consulta a [La Poste](carrier:la-poste) y a Poste Italiane, Poste primero desde Italia). Otros postales válidos en `IT` muestran «Operador postal desconocido» (Poste y luego [Chronopost](carrier:chronopost)); `X…IT` sin control postal (fuera de Chronopost) y referencias SDA/Crono van solo a Poste (`000A00000000B`, `9C0000I000000`, `9C0000J000000` también a [AliExpress / Cainiao](carrier:cainiao)). En 12 cifras válidas comprueba varios operadores como [Sending](carrier:sending), Poste y BRT («Comprobando servicios de seguimiento…»), o elígela con «Cambiar» / «Cambiar transportista». Admite enlaces `poste.it` (`#/risultati-spedizioni/`) y `sda.it` (`tracing.letteraVettura=`) sin código postal.

Desde España, Peek comprueba además los números postales válidos acabados en `IT` (`RR…IT`, `CW…IT`, salvo `ZC…IT`) en [Correos](carrier:correos-spain) y cambia en cuanto publica escaneos más recientes; a la inversa, etiquetas de 16 caracteres de [Vinted Go](carrier:vinted-go) o [Cainiao](carrier:cainiao) con un código `1UW`, `3UW`, `8UW` o `5P` pasan automáticamente a Poste Italiane.

Consulta cada hora y avisos en «Cada escaneo», «Pasos importantes» o «Solo la entrega». Muestra hasta 20 escaneos en el mapa, fecha prevista, servicio, «Gastos de importación» y horas locales («Horas en tu zona horaria: …» en peektracker.com y iPhone). Nombre, dirección y firma solo los ve el dueño del envío (ocultos al compartir); remitente, peso, medidas y oficina no se guardan. Tras dos días sin datos: «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.»

## Preguntas frecuentes sobre el seguimiento de Poste Italiane

### Código 1UW, 3UW u 8UW: ¿qué transportista es?

Poste Italiane: `1UW`, `3UW` y `8UW` seguidos de diez caracteres son códigos de paquetería Poste Delivery Business, rastreables en «Cerca Spedizioni» o arriba en esta página.

### ¿Cuánto tarda en llegar un paquete de Poste Italiane?

De forma orientativa en Italia: 4 días laborables en Crono, 1–2 en Crono Express y 1–4 tras la admisión en Poste Delivery Express (sin sábados, opción de pago solo en Business Express). Internacional: 2 días a la UE y 2–4 fuera de la UE en International Express; 10–15 días a la UE y 10–25 fuera de la UE en International Standard (sin sábados ni festivos).

### ¿Cuántos días guardan un paquete en Punto Poste o en la oficina postal?

En Poste Delivery Web: 7 días laborables en Punto Poste, 3 en locker y 10 en oficina postal; en Crono: 10 días laborables en Punto Poste; certificados: 30 días en oficina (15 en Raccomandata 1). Manda la fecha del aviso o SMS.

### ¿Cómo llamar a Poste Italiane desde España?

Al `+39 06 4526 3160` de lunes a sábado de 8:00 a 20:00 (salvo festivos italianos, coste según tu operador); el `803 160` solo funciona desde fijos italianos.

:::sources
- [Poste Italiane: FAQ Spedizioni](https://www.poste.it/assistenza/faq-spedizioni-e-consegne/) – código, aviso, autorización, certificados
- [Poste Italiane: FAQ Delivery Web](https://www.poste.it/assistenza/faq-poste-delivery-web) – 2 intentos, plazos (10/7/3), PIN, CP
- [Poste Italiane: Legenda stati](https://www.poste.it/cerca/views/risultati-spedizioni.html) – estados, 12 cifras, 2 y 24 meses
- [TG Poste: Tracking Poste](https://tgposte.poste.it/2022/03/09/tracking-poste-come-seguire-lo-stato-delle-spedizioni-di-poste-italiane/) – Dove Quando y centros
- [Poste Italiane: FAQ Scegli Tu](https://www.poste.it/assistenza/faq-scegli-tu) – cambio único
- [Poste Italiane: FAQ PostePlus](https://www.poste.it/assistenza/faq-posteplus) – residentes en Italia
- [Poste Italiane: FAQ Punto Poste](https://www.poste.it/assistenza/faq-rete-punto-poste) – comercios y lockers
- [Poste Italiane: Chiamaci](https://www.poste.it/assistenza/chiamaci/) – teléfonos, horario y titular
- [Poste Italiane: Scrivici](https://www.poste.it/assistenza/scrivici) – email y 06 4526 4526
- [Poste Italiane: Contact us](https://www.poste.it/assistenza/scrivici-en) – contacto en inglés
- [Poste Italiane: Reclami](https://www.poste.it/assistenza/reclami) – reclamaciones y PEC
- [Poste Italiane: Raccomandata](https://www.poste.it/posta-raccomandata/raccomandata) – código en el aviso
- [Poste Italiane: Ritiro Digitale](https://www.poste.it/ritiro-digitale) – recogida digital
- [Poste Italiane: Difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – spoofing SMS
- [Poste Italiane: Delivery Express](https://www.poste.it/prodotti/poste-delivery-express.html) – 1 a 4 días laborables
- [Poste Italiane: International Express](https://www.poste.it/prodotti/poste-delivery-international-express.html) – plazos y excepciones
- [Poste Italiane: International Standard](https://www.poste.it/prodotti/poste-delivery-international-standard.html) – plazos
- [Poste Italiane: CGC Express](https://www.media.poste.it/d9e41964-551f-4f58-9887-6608322cdf87/web/poste-delivery-express-cgc) – antiguo Paccocelere
- [Poste Italiane: CGC Standard](https://www.media.poste.it/e12354a8-ea52-44ce-a996-e83c184000ca/web/poste-delivery-standard-cgc) – antiguo pacco ordinario
- [Poste Italiane: Carta non universali](https://www.media.poste.it/37381c9f-a87e-4292-8b89-00e88fa53323/web/carta-servizi-non-universali) – Crono, plazos, sábado
- [Poste Italiane: Carta universali](https://www.media.poste.it/c919f7cf-8236-428e-82fc-f8b2be8428b8/web/carta-servizi-universali) – Standard y Posta4
- [Poste Italiane: Posta Ordinaria](https://www.poste.it/lettera/posta-ordinaria) – sin seguimiento
- [Poste Italiane: Servizio Universale](https://www.poste.it/variazione-servizio-postale-universale) – fin de Posta1
- [Poste Italiane: Spedizioni da estero](https://www.poste.it/ricevere-spedizioni-da-estero) – despacho y tasa 2 €
- [Poste Italiane: Aggiornamenti operativi](https://www.poste.it/aggiornamenti-operativi) – retrasos aduaneros
- [Comisión Europea: Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel 3 € y tasa 2 €
- [UE: Unión aduanera](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_es) – mercado interior
- [Your Europe: Envíos y entregas](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm) – entrega en 30 días
- [Universal Parcel Scraper: Poste Italiane](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/poste-italiane) – reglas de Peek
:::
