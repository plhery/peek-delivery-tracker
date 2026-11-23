---
title: Seguimento Andreani: números e estados na Argentina
description: Acompanha uma encomenda Andreani na Argentina: formato dos números de 15 dígitos, significado dos estados em espanhol e o que fazer se parar.
slug: seguimento-andreani
published: 2026-11-23
updated: 2026-11-23
---

A Andreani é uma transportadora de encomendas na Argentina. O catálogo do Peek lista-a na Argentina e no Brasil, também como Correo Andreani e Grupo Logístico Andreani. Se compraste online, a loja pode enviar com a Andreani: o número de seguimento está no e-mail de envio da loja ou na página da encomenda. Cola o número, ou uma ligação de seguimento de andreani.com, na caixa acima. O seguimento próprio da Andreani fica em andreani.com; o Peek lê esse seguimento e esta página explica cada passo em português.

## Número de seguimento Andreani: que aspeto tem

Os números da Andreani que o Peek reconhece têm 15 dígitos e começam por `36000`, `31000` ou `30000`. Com dígitos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `360004829103756` | `36000` e mais 10 dígitos: intervalo da Andreani, mas correspondência fraca. A [BRT](carrier:brt) e a Yunda também usam 15 dígitos, por isso o Peek pergunta tanto à Andreani como à BRT. |
| `310004829103756` | `31000` e mais 10 dígitos: também do intervalo da Andreani. |
| `300004829103756` | `30000` e mais 10 dígitos: também do intervalo da Andreani. |
| `739402815634281` | 15 dígitos com outro início. O Peek não associa à Andreani: [FedEx](carrier:fedex), DPD França, [Hermes](carrier:hermes-de), BRT e Yunda também usam 15 dígitos. |

Espaços não fazem diferença quando colas um número no Peek.

**Outras referências.** O seguimento da Andreani também aceita outras referências: números Andreani com letras e referências próprias dos remetentes. O Peek não consegue saber pela forma que pertencem à Andreani, por isso escolhe Andreani manualmente (vê «Seguir uma encomenda Andreani com o Peek» abaixo).

**Uma ligação de seguimento.** Uma ligação andreani.com com `/envio/` e o número também funciona: o Peek lê o número da ligação.

Não encontras o número? Vê [como encontrar um número de seguimento](guide:find-tracking-number).

## Estados de seguimento Andreani explicados

O seguimento da Andreani agrupa as linhas sob cabeçalhos em espanhol. Os quatro que o Peek conhece são `Pendiente de ingreso` (a aguardar entrada), `Ingresado` (recebida), `En camino` (a caminho) e `Entregado` (entregue). Cada linha é uma frase curta dirigida a ti, com a sucursal (`sucursal`) e a localidade quando existe. As linhas que o Peek conhece, pela ordem habitual:

| A Andreani diz | O que significa para ti | Estado no Peek |
| --- | --- | --- |
| `Tu envío se encuentra pendiente de ingreso.` | O remetente anunciou a encomenda à Andreani, mas a Andreani ainda não a recebeu. | «Etiqueta criada» |
| `¡Ya tenemos tu envío! El circuito comienza en C.a.b.a., Buenos Aires. Pronto lo enviaremos a la sucursal encargada de la entrega.` | A Andreani já tem a tua encomenda. O trajeto começa na localidade e província indicadas e segue depois para a sucursal encarregada da entrega. | «Aceite pela transportadora» |
| `Tu envío salió de la sucursal Monserrat (av Belgrano), en la localidad C.a.b.a. de la provincia Buenos Aires.` | Saiu da sucursal indicada, nessa localidade e província. | «Em trânsito» |
| `Tu envío se encuentra en camino a la sucursal La Plata (calle 2).` | Vai a caminho da sucursal indicada, que fará a entrega. | «Em trânsito» |
| `La sucursal La Plata (calle 2) ya está procesando tu envío. ¡Pronto tendrás novedades!` | A sucursal de entrega já recebeu a encomenda e está a processá-la. Em breve haverá novidades. | «Em trânsito» |
| `Hoy te visitaremos para entregarte tu envío.` | A Andreani passa hoje para fazer a entrega. | «Em distribuição» |
| `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!` | Entregue. | «Entregue» |

As sucursais e localidades nesta tabela são apenas exemplos: as tuas linhas mostram as do teu percurso. `C.a.b.a.` é a Cidade Autónoma de Buenos Aires (*Ciudad Autónoma de Buenos Aires*).

O Peek mostra cada linha nas palavras espanholas da Andreani, sob uma etapa do Peek em português. Uma linha que o Peek ainda não conheça assume a etapa do cabeçalho onde aparece: qualquer linha sob `En camino` mostra «Em trânsito». O Peek remove números de telefone e de documentos de identidade das linhas. Mais em [o que significam os estados de seguimento](guide:tracking-statuses).

## Como a Andreani entrega na Argentina

As linhas acima descrevem o percurso normal:

1. **Recebida.** A Andreani recebe a encomenda no ponto inicial.
2. **Para a sucursal de entrega.** Envia-a para a `sucursal encargada de la entrega`. A linha iniciada por `Tu envío se encuentra en camino a la sucursal` indica qual é.
3. **Na sucursal de entrega.** Quando diz que essa sucursal `ya está procesando tu envío`, a encomenda chegou lá.
4. **Dia de entrega.** `Hoy te visitaremos para entregarte tu envío.` significa que hoje alguém leva a encomenda à morada.
5. **Entregue.** `Ya entregamos tu envío.` encerra o trajeto.

O Peek não conseguiu ler o site da Andreani, pelo que esta página não inclui prazos de entrega, regras para entregas falhadas nem condições ou tempo de levantamento em sucursal. Pergunta à loja ou à Andreani. Vê também [entregas falhadas](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

## Seguimento Andreani parado, encomenda perdida ou devolvida

**Apenas `Pendiente de ingreso`.** O remetente criou o envio, mas ainda não entregou a encomenda à Andreani. O Peek mostra «Etiqueta criada». Pergunta à loja se já expediu o pedido.

**Nenhum resultado.** O seguimento da Andreani pode ainda não conhecer o número. Guardada na Andreani, a encomenda mostra «À espera de Andreani» e o Peek continua a perguntar. Se nada aparecer após alguns dias, confirma o número com a loja. Vê [porque o seguimento para de atualizar](guide:tracking-not-updating).

**Sem novidades há dias.** Quando a linha mais recente tem quatro dias ou mais e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a: «Sem atualizações há quatro dias». É frequente não haver movimentos ao fim de semana. Se nada mudar, fala com a loja.

**Entregue, mas não chegou nada.** A última linha é `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!`, mas não tens a encomenda. Pergunta a quem está na morada, aos vizinhos e ao pessoal do prédio, e depois à loja. Vê [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou danificada.** Fala primeiro com a loja: foi ela que contratou a entrega com a Andreani. Vê [encomendas perdidas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

**Devolvida.** A tabela de linhas da Andreani no Peek ainda não tem uma linha própria de devolução. Se a encomenda voltar ao remetente, a linha aparece na mesma nas palavras da Andreani. Pede à loja reembolso ou novo envio. Vê [devolvida ao remetente](guide:returned-to-sender).

**Se a loja não ajudar.** Se uma empresa onde compraste não cumpre o prometido, podes apresentar reclamação à *Defensa del Consumidor*, o organismo argentino de proteção ao consumidor. É gratuita e online, através da *Ventanilla Federal Única de Defensa del Consumidor*. Precisas do teu DNI ou passaporte, telefone ou e-mail para resposta, nome e localidade da empresa e os dados básicos da compra. A *Defensa del Consumidor* encaminha a reclamação para o serviço competente e envia-te um número de processo por e-mail.

## Apoio ao cliente e contacto da Andreani

Como o Peek não conseguiu ler o site da Andreani, esta página não indica telefone, e-mail nem horários: o Peek só lista contactos lidos em páginas oficiais. O site da Andreani é andreani.com.

Numa compra online, pergunta primeiro à loja: foi ela que marcou a entrega e tem os dados do envio.

## Seguir uma encomenda Andreani com o Peek

Um número de 15 dígitos não chega para o [Peek](/) saber só pela forma que é da Andreani. Cola-o na mesma: o Peek acompanha mais de 4 500 transportadoras.

**Um número que começa por `36000`, `31000` ou `30000`.** O Peek pergunta à Andreani e à BRT em simultâneo e mostra entretanto «A verificar serviços de seguimento…». Se a Andreani tiver a encomenda, a linha abaixo do número indica Andreani com «tem esta encomenda» e o Peek regista-a lá. Caso contrário, mostra «ainda não encontrada · o Peek continua a verificar». Adiciona-a na mesma: a encomenda mostra «Provavelmente Andreani» e «À espera do primeiro registo de Andreani», e o Peek continua a perguntar à Andreani de 6 em 6 horas após as primeiras horas na app para iPhone ou Android ou na tua conta. Numa consulta sem conta, o Peek verifica durante as primeiras 6 horas, depois quando abres a ligação (no máximo de 6 em 6 horas e, após os dois primeiros dias, no máximo uma vez por dia) e, com notificações ativas, diariamente até 30 dias após a consulta. Passada uma semana sem novidades, sugere confirmar o número com o remetente ou escolher outra transportadora.

**Uma ligação andreani.com.** Cola a ligação de seguimento com `/envio/`. O Peek indica logo Andreani («Transportadora detetada») e acompanha o número da ligação, incluindo referências com letras.

**Qualquer outro número.** Escolhe Andreani manualmente: toca em «Alterar» e procura Andreani em «Todas as transportadoras» ou na pesquisa. Num número começado por `36000`, `31000` ou `30000`, aparece também em «Corresponde a este número». Numa encomenda já adicionada, usa «Alterar transportadora». Para a Andreani, o Peek aceita de 6 a 30 letras e dígitos com pelo menos um dígito; fora disso, indica que a Andreani «não emite números como este».

**O que o Peek mostra.** O Peek lê o seguimento da Andreani diretamente, a mesma consulta feita pela página oficial. Para cada linha mostra o texto espanhol da Andreani, a etapa (tabela acima), a sucursal e a hora. As horas da Andreani seguem o relógio da Argentina, que tem um único fuso horário e sem mudança de hora; o Peek mostra-as no fuso horário do teu dispositivo. O seguimento da Andreani mostra ainda nome do remetente, nome do motorista, observações, posição no mapa, barra de progresso e estimativa de entrega: o Peek deixa isso de fora e por isso não mostra data prevista de entrega.

**Quando a Andreani não responde.** O Peek consulta serviços de seguimento — ParcelsApp, Ship24 e 17TRACK por ordem — e apresenta os textos de cada serviço. O Peek não conhece entregas passadas da Andreani a outra transportadora, pelo que acompanha a encomenda na Andreani até ao fim.

**Verificações e alertas.** O Peek verifica até de 10 em 10 minutos, ou de 2 em 2 a partir das 6h locais no dia em que a Andreani regista a encomenda em distribuição. Uma encomenda sem novidades há dois dias, ou há 12 horas em «Etiqueta criada» ou «Aceite pela transportadora», é verificada de hora a hora. Pode notificar-te em «Todas as atualizações», «Só as importantes» ou «Só no dia da entrega». O Peek não pode contactar a Andreani nem a loja por ti.

## Perguntas sobre o seguimento Andreani

### Que aspeto tem um número de seguimento Andreani?

15 dígitos começados por `36000`, `31000` ou `30000`, como `360004829103756`. O seguimento da Andreani também aceita outras referências. Cola o número ou a ligação de andreani.com no Peek.

### O que significa «Pendiente de ingreso» no seguimento Andreani?

A aguardar entrada: o remetente anunciou a encomenda, mas a Andreani ainda não a tem. O Peek mostra «Etiqueta criada». Se ficar assim durante dias, pergunta à loja se já a enviou.

### O que significa «Hoy te visitaremos para entregarte tu envío.»?

A Andreani passa hoje para entregar a tua encomenda. O Peek mostra «Em distribuição» e verifica com mais frequência até surgir a entrega.

### Porque não atualiza o meu seguimento Andreani?

A loja pode ainda não ter entregue a encomenda, ou a Andreani não tem novo registo, algo comum ao fim de semana. O Peek continua a consultar a Andreani e assinala uma encomenda sem novidades há quatro dias; fala então com a loja.

### Como acompanhar uma encomenda Andreani em português?

Cola o número no Peek. As linhas da Andreani continuam em espanhol, mas o Peek coloca cada uma sob uma etapa em português, e a tabela acima explica cada frase.

:::sources
- [Universal Parcel Scraper: Andreani](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/andreani) – nomes e países, formato de 15 dígitos, outras referências, ligações andreani.com, consulta ao seguimento, linhas e cabeçalhos espanhóis e respetivas etapas, remoção de números de telefone e documentos, fuso horário da Argentina, o que o seguimento mostra e o que o Peek omite, sem data de entrega
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de 15 dígitos de outras transportadoras, que transportadoras o Peek consulta, serviços de seguimento, sem transferência para a Andreani
- [Argentina.gob.ar: Iniciar un reclamo ante Defensa del Consumidor](https://www.argentina.gob.ar/servicio/iniciar-un-reclamo-ante-defensa-del-consumidor) – reclamações contra uma empresa que não cumpriu o prometido, Ventanilla Federal Única, gratuito e online, requisitos, número de reclamação por e-mail
:::
