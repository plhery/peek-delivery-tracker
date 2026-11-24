---
title: Seguimiento SkyPostal: códigos SKAMZUS, SP y soporte
description: Sigue un paquete SkyPostal de EE. UU. a Latinoamérica: códigos SKAMZUS y SP...BR, centro de Miami, aduana (CEN, HTI, DEL) y correos por país.
slug: seguimiento-skypostal
published: 2026-11-24
updated: 2026-11-24
---

**SkyPostal** es una red de entrega de paquetes nacionales en Estados Unidos y transfronterizos hacia Latinoamérica con sede en Miami, fundada por Albert P. y A.J. Hernandez ([skypostal.com](https://www.skypostal.com/)). Desde su principal centro de exportación estadounidense en Miami, Florida, SkyPostal transporta envíos de comercio electrónico para grandes tiendas y mercados online — como Amazon, Temu y eBay — hacia Brasil, México, Colombia, Perú, Ecuador, Chile y Costa Rica, gestionando la aduana de exportación e importación y confiando la entrega final a socios regionales latinoamericanos. Puedes consultar un envío en [tracking.skypostal.com](https://tracking.skypostal.com/) o pegar tu código o enlace de SkyPostal en el cuadro de arriba.

## Números de seguimiento SkyPostal (formatos `SKAMZUS` y `SP...BR`)

SkyPostal emite varios formatos transfronterizos según el vendedor y el país de destino; los más habituales son los códigos `SKAMZUS` de 17 caracteres (muy usados en exportaciones de Amazon EE. UU. a Latinoamérica) y los códigos `SP...BR` de 15 caracteres para Brasil:

| Formato (inventado) | Qué es |
| --- | --- |
| `SKAMZUS0482917365` | Código SkyPostal de 17 caracteres para exportaciones de Amazon EE. UU. (`SKAMZUS` + 10 dígitos). |
| `SP04829173605BR` | Código SkyPostal transfronterizo de 15 caracteres para Brasil (`SP` + 11 dígitos + `BR`). |

Si se selecciona manualmente, el localizador de SkyPostal también busca otras guías de vendedores y referencias de socios de 8 a 40 caracteres alfanuméricos con al menos un dígito. Consulta la guía de [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de SkyPostal

SkyPostal asigna un código de evento de 3 letras a cada hito — desde la predeclaración electrónica y la entrada en Miami hasta vuelos, aduana en destino y escaneos reenviados por el transportista local:

| Código y texto de SkyPostal | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `DRC` (`DATA RECEIVED`), `HCV` (`HS_CODE VALIDATION`), `TIC` (`TAX ID COLLECTED`), `DCO` (`DOCUMENTATION COMPLETE`) | Datos del pedido, validación arancelaria HS e identificación fiscal del comprador recibidos antes de llegar al centro de SkyPostal. | «Etiqueta creada» |
| `RUT` (`RECEPTACLE RECEIVED IN US TRANSPORT FACILITY`), `REC` (`RECEIVED`) | Paquete físico recibido en las instalaciones de exportación de SkyPostal en Miami. | «Admitido por el transportista» |
| `XRS` (`X-RAY SCAN`), `PLP` (`PROVIDER LABEL PRINTED`), `INC` (`NESTED TO RECEPTACLE`), `PRO`, `MNF`, `FDP` (`FLIGHT DEPARTED`), `ATT`, `ATC` (`ARRIVED IN COUNTRY`), `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) | Procesado en Miami, enviado en avión y transferido de la aerolínea al operador terrestre del país de destino (no es entrega en domicilio). | «En tránsito» |
| `CEN` (`CUSTOMS ENTRY`), `CHE` (`CUSTOMS HOLD AT CONTROL ENTITY`) | Ingresado en aduana en destino o a la espera de liberación en el almacén fiscal del aeropuerto. | «En aduanas» |
| `CCH` (`CUSTOMS CLEARED`), `TTC` (`TENDERED TO LOCAL CARRIER`), `TDP`, `PES` (`PROVIDER ENTRY SCAN`), `INT`, `PRH`, `PDH`, `SLH` (`HOLD RESOLVED`) | Despachado en aduana, entregado al mensajero local latinoamericano y en circulación por centros locales. | «En tránsito» |
| `OFD` (`OUT FOR DELIVERY`) | En reparto hoy con el transportista colaborador en destino. | «En reparto» |
| `PIK` (`AVAILABLE FOR PICKUP`) | Listo para recogida por el destinatario en un punto local o sucursal postal. | «Listo para recoger» |
| `CNA` (`CONSIGNEE NOT AVAILABLE`) | Intento de entrega fallido porque no había nadie disponible para recibirlo. | «Intento de entrega» |
| `HTI` (`HOLD FOR PROBLEMATIC TAX ID`), `ICA` (`INCORRECT / INCOMPLETE ADDRESS`), `DRF` (`DELIVERY REFUSED`), `PLR` (`PROVIDER LABEL REJECTED`), `INH` (`ON HOLD`) | Retenido por problema con el documento fiscal, dirección errónea o incompleta, etiqueta rechazada o rechazo de entrega. | «Incidencia» |
| `DEL` (`DELIVERED`) | Entregado al destinatario por el socio en destino. | «Entregado» |

Ten en cuenta que `DTP` (*Delivered from airline to operator*) solo indica que la aerolínea entregó la saca aérea al operador terrestre del aeropuerto de destino — Peek mantiene `DTP` en «En tránsito» en lugar de marcarlo entregado. Los eventos internos de facturación (`CUT`, `PAY`, `FSC`) no cambian la etapa.

## Aduanas en Latinoamérica, identificación fiscal (`HTI`) y entrega local

Los envíos transfronterizos desde Estados Unidos hacia Latinoamérica con SkyPostal requieren cumplir estrictamente los datos aduaneros y fiscales:

- **Identificación fiscal del comprador (`TIC` / `HTI`):** los escaneos de SkyPostal registran cuándo se recopila el documento fiscal (`TAX ID COLLECTED`) y cuándo un paquete queda retenido por ese motivo (`HOLD FOR PROBLEMATIC TAX ID`). Si aparece `HTI`, consulta al vendedor o escribe al correo de soporte de SkyPostal de tu país con el código completo. Consulta la guía de [paquetes retenidos en aduanas](guide:customs).
- **Entrega local de última milla:** una vez que aparecen `CCH` (*Customs cleared*) y `TTC` (*Tendered to local carrier*), un mensajero local se encarga de la entrega y el rastreador de SkyPostal muestra sus escaneos con la ciudad del socio.

## Paquete de SkyPostal retrasado, retenido o perdido

- **Pausas sin novedades entre Miami y la aduana en destino:** el seguimiento suele detenerse entre `FDP` (`FLIGHT DEPARTED`), `ATC` (`ARRIVED IN COUNTRY`) y `CCH` (`CUSTOMS CLEARED`) mientras los envíos consolidados pasan inspección aduanera. Consulta [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Errores de dirección (`ICA`) o entregas rechazadas (`DRF`):** contacta primero con tu plataforma de compra (Amazon, Temu, eBay, etc.) para reenvío o reembolso y escribe al correo de SkyPostal de tu país con el número completo si sigue retenido localmente. Consulta también [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente de SkyPostal y correos por país

En [SkyPostal Customer Service](https://www.skypostal.com/customer-service), SkyPostal pide a los destinatarios finales que contacten **por correo electrónico** indicando siempre el número de seguimiento completo y los detalles de la consulta (plazo de respuesta publicado: **3–5 días laborables**):

- **Brasil:** `Brazilcs@skypostal.com`
- **México:** `Mexicocs@skypostal.com`
- **Colombia:** `Colombiacs@skypostal.com`
- **Perú:** `Perucs@skypostal.com`
- **Ecuador:** `Ecuadorcs@skypostal.com`
- **Resto de países:** `customerservice@skypostal.com`
- **Sede en Estados Unidos:** *SkyPostal*, 2990 NW 75th Ave, Miami, FL 33122, Estados Unidos ([skypostal.com/about-us](https://www.skypostal.com/about-us)).

> Desconfía de mensajes falsos de WhatsApp o SMS que exijan pagar con urgencia un impuesto en una web desconocida para liberar una importación a Latinoamérica. Verifica cualquier tasa aduanera real directamente en tu página de pedido o en el portal oficial de aduanas o correos. Consulta [mensajes SMS falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de SkyPostal con Peek

[Peek](/) detecta SkyPostal automáticamente como único candidato cuando introduces un código `SKAMZUS` (`SKAMZUS0482917365`, inventado) o un código `SP...BR` (`SP04829173605BR`, inventado) y muestra «SkyPostal tiene este paquete.» cuando su localizador devuelve historial; Peek también reconoce enlaces pegados `tracking.skypostal.com/?trck_number=` y `tracking.skypostal.com/single-tracking.aspx?trck_number=`. Para otras referencias de tiendas, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete y selecciona **SkyPostal** (buscar «Sky Postal» también funciona).

Peek consulta el localizador de SkyPostal y muestra la etapa («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En aduanas», «En reparto», «Listo para recoger», «Intento de entrega», «Incidencia», «Entregado»), el texto en inglés del escaneo y la ciudad del socio o, en su defecto, el código de aeropuerto de SkyPostal. Los códigos de 3 letras fijan la etapa y ninguna aplicación de Peek los muestra. SkyPostal indica las horas sin desfase respecto a UTC: Peek fecha cada escaneo según la zona horaria del lugar (hora de Nueva York para `MIA`, de São Paulo para `GRU`, de Bogotá o Lima para escaneos allí), un escaneo sin lugar según el escaneo vecino, y muestra todas las horas en la hora de tu dispositivo. Los escaneos de socios pueden llegar duplicados con horas de diferencia, de modo que una etapa anterior publicada tras una entrega no la anula. Peek no guarda el comprobante de entrega, comentarios ni el código propio del socio, y ninguna aplicación de Peek muestra quién firmó. El rastreador de SkyPostal no facilita fecha estimada de entrega, peso ni hora de entrega aparte.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que SkyPostal muestra `OFD` (`OUT FOR DELIVERY`), cada 2 minutos desde las 6:00 según el reloj del escaneo más reciente mientras responda el localizador de SkyPostal. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de SkyPostal no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a SkyPostal.», junto con un enlace «Pide ayuda a SkyPostal» hacia su página de atención al cliente. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a SkyPostal.»

Puedes elegir alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa el seguimiento y no puede enviar identificaciones fiscales, despachar aduanas en Latinoamérica ni contactar con SkyPostal por ti.

## Preguntas sobre el seguimiento de SkyPostal

### ¿Qué aspecto tiene un número de seguimiento de SkyPostal?

Los códigos más frecuentes son `SKAMZUS` de 17 caracteres (`SKAMZUS0482917365`, inventado) para exportaciones de Amazon EE. UU. y `SP...BR` de 15 caracteres (`SP04829173605BR`, inventado) para Brasil, aunque SkyPostal también admite otras referencias de vendedores de 8 a 40 caracteres.

### ¿Qué significa `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) en SkyPostal?

No significa que el paquete se haya entregado en tu casa. `DTP` indica que la aerolínea entregó el envío consolidado al operador terrestre del aeropuerto de destino antes del despacho aduanero (`CEN`) y del traspaso al mensajero local (`TTC`).

### ¿Qué significa `HTI` (`HOLD FOR PROBLEMATIC TAX ID`)?

La aduana de destino detectó una identificación fiscal ausente o incorrecta del destinatario (como el CPF en Brasil o el RFC en México). Contacta con tu vendedor y escribe al correo de soporte de SkyPostal de tu país en [skypostal.com/customer-service](https://www.skypostal.com/customer-service).

### ¿Cómo contacto con el servicio de atención al cliente de SkyPostal?

Escribe al equipo de tu país (`Brazilcs@`, `Mexicocs@`, `Colombiacs@`, `Perucs@`, `Ecuadorcs@` o `customerservice@skypostal.com`) con tu número de seguimiento; SkyPostal indica un plazo de respuesta de 3–5 días laborables.

:::sources
- [SkyPostal: Official Website](https://www.skypostal.com/) – envíos nacionales en EE. UU. y transfronterizos a Latinoamérica, sede en Miami FL
- [SkyPostal: About Us](https://www.skypostal.com/about-us) – historia de la empresa y red privada de entregas en Latinoamérica
- [SkyPostal: Customer Service](https://www.skypostal.com/customer-service) – correos de soporte por país (Brasil, México, Colombia, Perú, Ecuador y general), referencia a Amazon/Temu/eBay y respuesta en 3–5 días laborables (URL de ayuda enlazada por Peek)
- [SkyPostal: Tracking Portal](https://tracking.skypostal.com/) – localizador público de envíos individuales
- [Universal Parcel Scraper: skypostal](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/skypostal) – reglas de detección `SKAMZUS` y `SP...BR`, reglas de enlace `tracking.skypostal.com`, mapa de códigos de 3 letras (`DRC`–`DEL`, `DTP`, `HTI`) y relojes de escaneo
:::
