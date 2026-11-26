---
title: Rastreamento Express One: status, pontos de coleta e ajuda
description: Rastreie encomendas da Express One na Hungria: onde encontrar o código, significado dos status, pontos e armários Exopoint, entregas perdidas e contatos.
slug: express-one-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Express One Hungary é uma transportadora húngara pertencente à [Austrian Post](carrier:austrian-post), que entrega pedidos de lojas virtuais com cerca de 700 entregadores e mais de 2.300 pontos de retirada e armários de encomendas. Rastreie uma encomenda pelo ID do pacote em [tracking.expressone.hu](https://tracking.expressone.hu/) ou cole o código na caixa acima. O número chega no e-mail que a Express One envia assim que o volume é registrado e chega à transportadora.

## Código de rastreamento da Express One

A Express One envia um pré-aviso por e-mail assim que o remetente registra a encomenda e ela chega fisicamente à Express One; o SMS só é enviado se o remetente pagar por ele. Dentro da Hungria, essas mensagens informam a faixa horária de entrega, o contato do entregador, eventual valor de pagamento na entrega e um link para alterar a entrega. Encomendas para o exterior não recebem esse aviso. Não recebeu nenhuma mensagem? Pergunte à loja e veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

O rastreador também aceita a referência do remetente ou o número do documento, mas essas opções exigem o Company ID de 5 dígitos do remetente; portanto, use o ID do pacote (o número do código de barras).

A Express One não publica um padrão oficial. Os IDs de pacote vistos até agora aparecem em dois formatos (dígitos inventados):

| Código | A quem pertence |
| --- | --- |
| `0482917364829` | 13 dígitos: um ID de pacote da Express One, mas a [DPD França](carrier:dpd-fr) e outras transportadoras também usam esse tamanho. |
| `148291736482917364821011` | 24 dígitos terminando em um código postal húngaro: Express One. Austrian Post, [InPost](carrier:inpost), [bpost](carrier:bpost) e [Ciblex](carrier:ciblex) também usam 24 dígitos. |
| `48291736482917` | 14 dígitos: DPD, nunca Express One. |
| `48291736482` | 11 ou 12 dígitos: GLS, nunca Express One. |
| `RR482917367HU` | Duas letras, 9 dígitos e `HU`: [Magyar Posta](carrier:magyar-posta), nunca Express One. |

Considere a transportadora indicada no e-mail da loja; veja mais em [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Express One

O site tracking.expressone.hu abre em inglês ou húngaro conforme o idioma do navegador, com um botão EN/HU. Cada leitura traz código, descrição, data e hora; leituras de hub e depósito acrescentam o local entre parênteses, como "(HU - Budapest)". Os textos em inglês, na ordem em que costumam aparecer:

| Status da Express One | O que significa para você |
| --- | --- |
| `Shipping details received, shipment not yet handed over to EXOHU` | O remetente [registrou a encomenda](guide:tracking-statuses); a Express One ainda não está com ela. |
| `Shipment (pan) data uploaded` | Os dados da encomenda entraram no sistema da Express One, geralmente logo após uma leitura no hub. |
| `Arrived at central hub` | Chegou ao centro de distribuição central em Budapeste. |
| `Departed central hub` | Saiu do centro central. |
| `Arrived at depot` | Chegou ao depósito que faz entregas no seu endereço. |
| `Out for delivery` | Está na van do entregador hoje. |
| `Delivery schedule updated` | O plano de entrega foi recalculado; o volume não se moveu. |
| `Processing in progress` | A entrega foi transferida para mais tarde, sem motivo informado. |
| `Addressee not present at delivery address` | O entregador compareceu e não havia ninguém no local. |
| `Addressee unavailable` | A segunda tentativa também falhou. |
| `Obstacles of nature` | Mau tempo interrompeu a rota; conta como tentativa e a encomenda sai novamente. |
| `Successful delivery` | Entregue. |

EXOHU significa Express One Hungary: o primeiro status indica apenas que a etiqueta existe. A barra acima do histórico tem seus próprios rótulos, de `Shipment data uploaded` até `Successfully delivered`. Em húngaro, entregue aparece como `Sikeres kézbesítés`.

## Entregas da Express One, pontos de coleta e tentativas falhas

**Quando chega.** O pré-aviso informa um intervalo de 4 horas. Na manhã da entrega, uma mensagem traz uma janela de 2 horas, que a Express One classifica apenas como indicativa: o entregador pode chegar antes ou depois. Nesse dia, a página de rastreamento mostra quantas paradas faltam antes da sua. Não há entregas no fim de semana, exceto entrega paga aos sábados para remetentes comerciais.

**Alteração da entrega.** O botão "Modification" no SMS ou e-mail da manhã permite alterar data ou endereço, ou direcionar o pacote para uma loja parceira ou armário, funcionando até uma hora antes de o entregador chegar. Alterações só são permitidas no primeiro dia de entrega e apenas uma vez. Algumas opções dependem do remetente (pacotes grandes não cabem em armários). Os termos da Express One afirmam que a alteração conta como tentativa de entrega e pode custar taxa extra; já um post do blog da Express One de 2024 diz que o redirecionamento é gratuito.

**Na entrega.** Destinatários pessoa física assinam e fornecem o número de um documento de identidade. O pagamento na entrega pode ser feito em dinheiro (até 300.000 Ft), no cartão (até 1.000.000 Ft) ou antecipadamente pelo link na mensagem de pré-chegada; em um Exopoint, o limite em dinheiro é de 250.000 Ft. Não é permitido abrir a caixa antes de assinar e pagar.

**Entrega perdida.** Pelos termos da Express One, ela avisa por e-mail ou SMS até o dia útil seguinte, faz mais uma tentativa gratuita e guarda o volume até o 5º dia útil no máximo. Enquanto isso, você pode pedir nova data ou ponto de retirada. Após a segunda falha, a encomenda retorna ao remetente; um post de 2024 no blog diz que ela primeiro aguarda 5 dias úteis no depósito central de Budapeste.

**Pontos de coleta e armários.** Muitas lojas permitem escolher um ponto ou armário Exopoint no checkout. A página de pontos da Express One afirma que o volume aguarda até uma semana, 24 horas por dia. Os artigos do blog divergem: 3 dias em um EXOBOX, 2 em um Alzabox, até 7 em um ponto Packeta (2025), 72 horas em armário (2026), 5 dias em ponto de coleta (2024), com acesso 24 horas apenas nos armários EXOBOX e Alzabox. Retirar no centro logístico de Budapeste (H-1239 Budapest, Európa street 12, prédio L1) exige agendamento com o atendimento.

## Encomenda da Express One atrasada, danificada ou perdida

**Sem atualização ou não encontrada.** A Express One não define prazo mínimo de espera. O rastreador informa que o número pode estar incorreto, ter mais de 180 dias ou ainda não ter sido registrado pelo remetente, orientando tentar novamente mais tarde. Seus termos permitem apagar os dados se nenhum volume chegar em 5 dias úteis e classificam o prazo previsto apenas como informativo. Veja [rastreamento que não atualiza](guide:tracking-not-updating).

**Produto danificado ou errado.** Avise a loja o quanto antes: a Express One abre reclamação após o relato do remetente e responde pelo transporte, não pelo conteúdo. Registre danos visíveis no ato da entrega; reporte danos ocultos por escrito em até 3 dias úteis após o recebimento, sob pena de perder o direito.

**Extravio.** Normalmente o remetente abre o pedido, embora você também possa fazê-lo se recebeu o pacote ou se o remetente transferiu o direito por escrito. A reclamação escrita por perda total deve ser feita em até 6 meses a partir do 15º dia após a postagem, com número do conhecimento, comprovação do conteúdo e valor e formulário da Express One. A Express One responde em até 30 dias corridos; se não ficar satisfeito, você pode recorrer à NMHH, autoridade húngara de mídia e comunicações, em até 30 dias.

## Telefone de contato e atendimento da Express One

Conforme a página de contato em inglês da Express One, em dias úteis das 8h às 17h no horário da Hungria (o FAQ cita até as 18h):

- **Atendimento ao cliente:** +36 1 8 777 400, ugyfelszolgalat@expressone.hu (inclusive se o entregador não atender). Em períodos de pico, a Express One recomenda escrever.
- **Atendimento internacional:** +36 1 8 777 410, intcs@expressone.hu; reclamações em intcsreklamacio@expressone.hu.
- **Reclamações por dano:** +36 1 8 777 470, karugy@expressone.hu.
- **Formulário de contato:** na [página de contato da Express One](https://expressone.hu/en/kapcsolat), com assuntos como "I am expecting a parcel" e "Delivery complaint"; ambos exigem o código da encomenda.

A página de rastreamento da Express One também lista o telefone +36 1 8 777 444 e um chatbot.

## Acompanhe encomendas da Express One com o Peek

O [Peek](/) não consulta a Express One automaticamente para um código de 13 dígitos, pois testa outras transportadoras. Um código de 24 dígitos vai primeiro para a Express One quando você busca da Hungria, antes de Austrian Post, InPost e Ciblex; fora da Hungria, o Peek consulta essas três primeiro e depois a Express One. Se nenhuma reconhecer, você verá "ainda não encontrada · o Peek continua verificando": escolha "Express One" em "Alterar". Ou cole um link de tracking.expressone.hu com `?plc_number=`, que identifica a Express One qualquer que seja o número.

As movimentações da Express One vêm sem fuso horário: o Peek interpreta cada uma no relógio do local da leitura (horário da Hungria para leituras na Hungria) e exibe o histórico próprio da Express One. Ele lê o status e, nas leituras de hub, a cidade e o país. Os textos permanecem como a Express One os envia, exceto `Out for delivery`; `Successful delivery` conta como entregue e um trajeto de retorno conta como devolvida. Uma leitura desconhecida pelo Peek exibe o texto da Express One sem etapa. Você pode ativar notificações de mudança de status. Não há previsão de entrega, peso nem ponto de coleta, e a barra de progresso e a assinatura ficam de fora.

## Perguntas sobre o rastreamento da Express One

### O que significa "shipment not yet handed over to EXOHU"?

O remetente criou a remessa, mas a Express One Hungary ainda não recebeu o pacote fisicamente. Se permanecer assim por vários dias, fale com a loja.

### Quanto tempo uma encomenda da Express One aguarda em um ponto de coleta?

Até uma semana, a qualquer hora, segundo a página de pontos da Express One; já o blog da empresa indica de 2 a 7 dias conforme o ponto, com acesso 24 horas apenas nos armários EXOBOX e Alzabox.

### A Express One da Eslováquia ou da Eslovênia é a mesma empresa?

Não. Elas também pertencem à Austrian Post, mas são empresas separadas; esta página e a opção "Express One" no Peek referem-se apenas à operação húngara.

:::sources
- [Express One: Parcel Tracking](https://expressone.hu/en/csomagkovetes) – paradas do entregador, central telefônica e chatbot
- [Express One: Tracking](https://tracking.expressone.hu/) – tipos de ID, Company ID, mensagem de não encontrado, 180 dias, status e idiomas
- [Express One: Frequently asked questions](https://expressone.hu/en/gyik) – modificação, janela de 2 horas, sábado, pagamento na entrega, danos e item errado
- [Express One: GYIK](https://expressone.hu/gyik) – limites de pagamento na entrega
- [Express One: Contact](https://expressone.hu/en/kapcsolat) – telefones, e-mails, horários, formulário e centro logístico
- [Express One: Parcel point](https://expressone.hu/en/csomagpont) – 2.300 pontos e armários, uma semana, 24/7 e seleção no checkout
- [Express One: Home](https://expressone.hu/en) – Austrian Post, 700 entregadores e redirecionamento até uma hora antes
- [Express One: General Terms and Conditions](https://expressone.hu/downloads/General_Terms_and_Conditions_2026_08_15.pdf) – tentativas, guarda, documento, taxa de alteração, indenizações, reclamações, NMHH e exclusão de dados
- [Express One: Merre jár a csomagom?](https://expressone.hu/blog/merre-jar-a-csomagom-csomagkovetes-es-ertesitesi-rendszerek-alkalmazasa) – pré-aviso após registro e chegada, janelas de 4 e 2 horas e opção de SMS
- [Express One: Csomag átirányítás és a kézbesítés átütemezése](https://expressone.hu/blog/csomag-atiranyitas-es-a-kezbesites-atutemezese) – redirecionamento gratuito e 5 dias no depósito de Budapeste ou ponto de coleta
- [Express One: Minden, amit az EXOPOINT megoldásokról tudni érdemes](https://expressone.hu/blog/minden-amit-az-exopoint-megoldasokrol-tudni-erdemes) – horários de funcionamento e prazos de 3, 2 e 7 dias
- [Express One: Csomagküldés csomagautomatába](https://expressone.hu/blog/csomagkuldes-csomagautomataba-miert-valasztjak-egyre-tobben) – 72 horas no armário
- [Express One: eBOX GYIK](https://expressone.hu/ebox/faq) – conteúdo das notificações, ausência para exportação e limite de 250.000 Ft em dinheiro no Exopoint
- [Austrian Post: Austrian Post Group acquires Hungarian parcel delivery provider](https://news.post.at/presse/en/post/id/2255390/) – subsidiária
- [Austrian Post: International subsidiaries](https://post.at/en/j/c/careers-austrian-post-subsidiaries-international) – empresas Express One em outros países
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê da Express One
:::
