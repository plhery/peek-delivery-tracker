---
title: Rastreamento Andreani: códigos e status na Argentina
description: Rastreie uma encomenda Andreani na Argentina: formato dos códigos de 15 dígitos, significado das frases em espanhol e o que fazer se parar de atualizar.
slug: rastreamento-andreani
published: 2026-11-26
updated: 2026-11-26
---

A Andreani é uma transportadora de encomendas da Argentina. O catálogo do Peek a lista na Argentina e no Brasil, também como Correo Andreani e Grupo Logístico Andreani. Se você comprou online, a loja pode enviar pela Andreani: o código de rastreamento fica no e-mail de envio da loja ou na página do pedido. Cole o código, ou um link de rastreamento de andreani.com, no campo acima. O próprio rastreador da Andreani fica em andreani.com. O Peek lê esse rastreador, e esta página explica cada etapa em português.

## Código de rastreamento Andreani: como ele é

Os códigos da Andreani que o Peek reconhece têm 15 dígitos e começam com 36000, 31000 ou 30000. Com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `360004829173654` | 36000 e mais 10 dígitos: faixa da Andreani, mas apenas como correspondência fraca. A [BRT](carrier:brt) e a Yunda também usam 15 dígitos, então o Peek consulta tanto a Andreani quanto a BRT. |
| `310004829173654` | 31000 e mais 10 dígitos: também na faixa da Andreani. |
| `300004829173654` | 30000 e mais 10 dígitos: também na faixa da Andreani. |
| `007394028156348` | 15 dígitos com outro início. O Peek não associa à Andreani. [FedEx](carrier:fedex), [DPD France](carrier:dpd-fr), [Hermes](carrier:hermes-de), BRT e Yunda também usam 15 dígitos. |

Espaços não fazem diferença quando você cola um código no Peek.

**Outras referências.** O rastreador da Andreani também aceita outras referências: códigos da Andreani com letras e referências próprias do remetente. O Peek não consegue saber pelo formato que são da Andreani, então escolha Andreani manualmente (veja "Acompanhe uma encomenda Andreani com o Peek" abaixo).

**Um link de rastreamento.** Um link de andreani.com com `/envio/` e o código também funciona: o Peek lê o código direto do link.

Não encontra o código? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da Andreani explicados

O rastreador da Andreani agrupa as linhas sob títulos em espanhol. Os quatro que o Peek conhece são `Pendiente de ingreso` (aguardando entrada), `Ingresado` (recebido), `En camino` (a caminho) e `Entregado` (entregue). Cada linha é uma frase curta dirigida a você, com a agência (`sucursal`) e a cidade quando houver. As linhas de um trajeto comum que o Peek reconhece, na ordem em que costumam surgir:

| A Andreani diz | O que significa para você | Status no Peek |
| --- | --- | --- |
| `Tu envío se encuentra pendiente de ingreso.` | O remetente informou o envio à Andreani, mas ela ainda não recebeu a encomenda. | "Etiqueta criada" |
| `¡Ya tenemos tu envío! El circuito comienza en C.a.b.a., Buenos Aires. Pronto lo enviaremos a la sucursal encargada de la entrega.` | A Andreani já está com a encomenda. O trajeto começa na cidade e província indicadas. Depois, ela segue para a agência responsável pela entrega. | "Recebida pela transportadora" |
| `Tu envío salió de la sucursal Monserrat (av Belgrano), en la localidad C.a.b.a. de la provincia Buenos Aires.` | Saiu da agência citada, naquela cidade e província. | "Em trânsito" |
| `Tu envío se encuentra en camino a la sucursal La Plata (calle 2).` | Está a caminho da agência citada, que será responsável pela entrega. | "Em trânsito" |
| `La sucursal La Plata (calle 2) ya está procesando tu envío. ¡Pronto tendrás novedades!` | A agência entregadora recebeu o pacote e está processando o envio. Em breve haverá novidades. | "Em trânsito" |
| `Hoy te visitaremos para entregarte tu envío.` | A Andreani passará hoje para entregar sua encomenda. | "Saiu para entrega" |
| `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!` | Entregue. | "Entregue" |

As agências e cidades na tabela são apenas exemplos: suas linhas trarão as agências da rota da sua encomenda. `C.a.b.a.` é a Cidade Autônoma de Buenos Aires (Ciudad Autónoma de Buenos Aires).

O Peek mostra cada linha no espanhol original da Andreani, sob uma das etapas em português do Peek. Uma linha que o Peek ainda não conhece herda a etapa do título em que aparece: qualquer linha sob `En camino` aparece como "Em trânsito". O Peek remove números de telefone e de documento de identidade das linhas. Veja mais em [significado dos status de rastreamento](guide:tracking-statuses).

## Como a Andreani entrega na Argentina

As linhas acima descrevem o trajeto habitual:

1. **Recebimento.** A Andreani recebe a encomenda no ponto inicial da rota.
2. **Rumo à agência entregadora.** Ela envia o pacote à agência responsável pela entrega (`sucursal encargada de la entrega`). A linha que começa com `Tu envío se encuentra en camino a la sucursal` informa qual é essa agência.
3. **Na agência entregadora.** A linha indicando que a agência `ya está procesando tu envío` (está processando seu envio) mostra que o pacote chegou lá.
4. **Dia da entrega.** `Hoy te visitaremos para entregarte tu envío.` indica que alguém levará o pacote ao endereço hoje.
5. **Entrega concluída.** `Ya entregamos tu envío.` encerra o trajeto.

O Peek não conseguiu ler o site da Andreani, então esta página não informa prazos de entrega, regras para tentativas sem sucesso nem detalhes sobre retirada em agência ou prazo de guarda. Pergunte à loja ou à Andreani sobre isso. Veja também [entregas perdidas](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

## Rastreamento Andreani parado, extraviado ou devolvido

**Apenas `Pendiente de ingreso`.** O remetente criou o envio, mas ainda não entregou o pacote à Andreani. O Peek exibe "Etiqueta criada". Pergunte à loja se o pedido já foi despachado.

**Nenhum resultado.** O rastreador da Andreani pode ainda não reconhecer o código. Classificada como Andreani, a encomenda mostra "Aguardando Andreani", e o Peek continua consultando. Se após dois dias a Andreani continuar sem reconhecer o código, o Peek pede para você conferi-lo, escolher outra transportadora ou excluir a encomenda: confira com a loja. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Sem novidades por vários dias.** Quando a linha mais recente tem quatro dias ou mais e a encomenda ainda está em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek marca o dia dessa linha: "Sem atualizações desde seg 12 out", com a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Andreani." e o link "Pedir ajuda a Andreani". Se nada mudar, fale com a loja. Após três semanas sem nova linha, o Peek também avisa: "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor."

**Marcada como entregue, mas não chegou.** A última linha diz `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!`, mas você não recebeu o pacote. Pergunte às pessoas no endereço, aos vizinhos e à portaria, e depois fale com a loja. Veja [encomenda marcada como entregue mas não recebida](guide:delivered-not-received).

**Extraviada ou danificada.** Fale primeiro com a loja, que contratou a entrega com a Andreani. Veja [encomendas extraviadas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

**Devolvida.** A tabela de linhas da Andreani no Peek ainda não inclui uma linha específica de devolução. Se a encomenda voltar ao remetente, a linha continua aparecendo nas palavras da Andreani. Fale com a loja sobre reembolso ou novo envio. Veja [encomenda devolvida ao remetente](guide:returned-to-sender).

**A loja não resolve.** Se a empresa onde você comprou não cumprir o prometido, é possível reclamar na Defensa del Consumidor, órgão argentino de proteção ao consumidor. O registro é gratuito e online pela Ventanilla Federal Única de Defensa del Consumidor. É preciso informar DNI ou passaporte, telefone ou e-mail para resposta, nome e cidade da empresa e os dados básicos da compra. A Defensa del Consumidor encaminha a reclamação ao órgão responsável e envia um número de protocolo por e-mail.

## Atendimento ao cliente e contato da Andreani

O Peek não conseguiu ler o site da Andreani, então esta página não traz telefone, e-mail nem horário de atendimento: o Peek lista apenas contatos verificados em páginas oficiais. O site da Andreani é andreani.com.

Para pedidos de loja virtual, fale primeiro com a loja: ela contratou o envio e tem os dados da remessa.

## Acompanhe uma encomenda Andreani com o Peek

Um código de 15 dígitos sozinho não basta para o [Peek](/) ter certeza de que é da Andreani. Cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras.

**Código iniciado por 36000, 31000 ou 30000.** O Peek consulta Andreani e BRT ao mesmo tempo (serviços de rastreamento e Ozon somente se nenhuma das duas conhecer o código), mostrando "Consultando serviços de rastreamento…" enquanto verifica. Se a Andreani tiver a encomenda, a linha abaixo do código indica Andreani dizendo "está com esta encomenda" e o Peek a classifica lá. Se não, aparece "ainda não encontrada · o Peek continua verificando". Adicione mesmo assim: a encomenda exibe "Provavelmente Andreani" e "Aguardando a primeira movimentação de Andreani", e o Peek continua consultando a Andreani a cada 6 horas depois das primeiras horas em encomendas no app de iPhone ou Android ou na sua conta. Em uma consulta sem conta, o Peek consulta nas primeiras 6 horas, depois quando você abre o link (no máximo a cada 6 horas, e no máximo uma vez por dia após os dois primeiros dias) e, se as notificações estiverem ativadas, diariamente até 30 dias após a consulta. Depois de uma semana sem retorno, pede que você confira o código com o remetente ou escolha outra transportadora.

**Link de andreani.com.** Cole o link de rastreamento com `/envio/`. O Peek identifica a Andreani imediatamente ("Transportadora detectada") e rastreia o código do link, inclusive referências com letras.

**Qualquer outro código.** Selecione Andreani manualmente: toque em "Trocar" e procure Andreani em "Todas as transportadoras" ou pela busca. Para códigos iniciados por 36000, 31000 ou 30000, ela também aparece em "Compatível com este código". Em uma encomenda já adicionada, use "Trocar transportadora". A consulta da Andreani no Peek aceita de 6 a 30 letras e dígitos, com pelo menos um dígito. Fora disso, a linha abaixo do código avisa que a Andreani "não emite códigos como este".

**O que o Peek mostra.** O Peek lê o próprio rastreador da Andreani, usando a mesma consulta feita pela página de rastreamento dela. Em cada linha exibe o texto em espanhol da Andreani, a etapa (tabela acima), a agência e o horário. Os horários da Andreani seguem o fuso horário da Argentina, único em todo o país e sem horário de verão; o Peek os converte para o fuso do seu dispositivo. O rastreador da Andreani também traz nome do remetente, nome do motorista, observações, posição no mapa, barra de progresso e previsão de entrega, que o Peek omite — por isso não mostra data prevista de entrega.

**Quando a Andreani não responde.** O Peek consulta serviços de rastreamento e mostra a cópia deles nas palavras deles. O Peek não registra transferência da Andreani para outra transportadora, então acompanha a encomenda na Andreani até o fim.

**Verificações e alertas.** Enquanto a encomenda estiver aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até a cada 10 minutos, e a cada 2 minutos a partir das 6h no horário da Argentina no dia em que a Andreani registra saída para entrega, enquanto o próprio rastreador da Andreani responder. Uma encomenda sem novidades há dois dias, ou há 12 horas em "Etiqueta criada" ou "Recebida pela transportadora", passa a ser verificada a cada hora, e a cada 6 horas após sete dias sem novidades em "Etiqueta criada". Ele pode avisar você sobre "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode falar com a Andreani nem com a loja por você.

## Perguntas sobre o rastreamento Andreani

### Como é um código de rastreamento da Andreani?

São 15 dígitos começando com 36000, 31000 ou 30000, como `360004829173654`. O rastreador da Andreani também aceita outras referências. Cole o código ou o link de rastreamento de andreani.com no Peek.

### O que significa "Pendiente de ingreso" no rastreamento da Andreani?

Aguardando entrada: o remetente informou o envio, mas a Andreani ainda não recebeu o pacote. O Peek mostra "Etiqueta criada". Se ficar assim por vários dias, pergunte à loja se a encomenda já foi enviada.

### O que significa "Hoy te visitaremos para entregarte tu envío."?

A Andreani passará hoje para entregar a encomenda. O Peek mostra "Saiu para entrega" e, enquanto a encomenda estiver aberta no Peek ou com notificações ativadas, verifica com mais frequência até aparecer a entrega.

### Por que meu rastreamento da Andreani não atualiza?

A loja pode ainda não ter entregue o pacote à transportadora, ou a Andreani pode estar sem nova leitura, o que é comum em fins de semana. O Peek continua consultando a Andreani e sinaliza a encomenda após quatro dias sem novidades. Depois, fale com a loja.

### Como entender o rastreamento da Andreani em português?

Cole o código no Peek. As frases da Andreani continuam em espanhol, mas o Peek agrupa cada uma em etapas em português, e a tabela acima explica o significado delas.

:::sources
- [Universal Parcel Scraper: Andreani](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/andreani) – nomes e países, formato de 15 dígitos, outras referências, links de andreani.com, consulta ao rastreador, frases e títulos em espanhol e suas etapas, remoção de telefones e documentos, fuso horário da Argentina, o que o rastreador exibe e o Peek omite, sem data de entrega
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de 15 dígitos de outras transportadoras, transportadoras consultadas pelo Peek, serviços de rastreamento, ausência de transferência na Andreani
- [Argentina.gob.ar: Iniciar un reclamo ante Defensa del Consumidor](https://www.argentina.gob.ar/servicio/iniciar-un-reclamo-ante-defensa-del-consumidor) – reclamações contra empresa que não cumpriu o prometido, Ventanilla Federal Única, gratuito e online, dados necessários, protocolo por e-mail
:::
