---
title: Seguimiento Sengi Express: códigos China–Italia y ayuda
description: Sigue un paquete de Sengi Express (grupo Poste Italiane) de China a Italia o Europa: códigos SDA/MT, aduanas, relevos Crono, retrasos y contactos.
slug: seguimiento-sengi-express
published: 2026-11-24
updated: 2026-11-24
---

**Sengi Express** (*Guangzhou Sengi Logistics Co., Ltd.* / *Seng Yi (Shenzhen) Global Supply Chain Management Co., Ltd.*, en chino *广州森吉物流有限公司*) es un operador logístico transfronterizo controlado mayoritariamente por **Poste Italiane** ([portal.sengiexpress.com/about/](https://portal.sengiexpress.com/about/)). Especializado en inyección directa de comercio electrónico de China a Italia y Europa, Sengi gestiona la consolidación en origen en China, carga aérea y marítima, despacho aduanero europeo B2C/B2B en puertas de entrada como Milán Malpensa (**MXP**), Ámsterdam (**AMS**), Lieja (**LGG**) y París (**CDG**), transporte por carretera paneuropeo mediante Sennder (del grupo Poste Italiane) y entrega final a domicilio o taquilla en Italia a través de la red Crono de [Poste Italiane](carrier:poste-italiane). Puedes seguir un paquete en [portal.sengiexpress.com](https://portal.sengiexpress.com/home) o pegar tu código en el cuadro de arriba.

## Números de seguimiento de Sengi Express (formatos SDA `...C...F` y `MT`)

Sengi Express suele emitir desde China un albarán **Poste Italiane / SDA** de 13 caracteres para que el mismo código sirva desde el almacén chino de Sengi hasta la entrega en Italia, junto con códigos transfronterizos de 16 caracteres terminados en `MT`:

| Formato (inventado) | Qué es |
| --- | --- |
| `482C73918462F` | Albarán SDA / Poste Italiane de 13 caracteres (`3 dígitos` + `C` + `8 dígitos` + `F`) seguido por Sengi Express antes de que Poste Italiane lo escanee en Italia. |
| `SENGI482917360MT` | Código transfronterizo Sengi de 16 caracteres (`5 letras` + `9 dígitos` + `MT`). |

Sengi asigna al paquete con destino a Italia su albarán SDA antes de que SDA lo conozca, de modo que el localizador de Poste Italiane puede no encontrar el número al principio; el localizador de Sengi sigue el paquete desde su almacén en China hasta el destinatario en Italia. Si lo eliges manualmente, el localizador de Sengi acepta también otras referencias alfanuméricas de socios de 8 a 40 caracteres. Consulta los [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Sengi Express

Sengi Express publica descripciones en inglés (y chino) de escaneos transfronterizos junto con códigos de evento opcionales y marcas horarias:

| Sengi Express muestra | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Order information received. We're expecting your parcel to arrive with us.` (código `10`) | El comerciante transmitió los datos electrónicos antes de la recepción física en el almacén de Sengi en China. | «Etiqueta creada» |
| `Arrived at Destinated Airport`, `Terminal Notification of Arrival` | El vuelo internacional aterrizó en la puerta aeroportuaria europea de destino (como MXP, AMS, LGG o CDG). | «En tránsito» |
| `Arrived at customs clearance warehouse` | El paquete entró en el almacén aduanero europeo de despacho. | «En aduanas» |
| `Customs clearance inspection, Reason: supposed as undervaluation, waiting for customs check(VM)` | Seleccionado por las aduanas europeas para comprobar el valor declarado o el contenido (`VM`). | «En aduanas» |
| `Ready for transit to last mile operator` | Despachado en aduanas y en cola para entregarse a [Poste Italiane](carrier:poste-italiane) o al transportista local. | «En tránsito» |

Un escaneo con otro texto toma su fase de la redacción. Sengi señala que los destinatarios pueden seguir también el paquete en la web de [Poste Italiane](carrier:poste-italiane), con el mismo albarán `...C...F` o con el nuevo número cuando Sengi reetiqueta el envío.

## Inyección directa, puertas aduaneras europeas y entrega con Poste Italiane

Según [Sobre Sengi Express](https://portal.sengiexpress.com/about/) y [Productos Sengi Express](https://portal.sengiexpress.com/product/):

- **Puertas aéreas europeas:** Sengi realiza despacho aduanero directo B2C y B2B en Milán Malpensa (`MXP`), Ámsterdam (`AMS`), Lieja (`LGG`) y París Charles de Gaulle (`CDG`).
- **Servicios de última milla de Poste Italiane:** en Italia, Sengi inyecta paquetes en **Crono** (estándar), **Crono Express**, **Extralarge** (sobredimensionados / última milla FBA), **Crono ADP** (taquillas) y **Crono Reverse** (devoluciones).
- **Controles de valor en aduana (`VM`) y cargos UE:** los envíos de China a la UE deben cumplir las normas aduaneras europeas, el IVA de importación (IOSS o pagado a la entrega) y los cargos aduaneros o de tramitación de la UE para pedidos de fuera de la UE. Si aparece `supposed as undervaluation, waiting for customs check(VM)`, aduanas verifica el valor declarado con el importador o agente — espera al despacho o avisa a tu vendedor. Consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de Sengi Express retenido, sin novedades o perdido

Como son los mercados en línea y vendedores chinos quienes mantienen el contrato con Sengi Express, **contacta siempre primero con tu vendedor o plataforma** para reembolsos, reenvíos o documentación de valor aduanero.

- **Silencio antes de los escaneos en Italia:** los paquetes suelen pausarse varios días entre `Order information received`, `Arrived at Destinated Airport` y `Ready for transit to last mile operator` mientras la carga aérea supera lotes aduaneros europeos y viaja en camión hasta Italia. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Sigue en Poste Italiane tras el relevo:** cuando aparezca `Ready for transit to last mile operator` en un envío italiano `PI`, consultar el mismo código `...C...F` (o el nuevo código reetiquetado que muestra Peek) en [Poste Italiane](carrier:poste-italiane) muestra la clasificación italiana, los intentos de entrega y los avisos de Punto Poste o taquilla.
- **Perdido, dañado o marcado como entregado:** pregunta primero a vecinos y en tu oficina local de Poste Italiane / Punto Poste, y abre disputa en la plataforma antes de que venza la protección al comprador. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contactos de Sengi Express

Contactos oficiales publicados en [Sobre Sengi Express](https://portal.sengiexpress.com/about/) y [Contacto Sengi Express](https://portal.sengiexpress.com/contact/):

- **Correo electrónico:** `service@sengiexpress.com`.
- **Formulario de contacto en línea:** [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).
- **Operador:** *Guangzhou Sengi Logistics Co., Ltd.* (*广州森吉物流有限公司*, ICP `粤ICP备2021101066号`), controlado mayoritariamente por Poste Italiane.

> Desconfía de SMS falsos que digan que debes pagar una tasa de Poste Italiane o de aduanas en un enlace desconocido. Confirma siempre cualquier cargo real en tu página de pedido o en la web oficial de Poste Italiane. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Sengi Express con Peek

Cuando introduces un código `...C...F` de 13 caracteres (`482C73918462F`, inventado), [Peek](/) consulta primero a [Poste Italiane](carrier:poste-italiane), luego a AliExpress y Sengi Express (Sengi antes que AliExpress en Italia; en China primero AliExpress y después Sengi), y muestra «Sengi Express tiene este paquete.» cuando Sengi lo gestiona. Para un código `...MT` (`SENGI482917360MT`, inventado), Peek consulta a [MaltaPost](carrier:maltapost), cuyos números también acaban en `MT`: para ese código u otro albarán de socio, toca «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete y elige **Sengi Express** (buscar «Sengi» también lo encuentra).

Peek lee el localizador de Sengi y muestra la fase («Etiqueta creada», «En tránsito», «En aduanas» u otra fase si el texto de un escaneo lo indica), el texto en inglés de Sengi (chino si no hay inglés) y el lugar. Los códigos de evento fijan la fase pero no aparecen en ninguna aplicación de Peek. Sengi indica el desfase respecto a UTC de sus escaneos; un escaneo sin desfase se fecha en la zona horaria del lugar (Italia para `MXP`, Pekín para Cantón) o en la del escaneo contiguo, y Peek los muestra todos en la hora de tu dispositivo. En la línea de Poste Italiane de Sengi, Peek comprueba si Poste Italiane tiene el paquete: la ficha muestra entonces «Entrega con» seguido de Poste Italiane y añade el nuevo número cuando Sengi reetiqueta el paquete.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que Sengi mostrara un escaneo de salida a reparto (ninguno hasta ahora), cada 2 minutos desde las 6:00 en la hora del último escaneo mientras el localizador de Sengi responda. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de Sengi no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Sengi Express.», con un enlace «Pide ayuda a Sengi Express» a la página de contacto de Sengi. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Sengi Express.»

Puedes elegir ajustes de alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede despachar aduanas, editar direcciones de entrega ni contactar con Sengi Express o Poste Italiane por ti.

## Preguntas sobre el seguimiento de Sengi Express

### ¿Qué es Sengi Express?

Sengi Express (*Guangzhou Sengi Logistics Co., Ltd.*) es un transportista logístico de comercio electrónico China–Italia y China–Europa controlado por **Poste Italiane**, encargado de la consolidación en origen, despacho aduanero europeo (MXP, AMS, LGG, CDG) e inyección en la red Crono de Poste Italiane.

### ¿Por qué mi código `...C...F` de Poste Italiane aparece primero en Sengi Express?

Sengi asigna a un paquete con destino a Italia su albarán SDA / [Poste Italiane](carrier:poste-italiane) (`482C73918462F`, inventado) antes de que SDA lo conozca. El localizador de Sengi muestra los escaneos de vuelo y aduana mientras que el de Poste Italiane puede no encontrar aún el código.

### ¿Qué significa `supposed as undervaluation, waiting for customs check(VM)`?

La aduana europea marcó el paquete para una inspección del valor declarado (`VM`). El agente de aduanas o el vendedor tramitan la documentación; contacta con tu vendedor si el estado no cambia.

### ¿Cómo contacto con la atención al cliente de Sengi Express?

Contacta primero con tu tienda en línea o mercado para reembolsos o justificantes aduaneros. Sengi Express indica `service@sengiexpress.com` y un formulario en línea en [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).

:::sources
- [Sengi Express: portal de seguimiento](https://portal.sengiexpress.com/home) – localizador público de albaranes e información legal de *Guangzhou Sengi Logistics Co., Ltd.*
- [Sengi Express: sobre nosotros](https://portal.sengiexpress.com/about/) – participación mayoritaria de Poste Italiane, servicios transfronterizos y `service@sengiexpress.com`
- [Sengi Express: productos y servicios](https://portal.sengiexpress.com/product/) – puertas aduaneras MXP/AMS/LGG/CDG, Poste Italiane Crono / Crono Express / Extralarge / Crono ADP / Crono Reverse y transporte europeo Sennder
- [Sengi Express: contacto](https://portal.sengiexpress.com/contact/) – página oficial de contacto para destinatarios y clientes (URL de ayuda enlazada por Peek)
- [Universal Parcel Scraper: sengi-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sengi-express) – reglas de detección `...C...F` y `...MT`, analizador `api-v2/tracking/web`, mapa de estados, gestión de desfase UTC y relevo Poste Italiane `PI` / `relabelTrackingNumber`
:::
