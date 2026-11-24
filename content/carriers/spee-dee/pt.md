---
title: Rastreio Spee-Dee Delivery: números SP, estados e apoio
description: Acompanha encomendas Spee-Dee Delivery Service no Centro-Oeste dos EUA: códigos SP de 18 e 20 caracteres, códigos reutilizados, cobertura e 800-862-5578.
slug: rastreio-spee-dee
published: 2026-11-24
updated: 2026-11-24
---

A Spee-Dee Delivery (*Spee-Dee Delivery Service, Inc.*) é uma transportadora familiar de segunda geração fundada em 1978 por Don Weeres como serviço de entregas sob pedido. Apresenta-se como líder indiscutível na entrega de última milha no Centro-Oeste americano, com rede de entrega no dia seguinte, oferecendo ainda carga fracionada LTL, armazenagem, balcões de envio presencial e EZR Returns ([speedeedelivery.com](https://speedeedelivery.com/)).

Podes acompanhar um envio na página [Track a Shipment da Spee-Dee](https://speedeedelivery.com/track-a-shipment/) ou colar o teu código de barras `SP` na caixa acima.

## Formatos de número de rastreio da Spee-Dee

O Peek reconhece os códigos de barras da Spee-Dee como `SP` seguido de 16 ou 18 dígitos:

| Formato (inventado) | O que é |
| --- | --- |
| `SP4829173605148291` | `SP` + 16 dígitos (18 caracteres no total) — código de barras padrão Spee-Dee; nota que a Spee-Dee recicla códigos de 16 dígitos ao longo do tempo. |
| `SP482917360514829173` | `SP` + 18 dígitos (20 caracteres no total) — formato mais longo de código de barras Spee-Dee. |

Ambos os formatos `SP` identificam a Spee-Dee com elevada confiança e não colidem com grandes transportadoras nacionais dos EUA como [UPS](carrier:ups), [FedEx](carrier:fedex) ou [USPS](carrier:usps). Se tiveres uma ligação `speedeedelivery.com/...?tracking=...`, o Peek extrai o código `SP` diretamente. Para outros formatos norte-americanos, consulta o guia de [formatos de números de seguimento](guide:tracking-number-formats).

## Área de serviço da Spee-Dee no Centro-Oeste

De acordo com os [mapas de cobertura da Spee-Dee](https://speedeedelivery.com/services/service-area-maps/), a Spee-Dee assegura entrega terrestre regular em **todo** o território de seis estados do Centro-Oeste e em cidades e códigos postais (*ZIP Codes*) selecionados de quatro estados vizinhos:

- **Cobertura estadual completa:** Illinois, Iowa, Minnesota, Dakota do Norte, Dakota do Sul e Wisconsin.
- **Cidades e ZIP Codes selecionados:** Kansas (incluindo os ZIPs alargados `660` e `666`), Michigan, Missouri (incluindo os ZIPs alargados `634–635`, `644–646` e `650–653`) e Nebraska.

Para tempos de trânsito, a página *Service Area Maps* apresenta um mapa *Days in Transit* por código postal de origem, e o ficheiro *Zip Transit File* lista os prazos para cada ZIP Code servido.

## O que significam os estados de rastreio da Spee-Dee

A tabela de progresso da Spee-Dee tem três colunas — `Date / Time`, `Location` e `Activity` — do mais recente para o mais antigo, acompanhada no topo por um bloco de resumo com `Status:`, `Delivered on:`, `Signed by:` e `Delivered to:` após a conclusão.

| Atividade Spee-Dee | O que significa |
| --- | --- |
| `ARRIVAL SCAN` | Registada num terminal ou centro de triagem Spee-Dee na rede terrestre do Centro-Oeste. |
| `OUT FOR DELIVERY` *(frequentemente seguido de um número de rota entre parênteses)* | Carregada na carrinha local Spee-Dee para entrega hoje ao domicílio ou empresa. |
| `Delivered` | Entrega concluída; o resumo na página da própria Spee-Dee regista também hora de entrega, morada e assinatura do destinatário quando recolhida. |
| `No packages were found matching the barcode supplied.` | O código de barras ainda não foi registado no sistema Spee-Dee ou tem um erro de escrita. |

### Códigos de barras de 16 dígitos reutilizados

A Spee-Dee reutiliza códigos de barras `SP` antigos de 16 dígitos em envios novos. Na página original da Spee-Dee, linhas de uma encomenda entregue anteriormente podem ainda aparecer abaixo dos registos da tua encomenda atual. Ao consultar um código reutilizado no site da Spee-Dee, observa as datas no topo da tabela e pára assim que encontrares um `Delivered` anterior de outro envio.

## Atrasos, registos em falta e reclamações

Alguns pontos ajudam quando o rastreio parece estranho:

- **Ainda sem registos:** se a página da Spee-Dee devolver `No packages were found matching the barcode supplied.`, a Spee-Dee ainda não registou a encomenda ou o código tem uma gralha. Confirma o código, pergunta ao vendedor se já entregou a encomenda à Spee-Dee ou lê [quando o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Limites em atualizações frequentes:** o servidor da Spee-Dee limita pesquisas por minuto e devolve temporariamente HTTP 503 se consultado repetidamente em sequência rápida. Esperar um minuto ou deixar o Peek verificar automaticamente evita esse limite.
- **Reclamações por não entrega ou danos:** o [formulário de apoio ao cliente Spee-Dee](https://speedeedelivery.com/customer-service/) inclui opções específicas **Claims – Non-Delivery** e **Claims – Damage** e pede que incluas a **morada completa de entrega** nos pedidos de rastreio. Vê também [entregue mas não recebida](guide:delivered-not-received) se aparecer entregue sem ter chegado.

## Acompanhar a Spee-Dee com o Peek

Cola qualquer código de barras Spee-Dee de 18 caracteres (`SP` + 16 dígitos) ou 20 caracteres (`SP` + 18 dígitos), ou uma ligação `speedeedelivery.com`, no Peek e o Peek reconhece logo a Spee-Dee. Se precisares de mudar a transportadora, usa «Alterar» ao adicionar ou «Alterar transportadora» no ecrã de seguimento.

O Peek lê diretamente a página de progresso da Spee-Dee através do [adaptador Spee-Dee em código aberto no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee), recorrendo a uma rede remota se o servidor da Spee-Dee recusar uma ligação direta. Num **código reutilizado**, conserva apenas as linhas desde a entrega anterior ocorrida um dia ou mais antes. Remove comentários HTML de depuração e o número de rota após `OUT FOR DELIVERY`, guardando cidade ou terminal e hora de cada registo. A Spee-Dee apresenta horas locais sem desfasamento: o Peek data cada registo no fuso do local (hora de Chicago para St. Cloud, Fargo, Milwaukee ou Sioux Falls, hora de Detroit para Detroit), um registo sem local no fuso vizinho, ou na hora de Nova Iorque, mostrando tudo no relógio do teu dispositivo. O rastreio Spee-Dee não fornece data estimada de entrega nem peso.

O Peek mapeia `ARRIVAL SCAN` para «Em trânsito», `OUT FOR DELIVERY` para «Em distribuição» e `Delivered` para «Entregue», lendo qualquer outra atividade através das suas regras partilhadas.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Spee-Dee apresenta `OUT FOR DELIVERY`, de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente, ou de 5 em 5 minutos quando a última verificação teve de abrir um navegador, enquanto o rastreio da Spee-Dee responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando a Spee-Dee não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Spee-Dee.», com a ligação «Pede ajuda a Spee-Dee» para a página de apoio ao cliente. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Spee-Dee.»

Nas preferências de notificações do Peek, escolhe «Todos os registos», «Passos importantes» ou «Só a entrega» para controlar os alertas.

## Contactar a Spee-Dee Delivery

A Spee-Dee refere na sua [página Customer Service](https://speedeedelivery.com/customer-service/) que quem telefona fala diretamente com uma pessoa em vez de um menu telefónico automático:

- **Apoio ao cliente gratuito:** `800-862-5578`
- **Telefone local:** `320-251-6697`
- **Fax:** `320-251-1846` (fax LTL: `320-240-7995`)
- **Formulário online de contacto e reclamações:** [Spee-Dee Customer Service](https://speedeedelivery.com/customer-service/) (inclui o código `SP` e a morada completa de entrega para questões de rastreio)
- **Morada física:** Spee-Dee Delivery Service Inc., 4101 Clearwater Road, St. Cloud, MN 56301
- **Morada postal:** P.O. Box 1417, St. Cloud, MN 56302
- **Sobre a empresa e rastreio:** [Sobre a Spee-Dee](https://speedeedelivery.com/about-us/) e [Track a Shipment](https://speedeedelivery.com/track-a-shipment/)

## Perguntas frequentes

### Porque mostra a minha página Spee-Dee uma entrega de há meses?

A Spee-Dee reutiliza códigos de barras `SP` de 16 dígitos ao longo do tempo, pelo que a página original pode listar um envio antigo já concluído abaixo dos registos da tua encomenda atual. O Peek deteta automaticamente esse limite e oculta as linhas de entregas anteriores.

### Que estados dos EUA serve a Spee-Dee Delivery?

A Spee-Dee cobre na totalidade Illinois, Iowa, Minnesota, Dakota do Norte, Dakota do Sul e Wisconsin, além de cidades e ZIP Codes selecionados no Kansas, Michigan, Missouri e Nebraska.

### A Spee-Dee Delivery mostra uma data estimada de entrega na página de rastreio?

Não. A página de rastreio Spee-Dee mostra registos de chegada a terminais, `OUT FOR DELIVERY` e detalhes de entrega final, sem data estimada. Podes consultar os dias habituais de trânsito terrestre por código postal em [Service Area Maps da Spee-Dee](https://speedeedelivery.com/services/service-area-maps/).

### O que devo incluir ao contactar a Spee-Dee sobre uma encomenda?

Tem à mão o teu número completo `SP` de 18 ou 20 caracteres e a morada completa de entrega — o formulário de apoio ao cliente da Spee-Dee pede expressamente a morada completa de entrega em pedidos de rastreio.

:::sources
- [Spee-Dee Delivery](https://speedeedelivery.com/)
- [Spee-Dee — About Us](https://speedeedelivery.com/about-us/)
- [Spee-Dee — Track a Shipment](https://speedeedelivery.com/track-a-shipment/)
- [Spee-Dee — Service Area Maps](https://speedeedelivery.com/services/service-area-maps/)
- [Spee-Dee — Customer Service](https://speedeedelivery.com/customer-service/)
- [universal-parcel-scraper Spee-Dee adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee)
:::
