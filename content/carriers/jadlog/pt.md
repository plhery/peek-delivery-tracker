---
title: Rastreio Jadlog: códigos de 14 dígitos, Pickup e Predict
description: Segue uma encomenda Jadlog no Brasil: números de 14 dígitos, estados em português e Geopost, pontos Pickup, SMS Predict de 1 hora e contactos.
slug: rastreio-jadlog
published: 2026-11-24
updated: 2026-11-24
---

A Jadlog é um dos maiores operadores brasileiros de encomendas expresso e logística para comércio eletrónico e a filial brasileira do grupo europeu Geopost (juntamente com DPD, Chronopost, SEUR, BRT e Speedy). No Brasil conta com mais de 500 franquias que cobrem cerca de 5 000 municípios, mais de 7 000 veículos e mais de 4 000 lojas parceiras Jadlog Pickup. Podes acompanhar um envio no campo `RASTREIE SUA ENCOMENDA` na [página inicial da Jadlog](https://www.jadlog.com.br/jadlog/home), ou colar o código de 14 dígitos na caixa acima.

## Número de rastreio Jadlog: formato de 14 dígitos e onde o encontrar

Procura o código do envio (*código de rastreio* ou *remessa*) no e-mail de expedição da loja online, no ecrã da encomenda no marketplace ou na etiqueta. Os números de rastreio da Jadlog têm 14 dígitos consecutivos:

| Formato (inventado) | O que é |
| --- | --- |
| `48291736405821` | Número de encomenda Jadlog de 14 dígitos. Partilha o formato de 14 dígitos com transportadoras europeias da Geopost/DPD como [DPD Alemanha](carrier:dpd-de), [Chronopost](carrier:chronopost), [SEUR](carrier:seur) e [BRT](carrier:brt). |

O Peek acompanha apenas pelo número de 14 dígitos e nunca pede CPF. Se o código tiver 15 dígitos começados por `8880`, pertence à [J&T Express Brazil](carrier:j-and-t-br); um código `BR` de 15 caracteres pertence à [SPX Express Brazil](carrier:spx-br); um código `AJ` de 17 caracteres pertence à [Anjun Logistics](carrier:anjun); e um código de 13 caracteres terminado em `BR` pertence aos [Correios](carrier:correios-br). Consulta [formatos de números de rastreio](guide:tracking-number-formats) para outros formatos.

## O que significam os estados de rastreio Jadlog

Como a Jadlog integra a Geopost, os registos aparecem em português no site da Jadlog ou em inglês quando lidos através da API de rastreio da Geopost.

| A Jadlog / Geopost mostra | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Order created`, `COLETA SOLICITADA` | O vendedor criou o envio eletronicamente e pediu a recolha; a Jadlog ainda não registou a encomenda física. | «Etiqueta criada» |
| `EMISSAO` | A Jadlog emitiu o documento de transporte e recebeu a encomenda na sua rede. | «Aceite pela transportadora» |
| `Em transferência` | A encomenda desloca-se entre franquias Jadlog, centros de triagem ou linhas de transporte pelo Brasil. | «Em trânsito» |
| `Em rota de entrega` | Um estafeta local carregou a encomenda e saiu para a entregar na tua morada. | «Em distribuição» |
| `ENTREGUE` | A Jadlog registou a encomenda como entregue. | «Entregue» |

Quando uma loja imprime etiquetas em lote antes de a carrinha da Jadlog recolher os volumes, `Order created` ou `COLETA SOLICITADA` pode permanecer como único evento durante dois dias úteis. Assim que surge `Em transferência`, longas viagens rodoviárias entre estados brasileiros também podem gerar pausas de vários dias antes do registo na franquia de destino.

## Serviços Jadlog: Predict, pontos Pickup e exportação internacional

A Jadlog apresenta os seus principais serviços em [jadlog.com.br/jadlog/servicos](https://www.jadlog.com.br/jadlog/servicos), [Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) e nas páginas dedicadas:

- **Jadlog.com e Jadlog Package:** entrega de comércio eletrónico (Jadlog.com aceita encomendas até 30 kg; Jadlog Package cobre envios rodoviários B2C e B2B).
- **Jadlog Rodo, RodoEconômico, Cargo e DOC:** carga rodoviária mais pesada até 50 kg (Rodo), serviço rodoviário económico, carga aérea B2B e transporte de documentos.
- **[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict):** disponível em áreas metropolitanas e de elevado volume, enviando uma SMS automática com um intervalo de entrega de 1 hora para saberes quando esperar o estafeta.
- **[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup):** mais de 4 000 lojas parceiras no Brasil (alcance superior a 90% da população) para levantamento, entrega e devoluções de comércio eletrónico (*Reversa*), com avisos por SMS e e-mail.
- **[Jadlog Internacional](https://www.jadlog.com.br/jadlog/internacional):** exportação a partir do Brasil para 154 países (300 destinos) através da rede internacional da Geopost.

**O que a Jadlog não publica de forma geral.** O site da Jadlog não indica um número universal de tentativas de entrega, um prazo garantido nem um prazo fixo de guarda nos pontos Pickup para todas as lojas online. Verifica a SMS ou o e-mail enviado para a tua encomenda Pickup — ou o ecrã do pedido na loja — para saberes os prazos exatos de levantamento e nova entrega.

## Encomenda Jadlog parada, atrasada ou não recebida

Em compras online, o vendedor é o cliente contratual da Jadlog e trata de reembolsos, correções de morada e reenvios.

**Parada em `Order created` ou `COLETA SOLICITADA`.** O vendedor gerou os dados do envio, mas a Jadlog ainda não recolheu nem deu entrada à encomenda na rede. Pergunta à loja se já saiu do armazém.

**Sem novidades em trânsito (`Em transferência`).** Viagens rodoviárias entre estados brasileiros, fins de semana e acumulação em centros regionais provocam frequentemente pausas entre registos de franquias. Consulta [porque o rastreio deixa de atualizar](guide:tracking-not-updating).

**Marcada `ENTREGUE` mas não aparece.** Confirma primeiro com familiares, vizinhos e na *portaria* (receção/porteiro) do edifício, e verifica se selecionaste uma loja Jadlog Pickup ao finalizar a compra. Se ninguém a recebeu, contacta logo a loja para abrir uma investigação junto da Jadlog. Consulta [entregue mas não recebida](guide:delivered-not-received).

**Danificada, perdida ou devolvida.** Guarda a embalagem exterior e tira fotografias nítidas dos danos e da etiqueta antes de falar com o vendedor; consulta [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Contactar a Jadlog e opções para consumidores

A página [Atendimento da Jadlog](https://www.jadlog.com.br/jadlog/atendimento) não publica um número de telefone de apoio a destinatários nem um e-mail geral de apoio ao cliente. As tuas opções práticas são:

- **Contactar primeiro a loja online:** as lojas detêm o contrato de transporte (`CT-e` / fatura) necessário para abrir averiguações, alterar a morada ou autorizar um reembolso.
- **Localizador de franquias locais:** consulta a unidade da tua zona em [Jadlog Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) caso precises de informações sobre um depósito local.
- **Pedidos de privacidade e dados pessoais (LGPD):** a Jadlog disponibiliza um portal próprio em [jadlog.com.br/jadlog/lgpd](https://www.jadlog.com.br/jadlog/lgpd).
- **Mediação de consumo:** se um litígio com uma loja ou transportadora aderente não for resolvido diretamente, os consumidores no Brasil com conta gov.br prata ou ouro podem registar uma reclamação gratuita em [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), onde as empresas participantes se comprometem a responder no prazo de 10 dias.

> Desconfia de ligações por SMS, WhatsApp ou e-mail que peçam o pagamento de uma pequena «taxa de libertação» ou «taxa de nova entrega» para uma encomenda doméstica Jadlog. Confirma primeiro qualquer cobrança inesperada diretamente com a loja onde compraste. Consulta [SMS falsas de entregas](guide:delivery-scam-texts).

## Segue uma encomenda Jadlog com o Peek

Como várias transportadoras Geopost/DPD partilham números de 14 dígitos em todo o mundo, o [Peek](/) usa a tua localização para decidir a quem perguntar primeiro. No Brasil, o Peek pergunta primeiro à Jadlog entre os candidatos de 14 dígitos (antes das unidades europeias DPD, Chronopost, SEUR e BRT) e mostra «Jadlog tem esta encomenda.» assim que a Jadlog a confirma. Fora do Brasil, o Peek usa a Jadlog como último recurso — pergunta primeiro às transportadoras europeias Geopost e consulta a Jadlog se nenhuma tiver o número. Colar um link `jadlog.com.br/jadlog/captcha?cte=` identifica diretamente a Jadlog em qualquer lugar; caso contrário toca em «Alterar» ao adicionar ou em «Alterar transportadora» na página da encomenda e seleciona Jadlog (pesquisar «DPD Brazil» também a encontra).

O Peek consulta diretamente a API pública de rastreio da Geopost para a Jadlog sem pedir código postal nem CPF. Mostra a etapa atual («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Em distribuição», «Entregue»), o histórico resumido de registos (em inglês da API Geopost ou em português quando vindo do rastreio local da Jadlog) e a hora de entrega. Datas estimadas, peso, moradas de pontos Jadlog Pickup e nomes ou assinaturas do destinatário não aparecem no Peek.

Enquanto manténs a encomenda aberta no Peek, ou no dia da entrega com as notificações ativas, o Peek verifica até de 10 em 10 minutos; no dia em que sai para distribuição, de 2 em 2 minutos a partir das 6 h locais, enquanto o rastreio da Jadlog responder. Nos restantes casos verifica de hora em hora. Após 12 horas só em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora em hora seja como for, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Jadlog não responde e o Peek acompanha a encomenda apenas por serviços de rastreio, verifica de hora em hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia de distribuição enquanto a mantiveres aberta ou com notificações ativas.

Quando o último registo tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-o com a data desse registo, como «Sem atualizações desde seg 12 out». A página mostra então: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Jadlog.» Após três semanas sem novos registos, mostra: «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Jadlog.»

Podes escolher os alertas «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek apenas acompanha o rastreio e não pode agendar uma nova entrega nem contactar uma franquia Jadlog por ti.

## Perguntas sobre o rastreio Jadlog

### Como é um número de rastreio Jadlog?

Um número de encomenda Jadlog tem 14 dígitos (por exemplo `48291736405821`, dígitos inventados). Podes segui-lo com esse código de 14 dígitos no Peek ou na [página inicial da Jadlog](https://www.jadlog.com.br/jadlog/home).

### O que é o Jadlog Predict?

O [Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) é um serviço automático de notificações por SMS em zonas metropolitanas e de alto volume que informa os destinatários da janela prevista de entrega de 1 hora.

### Como funciona o Jadlog Pickup?

O [Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup) usa mais de 4 000 lojas parceiras no Brasil onde os destinatários podem levantar encomendas ou entregar devoluções. A Jadlog envia avisos por SMS e e-mail para acompanhar a encomenda.

### Porque está a Jadlog ligada à DPD e à Geopost?

A Jadlog faz parte do grupo europeu Geopost juntamente com DPD, Chronopost, SEUR, BRT e Speedy. Por isso utiliza números de encomenda Geopost de 14 dígitos e liga-se à rede internacional da Geopost em 154 países.

:::sources
- [Jadlog: Home](https://www.jadlog.com.br/jadlog/home) – rastreio `RASTREIE SUA ENCOMENDA` e resumo da rede no Brasil
- [Jadlog: Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) – integração no grupo Geopost, 500+ franquias, 4 000+ parceiros Pickup, 7 000+ veículos, 7 000 colaboradores diretos e indiretos, 154 países
- [Jadlog: Produtos e Serviços](https://www.jadlog.com.br/jadlog/servicos) – Jadlog.com (até 30 kg), Package, Rodo (até 50 kg), RodoEconômico, Cargo, DOC
- [Jadlog: Pickup](https://www.jadlog.com.br/jadlog/pickup) – 4 000+ lojas parceiras, alcance >90% da população, avisos SMS/e-mail e devoluções Reversa
- [Jadlog: Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) – alertas SMS automáticos com intervalo de entrega de 1 hora
- [Jadlog: Internacional](https://www.jadlog.com.br/jadlog/internacional) – exportação para 154 países / 300 destinos via Geopost
- [Jadlog: Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) – pesquisa de franquias e unidades no Brasil
- [Jadlog: Atendimento](https://www.jadlog.com.br/jadlog/atendimento) – âmbito da página pública de apoio
- [Jadlog: LGPD](https://www.jadlog.com.br/jadlog/lgpd) – portal de privacidade e dados pessoais
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – plataforma pública de mediação de consumo (resposta em 10 dias, conta gov.br prata ou ouro)
- [Universal Parcel Scraper: jadlog](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jadlog) – deteção de 14 dígitos e prioridade no Brasil (alias `DPD Brazil`), regra de link `captcha?cte=`, API pública de rastreio da Geopost, mapeamento de estados e frequência de atualização
:::
