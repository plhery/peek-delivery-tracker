---
title: Seguimento Delhivery: números AWB, estados e apoio
description: Segue uma encomenda Delhivery na Índia ou para o estrangeiro: formato do número AWB, significado de estados como RTO, falhas na entrega, alfândega e suporte.
slug: seguimento-delhivery
published: 2026-11-21
updated: 2026-11-21
---

Fundada em 2011, a Delhivery (*Delhivery Limited*) apresenta-se como o maior operador logístico totalmente integrado da Índia: entrega compras online de marketplaces e lojas em mais de `18 850` códigos PIN e expede da Índia para mais de `220` países e territórios. Podes seguir uma encomenda na [página de seguimento da Delhivery](https://www.delhivery.com/tracking) com o número AWB, o ID da encomenda (`Order Id`), um `LRN` ou o teu telemóvel, ou colar o AWB na caixa acima.

## Número de seguimento Delhivery (AWB): como é

A Delhivery chama ao seu código de seguimento **AWB** (*airway bill number*, número de carta de porte). Estes são os formatos AWB que o Peek reconhece, com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `1482910573642` | **13 algarismos**: um AWB da Delhivery. Outras transportadoras usam também 13 dígitos, como a [Econt](carrier:econt), DPD France, FAN Courier ou Korea Post. |
| `14829105736492` | **14 algarismos**: também um AWB da Delhivery, formato partilhado com a [DPD](carrier:dpd-de), [SEUR](carrier:seur), [BRT](carrier:brt) ou Xpressbees. |

O comprimento por si só não identifica a Delhivery, pelo que convém confirmar o nome da transportadora no SMS ou e-mail da loja. Em `delhivery.com/tracking` também podes pesquisar por `Order Id`, por `LRN` (carga B2B) ou iniciar sessão com o teu número de telemóvel e código `OTP` para veres todos os envios destinados a ti; no Peek é necessário o **AWB** de 13 ou 14 algarismos ([como encontrar o número de seguimento](guide:find-tracking-number)).

## Estados de seguimento da Delhivery explicados

O seguimento da Delhivery junta uma barra de progresso às leituras registadas com a respetiva cidade. Eis os textos e códigos lidos pelo Peek:

| Estado da Delhivery | O que significa para a tua encomenda | O Peek mostra |
| --- | --- | --- |
| `WAITING_PICKUP` | Envio reservado mas ainda não recolhido (surge como `Waiting pickup` enquanto não houver leituras). | «Etiqueta criada» |
| `Picked Up` | A Delhivery recolheu a remessa no remetente (também usado como marco de carga sem leituras individuais). | «Aceite pela transportadora» |
| `PROD_REPLACED` | Numa recolha inversa de substituição, o estafeta já levantou o artigo substituído. | «Aceite pela transportadora» |
| `In Transit`, `IN_TRANSIT`, `On the Way` | Em viagem na rede da Delhivery (`IN_TRANSIT` como código, `On the Way` como marco de carga). | «Em trânsito» |
| `Dispatched`, `Out for Delivery` | Saiu para entrega (`Dispatched` na leitura do pacote, `Out for Delivery` na barra de progresso). | «Em distribuição» |
| `Delivered` | Entregue (num fluxo de devolução significa entregue de volta ao vendedor). | «Entregue» (ou «Devolvida ao remetente») |
| `Undelivered` | Não entregue e de regresso ao vendedor; em carga, a Delhivery muda o nome da etapa `On the Way` mantendo a data original dessa etapa, que não corresponde a uma tentativa de entrega. | «Problema reportado» |
| `REACHED_SEL_CITY` | Uma encomenda em devolução chegou à cidade do vendedor. | «Em trânsito» |
| `RTO`, `DELIVERED_SELLER` | *Return to Origin*: a encomenda não entregue regressou ao vendedor. | «Devolvida ao remetente» |
| `DTO` | Uma devolução enviada pelo cliente chegou ao vendedor. | «Devolvida ao remetente» |
| `LOST` | A Delhivery marcou a remessa como perdida. | «Problema reportado» |
| `CANCELLED`, `Canceled` | O envio ou a recolha de devolução foi cancelado. | «Problema reportado» |

Vê mais sobre cada fase em [o que significam os estados de seguimento](guide:tracking-statuses).

## Entrega da Delhivery na Índia: chamadas, falhas e morada

**Rede.** A Delhivery opera `50` centros de triagem automatizados, `123` plataformas e mais de `4 500` centros de distribuição direta em todos os estados indianos, cobrindo `99,5 %` dos códigos PIN (incluindo o Nordeste da Índia e Jammu e Caxemira) 24 horas por dia, todos os dias do ano.

**Chamadas e WhatsApp.** Os termos preveem que a Delhivery ou os seus parceiros te liguem, enviem SMS ou contactem por WhatsApp (as chamadas podem ser gravadas) para coordenar a entrega. Para lojas online disponibiliza gestão `Smart NDR` com chamadas de IA e confirmações no WhatsApp para novas tentativas, mas **nunca** pede `OTP` ou credenciais para confirmar a tua morada.

**Tentativas falhadas e regras Direct.** Nas remessas particulares *Delhivery Direct* (até `50 kg` de peso taxável, `10 kg` e `50 cm` por lado em modo expresso; sem apartados postais), a entrega é cancelada após **3 tentativas falhadas** sem reembolso do porte (devolução possível a expensas do remetente). Com acordo da Delhivery podes pedir entrega a vizinho, caixa de correio/cacifo ou levantamento em local aprovado, podendo a Delhivery recusar mudança de código PIN ([falha na entrega](guide:missed-delivery)). Nas compras online aplicam-se as condições da loja.

## Encomendas da Delhivery para o estrangeiro e alfândega

**Prazos e parceiros.** Com o **DLV Saver**, a Delhivery estima `5–7` dias para o Reino Unido e `8–12` dias para os EUA (disponível para EUA, Reino Unido, Canadá e Austrália); o **DLV Premium** serve mais de `220` países e territórios em `3–6` dias (*Express*) ou `6–10` dias (*Deferred Express*) a partir de `50 g`. No estrangeiro, a Delhivery refere a [FedEx](carrier:fedex) e a Aramex como parceiros estratégicos: se a loja te der outro código para o troço final, cola-o também no Peek.

**Alfândega em Portugal e noutros países.** Em **Portugal** (incluindo os Açores e a Madeira, que integram o território aduaneiro e do IVA da União Europeia), as compras online expedidas de fora da UE até `150 €` pagam desde 1 de julho de 2026 um direito aduaneiro temporário de `3 €` por artigo (bens com a mesma classificação pautal, descrição e, quando exigido, origem; acima de `150 €` aplica-se a pauta normal) e, desde 1 de novembro de 2026, uma taxa de tratamento da União de `2 €` por artigo qualquer que seja o valor, além do IVA na importação — exceto quando o IVA já foi cobrado no pagamento através do balcão IOSS. No **Brasil** e fora da UE aplicam-se as regras aduaneiras locais ([encomendas retidas na alfândega](guide:customs)).

## Seguimento parado, encomenda perdida, danificada ou devolvida

Para reembolso ou novo envio de uma compra online, contacta primeiro a loja que contratou a Delhivery.

- **Sem atualizações:** em «Etiqueta criada» (`WAITING_PICKUP`), o vendedor ainda não entregou o pacote. Se parar em trânsito, inicia sessão com o teu telemóvel em `delhivery.com/support` ou na app para abrir um pedido ([porque é que o seguimento não atualiza](guide:tracking-not-updating)).
- **Perdida, danificada ou entregue noutro sítio:** no *Delhivery Direct*, as reclamações por perda, dano ou entrega incorreta têm de ser abertas por ticket na app no prazo de **3 dias** (no próprio dia civil para caixas compradas à Delhivery que cheguem danificadas), juntando **3 fotografias** da embalagem exterior e do conteúdo em caso de dano (sugerem filmar a abertura). São resolvidas em **30 a 45 dias úteis** e, sem a cobertura paga *Delhivery Protect* (obrigatória entre `10 000 ₹` e o limite Direct de `49 999 ₹`; até `30 000 ₹` por AWB no e-commerce), a indemnização máxima é de **`2 000 ₹`** com tetos por categoria (`500 ₹` livros/documentos/outros, `700 ₹` vestuário, `800 ₹` lar, `1 000 ₹` eletrónica ou múltiplos artigos, `2 000 ₹` desporto, `400 ₹` consumíveis), sem compensação por atraso ([encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [entregue mas não recebido](guide:delivered-not-received)).
- **Devolvida (`RTO`, `DELIVERED_SELLER`):** o pacote regressou ao vendedor; pede à loja o reembolso ou nova expedição ([devolvida ao remetente](guide:returned-to-sender)).

## Apoio ao cliente da Delhivery e alertas de fraude

- **Apoio apenas pelo site ou app:** a Delhivery informa que o seu apoio ao cliente **só** está acessível através da [página de suporte](https://www.delhivery.com/support) (entrando com número de telemóvel e `OTP` em `Raise a Query`; secções `Report Issues`, `Grievance Officer`, `Report on-field staff issues`, `Returns`) ou da app *Delhivery Direct*.
- **Sem telefone público de apoio:** números de apoio encontrados noutros sites podem ser fraudulentos. Os e-mails oficiais vêm apenas de `@delhivery.com` e nas redes sociais a Delhivery só presta apoio pelas contas oficiais no Facebook, X e Instagram.
- **SMS falsos:** tem cuidado com mensagens sobre falhas de entrega que remetem para domínios falsos (`delhiveryu.top`, `delhiveryma.top`); a Delhivery nunca pede `PIN` nem `OTP` para pagamentos, nem `OTP` ou credenciais para confirmar a morada. Na Índia, em caso de burla financeira liga `1930` ou participa em `cybercrime.gov.in` (e denuncia chamadas/SMS spam em 3 dias em `sancharsaathi.gov.in`); vê também [mensagens falsas de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda Delhivery com o Peek

Cola o AWB de 13 ou 14 algarismos no [Peek](/): como várias transportadoras usam estes comprimentos, o Peek mostra «A verificar serviços de seguimento…» e consulta automaticamente a Delhivery quando acedes a partir da Índia com um código iniciado por `1`–`9` e terminado em `0`–`6` («Delhivery tem esta encomenda.»). Fora da Índia — ou para números iniciados por `0` ou terminados em `7`, `8` ou `9` — toca em «Alterar» e escolhe a Delhivery. Para outro comprimento, a escolha manual indica que «não emite números como este».

Guardada na Delhivery, antes da primeira leitura surge «À espera de Delhivery». O Peek lê o seguimento público sem conta: mostra as leituras concluídas com a cidade (ignorando etapas futuras da barra e dados do destinatário), determina o ano dos marcos de carga pelo dia da semana, lê as horas no fuso indiano (`Asia/Kolkata`, `UTC+5:30`) e converte-as para o fuso do teu dispositivo («Horas no teu fuso horário: …»), sem data prevista. Uma encomenda devolvida ao vendedor aparece como «Devolvida ao remetente» e não «Entregue».

Com a encomenda aberta ou no dia da entrega com notificações ativas, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos desde as `06:00` da Índia no dia em que a Delhivery regista «Em distribuição» enquanto o localizador da Delhivery responder; nos restantes casos, e após 12 horas em «Etiqueta criada» / «Aceite pela transportadora» ou dois dias sem leituras, de hora a hora («Todos os registos», «Passos importantes» ou «Só a entrega»). O Peek não pode contactar a Delhivery nem a loja por ti.

## Perguntas frequentes sobre o seguimento da Delhivery

### Como sigo uma encomenda da Delhivery?

Cola o AWB de 13 ou 14 algarismos no topo desta página no Peek (escolhendo a Delhivery em «Alterar» se estiveres fora da Índia), ou pesquisa em `delhivery.com/tracking` por AWB, `Order Id`, `LRN` ou número de telemóvel com `OTP`.

### O que é o número AWB na Delhivery?

É o *airway bill number*, o número de seguimento da Delhivery com 13 ou 14 algarismos enviado no SMS ou e-mail da loja.

### O que significa `RTO` no seguimento da Delhivery?

*Return to Origin*: a encomenda voltou para trás e já chegou ao vendedor. Pede à loja o reembolso ou um novo envio.

### Qual é o telefone de apoio ao cliente da Delhivery?

A Delhivery não divulga linha telefónica de apoio: o suporte funciona apenas iniciando sessão com o teu número em `delhivery.com/support` ou na app da Delhivery, avisando que outros números encontrados na Internet podem ser fraudulentos.

:::sources
- [Delhivery: Track your order](https://www.delhivery.com/tracking) – pesquisa por telemóvel, AWB, Order Id ou LRN e alertas de fraude
- [Delhivery: Support](https://www.delhivery.com/support) – início de sessão com OTP, abertura de pedidos e tópicos incluindo Grievance Officer
- [Delhivery: About Us](https://www.delhivery.com/about-us) – fundada em 2011, mais de 18 850 códigos PIN, 50 centros de triagem e mais de 4 500 centros de entrega 24/7
- [Delhivery: Página inicial](https://www.delhivery.com/) – mais de 220 países e territórios e parceiros estratégicos FedEx e Aramex
- [Delhivery: Express Parcel](https://www.delhivery.com/services/express-parcel) – cobertura de 99,5 % dos códigos PIN, Smart NDR via WhatsApp/IA e proteção e-commerce
- [Delhivery: International](https://www.delhivery.com/services/international) – prazos DLV Saver e DLV Premium desde 50 g e direitos e impostos
- [Delhivery: Personal Courier](https://www.delhivery.com/solutions/personal-courier) – envios pessoais Delhivery Direct
- [Delhivery: Terms and Conditions](https://www.delhivery.com/terms-and-conditions) – chamadas, SMS e mensagens WhatsApp sobre entregas
- [Delhivery: TnC (Direct National Courier)](https://www.delhivery.com/tnc-direct) – limites de peso, 3 tentativas, reclamações em 3 dias com 3 fotos, resolução em 30–45 dias úteis e tetos até 2 000 ₹ / Delhivery Protect
- [Delhivery: Fraud Disclaimer](https://www.delhivery.com/fraud-disclaimer) – canais oficiais @delhivery.com, SMS falsos, ausência de OTP de pagamento, linha 1930 e cybercrime.gov.in
- [Comissão Europeia: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € até 150 € desde julho de 2026 e taxa de 2 € desde novembro de 2026
- [Comissão Europeia: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definição de artigo, taxa de 2 € qualquer que seja o valor e regime IOSS
- [Comissão Europeia: Âmbito territorial do IVA e da união aduaneira](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Portugal, incluindo Açores e Madeira, integrado no território aduaneiro e do IVA da UE
- [Universal Parcel Scraper: Delhivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delhivery) – formatos AWB de 13 e 14 algarismos, 17 estados e códigos, marcos de carga, devoluções e fuso Asia/Kolkata
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – outras transportadoras de 13 e 14 algarismos
:::
