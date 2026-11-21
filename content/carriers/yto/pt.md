---
title: Seguimento YTO Express: número YT, estados e entrega
description: Segue uma encomenda YTO Express (圆通): formato do número YT, significado das leituras em chinês, entrega na China, prazos para Portugal e contactos.
slug: seguimento-yto-express
published: 2026-11-18
updated: 2026-11-20
---

A YTO Express (圆通速递, Yuantong) é uma transportadora expresso chinesa fundada em Xangai em 2000, com mais de 5 000 filiais em toda a China. Um número YTO pertence quase sempre a um envio doméstico dentro da China: por exemplo, uma compra na Taobao, Pinduoduo ou Douyin — plataformas servidas pela YTO — a caminho do armazém de um transitário ou de uma morada local. Podes segui-lo no [rastreador da YTO](https://www.yto.net.cn/ytoExpress/waybill/search), disponível apenas em chinês, ou na caixa acima. Os envios da YTO para fora da China seguem pela YTO International, que tem o seu próprio rastreador em [ytoglobal.com](https://www.ytoglobal.com/service).

## Número de seguimento YTO Express: como é composto

A YTO chama-lhe número de carta de porte (`运单号`). Com algarismos inventados:

| Número | O que é |
| --- | --- |
| `YT2468135790246` | `YT` e 13 algarismos: uma carta de porte da YTO Express. |
| `D24681357902` | `D` e 11 algarismos: formato usado pela YTO, mas também compatível com números da Colis Privé. |
| `G24681357902` | `G` e 11 algarismos: outro formato da YTO que também se pode sobrepor à Colis Privé. |
| `YT2612468135790246` | `YT` e 16 algarismos: não pertence à YTO, mas sim à [YunExpress](carrier:yunexpress). |

Minúsculas e espaços não fazem diferença no [Peek](/). O rastreador oficial da YTO aceita até 10 números em simultâneo, separados por vírgulas, barras ou espaços.

**Onde encontrá-lo.** O vendedor indica-o nos detalhes de expedição da encomenda e também figura na etiqueta colada na caixa. Se não o encontrares, consulta [como encontrar o número de seguimento](guide:find-tracking-number).

**Pesquisa no site da YTO.** Mesmo a partir da versão inglesa do site da YTO, a página de pesquisa mantém-se em chinês: introduz o número no campo de pesquisa e clica em `查询` (pesquisar). De acordo com a política de privacidade da YTO, cada carta de porte fica disponível para consulta durante três a seis meses consoante o canal de encomenda; findo esse prazo, a YTO elimina-a da consulta.

## Significado dos estados de seguimento da YTO Express

Cada leitura nos dados da YTO traz uma indicação curta em chinês, uma hora no fuso horário da China (`UTC+8`) e a filial ou estação onde foi registada. Eis como o Peek apresenta as leituras mais frequentes:

| Leitura na YTO | No Peek | O que aconteceu |
| --- | --- | --- |
| `揽收扫描` | `Picked up` | Recolhida junto do remetente. |
| `业务员收件` | `Picked up` | Recebida em mão por um estafeta da YTO junto do remetente. |
| `装件入车扫描` | `Loaded for transport` | Carregada num veículo para o percurso seguinte. |
| `下车扫描` | `Unloaded at a sorting centre` | Descarregada num centro de triagem ou numa filial. |
| `快件城市信息` | «Em trânsito» | Registo de passagem por uma cidade ao longo do trajeto. |
| `冬运会安检` | `Security check` | Controlo de segurança na rede YTO associado a um evento desportivo de inverno; segue depois viagem. |
| `派件扫描` | «Em distribuição» | Entregue ao estafeta para distribuição. |
| `入柜\|入库` | `In a parcel locker or pickup station` | Colocada num cacifo automático ou registada numa estação de recolha. |
| `小件员取出` | `Taken out of the locker by the courier` | O estafeta voltou a retirar a encomenda do cacifo ou da estação. |
| `PDA正常签收扫描` | «Entregue» | Entregue contra assinatura (`签收`), registada no terminal portátil do estafeta. |
| `退回件扫描` | `Return to the sender started` | Teve início a devolução ao remetente. |

As três linhas entre aspas aparecem em português no Peek; as restantes linhas da tabela mantêm-se em inglês e quaisquer leituras fora da lista surgem no chinês original da YTO. O estado geral da encomenda aparece sempre em português, como «Aceite pela transportadora» ou «Pronta para levantamento». Com `入柜|入库` o estado passa a «Pronta para levantamento»; se o estafeta voltar a tirar a encomenda (`小件员取出`), regressa a «Em trânsito».

Se a morada de entrega for o armazém na China de um transitário internacional, `PDA正常签收扫描` significa que esse armazém assinou a receção. A partir daí, passa a contar o número de seguimento próprio do transitário.

## Entrega da YTO na China: estafeta, cacifos e estações

Na **China**, um estafeta da YTO leva a encomenda à morada (`派件扫描`) ou esta fica disponível para recolha num cacifo ou estação (`入柜|入库`). A YTO indica o `95161` como «Courier Outbound Hotline», o número a partir do qual os seus estafetas telefonam aos destinatários.

As regras do mercado de correio expresso em vigor na **China** desde 1 de março de 2024 determinam que:

- A YTO tem de te informar de que podes verificar a encomenda no ato da entrega em confronto com a carta de porte; se a embalagem estiver visivelmente danificada ou o conteúdo for frágil, podes inspecionar o interior ou recusar a receção.
- Nenhuma empresa de entregas expresso pode confirmar a receção em teu nome nem depositar a encomenda num cacifo inteligente ou estação de serviço sem o teu consentimento.

Para pedir a devolução ou alterar a morada antes da entrega (`退回/更址`), a YTO remete designadamente para a sua aplicação e miniprograma. Segundo a política de privacidade, para corrigir a morada ou o telefone deves contactar o apoio ao cliente.

## YTO International: envios para fora da China

A YTO International (圆通国际) remonta a 2006 e declara mais de 50 escritórios em 18 países e regiões. O seu rastreador próprio em [ytoglobal.com/service](https://www.ytoglobal.com/service) está disponível em inglês («Express Freight Tracking») e chinês (`国际快递查询`) e aceita até 10 números separados por vírgulas ou ponto e vírgula.

As páginas da YTO não indicam que transportadora assegura a entrega final no teu país, referindo apenas parcerias com operadores logísticos mundiais de referência («top-notch global logistics service providers»). Para o serviço International Standard Express, destinado a particulares, a YTO aponta a partir do seu armazém internacional prazos meramente indicativos («for reference only») de 7 a 12 dias úteis para Portugal, Espanha, França, Alemanha, Itália, Polónia, Suíça, Reino Unido e Canadá (12 a 17 para Bulgária, Roménia e Angola), 10 a 13 para o Brasil, 9 a 12 para os Estados Unidos e 8 a 13 para a Austrália.

Em caso de ausência, as perguntas frequentes da YTO International aconselham a seguir o aviso de nova entrega («Redelivery Notification») para agendar gratuitamente uma segunda tentativa; se voltar a falhar, a YTO pede instruções ao remetente. Para combinar previamente uma hora de entrega, as FAQ indicam o número `400 60 95554`. Nos envios por via aérea, a alfândega e a segurança aeroportuária inspecionam as encomendas, podendo a alfândega apreender ou destruir remessas com artigos proibidos.

**Alfândega, territórios e avisos de fraude (UE e Portugal).** Os direitos e impostos dependem do país de destino: vê [encomendas retidas na alfândega](guide:customs). Na **União Europeia**, desde 1 de julho de 2026 as compras à distância provenientes de fora do território aduaneiro da UE em remessas com valor intrínseco até 150 € estão sujeitas a um direito aduaneiro de 3 € por categoria de artigo; desde 1 de novembro de 2026 acresce uma taxa de tramitação da União de 2 € por categoria de artigo, qualquer que seja o valor da encomenda (uma categoria reúne os bens de uma remessa com a mesma classificação pautal, a mesma descrição e, quando tem de ser declarada, a mesma origem, independentemente do número de unidades). Os Açores e a Madeira integram tanto o território aduaneiro como o território do IVA da UE. Em **Portugal**, o IVA, os direitos aduaneiros e a Taxa de Manuseamento Aduaneiro que não forem pagos no checkout são devidos à chegada da mercadoria; se os [CTT](carrier:ctt) te contactarem para concluir o desalfandegamento de um envio, pedem que disponibilizes a informação solicitada e consultes os encargos no portal de desalfandegamento dos CTT, e os CTT alertam na sua página de segurança contra mensagens fraudulentas de *phishing* por SMS ou e-mail que invocam taxas alfandegárias ou dados em falta com links falsos. Mais detalhes em [SMS falsos de entregas](guide:delivery-scam-texts).

## Encomenda YTO parada, perdida ou devolvida

**Ainda sem leituras.** Uma carta de porte recém-emitida permanece vazia até a YTO registar a primeira leitura física. Se continuar em branco, confirma o código junto do vendedor; numa remessa antiga, os dados podem já ter sido apagados após os três a seis meses de conservação.

**Seguimento parado.** Na **China**, o contrato de serviço expresso doméstico da YTO com os remetentes considera perdida uma encomenda sem atualizações durante 4 dias após o primeiro registo ou ainda por assinar 7 dias depois da data prevista. Contacta o vendedor que contratou o envio para que peça averiguações à YTO. Vê também [seguimento sem atualizações](guide:tracking-not-updating) e [seguir encomendas vindas da China](guide:tracking-from-china).

**Perdida ou danificada.** Numa compra online foi o vendedor quem pagou o envio: fala primeiro com ele. Ao abrigo desse mesmo contrato na **China**, uma encomenda sem valor declarado (`保价`) é indemnizada até 300 iuanes (até 100 iuanes no caso de documentos), enquanto uma encomenda com valor declarado é ressarcida pela perda efetiva até ao montante seguro. As reclamações fazem-se por telefone junto da filial YTO de expedição ou do apoio ao cliente da YTO; o site da YTO dispõe ainda, mediante início de sessão, de páginas para reclamações (`投诉反馈`) e indemnizações de valor declarado (`保价理赔`). Se a YTO não responder ou a resposta não resolver o problema, as regras chinesas permitem apresentar queixa ao regulador postal State Post Bureau (`消费者申诉` no respetivo site). Consulta igualmente [encomenda perdida](guide:lost-parcel).

**Devolvida.** Depois de `退回件扫描`, a encomenda segue de volta e as leituras de distribuição posteriores dizem respeito ao regresso ao remetente. Contacta o vendedor; vê [devolvida ao remetente](guide:returned-to-sender).

**Marcada como entregue, mas não chegou.** Na **China**, o contrato da YTO permite que coabitantes ou uma pessoa designada por ti assinem a receção, ou que o estafeta deposite a encomenda num cacifo ou estação perto da morada: verifica esses locais e a portaria do edifício. Vê [entregue mas não recebida](guide:delivered-not-received).

## Telefone e apoio ao cliente da YTO Express

Nas suas páginas, a YTO publica os seguintes números:

- **`95554`**: «Service Hotline» (`服务热线`) para a YTO Express dentro da China.
- **`400 60 95554`**: «Service Hotline» da YTO International para envios internacionais.
- **`95161`**: «Courier Outbound Hotline», a linha usada pelos estafetas da YTO para ligar aos destinatários.
- **`021-69777888`**: indicado ao lado dos restantes sem designação própria.

A YTO não indica horários de atendimento nem idiomas disponíveis nestas linhas. Na página inicial chinesa existe um botão de apoio ao cliente online (`在线客服`) e a YTO International disponibiliza um formulário de contacto. Para questões sobre a compra ou reembolso, fala com o vendedor.

## Seguir uma encomenda YTO Express com o Peek

O [Peek](/) identifica de imediato a YTO Express a partir de `YT` seguido de 13 algarismos. Já um código começado por `D` ou `G` com 11 algarismos também corresponde ao formato da Colis Privé, pelo que o Peek não o pode atribuir apenas pela forma. Cola-o ainda assim: o Peek acompanha mais de 3 500 transportadoras e consulta os serviços de seguimento («A verificar serviços de seguimento…»). Se a YTO Express não aparecer automaticamente, toca em «Alterar» e escolhe-a em «Corresponde a este número», ou usa «Alterar transportadora» numa encomenda já adicionada. O conector da YTO no Peek também aceita cartas de porte da YTO noutros formatos se selecionares YTO Express em «Todas as transportadoras». Um link `yto.net.cn` com `mailNo=` ou `waybillNo=` e o número atribui igualmente a encomenda à YTO.

O Peek lê o seguimento doméstico chinês da YTO, não o da YTO International: apresenta o estado, cada leitura devolvida pela YTO com a cidade da filial ou estação (ou o nome em chinês quando não consegue associá-lo a uma cidade) e a hora de entrega. As horas são lidas na hora da China (`UTC+8`: `20:00` em Pequim correspondem em Portugal continental a `12:00` WET no inverno e a `13:00` WEST na hora de verão) e convertidas para o fuso horário do teu dispositivo; em peektracker.com e nas aplicações para iPhone e Android, o histórico termina com «Horas no teu fuso horário:» e o respetivo nome. As leituras repetidas aparecem uma única vez. Logo após `退回件扫描` o estado muda para «Problema reportado», e o Peek apresenta o percurso de regresso em inglês como `Out for delivery back to the sender` e `In a parcel locker or station on its way back`, e a assinatura do remetente como `Returned to the sender` com o estado «Devolvida ao remetente».

A YTO não indica data prevista de entrega. O Peek omite nomes e números de telefone dos estafetas, dados do destinatário e moradas de recolha, visíveis apenas no rastreador da YTO. Como a YTO responde a qualquer número com formato válido, a falta de leituras não prova um erro de digitação: o Peek mostra «À espera de YTO Express» e continua a verificar. Se dois dias depois uma encomenda registada na YTO continuar desconhecida para a YTO e nenhuma outra fonte tiver respondido, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e passa a verificar diariamente e, decorrida uma semana desde que a adicionaste, semanalmente.

O Peek procura atualizações até a cada 10 minutos enquanto tiveres a encomenda aberta ou no dia da entrega com as notificações ativas, e até a cada 2 minutos a partir das 6:00 (hora da China) no dia em que a YTO regista a saída para distribuição (`派件扫描`) enquanto o rastreador próprio da YTO responder; através de serviços de seguimento, verifica no máximo uma vez por hora, a cada 3 horas após um dia sem leituras e até a cada 15 minutos no dia da entrega enquanto tiveres a encomenda aberta ou as notificações ativas. No resto do tempo e após dois dias sem novas leituras (12 horas enquanto mostrar «Aceite pela transportadora»), verifica de hora a hora. Podes ativar alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a YTO nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento YTO Express

### A YTO Express é fidedigna?

Sim. A YTO Express foi fundada em Xangai em 28 de maio de 2000. O grupo conta com duas empresas cotadas em bolsa — YTO Express (código 600233) e YTO International (06123.HK) — e declara 550 000 trabalhadores no final de 2024.

### O que significa 签收 no seguimento da YTO?

Entregue com assinatura: o estafeta entregou a encomenda e registou-a no seu terminal (`PDA正常签收扫描`), o que no Peek aparece como «Entregue». Pode ter sido recebida por ti, por alguém autorizado na tua morada ou pelo armazém de um transitário. Se ninguém tiver a encomenda, consulta [entregue mas não recebida](guide:delivered-not-received).

### Porque é que o meu número YTO não mostra seguimento?

Porque a YTO ainda não leu a encomenda, o número tem um erro ou já passaram mais do que os três a seis meses durante os quais a YTO conserva a consulta segundo a sua política de privacidade. Confirma o número com o vendedor ou cola-o no Peek, que continuará a consultá-lo.

### A YTO Express entrega fora da China?

Sim, através da YTO International e do seu rastreador em ytoglobal.com. As páginas da YTO não revelam que transportadora faz a entrega final no país de destino e dão prazos apenas de referência: 7 a 12 dias úteis para Portugal e a maioria dos países da UE e 10 a 13 dias úteis para o Brasil.

### Qual é o telefone da YTO Express?

O `95554` dentro da China e o `400 60 95554` para a YTO International, sem horários publicados. As chamadas vindas do `95161` partem dos estafetas da YTO.

:::sources
- [YTO Express: About YTO (em inglês)](https://www.yto.net.cn/en/aboutYto/about) – fundação em 28 de maio de 2000 em Xangai, empresas cotadas, mais de 5 000 filiais e 550 000 trabalhadores
- [YTO Express: 我要查件 (pesquisa de cartas de porte, em chinês)](https://www.yto.net.cn/ytoExpress/waybill/search) – rastreador apenas em chinês, até 10 números, páginas de reclamações e indemnizações
- [YTO Express: Domestic Express Services (em inglês)](https://www.yto.net.cn/en/domesticService) – plataformas servidas, devolução ou alteração de morada antes da entrega, linhas 95554 e 95161
- [YTO Express: Página inicial (em chinês)](https://www.yto.net.cn/) – apoio ao cliente online `在线客服`
- [YTO Express: 国内快递 (serviços domésticos, em chinês)](https://www.yto.net.cn/domesticService) – `退回/更址`, `服务热线`, `快递员外呼热线`
- [YTO Express: 国内快递服务协议 (contrato de serviço expresso doméstico, em chinês)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=DESA) – prazos de perda na China, indemnização com e sem valor declarado, reclamações e quem pode assinar
- [YTO Express: 圆通速递隐私政策 (política de privacidade, em chinês)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=PCPrivacyAgreement) – consulta disponível durante três a seis meses e alteração de morada através do apoio ao cliente
- [YTO Express: 寄快递 (enviar uma encomenda, em chinês)](https://www.yto.net.cn/ytoExpress/subCreatePages/create) – indemnização até 300 iuanes sem valor declarado
- [YTO Express: International Business Service (em inglês)](https://www.yto.net.cn/en/global/globalProduct) – linha YTO International 4006095554 e 021-69777888
- [YTO International: Company Introduction (em inglês)](https://www.ytoglobal.com/about-us) – atividade desde 2006, mais de 50 escritórios em 18 países e regiões e parceiros globais
- [YTO International: Express Freight Tracking](https://www.ytoglobal.com/service) – rastreador em inglês e chinês até 10 números
- [YTO International: International Standard Express (em inglês)](https://www.ytoglobal.com/product/express-intl) – serviço para particulares e prazos indicativos para Portugal, Brasil e outros destinos
- [YTO International: FAQ (em inglês)](https://www.ytoglobal.com/rich/question) – linha 400 60 95554, segunda tentativa gratuita, agendamento prévio e inspeção aduaneira
- [YTO International: Contact Us (em inglês)](https://www.ytoglobal.com/about-us/contact) – formulário de contacto
- [Ministério dos Transportes da China: 快递市场管理办法 (medidas sobre o mercado de correio expresso, em chinês)](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – em vigor na China desde 1 de março de 2024, verificação na entrega, consentimento para cacifos ou estações e queixa às autoridades postais
- [State Post Bureau da China: 国家邮政局主要职责 (atribuições principais, em chinês)](https://www.spb.gov.cn/gjyzj/c100001/c100006/201306/2722edab189242549b320d06e0dbd382.shtml) – entidade reguladora postal e do mercado expresso
- [State Post Bureau da China: página inicial (em chinês)](https://www.spb.gov.cn/) – portal de queixas dos consumidores (`消费者申诉`)
- [Comissão Europeia: EU Customs Reform (em inglês)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de 3 € por categoria de artigo até 150 € desde 1 de julho de 2026 e taxa de tramitação da União de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, em inglês)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de 2 € por categoria de artigo independentemente do valor e definição de categoria de artigo
- [Comissão Europeia: Territorial Scope (em inglês)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Açores e Madeira integrados no território aduaneiro e no território do IVA da UE
- [CTT: Fim da isenção de direitos aduaneiros em compras até 150 euros fora da União Europeia](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – encargos não pagos no checkout devidos à chegada a Portugal e o que fazer quando os CTT pedem para concluir o desalfandegamento
- [CTT: Alertas de Phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – avisos em Portugal sobre SMS e e-mails fraudulentos invocando taxas alfandegárias ou dados em falta
- [Comissão Europeia: Summertime (em inglês)](https://transport.ec.europa.eu/transport-themes/summertime_en) – hora de verão europeia do último domingo de março ao último domingo de outubro
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos reconhecidos, links de `yto.net.cn`, tradução das leituras da YTO, hora da China, percurso de devolução e dados mostrados ou omitidos pelo Peek
:::
