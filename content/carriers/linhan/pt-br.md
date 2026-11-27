---
title: Rastreamento LinHan International: códigos LHGJ e status
description: Rastreie uma encomenda LinHan International vinda de Shenzhen: formato do código LHGJ, significado de cada status e por que os horários usam Pequim.
slug: linhan-tracking
published: 2026-11-27
updated: 2026-11-27
---

A LinHan International (深圳市林瀚国际物流有限公司, aproximadamente Shenzhen LinHan International Logistics Co., Ltd.) é uma agente de cargas em Shenzhen, na China, que despacha por via aérea encomendas de vendedores on-line para fora da China e as repassa a um agente no país do comprador para desembaraço alfandegário e entrega. Seus códigos começam com `LHGJ`: digite o seu no rastreador da LinHan em [linhan56.com/index/tracknumber/track](https://www.linhan56.com/index/tracknumber/track) ou na caixa acima.

## Código de rastreamento LinHan: como identificar

A LinHan não publica o formato dos códigos. Seus conhecimentos de transporte trazem `LHGJ`, oito dígitos e `CD`. Exemplos com dígitos fictícios:

| Código | O que é |
| --- | --- |
| `LHGJ48291037CD` | `LHGJ`, oito dígitos e `CD`: código da LinHan International. |
| `LHGJ4829103CD` | Apenas sete dígitos: não corresponde ao formato LinHan. Verifique se falta um dígito. |

Letras minúsculas e espaços não fazem diferença ao colar no Peek.

**Onde encontrar.** A LinHan atende vendedores: sua página inicial oferece cotação em tempo real e reserva de rotas para remetentes, e aos destinatários apenas o rastreador. O código vem da loja onde você comprou; veja [como encontrar seu código de rastreamento](guide:find-tracking-number), em inglês.

**No rastreador da LinHan.** O site aceita até 20 códigos por busca, separados por vírgulas ou quebras de linha, e exibe apenas encomendas dos últimos dois meses.

## Significado dos status de rastreamento da LinHan

A LinHan não publica lista de status. O rastreador tem interface em chinês, mas as leituras vêm em inglês, da mais recente para a mais antiga. Acima delas, uma linha de resumo informa o país de destino e um status em chinês:

| A LinHan mostra | O que significa para você |
| --- | --- |
| `运输途中` | "Em trânsito": a encomenda está a caminho. |
| `成功签收` | "Assinada / recebida": entregue. |
| `查询不到` | "Não encontrada": o rastreador não tem registro desse código e nenhuma linha aparece. |

As leituras das etapas da própria LinHan na China, em ordem:

| A LinHan mostra | O que significa para você |
| --- | --- |
| `Order information received` | O vendedor enviou os dados do pedido à LinHan. Ela ainda não está com o pacote. |
| `Arrive at the cross border receiving point` | A LinHan recebeu a encomenda em seu ponto de recebimento transfronteiriço na China. `Arrive at the receiving point` significa o mesmo. |
| `Goods leave the operation center` | Saiu do centro de operações da LinHan. |
| `Arrival at domestic internationality airport` | Chegou ao aeroporto de saída na China. |
| `Departure from the airport of origin domestic` | O voo partiu da China. |

Em seguida vêm as etapas no seu país, quando o agente da LinHan e a rede de entrega assumem:

| A LinHan mostra | O que significa para você |
| --- | --- |
| `Item arrives at destination airport` | Pousou no país de destino. `Arrive at the destination international airport` significa o mesmo. |
| `local carrier Agent Import customs clearance` | O agente da LinHan está fazendo o desembaraço aduaneiro de importação; ainda não foi liberada. `Import customs clearance`, após a cidade portuária, é a mesma etapa. |
| `transfer to local acting company International self-operation team` | Repassada ao agente no país de destino. |
| `Internationaliy Package received at processing center` | Grafia original da LinHan. Primeira leitura da rede de entrega em um centro de processamento — não é um novo envio, pois a LinHan já recebeu o pacote na China. |
| `Leave the processing center and transport to … processing center` | Em deslocamento entre dois centros de processamento, com a cidade do próximo no lugar das reticências. `Leave the … processing center` indica o centro de partida. |
| `Arrive at the sorting and distribution center` | Chegou a um centro de triagem, com a cidade após "Arrive". `Leaving the sorting center` indica a saída dele. |
| `Arrive at the last mile sorting and distribution center` | No centro de triagem da rede de entrega final, precedido por cidade, estado e código postal. |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Leitura da rede de entrega com o nome dela substituído por "logistics carrier". |
| `Accepted at logistics carrier Regional Destination Facility` | Leitura daquela rede perto de você, após cidade, estado e código postal. "Accepted" não reinicia o envio: segue em trânsito. |
| `package is on the way` | Seguindo viagem a partir do centro de triagem local, após a cidade. Não significa que um entregador já saiu para entrega hoje. |
| `Delivered` | Entregue. |

Muitas linhas no país de destino começam com a cidade (nos EUA, cidade, estado e código postal), e a linha de entrega traz também o seu código postal: cidade, código postal, Delivered. A mesma linha pode aparecer duplicada.

**Horários no fuso de Pequim.** O rastreador da LinHan registra cada horário no fuso de Pequim (UTC+8), inclusive nas leituras do seu país. Como a data em Pequim muda às 16h UTC, uma leitura feita entre esse horário e a sua meia-noite mostra a data do dia seguinte: na Alemanha a partir das 17h no inverno e 18h no verão; em Nova York a partir das 11h no inverno e meio-dia no verão.

## Quem entrega encomendas da LinHan

A página inicial da LinHan descreve sua cadeia como coleta, armazenagem e triagem, desembaraço na China, frete aéreo, declaração aduaneira no exterior, armazéns no exterior e entrega (揽货, 仓储分拣, 国内通关, 空运, 海外报关, 海外仓, 海外派送). Não lista rotas nem destinos; as encomendas vistas no rastreador tinham destino nos Estados Unidos e na Alemanha.

No seu país, um agente assume. O rastreador o chama apenas de "local carrier Agent" e "local acting company", e a rede de entrega de "logistics carrier", sem revelar a empresa nem fornecer um segundo código. Ainda assim, o histórico segue até a entrega, mantendo o rastreador da LinHan como referência.

A LinHan não publica prazos de entrega nem regras para tentativas, pontos de retirada ou guarda. Se um aviso ou mensagem da transportadora local informar quem ela é, valem as regras dela: veja [entrega não realizada](guide:missed-delivery), em inglês.

## Encomenda LinHan não chega ou rastreamento parado

**Apenas `Order information received`.** O vendedor reservou o envio, mas o pacote ainda não chegou à LinHan. Se nada mudar por vários dias, pergunte ao vendedor se já foi postado.

**Nada após o voo.** Depois de `Departure from the airport of origin domestic`, a linha seguinte costuma sair quando o pacote pousa ou entra na alfândega do seu país. Mais em [rastrear encomenda da China](guide:tracking-from-china), em inglês, e [por que o rastreamento para de atualizar](guide:tracking-not-updating), em inglês.

**Na alfândega.** O rastreador da LinHan não informa se há taxas a pagar nem o nome da empresa local, o que dificulta conferir SMS cobrando tarifas: leia [mensagens falsas de entrega](guide:delivery-scam-texts), em inglês, antes de pagar, e [encomendas retidas na alfândega](guide:customs), em inglês.

**`查询不到`.** O rastreador desconhece o código: confirme com o vendedor. Como exibe apenas envios dos últimos dois meses, códigos mais antigos também não aparecem.

**Entregue mas não recebida.** Se a última linha for `Delivered`, consulte [entregue mas não recebida](guide:delivered-not-received), em inglês, e avise o vendedor. O site da LinHan não exibe foto; no máximo cita quem assinou na linha de entrega.

**Extraviada, danificada ou devolvida.** A LinHan não publica processo de indenização, compensação nem devolução. Como foi o vendedor quem contratou a LinHan, peça reembolso ou reenvio ao vendedor ou marketplace. Veja [encomendas extraviadas](guide:lost-parcel), em inglês.

## Contato da LinHan International

A página inicial da LinHan, apenas em chinês, termina com um bloco 联系我们 (Fale conosco) contendo um e-mail e um endereço de atendimento em Pinghu, distrito de Longgang, Shenzhen. Não divulga telefone, chat, formulário nem atendimento a destinatários: para qualquer assunto do pedido, fale com o vendedor.

## Como rastrear uma encomenda LinHan no Peek

O [Peek](/) identifica um código LinHan pelo formato: `LHGJ`, oito dígitos e `CD`, que nenhuma outra transportadora do catálogo utiliza. Links do rastreador da LinHan com `nos=` e o número também funcionam.

O Peek lê o rastreador da LinHan e mostra a etapa, cada leitura com sua cidade (quando informada) e o país de destino. Ele move a cidade do texto para o campo de local e remove o código postal, exibindo a entrega como `Delivered` na cidade. Linhas repetidas pela LinHan aparecem uma só vez, mantendo o inglês original, incluindo `Internationaliy`. Se a linha de entrega trouxer quem assinou (`Delivered, signed by …`), o nome permanece somente ali e é ocultado em links compartilhados. O Peek interpreta todos os horários no fuso de Pequim e os exibe no fuso do seu dispositivo.

Algumas linhas entram em etapas diferentes do que parecem: `Order information received` fica em "Etiqueta criada"; `Internationaliy Package received at processing center` e `Accepted at logistics carrier Regional Destination Facility` ficam em "Em trânsito", sem parecer nova postagem; as duas linhas aduaneiras aparecem como "Na alfândega"; e `package is on the way` permanece em "Em trânsito", pois não confirma saída para entrega.

A LinHan não fornece data prevista, peso, nome do serviço, transportadora final nem segundo código; portanto o Peek não exibe esses dados e acompanha o código `LHGJ` até o final sem trocar de transportadora.

O Peek verifica a cada 10 minutos no máximo enquanto a encomenda está aberta ou no dia da entrega com notificações ativas, enquanto o site da LinHan responder. Após dois dias sem novidade (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora, e a cada 6 horas após sete dias em "Etiqueta criada". Depois de quatro dias sem leitura em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", mostra "Sem atualizações desde …" e a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando LinHan International." Você escolhe alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a LinHan ou o vendedor por você.

## Perguntas frequentes sobre o rastreamento LinHan

### O que é a LinHan International?

A Shenzhen LinHan International Logistics (深圳市林瀚国际物流有限公司), agente de cargas no site linhan56.com que envia encomendas de vendedores por via aérea da China até um agente local que faz a alfândega e a entrega.

### Onde posso rastrear um código LHGJ?

No rastreador da LinHan em [linhan56.com/index/tracknumber/track](https://www.linhan56.com/index/tracknumber/track) ou no Peek. A página inicial da LinHan também traz um link para o 17TRACK.

### Quem entrega encomendas da LinHan?

Um agente no seu país, chamado no rastreador de "local carrier" ou "local acting company", sem revelar o nome. As leituras dele aparecem no site da LinHan até a entrega.

### Por que os horários da LinHan parecem um dia adiantados?

Porque o rastreador da LinHan registra todos os horários no fuso de Pequim (UTC+8), inclusive as leituras feitas no seu país. Qualquer leitura após as 16h UTC, quando já é o dia seguinte em Pequim, sai com a data seguinte. O Peek converte tudo para o fuso do seu dispositivo.

### Quem devo contatar se uma encomenda LinHan sumir?

O vendedor onde você comprou, pois foi ele quem contratou a LinHan. A página inicial da LinHan mostra apenas um e-mail e um endereço em Shenzhen, sem canal para destinatários.

:::sources
- [LinHan International: 深圳市林瀚国际物流有限公司 (home)](https://www.linhan56.com/) – company name, cross-border air freight for shippers, chain from collection to delivery abroad, tracking and 17TRACK links, contact block
- [LinHan International: 快件跟踪 (tracking)](https://www.linhan56.com/index/tracknumber/track) – Chinese tracker, parcels from the last two months, up to 20 numbers per search
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – LHGJ number shape, summary statuses and scan wordings, the destination agent and its unnamed network, Beijing-time clocks, towns and postcodes, destinations seen, what Peek shows and leaves out
:::
