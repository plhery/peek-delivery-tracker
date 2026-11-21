---
title: Seguimiento de SPX Express Brasil: Shopee y estados
description: Rastrea un paquete de SPX Express de un pedido de Shopee en Brasil: dónde ver el código BR, qué significan sus estados en portugués, Agência Shopee y reembolso.
slug: seguimiento-spx-express-brasil
published: 2026-11-21
updated: 2026-11-21
---

SPX Express (Shopee Xpress) es la red logística propia de Shopee en **Brasil**. Recoge los pedidos a los vendedores y los entrega a domicilio o en una *Agência Shopee*, un comercio colaborador donde retirar el paquete. Shopee denomina a este servicio *Entrega Padrão* (envío estándar) e indica seguir todo desde la app de Shopee en Brasil, donde figuran el pedido, la fecha estimada y cualquier reembolso. También puedes consultar el envío en [spx.com.br](https://spx.com.br/track) o pegar el código en el cuadro de arriba.

## Número de seguimiento de SPX Express: dónde encontrarlo y formato

En la aplicación de Shopee en Brasil, entra en `Eu` > `Minhas Compras`, abre el pedido dentro de `A Caminho` (algunos artículos de ayuda de Shopee lo llaman `A Receber`) y pulsa `Status de Envio` para ver cada actualización. Shopee incorpora allí automáticamente el código de seguimiento en cuanto se genera, junto con la fecha prevista de entrega.

Estos son los formatos que Peek reconoce (caracteres inventados):

| Formato | Qué identifica |
| --- | --- |
| `BR4829105736491` | SPX Express en Brasil: `BR`, 12 cifras y un dígito final (15 caracteres). |
| `BR482910573649X` | SPX Express en Brasil: `BR`, 12 cifras y una letra final (15 caracteres). |
| `BR482910573649XSPXLM48291057` | SPX Express: ese mismo código de 15 caracteres seguido de `SPXLM` y 8 cifras, tal como aparece en ciertas etiquetas de marketplace. |
| `EA482910576BR` | Dos letras, nueve cifras y `BR`: un código postal de [Correios de Brasil](carrier:correios-br), no de SPX. |

El identificador de pedido (`ID do pedido`) no sirve como código de seguimiento, aunque Atención al Cliente de Shopee puede solicitarlo.

No todos los pedidos de Shopee en Brasil viajan con SPX. Shopee diferencia entre *Entrega Padrão* (Shopee Xpress), *Entrega Correios* (cuyo número funciona también en la web de Correios) y *Entrega do Vendedor*, donde el propio comercio reparte y actualiza el estado. En compras enviadas desde el extranjero hacia Brasil, Shopee menciona a Correios —con un número local dentro de la app— o a su socio Anjun Courier; aun así, el seguimiento de SPX incluye eventos propios para determinados paquetes internacionales (ver más abajo). Para otros códigos, consulta [formatos de números de seguimiento](guide:tracking-number-formats) y [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Qué significan los estados de seguimiento de SPX Express

SPX muestra sus mensajes en portugués (con algún texto puntual en inglés); varias líneas añaden la localidad detrás de dos puntos.

| Mensaje de SPX | Qué significa para tu envío | Peek muestra |
| --- | --- | --- |
| `Pedido em preparação` | El vendedor está preparando el pedido; SPX aún no tiene el paquete. | «Etiqueta creada» |
| `Pedido postado …` | El vendedor entregó el paquete en un punto de admisión en Brasil (spx.com.br añade el municipio); SPX pasa a recogerlo allí. | «Admitido por el transportista» |
| `Seu pedido foi coletado` | SPX recogió el paquete en el domicilio o almacén del vendedor. | «Admitido por el transportista» |
| `Seu pedido chegou ao centro logístico: …` | Llegó a un centro logístico de SPX en Brasil. | «En tránsito» |
| `Seu pedido saiu do centro logístico: …` | Salió de un centro logístico hacia la siguiente etapa. | «En tránsito» |
| `O pedido está em separação no centro logístico, novas atualizações em até 1 dia.` | En clasificación; SPX avisa de nuevas lecturas en el plazo de un día. | «En tránsito» |
| `Pedido em trânsito` | En ruta entre centros. Entre estados brasileños o ciudades lejanas, Shopee explica que el bulto pasa días dentro del camión de transferencia y el estado no cambia hasta descargarse y escanearse en el centro de distribución de tu estado. | «En tránsito» |
| `Pedido em rota de entrega para seu endereço, aguarde pelas próximas horas.` | En reparto hacia tu dirección para las próximas horas. | «En reparto» |
| `Pedido deve ser retirado na agência: …` | El paquete espera a que el destinatario lo retire en la agencia indicada. | «Listo para recoger» |
| `Houve um imprevisto durante a entrega do seu pedido …`, `Um imprevisto ocorreu …`, `Insucesso na entrega por endereço incorreto…` | No pudo completarse el reparto ese día; SPX reprograma un nuevo intento en la dirección. | «Intento de entrega» |
| `Pedido entregue` | Entregado. | «Entregado» |
| `Pedido será devolvido ao vendedor`, `Pedido será devolvido ao vendedor. Por favor, aguarde.` | Va de vuelta al vendedor; la versión larga solo pide esperar. | «Devuelto al remitente» |
| `Pedido devolvido` | Devuelto al vendedor. | «Devuelto al remitente» |
| `Pedido extraviado. Estorno será processado automaticamente.` | Paquete extraviado; SPX indica que el reembolso se tramita de forma automática. | «Incidencia» |
| `Pedido descartado` | Tras fallar la entrega, el paquete fue dado de baja: ni se entregó ni volvió al vendedor. Contacta con Shopee. | «Incidencia» |

Algunos envíos reciben en su lugar líneas de la flota de reparto de SPX encabezadas por `Seu pacote`, entre ellas `Seu pacote foi recebido pelo centro de triagem` (en centro de clasificación), `Seu pacote foi recebido pelo centro de distribuição` (en el centro de distribución de tu zona), `Seu pacote está sendo entregue pelo entregador` (con el hub delante: en reparto) y `Seu pacote foi entregue` (entregado). Peek les asigna las mismas etapas, como «En reparto».

**Paquetes desde el extranjero hacia Brasil.** Las lecturas que empiezan por `[China]` corresponden al tramo de origen (como `[China]O pedido foi liberado na alfândega de exportação` al superar la aduana de exportación). Después aparecen `Pedido chegou no Brasil` (llegó a Brasil), `Pedido está na alfândega de importação` (en la aduana brasileña de importación), `Pedido em inspeção na alfândega de importação` (bajo inspección aduanera) y, en inglés, `Import Customs Cleared` (despacho de importación completado). Peek muestra «En aduanas» en los dos pasos aduaneros y vuelve a «En tránsito» tras `Import Customs Cleared`.

Por el contrario, `O pacote não pôde ser importado, será retornado ou confiscado pela alfândega` avisa de que la aduana brasileña denegó la importación y el paquete será devuelto o incautado; Peek muestra «Incidencia». En ese caso, tramita el reembolso desde Shopee.

## Entrega de SPX en Brasil: intentos, palabra clave y Agência Shopee

Las condiciones siguientes rigen para pedidos de Shopee en **Brasil**:

**Plazos.** Ni comprador ni vendedor pueden concertar fecha ni franja horaria de reparto. Shopee indica de 1 a 25 días hábiles desde la recogida en pedidos de vendedores radicados en Brasil, y por lo general de 30 a 50 días naturales en compras internacionales. Tu fecha estimada figura en la ficha del pedido.

**Intentos.** Los socios logísticos de Shopee realizan 3 intentos de entrega antes de devolver el envío al vendedor, y una entrega fallida se reembolsa automáticamente. La dirección solo puede cambiarse antes de confirmarse el pago y una única vez; después, Shopee indica dejar agotar los intentos y recibir el reembolso. Más detalles en [entrega fallida](guide:missed-delivery).

**Palabra clave (*palavra-chave*).** Determinados pedidos en Brasil llevan una contraseña aleatoria que debes decir al repartidor en la puerta; solo quien la conoce puede recibir el paquete. Aparece en la página del pedido y llega por correo y notificación de Shopee. Si no vas a estar, dásela a alguien de confianza, como un vecino o el conserje (*porteiro*) del edificio. Nunca la facilites antes de tener el paquete en la mano y rechaza cualquier entrega a otro nombre o dirección.

**Recogida en Agência Shopee (`Retire Fácil`).** Donde esté habilitada en Brasil, elígela al pagar en `Opção de Envio` > `Ver mais` > `Retire Fácil`; luego no es posible cambiar a entrega a domicilio. Cuando llega el paquete, Shopee envía un PIN por WhatsApp, correo electrónico o en la app, donde también consta el último día para retirarlo. Muestra el PIN y el código de seguimiento en el mostrador; cualquier persona con ambos datos puede recogerlo por ti. El plazo de estancia es de 7 días hábiles antes de que SPX retire el bulto. Si una agencia queda temporalmente inoperativa, Shopee puede redirigir el envío a tu dirección predeterminada. El [mapa de puntos de SPX](https://spx.com.br/service-point) localiza agencias seleccionando `Retirar um pacote`.

## Compras internacionales hacia Brasil: Remessa Conforme e impuestos

En Brasil, la Receita Federal incluye a Shopee entre los comercios certificados en su programa *Remessa Conforme* (PRC). En una plataforma certificada, el impuesto de importación y el ICMS se abonan al finalizar la compra. La Receita Federal advierte de comprobar que los tributos aparezcan desglosados en la pantalla de pago: si no figuran allí, se cobrarán al llegar la mercancía a Brasil perdiendo la rebaja arancelaria.

Tasas publicadas por la Receita Federal en Brasil para declaraciones registradas desde el 12 de mayo de 2026:

- **Impuesto de importación (*Imposto de Importação*)** en tienda certificada PRC para envíos a personas físicas: 0 % hasta 50 US$ de valor en aduana (suma de bienes, flete y seguro); por encima de 50 US$, 60 % con un descuento equivalente a 30 US$ sobre la cuota del impuesto.
- **ICMS** (impuesto estatal brasileño): actualmente entre el 17 % y el 20 % según el estado de Brasil, esté o no certificada la tienda.

Shopee señala que un CPF (número fiscal brasileño) irregular puede impedir una compra internacional y que los impuestos de importación no se reembolsan. Si la aduana brasileña retiene un pedido gestionado por Anjun Courier, Shopee pide justificante de pago y del importe de compra, copia de un documento brasileño de identidad (RG o CNH) y el formulario de comprobación de valor de la Receita Federal (RCV). En envíos de [Correios de Brasil](carrier:correios-br), revisa su buscador por si hubiera trámites pendientes. La Receita Federal recuerda que no envía recibos bancarios (*boletos*), no llama por teléfono ni manda correos para cobrar estos tributos. Consulta también [paquetes retenidos en aduanas](guide:customs).

## Envío de SPX sin actualizar, retrasado o no recibido

**Varios días sin novedades.** Shopee aclara que es habitual en Brasil: el seguimiento solo avanza cuando el paquete se escanea al entrar en un nuevo centro, de modo que los trayectos largos por carretera entre estados, los fines de semana y la cola en los hubs dejan el estado quieto. Una ruta que parece alejarse también entra dentro de la planificación logística, y a menudo saltan varios pasos juntos cuando sale a reparto. Más información en [seguimiento que no se actualiza](guide:tracking-not-updating).

**El código no funciona.** Puede que aún no hayan recogido el paquete, que el código tenga una errata o que todavía no conste en el sistema. Pregunta al vendedor en el chat de Shopee; la plataforma subraya que no implica que el envío se haya perdido.

**Con retraso.** Espera a que venza la fecha estimada de entrega. A partir de entonces puedes solicitar el reembolso en la app: `Eu` > `A Caminho` > el pedido > `Pedido de Reembolso`, motivo `Não recebi o pedido`. El vendedor tiene 2 días para contestar; si no lo hace, el reembolso se aprueba solo. Según Shopee, la mayoría de los pedidos atrasados llegan dentro de los 5 días posteriores a la fecha prevista.

**Figura como entregado pero no aparece.** Consulta con vecinos, familiares y la portería (*porteiro*) del inmueble y pide luego el reembolso en la app: en *Entrega Padrão*, dentro de los 7 días siguientes a la entrega. Shopee puede encargar una averiguación al transportista (*acareação*), que puede escribirte por WhatsApp o correo o hacer que el conductor regrese al domicilio. Lee también [entregado pero no recibido](guide:delivered-not-received).

**Devuelto.** Tras `Pedido será devolvido ao vendedor`, el paquete regresa al comercio. Si se debe a intentos fallidos de reparto, Shopee reembolsa automáticamente; ver [devuelto al remitente](guide:returned-to-sender).

## Contacto de SPX Express: atención a través de Shopee

Shopee indica que el comprador no puede contactar directamente con la empresa de transporte por cuestiones de seguimiento o reparto: lo gestiona Shopee bajo su *Garantia Shopee* en Brasil:

- **Chat Shopee:** en la app, entra en `Eu` > `Central de Ajuda`, baja hasta `Atendimento ao Cliente` y elige `Chat Shopee`. Funciona de lunes a viernes de 8:00 a 22:00 y sábados y domingos de 8:00 a 20:00, hora de Brasilia.
- **Formulario de solicitudes:** disponible a cualquier hora desde la [página de horarios de Atención al Cliente de Shopee](https://help.shopee.com.br/portal/4/article/141507), teniendo a mano el `ID do pedido`. (El pie de `spx.com.br`, orientado a conductores colaboradores y agencias, muestra además el canal de WhatsApp `(11) 2770-1438`, de lunes a sábado salvo festivos de 8:00 a 20:00, hora de Brasilia.)
- **Mala conducta del repartidor** (por ejemplo, pedir dinero extra): repórtalo en `Chat Shopee`.
- **Reclamación sin resolver en Brasil:** Shopee Brasil está adherida a [consumidor.gov.br](https://www.consumidor.gov.br/pages/principal/), la plataforma pública brasileña donde las empresas participantes se comprometen a responder en un máximo de 10 días (requiere cuenta `gov.br` de nivel plata u oro).

> Shopee afirma que solo se comunica mediante canales oficiales como su aplicación, cuentas verificadas en redes sociales o correos terminados en `shopee.com` (mientras que el rastreador público de SPX opera en `spx.com.br`), y el porte se paga siempre dentro de la app. Aprende a detectar engaños en [mensajes falsos de entrega](guide:delivery-scam-texts).

## Rastrear un paquete de SPX Express con Peek

[Peek](/) reconoce a SPX Express Brazil al instante en cuanto pegas un código `BR` de 12 cifras seguidas de un dígito o letra final (con o sin extensión `SPXLM…`), mostrando «Transportista detectado» junto con «Cambiar». Un enlace de `spx.com.br` (`/track?` o `/detail/` con el código `BR`) también asigna directamente el paquete a SPX aunque venga en un SMS sin `https://`. Peek consulta en SPX únicamente códigos `BR`, no el identificador de pedido de Shopee.

Peek lee el mismo seguimiento que `spx.com.br` y presenta el estado actual, las líneas de SPX (en portugués y algunas en inglés), la localidad registrada en centros logísticos, hubs, puertos o agencias de recogida y la hora exacta de entrega convertida a la zona horaria de tu dispositivo —en `peektracker.com` y en las apps de iPhone y Android el historial cierra con «Horas en tu zona horaria: …»—. También enseña hitos que la web pública de SPX oculta, como `Seu pedido chegou ao último centro logístico` (llegada al último centro logístico) o una recogida fallida en el vendedor («Etiqueta creada»); cuando una recogida fallida aparece después de `Pedido postado …`, el bulto continúa en el punto de admisión («Admitido por el transportista»), salvo que el aviso indique que el remitente no había preparado el pedido («Etiqueta creada»). SPX no facilita fecha prevista de entrega, peso ni nombre del socio de reparto. En un enlace compartido, el evento de entrega muestra solo `Delivered` y omite quién recibió el paquete. La palabra clave, el PIN de la *Agência Shopee* y los reembolsos se gestionan en la app de Shopee.

Mientras SPX aún no reconozca el código, Peek muestra «Esperando a SPX Express Brazil» y sigue comprobándolo. Si pasados dos días SPX continúa sin conocerlo y ninguna otra fuente ha respondido, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a revisarlo una vez al día y, cumplida una semana desde que lo añadiste, una vez por semana. Si el envío ya está en marcha y transcurren cuatro días sin un nuevo escaneo, Peek ofrece «Pide ayuda a SPX Express Brazil», que abre la página de horarios de Atención al Cliente de Shopee.

Mientras mantengas el paquete abierto en pantalla o, el día de reparto, con las notificaciones activadas, Peek comprueba novedades hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 de la mañana (hora local) el día en que SPX escanea el paquete en reparto, siempre que responda el propio rastreador de SPX (mediante servicios de seguimiento externos solos: cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o avisos activos)—; el resto del tiempo, así como tras dos días sin novedades (o 12 horas en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora. Puedes activar alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar con SPX ni con Shopee en tu nombre.

## Preguntas sobre el seguimiento de SPX Express Brasil

### ¿SPX Express es lo mismo que Shopee?

Es su red propia de reparto: Shopee define a SPX Express como su logística oficial en Brasil. Detrás de `spx.com.br` opera SPX Express Brasil Ltda., con más de 100 hubs logísticos y 11 centros de distribución en el país.

### ¿Cómo rastrear un paquete de SPX Express?

Desde la app de Shopee en Brasil (`Eu` > `Minhas Compras` > el pedido > `Status de Envio`), pegando el código `BR` en Peek o usando el buscador de `spx.com.br`.

### ¿Qué significa Pedido em rota de entrega para seu endereço?

Que el paquete ha salido a reparto hacia tu domicilio en Brasil y SPX prevé entregarlo en las próximas horas. Si tu pedido incluye palabra clave (*palavra-chave*), tenla lista para el repartidor.

### ¿Cuánto tarda SPX Express en entregar un pedido en Brasil?

Shopee señala de 1 a 25 días hábiles desde la recogida para compras a vendedores en Brasil, y habitualmente de 30 a 50 días naturales en pedidos internacionales. La fecha estimada concreta aparece en la página de tu pedido.

### ¿Se puede hablar directamente con SPX Express?

Shopee indica que no para consultas de seguimiento o entrega: debes acudir a `Chat Shopee` en la aplicación, de lunes a viernes de 8:00 a 22:00 y fines de semana de 8:00 a 20:00 (hora de Brasilia), o enviar su formulario web.

:::sources
- [SPX Express: Inicio](https://spx.com.br/br) – logística integrada de Shopee en Brasil, más de 100 hubs y 11 centros de distribución, SPX Express Brasil Ltda. y WhatsApp de socios/agencias
- [SPX Express: Seguimiento](https://spx.com.br/track) – buscador público de seguimiento de SPX en Brasil
- [SPX Express: Sobre Nós](https://spx.com.br/br/help-center/about-us.html) – seguimiento en la app de Shopee y red en todo Brasil
- [SPX Express: Encontre uma Agência Shopee](https://spx.com.br/service-point) – mapa de agencias y opción Retirar um pacote
- [Shopee: O que é a SPX Express?](https://help.shopee.com.br/portal/4/article/197188) – logística propia y oficial de Shopee en Brasil
- [Shopee: O que é Shopee Xpress (SPX)?](https://help.shopee.com.br/portal/4/article/76163) – recogida, centros de distribución y socios logísticos
- [Shopee: Posso entrar em contato com os parceiros logísticos da Shopee?](https://help.shopee.com.br/portal/4/article/76264) – modalidades de envío, sin contacto directo con el transportista y 3 intentos
- [Shopee: Como faço para verificar o status do meu pedido?](https://help.shopee.com.br/portal/4/article/76323) – códigos de Correios
- [Shopee: Quais são as opções de envio disponíveis na Shopee?](https://help.shopee.com.br/portal/4/article/76335) – envío automático del código de seguimiento
- [Shopee: Como eu acho a data prevista de entrega para o meu pedido?](https://help.shopee.com.br/portal/4/article/81887) – fecha prevista y apartado Status de Envio
- [Shopee: Como encontro o ID do pedido da minha compra?](https://help.shopee.com.br/portal/4/article/76194) – identificador ID do pedido
- [Shopee: O que é logística do vendedor?](https://help.shopee.com.br/portal/4/article/132039) – reparto gestionado por el vendedor
- [Shopee: Como minha encomenda do exterior será entregue quando chegar ao Brasil?](https://help.shopee.com.br/portal/4/article/76363) – entrega con Correios en pedidos del exterior
- [Shopee: Por que meu pedido está parado na alfândega do Brasil?](https://help.shopee.com.br/portal/4/article/153683) – Correios o Anjun Courier, documentos de identidad y formulario RCV
- [Shopee: Quando receberei meu pedido?](https://help.shopee.com.br/portal/4/article/76349) – 1 a 25 días hábiles nacionales y 30 a 50 días naturales internacionales
- [Shopee: Posso agendar a data e hora de entrega do meu pedido?](https://help.shopee.com.br/portal/4/article/76333) – sin cita previa, intentos y reembolso automático
- [Shopee: Posso alterar meu endereço de entrega depois que meu pedido for feito?](https://help.shopee.com.br/portal/4/article/76279) – cambio único de dirección antes de confirmar el pago
- [Shopee: O que é a palavra-chave no meu pedido?](https://help.shopee.com.br/portal/4/article/127204) – funcionamiento de la palabra clave en la entrega
- [Shopee: O que fazer quando não estiver em casa para informar a palavra-chave?](https://help.shopee.com.br/portal/4/article/127206) – entrega de la clave a una persona de confianza
- [Shopee: O que fazer se minha palavra-chave não funcionar corretamente?](https://help.shopee.com.br/portal/4/article/127205) – no dar la clave antes de recibir el bulto y rechazar paquetes ajenos
- [Shopee: Retirada de pacotes na Agência Shopee](https://help.shopee.com.br/portal/4/article/164642) – opción Retire Fácil, avisos, PIN y fecha tope
- [Shopee: Sobre a Agência Shopee](https://help.shopee.com.br/portal/4/article/164639) – retirada con PIN y código de seguimiento, 7 días hábiles y desvío a domicilio
- [Shopee: Meu pedido está sem atualização no rastreamento](https://help.shopee.com.br/portal/4/article/223973) – días en camión de transferencia entre estados y margen de 5 días tras la fecha prevista
- [Shopee: Por que meu pedido está parado ou mudou de rota?](https://help.shopee.com.br/portal/4/article/159363) – desvíos aparentes entre centros
- [Shopee: Por que meu código de rastreio não está funcionando?](https://help.shopee.com.br/portal/4/article/76369) – causas de un código sin registros aún
- [Shopee: O que devo fazer se não receber meu pedido até a data prevista de entrega?](https://help.shopee.com.br/portal/4/article/76321) – solicitud de reembolso y plazo de 2 días para el vendedor
- [Shopee: O que devo fazer se não receber um pedido, mas o status está como Entregue?](https://help.shopee.com.br/portal/4/article/76207) – comprobaciones y averiguación acareação
- [Shopee: Como faço para solicitar devolução/reembolso?](https://help.shopee.com.br/portal/4/article/76204) – plazo de 7 días desde la entrega en Entrega Padrão
- [Shopee: Como entro em contato com o Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/78354) – ruta al Chat Shopee en la app
- [Shopee: Qual horário do Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/141507) – horario de Brasilia y formulario de solicitudes
- [Shopee: O entregador do meu pedido teve uma má conduta](https://help.shopee.com.br/portal/4/article/224511) – aviso sobre conducta del repartidor
- [Shopee: Como faço para detectar um golpe?](https://help.shopee.com.br/portal/4/article/76330) – canales oficiales shopee.com y pago del envío siempre en la app
- [Shopee: Por que não consigo concluir minha compra internacional?](https://help.shopee.com.br/portal/4/article/141561) – bloqueo por CPF irregular
- [Shopee: Como solicitar devolução/reembolso ou realizar o cancelamento da minha compra internacional?](https://help.shopee.com.br/portal/4/article/134867) – reembolso internacional e impuestos de importación no reembolsables
- [Receita Federal: Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – tramos PRC desde el 12 de mayo de 2026, ICMS del 17 % al 20 % y alerta sobre boletos falsos
- [Receita Federal: Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – cobro de impuestos en el checkout
- [Receita Federal: Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – Shopee certificada en el PRC
- [Consumidor.gov.br: Início](https://www.consumidor.gov.br/pages/principal/) – respuesta en 10 días y cuenta gov.br plata u oro
- [Consumidor.gov.br: Empresas participantes](https://www.consumidor.gov.br/pages/principal/empresas-participantes) – Shopee Brasil participante
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detección de códigos BR y enlaces spx.com.br, estados y flota de SPX, hitos ocultos y privacidad
:::
