---
title: Seguimiento Onlog Express: códigos AA, estados y ayuda
description: Sigue un paquete de Onlog Express en Brasil: códigos AA de 12 caracteres, estados en centros de distribución, aviso contra estafas y soporte.
slug: seguimiento-onlog-express
published: 2026-11-24
updated: 2026-11-24
---

**Onlog Express** (*Onlog Franqueadora*) es una red brasileña de logística y entrega de paquetes con sede en São Paulo, más de 20 años de experiencia en alianzas logísticas y más de 18 millones de paquetes entregados en Brasil ([onlogexpress.com.br](https://onlogexpress.com.br/)). Conecta comercios electrónicos y empresas con operadores nacionales de reparto en modalidades que van desde entregas urgentes en minutos o en el mismo día (*Entrega em Minutos*, *Entrega no Dia*) hasta prioritario (*Expresso D1*) y económico (*Econômico D2*), además de fulfillment y carga internacional; ofrece seguimiento público por código de objeto o CPF/CNPJ en [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php). Puedes consultar un envío allí o pegar tu código `AA` de 12 caracteres en el cuadro de arriba.

## Números de seguimiento de Onlog Express (formato `AA`)

El seguimiento público de Onlog Express usa un código de objeto de 12 caracteres que comienza por `AA` seguido de 10 dígitos:

| Formato (inventado) | Qué es |
| --- | --- |
| `AA4829173605` | Código de objeto Onlog Express de 12 caracteres (`AA` + 10 dígitos). |
| `AA7391846208` | Otra referencia de paquete `AA` de Onlog Express de 12 caracteres. |

Busca la referencia `AA` en el correo de envío de tu tienda o en el enlace de seguimiento `onlog.app.br/rastreio/...`. Si no dispones del código, el portal web de Onlog en [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) permite buscar con tu CPF o CNPJ brasileño (e incluye una opción separada para objetos anteriores al 01/01/2025). Consulta los [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Onlog Express

Onlog Express publica descripciones de escaneos en portugués desde la creación de la etiqueta y recogida hasta los traslados por centros de distribución y la firma del destinatario:

| Onlog Express muestra (PT-BR) | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `OBJETO CRIADO`, `ETIQUETA EMITIDA` | Envío creado y etiqueta emitida electrónicamente antes de la recogida física. | «Etiqueta creada» |
| `OBJETO POSTADO`, `COLETA DE ENCOMENDA` | Paquete depositado en una unidad Onlog o recogido en el remitente. | «Admitido por el transportista» |
| `OBJETO EM TRANSFERÊNCIA`, `ENTRADA NO CENTRO DE DISTRIBUIÇÃO`, `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`, `BIPE DE EXPEDIÇÃO`, `BIPE DE RECEBIMENTO` | Recibido en un centro de distribución, enviado entre instalaciones o remitido al operador logístico regional. | «En tránsito» |
| `OBJETO SAIU PARA ENTREGA AO DESTINATÁRIO`, `BIPE DE SAÍDA PARA ENTREGA` | En ruta con el repartidor local para la entrega final a domicilio hoy. | «En reparto» |
| `OBJETO ENTREGUE AO DESTINATÁRIO`, `ASSINATURA DE ENCOMENDA` | Entregado y firmado en la dirección de destino. | «Entregado» |

`ENCAMINHADO PARA O OPERADOR LOGÍSTICO` significa que el paquete fue transferido al operador logístico encargado del siguiente tramo.

## Servicios de entrega y aviso oficial contra estafas de tasas

Según [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Niveles de servicio:** **Entrega em Minutos** (urgente), **Entrega no Dia** (mismo día), **Expresso D1** (tránsito prioritario) y **Econômico D2** (económico), dependiendo los plazos exactos y la disponibilidad de los códigos postales (CEP) de origen y destino.
- **Aviso importante contra estafas:** Onlog publica una advertencia explícita en su página principal afirmando que **Onlog no solicita pagos de tasas por correo electrónico, WhatsApp ni teléfono**. No pagues nunca ningún impuesto o tasa de liberación exigida mediante mensajes no solicitados en nombre de Onlog. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Paquete de Onlog Express retrasado, sin novedades o perdido

- **Silencio tras `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`:** los escaneos suelen pausarse entre hubs regionales o durante el traspaso al socio local de última milla entre estados brasileños. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Entregado sin justificante o paquete perdido:** pregunta primero en la portería (*portaria*), a familiares y vecinos; luego contacta con la tienda en línea donde compraste y comunícate con el soporte de Onlog en São Paulo en horario laboral con tu número `AA`. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente de Onlog Express y contactos en São Paulo

Canales oficiales publicados en [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Teléfono (São Paulo):** `(11) 5662-8108` y `(11) 5663-3829`.
- **Correo electrónico:** `atendimento@onlogexpress.com.br`.
- **Horario de atención:** de lunes a viernes, de `08:00` a `18:00` BRT (cerrado sábados y domingos).
- **Sede (*Matriz São Paulo*):** Rua Estados Unidos, 2031, Jardim América, São Paulo – SP, Brasil.
- **Localizador en línea:** [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php).

> Recuerda el aviso oficial de Onlog: Onlog nunca cobra tasas de liberación por correo electrónico, WhatsApp ni teléfono. Ignora y denuncia cualquier mensaje que pida un pago por PIX o tarjeta para desbloquear un paquete. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Onlog Express con Peek

Cuando introduces un código `AA` de 12 caracteres (`AA4829173605` o `AA7391846208`, inventados), [Peek](/) consulta primero a [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) y [Aramex Nueva Zelanda](carrier:aramex-nz), luego a los servicios de seguimiento y después a Onlog Express — cuyo localizador abre en un navegador —, y muestra «Onlog Express tiene este paquete.» cuando Onlog lo confirma. Peek reconoce también como Onlog los enlaces pegados `onlog.app.br/rastreio/AA...`. Si necesitas elegir Onlog manualmente, toca «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete (buscar «ONLOG» o «Onlog Franqueadora» también lo encuentra).

Peek lee el localizador de Onlog en un navegador y muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En reparto», «Entregado»), el texto portugués del escaneo y la ubicación. Onlog entrega cada escaneo al minuto sin desfase UTC: Peek lo fecha en la zona horaria de su lugar (São Paulo para un escaneo en São Paulo, Manaos para Manaos), un escaneo sin lugar en la del escaneo contiguo o en su defecto en la de São Paulo, y los muestra todos en la hora de tu dispositivo. El localizador de Onlog no proporciona día estimado de entrega, peso, códigos de evento ni hora de entrega.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 30 minutos — el mínimo que permite Onlog entre dos consultas — y espera una hora tras un fallo; no realiza comprobaciones cada 2 minutos el día en que el paquete está en reparto. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de Onlog no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 30 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Onlog Express.» Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Onlog Express.»

Puedes elegir ajustes de alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede buscar paquetes por CPF/CNPJ, editar direcciones de entrega ni contactar con Onlog Express por ti.

## Preguntas sobre el seguimiento de Onlog Express

### ¿Qué aspecto tiene un número de seguimiento de Onlog Express?

Los códigos de seguimiento de Onlog Express empiezan por `AA` seguidos de 10 dígitos (12 caracteres en total, por ejemplo `AA4829173605`, inventado).

### ¿Puedo seguir un paquete de Onlog Express con mi CPF o CNPJ?

Sí, en el sitio de Onlog en [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) puedes cambiar a la pestaña **POR CPF/CNPJ** si no tienes el código `AA`. Peek utiliza únicamente el código de objeto `AA`.

### Recibí un WhatsApp o correo pidiendo una tasa de Onlog — ¿es real?

No. Onlog afirma explícitamente en [onlogexpress.com.br](https://onlogexpress.com.br/) que **nunca** pide pagos de tasas por correo electrónico, WhatsApp ni teléfono. No pagues.

### ¿Cómo contacto con la atención al cliente de Onlog Express?

Llama al `(11) 5662-8108` o `(11) 5663-3829`, o escribe a `atendimento@onlogexpress.com.br` de lunes a viernes de 08:00 a 18:00 BRT.

:::sources
- [Onlog Express: sitio oficial](https://onlogexpress.com.br/) – niveles de servicio (*Entrega em Minutos*, *Entrega no Dia*, *Expresso D1*, *Econômico D2*), 18M+ paquetes, 20+ años, aviso contra estafas y contactos de la sede de São Paulo
- [Onlog Express: localizador oficial (Conecta)](https://conecta.log.br/rastreio.php) – seguimiento público por código de objeto o CPF/CNPJ
- [Universal Parcel Scraper: onlog-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/onlog-express) – formato `AA\d{10}`, regla de enlace `onlog.app.br/rastreio/`, mapa de 13 frases en portugués, consulta en navegador cada 30 minutos como máximo (60 tras error) y relojes de escaneo
:::
