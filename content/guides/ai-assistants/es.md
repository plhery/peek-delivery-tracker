---
title: Sigue tus paquetes en ChatGPT, Claude o Gemini
description: Conecta Peek a ChatGPT, Claude o Gemini y pregúntale dónde están tus paquetes, o pídele que añada los números de seguimiento de tus correos. Quién puede y cómo.
slug: seguimiento-paquetes-chatgpt-claude-gemini
picture: Pip, el paquete con cara, asomado a la ventana de un chat en un portátil, donde una pregunta sobre los paquetes de esta semana recibe una lista corta como respuesta.
published: 2026-11-19
updated: 2026-11-21
---

Puedes seguir tus paquetes en Claude, ChatGPT o Gemini si los conectas a [Peek](/), un localizador de paquetes gratuito: el asistente podrá decirte dónde están tus paquetes y añadir los números de seguimiento que le des. Necesitas una cuenta de Peek, y cada asistente decide quién puede: Claude con cualquier plan, ChatGPT con un plan Plus, Pro, Business, Enterprise o Edu, Gemini para adultos en EE. UU. con una cuenta de Google personal, y OpenClaw para quien lo ejecuta. Peek marca la función como «Beta», así que aún puede cambiar.

## ¿Puede ChatGPT, Claude o Gemini seguir mis paquetes?

Sí, una vez que los conectas a Peek. Cada asistente pone sus propias condiciones:

| Asistente | Dónde funciona | Quién puede añadir Peek |
| --- | --- | --- |
| Claude | claude.ai y las apps de Claude para escritorio, iPhone y Android | Cualquier plan. Con un plan Team o Enterprise, primero un propietario añade Peek |
| ChatGPT | Se añade en un ordenador, en chatgpt.com o en la app de escritorio de ChatGPT | Un plan Plus, Pro, Business, Enterprise o Edu, no Free ni Go. En un espacio de trabajo, puede que un administrador tenga que añadir o permitir Peek primero |
| Gemini | Se conecta en gemini.google.com y luego funciona también en la app de Gemini | 18 años o más, en EE. UU., con una cuenta de Google personal y el historial activado, en inglés |
| OpenClaw | El ordenador o servidor donde lo ejecutas, en los chats de sus agentes | Quien lo ejecuta, desde un terminal en ese equipo |
| Otro asistente | Cualquiera que permita añadir un conector personalizado o un servidor MCP | Quien pueda añadir uno ahí |

Por parte de Peek, cualquier cuenta puede conectar uno (un invitado no), y Peek es gratis.

Home Assistant se conecta de otra forma, con la integración propia de Peek: consulta la guía [Sigue tus paquetes en Home Assistant](guide:home-assistant).

## ¿Cómo añado Peek a Claude, ChatGPT o Gemini?

Añades Peek en el asistente, con la dirección de Peek para asistentes: `https://peektracker.com/mcp`. Los pasos de abajo son [los del propio Peek](https://peektracker.com/es/assistants); con la sesión iniciada en la web de Peek (no en sus apps), también están en «Ajustes» → «Entregas» → «Asistentes», con un botón «Copiar dirección».

### Claude

:::steps
- Abre «Peek Parcel Tracker» | En el [directorio de conectores de Claude](https://claude.ai/directory/peek-parcel-tracker) o, en Claude, abre «Customize › Connectors», selecciona «+» y busca Peek.
- Selecciona «Connect» | Claude te pide entonces que inicies sesión en Peek.
- Inicia sesión en Peek | Elige qué puede hacer Claude y selecciona «Permitir».
- Activa Peek en un chat | Selecciona «+» abajo a la izquierda, luego «Connectors», y activa Peek.
:::

Con un plan Team o Enterprise, primero un propietario añade Peek: «Organization settings › Connectors», «Browse connectors» y luego «Add to your team».

Si Claude no muestra «Peek Parcel Tracker», añade Peek como conector personalizado en claude.ai o en la app de escritorio (el plan Free permite uno):

:::steps
- Abre «Customize › Connectors» | Selecciona «+ Add» y luego «Add custom connector».
- Ponle de nombre Peek | Pega `https://peektracker.com/mcp` y selecciona «Continue».
- Mantén los ajustes de inicio de sesión que encuentre Claude | Selecciona «Continue» y luego «Add».
:::

### ChatGPT

:::steps
- Abre «Plugins» | Está en la barra lateral de ChatGPT, o en «chatgpt.com/plugins». Selecciona «+» arriba a la derecha y luego «Add custom MCP server».
- Ponle de nombre Peek | Pega `https://peektracker.com/mcp` y elige «OAuth» como autenticación. Selecciona «I understand and want to continue» y luego «Create as a plugin».
- Inicia sesión en Peek | Elige qué puede hacer ChatGPT y selecciona «Permitir».
- Instálalo | Abre Peek en tus plugins y selecciona «Install plugin». Después escribe @ en un chat nuevo y elige Peek.
:::

### Gemini

La página de ayuda de Google dice que, para conectar una app personalizada como Peek, debes tener 18 años o más y estar en EE. UU., usar una cuenta de Google personal, no una del trabajo ni del centro educativo, y tener activado el historial («History»). La conectas en gemini.google.com, y después funciona también en la app de Gemini para móvil, solo en inglés. Google no menciona ningún plan de pago. La página de Google y la de Peek dicen las dos que añadas la dirección de Peek en «Settings» de Gemini, luego en «Connected Apps», en «Custom apps».

### OpenClaw

OpenClaw es un asistente que ejecutas tú mismo, en tu propio ordenador o servidor. Añades Peek desde un terminal en ese equipo, y luego sus agentes usan tu cuenta de Peek en todos los chats, hablen con quien hablen. Sus comandos `openclaw mcp set` y `openclaw mcp login` no se ejecutan mientras su Gateway está en marcha, así que primero detienes el Gateway:

:::steps
- Detén el Gateway | Ejecuta `openclaw gateway stop`.
- Añade Peek | Ejecuta `openclaw mcp set peek '{"url":"https://peektracker.com/mcp","transport":"streamable-http","auth":"oauth"}'`.
- Inicia sesión en Peek | Ejecuta `openclaw mcp login peek` y abre el enlace que muestra. Peek lo llama «OpenClaw MCP»: elige qué puede hacer y selecciona «Permitir».
- Vuelve a iniciar el Gateway | Ejecuta `openclaw gateway start` y luego pregunta a tu agente por tus paquetes.
:::

Si tu navegador está en otro dispositivo, la página que abre después de iniciar sesión no carga: termina con el código que aparece en su dirección, como explica OpenClaw en el terminal.

## ¿Qué le puedo pedir?

Pídeselo con tus propias palabras, por ejemplo:

- «Revisa mis correos y añade los números de seguimiento que encuentres». Funciona si el asistente puede leer tu correo por su cuenta, por ejemplo con su propio conector de Gmail: Peek nunca lee tu correo.
- «¿Qué llega esta semana?»
- «¿Dónde está mi paquete de IKEA?»
- «Archiva todo lo que ya se ha entregado».
- «¿Cuál fue mi último paquete entregado?»

También puede encontrar los paquetes que te necesitan, como uno que espera en un punto de recogida, y los que se esperan hoy, en tu zona horaria.

Para añadir paquetes, dale un número de seguimiento o la parte del correo sobre el envío: Peek sigue cada número de seguimiento que encuentra, hasta 10 a la vez, y solo guarda los números y el nombre indicado, nunca el texto. Un número de pedido no es un número de seguimiento: [así se distinguen](guide:find-tracking-number).

### Pedidos de Amazon

Los correos de Amazon no suelen llevar número de seguimiento. Por eso el asistente necesita el navegador en el que has iniciado sesión en Amazon, en un ordenador: Claude in Chrome o la extensión de navegador de ChatGPT. Desde su app del móvil, o con un navegador que ejecuta en sus propios servidores, no llega a tus pedidos, y se le indica que te lo diga en vez de intentarlo. Si un asistente puede navegar por la web de Amazon en la que tienes la sesión iniciada, se le indica que abra «Mis pedidos», luego «Track package», y que envíe el transportista y el «Tracking ID», nunca el número de pedido. Ahí solo puede leer: nunca comprar, cancelar, devolver ni cambiar nada, ni iniciar sesión por ti. Si no, busca tú el número: [nuestra guía de Amazon](guide:amazon-orders) te dice dónde. Un paquete que reparte la propia Amazon, sin ID de seguimiento, solo se sigue en tu cuenta de Amazon.

## ¿Puede el asistente borrar o cambiar mis paquetes?

Nunca puede borrar un paquete: eso lo haces tú en Peek. Lo demás lo decides tú: al conectarlo, la pantalla de Peek («¿Conectar Claude a Peek?», por ejemplo) muestra tu dirección de correo y una casilla por cada permiso que pide el asistente:

| Permiso en la pantalla de Peek | Lo que el asistente puede hacer entonces |
| --- | --- |
| «Ver tus paquetes: dónde está cada uno, cuándo está previsto y su historial» | Ver la lista de tus paquetes y abrir uno con sus escaneos |
| «Añadir paquetes a partir de los números de seguimiento que le des, y archivarlos» | Añadir paquetes y archivar uno |
| «Cambiar el nombre, el transportista o las notificaciones de tus paquetes, y restaurar los archivados» | Cambiar el nombre de un paquete o su transportista, desactivar o activar sus notificaciones o los correos de Peek, y restaurar uno archivado |

Desmarca lo que no quieras; «Permitir» sigue en gris hasta que marques al menos una casilla. Si más adelante pides algo que no permitiste, al asistente se le indica que vuelva a conectar Peek con ese permiso.

## ¿Qué ve el asistente y cómo lo desconecto?

Recibe lo que permitiste: nombres, remitentes, transportistas, números de seguimiento, estados, días previstos, puntos de recogida y escaneos de tus paquetes. Nunca el nombre del destinatario, ni el código postal o las cifras de teléfono que diste: un escaneo que nombra al destinatario, a quien firmó, un código o una puerta solo le llega como su etapa, por ejemplo «Entregado». El lugar de un escaneo queda como lo escribió el transportista, así que puede nombrar la localidad de entrega y su código postal. Después se aplican las condiciones de privacidad de su proveedor. El [aviso de privacidad](https://peektracker.com/privacy.html) de Peek explica qué guarda Peek de cada asistente que conectas.

Para desconectar un asistente, en la web de Peek:

:::steps
- Abre «Ajustes» | Desde el botón redondo de arriba con la primera letra de tu dirección de correo.
- Selecciona «Asistentes» | En «Entregas». Cada asistente muestra qué puede hacer y cuándo se usó por última vez.
- Selecciona «Desconectar» | Junto al asistente.
:::

También puedes quitar Peek en los ajustes del propio asistente. Una conexión que no se usa durante 60 días termina sola, y al borrar tu cuenta de Peek se borran sus asistentes conectados.

## ¿Por qué no puedo añadir Peek a mi asistente?

Normalmente es por el plan, el país o la cuenta:

- **Tu plan o tu país.** Mira la tabla de arriba y, con un plan de equipo o de espacio de trabajo, pregunta a su propietario o administrador.
- **No tienes cuenta de Peek.** Inicia sesión cuando el asistente te lo pida, con un código de seis cifras que Peek te envía por correo, o con Google o Apple si la pantalla lo ofrece. Tu primer inicio de sesión crea la cuenta.
- **Peek te pide que vuelvas a iniciar sesión.** Para permitir un asistente hace falta haber iniciado sesión hace poco, aunque ya tengas la sesión iniciada: inicia sesión y luego selecciona «Permitir».
- **«Este enlace no puede conectar ningún asistente».** Vuelve a empezar la conexión desde tu asistente.

Hay más casos en «Si algo falla», en la [página de asistentes de Peek](https://peektracker.com/es/assistants), y la [página de ayuda](https://peektracker.com/support.html) de Peek explica cómo contactar con Peek.

:::sources
- [Peek: Conectar Peek a Claude, ChatGPT o Gemini](https://peektracker.com/es/assistants) – quién puede, los pasos de cada asistente, qué pedirle, qué comparte Peek, si algo falla
- [Peek en GitHub: la configuración de cada asistente](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – pasos y nombres de los menús, iniciar sesión antes del último paso
- [Peek en GitHub: textos en español](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/es.json) – planes y lugares, permisos, Beta, si algo falla
- [Peek en GitHub: las herramientas de los asistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – lo que un asistente puede ver, añadir y editar, sin borrar, Amazon
- [Peek en GitHub: la pantalla de consentimiento](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/AssistantConsentScreen.tsx) – casillas, Permitir, inicio de sesión reciente
- [Peek en GitHub: Ajustes, Asistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – dirección, asistentes conectados, Desconectar
- [Peek: aviso de privacidad (en inglés)](https://peektracker.com/privacy.html) – lo que recibe un asistente, lo que guarda Peek, 60 días, borrado de la cuenta
- [Peek en GitHub: condiciones (en inglés)](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – gratis, sin tarifas ni suscripción
- [Peek: ayuda (en inglés)](https://peektracker.com/support.html) – iniciar sesión con un código de seis cifras
- [Centro de ayuda de Anthropic: Comienza con conectores personalizados usando MCP remoto](https://support.claude.com/es/articles/11175166-comienza-con-conectores-personalizados-usando-mcp-remoto) – pasos del conector personalizado, uno en el plan Free
- [OpenAI: Add custom MCP server (en inglés)](https://developers.openai.com/api/docs/guides/custom-mcp-server) – los pasos en ChatGPT, con los nombres de sus menús y botones
- [Centro de ayuda de OpenAI: Plugins in ChatGPT (en inglés)](https://help.openai.com/en/articles/20001256-plugins-in-chatgpt) – «Plugins» en la barra lateral, «Install plugin»
- [ChatGPT: Pricing (en inglés)](https://chatgpt.com/pricing/) – «Developer mode», del que depende un servidor MCP propio: Plus y Pro, no Free ni Go
- [Centro de ayuda de OpenAI: Connecting custom MCP servers in ChatGPT (en inglés)](https://help.openai.com/en/articles/12584461-connecting-custom-mcp-servers-in-chatgpt) – Business, Enterprise y Edu, y quién puede añadir uno en un espacio de trabajo
- [Ayuda de Aplicaciones de Gemini: Connect & manage custom apps for Gemini Apps (en inglés)](https://support.google.com/gemini/answer/17209137?hl=en&co=GENIE.Platform%3DDesktop) – quién puede conectar una app personalizada, historial activado, inglés, conexión en la web
- [OpenClaw: Transports and OAuth (en inglés)](https://docs.openclaw.ai/cli/mcp/transports) – guardar un servidor con OAuth, `openclaw mcp login`, terminar con el código, un inicio de sesión compartido
- [OpenClaw: Connect MCP servers (en inglés)](https://docs.openclaw.ai/tools/mcp) – los agentes usan las herramientas de un servidor guardado
- [OpenClaw en GitHub: los comandos MCP y el Gateway (en inglés)](https://github.com/openclaw/openclaw/blob/v2026.11.5/src/cli/mcp-cli-state-owner.ts) – `mcp set` y `mcp login` rechazados mientras el Gateway está en marcha
:::
