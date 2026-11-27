---
title: Rastreio Thailand Post: códigos TH S10, EMS e contato
description: Rastreie encomendas dos Correios da Tailândia (Thailand Post): códigos TH S10 de 13 caracteres, status domésticos e EMS, COD, alfândega e 1545.
slug: thailand-post-tracking
published: 2026-11-27
updated: 2026-11-27
---

O **Thailand Post** (*Thailand Post Co., Ltd.*, em tailandês *บริษัท ไปรษณีย์ไทย จำกัด*) é o operador postal nacional da Tailândia ([thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en)). Atendendo cartas nacionais, encomendas registradas, EMS, Same Day, LogisPost para cargas pesadas e pagamento na entrega (COD) em toda a Tailândia — além dos serviços internacionais EMS World, Registered, ePacket, Courier Post (até 200 kg) e Courier Post Plus para o mundo todo —, o Thailand Post oferece rastreamento público em [track.thailandpost.co.th](https://track.thailandpost.co.th/) e comunicados internacionais em [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update). Você pode consultar ali ou colar seu código `TH` de 13 caracteres na caixa acima.

## Códigos de rastreio Thailand Post (formato `TH` S10 de 13 caracteres)

Objetos nacionais e internacionais rastreados do Thailand Post usam códigos S10 de 13 caracteres da União Postal Universal (UPU): duas letras maiúsculas de serviço, 8 dígitos seriais, 1 dígito verificador S10 e o código do país `TH`:

| Formato (inventado) | O que é |
| --- | --- |
| `EE482917367TH` | Envio expresso EMS do Thailand Post de 13 caracteres (prefixo `EE` + 9 dígitos com dígito verificador S10 válido + `TH`). |
| `RR739184627TH` | Correspondência registrada ou pequeno pacote de 13 caracteres (prefixo `RR` + 9 dígitos com dígito verificador S10 válido + `TH`). |

EMS nacional, Registrado (`R...TH`), ePacket (`L...TH`) e prefixos domésticos (`E`, `R`, `C`, `W` etc. terminados em `TH`) seguem todos a mesma estrutura S10 de 13 caracteres. Cartas simples sem código de barras de 13 caracteres não possuem rastreamento on-line. Veja [formatos de código de rastreio](guide:tracking-number-formats).

## Significado dos status de rastreio Thailand Post

O rastreador do Thailand Post associa códigos numéricos `statusID` alinhados à UPU a descrições em inglês (e nomes de agências em tailandês), abrangendo leituras domésticas na Tailândia e atualizações EDI repassadas pelos correios de destino:

| Código e texto Thailand Post | O que significa para você | O que o Peek mostra |
| --- | --- | --- |
| `3` `Posting/Collection` | Postado no balcão do Thailand Post ou coletado do remetente. | "Recebida pela transportadora" |
| `9` `Arrival at outward office of exchange`, `15` `Departure From Outward OE`, `18` `Arrival at inward office of exchange` | Processado em um centro internacional de permuta para exportação ou importação. | "Em trânsito" |
| `23`–`27` `In transit`, `Item into sorting center`, `Arrival at post office` | Em deslocamento entre centros de triagem e chegada à agência local entregadora. | "Em trânsito" |
| `31` `Item out for physical delivery` | Saiu hoje com o carteiro do Thailand Post para entrega no endereço. | "Saiu para entrega" |
| `57` `Contact recipient` | O carteiro tentou ligar para o destinatário (registro sem deslocamento; mantém o estágio atual). | Mantém estágio anterior |
| `32` `Item arrival at collection point for pick-up (by recipient)` | Depositado em armário inteligente ou ponto de retirada do Thailand Post pronto para busca. | "Pronta para retirada" |
| `34` `Scheduled for further delivery attempt today` / `Item held, addressee notified` (grupo 4) | Tentativa de entrega não concluída (por exemplo, destinatário ausente ou taxas alfandegárias/COD a pagar). | "Tentativa de entrega" |
| `28` `Return to the origin post office` | Ocorrência registrada e objeto em rota de retorno à agência de origem. | "Precisa de atenção" |
| `35/L` `The sender received the returned item` | Encomenda devolvida entregue de volta ao remetente original. | "Devolvida ao remetente" |
| `35/S` `Final delivery` | Entregue ao destinatário (em envios COD pode vir seguido por `36` `Successfully transfer money to seller`, sem alterar o horário da entrega). | "Entregue" |

## Pagamento na entrega (COD), repasses internacionais e alfândega

- **Exportações internacionais (`...TH` para o exterior):** após `15` (`Departure From Outward OE`), o correio nacional do seu país — como [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl) / Deutsche Post ou [Australia Post](carrier:australia-post) — conduz o desembaraço de importação e a entrega local, repassando leituras EDI de volta ao Thailand Post.
- **Impostos aduaneiros e IVA de importação:** encomendas exportadas da Tailândia ou importadas para lá estão sujeitas às regras alfandegárias, IVA de importação e taxas de desembaraço do destino (incluindo cobranças da UE sobre pedidos de fora do bloco). Se aparecer `34` (`Item held, addressee notified due to Payment of charges`), verifique sua caixa de correio ou agência local para o aviso de pagamento. Veja [encomendas retidas na alfândega](guide:customs).
- **Pagamento na entrega (COD) na Tailândia:** em entregas nacionais COD, o Thailand Post registra `35/S` (`Final delivery`) quando o destinatário recebe e paga o valor, seguido de `36` (`Successfully transfer money to seller`) quando o repasse financeiro chega ao vendedor.

## Encomenda Thailand Post atrasada, retida ou sumida

- **Pausa após sair da Tailândia:** despachos aéreos internacionais (em especial registrados `R...TH` e econômicos) costumam pausar dias ou semanas entre `Departure From Outward OE` e `Arrival at inward office of exchange`. Consultar o mesmo código `TH` de 13 caracteres no site do correio do país de destino frequentemente traz mais detalhes de alfândega ou entrega local. Veja [por que o rastreio para de atualizar](guide:tracking-not-updating).
- **Envios perdidos, danificados ou devolvidos:** fale primeiro com o remetente ou marketplace e guarde o código de 13 caracteres e o recibo. Veja [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente Thailand Post e central 1545

Canais oficiais divulgados em [Thailand Post Contact Us](https://www.thailandpost.co.th/un/contact_thp/un/41) e [thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en):

- **THP Contact Center (Tailândia):** **`1545`** (dúvidas gerais, rastreamento e reclamações).
- **Portal oficial de contato:** [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).
- **Rastreador on-line e avisos internacionais:** [track.thailandpost.co.th](https://track.thailandpost.co.th/) e [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update).

> Cuidado com SMS ou mensagens no LINE falsas pedindo o pagamento de pequena taxa em link desconhecido para liberar uma encomenda Thailand Post. Verifique qualquer cobrança diretamente em [track.thailandpost.co.th](https://track.thailandpost.co.th/) ou na central THP `1545`. Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

## Acompanhe sua encomenda Thailand Post no Peek

O [Peek](/) identifica o Thailand Post automaticamente como única opção para qualquer código `TH` S10 válido de 13 caracteres (`EE482917367TH` ou `RR739184627TH`, inventados) e reconhece diretamente links `track.thailandpost.co.th/?trackNumber=`. Se quiser trocar de transportadora manualmente (por exemplo, para comparar com as leituras do correio local após a chegada ao destino), toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela do pacote.

O Peek lê o rastreador do Thailand Post — e seu servidor alternativo caso o principal falhe — exibindo a etapa ("Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Pronta para retirada", "Tentativa de entrega", "Precisa de atenção", "Devolvida ao remetente", "Entregue"), o texto em inglês, a agência postal e o horário da entrega. Os códigos numéricos determinam a etapa, e nenhum app do Peek os mostra; o nome do destinatário aparece em "Destinatário" apenas na sua tela privada, nunca em links compartilhados, e nenhum aplicativo mostra quem assinou. Leituras do próprio Thailand Post trazem fuso de Bangkok; já leituras EDI de correios estrangeiros mantêm o relógio local do escritório estrangeiro mas são gravadas pelo Thailand Post como hora de Bangkok, então o Peek aplica o fuso daquele local (Tóquio para `TOKYO INTERNATIONAL`, ou o da leitura vizinha se faltar local) e converte tudo para o fuso do seu aparelho. O Peek omite nome do entregador, telefones, assinatura e nota de contato mascarada; o Thailand Post não informa data prevista de entrega.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que o Thailand Post exibir `31` (`Item out for physical delivery`), a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o rastreador do Thailand Post responder. Nos demais casos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta a cada hora, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o Thailand Post não responde e o Peek acompanha por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Thailand Post.", com o link "Pedir ajuda a Thailand Post" para a página de contato do Thailand Post. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Thailand Post."

Você pode selecionar alertas de "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas monitora o rastreamento e não libera cargas na alfândega, não paga cobranças COD nem contata o Thailand Post por você.

## Perguntas sobre o rastreio Thailand Post

### Como é um código de rastreio do Thailand Post?

Objetos rastreados do Thailand Post usam códigos UPU S10 de 13 caracteres começando com duas letras de serviço, seguidas por 9 dígitos (o último sendo verificador) e `TH` — como `EE482917367TH` para EMS ou `RR739184627TH` para registrado (exemplos inventados).

### O que significa o código `36` (`Successfully transfer money to seller`)?

Em entregas nacionais tailandesas com pagamento na entrega (COD), o código `36` confirma que o Thailand Post repassou o valor pago pelo destinatário ao vendedor após a entrega final (`35/S`). É um evento financeiro, não uma movimentação do pacote.

### Por que minha encomenda Thailand Post parou de atualizar após `Departure From Outward OE`?

A encomenda deixou o centro internacional de saída da Tailândia e está em trânsito aéreo internacional ou aguardando recebimento de importação pelo operador postal do país de destino.

### Como entrar em contato com o atendimento do Thailand Post?

Ligue para o THP Contact Center no **`1545`** dentro da Tailândia ou utilize a página oficial de contato em [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).

:::sources
- [Thailand Post: Official Website](https://www.thailandpost.co.th/index.php?page=index&language=en) – *Thailand Post Co., Ltd.*, serviços domésticos e internacionais EMS, Registrado, Courier Post, LogisPost e COD
- [Thailand Post: Contact Us & THP Contact Center 1545](https://www.thailandpost.co.th/un/contact_thp/un/41) – suporte oficial, portal de reclamações e central `1545` (URL de ajuda vinculada pelo Peek)
- [Thailand Post: Public Tracker](https://track.thailandpost.co.th/) – consulta pública oficial de rastreamento
- [Thailand Post: International Service Advisories](https://sites.google.com/thailandpost.com/interthp/update) – status e comunicados de serviços internacionais
- [Universal Parcel Scraper: thailand-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/thailand-post) – validação do dígito verificador `TH` S10, alternância entre dois hosts, mapeamento `statusID` UPU (`3`–`36`, `57`), relógios de Bangkok vs. EDI estrangeiro e extração de destinatário
:::
