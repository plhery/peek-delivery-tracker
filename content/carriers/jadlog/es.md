---
title: Seguimiento Jadlog: 14 dígitos, Pickup, Predict y estados
description: Rastrea un paquete Jadlog en Brasil: códigos de 14 dígitos, estados en portugués y Geopost, puntos Pickup, SMS Predict de 1 hora y soporte.
slug: seguimiento-jadlog
published: 2026-11-24
updated: 2026-11-24
---

Jadlog es uno de los mayores operadores brasileños de paquetería exprés y logística para comercio electrónico, además de la filial brasileña del grupo europeo Geopost (junto a DPD, Chronopost, SEUR, BRT y Speedy). En Brasil cuenta con más de 500 franquicias que cubren unos 5.000 municipios, más de 7.000 vehículos y más de 4.000 comercios asociados Jadlog Pickup. Puedes consultar un envío en el cuadro `RASTREIE SUA ENCOMENDA` en la [portada de Jadlog](https://www.jadlog.com.br/jadlog/home), o pegar el código de 14 dígitos en el cuadro superior.

## Número de seguimiento Jadlog: formato de 14 dígitos y dónde encontrarlo

Busca tu código de envío (*código de rastreio* o *remessa*) en el correo de expedición de la tienda online, en la pantalla de pedido del marketplace o en la etiqueta. Los números de seguimiento de Jadlog tienen 14 dígitos consecutivos:

| Formato (inventado) | Qué es |
| --- | --- |
| `48291736405821` | Número de paquete Jadlog de 14 dígitos. Comparte el formato de 14 dígitos con transportistas europeos de Geopost/DPD como [DPD Alemania](carrier:dpd-de), [Chronopost](carrier:chronopost), [SEUR](carrier:seur) y [BRT](carrier:brt). |

Peek realiza el seguimiento solo con el número de 14 dígitos y nunca pide tu CPF. Si tu código tiene 15 dígitos y empieza por `8880`, pertenece a [J&T Express Brazil](carrier:j-and-t-br); un código `BR` de 15 caracteres pertenece a [SPX Express Brazil](carrier:spx-br); un código `AJ` de 17 caracteres pertenece a [Anjun Logistics](carrier:anjun); y uno de 13 caracteres terminado en `BR` pertenece a [Correios](carrier:correios-br). Consulta [formatos de números de seguimiento](guide:tracking-number-formats) para ver otros formatos.

## Qué significan los estados de seguimiento de Jadlog

Como Jadlog forma parte de Geopost, las líneas de seguimiento aparecen en portugués en la web de Jadlog o en inglés cuando se consultan a través de la API de Geopost.

| Jadlog / Geopost muestra | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Order created`, `COLETA SOLICITADA` | El vendedor creó el envío electrónicamente y solicitó la recogida; Jadlog todavía no ha escaneado el paquete físico. | «Etiqueta creada» |
| `EMISSAO` | Jadlog emitió el documento de transporte y admitió el paquete en su red. | «Admitido por el transportista» |
| `Em transferência` | El paquete viaja entre franquicias de Jadlog, centros de clasificación o rutas troncales por Brasil. | «En tránsito» |
| `Em rota de entrega` | Un repartidor local cargó el paquete y salió a entregarlo en tu dirección. | «En reparto» |
| `ENTREGUE` | Jadlog registró el paquete como entregado. | «Entregado» |

Cuando una tienda imprime etiquetas por lotes antes de que pase la furgoneta de Jadlog, `Order created` o `COLETA SOLICITADA` puede seguir siendo el único evento durante un par de días laborables. Una vez que aparece `Em transferência`, las largas distancias por carretera entre estados brasileños también pueden dejar pausas de varios días hasta el escaneo en la franquicia de destino.

## Servicios de Jadlog: Predict, puntos Pickup y exportación

Jadlog detalla sus servicios principales en [jadlog.com.br/jadlog/servicos](https://www.jadlog.com.br/jadlog/servicos), [Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) y páginas de producto:

- **Jadlog.com y Jadlog Package:** entrega para comercio electrónico (Jadlog.com admite paquetes de hasta 30 kg; Jadlog Package cubre envíos por carretera B2C y B2B).
- **Jadlog Rodo, RodoEconômico, Cargo y DOC:** carga por carretera más pesada hasta 50 kg (Rodo), servicio económico por carretera, carga aérea B2B y transporte de documentos.
- **[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict):** disponible en áreas metropolitanas y zonas de alto volumen; envía un SMS automático con una franja de entrega de 1 hora para saber cuándo esperar al repartidor.
- **[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup):** más de 4.000 comercios asociados en Brasil (cobertura superior al 90 % de la población) para recogida, depósito y devoluciones de comercio electrónico (*Reversa*), con avisos por SMS y correo electrónico.
- **[Jadlog Internacional](https://www.jadlog.com.br/jadlog/internacional):** exportación desde Brasil a 154 países (300 destinos) mediante la red internacional de Geopost.

**Lo que Jadlog no publica de forma general.** La web de Jadlog no fija un único límite universal de intentos de entrega, un plazo garantizado ni un plazo fijo de custodia en puntos Pickup válido para todas las tiendas online. Revisa el SMS o correo recibido para tu paquete Pickup — o la pantalla de pedido de la tienda — para ver las fechas exactas de recogida y reintento.

## Paquete Jadlog bloqueado, retrasado o no recibido

En compras online, el vendedor es el cliente contractual de Jadlog y gestiona reembolsos, correcciones de dirección y reenvíos.

**Parado en `Order created` o `COLETA SOLICITADA`.** El vendedor generó los datos del envío, pero Jadlog aún no ha recogido ni incorporado el paquete a su red. Pregunta a la tienda si ya salió de su almacén.

**Sin cambios en tránsito (`Em transferência`).** Los trayectos por carretera entre estados brasileños, fines de semana y congestiones en centros regionales suelen pausar el seguimiento entre escaneos de franquicias. Consulta [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating).

**Marcado `ENTREGUE` pero no aparece.** Comprueba primero con familiares, vecinos y la *portaria* (portería/recepción) de tu edificio, y revisa si elegiste un punto Jadlog Pickup al comprar. Si nadie lo recibió, contacta enseguida con la tienda para que abra una investigación ante Jadlog. Consulta [entregado pero no recibido](guide:delivered-not-received).

**Dañado, perdido o devuelto.** Conserva el embalaje exterior y haz fotos nítidas del daño y de la etiqueta antes de avisar al vendedor; consulta [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Contactar con Jadlog y opciones de consumo

La página [Atendimento de Jadlog](https://www.jadlog.com.br/jadlog/atendimento) no muestra un teléfono público para destinatarios ni un correo general de atención al cliente. Tus opciones prácticas son:

- **Contactar primero con la tienda online:** los comercios disponen del contrato de envío (`CT-e` / factura) necesario para abrir incidencias, corregir la dirección o autorizar un reembolso.
- **Buscador de franquicias locales:** localiza la sucursal de tu zona en [Jadlog Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) si necesitas datos de un depósito local.
- **Solicitudes de privacidad y datos personales (LGPD):** Jadlog ofrece un portal específico en [jadlog.com.br/jadlog/lgpd](https://www.jadlog.com.br/jadlog/lgpd).
- **Mediación de consumo:** si un conflicto con una tienda o transportista adherido no se resuelve directamente, los consumidores en Brasil con cuenta gov.br plata u oro pueden presentar una reclamación gratuita en [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), donde las empresas participantes se comprometen a responder en hasta 10 días.

> Desconfía de enlaces por SMS, WhatsApp o correo que pidan pagar una pequeña «tasa de liberación» o «tarifa de reentrega» por un paquete nacional de Jadlog. Confirma antes cualquier cargo inesperado directamente con la tienda donde compraste. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Sigue un paquete Jadlog con Peek

Como varios transportistas Geopost/DPD comparten números de 14 dígitos en todo el mundo, [Peek](/) usa tu ubicación para decidir a quién consultar primero. En Brasil, Peek consulta primero a Jadlog entre los candidatos de 14 dígitos (antes que las filiales europeas de DPD, Chronopost, SEUR y BRT) y muestra «Jadlog tiene este paquete.» en cuanto Jadlog lo confirma. Fuera de Brasil, Peek usa Jadlog como alternativa secundaria: consulta primero a los transportistas europeos de Geopost y recurre a Jadlog si ninguno tiene el número. Pegar un enlace `jadlog.com.br/jadlog/captcha?cte=` identifica directamente a Jadlog en cualquier país; si no, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete y elige Jadlog (buscar «DPD Brazil» también lo encuentra).

Peek consulta directamente la API pública de seguimiento de Geopost para Jadlog sin pedir código postal ni CPF. Muestra la fase actual («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En reparto», «Entregado»), el historial resumido de escaneos (en inglés desde la API de Geopost o en portugués cuando llega del rastreador local de Jadlog) y la hora de entrega. No se muestran en Peek la fecha estimada, el peso, la dirección del punto Jadlog Pickup ni el nombre o firma del destinatario.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que el paquete sale a reparto, cada 2 minutos desde las 6:00 hora local, mientras el seguimiento de Jadlog responda. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, comprueba cada hora sea cual sea tu forma de seguirlo, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el seguimiento de Jadlog no responde y Peek sigue el paquete únicamente mediante servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de reparto mientras lo tengas abierto o tengas las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con la fecha de ese escaneo, por ejemplo «Sin novedades desde el lun 12 oct». La ficha muestra entonces: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Jadlog.» Tras tres semanas sin ningún escaneo nuevo, dice: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Jadlog.»

Puedes elegir los avisos «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa el seguimiento y no puede concertar un nuevo reparto ni contactar con una franquicia de Jadlog por ti.

## Preguntas sobre el seguimiento de Jadlog

### ¿Cómo es un número de seguimiento de Jadlog?

Un número de paquete Jadlog tiene 14 dígitos (por ejemplo `48291736405821`, dígitos inventados). Puedes seguirlo con ese código de 14 dígitos en Peek o en la [portada de Jadlog](https://www.jadlog.com.br/jadlog/home).

### ¿Qué es Jadlog Predict?

[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) es un servicio automático de avisos por SMS disponible en áreas metropolitanas y de gran volumen que indica al destinatario la franja prevista de entrega de 1 hora.

### ¿Cómo funciona Jadlog Pickup?

[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup) cuenta con más de 4.000 comercios asociados en Brasil donde los destinatarios pueden recoger paquetes o depositar devoluciones. Jadlog envía avisos por SMS y correo electrónico para seguir el paquete.

### ¿Por qué está Jadlog vinculado a DPD y Geopost?

Jadlog forma parte del grupo europeo Geopost junto con DPD, Chronopost, SEUR, BRT y Speedy. Por eso utiliza números de paquete Geopost de 14 dígitos y enlaza con la red internacional de Geopost en 154 países.

:::sources
- [Jadlog: Home](https://www.jadlog.com.br/jadlog/home) – buscador `RASTREIE SUA ENCOMENDA` y resumen de la red brasileña
- [Jadlog: Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) – pertenencia al grupo Geopost, más de 500 franquicias, más de 4.000 puntos Pickup, más de 7.000 vehículos, 7.000 colaboradores directos e indirectos, 154 países
- [Jadlog: Produtos e Serviços](https://www.jadlog.com.br/jadlog/servicos) – Jadlog.com (hasta 30 kg), Package, Rodo (hasta 50 kg), RodoEconômico, Cargo, DOC
- [Jadlog: Pickup](https://www.jadlog.com.br/jadlog/pickup) – más de 4.000 comercios asociados, cobertura >90 % de la población, avisos SMS/correo y devoluciones Reversa
- [Jadlog: Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) – avisos automáticos por SMS con franja de entrega de 1 hora
- [Jadlog: Internacional](https://www.jadlog.com.br/jadlog/internacional) – exportación a 154 países / 300 destinos vía Geopost
- [Jadlog: Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) – buscador de franquicias y delegaciones en Brasil
- [Jadlog: Atendimento](https://www.jadlog.com.br/jadlog/atendimento) – alcance de la página pública de atención
- [Jadlog: LGPD](https://www.jadlog.com.br/jadlog/lgpd) – portal de derechos de privacidad y datos personales
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – plataforma pública de mediación (respuesta en 10 días, cuenta gov.br plata u oro)
- [Universal Parcel Scraper: jadlog](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jadlog) – detección de 14 dígitos y prioridad en Brasil (alias `DPD Brazil`), regla de enlace `captcha?cte=`, API pública de seguimiento de Geopost, mapeo de estados y frecuencia de actualización
:::
